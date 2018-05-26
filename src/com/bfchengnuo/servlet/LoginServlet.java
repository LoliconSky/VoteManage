package com.bfchengnuo.servlet;

import com.bfchengnuo.po.Users;
import com.bfchengnuo.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by 冰封承諾Andy on 2018/5/26.
 */
@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");

        String method = request.getParameter("method");

        try {
            Method method1 = this.getClass().getDeclaredMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            method1.invoke(this, request, response);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");

        LoginService loginService = new LoginService();
        Users user = loginService.queryUserByName(name);
        if (user == null || !user.getPassword().equals(pwd)) {
            response.sendRedirect(request.getContextPath() + "/login.html");
            return;
        }
        // 登陆成功
        request.getSession().setAttribute("user", user);
        response.sendRedirect(request.getContextPath() + "/VoteServlet");
    }

    protected void reg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");

        LoginService loginService = new LoginService();
        Users user = loginService.queryUserByName(name);

        if (user != null) {
            response.sendRedirect(request.getContextPath() + "/register.html");
            return;
        }
        user = new Users();
        user.setName(name);
        user.setPassword(pwd);
        loginService.addUser(user);
        response.sendRedirect(request.getContextPath() + "/login.html");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
