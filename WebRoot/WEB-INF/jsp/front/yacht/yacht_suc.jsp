<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="top.jsp"/>
<html>
<head>
</head>
<style>
b{color:#BFA278;}
</style>
<body>
<table align="center" style="margin:0 auto;width:1024px;border:1px solid #F7F6F4;padding:40px;">
<tr><td>
<B><spring:message code="yacht.success.tip"/></B></td></tr>
<tr><td>
   <spring:message code="yacht.success.title"/>${map.user.cname} ：</td></tr>
<tr><td>
   <spring:message code="yacht.exhibition.tip1"/><br>
   <spring:message code="yacht.exhibition.tip2" /> 
  </td></tr>
 <tr ><td style="border-bottom:1px dashed #000;padding-bottom:20px;"><spring:message code="yacht.vericode"/>
  ${map.user.veriCode}</td></tr>
  <tr><td>
<B><spring:message code="yacht.register.info"/></B></td></tr>
<tr><td><spring:message code="yacht.register.time"/>${map.user.createTime}</td></tr>
 <tr><td><spring:message code="yacht.register.email"/> ${map.user.email} </td></tr>
<tr><td>
<!--    用户名：<font color=red> ${map.user.cname}</font>    -->
   <spring:message code="yacht.register.password"/><font color=red> ${map.user.password}</font> </td></tr>
   <tr><td style="padding-left:30px;"><font size="-6" color=red>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(*<spring:message code="yacht.register.tip"/>)</font></td></tr>
 <tr><td style="border-bottom:1px dashed #000;padding-bottom:20px;">
<spring:message code="yacht.register.mobile"/>${map.user.mobile}</td></tr>
<tr><td>
 <B><spring:message code="yacht.exhibition.name"/></B></td></tr>
 <tr><td>
<spring:message code="yacht.exhibition.title"/></td></tr>
 <tr><td>
 <B><spring:message code="yacht.exhibition.date"/></B></td></tr>
 <tr><td><spring:message code="yacht.exhibition.date.value"/>   </td></tr>
 <tr><td><B><spring:message code="yacht.exhibition.time"/></B></td></tr>
 <tr><td>
 <spring:message code="yacht.exhibition.time.value"/></td></tr>
<tr><td> <B><spring:message code="yacht.exhibition.address"/></B></td></tr>
<tr><td>
<spring:message code="yacht.exhibition.address.value"/></td></tr>
 </table></body>
</html>
<jsp:include page="bottom.jsp" />