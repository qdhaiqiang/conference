<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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


<jsp:include page="../../../include/common.jsp"></jsp:include>
<%--<title><spring:message code="titleInfo.meetingTitle" /></title>--%>
<script src="<%=basePath%>js/jquery.form.js"
		type="text/javascript"></script>
<link href="<%=basePath%>js/plugins/bwizard/css/bwizard.min.css"
	rel="stylesheet" />

<style>
.label-inverse,.badge-inverse {
	background-color: #333;
}

.label {
	background-color: #999;
}

.regi_banner{
	margin-bottom:4px;
}

.container h2{
	color:#000;
}

#footer{
	padding-top: 30px;
	text-align: center;
	font-size: 12px;
	padding-bottom:20px;
	margin:0 auto;
}

#footer a{
	font-size: 12px;
	color:#999;
	text-decoration:none;
}
#footer a:hover{
	color:#F9970B;
}
#footer p{
	font-size: 12px;
	color:#999;
	line-height: 12px;
}
#top_1_0 {
background: url(images/home_03.png) repeat-x;
height: 30px;
min-width: 1000px;
position: relative;
font-size:13px;
}

#top_1_0 a {
text-decoration: none;
color: #5D0D08;
border-right:1 solid #CCCBCB;
}

#top_1_0 a:hover {
color: #F9970B;
}


.top_1_ul{
display: block;
list-style-type: disc;
margin-left:180px;
float:left;
margin-top:0px;
}

.top_1_ul li {
float: left;
margin-right: 10px;
line-height: 30px;
}
li {
list-style: none;
}
#updatePwd{
	color:#BFA27A;
	background:#EEEBD8;
	border-radius:10px;
	font-size:12px;
}
a:hover{
	text-decoration:none;
}
</style>

</head>

<body>
	<div class="container" style="height:auto;">
		<div class="regi_banner"><img src="images/reg_banner.jpg" /></div>
		<div id="wizard">
			<ol>
				<li><spring:message code="index.baseInfo"/></li>
				<li><spring:message code="index.conference"/></li>
			</ol>
			<a href="r/front/password_update" style="margin-left:732px;padding:4px 10px;" id="updatePwd"><spring:message code="updatePassword.title"/></a>
			<div>
				<jsp:include page="basic_info2.jsp"></jsp:include> 
			</div>
			<div>
				<div class="modal-body wizard-body" style="">
					<jsp:include page="formsRegister.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</div>
	
	<jsp:include page="footer.jsp"></jsp:include>

	<script src="<%=basePath%>js/plugins/bwizard/js/bwizard.min.js"
		type="text/javascript"></script>
	<script type="text/javascript">
	var isSubmit = false;
	var optionBwizard = {backBtnText:'<spring:message code='goBack'/>', nextBtnText:'<spring:message code='nextStep'/>',activeIndexChanged:indexChanged};
		$("#wizard").bwizard(optionBwizard);
		
		$(".previous").attr("style","display:none");
		$(".next").attr("style","display:none");
		
		function indexChanged(e, ui) {
			// 如果是会议信息
			if (ui.index==1) {
				// 判断session中是否有userid,如果没有，则退回第一个标签 
				var userId = '${sessionScope.user_info.id}';
				if (userId=="") {
					if (!isSubmit) {
						alert("<spring:message code='index.incomplete'/>");
						$("#wizard").bwizard("back");
					}
				}
			}
		}
		
	</script>
</body>
</html>
