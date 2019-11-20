package com.jhc.servlet;

import com.jhc.dao.*;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Submit", value = "/Submit")
public class SubmitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String highlight_text = request.getParameter("highlight_text");
        int cost =  Integer.parseInt(request.getParameter("cost"));

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        Interface inter = (Interface)session.getAttribute("inter");
        Integer contentIndex = (Integer) session.getAttribute("contentIndex");
//        Content content = (Content)session.getAttribute("content");
        List<Content> contents = (ArrayList<Content>)session.getAttribute("contents");
        Content content = contents.get(contentIndex);

        String username = user.getUsername();

//        //监督实验：取得实际界面inter
//        Interface inter = new Interface();
//        int interfaceIdExp = interExp.getInterfaceId();
//        int interOffsetExp = interExp.getOffset();
//        if(interfaceIdExp == 16){
//            InterfaceDao id = new InterfaceDaoImpl();
//            inter = ((InterfaceDaoImpl) id).getExpInterface(username,interOffsetExp);
//        }else{
//            inter = interExp;
//        }

        int InterfaceId = inter.getInterfaceId();
        String contentId = content.getContentId();
        String result = request.getParameter("result");

        Result res = new Result();
        res.setUsername(username);
        res.setContentId(contentId);
        res.setInterfaceId(InterfaceId);
        res.setResult(result);
        res.setHighlight_text(highlight_text);
        res.setCost(cost);

        ResultDao rd = new ResultDaoImpl();

        if(rd.submit(res)){
//            request.getRequestDispatcher("/GetContent").forward(request, response);

            //将页面索引置为下一页
            session.setAttribute("contentIndex",contentIndex + 1);
            response.sendRedirect("/Labeling/GetContent");
        }else{
            session.setAttribute("errorInfo","提交失败，请重试");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
