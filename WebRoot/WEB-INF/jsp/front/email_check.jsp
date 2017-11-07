<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
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



<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title><spring:message code="titleInfo.meetingTitle" /></title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>public/ui/themes/default/easyui.css">
<script src="<%=basePath%>js/jquery-1.11.0.js"></script>
<script src="<%=basePath%>js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=basePath%>public/ui/jquery.easyui.min.js"></script>
<link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
<script src="<%=basePath%>js/message_cn.js" charset="gbk"
		type="text/javascript"></script>
<style>
.label-inverse,.badge-inverse {
	background-color: #333;
}

.label {
	background-color: #999;
}


.panel-heading {
	color: #333;
	padding: 10px 15px;
	border-top-left-radius: 3px;
	border-top-right-radius: 3px;
	margin-top:1px;
}

.panel-title {
	margin-top: 0;
	margin-bottom: 0;
	font-size: 14px;
	color:#891300;;
	font-weight:bold;
}

.panel-body {
	padding: 15px;
	background:none;
	border:none;
}


.form-control {
	width: 100%;
	height: 34px;
	padding: 6px 12px;
	font-size: 14px;
	color: #555;
	border: 1px solid #ccc;
	border-radius: 4px;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	-webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow
		ease-in-out .15s;
	-o-transition: border-color ease-in-out .15s, box-shadow ease-in-out
		.15s;
	transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
}

button {
	color: #fff;
	background-color: #C0A17B;
	display: inline-block;
	padding: 6px 12px;
	margin: 10px 0;
	font-size: 14px;
	font-weight: 400;
	text-align: center;
	vertical-align: middle;
	cursor: pointer;
	border: none;
	font-size: 18px;
	line-height: 1.33;
	border-radius: 6px;
	width: 100%;
}

label{
	margin: 0 0 7px -12px;
	padding: 7px 0 7px 12px;
	font: bold 12px Times, serif;
	color: #891300;
	border-top: 0;
	border-bottom: 1px dotted #dddad9;
}

.col-md-offset-4{
	margin-left:53%;
	margin-top:60px;
	padding-top:50px;
	padding-left:40px;
}

.row{
	margin-top:20px;
	margin:0 auto;
}

.col-md-offset-4 {
	margin-left: 50%;
	margin-top: 0px;
	padding-top: 35px;
}
.user_info{
	width:390px;
	float:right;
	text-align: left;
	margin-left: 78px;
	margin-right:50px;
}

#emailVerify{
	width: 300px;
	height: 314px;
	float: right;
	background: url(images/login_v2.png) no-repeat;
	margin-top: 25px;
	margin-right: 100px;
	margin-bottom:46px;
}
.type_info {
	width: 1140px;
	background: #F4F2E5;
	text-align: left;
	margin: 0px 20px 0px 0px;
	padding-bottom:60px;
	padding-left:100px;
}
body{
	background:#F7F6F4;
}
#userTypeDiv{
	margin-top:10px;
}
p{
	text-indent:2em;
	font-size:13px;
}
#top_1_0 {
background: url(images/home_03.png) repeat-x;
height: 30px;
min-width: 1000px;
position: relative;
font-size:13px;
}

#top_1_0 a {
text-decoration: none;
color: #5D0D08;
border-right:1 solid #CCCBCB;
}

#top_1_0 a:hover {
color: #F9970B;
}

ul{
display: block;
list-style-type: disc;
margin-left:180px;
float:left;
margin-top:0px;
}

.top_1_ul li {
float: left;
margin-right: 10px;
line-height: 30px;
}
li {
list-style: none;
}
#footer{
	margin-top: 0px;
	padding-top:20px;
	text-align: center;
	font-size: 12px;
	background:#F4F2E5;
	width:1140px;
	margin:0 auto;
	border-top:1px dashed #CDCBCB;
}

#footer a{
	font-size: 12px;
	color:#999;
	text-decoration:none;
}
#footer a:hover{
	color:#F9970B;
}
#footer p{
	font-size: 12px;
	color:#999;
	line-height: 12px;
}
.userinfo_left{
	float:left;
	width:500px;
}
h4 {
	margin: 0 0 7px -12px;
	padding: 7px 0 7px 12px;
	font: bold 12px Times, serif;
	color: #891300;
	border-top: 0;
	border-bottom: 1px dotted #dddad9;
	font-size:13px;
}

h5 {
	margin: 0 0 7px -12px;
	padding: 7px 0 7px 12px;
	font: bold 12px Times, serif;
	color: #383838;
	border-top: 0;
	border-bottom: 1px dotted #5F5D91;
	font-size:13px;
}

.userinfo_left span{
	font-family:Verdana, Arial, Helvetica, sans-serif;
	font-size:13px;
}

.userinfo_left a {
	color:#383838;
	text-decoration:underline;
}


ol {
	font: italic 11px Verdana, Arial, Helvetica, sans-serif;
	padding: 0 0;
	margin-left: 10px;
}
li.prompt{
	margin-left: 10px;
	margin-bottom: 5px;
	padding-left: 10px;
	list-style-type: decimal;
}

li.language{
	margin-left: 10px;
	margin-bottom: 5px;
	padding-left: 10px;
	list-style-type: none;
}

.emailVerify {
	width: 320px;
	height: 314px;
	float: right;
	background: #F8F8F6;
	margin-top: 15px;
	margin-right: 100px;
	margin-bottom:20px;
}
</style>

</head>


<body>
	<div id="authorDiv" class="easyui-dialog" data-options="iconCls:'icon-save',modal:true" style="width:390px;top:160px">
		<div id="authorTop">
			<label id="authorityTitle"><spring:message code='basicInfo.authorization'/></label>
				<p id="authorityContent" style="width:360px;"><spring:message code='basicInfo.authorizationCont'/></p>
			</div>
			<!-- 同意与否的按钮 -->
			<div id="authorButtons">
				<button id="ok" onclick="gotoRegister()" style="width: 110px;margin-right: 20px;"><spring:message code="basicInfo.approve"/></button>
				<button id="cancle" style="width: 110px;" onclick="gotoIndex()"><spring:message code="basicInfo.disApprove"/></button>
			</div>
			<p style="text-indent:0px;color:#891300;">
				<spring:message code="basicInfo.protocolDes"/>
			</p>
	</div>
	<div id="top_1_0">
		<div class="clearfix">
			<ul class="top_1_ul" style="margin-left:10px;float:right;margin-right:180px;">
				<li class="language">
					<a href="front/reg/emailCheck/${type}?locale=en_US">English&nbsp;&nbsp;</a>
				</li>
				<li class="language">
					<a href="front/reg/emailCheck/${type}?locale=zh_CN" style="border-right:none;">中文</a>
				</li>
			</ul>
		</div>
	</div>
	<div class="container row" >
		<img src="images/reg_banner.jpg" style="margin-top:-12px;"/>
		<div class="col-md-4 col-md-offset-4 type_info">
		<h4 style="font-size:15px;"><spring:message code="emailCheck.introTitle"/></h4>
		<div class="userinfo_left">
			<h4><spring:message code="emailCheck.intro1"/></h4>
			<span><spring:message code="emailCheck.intro2"/></span>
			<br><br>
			<h5><spring:message code="emailCheck.intro3"/></h5>
			<ol>
				<li class="prompt"><a href="../../../../article/8ada99c148402244014840421a8d024f" target="_blank"><spring:message code="emailCheck.intro4"/></a></li>
				<li class="prompt"><a href="../../../../article/8ada99c14840224401484042fd9b0254" target="_blank"><spring:message code="emailCheck.intro5"/></a></li>
				<li class="prompt"><a href="../../../../article/8ada99c14840224401484043c5fc025e" target="_blank"><spring:message code="emailCheck.intro6"/></a></li>
				<li class="prompt"><a href="../../../../article/8ada99c148402244014840443709025f" target="_blank"><spring:message code="emailCheck.intro7"/></a></li>
				<li class="prompt"><a href="../../../../article/8ada99c1485f7ce401486002198901ad" target="_blank"><spring:message code="emailCheck.intro14"/></a></li>
				<li class="prompt"><a href="../../../../article/8ada99c1484022440148408ceb1204bd" target="_blank"><spring:message code="emailCheck.intro15"/></a></li>
			</ol>
			<div>
				<span>
					<spring:message code="emailCheck.intro8"/>
				</span>
			</div>
			<br>
			<div>
				<span>
					<spring:message code="emailCheck.intro9"/>
				</span>
			</div>
			<h5>&nbsp;</h5>
			<h5 style="font-weight:normal;"><spring:message code="emailCheck.intro12"/></h5>
			<h5 style="font-weight:normal;"><spring:message code="emailCheck.intro13"/></h5>
		</div>
		<div id="userInfo" class="user_info">
				<div id="userTypeDiv">
						<font style="color:#891300;font:bold 14px Times, serif;">
							<spring:message code="basicInfo.selectUserType"/>&nbsp;&nbsp;
						</font>
						<select id="roleselect" onchange="selectRole()">
						</select>
				</div>
				<div id="emailVerify" >
					<div class="panel-heading" style="border-bottom:none;">
						<h3 class="panel-title"><spring:message code="login.validateEmail"/></h3>
					</div>
					<div class="panel-body">
						<form role="form" method='POST' id="email-form">
								<div class="form-group">
									<spring:message code="basicInfo.email"/>：<input type="text" id="email" name="email" style="margin-top:2px;" class="form-control required email" />
									<button id="sec-code-btn" type="button" disabled="disabled" ><spring:message code="basicInfo.getVerifyCode"/></button>
								</div>
								<spring:message code="basicInfo.verifyCode"/>：<input type="text" class="form-control" id="emailcode">
								<button class="" value="" id="register-btn" type="button" disabled="disabled"><spring:message code="basicInfo.continueReg"/></button>
						</form>
					</div>
				</div>
			<h5 style="font-weight:normal;"><spring:message code="emailCheck.intro10"/></h5>
			<h5 style="font-weight:normal;"><spring:message code="emailCheck.intro11"/></h5>
		</div>
		
       </div>
	</div>
	 
	<div style="width:100%;height:1px">&nbsp;</div>
	<jsp:include page="footer.jsp"></jsp:include>
	<script type="text/javascript">
		var roleCode;//同意授权书或者其他方式跳转注册页面之前，记录所选角色code, 传值request，方便之后注册信息填写
	
		function loadRoles(){
			$.ajax({
				url: "<%=basePath%>dict/r/getDict",
				data : {
					categoryCode : "${categoryCode}",
					type : "${type}"
				},
				type : "GET",
				success : function(data) {
					setRoles(data);
				}
			});
		}
		
		function setRoles(data){
			var roleselectHtml = "<option value='00' selected><spring:message code='basicInfo.select'/></option>"; 
			for(var i in data){
				var element = data[i];
				roleselectHtml += "<option value='";
			    roleselectHtml += element.code;
			    roleselectHtml += "'>";
			    roleselectHtml += element.name;
			    roleselectHtml += "</option>";
			}
			$("#roleselect").html(roleselectHtml);
		}
		
		function selectRole(){
			$("#sec-code-btn").attr("disabled",false);
			$("#register-btn").attr("disabled",false);
			$("#register-btn").css("background-color","#C0A17B");
			$("#sec-code-btn").css("background-color","#C0A17B");
			var value = $("#roleselect").val();
			roleCode = value;
			if(value != "00"){
				if(value == "2" || value=="13"){
					disableButton();
					$("#authorDiv").dialog("open").dialog("setTitle","<spring:message code='emailCheck.authorization'/>");
				}else{
					//其他不需要填写授权书的角色，直接跳转到注册页面
					gotoRegister();
				}
			}else{
				disableButton();
			}
		}
		
		function gotoRegister(){
			$("#authorDiv").dialog("close");
			$("#sec-code-btn").attr("disabled",false);
			$("#register-btn").attr("disabled",false);
			$("#register-btn").css("background-color","#C0A17B");
			$("#sec-code-btn").css("background-color","#C0A17B");
		}
		
		function gotoIndex(){
			window.location.href = "<%=basePath%>r/front/meeting_intro" ;
		}
		
		function disableButton(){
			$("#sec-code-btn").attr("disabled","disabled");
			$("#register-btn").attr("disabled","disabled");
			$("#register-btn").css("background-color","#ccc");
			$("#sec-code-btn").css("background-color","#ccc");
		}
		$(document).ready(function() {
			$("#authorDiv").dialog("close");
			disableButton();
			loadRoles();
			
			var form = $("#email-form");
			
			form.validate();

			$("#sec-code-btn").click(function() {
				
				if(!form.valid()){
					alert("<spring:message code='emailCheck.emailIllegal'/>");
					return;
				}
				var email = $("#email").val();
				if (email == "") {
					alert("<spring:message code='basicInfo.inputEmail'/>");
					return;
				}
				$.ajax({
					type : "GET",
					url : "front/reg/securitycode",
					data : {
						email : email,
						roleCode:roleCode
					},
					success : function(result) {
						if (result == "registered") {
							alert("<spring:message code='basicInfo.emailHasRegister'/>");
						} else if (result == "failure") {
							alert("<spring:message code='emailCheck.emailSendFailed'/>");
						} else {
							alert("<spring:message code='emailCheck.emailSend'/>");
						}
					}
				});

			});

			$("#register-btn").click(function() {
				var emailCode = $("#emailcode").val();
				$.ajax({
					type : "GET",
					url : "front/reg/reglogin",
					data : {
						emailCode : emailCode,
						email:$("#email").val()
					},
					success : function(result) {
						if (result == "success") {
							$("#email-form").attr("action", "front/reg/register/" + roleCode);
							$("#email-form").submit();
							
						} else if (result == "registered") {
							alert("<spring:message code='basicInfo.emailHasRegister'/>");
						} else{
							alert("<spring:message code='emailCheck.verifyCodeError'/> ");
						}
					}
				});
			});
		});
		
	</script>

</body>
</html>
