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

<!-- 首先需要引入plupload的源代码 -->
<link href="<%=basePath%>js/plugins/bswizard/dist/bs-wizard-min.css" rel="stylesheet" />
 
<script src="<%=basePath%>js/jquery.validate.js"></script>

<script src="<%=basePath%>js/jquery.validate.bootstrap.popover.js"></script>

<script src="<%=basePath%>js/common.js"></script>

<link href="<%=basePath%>style/css.css" rel="stylesheet" />

<style type="text/css">
 
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
				<li class="bs-wizard-todo"><a href="javascript:void(0)">
						<spring:message code="basicInfo.overheadInformation"/></a></li>
				<li class="bs-wizard-todo"><a href="javascript:void(0)">
						<spring:message code="basicInfo.selfIntroduction"/></a></li>
				<li class="bs-wizard-todo"><a href="javascript:void(0)">
						<spring:message code="basicInfo.otherInfo"/></a></li>

			</ol>
		</div>
		<div class="col-lg-9">
			<form method="POST" id="basic_form" accept-charset="UTF-8">
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
						<jsp:include page="contact_total_tab2.jsp"></jsp:include>
					</div>

					<div class="bs-step inv">
						<jsp:include page="cert_total_tab3.jsp"></jsp:include>
					</div>


					<!-- 4、职位信息 -->
					<div class="bs-step inv">
						<jsp:include page="company_tab4.jsp"></jsp:include>
					</div>


					<!-- 5附加信息 -->
					<div class="bs-step inv">
						<jsp:include page="extra_tab5.jsp"></jsp:include>
					</div>

					<!-- 6 个人简介 -->
					<div class="bs-step inv">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title"><spring:message code="basicInfo.introduction"/></h3>
							</div>
							<div class="panel-body bs-step-inner">
								<p><spring:message code="basicInfo.introduction"/></p>

								<div class="form-group">
									<div class="col-sm-12">
										<textarea rows="10" style="margin-bottom:20px;width:90%" id="selfIntro" class=" max-len-1024"
											name="selfIntro">${user.selfIntro}</textarea>

									</div>
								</div>
								
								<p><spring:message code="basicInfo.introductionEn"/></p>

								<div class="form-group">
									<div class="col-sm-12">
										<textarea rows="10" style="margin-bottom:20px;width:90%" id="selfIntroEn" class=" max-len-1024"
											name="selfIntroEn">${user.selfIntroEn}</textarea>

									</div>
								</div>

							</div>
						</div>
					</div>
					
					<!-- 7其他-->
					<div class="bs-step inv">
						<jsp:include page="others_tab7.jsp"></jsp:include>
					</div>

				</fieldset>
			</form>
		</div>
	</div>

	<script src="<%=basePath%>js/plugins/bswizard/dist/bs-wizard-min.js"
		type="text/javascript"></script>

	<script type="text/javascript">
		$("#contact-div").hide(); //默认指定联系人不显示
		$(".entourage-div").hide();
		 
		$(function() {
			
			var option = {backText:'<spring:message code='goBack'/>',nextText:'<spring:message code='nextStep'/>',beforeNext: before_next};
			$(".bs-wizard").bs_wizard(option);
			
			//最后一个tab前一页事件单独写
			$('#last-back').click(function() {
				$(".bs-wizard").bs_wizard('show_step', 6);
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
					
					if (!current_step()) {
						$("#btn-basic-submit").removeAttr("disabled");//将按钮可用
						return;
					}
					
					if($("[name='useAlias']:checked").val() == 1 && $("[name='ualias']").val()=="") {

						alert("<spring:message code='basicInfo.inputAlias' />"+"  "+"<spring:message code='basicInfo.lackOfInfo'/>");		
						$("#btn-basic-submit").removeAttr("disabled");//将按钮可用
						return false;
						
					}
					if($("[name='useOtherPtitle']:checked").val() == 1 && $("[name='positionTitle']").val()=="") {
						 
						alert("<spring:message code='basicInfo.useOtherPositionTitle' />"+"  "+"<spring:message code='basicInfo.lackOfInfo'/>");		
						$("#btn-basic-submit").removeAttr("disabled");//将按钮可用
						return false;	 
					}
					
					var form = $("#basic_form");
					$.ajax({
						  type:"post",
						  url:'front/reg/registersave',
						  data:form.serialize(),
						  success:function(result){
							  isSubmit = true;
							  if(result.status=="1"){
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
				return check_total_tab2();
			} else if (current_step()==3){
				return check_total_tab3();
			} else if (current_step()==4) {
				return check_tab4();
			} else if (current_step()==5) {
				return check_tab5();
			} else if (current_step()==6) {
				if ($("[name='selfIntro']").val()=="" && $("[name='selfIntroEn']").val()==""){
					alert("<spring:message code='basicInfo.introductionEn'/>"+"   "+"<spring:message code='basicInfo.lackOfSelfIntro'/>");
					return false;
				}
			} 
			
			return true;//false不跳转
		}
		
	  	function current_step() {
		    return $(".bs-wizard").bs_wizard('option', 'currentStep');
		}
		</script>
	
</body>
</html>
