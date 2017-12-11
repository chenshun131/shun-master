<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录页面</title>

    <link rel="stylesheet" type="text/css" href="${basePath}/static/css/stateless/login.css"/>
    <script type="text/javascript" src="${basePath}/static/js/stateless/login/login.js"></script>
</head>
<body>

<div id="login_frame">
    <p id="image_logo">
        <img src="${basePath}/static/images/stateless/login/fly.png">
    </p>
    <form method="post" action="${basePath}/static/js/stateless/login/login.js">
        <p>
            <label class="label_input">用户名</label>
            <input type="text" id="username" class="text_field"/>
        </p>
        <p>
            <label class="label_input">密码</label>
            <input type="password" id="password" class="text_field"/>
        </p>
        <p>
            <label class="label_input">验证码</label>
            <input type="text" id="validator" class="text_field"/>
        </p>
        <div id="login_control">
            <input type="button" id="btn_login" value="登录" onclick="login();"/>
            <a id="forget_pwd" href="#">忘记密码</a>
        </div>
    </form>
</div>

</body>
</html>
