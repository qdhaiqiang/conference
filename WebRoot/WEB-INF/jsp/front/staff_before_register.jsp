<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%--<title>角色选择</title>--%>
<title><spring:message code="titleInfo.meetingTitle" /></title>
</head>
<body style="width:70%;">
	<img alt="" src="<%=basePath%>images/logo.png">
	<div id="userTypeDiv">
		请选择用户类型
		<select id="roleselect" onchange="selectRole()">
		</select>
	</div>

</body>

<!-- jQuery Version 1.11.0 -->
<script src="<%=basePath%>js/jquery-1.11.0.js"></script>
<script type="text/javascript">
var roleCode;//同意授权书或者其他方式跳转注册页面之前，记录所选角色code, 传值request，方便之后注册信息填写
$(function(){
	$("#authorDiv").hide();
	loadRoles();
});

function loadRoles(){
	$.ajax({
		url: "<%=basePath%>dict/r/user_type",
		type : "GET",
		success : function(data) {
			setRoles(data);
		}
	});
}

function setRoles(data){
	var roleselectHtml = "<option value='00' selected>请选择</option>"; 
	for(var i in data){
		var element = data[i];
		if(element.code == "7" || element.code == "8" || element.code == "9"){
		
		roleselectHtml += "<option value='";
	    roleselectHtml += element.code;
	    roleselectHtml += "'>";
	    roleselectHtml += element.name;
	    roleselectHtml += "</option>";
		}
	}
	$("#roleselect").html(roleselectHtml);
}

function selectRole(){
	var value = $("#roleselect").val();
	roleCode = value;
	if(value != "00"){
			gotoRegister();
	}
}

function gotoRegister(){
	window.location.href="<%=basePath%>front/reg/emailCheck/"+roleCode;
}
</script>


</html>