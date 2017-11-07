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
<link type="text/css" rel="stylesheet" href="<%=basePath%>public/layer/skin/layer.css" id="skinlayercss">
<script type="text/javascript" src="<%=basePath%>public/layer/layer.min.js"></script>
<%// 从Session中获取用户信息
	ConfUser confUser = (ConfUser)request.getSession().getAttribute(Constant.SESSION_CONF_USER);
	// 跳转到登录页面
	if (confUser==null) {
		String meetingId = (String)request.getSession().getAttribute(Constant.FRONT_SESSION_MEETING_ID);
	%>
		<script>
			/**layer.alert("您未登录或登录已失效，请重新登录",8,"提示",function () {
				window.location.href="<%=basePath%>front/reg/reLogin_app?meetingId=<%=meetingId%>";
			});
			**/
			window.location.href="<%=basePath%>front/reg/reLogin_app?meetingId=<%=meetingId%>";
		</script>
	<%
	}
 %>
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
	$(document).ready(function() {
		$("h1").html($("#title").val());
	});
</script>
</head>

<body>
	<div data-role="header" class="ui-shadow" style="width:100%;position:fixed;top:0;z-index:999999;">
			<h1></h1>
			<a href="#"  data-rel="back"  class="ui-btn ui-corner-all ui-shadow ui-btn-icon-left  ui-icon-back ui-btn-icon-notext">
			<spring:message code="mobile.common.back" />
			</a>
			<a href="<%=basePath%>r/mobile/index" data-ajax="false" class="ui-btn ui-corner-all ui-shadow ui-btn-icon-right ui-icon-home ui-btn-icon-notext ">
			<spring:message code="mobile.common.home" />
			</a>
		</div>
</body>
</html>
