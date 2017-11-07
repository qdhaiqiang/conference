<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<div class="panel panel-default">
	<div class="panel-heading">
		<spring:message code="basicInfo.positionInfo" />
	</div>
	<div class="panel-body bs-step-inner">
		<!-- 所在单位/原单位 -->
		<div class="col-sm-8">
			<label class="required-label">*</label><spring:message code="basicInfo.company" />
			<input type="text" class="form-control" maxlength="50" id="company" value="${user.company}" name="company">
		</div>
		<div class="col-sm-4 help">
			<h4>
				<span style="color:#891300;"><spring:message code="basicInfo.help" /></span>
				<span><spring:message code="basicInfo.company" /></span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.companyHelp" /> </span>
			</p>
		</div>
		<div class="clearfix"></div>

		<!-- 企业性质 -->
		<div class="col-sm-8">
			<label class="required-label">*</label><spring:message code="basicInfo.companyType" /><br>
			<input id="companyType" class="easyui-combobox" name="companyType" editable="false" value="${user.companyType}" data-options="valueField:'code',textField:'name',url:'dict/r/company_type',method:'get',required:'true'" />
		</div>
		<div class="col-sm-4 help">
			<h4>
				<span style="color:#891300;"><spring:message code="basicInfo.help" /></span>
				<span><spring:message code="basicInfo.companyType" /></span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.companyTypeHelp" /></span>
			</p>
		</div>
		<div class="clearfix"></div>
		
		<!-- 所有单位所属行业类别 -->
		<div class="col-sm-8">
			<label class="required-label">*</label><spring:message code="basicInfo.industry" /><br>
			<input id="industry" class="easyui-combobox" name="industry" editable="false" value="${user.industry}" data-options="valueField:'code',textField:'name',url:'dict/r/industry',method:'get',required:'true'" />
		</div>
		<div class="col-sm-4 help">
			<h4>
				<span style="color:#891300;"><spring:message code="basicInfo.help" /></span>
				<span><spring:message code="basicInfo.industry" /></span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.industryHelp" /></span>
			</p>
		</div>
		<div class="clearfix"></div>
	
		<!-- 个人职务  -->
		<div class="col-sm-8">
			<label class="required-label">*</label><spring:message code="basicInfo.position" />
			<input type="text" class="form-control" maxlength="50" id="position" name="position" value="${user.position}">
		</div>
		<div class="col-sm-4 help">
			<h4>
				<span style="color:#891300;"><spring:message code="basicInfo.help" /></span>
				<span><spring:message code="basicInfo.position" /></span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.companyPosition" /> </span>
			</p>
		</div>
		<div class="clearfix" id="last-div-audience"></div>
	</div>
</div>

<script type="text/javascript">
	var check_tab4 = function() {

		//检查必填项
		if ($("[name='company']").val() == ""){ 
			//提示基本信息不完整
			alert("<spring:message code='basicInfo.company' />" +"  "+ "<spring:message code='basicInfo.lackOfInfo'/>");
			document.getElementById("company").focus();
			return false;
		}	
		 
		if ($("[name='companyType']").val() == ""){
			//提示基本信息不完整
			alert("<spring:message code='basicInfo.companyType' />" +"  "+ "<spring:message code='basicInfo.lackOfInfo'/>");
			return false;
		}
		
		if ($("[name='industry']").val() == ""){
			//提示基本信息不完整
			alert("<spring:message code='basicInfo.industry' />" +"  "+ "<spring:message code='basicInfo.lackOfInfo'/>");
			return false;
		}
		
		if ($("[name='position']").val() == ""){
			//提示基本信息不完整
			alert("<spring:message code='basicInfo.position' />" +"  "+ "<spring:message code='basicInfo.lackOfInfo'/>");
			document.getElementById("position");
			return false;
		}
		 
		return true;	
	}
</script>