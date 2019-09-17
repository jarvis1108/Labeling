<%@ page import="com.jhc.entity.Content" %>
<%@ page import="java.util.List" %>
<%@ page import="com.jhc.entity.User" %>
<%@ page import="com.jhc.entity.Interface" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Labeling | WHU HCI</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport"
              content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <meta name="format-detection" content="telephone=no">
        <meta name="renderer" content="webkit">
        <meta http-equiv="Cache-Control" content="no-siteapp"/>
        <link rel="alternate icon" type="image/png" href="assets/i/favicon.png">
        <link rel="stylesheet" href="assets/css/amazeui.min.css"/>

        <style>
            @media only screen and (min-width: 1200px) {
                .blog-g-fixed {
                    max-width: 1200px;
                }
            }

            @media only screen and (min-width: 641px) {
                .blog-sidebar {
                    padding: 20px 0;
                    font-size: 1.4rem;
                }
            }

            .blog-main {
                padding: 20px 0;
            }

            .blog-index {
                padding: 10px 0;
            }

            .page-current{
                font-size: 5rem;
            }

            .page-total{
                font-size: 2.4rem;
            }

            /* highlight */
            .blog-meta {
                font-size: 14px;
                margin: 10px 0 20px 0;
                color: #222;
            }

            .blog-meta a {
                color: #27ae60;
            }

            .blog-pagination a {
                font-size: 1.4rem;
            }

            .blog-team li {
                padding: 4px;
            }

            .blog-team img {
                margin-bottom: 0;
            }

        </style>
    </head>
    <body>
    <header class="am-topbar">
        <h1 class="am-topbar-brand">
            <a href="http://hci.whu.edu.cn/">WHU HCI</a>
        </h1>

        <%
            User user = (User)session.getAttribute("user");
            String username = user.getUsername();;
        %>

        <div class="am-collapse am-topbar-collapse" id="doc-topbar-collapse">
            <ul class="am-nav am-nav-pills am-topbar-nav">
                <li class="am-active"><a href="index.jsp">标注</a></li>
                <li><a href="my.jsp">我的</a></li>
                <li><a><%=username %></a></li>
            </ul>

            <div class="am-topbar-right">
                <button class="am-btn am-btn-secondary am-topbar-btn am-btn-sm"><span class="am-icon-pencil"></span> 注册</button>
            </div>
        </div>
    </header>

    <%
        int contentIndex = (int)session.getAttribute("contentIndex");
        int pageNum = contentIndex + 1;
        int contentTotal = (int)session.getAttribute("contentTotal");
    %>

    <div class="am-g am-g-fixed blog-g-fixed">

        <div class="col-md-1 blog-index">
            <a class="page-current"><%= pageNum %></a>
            <a>/</a>
            <a class="page-total"><%= contentTotal %></a>
        </div>


        <div class="col-md-6 col-sm-offset-1">
            <article class="blog-main">
                <div class="am-g blog-content">
                    <div class="col-sm-12">
                        <p class="text-content">
                            <%
                                String text = "";
                                List<Content> contentList = (List<Content>)(session.getAttribute("contentList"));
                                Content content = contentList.get(contentIndex);
                                text = content.getContent().replace(" ","");
                                out.println(text);
                            %>
                        </p>
                    </div>
                </div>
            </article>

            <hr class="am-article-divider blog-hr">
            <button type="button" class="am-btn am-btn-block am-btn-danger" onclick="submit(1)">涉黄</button>
            <button type="button" class="am-btn am-btn-block am-btn-secondary" onclick="submit(0)">正常</button>

        </div>

        <div class="col-md-3 col-sm-offset-1 blog-sidebar">
            <div class="am-panel-group" style="
                    <%
                        //控制是否显示guideline
                        Interface inter = (Interface)session.getAttribute("inter");
                        int interfaceId = inter.getInterfaceId();
                        switch(interfaceId){
                            case 1: case 3: case 4: case 6: case 7: case 10:{
                                out.println("display:none;");
                                break;
                            }
                            default: ;break;
                        }
                     %>
            ">
                <section class="am-panel am-panel-default">
                    <div class="am-panel-hd">判断标准</div>
                    <div class="am-panel-bd">
                        <h3>《中华人民共和国刑法（2017注释版）》</h3>
                        <p>第三百六十七条 【淫秽物品的范围】本法所称淫秽物品，是指具体描绘性行为或者露骨宣扬色情的诲淫性的书刊、影片、录像带、录音带、图片及其他淫秽物品。有关人体生理、医学知识的科学著作不是淫秽物品。包含有色情内容的有艺术价值的文学、艺术作品不视为淫秽物品。</p>
                        <h3>《正确认定处罚“扫黄”涉及的犯罪案件》</h3>
                        <p>刘星明, 阮方民发表于《法学研究》</p>
                        <p>在一些文学艺术作品里, 出于情节的需要, 表现男女私情时, 有些细微描写或形象的图画, 但整个作品的基本格调是高雅的, 健康的, 有益的, 这类书刊或其音像、电影作品不应列淫秽、色情出版物的范畴。而对那些夹有色情、裸体半裸体东西的目的, 是为了变着法子, 拐弯抹角地兜售和宣扬淫秽的东西或者是以淫秽的东西作为招徕观众或读者的手段, 基本格调是低下的、庸俗的、萎靡的, 是有害于青少年身心健康的, 社会效果很坏的作品, 就应列为淫秽、色情出版物。</p>
                    </div>
                </section>
            </div>
        </div>
    </div>

    <script>

        function submit(result){

            var myForm = document.createElement("form");
            myForm.method = "post";
            myForm.action = "SubmitServlet";

            var myInput = document.createElement("input");
            myInput.setAttribute("name", "result");
            myInput.setAttribute("value", result);
            myForm.appendChild(myInput);

            document.body.appendChild(myForm);
            myForm.submit();
            document.body.removeChild(myForm);
        }

    </script>
    </body>
</html>
