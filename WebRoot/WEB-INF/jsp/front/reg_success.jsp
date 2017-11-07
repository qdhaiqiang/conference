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

<%--<title>注册成功</title>--%>
<title><spring:message code="titleInfo.meetingTitle" /></title>
<style>
body{
	background:#F7F6F4;
	margin:0px;
}
.container{
	width:866px;
	padding-left:24px;
	padding-top:6px;
	margin:0 auto;
	background-image: url(images/content_tile.jpg);
}

p{
	font-family: bold 12px Times, serif;
	line-height: 26px;
	font-size: 14px;
	color: #666666;
	text-indent:2em;	
}

.pcon{
	width:93%;
	margin-left:24px;
}
.sel_reg a{
	text-decoration:none;
	cursor:none;
}

.btn{display:inline-block;padding:12px 15px;margin-bottom:0;font-size:15px;font-family:"宋体";font-weight:bold;line-height:1.42857143;text-align:center;white-space:nowrap;vertical-align:middle;-webkit-user-select:none;-moz-user-select:none;-ms-user-select:none;user-select:none;background-image:none;border:1px solid transparent;border-radius:4px}
.btn-info{color:#fff;background-color:#5bc0de;border-color:#46b8da}
#footer{
	padding-top: 0px;
	text-align: center;
	font-size: 12px;
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
.sel_reg{
	text-align:center;
	padding:100px 0 100px 0;
}

.reglog a{
	font-size:13px;
	color:#417394;
}
.reglog a:hover{
	color:#F9970B;
	background:none;
}
b{
	color:#F9970B;
	font-size:17px;
	font-weight:bold;
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
color: #CCCBCB;
border-right:1 solid #CCCBCB;
}

#top_1_0 a:hover {
color: #901E17;
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
.reg_su_note{
	margin:0 auto;
	width:610px;
	padding:20px;
}

</style>
</head>

<body>
	<div id="top_1_0">
		<div class="clearfix">
			<ul class="top_1_ul" style="margin-left:10px;float:right;margin-right:180px;">
				<li>
					<a>English&nbsp;&nbsp;&nbsp;&nbsp;</a>
				</li>
				<li>
					<a style="border-right:none;">中文</a>
				</li>
			</ul>
		</div>
	</div>
	<div class="container">
		<img src="images/meeting_banner.jpg" style="margin-top:-9px;"/>
		<div style="background:#F4F2E5;width:842;padding:5px 0 10px 0;">
		<div class="sel_reg" >
			<div class="btn btn-info"><spring:message code="regSuccess.success"/></div>
			<br><br>
			<div class="reg_su_note">
			<p>
			<spring:message code="regSuccess.info1"/></p>
			<p><spring:message code="regSuccess.info2"/></p>
			<p><spring:message code="regSuccess.info3"/><font style="color:#F9970B;"><spring:message code="regSuccess.info4"/></font><spring:message code="regSuccess.info5"/>
			</p>
			</div>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
		</div>
	</div>

</body>
</html>
