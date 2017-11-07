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
</style>
	<script type="text/javascript">
	$(document).on("pageinit","#meetingSchedulePage",function() {
		$.mobile.loading("show");
		$.ajax({
					url : "<%=basePath%>mySchedule/r",
					type : "get",
					success : function(result) {
						$.mobile.loading("hide");
						var html="";
						
						$(result).each(function(i, obj) {
							//html+="<li data-role=\"list-divider\" >"+obj.location+"</li>";
							$(obj.schedules).each(function(i, obj) {
								html+="<li >";
								html+="<h2 style='white-space:pre-wrap;'>"+obj.title+"</h2>";
								/**if ("en_US"=="<%=locale%>") {//英文
									html+="<h2>"+obj.titleEn+"</h2>";
								} else {
									html+="<h2>"+obj.title+"</h2>";
								}**/
								html+="<p><strong>"+obj.startTime+" ~ "+obj.endTime+"</strong></p>";
								html+="<p>"+obj.intro+"</p>";
								/**if ("en_US"=="<%=locale%>") {//英文
									html+="<p>"+obj.introEn+"</p>";
								} else {
									html+="<p>"+obj.intro+"</p>";
								}**/
								html+="</a></li>";
							});
						});
						if(html==""){
							html+="<li><spring:message code="mobile.common.mySchedule.noData" /></li>";
						}
						$("#ulContent").html(html);
						$( "#ulContent" ).listview( "refresh" );
					},
					error:function(){
						$.mobile.loading("hide");
					}
				});
	});
</script>
</head>

<body>
	<div data-role="page" data-theme="a" id="meetingSchedulePage" style="margin-top: 30px;">
		<input id="title" value="<spring:message code="mobile.common.mySchedule" />" type="hidden"/>
		<jsp:include page="../../../include/header-mobile.jsp"/>
		<div data-role="content" >
				<ul data-role="listview" data-inset="true" id="ulContent">
					
				</ul>
			</div>
	</div>
</body>
</html>
