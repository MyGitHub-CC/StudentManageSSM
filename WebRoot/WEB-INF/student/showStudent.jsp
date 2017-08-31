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

	<title>学生管理</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
	<script src="bootstrap/js/jquery.min.js" type="text/javascript"></script>
	<script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" type="text/css"></link>

<style>
.search-span{
	line-height:30px;
	font-size:16px;
	font-weight:bold;
	float:left;
	margin-right:10px;
}
.search-input{
	width:100px;
	float:left;
	margin-right:20px;
}
th{
	text-align:center;
}
.photo{
	width:30px;
	height:30px;
}
#BigPhoto{
	display:none;
	position:absolute;
	width:100px;
	height:100px;
	z-index:10;
}
#BigPhoto img{
	width:100px;
	height:100px;
}
</style>


<script>
  $(document).ready(function(){
  	var selectId = 0;
	$("#add").click(function(){
			window.location.href="student?type=showAdd";
	});
	
	$("#modify").click(function(){
		if(selectId != 0){
			window.location.href="student?type=showModify&id=" + selectId;
		} else{
			alert("请选择要修改的学生！");
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
				window.location.href="student?type=delete&id=" + selectId;
			}
		} else{
			alert("请选择要删除的学生！");
		}
	});
	
		
// 	$("form").submit(function(){
// 		var name = $("[name=name]").val();
// 		var age = $("[name=age]").val();
// 		var sex = $("[name=sex]").val();
// 		var className = $("[name=className]").val();
// 		if(name != ""){
// 			alert(name);
// 			request.setAttribute("name", name);
// 		}
// 		var sex = $("[name=sex]:checked").val(); 单选按钮被选中的
// 		var checked = $("[name=sex]").attr("checked");
// 	 	if(name == ""  && age == "" && sex == "" && className == "" ){
// 	 		alert("请输入查询条件！");
// 	 		return false;
// 	 	}
// 	});
	
	$("#back").click(function(){
			window.location.href="login" ;
	});
	
	$(".photo").hover(function (event){
		$("#BigPhoto").toggle();
		$("#BigPhoto").children().attr("src",$(this).attr("src"));
		$("#BigPhoto").offset({
			left:event.pageX-100,
			top:event.pageY,
		});
	});
});
</script>
</head>

<body>
	<%
		List<Student> stus = (List<Student>) (request.getAttribute("stus"));
		List<BanJi> banJis = (List<BanJi>) (request.getAttribute("banJis"));
		int ye = (Integer) request.getAttribute("page");
		int maxPage = (Integer) request.getAttribute("maxPage");
 		Student stu = (Student)request.getAttribute("stu");
	%>
	<div style="width:780px;margin:20px auto ">
		<form action="student" method="post">
			<input type="hidden" name="type" value="search" />
			<span class="search-span">姓名：</span>
			<input type="text" name="name"  class="form-control search-input" 
			value="<%if(stu.getName() != null){%><%=stu.getName() %><%} %>" />
			<span class="search-span">年龄：</span>
			<input type="text" name="age"  class="form-control search-input"
			value="<%if(stu.getAge() > 0){%><%=stu.getAge()%><%} %>" />
			<span class="search-span">性别：</span>
			<input type="text" name="sex"  class="form-control search-input" 
			value="<%if(stu.getSex() != null){%><%=stu.getSex()%><%} %>" />
			<div class="form-group search-input" >
				<select name="className" class="form-control" style="width:120px;">
					<option value="0">请选择</option>
					<%	for (BanJi banJi : banJis) { %>
						<option value="<%=banJi.getId()%>" 
						 <%if(stu.getBanJi().getId() == banJi.getId()){ %>selected="selected"<% } %>>
						 <%=banJi.getName()%>
						</option>
					<% }  %>
				</select>
			</div>
			<input type="submit" value="查询"  class="btn btn-primary"  style="width:80px;float:left;margin-left:20px;"/>
		</form>
		
		<div id="BigPhoto"><img src="" /></div>
		<table class="table table-bordered table-striped" style="text-align:center;">
			<tr class="info">
				<th>id</th>
				<th>姓名</th>
				<th>年龄</th>
				<th>性别</th>
				<th>班级</th>
				<th>照片</th>
			</tr>
			<%
				for (Student student : stus) {
			%>
			
			<tr data-id=<%=student.getId()%>>
				<td><%=student.getId()%></td>
				<td><%=student.getName()%></td>
				<td><%=student.getAge()%></td>
				<td><%=student.getSex()%></td>
				<td><%=student.getBanJi().getName()%></td>
				<%
				String photo = "default.jpg";
				if(student.getPhoto() != null){
					photo = student.getPhoto();
				}
				 %>
				<td>
					<img class="photo" src="photo/<%=photo%>">
				</td>
			</tr>
			<%
				}
			%>
		</table>
		<ul class="pagination" style="margin-left:0">
			<li>
				<a href="student?type=search&page=<%=1%>
				&name=<%=stu.getName()%>&age=<%=stu.getAge()%>&sex=<%=stu.getSex()%>
				&className=<%=stu.getBanJi().getId()%>">
					首页
				</a>
			</li>

			<li <%if (ye <= 1) {%> class="disabled" <%}%>>
				<a href="student?type=search&page=<%=ye - 1%>
				&name=<%=stu.getName()%>&age=<%=stu.getAge()%>&sex=<%=stu.getSex()%>
				&className=<%=stu.getBanJi().getId()%>">
					上一页
				</a>
			</li>

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
			<li <%if (ye == i) {%> class="active" <%}%>>
				<a href="student?type=search&page=<%=i%>
				&name=<%=stu.getName()%>&age=<%=stu.getAge()%>&sex=<%=stu.getSex()%>
				&className=<%=stu.getBanJi().getId()%>">
					<%=i%>
				</a>
			</li>
			<% } %>

			<li <%if (ye >= maxPage) {%> class="disabled" <%}%>>
				<a href="student?type=search&page=<%=ye + 1%>
				&name=<%=stu.getName()%>&age=<%=stu.getAge()%>&sex=<%=stu.getSex()%>
				&className=<%=stu.getBanJi().getId()%>">
					下一页
				</a>
			</li>
			<li>
				<a href="student?type=search&page=<%=maxPage%>
				&name=<%=stu.getName()%>&age=<%=stu.getAge()%>&sex=<%=stu.getSex()%>
				&className=<%=stu.getBanJi().getId()%>">
					尾页
				</a>
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
