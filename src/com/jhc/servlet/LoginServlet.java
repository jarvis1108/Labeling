package com.jhc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jhc.dao.*;
import com.jhc.entity.Interface;
import com.jhc.entity.User;
import com.jhc.tools.GetMixContent;
import com.jhc.tools.ValidationSelector;

@WebServlet(name = "Login", value = "/Login")
public class LoginServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //validation
//        ValidationSelector vs = new ValidationSelector();
//        vs.select();

        //getMixContent
//        GetMixContent gmc = new GetMixContent();
//        gmc.getTable();

        //wordListInit
//        ContentDao cd = new ContentDaoImpl();
//        ((ContentDaoImpl) cd).wordListInit();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDao ud = new UserDaoImpl();
        InterfaceDao id = new InterfaceDaoImpl();
        HttpSession session = request.getSession();

        if(ud.login(username, password)){
            Interface inter = id.getInterface(username);
            User user = ud.getUser(username, password);

            session.setAttribute("inter", inter);
            session.setAttribute("user", user);
            request.getRequestDispatcher("/GetContent").forward(request, response);
        }else{
            request.setAttribute("errorInfo","用户名或密码错误，请重试");
            request.getRequestDispatcher("/login.jsp").forward(request, response);

        }
    }

}

