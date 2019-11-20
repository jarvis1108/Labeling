<%--
  Created by IntelliJ IDEA.
  User: Jarvis
  Date: 2019/9/16
  Time: 17:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head lang="en">
    <meta charset="UTF-8">
    <title>Login | WHU HCI</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="format-detection" content="telephone=no">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="alternate icon" type="image/png" href="assets/i/HCI Logo.png">
    <link rel="stylesheet" type="text/css" href="assets/css/amazeui.min.css" />
    <script src="assets/js/jquery-3.4.1.slim.min.js"></script>
    <style>
        .header {
            text-align: center;
        }

        .header h1 {
            font-size: 200%;
            color: #333;
            margin-top: 30px;
        }

        .header p {
            font-size: 18px;
        }

        .info-alarm {
            color: #dd514c;
        ;
        }
    </style>
</head>

<body>
<div class="header">
    <div class="am-g">
        <h1>WHU HCI</h1>
        <p>网络小说涉黄文本标注实验</p>
    </div>
    <hr />
</div>
<div class="am-g">
    <div class="col-lg-6 col-md-8 col-sm-centered">
        <br>
        <h2>实验介绍</h2>
        <p>欢迎参与由武汉大学人机交互与协作创新团队和阿里达摩院合作开展的网络小说涉黄文本标注实验。您一共需要阅读120/180个任务，并判断该文本是否有涉黄内容，预计总用时<b> 1 </b>小时，报酬为<b> 0.3 </b>元/任务。请您耐心阅读，仔细判断。</p>
        <p>本实验承诺，所收集的一切信息仅用于科学研究，请放心填写。有任何疑问请联系董同学（<a>dj_whu@163.com</a>）或樊同学（<a>18328446430</a>），感谢您的支持。</p>
        <hr>

        <%
            String errorInfo = "";
            errorInfo = (String)request.getAttribute("errorInfo");
            if(errorInfo != null){
                out.println("<p class='info-alarm'>" + errorInfo + "</p>");
            } else {
                out.println("<br>");
            }
        %>

        <form method="post" class="am-form" action="Login">
            <h2>用户登录</h2>
            <div id="form-username">
                <label for="username">手机</label>
                <input type="text" name="username" id="username" value="" class="am-form-field">
                <p id="info-username" class="info-alarm"></p>
            </div>
            <br>
            <div id="form-password">
                <label for="password">密码</label>
                <input type="password" name="password" id="password" value="" class="am-form-field">
                <p id="info-password" class="info-alarm"></p>
            </div>
            <br>
            <div class="am-cf">
                <input type="submit" name="start" value="登录" class="am-btn am-btn-success am-fl">
            </div>
            <p>没有账户？<a href="/Labeling/register.jsp">点此注册</a></p>
        </form>

        <hr>
        <p>© 2019 WHU HCI</p>
    </div>
</div>

<script>

    function checkUsername() {
        var usernameVal = $('#username').val();
        if (usernameVal.length != 11) {
            $('#form-username').removeClass().addClass('am-form-error');
            $('#info-username').html('请输入有效的的手机号码');
            return false;
        }
        $('#form-username').removeClass().addClass('am-form-success');
        $('#info-username').html('');
        return true;
    }

    function checkPassword() {
        var passwordVal = $('#password').val();
        if (passwordVal.length < 6 || passwordVal.length > 20) {
            $('#form-password').removeClass().addClass('am-form-error');
            $('#info-password').html('请输入6-20位的密码');
            return false;
        }
        $('#form-password').removeClass('am-form-error').addClass('am-form-success');
        $('#info-password').html('');
        return true;
    }

    $(function () {
        $('#username').blur(function () {
            return checkUsername();
        });

        $('#password').blur(function () {
            return checkPassword();
        });

        $('#form').submit(function () {
            return checkUsername() && checkPassword();
        })

    });

</script>
</body>

</html>
