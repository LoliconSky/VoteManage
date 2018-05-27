package com.bfchengnuo.servlet;

import com.bfchengnuo.common.bean.ChooseAndSelect;
import com.bfchengnuo.common.bean.TopicPlus;
import com.bfchengnuo.common.bean.UserTopicChoose;
import com.bfchengnuo.po.Choose;
import com.bfchengnuo.po.Topic;
import com.bfchengnuo.po.UserChoose;
import com.bfchengnuo.po.Users;
import com.bfchengnuo.service.VoteService;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by 冰封承諾Andy on 2018/5/26.
 */
@WebServlet(name = "VoteServlet", urlPatterns = "/VoteServlet")
public class VoteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/plain; charset=UTF-8");

        String method = request.getParameter("method");
        if (method == null) {
            queryAllTopic2Jsp(request, response);
            return;
        }

        try {
            Method method1 = this.getClass().getDeclaredMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            method1.invoke(this, request, response);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void queryAllTopic2Jsp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        VoteService voteService = new VoteService();
        List<Topic> data = voteService.queryTopicAndChoose();

        Users user = (Users) request.getSession().getAttribute("user");
        System.out.println(user);
        if (user == null) {
            // 未登录
            response.sendRedirect(request.getContextPath() + "/login.html");
            return;
        }

        // 查询用户已选择的结果
        List<UserTopicChoose> userChooses = voteService.queryChooseByUid(user.getId());

        // 根据用户选择构造新表
        List<TopicPlus> list = getTopicPlus(data, userChooses);

        request.setAttribute("list", list);
        request.setAttribute("uid", user.getId());
        request.getRequestDispatcher("/vote.jsp").forward(request, response);
    }

    /**
     * 构造题目-选项列表-用户是否选择 的信息
     * 三层遍历，效率极低，慎用
     * 待优化，深夜精神不佳
     * @param data 题目列表
     * @param utc 用户选择的列表
     * @return 新的构造 bean
     */
    private List<TopicPlus> getTopicPlus(List<Topic> data, List<UserTopicChoose> utc) {
        List<TopicPlus> plusList = new ArrayList<>();
        // 遍历每个菜单
        data.forEach(topic -> {
            TopicPlus tp = new TopicPlus();
            tp.setTopic(topic);
            // 遍历每个选项
            List<ChooseAndSelect> list = tp.getChooseAndSelect();
            topic.getChoosesById().forEach(choose -> {
                ChooseAndSelect cs = new ChooseAndSelect();
                cs.setChoose(choose);
                // 判断此选项是否已被选择
                if (utc != null) {
                    try {
                        utc.forEach(userTopicChoose -> {
                            if (userTopicChoose != null && userTopicChoose.getCid() != null && userTopicChoose.getCid() == choose.getId()) {
                                cs.setSelect(true);
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                list.add(cs);
            });

            plusList.add(tp);
        });
        return plusList;
    }


    protected void addChoose(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        VoteService voteService = new VoteService();
        String uid = request.getParameter("uid");
        Map<String, String[]> parameterMap = request.getParameterMap();

        List<String> params = new ArrayList<>();
        parameterMap.forEach((key, value) -> {
            if (!"uid".equals(key) && !"method".equals(key)) {
                Collections.addAll(params, value);
            }
        });

        Users user = voteService.queryUserById(Integer.parseInt(uid));
        // 删除此用户的所有选择
        voteService.removeAllChoose(user);

        List<UserChoose> list = new ArrayList<>();
        for (String choose : params) {
            UserChoose userChoose = new UserChoose();
            userChoose.setUsersByUid(user);
            userChoose.setChooseByCid(new Choose(Integer.parseInt(choose)));
            userChoose.setSubdate(new Timestamp(new Date().getTime()));
            list.add(userChoose);
        }

        voteService.addUserChooseList(list);
        response.sendRedirect(request.getContextPath() + "/suc.html");
    }

    protected void queryAllTopic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        VoteService voteService = new VoteService();
        List<Topic> list = voteService.queryTopicAndChoose();

        JsonConfig config = new JsonConfig();
        config.setExcludes(new String[]{"chooseByCid", "usersByUid", "subdate", "topicByTid"});

        JSONArray ja = JSONArray.fromObject(list, config);
        response.getWriter().print(ja);
    }

    protected void queryUsersByCid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        VoteService voteService = new VoteService();
        List<Users> list = voteService.queryChooseUsers(Integer.parseInt(cid));

        JsonConfig config = new JsonConfig();
        config.setExcludes(new String[]{"userChoosesById", "password"});
        JSONArray ja = JSONArray.fromObject(list, config);
        response.getWriter().print(ja);

        // request.setAttribute("list", list);
        // request.getRequestDispatcher("/show.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
