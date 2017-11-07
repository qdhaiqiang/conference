<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<input type="hidden" value="${company.companyId }" name="companyId">
<table class="swtable">
	<tr>
		<td><spring:message code="basicInfo.companyName" /></td>
		<td style="width:400px;" id="companyNameCnTd">
			${company.companyNameCn}
		</td>
	</tr>
	<tr>
		<td><abbr class='requiredtitle' title='必填required'>*</abbr><spring:message code="exhibition.contact"/></td>
		<td>
			<input type="text" style="width:400px;" value="${company.contactPerson}" name="contactPerson" class="formcontrol" onchange="changeContactPerson(this)">
		</td>
	</tr>
	<tr>
		<td><spring:message code="exhibition.title"/></td>
		<td>
			<input type="text" style="width:400px;" value="${company.contactJob}" name="contactJob" class="formcontrol" onchange="changeContactJob(this)">		
		</td>
	</tr>
	<tr>
		<td><abbr class='requiredtitle' title='必填required'>*</abbr><spring:message code="basicInfo.email"/></td>
		<td>
			<input type="text" style="width:400px;" value="${company.contactEmail}" name="contactEmail" class="formcontrol easyui-validatebox" validType="email" required="true">			
		</td>
	</tr>
	<tr>
		<td><abbr class='requiredtitle' title='必填required'>*</abbr>联系电话</td>
		<td>
			<input type="text" style="width:400px;" value="${company.contactTel}" name="contactTel" class="formcontrol">			
		</td>
	</tr>
	<tr>
		<td><spring:message code="basicInfo.faxNo"/></td>
		<td>
			<input type="text" style="width:400px;" value="${company.contactFax}" name="contactFax" class="formcontrol">			
		</td>
	</tr>
</table>	
	
<script type="text/javascript">
	function changeContactPerson(obj){
		var person = obj.value;
		$("#contactPersonTd").text(person);
	}
	function changeContactJob(obj){
		var job = obj.value;
		$("#contactJobTd").text(job);
	}
	//分步验证
	function checkTab0(){
		var isValidate = true;
		if($("[name='contactPerson']").val().length==0 || $("[name='contactPerson']").val().length > 200){
			alert("联系人信息必填");
			isValidate = false;
		}
		if($("[name='contactEmail']").val().length==0 || $("[name='contactEmail']").val().length > 100){
			alert("邮箱不符合规范");
			isValidate = false;
		}
		if($("[name='contactTel']").val().length==0 || $("[name='contactTel']").val().length > 50){
			alert("电话不符合规范");
			isValidate = false;
		}
		
		return isValidate;
	}
</script>