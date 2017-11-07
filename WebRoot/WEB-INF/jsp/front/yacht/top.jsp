<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="yacht.macau.show"/></title>
<style type="text/css">
<!--
body,td,th {
	font-family: YT;
}
-->
</style>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/style.css" media="screen">
</head>

<body> 

<table width="1024" border="0" align="center" cellpadding="0" cellspacing="0" style="margin: 0 auto;">
  <tbody><tr>
    <td><img src="<%=basePath %>images/top1.jpg" border="0"></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0" >
      <tbody><tr>
        <td><img src="<%=basePath %>images/menu0.jpg" border="0"></td>
        <td width="894" style="background:url(<%=basePath %>images/menu_bg.jpg) no-repeat; height:100px;">
        <div class="ymenu">
    		<ul>
            	<li class="ll"><a href="http://www.mcyachtshow.com/main.htm#" class="li1">首頁</a></li>
                <li><a href="http://www.mcyachtshow.com/about.htm" class="li1">了解展會</a></li>
                <li><a class="li1">參展商專區
                    <!--[if IE 7]><!--></a><!--<![endif]-->
                    <!--[if lte IE 6]><table><tr><td><![endif]-->
                    <ul>
                        <li><a href="http://www.mcyachtshow.com/exhibitors.htm">本屆參展商</a></li>
                        <li><a href="http://www.mcyachtshow.com/howtoexhibit.htm">如何參展</a></li>
                    </ul>
                    <!--[if lte IE 6]></td></tr></table></a><![endif]-->
                </li>
                <li><a class="li1">觀眾專區
                    <!--[if IE 7]><!--></a><!--<![endif]-->
                    <!--[if lte IE 6]><table><tr><td><![endif]-->
                    <ul>
                        <li><a href="http://www.mcyachtshow.com/login.htm">注冊</a></li>
                        <li><a href="http://www.mcyachtshow.com/getinvitation.htm">如何獲得邀請</a></li>
                    </ul>
                    <!--[if lte IE 6]></td></tr></table></a><![endif]-->
                </li>
                <li><a class="li1">媒體專區
                    <!--[if IE 7]><!--></a><!--<![endif]-->
                    <!--[if lte IE 6]><table><tr><td><![endif]-->
                    <ul>
                        <li><a href="http://www.mcyachtshow.com/mediapartner.htm">媒體伙伴</a></li>
                        <li><a href="http://www.mcyachtshow.com/news.htm">新聞資訊</a></li>
                    </ul>
                    <!--[if lte IE 6]></td></tr></table></a><![endif]-->
                </li>
                <li><a href="http://www.mcyachtshow.com/cooperrativepartner.htm" class="li1">合作伙伴</a></li>
                <li><a href="http://www.mcyachtshow.com/contact.htm" class="li1">聯絡方式</a></li>
            </ul>
        </div></td>
      </tr>
    </tbody></table>
    </td>
  </tr></tbody></table></body></html>