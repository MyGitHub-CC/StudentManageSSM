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

<title>修改学生</title>

<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<script src="bootstrap/js/jquery.min.js" type="text/javascript"></script>
<script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script>
	$(document).ready(function() {
		$("form").submit(function() {
			var name = $("[name=name]").val();
			if(name == ""){
				alert("姓名不能为空！");
				return false;
			}
		
			var age = $("[name=age]").val();
			var reg = /^[0-9]+$/;
			if (reg.test(age)) {
				return true;
			} else {
				$("#msg").html("年龄只能是数字！");
				return false;
			}
			;
		});
		
		$("[name=age]").focus(function() {
			$("#msg").html("");
		});

		$("#back").click(function(){
			window.location.href="student?type=search" ;
		});
	});
</script>
</head>
<body>
	<%
		Student student = (Student) request.getAttribute("student");
		List<BanJi> banJis = (List<BanJi>) (request.getAttribute("banJis"));	
	%>
	<div style="width:500px;margin:20px auto">
		<form action="student" method="post" class="form-horizontal" >
			<input type="hidden" name="type" value="modify" />
			<input type="hidden" name="id" value="<%=student.getId()%>" />
			<div class="form-group">
				<label class="col-sm-2 control-label">姓名：</label>
				<div class="col-sm-6">
					<input type="text" name="name" class="form-control"	value="<%=student.getName()%>" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">性别：</label>
				<div class="col-sm-6 radio">
					<label>
					<input type="radio" name="sex" value="男"
						<%if (student.getSex().equals("男")) {%>checked<%}%> />男
					</label>
					<label> 
					<input type="radio" name="sex" value="女"
						<%if (student.getSex().equals("女")) {%> checked<%}%> />女
					</label>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label"> 年龄：</label>
				<div class="col-sm-6">
					<input type="text" name="age" class="form-control"
						value="<%=student.getAge()%>" />
				</div>
				<div id="msg" class="col-sm-4" style="line-height:30px;color:red"></div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label"> 班级：</label>
				<div class="col-sm-6">
					 <select name="className" class="form-control" >
					 <%	for (BanJi banJi : banJis) { %>
					    <option value="<%=banJi.getId()%>" 
					     <%if(banJi.getName().equals(student.getBanJi().getName())){%> selected="selected"<%} %> >
					     <%=banJi.getName()%></option>
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
