<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<%--	<title><spring:message code="staffIndex.title"/></title>--%>
<%--<title><spring:message code="titleInfo.meetingTitle" /></title>--%>

	<jsp:include page="../../../include/common.jsp"></jsp:include>
	<script src="<%=basePath%>js/jquery.form.js" type="text/javascript"></script>
	
	<link href="<%=basePath%>style/css.css" rel="stylesheet" />
	
	<link href="<%=basePath%>js/plugins/bwizard/css/bwizard.min.css"
		rel="stylesheet" />
	<script src="<%=basePath%>js/plugins/bwizard/js/bwizard.min.js"
		type="text/javascript"></script>
		
</head>

<body>

	<div class="container" style="height:auto;">
		<div class="regi_banner"><img src="images/reg_banner.jpg" /></div>
		<div id="wizard">
			<ol>
				<li><spring:message code="index.baseInfo"/></li>
			</ol>
			<a href="r/front/password_update" style="margin-left:732px;padding:4px 10px;" id="updatePwd"><spring:message code="updatePassword.title"/></a>
			<div>
				<jsp:include page="staff_info_basic.jsp"></jsp:include> 
			</div>
		</div>
	</div>
	
	<jsp:include page="footer.jsp"></jsp:include>
	
	<script type="text/javascript">
		var optionBwizard = {backBtnText:'<spring:message code='goBack'/>', nextBtnText:'<spring:message code='nextStep'/>'};
		$("#wizard").bwizard(optionBwizard);
		$(".bwizard-buttons").remove();
	</script>
</body>
</html>
