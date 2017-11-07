<%@ page language="java" import="java.util.*,javax.servlet.*,javax.servlet.http.*,org.springframework.web.servlet.support.RequestContextUtils" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
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
</head>

<body>
	<div data-role="page" id="pageone" data-theme="a" style="margin-top: 30px;">
		<input id="title" value="<spring:message code="mobile.speaker.title" />" type="hidden"/>
		<jsp:include page="../../../include/header-mobile.jsp"/>
		<div data-role="content">
			<form method="post" action="demoform.asp">
				<fieldset data-role="controlgroup" >
					 
					<c:forEach var="schedule" items="${scheduleList}">
					<% if ("en_US".equals(locale.toString())) {  %>
				      			<label for="${schedule.scheduleId}">${schedule.titleEn}</label>
				      		<% } else { %>
				      			<label for="${schedule.scheduleId}">${schedule.title}</label>
				     <% } %>
						<input type="radio" name="schedule" id="${schedule.scheduleId}" 
							value="${schedule.scheduleId}" onclick="clickLabel(this.value)">
					</c:forEach>

				</fieldset>
			</form>

			<a href="r/mobile/question/#pagetwo" id="question-a" style="display: none" data-transition="slide" >提问</a>

		</div>
	</div>

	<div data-role="page" id="pagetwo" data-theme="a" style="margin-top: 30px;">
		<input id="title" value="<spring:message code="mobile.speaker.title" />" type="hidden"/>
		<jsp:include page="../../../include/header-mobile.jsp"/>
		<div data-role="content">
			<ul data-role="listview" data-inset="true" id="speakers-ul">
				 
			</ul>
		</div>
	</div>
	
	
	<div data-role="page" id="pageThree" data-theme="a" style="margin-top: 30px;">
		<input id="title" value="<spring:message code="mobile.speaker.title" />" type="hidden"/>
		<jsp:include page="../../../include/header-mobile.jsp"/>
		<div data-role="content">
		<label>个人简介/Introduction:</label>
			 <div id="intro"></div>
			 <div id="intro_en"></div>
		</div>
	</div>

<script type="text/javascript">
	$(document).ready(function() {
		 
 
	});
	
	function clickLabel(value){
		
		$("#question-a").click();
		$.ajax({
			url:"mobile/speaker/r",
			method:"get",
			data:{scheduleId:value},
			success: function(result){
				fillNames(result);
			}
		});
	}
	
	
	function fillNames(result){
		var ret = "";
		for(var i in result){
			ret +=" <li ><a href='#pageThree' onclick='showIntro(\""+result[i].self_intro+"\",\""+result[i].self_intro_en+"\")'>"
			if(result[i].sex==2){
				ret+="<img src='images/female.png' width='80' height='80' class='ui-li-thumb'>";
			}else{
				ret+="<img src='images/male.png' width='80' height='80'>";
			}
			ret += result[i].cname+" </a> </li> "
		}
		$("#speakers-ul").html(ret);
		$("#speakers-ul").listview("refresh");
	}
	
	
	function showIntro(intro,intro_en){
		if(intro!="null"){
			$("#intro").html(intro); 
		}
		if(intro_en!="null"){
			$("#intro_en").html(intro_en);
		}
	}
	
	$("#submit").click(function(){
		var sId = $("#sId").val();
		var content = $("#question").val();
		var nickname = $("#nickname").val();
		 
		$.ajax({
			url:"question/savefrontq",
			method:"post",
			data:{scheduleId:sId,content:content,nickname:nickname},
			success:function(result){
				if(resule=="1"){ //保存成功
					$("#result").append("<spring:message code='basicInfo.saveSucceed' />");
				}else{
					$("#result").append("<spring:message code='basicInfo.saveFailed' />");
				}
			}
		});
		 
	});
	
</script>

</body>
</html>

 