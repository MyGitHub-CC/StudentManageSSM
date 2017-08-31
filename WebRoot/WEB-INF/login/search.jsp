<%@page import="com.sun.xml.internal.bind.v2.runtime.Name"%>
<%@ page language="java" import="java.util.*,entity.*"
	pageEncoding="utf-8"%>
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

<title>查询学生</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
<script src="bootstrap/js/jquery.min.js" type="text/javascript"></script>
<script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script>
$(document).ready(function(){
 	$("form").submit(function(){
		var name = $("[name=name]").val();
		var age = $("[name=age]").val();
		var sex = $("[name=sex]").val();
		// var sex = $("[name=sex]:checked").val(); 单选按钮被选中的
		//var checked = $("[name=sex]").attr("checked");
		var className = $("[name=className]").val();
 	if(name == ""  && age == "" && className == "0" && sex == "" && className == ""){
 		alert("请输入查询条件！");
 		// alert("请输入查询条件！"+ "age:" + age + "sex:" + sex + "name:" + name + "className:" + className + "checked:"+checked);
 		return false;
 	}
	});
	
	$("#back").click(function(){
			window.location.href="student?type=show" ;
	});
});
</script>
</head>
<body>
	<%
		List<BanJi> list = (List<BanJi>) (request.getAttribute("list"));
	%>
	<div style="width:500px;margin:20px auto">
		<form action="student?type=search" method="post" class="form-horizontal" role="form">
			<div class="form-group">
				<label class="col-sm-2 control-label">姓名：</label>
				<div class="col-sm-6" >
					<input type="text" name="name" class="form-control" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label">性别：</label>
				<div class="col-sm-6" >
					<input type="text" name="sex" class="form-control" />
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
				<label class="col-sm-2 control-label"> 班级：</label>
				<div class="col-sm-6">
					 <select name="className" class="form-control" >
					 <option value="0">请选择：</option>
					 <%	for (BanJi banJi : list) {	 %>
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
