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

<title>录入成绩</title>

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
	margin-right:40px;
}
th{
	text-align:center;
}
</style>


<script>
  $(document).ready(function(){
  	var selectId = 0;
	
	$("tr").click(function(){
		$("tr").removeClass("info");
		$(this).addClass("info");
		selectId = $(this).data("id");
	});
	
	function clearMes(){
		obj.next().html("");
	}
	var obj = "";
	$("[name='score']").blur(function(){
		obj = $(this);
		$.ajax({
			type:"post",
			url:"score?type=input",
			data:"score="+$(this).val()+"&scoreId="
			+  $(this).data("id")
			+  "&stuId="+$(this).data("stuid") 
			+ "&subId=" + $(this).data("subid"),
			dataType:"text",
			success:function(data){
				obj.next().html(data);
				setTimeout(clearMes,1000);
			}
		});
	});
	
});
</script>
</head>

<body>
	<%
		List<Score> scores = (List<Score>) (request.getAttribute("scores"));
		List<BanJi> banJis = (List<BanJi>) (request.getAttribute("banJis"));
		List<Subject> subjects = (List<Subject>) (request.getAttribute("subjects"));
		Score conditionScore = (Score) (request.getAttribute("conditionScore"));
		int ye = (Integer) request.getAttribute("page");
		int maxPage = (Integer) request.getAttribute("maxPage");
		String name = "";
		int bjId = 0;
		int subId = 0;
		if(request.getAttribute("conditionScore") != null){
			name = conditionScore.getStudent().getName();
			bjId = conditionScore.getStudent().getBanJi().getId();
		   subId = conditionScore.getSubject().getId();
		}
	%>
	<div style="width:780px;margin:20px auto ">
		<form action="score" method="post">
			<input type="hidden" name="type" value="showInput" />
			<span class="search-span">姓名：</span>
			<input type="text" name="name"  class="form-control search-input" 
			value="<%if(conditionScore.getStudent().getName() != null)
			{%><%=name %><%} %>" />
				<span class="search-span" >班级：</span>
			<div class="form-group search-input" >
				<select name="bjId" class="form-control" style="width:120px;">
					<option value="0">所有班级</option>
					<%	for (BanJi banJi : banJis) { %>
						<option value="<%=banJi.getId()%>" 
						 <%if(bjId == banJi.getId()){ %>selected="selected"<% } %>>
						 <%=banJi.getName()%>
						</option>
					<% }  %>
				</select>
			</div>
				<span class="search-span">科目：</span>
			<div class="form-group search-input" >
				<select name="subId" class="form-control" style="width:120px;">
					<option value="0">所有科目</option>
					<%	
						for (Subject subject : subjects) { 
					%>
					<option value="<%=subject.getId()%>" 
						 <%if(conditionScore.getSubject().getId() == subject.getId()){ %>selected="selected"<% } %>>
						 <%=subject.getName()%>
					</option>
					<% }  %>
				</select>
			</div>
			<input type="submit" value="查询"  class="btn btn-primary"  style="width:80px;float:left;margin-left:20px;"/>
		</form>
		
		<table class="table table-bordered table-striped" style="text-align:center;">
			<tr class="info">
				<th>学生id</th>
				<th>姓名</th>
				<th>班级</th>
				<th>科目</th>
				<th>成绩</th>
			</tr>
			<%
				for (Score score : scores) {
			%>
			
			<tr>
				<td><%=score.getStudent().getId()%></td>
				<td><%=score.getStudent().getName()%></td>
				<td><%=score.getStudent().getBanJi().getName()%></td>
				<td><%=score.getSubject().getName()%></td>
				<td>
					<input type="text" name="score" value=<%=score.getScore()%>
					 style="width:100px;" 
					 data-id="<%=score.getId() %>" 
					 data-stuid="<%=score.getStudent().getId() %>" 
					 data-subid="<%=score.getSubject().getId() %>" />
					 <div> </div>
				</td>
			</tr>
			<%
				}
			%>
		</table>
		<ul class="pagination" style="margin-left:0">
			<li>
				<a href="score?type=showInput&page=<%=1%>
				&name=<%=conditionScore.getStudent().getName()%>
				&bjId=<%=conditionScore.getStudent().getBanJi().getId()%>
				&subId=<%=conditionScore.getSubject().getId()%>">
					首页
				</a>
			</li>

			<li <%if (ye <= 1) {%> class="disabled" <%}%>>
				<a href="score?type=showInput&page=<%=ye - 1%>
				&name=<%=conditionScore.getStudent().getName()%>
				&bjId=<%=conditionScore.getStudent().getBanJi().getId()%>
				&subId=<%=conditionScore.getSubject().getId()%>">
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
				<a href="score?type=showInput&page=<%=i%>
				&name=<%=conditionScore.getStudent().getName()%>
				&bjId=<%=conditionScore.getStudent().getBanJi().getId()%>
				&subId=<%=conditionScore.getSubject().getId()%>">
					<%=i%>
				</a>
			</li>
			<% } %>

			<li <%if (ye >= maxPage) {%> class="disabled" <%}%>>
				<a href="score?type=showInput&page=<%=ye + 1%>
				&name=<%=conditionScore.getStudent().getName()%>
				&bjId=<%=conditionScore.getStudent().getBanJi().getId()%>
				&subId=<%=conditionScore.getSubject().getId()%>">
					下一页
				</a>
			</li>
			<li>
				<a href="score?type=showInput&page=<%=maxPage%>
				&name=<%=conditionScore.getStudent().getName()%>
				&bjId=<%=conditionScore.getStudent().getBanJi().getId()%>
				&subId=<%=conditionScore.getSubject().getId()%>">
					尾页
				</a>
			</li>
		</ul>
	</div>
</body>
</html>
