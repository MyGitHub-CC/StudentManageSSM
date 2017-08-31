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

<title>科目管理</title>

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
			window.location.href="subject?type=showAdd";
	});
	
	$("#modify").click(function(){
		if(selectId != 0){
			window.location.href="subject?type=showModify&id=" + selectId;
		} else{
			alert("请选择要修改的科目！");
		}
	});
	
	$("tr").click(function(){
		$("tr").removeClass("info");
		$(this).addClass("info");
		selectId = $(this).data("id");
	});
	
	$("#delete").click(function(){
		if(selectId != 0){
			if(confirm("是否确认删除")){
				window.location.href="subject?type=delete&id=" + selectId;
			}
		} else{
			alert("请选择要删除的科目！");
		}
	});
	
	$("#search").click(function(){
			window.location.href="subject?type=show" ;
	});
	
	$("#back").click(function(){
			window.location.href="login" ;
	});
});
</script>
<style>
#input{
	padding-left:30px;
}
.search-span{
	line-height:30px;
	font-size:16px;
	font-weight:bold;
	float:left;
	margin-right:10px;
}
.search-input{
	width:150px;
	float:left;
	margin-right:20px;
}
th{
text-align:center
}
</style>
<body>
	<%
		List<Subject> subjects = (List<Subject>) (request.getAttribute("subjects"));
		int ye = (Integer) request.getAttribute("page");
		int maxPage = (Integer) request.getAttribute("maxPage");
		Subject sub = (Subject)request.getAttribute("sub");
	%>
	
	<div style="width:450px;margin:20px auto; ">
		<form action="subject" method="post">
			<input type="hidden" name="type" value="search" />
			<span class="search-span">科目名称：</span>
			<input type="text" name="name"  class="form-control search-input" 
			value="<%if(sub.getName() != null){%><%=sub.getName() %><%} %>" />
			<input type="submit" value="查询"  class="btn btn-primary"  style="width:80px;float:left"/>
		</form>
		<div  style="height:60px;"></div>
		<table class="table table bordered" border=1 >
			<tr align="center" valign="middle" class="info">
				<th>id</th>
				<th>科目名称</th>
		 	</tr>
			<%
				for (Subject subject : subjects) {
			%>
			<tr class="success" data-id=<%=subject.getId()%>>
				<td><%=subject.getId()%></td>
				<td><%=subject.getName()%></td>
			</tr>
			<%
				}
			%>
		</table>
		<ul class="pagination" style="margin-left:0">
			<li><a href="subject?type=search&page=<%=1%>
			&name=<%=sub.getName()%>">首页</a>
			</li>

			<li <%if (ye <= 1) {%> class="disabled" <%}%>><a
				href="subject?type=search&page=<%=ye - 1%>
				&name=<%=sub.getName()%>">上一页</a></li>

			<%
				int end = ye + 4;
				int begin = ye;
				
				if (end >= maxPage ) {
					end = maxPage;
					begin = end - 4;
				}
				if(begin < 1){
					begin = 1;
				}
				for (int i = begin; i <= end; i++) {
			%>
			<li <%if (ye == i) {%> class="active" <%}%>><a
				href="subject?type=search&page=<%=i%>
				&name=<%=sub.getName()%>"><%=i%></a>
			</li>
			<%
				}
			%>

			<li <%if (ye >= maxPage) {%> class="disabled" <%}%>>
			<a href="subject?type=search&page=<%=ye + 1%>
			&name=<%=sub.getName()%>">下一页</a>
			</li>
			<li>
			<a href="subject?type=search&page=<%=maxPage%>
			&name=<%=sub.getName()%>">尾页</a>
			</li>
		</ul>
		<div class="btn-group1">
		    <button id="add" type="button" class="btn btn-primary" >新增</button>
		    <button id="modify" type="button" class="btn btn-primary">修改</button>
 		    <button id="delete" type="button" class="btn btn-primary">删除</button>
		    <button id="back" type="button" class="btn btn-primary">返回</button>
		</div>
	</div>
</body>
</html>
