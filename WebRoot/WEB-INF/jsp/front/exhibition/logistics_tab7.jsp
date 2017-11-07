<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<div class="tabp7">
		<h3><spring:message code="exhibition.tab7.info1"/></h3>
		<p>
			<input type="hidden" name="expressNeeds.expressneedsId" value="${expressneed.expressneedsId }">
			<spring:message code="exhibition.tab7.info2"/><spring:message code="exhibition.tab7.info2.hint"/></p>
		<div style="padding-left:30px;margin-bottom:20px;">
			<c:choose>
				<c:when test="${expressneed.expressNeed eq 'Y'}">
					<input type="radio" name="expressNeeds.expressNeed" checked="checked" value="Y"><spring:message code="YES"/><br>
					<input type="radio" name="expressNeeds.expressNeed" value="N"><spring:message code="NO"/>
				</c:when>
				<c:when test="${expressneed.expressNeed eq 'N'}">
					<input type="radio" name="expressNeeds.expressNeed" value="Y"><spring:message code="YES"/><br>
					<input type="radio" name="expressNeeds.expressNeed" checked="checked" value="N"><spring:message code="NO"/>
				</c:when>
				<c:otherwise>
					<input type="radio" name="expressNeeds.expressNeed" value="Y"><spring:message code="YES"/><br>
					<input type="radio" name="expressNeeds.expressNeed" value="N"><spring:message code="NO"/>
				</c:otherwise>
			</c:choose>
		</div>	
		
		<div id="expressneedfile" style="padding-left:30px;">
			<font><spring:message code="exhibition.tab7.info3"/>：</font>
			<div style="padding:6px 0;" id="expressneedfilelist"></div>
			<div style="padding-bottom:6px;" id="expressneedimg"></div>
			<input id="expressneedinput" name="expressNeeds.expressFile" value="${expressneed.expressFile}" readonly="readonly">
			<a id="expressneedbrowse" href="javascript:;">[<spring:message code="basicInfo.selectFile" />]</a>
			
			<spring:message code="exhibition.comments"/>：<input type="text" name="expressNeeds.memo" value="${expressneed.memo }">	
		</div>
		<div style="padding-left:30px;margin-bottom:20px;">
			<a href="<%=basePath %>file/6B19B546-7EC2-4F6E-BE05-C06ACA851C6C.docx" style="color:#EA8511;"><spring:message code="exhibition.tab7.info4"/></a>
		</div>
		<c:forEach items="${expresses}" var="express" varStatus="status">
			${status.index+1 }、
			<p><spring:message code="exhibition.tab7.info5"/>:${express.expressCompany}&nbsp;&nbsp;/&nbsp;&nbsp;${express.expressCompanyEn}</p>
			<p><spring:message code="exhibition.address"/>:${express.expressAddress}</p>
			<p><spring:message code="exhibition.tab7.info6"/>:${express.expressOrder}&nbsp;&nbsp;/&nbsp;&nbsp;${express.expressOrderEn}</p>
			<p><spring:message code="exhibition.tele"/>:${express.expressTele}</p>
			<p><spring:message code="exhibition.fax"/>:${express.expressFax}</p>
			<p><spring:message code="exhibition.tab7.info1"/>:${express.expressMobile}</p>
			<p><spring:message code="basicInfo.email"/>:${express.expressEmail}</p>
		</c:forEach>
	</div>
	
	
	<script type="text/javascript">
	$(function(){
		$("#expressneedfile").hide();
	});
	var expressneed_params = {
		optiontype:"metting",
		sign:'${frontMeetingId}',//后期修改成从session中取mettingid
		filecategory:'${user_info.email}/exhibitions'
	};
	initUploader("expressneedbrowse","expressneedfilelist","expressneedimg","expressneedinput","expressneedfile",fileuploadurl,false,0,0,false,expressneed_params);
	
	//分步验证
	function checkTab6(){
		return true;
	}
	</script>
</html>