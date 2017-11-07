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
<meta Name="viewport" content="width=device-width, initial-scale=1">
<meta Name="description" content="">
<meta Name="author" content="">
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


.pcon{
	width:93%;
	margin-left:24px;
}

#footer{
	/*padding-top: 100px;*/
	text-align: center;
	font-size: 12px;
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
.contact_info{
	text-align:left;
	padding:50px 0 20px 50px;
	display:inline-block;
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


.container ul{
list-style-type: disc;
margin-left:10px;
float:left;
margin-right:20px;
font-size:13px;
margin-top:20px;
}

.container li {
line-height: 22px;
list-style: none;
}

h3 {
margin: 0 0 7px -12px;
padding: 7px 0 7px 12px;
font: bold 12px Times, serif;
color: #891300;
border-top: 0;
border-bottom: 1px dotted #dddad9;
font-size:14px;
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

.contact_info div{
	width:350px;
	float:left;
}

.role{
	font-size:15px;
	border-bottom: 1px dotted #dddad9;
}
</style>
</head>

<body>
	<div id="top_1_0">
		<div class="clearfix">
			<ul class="top_1_ul" style="margin-left:10px;float:right;margin-right:180px;">				
			</ul>
		</div>
	</div>
	<div class="container">
		<img src="images/meeting_banner.jpg" style="margin-top:-9px;"/>
		<div style="background:#F4F2E5;width:842;padding:5px 0 10px 0;">
		<div class="contact_info" >
			<h3>联系人信息(Contact US)</h3>
			<div>
				<ul>
					<li><h3>嘉宾/Guest</h3></li>
					<li>姓名(Name)        ：陈柯安(Kan)</li>
					<li>手机(Mobile phone)：15344850787</li>
					<li>传真(Fax)         ：00853-28716550</li>
					<li>邮箱(Email)       ：wfc@namkwong.com.mo</li>
					<li>座机(Fixed phone) ：00853-83911352</li>
				</ul>
			</div>
			<div>
				<ul>
					<li><h3>媒体/Media</h3></li>
					<li>姓名(Name)        ：李丽荣(Echo)</li>
					<li>手机(Mobile phone)：15344866767</li>
					<li>传真(Fax)         ：00853-28716550</li>
					<li>邮箱(Email)       ：1847751458@qq.com</li>
					<li>座机(Fixed phone) ：00853-83911355</li>
				</ul>
			</div>
			<div>
				<ul>
					<li><h3>参展商/Exhibitor</h3></li>
					<li>姓名(Name) ：朱祉华(Allen)</li>
					<li>手机(Mobile phone)：15338154985</li>
					<li>传真(Fax) ：00853-28716550</li>
					<li>邮箱(Email) ：zhuzhihua@namkwong.com.mo</li>
					<li>座机(Fixed phone) ：00853-83911354</li>
				</ul>
			</div>
			<div>
				<ul>
					<li><h3>观众/Audience</h3></li>
					<li>姓名(Name) ：华钰萍(Rachel)</li>
					<li>手机(Mobile phone)：15363733223</li>
					<li>传真(Fax) ; ：00853-28716553</li>
					<li>邮箱(Email)       ：huayuping@namkwong.com.mo</li>
					<li>座机(Fixed phone) ：00853-83911352</li>
				</ul>
			</div>
			<div>
				<ul>
					<li><h3>技术支持/Support</h3></li>
					<li>邮箱(Email) ：support@namkwong.org</li>
					<li>电话(Telephone) ：(0086)400-621-6886</li>
				</ul>
			</div>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
		</div>
	</div>

</body>
</html>
