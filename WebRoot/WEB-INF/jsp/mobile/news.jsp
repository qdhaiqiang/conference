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
</style>
	<script type="text/javascript">
	$(document).on("pageinit","#news",function() {
		/*$.mobile.loading("show");
		$.ajax({
					url : "<%=basePath%>meetingSchedule/r",
					type : "get",
					success : function(result) {
						$.mobile.loading("hide");
						var html="";
						
						$(result).each(function(i, obj) {
							html+="<li data-role=\"list-divider\" >"+obj.location+"</li>";
							$(obj.schedules).each(function(i, obj) {
								html+="<li >";
								html+="<h2>"+obj.title+"</h2>";
								html+="<p><strong>"+obj.startTime+" ~ "+obj.endTime+"</strong></p>";
								html+="<p>"+obj.intro+"</p>";
								html+="</a></li>";
							});
						});
						$("#ulContent").html(html);
						$( "#ulContent" ).listview( "refresh" );
					},
					error:function(){
						$.mobile.loading("hide");
					}*/
				});
	});
</script>
</head>

<body>
	<div data-role="page" data-theme="a" id="news" style="margin-top: 30px;">
		<input id="title" value="<spring:message code="mobile.common.news" />" type="hidden"/>
		<jsp:include page="../../../include/header-mobile.jsp"/>
		<div data-role="content" >
				<ul data-role="listview"  data-inset="true" id="ulContent">
					<c:forEach items="${mobileNewsList }" var="news">
					<li>
						<a href="<%=basePath%>article/mobile/${news.id}" data-ajax="false">
							<% if ("en_US".equals(locale.toString())) {  %>
								${news.essay_title_en}
							<% } else { %>
								${news.essay_title}
							<% } %>
						</a>
					</li>
					</c:forEach>
				</ul>
			</div>
	</div>
</body>
</html>
