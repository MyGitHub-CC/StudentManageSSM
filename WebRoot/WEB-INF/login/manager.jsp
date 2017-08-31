<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>	后台管理页面 </title>
    <script src="bootstrap/js/jquery.min.js" type="text/javascript"></script>
	<script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" type="text/css"></link>

  </head>
  	
  <body>
  	<%
  		String userName = (String) session.getAttribute("userName");
	  	if(userName == null){
	  		response.sendRedirect("login.jsp");
	  	} else {
	  			%>
  		<script>
	  		window.location.href="login?type=main";
  		</script>
  	<%
	  	}
   %>
  </body>
</html>
