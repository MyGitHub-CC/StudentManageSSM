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

	<title>班级科目</title>

	<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
	<script src="bootstrap/js/jquery.min.js" type="text/javascript"></script>
	<script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" type="text/css"></link>
	
	</head>
	
	<%
		BanJi banJi = (BanJi)request.getAttribute("banJi");
		List<Subject> subList = banJi.getSubList();//已选科目集合
		List<Subject> list = (List<Subject>)request.getAttribute("list");//未选科目集合
	%>
<script>
  $(document).ready(function(){
  	var addId = 0;
	var bjId = $("#bjId").val();
	
	$(".tr-right").click(function(){
		$(".tr-right").removeClass("info");
		$(this).addClass("info");
		addId = $(this).data("id");
	});
	
	$("#add").click(function(){
		if(addId != 0){
			window.location.href="banji?type=addSubject&bjId=" + bjId + "&bjName=<%=banJi.getName() %>" + "&subid="+ addId;
		} else{
			alert("请选择要添加的科目！");
		}
	});
	
	var deleteId = 0;
	$(".tr-left").click(function(){
		$(".tr-left").removeClass("info");
		$(this).addClass("info");
		deleteId = $(this).data("subid");
	});
	
	$("#delete").click(function(){
		if(deleteId != 0){
			window.location.href="banji?type=deleteSubject&bjId=" + bjId + "&bjName=<%=banJi.getName() %>" + "&subId=" + deleteId;
		} else{
			alert("请选择要删除的科目！");
		}
	});
	
	$("#search").click(function(){
			window.location.href="banji?type=searchSubject" ;
	});
	
	$("#back").click(function(){
			window.location.href="banji?type=searchClass" ;
	});
});
</script>
<style>

th{
	text-align:center;
}

.panel{
	width：350px;
	height:350px;
	float:left;
	overflow:hidden;
	border:1px solid #ccc;
}
.btn-group1 button{
	margin-top:50px;
}
</style>
<body>
	<div style="width:700px;margin:50px 80px 0 120px; float:left;" >
	<input type="hidden" id="bjId" value="<%=banJi.getId() %>" />
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title"><%=banJi.getName()%>(已选课程)</h3>
			</div>
			<div class="panel-body">
				<table class="table table-striped">
					<%
						for (Subject subject : subList) {
					%>
					<tr class="tr-left" data-subid=<%=subject.getId()%>>
						<td><%=subject.getName()%></td>
					</tr>
					<%
						}
					%>
				</table>
			</div>
		</div>

		<div class="btn-group1 panel" style="margin:0 30px">
			<button id="add" type="button" class="btn btn-primary">添加</button><br/>
			<button id="delete" type="button" class="btn btn-primary">删除</button><br/>
			<button id="back" type="button" class="btn btn-primary"> 返回 </button><br/>
		</div>

		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title"><%=banJi.getName()%>(未选课程)</h3>
			</div>
			<div class="panel-body">
				<table class="table table-striped" >
					<%
						for (Subject subject : list) {
					%>
					<tr class="tr-right" data-id=<%=subject.getId()%>>
						<td><%=subject.getName()%></td>
					</tr>
					<%
						}
					%>
				</table>
			</div>
		</div>
  </div>

</body>
</html>
