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

 
<script src="<%=basePath%>js/plupload.full.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/fileupload.js"></script>

<link href="<%=basePath%>js/plugins/bswizard/dist/bs-wizard-min.css"
	rel="stylesheet" />
 
<script src="<%=basePath%>js/jquery.validate.js"></script>
<script src="<%=basePath%>js/jquery.validate.bootstrap.popover.js"></script>
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
						<spring:message code="basicInfo.otherInfo"/></a></li>

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

					<div class="bs-step inv">
						<jsp:include page="cert_tab3.jsp"></jsp:include>
					</div>


					<!-- 4、职位信息 -->
					<div class="bs-step inv">
						<jsp:include page="media_company_tab4.jsp"></jsp:include>
					</div>
					
					<!-- 5备注 -->
					<div class="bs-step inv">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">
									<spring:message code="basicInfo.remarks" />
								</h3>
							</div>
							<div class="panel-body bs-step-inner">
								<p>
									<spring:message code="basicInfo.remarks" />
								</p>

								<div class="col-sm-12">
									<textarea rows="10" style="margin-bottom:20px;width:90%"
										id="remark" class=" max-len-1024" name="remark">${user.remark}</textarea>
								</div>
								<button id="last-back" type="button" class="btn btn-default">
									<spring:message code="goBack" />
								</button>
								<button id="btn-basic-submit" type="button"
									class="btn btn-primary submit-btn">
									<spring:message code="Submit" />
								</button>


							</div>
						</div>
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
			
			//最后一个tab前一页事件单独写
			$('#last-back').click(function() {
				$(".bs-wizard").bs_wizard('show_step', 4);
			});
			
			//保存基本信息
			$('#btn-basic-submit').click(function() {
				if("${front_approveState}" == "2"){
					alert("<spring:message code='basicInfo.alreadyVerified'/>");//已审核通过，不允许修改
				}else{
					 $("#btn-basic-submit").attr({"disabled":"disabled"});
					 
						//审核通过，不能保存基本信息
						if (approveState == "2") {
							alert("<spring:message code='basicInfo.alreadyVerified'/>");
							return;
						}
						
						var form = $("#basic_form");
	
						$.ajax({
							  type:"post",
							  url:'front/reg/registersave',
							  data:form.serialize(),
							  success:function(result){
								  isSubmit=true;
								  if(result.status=="1"){
									  //基本信息保存成功后跳转
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
			} else if (current_step()==3){
				return check_tab3();
			} else if (current_step()==4) {
				return check_tab4_brief();
			}
			
			return true;//false不跳转
		}
		
	  	function current_step() {
		    return $(".bs-wizard").bs_wizard('option', 'currentStep');
		}
	</script>
	
</body>
</html>
