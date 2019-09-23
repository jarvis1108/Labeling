<%--
  Created by IntelliJ IDEA.
  User: Jarvis
  Date: 2019/9/18
  Time: 1:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head lang="en">
    <meta charset="UTF-8">
    <title>Error | WHU HCI</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="format-detection" content="telephone=no">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="alternate icon" type="image/png" href="assets/i/HCI Logo.png">
    <link rel="stylesheet" href="assets/css/amazeui.min.css" />
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
    <div class="col-lg-4 col-md-8 col-sm-centered">
        <br>
        <%
            String errorInfo = "";
            if(null != session.getAttribute("errorInfo")){
                errorInfo = (String)session.getAttribute("errorInfo");
            };
        %>
        <h2>抱歉，出错了。</h2>
        <p><%=errorInfo %></p>
        <br>
        <hr>
        <p>© 2019 WHU HCI</p>
    </div>
</div>
</body>
</html>
