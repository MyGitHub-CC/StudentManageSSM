<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>教务管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="bootstrap/js/jquery.min.js" type="text/javascript"></script>
	<script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" type="text/css"></link>

  <style>
  *{
  	margin:0;
  	padding:0;
  }
  #container{
  	width:1200px;
  	margin:10px auto;  
  }
   #header{
   	height:100px;
   	padding:10px 0;
   }
  
   #left, #right{
  	height:470px;
  	
  }
  #footer{
  	height:60px;
  	padding:10px 0;
  }
  #header,#left, #right, #footer{
  	border:1px solid #ccc;
  }
  
  </style>
  </head>
  <body>
	  <div id="container">
	    	<div id="main" class="row"> 
	    		<iframe src="main?type=header" id="header" class="col-sm-12" scrolling="no" frameborder="0">
	    		</iframe>
	    	</div>
	    	
	    	<div class="row"> 
	    		<iframe src="main?type=left" id="left" class="col-sm-3" scrolling="no" frameborder="0">
	    		</iframe>
	    	
				<iframe src="student" id="right" name="right" class="col-sm-9" scrolling="yes" frameborder="0">
				</iframe>    	
	    	</div>
	    	
	    	<div class="row"> 
	    		<iframe src="main?type=footer" id="footer" class="col-sm-12" scrolling="no" frameborder="0">
	    		</iframe>
	    	</div>
	    </div>
  </body>
</html>
