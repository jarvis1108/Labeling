<%--
  Created by IntelliJ IDEA.
  User: Jarvis
  Date: 2019/9/16
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head lang="en">
    <meta charset="UTF-8">
    <title>Register | WHU HCI</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="format-detection" content="telephone=no">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="alternate icon" type="image/png" href="assets/i/favicon.png">
    <link rel="stylesheet" href="assets/css/amazeui.min.css" />
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
        <p>欢迎参与由武汉大学人机交互与协作创新团队和阿里达摩院合作开展的网络小说涉黄文本标注实验。您一共需要阅读120-180个文本，并判断该文本是否有涉黄内容，预计总用时<b> 2-3 </b>小时，报酬<b> 50 </b>元。<b class="info-alarm ">账号注册后48小时内有效。</b><b>请您耐心阅读，仔细判断，若标注正确率过低，报酬会相应减少。</b><br>感谢您对本实验的支持。</p>
        <hr>
        <br>

        <form method="post" class="am-form" action="RegisterServlet" id="form-register">
            <h2>用户注册</h2>
            <p>已有账户？<a href="/Labeling/login.jsp">点此登录</a></p>
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

            <h2>收款信息</h2>
            <div id="form-account">
                <label for="account">支付宝账号</label>
                <input type="text" name="account" id="account" value="" class="am-form-field">
                <p id="info-account" class="info-alarm"></p>
            </div>
            <br>
            <div id="form-account-confirm">
                <label for="accountc_onfirm">确认支付宝账号</label>
                <input type="text" name="account_confirm" id="accountc_onfirm" value="" class="am-form-field" onpaste="return false">
                <p id="info-account-confirm" class="info-alarm"></p>
            </div>

            <h2>背景调查</h2>
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
                <input type="button" name="start" value="开始标注" class="am-btn am-btn-primary am-fl" onclick="checkForm()">
            </div>
        </form>
        <hr>
        <p>© 2019 WHU HCI</p>

    </div>
</div>

<script type="text/javascript">

    function checkUsername() {
        var usernameVal = $('#username').val();
        if (usernameVal == '') {
            $('#form-username').removeClass().addClass('am-form-error');
            $('#info-username').html('请填写有效的手机号码');
            return false;
        }
        if (usernameVal.length != 11) {
            $('#form-username').removeClass().addClass('am-form-error');
            $('#info-username').html('请输入正确格式的手机号码');
            return false;
        }
        $('#form-username').removeClass().addClass('am-form-success');
        $('#info-username').html('');
        return true;
    }

    function checkPassword() {
        var passwordVal = $('#password').val();
        if (passwordVal == '') {
            $('#form-password').removeClass().addClass('am-form-error');
            $('#info-password').html('请填写6-20位数的密码');
            return false;
        }
        if (passwordVal.length < 6 || passwordVal.length > 20) {
            $('#form-password').removeClass().addClass('am-form-error');
            $('#info-password').html('长度只能在6-20个字符之间');
            return false;
        }
        $('#form-password').removeClass('am-form-error').addClass('am-form-success');
        $('#info-password').html('');
        return true;
    }

    function checkAccount(){
        var accountVal = $('#account').val();
        if(accountVal == ''){
            $('#form-account').removeClass().addClass('am-form-error');
            $('#info-account').html('请填写接收报酬的支付宝账号');
            return false;
        }
        $('#form-account').removeClass('am-form-error').addClass('am-form-success');
        $('#info-account').html('');
        return true;
    }

    function checkAccountConfirm(){
        var accountVal = $('#account').val();
        var accountConfirmVal = $('#accountc_onfirm').val();
        if(accountConfirmVal == ''){
            $('#form-account-confirm').removeClass().addClass('am-form-error');
            $('#info-account-confirm').html('请再次输入支付宝账号');
            return false;
        }
        if(accountVal != accountConfirmVal){
            $('#form-account-confirm').removeClass().addClass('am-form-error');
            $('#info-account-confirm').html('两次账号输入不一致');
            return false;
        }
        $('#form-account-confirm').removeClass('am-form-error').addClass('am-form-success');
        $('#info-account-confirm').html('');
        return true;

    }

    function checkSex() {
        var sexVal = $('input:radio[name="sex"]:checked').val();
        if (sexVal == null) {
            $('#info-sex').html('请选择性别');
            return false;
        }
        $('#info-sex').html('');
        return true;
    }

    function checkAge() {
        var ageVal = $('#age option:selected').val();
        if (ageVal == 0) {
            $('#form-age').removeClass('am-form-success').addClass('am-form-error');
            return false;
        }
        $('#form-age').removeClass('am-form-error').addClass('am-form-success');
        return true;
    }

    function checkEducation() {
        var educationVal = $('#education option:selected').val();
        if (educationVal == 0) {
            $('#form-education').removeClass('am-form-success').addClass('am-form-error');
            return false;
        }
        $('#form-education').removeClass('am-form-error').addClass('am-form-success');
        return true;
    }

    function checkProfession() {
        var professionVal = $('#profession option:selected').val();
        if (professionVal == 0) {
            $('#form-profession').removeClass('am-form-success').addClass('am-form-error');
            return false;
        }
        $('#form-profession').removeClass('am-form-error').addClass('am-form-success');
        return true;
    }

    function checkLabelingExp() {
        var labelingExpVar = $('input:radio[name="labeling_exp"]:checked').val();
        if (labelingExpVar == null) {
            $('#info-labelingExp').html('请选择是否有标注经验');
            return false;
        }
        $('#info-labelingExp').html('');
        return true;
    }

    function checkReadingExp() {
        var readingExpVal = $('input:radio[name="reading_exp"]:checked').val();
        if (readingExpVal == null) {
            $('#info-readingExp').html('请评价自己的网络小说阅读经验');
            return false;
        }
        $('#info-readingExp').html('');
        return true;
    }

    function checkForm(){
        if(checkUsername() && checkPassword() && checkSex() && checkAge() && checkEducation() && checkProfession() && checkLabelingExp() && checkReadingExp() && checkAccountConfirm() && checkAccount()){
            $('#form-register').submit();
        }
    }

    $(function () {

        $('#username').blur(function () {
            return checkUsername();
        });

        $('#password').blur(function () {
            return checkPassword();
        });

        $('#age').blur(function () {
            return checkAge();
        });

        $('#education').blur(function () {
            return checkEducation();
        });

        $('#profession').blur(function () {
            return checkProfession();
        });

        $('#account').blur(function () {
            return checkAccount();
        });

        $('#accountc_onfirm').blur(function () {
            return checkAccountConfirm();
        });


    });

</script>
</body>

</html>