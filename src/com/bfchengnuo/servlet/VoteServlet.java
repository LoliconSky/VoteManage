package com.bfchengnuo.servlet;

import com.bfchengnuo.po.Topic;
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

        try {
            Method method1 = this.getClass().getDeclaredMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            method1.invoke(this, request, response);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    protected void queryAllTopic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        VoteService voteService = new VoteService();
        List<Topic> list = voteService.queryTopicAndChoose();

        JsonConfig config = new JsonConfig();
        config.setExcludes(new String[]{"chooseByCid", "usersByUid", "subdate","topicByTid"});

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
