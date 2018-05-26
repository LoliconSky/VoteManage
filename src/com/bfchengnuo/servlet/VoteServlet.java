package com.bfchengnuo.servlet;

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
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

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
        List<Topic> list = voteService.queryTopicAndChoose();

        Users user = (Users) request.getSession().getAttribute("user");
        System.out.println(user);
        if (user == null) {
            // 未登录
            response.sendRedirect(request.getContextPath() + "/login.html");
            return;
        }

        request.setAttribute("list", list);
        request.setAttribute("uid", user.getId());
        request.getRequestDispatcher("/vote.jsp").forward(request, response);
    }


    protected void addChoose(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        VoteService voteService = new VoteService();
        String uid = request.getParameter("uid");
        Enumeration<String> parameterNames = request.getParameterNames();
        List<String> params = new ArrayList<>();
        while (parameterNames.hasMoreElements()) {
            String s = parameterNames.nextElement();
            if (!"uid".equals(s) && !"method".equals(s)) {
                params.add(s);
            }
        }

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
