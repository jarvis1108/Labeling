package com.jhc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jhc.dao.UserDao;
import com.jhc.dao.UserDaoImpl;
import com.jhc.entity.User;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDao ud = new UserDaoImpl();

        if(ud.login(username, password)){
            User user = ud.getUser(username,password);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            request.getRequestDispatcher("/GetContentServlet").forward(request, response);
        }else{
            request.setAttribute("errorInfo","用户名或密码错误，请重试");
            request.getRequestDispatcher("/login.jsp").forward(request, response);

        }
    }

}

