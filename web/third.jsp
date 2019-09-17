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
        int pageNum=3;
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
                        <p class="text-content">只见女儿半个身体都倚在爸爸身上，把头靠在爸爸的肩头斜着脸得意地看着妈妈挤眉弄眼吐舌头。这样温馨的一幕，可能是他们一家的常态吧，他的女儿也应该这么大，也这么高了吧？他的妻子也是有这样婉约娴静的气质吧！她索性不再控制自己，放开思绪想他，想他们。那是一夜旖旎过后，她趴在他的怀里仰着脸问他：“你说，如果我们可以生一个孩  子，你希望是男孩还是女孩呢？”他抚摸着顾长青的肩头后背说：“只要是我们的孩子，男孩女孩都好！”顾长青把头埋在他的怀里喃喃地说：“我想生个男孩，因为你已经有个女儿了，我如果再生个女儿，你一定不会喜欢的！”尽管她的声音很小，但他还是听清了或者说他不用听就能明白她在说什么，他把头埋在她的肩头，在她的耳边轻声说：“傻瓜，只要是你生的，小猫小狗我都会喜欢！”他的气息哈的她耳朵痒痒的，心也痒痒的。她娇嗔到：“哈，你骂我！”说完就够着去咬他，他迎着吻她，他抚摸着她的头发说：“我希望你能生个女儿，然后我看着她出生看着她长大，这样，我就把错过的你的时光都补回来了！”他的声音低沉，语气伤感深情，听的顾长青眼眶湿透，他感觉到她略微颤抖的身体，和胸前的湿凉，他低着头，深深地去吻她……  顾长青一边走，一边流着眼泪一边想。从昨天晚上的梦，到今天在灵感寺的偶遇，她一直在克制着自己，应该说一直在积极地努力着自救，可就在这一刻，所有的努力所有的克制瞬间崩溃瓦解，这种决堤似的冲击力，让她痛到无法承受。</p>
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
                window.location.href = "second.jsp";
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
