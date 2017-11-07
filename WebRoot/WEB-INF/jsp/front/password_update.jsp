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

<%--<title><spring:message code="updatePassword.title"/></title>--%>
<title><spring:message code="titleInfo.meetingTitle" /></title>
<script src="<%=basePath%>js/jquery-1.11.0.js"></script>
<script src="<%=basePath%>js/jquery.validate.js"></script>
<link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
<script src="<%=basePath%>js/common.js"></script>
<link href="<%=basePath%>css/common.css" rel="stylesheet">
<style>
#footer{
	height:180px;
	padding-top:30px;
}
</style>
</head>
<body>
<div id="top">
<div id="top_1_0">
	<div class="clearfix">
		<ul class="top_1_ul" style="margin-left:10px;float:right;margin-right:180px;">
			<li>
				<a href="r/front/password_update?locale=en_US">English&nbsp;&nbsp;&nbsp;&nbsp;</a>
			</li>
			<li>
				<a href="r/front/password_update?locale=zh_CN" style="border-right:none;">中文</a>
			</li>
		</ul>
	</div>
</div>
</div>
<div id="center">
	<div class="container" style="width:1170px;border:none;">
		<div class="loginForm">
			<h3><spring:message code="updatepasswd.welcome"/></h3>
			<div class="col-md-4 col-md-offset-4">				
				<form role="form" method='POST' id="chg-psw-form" >
					<div class="form-group">
						<spring:message code="oldPassword"/>
						<input class="form-control required" type="password" id="oldPass"
							autofocus name='oldPass'>
					</div>
					<div class="form-group">
						<spring:message code="password"/><input class="form-control required"
							type="password" value="" name='newPass' id="newPass">
					</div>
					<div class="form-group">
						<spring:message code="Repeat"/><input class="form-control required" 
							type="password" value="" name='passRepeat' id="passwordRepeat">
					</div>
					<button type="button" id="btn-login"><spring:message code="Submit"/></button>

					<!-- Change this to a button or input when using this as a form 
                                <a href="index.html" class="btn btn-lg btn-success btn-block">Login</a>-->
				</form>
			</div>
		</div>
	</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
	<script type="text/javascript">
		$(document).ready(function() {
			
			var form = $("#chg-psw-form");
			form.validate();
			
			//点击登陆，验证用户名密码是否正确
			$("#btn-login").click(function() {
				
				if(!form.valid()){
					return;
				}
				
				var pw1 = $("#newPass").val();
				var pw2 = $("#passwordRepeat").val();
				if(pw1==""){
					alert("<spring:message code='basicInfo.passwordEmpty'/>");
					return;
				}
				if (pw1!=pw2) {
					alert("<spring:message code='pwdnotmatch'/>");
					$("#passwordRepeat").val("");
					return;
				}  
				
				var oldPw = $("#oldPass").val();
				
				$.ajax({
					url : "front/reg/changePass",
					type : "post",
					data : {oldPass:oldPw,newPass:pw1},
					success : function(result) {
						if(result=="0"){
							alert("<spring:message code='basicInfo.operationSucceed'/>");
							//跳转到哪儿呢？？？？
							window.location.href='<%=basePath%>r/front/login';
						}else if(result=="2"){
							//用户名密码错
							alert("<spring:message code='passwordUpdate.passwordError'/>");
						}else{
							//用户名密码错
							alert("<spring:message code='basicInfo.operationFailed'/>");
						}
					}
				});

			});

		});
	</script>
</body>
</html>
