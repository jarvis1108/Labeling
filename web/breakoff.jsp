<%@ page import="com.jhc.entity.User" %>
<%@ page import="com.jhc.entity.Interface" %>
<%@ page import="com.jhc.dao.InterfaceDao" %>
<%@ page import="com.jhc.dao.InterfaceDaoImpl" %><%--
  Created by IntelliJ IDEA.
  User: Jarvis
  Date: 2019/9/18
  Time: 1:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head lang="en">
    <meta charset="UTF-8">
    <title>Thanks | WHU HCI</title>
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
            String username = "";
            int offset = -1;
            int number = -1;

            Interface interExp = (Interface) session.getAttribute("inter");
            if(null != interExp){
                username = interExp.getUsername();
                offset = interExp.getOffset();
                number = interExp.getNumber();

                //开启下一轮标注
                InterfaceDao id = new InterfaceDaoImpl();
                ((InterfaceDaoImpl) id).updateInterfaceOffset(username, offset + 1);
            }
        %>
        <h2>谢谢, <%=username %></h2>
        <p>你已经完成了第<b><%=offset %></b>轮实验，共有<b><%=number %></b>轮。非常感谢您的参与和支持！</p>
        <p><a href="javascript:void(0);" onclick="submit()">点击此处</a>以继续进行下一轮标注</p>
        <p>如有任何疑问，可咨询董同学（<a>dj_whu@163.com</a>）或樊同学（<a>18328446430</a>）。</p>
        <br>
        <hr>
        <p>© 2019 WHU HCI</p>
    </div>
</div>

<script>
    function submit(){
        var myForm = document.createElement("form");
        myForm.method = "post";
        myForm.action = "GetContent";
        myForm.submit();
    }
</script>

</body>
</html>
