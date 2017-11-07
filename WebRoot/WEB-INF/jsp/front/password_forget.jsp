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
<script src="<%=basePath%>js/jquery-1.11.0.js"></script>
<script src="<%=basePath%>js/jquery.validate.js"></script>
<link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
<link href="<%=basePath%>css/common.css" rel="stylesheet">

<script type="text/javascript">


</script>
</head>
<body>
<div id="top">
	<span class="unvisi">世界传统医学文化保护与发展高级别（澳门）会议系统</span>
	 
</div>
<div id="center">
	<div class="container" style="width:1170px;border:none;">
		<div class="loginForm">
			<h3><spring:message code="findPassword.title"/></h3>
			<div class="col-md-4 col-md-offset-4">
				<form role="form" method='POST' id="password-form" action="r/front/login">
					<div class="form-group">
						<spring:message code="findPassword.email"/><input class="form-control required email"
							autofocus name='email' id="email">
					</div>
					     
					 
					<button type="button" id="findpwd-btn"><spring:message code="Submit"/></button>

					<!-- Change this to a button or input when using this as a form 
                                <a href="index.html" class="btn btn-lg btn-success btn-block">Login</a>-->
				</form>


			</div>
		</div>
	</div>
	</div>
	<div id="footer">
		<br>
		<p>主办单位：
			<a href="#" target="_blank">太湖世界文化论坛&nbsp;&nbsp;|&nbsp;&nbsp;</a>
			<a href="#" target="_blank">澳门特别行政区经济局&nbsp;&nbsp;|&nbsp;&nbsp;</a>
			<a href="#" target="_blank">南光（集团）有限公司</a>
		</p>
		<p>承办单位：
			<a href="#" target="_blank">太湖世界文化论坛常务理事会&nbsp;&nbsp; |&nbsp;&nbsp; </a>
			<a href="#" target="_blank">江西中医药大学&nbsp;&nbsp; |&nbsp;&nbsp;</a>
			<a href="#" target="_blank">南光展览工程有限公司</a></p>
		<p>协办单位：<a href="#" target="_blank">中国中医科学院&nbsp;&nbsp; |&nbsp;&nbsp;</a>
			<a href="#" target="_blank">中国中医科学院中医临床基础医学研究所&nbsp;&nbsp; |&nbsp;&nbsp; </a>
			<a href="#" target="_blank">香港浸会大学中医药学院&nbsp;&nbsp; |&nbsp;&nbsp; </a><a href="#" target="_blank">澳门科技大学</a>
		</p>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			
			var form = $("#password-form");
			form.validate();
			
			//点击登陆，验证用户名密码是否正确
			$("#findpwd-btn").click(function() {
				
				if(!form.valid()){
					return;
				}
				
				var email = $("#email").val();
				$.ajax({
					url : "front/reg/findPass",
					type : "post",
					data : {email:email},
					success : function(result) {
						if(result=="1"){
							alert("<spring:message code='passwordUpdate.newPasswordSent'/>");
							$("#password-form").submit();
						}else{
							//操作失败
							alert("<spring:message code='basicInfo.operationFailed'/>");
						}
					}
				});

			});

		});
	</script>


</body>
</html>
