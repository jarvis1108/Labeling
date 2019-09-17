package com.jhc.servlet;

import com.jhc.dao.ContentDao;
import com.jhc.dao.ContentDaoImpl;
import com.jhc.dao.ResultDao;
import com.jhc.dao.ResultDaoImpl;
import com.jhc.entity.Content;
import com.jhc.entity.Result;
import com.jhc.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SubmitServlet", value = "/SubmitServlet")
public class SubmitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        Content content = (Content)session.getAttribute("content") ;

        String username = user.getUsername();
        int contentId = content.getContentId();
        int InterfaceId = user.getInterfaceId();
        String result = request.getParameter("result");

        Result res = new Result();
        res.setUsername(username);
        res.setContentId(contentId);
        res.setInterfaceId(InterfaceId);
        res.setResult(result);

        ResultDao rd = new ResultDaoImpl();

        if(rd.submit(res)){
            //TODO：提交后选中下一条内容
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }else{
            //TODO：出错页面
            request.setAttribute("errorInfo","提交失败");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
