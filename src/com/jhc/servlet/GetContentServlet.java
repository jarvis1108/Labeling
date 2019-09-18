package com.jhc.servlet;

import com.jhc.dao.*;
import com.jhc.entity.Content;
import com.jhc.entity.Interface;
import com.jhc.entity.User;

import javax.jws.soap.SOAPBinding;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "GetContentServlet", value = "/GetContentServlet")
public class GetContentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Interface inter = (Interface)session.getAttribute("inter");
        User user = (User)session.getAttribute("user");

        int offset = inter.getOffset();
        int number = inter.getNumber();

        ContentDao td = new ContentDaoImpl();
        List<Content> contentList = new ArrayList<>();

        //TODO：context
        int interfaceId = inter.getInterfaceId();
        switch (interfaceId){
            case 4: case 7: case 9: case 10: case 12: case 13: case 14:{
                contentList = td.getContentMix(offset, number);
                break;
            }
            default:{
                contentList = td.getContentAll(offset, number);
                break;
            }
        }

        //获取下一页内容索引
        ResultDao rd = new ResultDaoImpl();
        String username = user.getUsername();
        int contentIndex = rd.countResultsByUsername(username);
        int contentTotal = number;

        if(contentIndex < contentTotal){
            session.setAttribute("contentList",contentList);
            session.setAttribute("contentIndex",contentIndex);
            session.setAttribute("contentTotal",contentTotal);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }else {
            request.getRequestDispatcher("/thanks.jsp").forward(request, response);
        }

}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
