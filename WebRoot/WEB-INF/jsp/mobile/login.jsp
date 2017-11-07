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
<meta name="viewport" content="width = device-width, user-scalable=no minimum-scale=1.0 maximum-scale=1.0" />
<title><spring:message code="titleInfo.meetingTitle" /></title>
<link rel="stylesheet"
	href="<%=basePath%>css/plugins/mobile/jquery.mobile-1.4.4.min.css">
<link rel="stylesheet" href="<%=basePath%>css/common-mobile.css">
<script src="<%=basePath%>public/mobile/jquery.js"></script>
<script src="<%=basePath%>public/mobile/jquery.mobile-1.4.4.min.js"></script>
<style type="text/css">
.ui-content {
border-width: 0;
overflow: visible;
overflow-x: hidden;
padding: 10px;
}
#warnning {
	text-align: center;
	color: red;
	font-size: 15px;
	line-height: 10px;
}

</style>
<script type="text/javascript">
	$(document).ready(function(){  
    	$("#btnLogin").bind('click',function(){
    		var warnning = $("#warnning");
			warnning.html("&nbsp;");
			var email = $.trim($("#fInputEmail").val());
			var pwdval = $.trim($("#finputpwd").val());
			if (email == "") {
				//请输入电子邮箱
				warnning.html('<spring:message code="mobile.login.inputEmail"/>');
				return false;
			}
			if (pwdval == "") {
				//请输入密码
				warnning.html('<spring:message code="mobile.login.inputPwd"/>');
				return false;
			}
			$.mobile.loading("show");
			$.ajax({
				url : "<%=basePath%>front/reg/login_app",
				type : "post",
				data : $("#login-form").serialize(),
				success : function(result) {
					$.mobile.loading("hide");
					if (result.status == 1) {
						//跳转到首页
						window.location.href="<%=basePath%>r/mobile/index";
						//jQuery.mobile.changePage("<%=basePath%>r/mobile/index");
					} else {
						//用户名密码错
						warnning.html('<spring:message code="invalidUsernamePwd"/>');
					}
				}
			});
    	}); 
	});
</script>
</head>

<body>
	<div data-role="page" id="loginPage" data-dom-cache="true">
		<!-- 图片 -->
		<jsp:include page="../../../include/banner-mobile.jsp"></jsp:include>
		<!-- 表单 -->
		<div data-role="content" data-theme="c" >
			<form role="form" method='POST' id="login-form">

				<ul data-role="listview" data-inset="true">
					<li><label for="fInputEmail" class="lableArea"> <spring:message
								code="basicInfo.email" /> </label>
						<input type="text" name="email" id="fInputEmail"
						data-clear-btn="true"></li>
					<li><label for="finputpwd" class="lableArea"> <spring:message
								code="enterPassword" /> </label>
						 <input type="password" name="password"
						id="finputpwd" data-clear-btn="true">
					</li>
				</ul>
				<div id="warnning">&nbsp;</div>
				<input id="btnLogin" type="button" value="<spring:message code="mobile.login" />">
			</form>
		</div>
	</div>
</body>
</html>
