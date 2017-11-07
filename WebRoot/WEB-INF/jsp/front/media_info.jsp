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
				<li><spring:message code="index.conference"/></li>
			</ol>
			<a href="r/front/password_update" style="margin-left:732px;padding:4px 10px;" id="updatePwd"><spring:message code="updatePassword.title"/></a>
			<div>
				<jsp:include page="media_info_basic.jsp"></jsp:include> 
			</div>
			<div>

				<div class="modal-body wizard-body" style="">
					<jsp:include page="formsRegister.jsp"></jsp:include>
				</div>
					
			</div>
		</div>
	</div>
	
	<jsp:include page="footer.jsp"></jsp:include>

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
						$("#wizard").bwizard("back");
					}
				}
			}
		}
	</script>


  </body>
</html>
