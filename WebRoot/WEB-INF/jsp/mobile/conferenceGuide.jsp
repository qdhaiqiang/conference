<%@ page language="java" import="java.util.*,javax.servlet.*,javax.servlet.http.*,org.springframework.web.servlet.support.RequestContextUtils" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Locale locale = RequestContextUtils.getLocale(request);
%>
<!-- 主讲嘉宾 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta name="viewport" content="width = device-width, user-scalable=no minimum-scale=1.0 maximum-scale=1.0" />
<title><spring:message code="titleInfo.meetingTitle" />
</title>
<link rel="stylesheet" href="<%=basePath%>css/common-mobile.css">
<link rel="stylesheet"
	href="<%=basePath%>css/plugins/mobile/jquery.mobile-1.4.4.min.css">
<script src="<%=basePath%>public/mobile/jquery.js"></script>
<script src="<%=basePath%>public/mobile/jquery.mobile-1.4.4.min.js"></script>
</head>

<body>
	<div data-role="page" data-theme="a" id="news" style="margin-top: 30px;">
		<input id="title" value="<spring:message code="mobile.common.conferenceGuide" />" type="hidden"/>
		<jsp:include page="../../../include/header-mobile.jsp"/>
		<div data-role="content" >
			<ul data-role="listview"  data-inset="true" id="ulContent">
				<c:forEach items="${mobileGuideList }" var="guide">
				<li>
					<a href="<%=basePath%>article/mobile/${guide.id}" data-ajax="false">
						<% if ("en_US".equals(locale.toString())) {  %>
							${guide.essay_title_en}
						<% } else { %>
							${guide.essay_title}
						<% } %>
					</a>
				</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</body>
</html>
