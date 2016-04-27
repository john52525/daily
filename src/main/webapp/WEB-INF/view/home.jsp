<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <title>welcome</title>
</head>
<body>
<div style="border:red solid thin;outline:#00aa00 dotted thick;">
    <c:if test="${empty username}">
        <form action="/daily/account/login.do" method="post">
            username:<input value="001" name="username" type="text"><br>
            password:<input value="001" name="password" type="password"><br>
            <button type="submit">登录</button>
        </form>
        <a href="/daily/register.html">register</a>
    </c:if>
    <c:if test="${not empty username}">
        <h2>欢迎您:${username}</h2>
        <a href="/daily/account/logout.do">退出</a>
    </c:if>
</div>
<div>
    <a href="http://www.baidu.com" style="font-size:50px">百度</a><br>
    <a href="http://www.sina.com" style="font-size:50px">新浪</a>
</div>
</body>
</html>
