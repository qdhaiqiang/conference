<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'schedule.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/lunbo.css">

	<script src="<%=basePath%>js/js/jquery-latest.js" type="text/javascript"></script>
	<script src="<%=basePath%>js/js/shBrushXml.js" type="text/javascript"></script>
	<script src="<%=basePath%>js/js/jquery.bxslider2.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>js/js/scripts.js" type="text/javascript"></script>
	<script type="text/javascript">
		SyntaxHighlighter.all();
	</script>
  </head>
  
  <body>
  	<div class="wrapbox">
		<div class="tl">
			<img src='<%=basePath%>images/meeting_q.jpg' width="800" />
		</div>
		<div id="content">
		  <div id="content_inner">
		  <h2 class="foruser">嘉宾详细信息</h2>
		    <ul id="slides1">
		      <li> <img src="<%=basePath%>images/female.png" width="200" />
		        <div class="content">
		          <h3>张三丰</h3>
		          <h4>青岛市常务副理事长</h4>
		        </div>
		        <div class="clear"></div>
		      </li>
		      <li> <img src="<%=basePath%>images/male.png" width="200" />
		        <div class="content">
		          <h3>李四</h3>
		          <h4>青岛市常务副理事长</h4>
		        </div>
		        <div class="clear"></div>
		      </li>
		    </ul>
		  </div>
		</div>
		<div class="huiyiright">
			<h2>观众详细信息</h2>		
         	  	<ul>
					<li>张三丰 zhangsanfeng 青岛市常务副理事长 </li>
					<li>张三丰 zhangsanfeng 青岛市常务副理事长 </li>
					<li>张三丰 zhangsanfeng 青岛市常务副理事长 </li>
					<li>张三丰 zhangsanfeng 青岛市常务副理事长 </li>
					<li>张三丰 zhangsanfeng 青岛市常务副理事长 </li>
					<li>张三丰 zhangsanfeng 青岛市常务副理事长 </li>
					<li>张三丰 zhangsanfeng 青岛市常务副理事长 </li>
					<li>张三丰 zhangsanfeng 青岛市常务副理事长 </li>
				</ul>
		</div>
	</div>
  </body>
</html>
