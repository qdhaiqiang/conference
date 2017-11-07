<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<table class="swtable" style="height:500px;">
	<tr>
		<td><abbr class='requiredtitle' title='必填required'>*</abbr><spring:message code="exhibition.companyName"/><spring:message code="exhibition.Chinese"/></td>
		<td>
			<input type="text" style="width:400px;" value="${company.companyNameCn}" name="companyNameCn" class="formcontrol" onchange="changeCompanyNameCn(this)">
		</td>
	</tr>
	<tr>
		<td><spring:message code="exhibition.companyName"/><spring:message code="exhibition.English"/></td>
		<td>
			<input type="text" style="width:400px;" value="${company.companyNameEn}" name="companyNameEn" class="formcontrol">
		</td>
	</tr>
	<tr>
		<td><abbr class='requiredtitle' title='必填required'>*</abbr><spring:message code="exhibition.address"/></td>
		<td>
			<input type="text" style="width:400px;" value="${company.companyAddress}" name="companyAddress" class="formcontrol">
		</td>
	</tr>
	<tr>
		<td><abbr class='requiredtitle' title='必填required'>*</abbr><spring:message code="exhibition.tele"/></td>
		<td>
			<input type="text" style="width:400px;" value="${company.companyTel}" name="companyTel" class="formcontrol">
		</td>
	</tr>
	<tr>
		<td><spring:message code="basicInfo.faxNo"/></td>
		<td>
			<input type="text" style="width:400px;" value="${company.companyFax}" name="companyFax" class="formcontrol">
		</td>
	</tr>
	<tr>
		<td><abbr class='requiredtitle' title='必填required'>*</abbr><spring:message code="basicInfo.email"/></td>
		<td>
			<input type="text" style="width:400px;" value="${company.companyEmail}" name="companyEmail" class="formcontrol easyui-validatebox" validType="email">
		</td>
	</tr>
	<tr>
		<td><spring:message code="exhibition.website"/></td>
		<td>
			<input type="text" style="width:400px;" value="${company.companyWebsite}" name="companyWebsite" class="formcontrol easyui-validatebox" validType="url">
		</td>
	</tr>
	<tr>
		<td><spring:message code="exhibition.contact"/></td>
		<td id="contactPersonTd">
			${company.contactPerson}
		</td>
	</tr>
	<tr>
		<td><spring:message code="exhibition.title"/></td>
		<td id="contactJobTd">
			${company.contactJob}
		</td>
	</tr>
	<tr>
		<td><abbr class='requiredtitle' title='必填required'>*</abbr><spring:message code="exhibition.itemType"/></td>
		<td>
			<input type="text" style="width:400px;" value="${company.companyExhibitType}" name="companyExhibitType" class="formcontrol">
		</td>
	</tr>
	<tr>
		<td><spring:message code="exhibition.shortSummary"/></td>
		<td>
			<textarea name="companyIntroduction" style="width:400px;">${company.companyIntroduction}</textarea>
			<!-- <input type="text" style="width:400px;" value="${company.companyIntroduction}" name="companyIntroduction" class="formcontrol"> -->
		</td>
	</tr>
	<!-- <input id="companyType" name="companyType" class="easyui-combobox" editable="false" value="${user.companyType}" style="width:400px;"
				data-options="valueField:'code',textField:'name',url:'dict/r/company_type',method:'get',required:'true'" /> -->

</table>

<script type="text/javascript">
function changeCompanyNameCn(obj){
	var name = obj.value;
	$("#companyNameCnTd").text(name);
}
//分步验证
function checkTab1(){
	var isValidate = true;
	if($("[name='companyNameCn']").val().length==0 || $("[name='companyNameCn']").val().length > 500){
		alert("请正确填写公司中文名称");
		isValidate = false;
	}
	if($("[name='companyNameEn']").val().length > 500){
		alert("请正确填写公司英文名称，不要超过1000字");
		isValidate = false;
	}
	if($("[name='companyAddress']").val().length==0 || $("[name='companyAddress']").val().length > 500){
		alert("请正确填写地址，不要超过500字");
		isValidate = false;
	}
	if($("[name='companyEmail']").val().length==0 || $("[name='companyEmail']").val().length > 100){
		alert("请填写公司邮箱");
		isValidate = false;
	}
	if($("[name='companyExhibitType']").val().length==0 || $("[name='companyExhibitType']").val().length > 500){
		alert("请填写展品类型");
		isValidate = false;
	}
	if($("[name='companyTel']").val().length==0 || $("[name='companyTel']").val().length > 500){
		alert("请填写公司电话");
		isValidate = false;
	}
	if($("[name='companyFax']").val().length > 500){
		alert("请正确填写公司传真");
		isValidate = false;
	}
	if($("[name='companyWebsite']").val().length > 500){
		alert("公司网址太长了");
		isValidate = false;
	}
	
	if($("[name='companyIntroduction']").val().length==0 || $("[name='companyIntroduction']").val().length > 120){
		alert("请填写公司简介必填，最多100字");
		isValidate = false;
	}
	return isValidate;
}
</script>