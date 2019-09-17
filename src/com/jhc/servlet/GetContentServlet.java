package com.jhc.servlet;

import com.jhc.dao.ContentDao;
import com.jhc.dao.ContentDaoImpl;
import com.jhc.entity.Content;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetContentServlet", value = "/GetContentServlet")
public class GetContentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ContentDao td = new ContentDaoImpl();
        Content content = ((ContentDaoImpl) td).getContent();
        HttpSession session = request.getSession();
        session.setAttribute("content",content);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
