package com.jhc.servlet;

import com.jhc.dao.ContentDao;
import com.jhc.dao.ContentDaoImpl;
import com.jhc.dao.ResultDao;
import com.jhc.dao.ResultDaoImpl;
import com.jhc.entity.Content;
import com.jhc.entity.Interface;
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
        Interface inter = (Interface)session.getAttribute("inter");
        List<Content> contentList = (List<Content>)session.getAttribute("contentList");
        int contentIndex = (int)session.getAttribute("contentIndex");
        Content content = contentList.get(contentIndex);

        String username = user.getUsername();
        int InterfaceId = inter.getInterfaceId();
        String contentId = content.getContentId();
        String result = request.getParameter("result");

        Result res = new Result();
        res.setUsername(username);
        res.setContentId(contentId);
        res.setInterfaceId(InterfaceId);
        res.setResult(result);

        ResultDao rd = new ResultDaoImpl();

        if(rd.submit(res)){
            request.getRequestDispatcher("/GetContentServlet").forward(request, response);
        }else{
            session.setAttribute("errorInfo","提交失败，请重试");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
