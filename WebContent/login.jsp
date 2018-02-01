<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<%
	String basePath = request.getScheme() + "://" + 
		request.getServerName() + ":" + request.getServerPort() + 
		request.getContextPath() + "/" ;
%>
<base href="<%=basePath%>"/>
<title>MVC登录</title>
</head>
<body>
<%!
	public static final String LOGIN_URL = "LoginServlet";
%>
<h1><%=request.getAttribute("msg")==null ? "":request.getAttribute("msg")%></h1>
<h1><%=request.getAttribute("errors")==null ? "":request.getAttribute("errors")%></h1>
<form action="<%=LOGIN_URL%>" method="post">
	用户名：<input type="text" name="name" id="name"><br>
	密&nbsp;码：<input type="password" name="password" id="password"><br>
	<input type="submit" value="登录">
	<input type="reset" value="重置">
</form>
</body>
</html>