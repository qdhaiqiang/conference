<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	<title><spring:message code="titleInfo.meetingTitle" /></title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<link rel="stylesheet" type="text/css" href="<%=basePath%>public/ui/themes/default/easyui.css">
    <!-- Bootstrap Core CSS -->
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery Version 1.11.0 -->
    <script src="<%=basePath%>js/jquery-1.11.0.js"></script>

	<script src="<%=basePath%>js/jquery.databox.ex.js"></script>
	
    <!-- Bootstrap Core JavaScript -->
    <script src="<%=basePath%>js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/jquery.cookie.js"></script>

	<link rel="icon" href="<%=basePath%>favicon.ico" type="image/x-icon" />
	<link rel="shortcut icon" href="<%=basePath%>favicon.ico" type="image/x-icon" />
  </head>
</html>
