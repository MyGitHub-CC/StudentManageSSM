<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
   <base href="<%=basePath%>">
   
   <title>登录界面</title>
   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
	<script src="bootstrap/js/jquery.min.js" type="text/javascript"></script>
	<script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<style>
*{
	margin: 0;
	padding: 0;
}
body{
	background-color: #fff;
	color: #666;
	font: 12px/150% Arial,Verdana,"\5b8b\4f53";
}
a{
	text-decoration: none;
	color: #666666;	
}
div{
	display: block;
}
/*第一层：logo*/
#frist-head{
	width: 100%;
	height: 80px;
}
.w{
	width: 990px;
	margin: 0 auto;
}
#logo{
	width: 300px;
	height: 60px;
	margin: 10px 0;
	float: left;
	position: relative;
}
#logo b{
	width: 110px;
	height: 40px;
	position: absolute;
	right: 0;
	top: 10px;
	background: url(login/Welcome.png) no-repeat;
	display: block;
}
#logo-right{
	width: 150px;
	position: relative;
	top: 52px;
	float: right;
}
#logo-right b{
	width: 18px;
	height: 14px;
	margin: 0 5px;
	background: url(login/login-icon.png) no-repeat;
	display: inline-block;
	overflow: hidden;
	vertical-align: middle;
}
#logo-right a{
	color: #999;
}
/*第二层：主体内容*/
#second-content{				
	clear: both;
}
#content{
	height: 475px;
	margin: 10px 0 20px 0;
	position: relative;
	z-index: 5;
}
.content-bg{
	width: 100%;
	height: 475px;
	background-color: #19040b;
	position: absolute;
	left: 0;
	top: 0;
}
.content-form{
	width: 346px;
	background: #fff;
	float: right;
	position: relative;
	top: 40px;
	overflow: visible;
	z-index: 4;
	height: 361px;
}
.login-table-left , .login-table-right{
	width: 173px;
	height: 54px;
	font-size: 18px;
	font-family: "microsoft yahei";
	text-align: center;
	background: #fff;
	border: 1px solid #F4F4F4;
	position: absolute;
}
.login-table-right{
	right: 0;
}
.login-table-left a , .login-table-right a{
	width: 99%;
	height: 18px;
	position: absolute;
	left: 0;
	top: 18px;
}
.login-table-right a{
	font-weight: 700;
	color: #E4393C;
}

.form-input{					
	width: 305px;
	height: 209px;
	padding: 20px;
	
	position: absolute;
	top: 51px;
	left: 0;
}
.form-input .i-txt{
	width: 210px;
	height: 30px;
	line-height: 15px;
	padding-left: 10px;
	overflow: hidden;
	font-size: 14px;
	font-family: '\5b8b\4f53';
	margin: 10PX 0 0 10px;
	float:left;
}
#image{
	float:left;
	margin-top: 10PX;
	margin-left:20px;
}
.form-input .login-info{
	width:70px;
	line-height:50px;
	font-size: 14px;
	font-weight: bold;
	float:left;
	text-align:right;
}
#login{
	width: 302px;
	height: 31px;
	line-height: 31px;
	color: #FFFFFF;
	background: #E4393C;
	border: 1px solid #e85356;
	font-size: 20px;
	font-family: "microsoft yahei";
}
#safe-forget{
	width:305px;
	height: 20px;
	line-height: 20px;
	margin: 6px 0;
}
#safe-forget a{
	float: right;
}
.from-bottom{					
	width: 305px;
	height: 51px;
	padding: 0 20px;
	line-height: 50px;
	background-color:#fcfcfc;
	border-top:1px solid#f4f4f4 ;
	
	position: absolute;
	bottom: 0;
}
ul{
	list-style: none;
	display: block;
}
li{
	float: left;
	display: list-item;
}
.from-bottom a{
	padding-left: 24px;
	position: relative;
	float: left;
}
.QQ-icon , .weixin-icon{
	width: 19px;
	height: 18px;
	float: left;
	position: absolute;
	left: 0;
	top: 16px;
}
.div-line{
	color: #CCCCCC;
	padding: 0 10px;
}
.regist-icon{
	width: 61px;
	height: 22px;
	margin-top: 14px;
	margin-right:5px ;
	float: left;
}
.regist{
	position: absolute;
	right: 20px;
}
.regist a{
	color: #b61d1d;
	font-size: 14px;
	line-height: 50px;
	float: left;
}

#third-footer{
	width: 100%;
	height: 86px;
}
#third-footer .links a{
	margin: 0 10px;
}
.copyright{
	margin: 10px 0;
	text-align: center;
}
</style>
<script>
	if(self != top){
		top.location = self.location;
	}
	
	$().ready(function(){
		$("#image").click(function(){
			$(this).attr("src","login?type=randomImage&" + Math.random());
		});
		
		$("#login").click(function(){
			$.ajax({
				type:"post",
				url:"login?type=doLogin",
				data:$("#form").serialize(),
				dataType:"text",
				success:function(data){
					if(data == "success"){
						location.href="main";
					} else{
						$("#mes").html(data);
						$("#image").attr("src","login?type=randomImage&" + Math.random());
					}
				}			
			});
		});
	});

</script>
 </head>
  
 <%
 	String name = "";
 	Cookie[] cookies = request.getCookies();
 	for(int i = 0; cookies != null && i < cookies.length; i++){
 		if("userName".equals(cookies[i].getName())){
 			name = cookies[i].getValue();
 		}
 	}
  %>
  
  <body>
    <!-- 第一层：logo -->
		<div id="frist-head">
			<div class="w">
				<!-- 第一层：左侧logo -->
				<div id="logo">  
					<a href="//www.jd.com/">
						<img src="login/Login-logo.png" alt="京东"/>
					</a>
					<b></b>
				</div>
				<div id="logo-right">
					<a href="//surveys.jd.com/index.php?r=survey/index/sid/568245/lang/zh-Hans" target="_blank">
						<b></b>登录页面，调查问卷
					</a>
				</div>
			</div>
		</div>
		<!-- 第二层：主体内容 -->
		<div id="second-content">
			<div id="content">
				<div class="content-bg" >
					<div class="w">
						<div id="bg-img">
							<img src="login/content-bg.png" alt="手机京东"/>
						</div>
					</div>
				</div>
				<div class="w">
					<div class="content-form">
						<div class="login-table-left">  	<!-- 登录表单顶部 -->
							<a href="#">扫码登录</a>
						</div>
						<div class="login-table-right">
							<a href="#">账户登录</a>
						</div>
						
					<div class="form-input">			<!-- 登录表单中部 -->
						<form id="form" action="login" method="post">
							<input type="hidden" name="type" value="doLogin" />
								<div class="row">
									<span class="login-info" >用户名：</span>
									<input type="text" name="userName" id="userName" class="i-txt" placeholder="邮箱/用户名/已验证手机" />
								</div>
								<div class="row">
									<span class="login-info"> 密码：</span>
									<input type="password" name="password" id="pwd" class="i-txt" placeholder="密码" />
								</div>
								<div class="row">
									<span class="login-info"> 验证码：</span>
									<input type="text" name="random" id="image-input" class="i-txt"  placeholder="请输入验证码" style="width:100px" />
									<img src="login?type=randomImage" id="image" />
								</div>
								<div id="mes" ></div>
							<div id="safe-forget">
								<a href="#">忘记密码？</a>
							</div>
							<input type="button" value="登&nbsp;&nbsp;&nbsp;&nbsp;录" id="login" />
						</form>
					</div>
						
						<div class="from-bottom">			<!-- 登录表单底部 -->
							<ul>
								<li>
									<a href="#"  class="QQ-link">
										<img src="login/QQ-icon.jpg" class="QQ-icon"/>
										<span>  QQ </span>
									</a>
									<span class="div-line"> | </span>
								</li>
								<li>
									<a href="#" class="weixin-link">
										<img src="login/weixin.png" class="weixin-icon"/>
										<span>&nbsp; 微信 </span>
									</a>
								</li>
								<li class="regist">
									<a href="#" class="regist-link">
										<img src="login/register.png" class="regist-icon"/>
										<span> 立即注册 </span>
									</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 第一层：统一信息 -->
		<div id="third-footer">
			<div class="w">
				<div class="links">
					<a href="#">关于我们</a>
					|
					<a href="#">联系我们</a>
					|
					<a href="#">人才招聘</a>
					|
					<a href="#">商家入驻</a>
					|
					<a href="#">广告服务</a>
					|
					<a href="#">手机京东</a>
					|
					<a href="#">友情链接</a>
					|
					<a href="#">销售联盟</a>
					|
					<a href="#">京东社区</a>
					|
					<a href="#">京东公益</a>
					|
					<a href="#">English Site</a>
				</div>
			</div>
			
			<div class="copyright">
            	Copyright © 2004-2017  京东JD.com 版权所有
			</div>
		</div>
  </body>
</html>
