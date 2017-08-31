<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'init.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="bootstrap/js/jquery.min.js" type="text/javascript"></script>
	<script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" type="text/css"></link></head>

	<script>
	  $(document).ready(function(){
		$("#banji").click(function(){
				window.location.href="login?type=showClass";
		});
		
		$("#student").click(function(){
				window.location.href="login?type=showStudent";
		});
		
		$("#subject").click(function(){
				window.location.href="login?type=showSubject";
		});
		
	});
	</script>

  </head>
  
  <body>
  	<div  style="width:450px;height:200px; margin:20px auto ">
	    <div id="banji">
	    	<input type="button" name="banji" value="班级管理信息系统"   class="btn btn-primary btn-lg btn-block"/>
	    </div>
	    <br/>
	    <div id="student">
	    	<input type="button" name="banji" value="学生管理信息系统"  class="btn btn-primary btn-lg btn-block" />
	    </div>
	    <br/>
	    <div id="subject">
	    	<input type="button" name="subject" value="科目管理信息系统"  class="btn btn-primary btn-lg btn-block" />
	    </div>
  	 </div>
  </body>
</html>
