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

<title>修改科目</title>

<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<script src="bootstrap/js/jquery.min.js" type="text/javascript"></script>
<script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script>
	$(document).ready(function() {
		$("form").submit(function() {
			var name = $("[name=name]").val();
			if(name == ""){
				alert("科目名称不能为空！");
				return false;
			}
		});
		
		$("#back").click(function(){
			window.location.href="subject?type=search" ;
		});
	});
</script>
</head>
<body>

	<%
		Subject subject = (Subject) request.getAttribute("subject");
	%>
	<div style="width:500px;margin:20px auto">
		<form action="subject?" method="post" class="form-horizontal">
			<input type="hidden" name="type" value="modify" />
			<input type="hidden" name="id" value="<%=subject.getId()%>" />
			<div class="form-group">
				<label class="col-sm-5 control-label">科目名称：</label>
				<div class="col-sm-7">
					<input type="text" name="name" class="form-control" value="<%=subject.getName()%>" />
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
