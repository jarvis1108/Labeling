<%@ page import="com.jhc.entity.Content" %>
<%@ page import="java.util.List" %>
<%@ page import="com.jhc.entity.User" %>
<%@ page import="com.jhc.entity.Interface" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.SplittableRandom" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
        <link rel="alternate icon" type="image/png" href="assets/i/HCI Logo.png">
        <link rel="stylesheet" type="text/css" href="assets/css/amazeui.min.css"/>
        <script src="assets/js/jquery-3.4.1.slim.min.js"></script>

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

            .blog-team li {
                padding: 4px;
            }

            .blog-team img {
                margin-bottom: 0;
            }

            .am-icon-info-circle{
                color: #636262;
            }

            /*::selection {*/
                /*background-color: #f2e94f;*/
            /*}*/

            .am-badge{
                opacity:0.8;
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
            String username = "";
            String deadline = "";
            //检查用户登录情况
            if(null != user){
                username = user.getUsername();
                Date finish_time =  new Date(user.getFinish_time().getTime());
                DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                deadline = sdf.format(finish_time);
            } else {
                response.sendRedirect("/Labeling/login.jsp");
            }
        %>

        <div class="am-collapse am-topbar-collapse" id="doc-topbar-collapse">
            <ul class="am-nav am-nav-pills am-topbar-nav">
                <li class="am-active"><a href="index.jsp">标注</a></li>
                <li><a><%=username %></a></li>
            </ul>
        </div>
    </header>

    <%
        //获取页码
        Integer contentIndex = 0;
        Integer contentTotal = 0;
        if(null != session.getAttribute("contentIndex")){
            contentIndex = (Integer) session.getAttribute("contentIndex");
        }
        int pageNum = contentIndex + 1;
        if(null != session.getAttribute("contentTotal")){
            contentTotal = (Integer)session.getAttribute("contentTotal");
        }
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
                        <p id="text-content">
                            <%
                                //获取文本内容
                                String text = "";

                                //content
//                                Content content = (Content)session.getAttribute("content");

                                //contents
                                List<Content> contents = (List<Content>) session.getAttribute("contents");
                                Content content = contents.get(contentIndex);

                                if(null != content){
                                    text = content.getContent().replace(" ","").replace("null","");
                                }
                                out.println(text);
                            %>
                        </p>
                    </div>
                </div>
            </article>

            <hr class="am-article-divider blog-hr">
            <button type="button" class="am-btn am-btn-block am-btn-danger btn-submit" onclick="submit(1)">涉黄</button>
            <button type="button" class="am-btn am-btn-block am-btn-secondary btn-submit" onclick="submit(0)">正常</button>
            <br>
            <p><span class="am-icon-info-circle"></span> 若涉黄，请在文本中拖选出一个或多个 <span style="color:#dd514c;"><b>判断依据</b></span></p>
            <p><span class="am-icon-info-circle"></span> 请在 <a><%=deadline%></a> 前完成标注</p>

        </div>

        <div class="col-md-3 col-sm-offset-1 blog-sidebar">
            <div class="am-panel-group" style="
                    <%
                        //控制是否显示guideline
                        Boolean guideline = false;
                        if(null != session.getAttribute("guideline")){
                            guideline = (Boolean)session.getAttribute("guideline");
                        }
                        if(!guideline){
                            out.println("display:none;");
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

            <div class="am-panel am-panel-danger" id="blog-highlight">
                <div class="am-panel-hd">
                    <h3 class="am-panel-title">判断依据</h3>
                </div>
                <ul class="am-list am-list-static" id="blog-highlight-list">
                </ul>
            </div>

        </div>
    </div>

    <script>
        var date = new Date();
        var startTime = date.getTime();

        function submit(result) {
            $('.btn-submit').attr("disabled",true);

            var myForm = document.createElement("form");
            myForm.method = "post";
            myForm.action = "Submit";

            var childResult = document.createElement("input");
            childResult.setAttribute("type", "text");
            childResult.setAttribute("name", "result");
            childResult.setAttribute("value", result);
            myForm.appendChild(childResult);

            //计时器
            var endDate = new Date();
            var endTime = endDate.getTime();
            var leftTime = endTime - startTime;
            var cost = -1;
            if(leftTime > 0 ){
                cost = Math.floor(leftTime/1000%60);
            }
        var childResult = document.createElement("input");
            childResult.setAttribute("type", "text");
            childResult.setAttribute("name", "cost");
            childResult.setAttribute("value", cost);
            myForm.appendChild(childResult);

            if(1 == result){

                //多条敏感内容使用//分隔
                var highlight_text = "";
                var $highlight_childs = $("#blog-highlight-list").children();
                for(i=0;i<$highlight_childs.length;i++){
                    highlight_text += $highlight_childs[i].innerText + "//";
                }

                //判断涉黄，检查是否选择了判断依据
                if ("" == highlight_text) {
                    alert("请在文本中拖选出您的判断依据");
                    $('.btn-submit').attr("disabled",false);
                } else {
                    //若已选择，提交判断依据
                    var childHighlight = document.createElement("input");
                    childHighlight.setAttribute("type", "text");
                    childHighlight.setAttribute("name", "highlight_text");
                    childHighlight.setAttribute("value", highlight_text);
                    myForm.appendChild(childHighlight);
                    document.body.appendChild(myForm);
                    myForm.submit();
                    document.body.removeChild(myForm);
                }
                //判断不涉黄，正常提交
            } else {
                document.body.appendChild(myForm);
                myForm.submit();

                document.body.removeChild(myForm);
            }
        }

        // 显示已选择的判断依据
        $('#blog-highlight').hide();
        $("#text-content").mouseup(function () {
            $('#blog-highlight').show();
            var highlight_text = window.getSelection() + "";
            if("" != highlight_text){
                var child = "<li>"+ highlight_text +"<span class='am-btn am-badge am-badge-danger am-radius am-icon-close blog-highlight-child' onclick='remove(this)'> </span></li>";
                $('#blog-highlight-list').append(child);
            }
        });

        //取消已选择的判断依据
        function remove(obj) {
            obj.parentNode.parentNode.removeChild(obj.parentNode);
        };

        //highlight：控制敏感词样式
        $(function (){
            <%
                  Boolean highlight = false;
                  if(null != session.getAttribute("highlight")){
                      highlight = (Boolean)session.getAttribute("highlight");
                  }
                  if(highlight){
                      String wordList = content.getWordList();
                       String[] words = wordList.split(",");
                      for(String word : words){
                          out.println("var txt = document.getElementById(\"text-content\").innerHTML;");
                          out.println("document.getElementById(\"text-content\").innerHTML = txt.replace(\"" + word + "\",\"" + "<span style='color:#dd514c'>"+ word +"</span>\");");
                      }
                  }
            %>
        });

    </script>
    </body>
</html>
