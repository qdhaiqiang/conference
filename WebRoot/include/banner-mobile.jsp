<%@ page language="java" import="java.util.*,
	com.centling.conference.base.Constant,
	com.centling.conference.user.entity.ConfUser" pageEncoding="UTF-8"%>
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
<meta name="viewport" content="width = device-width, user-scalable=no minimum-scale=1.0 maximum-scale=1.0" />
<title><spring:message code="titleInfo.meetingTitle" />
</title>

<style type="text/css">
#banImg {
	width: 100%;
	height: auto;
}
</style>
<script type="text/javascript">
</script>
</head>

<body>
	<div data-position="fixed" data-fullscreen="true" class="ui-shadow" >
		<img id="banImg" src="<%=basePath%>images/meeting_banner.jpg">
	</div>
</body>
</html>
