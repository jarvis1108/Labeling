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

//        //监督实验：取得当前需要标注的界面
//        if(interfaceIdExp == 16){
//            InterfaceDao id = new InterfaceDaoImpl();
//            Interface inter = ((InterfaceDaoImpl) id).getExpInterface(username,interOffsetExp);
//            interfaceId = interOffsetExp;
//            interOffset = inter.getOffset();
//            number = inter.getNumber();
//        } else {
//            interfaceId = interfaceIdExp;
//            interOffset = interOffsetExp;
//            number = numberExp;
//        }

        //获取下一页内容索引
//        int userOffset = -1;
//        userOffset = rd.countResultsByUsername(username);
//        int contentIndex = userOffset;
//        int contentTotal = number;


//        //监督实验：取得当前用户对应当前界面的结果数
//        int userOffset = -1;
//        if(interfaceIdExp == 16){
//            userOffset = rd.countResultsByUsernameAndInterfaceId(username, interfaceId);
//        }else{
//            userOffset = rd.countResultsByUsername(username);
//        }


        //content 每次取单条内容
//        Content content = new Content();
//        //context
//        switch (interfaceId){
//            case 4: case 7: case 9: case 10: case 12: case 13: case 14: case 15:{
//                content = td.getContentMix(interOffset,userOffset);
//                break;
//            }
//            default:{
//                content = td.getContent(interOffset + userOffset);
//                break;
//            }
//        }

        //contents 一次取出全部内容

        //从session获取参数
        HttpSession session = request.getSession();
        List<Content> contents = (ArrayList<Content>) session.getAttribute("contents");
        Integer contentIndex = (Integer)session.getAttribute("contentIndex") ;
        Integer contentTotal = (Integer)session.getAttribute("contentTotal");
        Boolean highlight = (Boolean)session.getAttribute("highlight");
        Boolean guideline = (Boolean)session.getAttribute("guideline");

        //首次登陆，获取全部内容
        if(null == contents){
            User user = (User)session.getAttribute("user");
            Interface inter = (Interface)session.getAttribute("inter");
            String username = inter.getUsername();
            ResultDao rd = new ResultDaoImpl();
            contentIndex = rd.countResultsByUsername(username);

            int interfaceId = inter.getInterfaceId();
            int interOffset = inter.getOffset();//当前界面编号
            int number = inter.getNumber();//总界面数
            ContentDao td = new ContentDaoImpl();
            contents = new ArrayList<>();

            //context
            switch (interfaceId){
                case 4: case 7: case 9: case 10: case 12: case 13: case 14: case 15:{
                    contents = td.getContentMixAll(interOffset, number);
                    break;
                }
                default:{
                    contents = td.getContentAll(interOffset, number);
                    break;
                }
            }

            //pageNum
            contentTotal = number;

            //highlight
            switch (interfaceId){
                case 1: case 5: case 6: case 7: case 11: case 12: case 14: case 15:{
                    highlight = true;
                    break;
                }
                default:{
                    highlight = false;
                    break;
                }
            }

            //guideline
            switch(interfaceId){
                case 2: case 5: case 8: case 9: case 11: case 12: case 13: case 15:{
                    guideline = true;
                    break;
                }
                default: {
                    guideline = false;
                    break;
                }
            }
        }




        if(contentIndex < contentTotal){
//            session.setAttribute("content",content);
            session.setAttribute("contents",contents);
            session.setAttribute("highlight",highlight);
            session.setAttribute("guideline",guideline);
            session.setAttribute("contentIndex",contentIndex);
            session.setAttribute("contentTotal",contentTotal);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }else {
//            //监督实验：标注完一种界面后，继续下一界面的标注，直到全部完成
//            if(interOffsetExp == 15){
//                request.getRequestDispatcher("/thanks.jsp").forward(request, response);
//            }else{
//                request.getRequestDispatcher("/breakoff.jsp").forward(request, response);
//            }

            request.getRequestDispatcher("/thanks.jsp").forward(request, response);
        }
}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
