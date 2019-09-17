<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>WHU HCI | Text Labeling System</title>
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

            .blog-title {
                margin: 10px 0 20px 0;
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

        <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only"
                data-am-collapse="{target: '#doc-topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span
                class="am-icon-bars"></span></button>

        <div class="am-collapse am-topbar-collapse" id="doc-topbar-collapse">
            <ul class="am-nav am-nav-pills am-topbar-nav">
                <li class="am-active"><a href="#">首页</a></li>
                <li><a href="#">项目</a></li>
                <li class="am-dropdown" data-am-dropdown>
                    <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
                        菜单 <span class="am-icon-caret-down"></span>
                    </a>
                    <ul class="am-dropdown-content">
                        <li class="am-dropdown-header">标题</li>
                        <li><a href="#">关于我们</a></li>
                        <li><a href="#">标注记录</a></li>
                        <li><a href="#">TIPS</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </header>

    <%
        int pageNum=2;
        int pageTotal=10;
    %>

    <div class="am-g am-g-fixed blog-g-fixed">

        <div class="col-md-1 blog-index">
            <a class="page-current"><%=pageNum %></a>
            <a>/</a>
            <a class="page-total"><%=pageTotal %></a>
        </div>


        <div class="col-md-6 col-sm-offset-1">
            <article class="blog-main">
                <%--<h3 class="am-article-title blog-title">--%>
                    <%--<a href="#">第一章</a>--%>
                <%--</h3>--%>

                <!-- <h4 class="am-article-meta blog-meta">by <a href="">open</a> posted on 2014/06/17 under <a href="#">字体</a></h4> -->

                <div class="am-g blog-content">
                    <div class="col-sm-12">
                        <p class="text-content">泥人尚有三分脾气，鲁小智被这么打也被打出真火了，而且和女子一样，鲁小智也确认了一件事，那就是对方打不死自己，因为鲁小智发现自己的血量值是有下限的，下限是1，也就是说无论受多重的伤，无论对方打多少掌，鲁小智的血量最低为1，虽然残血但就是不死。其实以前鲁小智跟帝武天战斗时也出现过这种情况，不过那时候鲁小智以为自己只是幸运，以为帝武天的攻击非常不凑巧只打掉了他（当时血量值-1）的血量，但现在鲁小智算是明白了，当初并不是帝武天的攻击力凑巧差那一点血，而是帝武天根本打不掉最后的那滴血。在明白了这点后鲁小智还怕什么，他也不顾及什么了，更何况女子这一掌掌的打下来虽然打不死人但打得也够疼的，鲁小智也被打出脾气来了，他对着女子说道：“臭娘们你别嚣张，今天你要杀不死我，等日后我再找你算账，到时候让你求生不得求死不能！”女子没说话，也没再动手，她用一种充满怨毒的目光看着鲁小智，这样的目光以前几乎从没在她身上出现过，她也没从来没有像现在这般想要杀人，但却怎么也杀不死人。看到女子不说话，鲁小智故意露出个教科书式的坏蛋笑容，一边故意用赤裸裸的目光盯着女子的身体一边嘿嘿的说道：“婊砸你的欧派不错，虽然有点小，但是还挺结实，手感扎实而不乏弹性，实乃不可多得的佳品，值得让人好好把玩，细细品味，当真是妙不可言。”</p>
                    </div>
                </div>
            </article>

            <hr class="am-article-divider blog-hr">
            <button type="button" class="am-btn am-btn-block am-btn-danger" onclick="dangerClick()">涉黄</button>
            <button type="button" class="am-btn am-btn-block am-btn-secondary" onclick="normalClick()">正常</button>


            <hr class="am-article-divider blog-hr">
            <ul class="am-pagination blog-pagination">
                <li class="am-pagination-prev"><a onclick="goLastPage()">&laquo; 上一篇</a></li>
                <li class="am-pagination-next"><a onclick="goNextPage()">下一篇 &raquo;</a></li>
            </ul>
        </div>

        <div class="col-md-3 col-sm-offset-1 blog-sidebar">
            <div class="am-panel-group">
                <section class="am-panel am-panel-default">
                    <div class="am-panel-hd">判断标准</div>
                    <div class="am-panel-bd">
                        <p>判断标准内容 (guideline)</p>
                        <p>1---------------------------</p>
                        <p>2---------------------------</p>
                        <p>3---------------------------</p>
                        <p>4---------------------------</p>
                        <a class="am-btn am-btn-success am-btn-sm" onclick="moreClick()">查看更多</a>
                    </div>
                </section>
            </div>
        </div>
    </div>

    <script>
        var pageNum = 2;

        function goLastPage() {
            if (pageNum == 1){
                return;
            }
            else {
                // window.location.reload(document.URL.split("?")[0] + "?" + pageNum);
                pageNum--;
                window.location.href = "index.jsp";
            }
        }

        function goNextPage() {
            pageNum++;
            window.location.href = "third.jsp";
            // window.location.href = "GetContentServlet";
            // document.getElementsByClassName("current-index").text = pageNum;
            // window.location.reload(document.URL.split("?")[0] + "?" + pageNum);
            // window.location.href = "Collection.html?" + document.URL.split("?")[1];
        }

        function dangerClick() {

        }

        function normalClick() {

        }

        function moreClick() {

        }
    </script>
    </body>
</html>
