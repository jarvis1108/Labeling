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

@WebServlet(name = "Submit", value = "/Submit")
public class SubmitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String highlight_text = request.getParameter("highlight_text");

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        Interface inter = (Interface)session.getAttribute("inter");
        Content content = (Content)session.getAttribute("content");
        int contentIndex = (int)session.getAttribute("contentIndex");

        String username = user.getUsername();
        int InterfaceId = inter.getInterfaceId();
        String contentId = content.getContentId();
        String result = request.getParameter("result");

//        测试无效
//        request.setCharacterEncoding("utf-8");
//        response.setContentType("text/html;charset=utf-8");
//        String highlight_text = request.getParameter("highlight_text");

//        本地测试成功，但在部署到服务器后测试无效
//        String highlight_text = new String(request.getParameter("highlight_text").getBytes("iso-8859-1"),"utf-8");

        Result res = new Result();
        res.setUsername(username);
        res.setContentId(contentId);
        res.setInterfaceId(InterfaceId);
        res.setResult(result);
        res.setHighlight_text(highlight_text);

        ResultDao rd = new ResultDaoImpl();

        if(rd.submit(res)){
            request.getRequestDispatcher("/GetContent").forward(request, response);
        }else{
            session.setAttribute("errorInfo","提交失败，请重试");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
