<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="panel panel-default">
	<div class="panel-heading">
		<spring:message code="basicInfo.positionInfo" />
	</div>
	<div class="panel-body bs-step-inner">

		<div class="col-sm-8">
			<label class="required-label">*</label>
			<spring:message code="basicInfo.company" />
			<input type="text" class="form-control" maxlength="50" id="company"
				value="${user.company}" name="company">
		</div>
		<div class="col-sm-4 help">
			<h4>
				<span style="color:#891300;"><spring:message
						code="basicInfo.help" /> </span> <span><spring:message
						code="basicInfo.company" /> </span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.companyHelp" /> </span>
			</p>
		</div>
		<div class="clearfix"></div>

		<div class="col-sm-8">
			<label class="required-label">*</label>
			<spring:message code="basicInfo.Position" />
			<input type="text" class="form-control" maxlength="50" id="position"
				name="position" value="${user.position}">
		</div>
		<div class="col-sm-4 help">
			<h4>
				<span style="color:#891300;"><spring:message
						code="basicInfo.help" /> </span> <span><spring:message
						code="basicInfo.Position" /> </span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.companyPosition" /> </span>
			</p>
		</div>
		<div class="clearfix" id="last-div-audience"></div>
	</div>
</div>


<script type="text/javascript">
	//新注册用户不能修改密码
	$(document).ready(function(){
		
		var email = $("#email").val();
		//photoUploader(email);
		var userType = $("#userType").val();
		if(userType=="23"||userType=="24"){ //配偶只检查移动电话，移动电话红星显示
			$("#contact .required-label:not(.mobile)").remove();
			$("#company-position .required-label").remove();
		}
		
	});
	
 
	var check_tab4_brief = function(){
		
		//检查必填项
		//配偶的公司和职位信息可以为空,随行人员必填
		var userType = $("#userType").val();
		if(!(userType=="23")&&!(userType=="24")){
			if( $("[name='company']").val().Trim()==""){
				//提示基本信息不完整
				alert("<spring:message code='basicInfo.company'/>"+"  "+"<spring:message code='basicInfo.lackOfInfo'/>");
				return false;
			}
			if( $("[name='position']").val().Trim()==""){
				
				//提示基本信息不完整
				alert("<spring:message code='basicInfo.position'/>"+"  "+"<spring:message code='basicInfo.lackOfInfo'/>");
				return false;
			}
		} 
		
		return true;	
		
	};
	
	

</script>
