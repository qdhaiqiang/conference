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
		<input id="title" value="<spring:message code="mobile.question.title" />" type="hidden"/>
		<jsp:include page="../../../include/header-mobile.jsp"/>
		<div data-role="content">
				<label><spring:message code="mobile.question.schedule" /></label> 
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
			<a href="r/mobile/question/#pagetwo" id="question-a" style="display: none" data-transition="slide" >提问</a>

		</div>
	</div>

	<div data-role="page" id="pagetwo" data-theme="a" style="margin-top: 30px;">
		<input id="title" value="<spring:message code="mobile.question.title" />" type="hidden"/>
		<jsp:include page="../../../include/header-mobile.jsp"/>
		<div data-role="content">
			<form id="form">
				<div data-role="fieldcontain">
					<label for="question"><spring:message code="mobile.question.question"/>:</label>
					   <textarea id="question" name="content" class="text" style="height:50px"></textarea>
					<label for="nickname"><spring:message code="mobile.question.nickname"/>: </label>
					<input type="text" name="nickname" id="nickname">
					<input name="scheduleId" id="sId" type="hidden">
				</div>
				<a data-role="button" id="submit"><spring:message code="Submit"/></a>
			</form>
			
		</div>
	</div>
	

<script type="text/javascript">
 
	function clickLabel(value){
		
		$("#sId").val(value);
		$("#question").val("");
		$("#questionEn").val("");
		$("#nickname").val("");
		$("#question-a").click();
		
	}
	
	$("#submit").click(function(){
		var sId = $("#sId").val();
		var content = $("#question").val();
		var nickname = $("#nickname").val();
		if(content!=undefined &&　content.length==0){ 
			layer.alert("<spring:message code='mobile.question.question' />"+" "+"<spring:message code='basicInfo.lackOfInfo'/>");	
			return; 
		} 
		
		$.ajax({
			url:"question/savefrontq",
			method:"post",
			data:{scheduleId:sId,content:content,nickname:nickname},
			success:function(result){
				if(result=="1"){ //保存成功 
					//$("#result").html("<spring:message code='basicInfo.saveSucceed' />"); 
					//layer.alert("<spring:message code='basicInfo.saveSucceed' />",1); 
					layer.alert("<spring:message code='basicInfo.saveSucceed'/>",1,"<spring:message code='mobile.vote.tips' />",function () { 
						//window.location.href='<%=basePath%>r/mobile/index'; 
						window.location.href="<%=basePath%>question/enter/1";
					}); 

				}else{ 
					//$("#result").html("<spring:message code='basicInfo.saveFailed' />"); 
					layer.alert("<spring:message code='basicInfo.saveFailed' />",8); 
				} 
			}
		});
		 
	});
	
</script>

</body>
</html>

 