<%@ page import="com.jhc.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: Jarvis
  Date: 2019/9/18
  Time: 1:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>My | WHU HCI</title>
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
        String username = user.getUsername();
        String password = user.getPassword();
        String account = user.getAccount();
        String sex = user.getSex();
        String age = user.getAge();
    %>

    <div class="am-collapse am-topbar-collapse" id="doc-topbar-collapse">
        <ul class="am-nav am-nav-pills am-topbar-nav">
            <li><a href="index.jsp">标注</a></li>
            <li class="am-active"><a href="my.jsp">我的</a></li>
            <li><a><%=username %></a></li>
        </ul>
    </div>
</header>

<div class="am-g">
    <div class="col-lg-6 col-md-8 col-sm-centered">
        <br>
        <form method="post" class="am-form" action="RegisterServlet" id="form-register">
            <h2>用户信息</h2>
            <div id="form-username">
                <label for="username">手机</label>
                <input type="text" name="username" id="username" value="<%=username %>" class="am-form-field" disabled>
                <p id="info-username" class="info-alarm"></p>
            </div>
            <br>
            <div id="form-password">
                <label for="password">密码</label>
                <input type="password" name="password" id="password" value="<%=password %>" class="am-form-field">
                <p id="info-password" class="info-alarm"></p>
            </div>

            <h2>收款信息</h2>
            <div id="form-account">
                <label for="account">支付宝账号</label>
                <input type="text" name="account" id="account" value="<%=account %>" class="am-form-field">
                <p id="info-account" class="info-alarm"></p>
            </div>

            <h2>背景信息</h2>
            <label>性别</label>
            <div class="am-form-group">
                <label class="am-radio-inline">
                    <input type="radio" value="1" name="sex" class="am-form-field"> 男
                </label>
                <label class="am-radio-inline">
                    <input type="radio" value="0" name="sex"> 女
                </label>
                <p id="info-sex" class="info-alarm"></p>
            </div>
            <br>
            <div id="form-age" class="am-form-group">
                <label for="age">年龄</label>
                <select id="age" name="age" class="am-form-field">
                    <option value="0" selected = "selected">请选择…</option>
                    <option value="1">18以下</option>
                    <option value="2">18-25</option>
                    <option value="3">26-30</option>
                    <option value="4">31-40</option>
                    <option value="5">41-50</option>
                    <option value="6">51-60</option>
                    <option value="7">60以上</option>
                </select>
                <span class="am-form-caret"></span>
            </div>
            <br>
            <div id="form-education" class="am-form-group">
                <label for="education">教育水平</label>
                <select id="education" name="education" class="am-form-field">
                    <option value="0" selected = "selected">请选择…</option>
                    <option value="1">本科以下</option>
                    <option value="2">本科</option>
                    <option value="3">硕士</option>
                    <option value="4">博士</option>
                </select>
                <span class="am-form-caret"></span>
            </div>
            <br>
            <div id="form-profession" class="am-form-group">
                <label for="profession">职业</label>
                <select id="profession" name="profession" class="am-form-field">
                    <option value="0" selected = "selected">请选择…</option>
                    <option value="1">公职人员（含公务员和除教师外的事业单位人员）</option>
                    <option value="2">教师</option>
                    <option value="3">企业职员</option>
                    <option value="4">工人（含农民工）</option>
                    <option value="5">农民</option>
                    <option value="6">个体户</option>
                    <option value="7">学生</option>
                    <option value="8">其他</option>
                </select>
                <span class="am-form-caret"></span>
            </div>
            <br>
            <label>是否参与过文本标注工作</label>
            <div class="am-form-group">
                <label class="am-radio-inline">
                    <input type="radio" value="1" name="labeling_exp"> 是
                </label>
                <label class="am-radio-inline">
                    <input type="radio" value="0" name="labeling_exp"> 否
                </label>
                <p id="info-labelingExp" class="info-alarm"></p>
            </div>
            <br>
            <label>请评价自己的网络小说阅读经验</label>
            <div class="am-form-group">
                <label class="am-radio-inline">
                    <input type="radio" value="1" name="reading_exp"> 从不阅读
                </label>
                <label class="am-radio-inline">
                    <input type="radio" value="2" name="reading_exp"> 较少阅读
                </label>
                <label class="am-radio-inline">
                    <input type="radio" value="3" name="reading_exp"> 经常阅读
                </label>
                <p id="info-readingExp" class="info-alarm"></p>
            </div>
            <br>

            <br>
            <div class="am-cf">
                <input type="button" name="start" value="修改" class="am-btn am-btn-primary am-fl" onclick="checkForm()" disabled>
            </div>
            <% //TODO：修改个人信息 %>
        </form>
        <hr>
        <p>© 2019 WHU HCI</p>

    </div>
</div>

<script>
    window.onload = function() {
        //TODO:加载时填充用户信息到界面中
    }
</script>

</body>
</html>

