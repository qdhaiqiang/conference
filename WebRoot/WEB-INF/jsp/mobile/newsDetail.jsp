<%@ page language="java" import="java.util.*,javax.servlet.*,javax.servlet.http.*,org.springframework.web.servlet.support.RequestContextUtils" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Locale locale = RequestContextUtils.getLocale(request);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta name="viewport" content="width = device-width, user-scalable=no minimum-scale=1.0 maximum-scale=1.0" />
<title><spring:message code="titleInfo.meetingTitle" />
</title>
<link rel="stylesheet"
	href="<%=basePath%>css/plugins/mobile/jquery.mobile-1.4.4.min.css">
<link rel="stylesheet" href="<%=basePath%>css/common-mobile.css">
<script src="<%=basePath%>public/mobile/jquery.js"></script>
<script src="<%=basePath%>public/mobile/jquery.mobile-1.4.4.min.js"></script>
<style type="text/css">
body {
	margin: 0px;
	padding: 0px;
	font-family: "微软雅黑", "宋体";
	font-size: 15px;
	background-color: #fff;
}
.ui-content {
border-width: 0;
overflow: scroll;
/* overflow-x: hidden; */
padding: 1em;
}
</style>
	<script type="text/javascript">
	$(document).on("pageinit","#newsDetail",function() {
	});
</script>
</head>

<body>
	<div data-role="page" data-theme="a" id="newsDetail" style="margin-top: 30px;">
		<% if ("en_US".equals(locale.toString())) {  %>
			<input id="title" value="${confEssay.essayTitleEn }" type="hidden" />
		<% } else { %>
			<input id="title" value="${confEssay.essayTitle }" type="hidden" />
		<% } %>
		<jsp:include page="../../../include/header-mobile.jsp" />
		<div data-role="content">
			<% if ("en_US".equals(locale.toString())) {  %>
				${confEssay.essayContentEn }
			<% } else { %>
				${confEssay.essayContent }
			<% } %>
		</div>
	</div>
</body>
</html>
