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
<link href="<%=basePath%>style/css.css"
	rel="stylesheet" />
 
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
					href="javascript:void(0)"><spring:message
							code="basicInfo.personalInfo" /></a></li>
				<li class="bs-wizard-todo"><a href="javascript:void(0)"> <spring:message
							code="basicInfo.contact" /></a></li>
				<li class="bs-wizard-todo"><a href="javascript:void(0)"> <spring:message
							code="basicInfo.identification" /></a></li>
				<li class="bs-wizard-todo bs-wizard-active"><a
					href="javascript:void(0)"> <spring:message code="basicInfo.position"/></a></li>
				<li class="bs-wizard-todo"><a href="javascript:void(0)"> <spring:message
							code="basicInfo.overheadInformation" /></a></li>

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
				 
					<div class="bs-step inv">
						  <jsp:include page="basic_tab1.jsp"></jsp:include> 
					</div>
					
					<div class="bs-step inv" id="contact">
						  <jsp:include page="contact_tab2.jsp"></jsp:include> 
					</div>

					<!-- 证件信息 -->
					<div class="bs-step inv">
						 <jsp:include page="cert_total_tab3.jsp"></jsp:include> 
					</div>

					<!-- 单位信息和职务 -->
					<div class="bs-step inv" id="company-position">
						 <jsp:include page="media_company_tab4.jsp"></jsp:include>
					</div>

					<!-- 附加信息 -->
					<div class="bs-step inv">
						  <jsp:include page="extra_tab5.jsp"></jsp:include> 
						  
						  
					</div>

				</fieldset>
			</form>
		</div>
	</div>

	<script src="<%=basePath%>js/plugins/bswizard/dist/bs-wizard-min.js"
		type="text/javascript"></script>

<!-- 首先需要引入plupload的源代码 -->
    <script src="<%=basePath%>js/plupload.full.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/fileuploaduser.js"></script>

	<script type="text/javascript">

	$(function() {
			
			var option = {backText:'<spring:message code='goBack'/>',nextText:'<spring:message code='nextStep'/>',beforeNext: before_next};
			$(".bs-wizard").bs_wizard(option);
			
			var button = "<button id='last-back' type='button' class='btn btn-default'><spring:message code='goBack'/></button>"
			+"<button id='btn-basic-submit' type='button' class='btn btn-primary submit-btn'><spring:message code='Submit'/></button>";
			
			$("#last_entourage_div").after(button);
			
			
			//最后一个tab前一页事件单独写
			$('#last-back').click(function() {
				$(".bs-wizard").bs_wizard('show_step', 4);
			});
			
			var id = $("#id").val();
			if(id==""){
				var guestInfo = "<div class='col-sm-8' style='padding:20px 0px;'><p><label class='required-label'>*</label><spring:message code='basicInfo.guestEmail'/>：</p>"
					+"<input type='text' class='form-control' id='guestEmail' value='' name='guestEmail'> <p><spring:message code='basicInfo.name'/>：</p>"
					+" <input type='text' class='form-control' id='guestName' readonly='readonly'>"
					+" <input type='hidden' class='form-control' id='guestId' name='guestId'></div> "
					+" <div class='col-sm-4 help entourage-div'>"
					+" <h4> <span style='color:#891300;'><spring:message code='basicInfo.help'/></span> <span><spring:message code='basicInfo.guestEmail'/></span>"
					+" </h4> <p> <span><spring:message code='basicInfo.guestEmailHelp'/></span></p></div><div class='clearfix'></div>";
					
					$('#cname-div').before(guestInfo);
			}
			
			
			$("#guestEmail").blur(function(){ 
				var email = $("#guestEmail").val();
				$.ajax({
					type : "POST",
					url : "front/reg/checkguestemail",
					data:{email:email},
					success : function(result) {
						if (result.status == "0") {
							alert("<spring:message code='basicInfo.VIPNotRegister'/>");
						}else{
							$("#guestId").val(result.guestId);
							$("#guestName").val(result.guestCName+"  "+result.guestEName);
						}
					}

				});
			});
			
			//保存基本信息
			$('#btn-basic-submit').click(function() {
				if("${front_approveState}" == "2"){
					alert("<spring:message code='basicInfo.alreadyVerified'/>");//已审核通过，不允许修改
				}else{
					$("#btn-basic-submit").attr({"disabled":"disabled"});
					 
					//若是随行人员，嘉宾email必填
						 
					 if(id==""&&$("#guestEmail").val().Trim()==""){
						 alert("<spring:message code='basicInfo.noVIPEmail'/>");
						 $("#btn-basic-submit").removeAttr("disabled");//将按钮可用
						 return;
					 }
					 
					 
					//检查必填项
					if(!check_tab5()){
						$("#btn-basic-submit").removeAttr("disabled");//将按钮可用
						return;
					}
					
					var approveState = $("#approveState").val();
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
							  isSubmit = true;
							  if(result.status=="1"){
							  //邀请媒体 和 随行人员 注册成功直接跳转到注册成功页面，无需填写会议和日程信息
								// alert("<spring:message code='basicInfo.saveSucceed'/>");
							  	// window.location.href='<%=basePath%>r/front/reg_success';
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
				if($("#id").val()==""){ // 注册的时候判断邮箱是否为空。
					if($("#guestEmail").val().Trim()=="" || $("#guestName").val().Trim()==""){
						 alert("<spring:message code='basicInfo.noVIPEmail'/>");
						 return false;
					 }
				}
				return check_tab1();
			} else if (current_step()==2) {
				return check_tab2();
			} else if (current_step()==3){
				return check_total_tab3();
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

