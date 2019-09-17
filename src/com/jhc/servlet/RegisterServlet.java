package com.jhc.servlet;

import com.jhc.dao.InterfaceDao;
import com.jhc.dao.InterfaceDaoImpl;
import com.jhc.dao.UserDao;
import com.jhc.dao.UserDaoImpl;
import com.jhc.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username"); //获取jsp页面传过来的参数
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        String age = request.getParameter("age");
        String education = request.getParameter("education");
        String profession = request.getParameter("profession");
        String labeling_exp = request.getParameter("labeling_exp");
        String reading_exp = request.getParameter("reading_exp");
        //获取可用的界面类型
        InterfaceDao id = new InterfaceDaoImpl();
        int interfaceId = id.getAvailableInterface();

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setSex(sex);
        user.setAge(age);
        user.setEducation(education);
        user.setProfession(profession);
        user.setLabeling_exp(labeling_exp);
        user.setReading_exp(reading_exp);
        user.setInterfaceId(interfaceId);

        UserDao ud = new UserDaoImpl();

        if(ud.register(user)){
            user = ud.getUser(user.getUsername(),user.getPassword());
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            request.getRequestDispatcher("/GetContentServlet").forward(request, response);
        }else{
            request.setAttribute("errorInfo","该手机号码已注册，请直接登录");
            request.getRequestDispatcher("/login.jsp").forward(request, response);

        }
    }

}
