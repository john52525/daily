<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>登录页面</title>
</head>
<h1>登录页面${message }</h1>
<form action="./login.ctr"  method="post">
    <input type="username" name="username">username</input> <br/>
    <input type="password"  name="password">password</input> <br/>
    <input type="submit"/>
</form>
</body>
</html>