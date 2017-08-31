<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>main-左侧</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="bootstrap/js/jquery.min.js" type="text/javascript"></script>
	<script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" type="text/css"></link>
	<style>
	#accordion a{
		text-decoration: none;
	}
	.panel-title, .panel-body{
		text-align:center;	
	}
	.panel-body a{
		cursor: pointer;
	}
	</style>
  </head>
  
  <body>
	<div class="panel-group" id="accordion">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">
					<a data-toggle="collapse" data-parent="#accordion"
						href="#student"> 学生管理 </a>
				</h4>
			</div>
			<div id="student" class="panel-collapse collapse in">
				<div class="panel-body">
					<a href="student" target="right">显示学生信息</a>
				</div>
				<div class="panel-body">
					<a href="student?type=showAdd" target="right">添加学生信息</a>
				</div>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">
					<a data-toggle="collapse" data-parent="#accordion"
						href="#banJi"> 班级管理 </a>
				</h4>
			</div>
			<div id="banJi" class="panel-collapse collapse">
				<div class="panel-body">
					<a href="banji" target="right">显示班级信息</a>
				</div>
				<div class="panel-body">
					<a  href="banji?type=showAddClass" target="right">添加班级信息</a>
				</div>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">
					<a data-toggle="collapse" data-parent="#accordion"
						href="#subject"> 科目管理</a>
				</h4>
			</div>
			<div id="subject" class="panel-collapse collapse">
				<div class="panel-body">
					<a  href="subject" target="right">显示科目信息</a>
				</div>
				<div class="panel-body">
					<a  href="subject?type=showAdd" target="right">添加科目信息</a>
				</div>
			</div>
		</div>
			<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">
					<a data-toggle="collapse" data-parent="#accordion" href="#score"> 成绩管理</a>
				</h4>
			</div>
			<div id="score" class="panel-collapse collapse">
				<div class="panel-body">
					<a href="score" target="right">显示成绩信息</a>
				</div>
				<div class="panel-body">
					<a href="score?type=showInput" target="right">录入成绩信息</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
