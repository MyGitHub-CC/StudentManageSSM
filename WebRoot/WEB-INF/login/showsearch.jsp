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

	<title>显示学生</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
	<script src="bootstrap/js/jquery.min.js" type="text/javascript"></script>
	<script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" type="text/css"></link></head>
<script>
  $(document).ready(function(){
  	var selectId = 0;
	$("#add").click(function(){
			window.location.href="student?type=showAdd";
	});
	
	$("#modify").click(function(){
			window.location.href="student?type=showModify&id=" + selectId;
	});
	
	$("tr").click(function(){
		$("tr").removeClass("info");
		$(this).addClass("info");
		selectId = $(this).data("id");
	});
	
	$("#delete").click(function(){
			window.location.href="student?type=delete&id=" + selectId;
	});
	
	$("#search").click(function(){
			window.location.href="student?type=showSearch" ;
	});
	
	$("#back").click(function(){
			window.location.href="student?type=show" ;
	});
});
</script>

<body>
	<%
		List<Student> list = (List<Student>) (request.getAttribute("list"));
// 		int ye = (Integer) request.getAttribute("page");
// 		int maxPage = (Integer) request.getAttribute("maxPage");
	%>
	<div style="width:450px;margin:20px auto ">
		<table class="table table bordered" border=1>
			<tr align="center" valign="middle">
				<th>id</th>
				<th>name</th>
				<th>age</th>
				<th>sex</th>
			</tr>
			<%
				for (Student student : list) {
			%>
			
			<tr class="success" data-id=<%=student.getId()%>>
				<td><%=student.getId()%></td>
				<td><%=student.getName()%></td>
				<td><%=student.getAge()%></td>
				<td><%=student.getSex()%></td>
			</tr>
			<%
				}
			%>
		</table>
		
		<div class="btn-group1">
		    <button id="add" type="button" class="btn btn-primary" >新增</button>
		    <button id="modify" type="button" class="btn btn-primary">修改</button>
 		    <button id="delete" type="button" class="btn btn-primary">删除</button>
		    <button id="search" type="button" class="btn btn-primary">查找</button>
		     <button id="back" type="button" class="btn btn-primary">返回</button>
		</div>
	</div>
</body>
</html>
