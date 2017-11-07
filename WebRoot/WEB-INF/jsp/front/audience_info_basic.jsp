<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<link href="<%=basePath%>js/plugins/bswizard/dist/bs-wizard-min.css"
	rel="stylesheet" />
<script src="<%=basePath%>js/jquery.validate.bootstrap.popover.js"></script>
<script src="<%=basePath%>js/common.js"></script>

<style type="text/css">

.pw-label {
	margin: 0 0 7px -12px;
	padding: 7px 0 7px 12px;
	font: bold 12px Times, serif;
	color: #891300;
	border-top: 0;
}

.panel-bar{
	background-color:#C2A580 !important;
	color:#ffffff !important;
}

.combo{
	width: 300px !important;
}
fieldset a{
	color:black;
}
fieldset a:link{
	color:black;
}
fieldset a:visited{
	color:black;
}
fieldset a:hover{
	color:black;
}
fieldset a:active{
	color:black;
}
</style>

<script type="text/javascript">

	//阻止上传图片button点击
	document.onkeydown = eventaction;
	
</script>

</head>

<body>
	<div class="row bs-wizard">
		<div class="col-lg-3" style="width:20%">
			<ol class="bs-wizard-sidebar">
				<li class="bs-wizard-todo bs-wizard-active"><a
					href="javascript:void(0)"><spring:message code="basicInfo.personalInfo"/></a></li>
				<li class="bs-wizard-todo"><a href="javascript:void(0)">
						<spring:message code="basicInfo.contact" /></a></li>
				<li class="bs-wizard-todo"><a href="javascript:void(0)">
						<spring:message code="basicInfo.identification"/></a></li>
				<li class="bs-wizard-todo bs-wizard-active"><a
					href="javascript:void(0)"> <spring:message code="basicInfo.position"/></a></li>

			</ol>
		</div>
		<div class="col-lg-9">
			<form method="POST" id="basic_form" class="new_client" accept-charset="UTF-8">
				<input type="hidden" name="approveState" id="approveState"
					value="${user.approveState}">
				<input type="hidden" name="userType" id="userType"
					value="${roleCode}">
				<input type="hidden" name="id" id="id"
					value="${user.id}">
				<fieldset>
					<div class="bs-step inv" id="basic-info">
						 <jsp:include page="basic_tab1.jsp"></jsp:include> 
					</div>

					<div class="bs-step inv">
						<jsp:include page="contact_tab2.jsp"></jsp:include> 
					</div>

					<!-- 证件信息 -->
					<div class="bs-step inv">
						<jsp:include page="cert_tab3.jsp"></jsp:include>
					</div>
					
					<!-- 4职务信息-->
					<div class="bs-step inv">
						
						<jsp:include page="company_tab4.jsp"></jsp:include>

					</div>


				</fieldset>
			</form>
		</div>
	</div>

	<script src="<%=basePath%>js/plugins/bswizard/dist/bs-wizard-min.js"
		type="text/javascript"></script>

	<script type="text/javascript">
	$(function() {
			var option = {backText:'<spring:message code='goBack'/>',nextText:'<spring:message code='nextStep'/>',beforeNext: before_next};
			$(".bs-wizard").bs_wizard(option);
			//$("#basic_form").validate();
			
			var button = "<button id='last-back' type='button' class='btn btn-default'><spring:message code='goBack'/></button>"
			+"<button id='btn-basic-submit' type='button' class='btn btn-primary submit-btn'><spring:message code='Submit'/></button>";
			
			$("#last-div-audience").after(button);
			
			//最后一个tab前一页事件单独写
			$('#last-back').click(function() {
				$(".bs-wizard").bs_wizard('show_step', 3);
			});
			
			//保存基本信息
			$('#btn-basic-submit').click(function() {
				if("${front_approveState}" == "2"){
					alert("<spring:message code='basicInfo.alreadyVerified'/>");//已审核通过，不允许修改
				}else{
					$("#btn-basic-submit").attr({"disabled":"disabled"});
					 
					var approveState = $("#approveState").val();

					//审核通过，不能保存基本信息
					if (approveState == "2") {
						alert("<spring:message code='basicInfo.alreadyVerified'/>");
						return;
					}
					
					//验证最后一个tab
					if(!check_tab4()){
						$("#btn-basic-submit").removeAttr("disabled");//将按钮可用
						return;
					}
					
					var form = $("#basic_form");

					$.ajax({
						  type:"post",
						  url:'front/reg/registersave',
						  data:form.serialize(),
						  success:function(result){
							  isSubmit = true;
							  if(result.status=="1"){
								  //基本信息保存成功后跳转 观众有会议和日程信息
								  // alert("<spring:message code='basicInfo.saveSucceed'/>");
								  alert("<spring:message code='basicInfo.goToMeetingTip'/>");
								  $(".next").click();
							  }else if(result.status=="2"){
								  alert("<spring:message code='basicInfo.alreadyVerified'/>");
							  }else if(result.status=="3"){
								  alert("<spring:message code='basicInfo.alreadySaved'/>");
							  }else{
								  alert("<spring:message code='basicInfo.saveFailed'/>");
							  }
				 			  $("#btn-basic-submit").removeAttr("disabled");//将按钮可用
						}
					});
				}		
 			});
		});
	
		// 分步验证
		function before_next() {
			if (current_step()==1) {
				return check_tab1();
			} else if (current_step()==2) {
				return check_tab2();
			} else if (current_step()==3) {
				return check_tab3();
			} 
			return true;
		}
		
		function current_step() {
		    return $(".bs-wizard").bs_wizard('option', 'currentStep');
		}
	</script>
	
</body>
</html>
