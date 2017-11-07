<%@ page language="java" import="java.util.*,javax.servlet.*,javax.servlet.http.*,org.springframework.web.servlet.support.RequestContextUtils" pageEncoding="UTF-8"%>
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
<style>
body{
	background:#F7F6F4;
	margin:0px;
}
.container{
	width:866px;
	padding-left:25px;
	padding-top:6px;
	margin:0 auto;
	background-image: url(images/content_tile.jpg);
}

p{
	font-family: bold 12px Times, serif;
	line-height: 26px;
	font-size: 13px;
	color: #666666;
	text-indent:2em;	
}

.pcon{
	width:93%;
	margin-left:24px;
	min-height:280px;
}
.sel_reg a{
	text-decoration:none;
}
.sel_reg a:hover{
	border-color:#F9970B;
	background:#F9970B;
}
.btn{display:inline-block;padding:12px 15px;margin-bottom:0;font-size:15px;font-family:"宋体";font-weight:bold;line-height:1.42857143;text-align:center;white-space:nowrap;vertical-align:middle;cursor:pointer;-webkit-user-select:none;-moz-user-select:none;-ms-user-select:none;user-select:none;background-image:none;border:1px solid transparent;border-radius:4px}
.btn-info{color:#fff;background-color:#5bc0de;border-color:#46b8da}
.sel_reg{
	text-align:center;
	padding:30px 0 0 90px;
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

</style>
</head>

<body>
	<div class="container">
		<img src="images/meeting_banner.jpg" style="margin-top:-9px;"/>
		<div style="background:#F4F2E5;width:842px;padding:5px 0 10px 0;">
			<div class="pcon"> 
			<br><br>
				<div align="center"><font style="color:#891300;">${confArticle.title }</font></div>
				<br><br>
				${confArticle.context }
			</div>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>