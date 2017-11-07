<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
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

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title><spring:message code="titleInfo.meetingTitle" /></title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>public/ui/themes/default/easyui.css">
<script src="<%=basePath%>js/jquery-1.11.0.js"></script>
<script src="<%=basePath%>js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=basePath%>public/ui/jquery.easyui.min.js"></script>
<link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
<link href="<%=basePath%>css/common.css" rel="stylesheet">
<script src="<%=basePath%>js/message_cn.js" charset="gbk"
		type="text/javascript"></script>
<style>
#footer{
	height:180px;
	padding-top:30px;
}
</style>
<script type="text/javascript">
	function slideSwitch() {
		var $active = $('#slideshow IMG.active');
		if ($active.length == 0)
			$active = $('#slideshow IMG:last');
		// use this to pull the images in the order they appear in the markup
		var $next = $active.next().length ? $active.next()
				: $('#slideshow IMG:first');
		// uncomment the 3 lines below to pull the images in random order
		// var $sibs  = $active.siblings();
		// var rndNum = Math.floor(Math.random() * $sibs.length );
		// var $next  = $( $sibs[ rndNum ] );
		$active.addClass('last-active');
		$next.css({
			opacity : 0.0
		}).addClass('active').animate({
			opacity : 1.0
		}, 1000, function() {
			$active.removeClass('active last-active');
		});
	}

	$(function() {
		setInterval("slideSwitch()", 3000);
	});
</script>
</head>
<body>

	<div id="authorDiv" class="easyui-dialog" data-options="iconCls:'icon-save',modal:true" style="width:390px;top:160px">
		<div id="authorTop">
			<label id="authorityTitle">公司账号授权</label>
				<p id="authorityContent" style="width:360px;">
					是否将您的个人账号设为贵公司的登录账号，如同意可继续操作，不同意将不能继续.
				</p>
			</div>
			<!-- 同意与否的按钮,是否将您的个人账号设为贵公司的登录账号，如同意可继续操作，不同意将不能继续 -->
			<div id="authorButtons">
				<button id="approveBtn" onclick="gotoLoginSuccess()" style="width: 110px;margin-right: 20px;"><spring:message code="basicInfo.approve"/></button>
				<button id="disApproveBtn" onclick="gotoIndex()" style="width: 110px;"><spring:message code="basicInfo.disApprove"/></button>
			</div>
			<p style="text-indent:0px;color:#891300;">
				<spring:message code="basicInfo.protocolDes"/>
			</p>
	</div>
	
<div id="top">
<div id="top_1_0">
	<div class="clearfix">
		<ul class="top_1_ul" style="margin-left:10px;float:right;margin-right:180px;">
			<li>
				<a href="r/front/exhibition/login?locale=en_US">English&nbsp;&nbsp;&nbsp;&nbsp;</a>
			</li>
			<li>
				<a href="r/front/exhibition/login?locale=zh_CN" style="border-right:none;">中文</a>
			</li>
		</ul>
	</div>
</div>
</div>
	<div style="margin:20px">
		<img alt="logo" src="images/login-logo.png" width="450px;" height="120px;">
	</div>
	<div id="center" style="margin-top:-140px">
	<div class="container" style="border:none;">
		<div class="loginForm">
			<h3><spring:message code="login.welcomeLogin"/></h3>
			<div class="col-md-4 col-md-offset-4">
				<form role="form" method='POST' id="login-form" >
					<div class="form-group">
						<spring:message code="basicInfo.email"/><input class="form-control required email"
							autofocus name='email' onchange="emailChange(this)">
					</div>
					<div class="form-group">
						<spring:message code="enterPassword"/><input class="form-control required" 
							type="password" value="" name='password'>
					</div>
					<div class="checkbox" style="margin-bottom:0px;">
						<label> <input name="remember" type="checkbox"
							value="Remember Me"><spring:message code="login.remember"/></label>
					</div>
					<button type="button" id="btn-login"><spring:message code="applyExhibition"/></button>
					<a href="r/front/password_forget" target="_blank"><spring:message code="findPassword.title"/></a>
					<!-- Change this to a button or input when using this as a form 
                                <a href="index.html" class="btn btn-lg btn-success btn-block">Login</a>-->
				</form>


			</div>
		</div>
	</div>
	</div>
	<jsp:include page="../footer.jsp"></jsp:include>
	<script type="text/javascript">
		var form = $("#login-form");
		$(document).ready(function() {
			$("#authorDiv").dialog("close");//同意对话框隐藏
			form.validate();
			//点击登陆，验证用户名密码是否正确
			$("#btn-login").click(function() {
				loginFun();
			});

		});
		
		function loginFun(){
			form.validate();
			if(!form.valid()){
				return;
			}
			$.ajax({
				url : "front/exhibitCompany/exhibitionlogin",
				type : "post",
				data : $("#login-form").serialize(),
				success : function(result) {
					if(result.status==1){
						//直接登录
						gotoLoginSuccess();
					}else if(result.status==2){
						//是否将您的个人账号设为贵公司的登录账号，如同意可继续操作，不同意将不能继续
						dialogShow();
					}else{
						//用户名密码错
						alert('<spring:message code="invalidUsernamePwd"/>');
					}
				}
			});
		}
		
		function gotoLoginSuccess(){
			$("#login-form").attr("action",'front/exhibitCompany/exhibitionloginsuccess');
			$("#login-form").submit();
		}
		
		//是否将您的个人账号设为贵公司的登录账号，如同意可继续操作，不同意将不能继续
		function dialogShow(){
			$("#btn-login").attr("disabled","disabled");
			$("#authorDiv").dialog("open").dialog("setTitle","公司账号授权");
		}
		
		function gotoIndex(){
			window.location.href = "<%=basePath%>r/front/meeting_intro" ;
		}
		
		function emailChange(obj){
			$("#btn-login").attr("disabled",false);
		}
	</script>


</body>
</html>
