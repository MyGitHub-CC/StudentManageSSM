<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page import="com.mysql.jdbc.Driver" %>   
<%@ page import="java.sql.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'doLogin.jsp' starting page</title>
    <script src="bootstrap/js/jquery.min.js" type="text/javascript"></script>
	<script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" type="text/css"></link>

  </head>
  
  <body>
  <%
   String userName = (String) request.getAttribute("userName");
   if(userName != null ){
  		session.setAttribute("userName", userName);
  		Cookie cookie = new Cookie("userName", userName);
  		cookie.setMaxAge(60);
  		response.addCookie(cookie);
  	%>
  		<script>
	  		window.location.href="login?type=manager";
  		</script>
  	<%
  	} else {
  	%>
  		<script>
	  		alert("用户名或密码错误！"); 
	  		window.location.href="login?type=login";
  		</script>
  <% } %>

  </body>
</html>
