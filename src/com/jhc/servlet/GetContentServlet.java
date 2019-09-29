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

@WebServlet(name = "GetContent", value = "/GetContent")
public class GetContentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Interface inter = (Interface)session.getAttribute("inter");
        User user = (User)session.getAttribute("user");

        int interOffset = inter.getOffset();
        int number = inter.getNumber();

        ContentDao td = new ContentDaoImpl();
        Content content = new Content();

        //获取下一页内容索引
        ResultDao rd = new ResultDaoImpl();
        String username = user.getUsername();
        int userOffset = rd.countResultsByUsername(username);
        int contentIndex = userOffset;
        int contentTotal = number;

        //context
        int interfaceId = inter.getInterfaceId();
        switch (interfaceId){
            case 4: case 7: case 9: case 10: case 12: case 13: case 14: case 15:{
                content = td.getContentMix(interOffset,userOffset);
                break;
            }
            default:{
                content = td.getContent(interOffset + userOffset);
                break;
            }
        }

        //highlight
        boolean highlight = false;
        switch (interfaceId){
            case 1: case 5: case 6: case 7: case 11: case 12: case 14: case 15:{
                highlight = true;
                break;
            }
            default:{ break;}
        }

        //guideline
        boolean guideline = false;
        switch(interfaceId){
            case 2: case 5: case 8: case 9: case 11: case 12: case 13: case 15:{
                guideline = true;
                break;
            }
            default: break;
        }

        if(contentIndex < contentTotal){
            session.setAttribute("content",content);
            session.setAttribute("highlight",highlight);
            session.setAttribute("guideline",guideline);
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
