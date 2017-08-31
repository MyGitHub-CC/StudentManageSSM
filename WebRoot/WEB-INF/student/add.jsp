<%@page import="com.sun.xml.internal.bind.v2.runtime.Name"%>
<%@ page language="java" import="java.util.*,entity.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>添加学生</title>

<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<script src="bootstrap/js/jquery.min.js" type="text/javascript"></script>
<script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script>
$(document).ready(function(){
	
	$("#back").click(function(){
			window.location.href="student?type=search" ;
	});

});
</script>
</head>
<body>
	<%
		List<BanJi> banJis = (List<BanJi>) (request.getAttribute("banJis"));
	%>
	<div style="width:500px;margin:20px auto">
		<form action="student?type=add" method="post" enctype="multipart/form-data" class="form-horizontal" role="from">
			<div class="form-group">
				<label class="col-sm-2 control-label">姓名：</label>
				<div class="col-sm-6" >
					<input type="text" name="name" class="form-control" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label">性别：</label>
				<div class="col-sm-6 radio">
					<label>
					<input type="radio" name="sex" value="男" checked="checked" />男
					</label>
					<label>
					<input type="radio" name="sex" value="女" />女
					</label>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label"> 年龄：</label>
				<div class="col-sm-6">
					<input type="text" name="age" class="form-control" />
				</div>
				<div id="msg" class="col-sm-4" style="line-height:30px;color:red"></div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label"> 照片：</label>
				<div class="col-sm-6">
					<input type="file" name="photo" class="form-control" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label"> 班级：</label>
				<div class="col-sm-6">
					 <select name="className" class="form-control" >
					 <%	for (BanJi banJi : banJis) {	 %>
					    <option value="<%=banJi.getId()%>"><%=banJi.getName()%></option>
					  <% }  %>
					  </select>
				</div>
			</div>
		<div class="form-group" style="width:200px;margin-left:125px">
			<input type="submit" name="保存" class="btn btn-primary" />
			 <button id="back" type="button" class="btn btn-primary">返回</button>
		</div>
		</form>
	</div>
</body>
</html>
