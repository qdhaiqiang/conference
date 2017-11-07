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
<base href="<%=basePath%>">

<link href="<%=basePath%>js/plugins/bswizard/dist/bs-wizard-min.css"
	rel="stylesheet" />

<script src="<%=basePath%>js/common.js"></script>
<script src="<%=basePath%>js/jquery.validate.js"></script>
<script src="<%=basePath%>js/plupload.full.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/fileupload.js"></script>

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
						<spring:message code="basicInfo.contact"/></a></li>
				<li class="bs-wizard-todo"><a href="javascript:void(0)">
						<spring:message code="basicInfo.identification"/></a></li>
				<li class="bs-wizard-todo bs-wizard-active"><a
					href="javascript:void(0)"> <spring:message code="basicInfo.position"/></a></li>
			</ol>
		</div>
		<div class="col-lg-9"> 
		<form method="POST" id="staff-info-form" action="front/reg/staffsave"
			accept-charset="UTF-8">
		
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
					<jsp:include page="staff_company_tab4.jsp"></jsp:include>
				</div>
			</fieldset>

		</form>
		</div>
	</div>
	
	<script src="<%=basePath%>js/plugins/bswizard/dist/bs-wizard-min.js"
		type="text/javascript"></script>
	
	<script>
		
		$(document).ready(function(){  
		   	if ($("#userType").val()=="7" || $("#userType").val()=="18") 
		   	{
		   		$(".hide1").hide();
		   	}
		   	
		   	if ($("#userType").val()=="9" || $("#userType").val()=="20") 
		   	{
		   		$(".hide3").hide();
		   	}
		   	
		});  
		$(function() {
			
			var option = {backText:'<spring:message code='goBack'/>',nextText:'<spring:message code='nextStep'/>',beforeNext: before_next};
			$(".bs-wizard").bs_wizard(option);
			
			var button = "<button id='last-back' type='button' class='btn btn-default'><spring:message code='goBack'/></button>"
			+"<button id='btn-basic-submit' type='button' class='btn btn-primary submit-btn'><spring:message code='Submit'/></button>";
			
			$("#last-div-staff").after(button);
			
			//最后一个tab前一页事件单独写
			$('#last-back').click(function() {
				$(".bs-wizard").bs_wizard('show_step', 3);
			});
			
			//保存基本信息
			$('#btn-basic-submit').click(  function() {
				//用户审核状态，1未审核，2审核通过，3审核未通过
				if("${front_approveState}" == "2"){
					var r = confirm("<spring:message code='basicInfo.alreadyVerified'/>");
				    if (r == false ||　r == true) {  //已审核通过，不允许修改
				        return false;
				    }
				}else{
					var r = confirm("<spring:message code='schedule.confirmHelp'/>");
				    if (r == false) {  ////提示提交之后不允许修改;取消，重新选择日程，确定，继续保存
				        return false;
				    }
					$("#btn-basic-submit").attr({"disabled":"disabled"});
					
					var approveState = $("#approveState").val();
		
					//审核通过，不能保存基本信息
					if (approveState == "2") {
						alert("<spring:message code='basicInfo.alreadyVerified'/>");
						return;
					}
					
					//验证最后一个tab
					if(!check_staff_tab4()){
						$("#btn-basic-submit").removeAttr("disabled");//将按钮可用
						return;
					}
					
					var form = $("#staff-info-form");
					//Ajax提交保存
					$.ajax({
						  type:"post",
						  url:'front/reg/registersave',
						  data:form.serialize(),
						  success:function(result){
							  if(result.status=="1"){
								  alert("<spring:message code='basicInfo.saveSucceed'/>");
								  window.location.href='<%=basePath%>r/front/reg_success';
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
			} else if (current_step()==3){
				return check_tab3();
			} 
			
			return true;//false不跳转
		}
		
	  	function current_step() {
		    return $(".bs-wizard").bs_wizard('option', 'currentStep');
		}
		
	</script>
	
</body>
</html>
