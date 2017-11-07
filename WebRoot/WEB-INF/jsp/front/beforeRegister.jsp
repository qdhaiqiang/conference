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
<title><spring:message code="titleInfo.meetingTitle" /></title>
</head>
<body style="width:70%;">
	<img alt="" src="<%=basePath%>images/logo.png">
	<div id="userTypeDiv">
		请选择用户类型
		<select id="roleselect" onchange="selectRole()">
		</select>
	</div>
	<div id="authorDiv">
		<div id="authorTop">
			<img alt="" src="<%=basePath%>images/logo.png" style="float:right;">
			<label id="authorityTitle"></label>
			<p id="authorityContent"></p>
		</div>
		<!-- 同意与否的按钮 -->
		<div id="authorButtons">
			<button id="ok" onclick="gotoRegister()">同意</button>
			<button id="cancle">不同意</button>
		</div>
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
		if(element.code == "1" || element.code == "7" || element.code == "8" || element.code == "9"){
			continue;
		}
		roleselectHtml += "<option value='";
	    roleselectHtml += element.code;
	    roleselectHtml += "'>";
	    roleselectHtml += element.name;
	    roleselectHtml += "</option>";
	}
	$("#roleselect").html(roleselectHtml);
}

function selectRole(){
	var value = $("#roleselect").val();
	roleCode = value;
	if(value != "00"){
		if(value == "2"){
		$("#authorDiv").show();
			//演讲嘉宾，填写授权书，同意之后跳转到注册页面
			$("#authorityTitle").html("文章授权书");
			$("#authorityContent").html("今授权太湖世界文化论坛秘书处出版发行授权人提交的论文/在会上发表的演讲。授权人保证拥有所授予权利，上述权利的行使没有侵犯他人著作权或侵犯他人名誉权、肖像权、姓名权等其他权利。");
		}else{
			//其他不需要填写授权书的角色，直接跳转到注册页面
			gotoRegister();
		}
	}
}

function gotoRegister(){
	window.location.href="<%=basePath%>front/reg/emailCheck/"+roleCode;
}
</script>


</html>