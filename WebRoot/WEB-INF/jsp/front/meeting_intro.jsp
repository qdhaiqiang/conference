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
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="">
	
	<link href="<%=basePath%>css/exhibition_login.css" rel="stylesheet">
	<script src="<%=basePath%>js/jquery-1.7.2.min.js"></script>
	<script src="<%=basePath%>js/work.js"></script>
	<title><spring:message code="titleInfo.meetingTitle" /></title>
	<script type="text/javascript">
	$(document).ready(function(){
		    var userAgent = window.navigator.userAgent.toLowerCase();
		    $.browser.msie10 = $.browser.msie && /msie 10\.0/i.test(userAgent);
		    $.browser.msie9 = $.browser.msie && /msie 9\.0/i.test(userAgent); 
		    $.browser.msie8 = $.browser.msie && /msie 8\.0/i.test(userAgent);
		    $.browser.msie7 = $.browser.msie && /msie 7\.0/i.test(userAgent);
		    $.browser.msie6 = !$.browser.msie8 && !$.browser.msie7 && $.browser.msie && /msie 6\.0/i.test(userAgent);
		    if($.browser.msie6||$.browser.msie7||$.browser.msie8||$.browser.msie9){
		    	//$("#a11").attr("href","javascript:void(0)");
		    	//$("#a12").attr("href","javascript:void(0)");
		    	//$("#a13").attr("href","javascript:void(0)");
		    	$("#a11").click(function(){
		    		return false;
		    	});
		    	$("#a12").click(function(){
		    		return false;
		    	});
		    	$("#a13").click(function(){
		    		return false;
		    	});
		    	$("#navul").click(function(){
		    		return false;
		    	});
			    $("#warningbanner").slideDown("slow");
		    }
	});
	</script>
	
<style type="text/css">

#navul a{
	color:white;
}

</style>
</head>
<body>
	<div id="warningbanner" style="display:none;text-align:center; margin:0 auto; width:88%; height:60px; position:relative;font-size: 22px;padding: 16px;border-radius: 4px;color: #a94442;border-color: #ebccd1;background-color: #f2dede; box-sizing: border-box;">
		<spring:message code="index.explorerwarning"/>
	</div>
	
	<!-- 按钮及中英文切换 -->
	<div id="top_1_0">
		<div class="clearfix">
			<ul class="top_1_ul">
				<li>
					<a target="_blank" href="r/front/contact_us"  title="<spring:message code="index.contactUs"/>"><spring:message code="index.contactUs"/>&nbsp;&nbsp;</a>
				</li>
				<li>
					<a target="_blank" id="a11" href="front/reg/emailCheck/2" title="<spring:message code="index.participantenter"/>"><spring:message code="index.participantenter"/>&nbsp;&nbsp;</a>
				</li>
				<li>
					<a target="_blank" id="a12" href="front/reg/emailCheck/1" title="<spring:message code="index.staffenter"/>"><spring:message code="index.staffenter"/>&nbsp;&nbsp;</a>
				</li>
				<li>
					<a target="_blank" id="a13" href="r/front/login" title="<spring:message code="index.tologin"/>" style="border-right:none;"><spring:message code="index.tologin"/></a>
				</li>
			</ul>
			<ul class="top_1_ul" style="float:right;margin-right:190px;">
				<li>
					<a href="r/front/meeting_intro?locale=en_US">English&nbsp;&nbsp; </a>
				</li>
				<li>
					<a href="r/front/meeting_intro?locale=zh_CN" style="border-right:none;">中文</a>
				</li>
			</ul>
		</div>
	</div>
	
	<div class="container">
		<img src="images/meeting_banner.jpg" style="margin-top:-11px;margin-left: 22px;"/>
		<div class="navbg" style="margin-left: 22px;margin-top:10px;">
			<div class="col960">
			    <ul id="navul" class="cl">
			      <c:forEach items="${confEssayMenu['essayTypeList'] }" var="essayType">
			      	<li>
			      		<% if ("en_US".equals(locale.toString())) {  %>
			      			<a href="javascript:void(0)">${essayType.typeNameEn }</a>
			      			<%-- <a href="<%=basePath %>r/front/exhibition/exhibition_index">${essayType.typeNameEn }</a> --%>
			      		<% } else { %>
			      			<a href="javascript:void(0)">${essayType.typeName }</a>
<%-- 			      			<a href="<%=basePath %>r/front/exhibition/exhibition_index">${essayType.typeName }</a> --%>
			      		<% } %>
			      		<ul>
			      		<c:forEach items="${confEssayMenu['essayMap'] }" var="essayMap">
			      			<c:if test="${essayMap.key==essayType.id }">
			      				<c:forEach items="${essayMap.value }" var="secondEssayType">
			      					<li>
			      						<% if ("en_US".equals(locale.toString())) {  %>
			      							<a href="<%=basePath %>confExhibitionController/goToExhibitIndex/${secondEssayType.id}">${secondEssayType.typeNameEn }</a>
			      						<% } else { %>
			      							<a href="<%=basePath %>confExhibitionController/goToExhibitIndex/${secondEssayType.id}">${secondEssayType.typeName }</a>
			      						<% } %>
			      					</li>
			      				</c:forEach>
			      			</c:if>
			      		</c:forEach>
			      		</ul>
			      	</li>
			      </c:forEach>
			    </ul>
			</div>
		</div>
	
		<div style="background:#F4F2E5;width:842px;padding:5px 0 10px 0;margin-left: 22px;">
			<div class="pcon">
				<%
					if ("zh_CN".equals(locale.toString())) {
				%>
						${frontMeeting.meetingIntro }
				<% 
					} else {
				%>
					${frontMeeting.meetingIntroEn }
				<% 
					}
				%>
			</div>
			<div style="width:100%;height:1px">&nbsp;</div>
	
			<jsp:include page="footer.jsp"></jsp:include>
		</div>
	</div>
</body>
</html>