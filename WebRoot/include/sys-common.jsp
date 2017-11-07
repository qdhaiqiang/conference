<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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

    <title>南光会议会展管理及智能营销系统</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>style/css.admin.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>public/ui/themes/default/easyui.admin.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>public/ui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>public/ui/demo/demo.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>public/ui/jquery-ui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/plugins/formbuilder/vendor.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/plugins/formbuilder/formbuilder.css">
		
	<script type="text/javascript" src="<%=basePath%>js/plugins/formbuilder/vendor.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/plugins/formbuilder/formbuilder.js"></script>
	<script type="text/javascript" src="<%=basePath%>public/ui/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>public/layer/layer.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>public/ui/jquery.easyui.min.js"></script>
	<script src="<%=basePath%>js/jquery.databox.ex.js"></script>
	<script type="text/javascript" src="<%=basePath%>public/ui/plugins/jquery.validate.ex.js"></script>
	<script type="text/javascript" src="<%=basePath%>public/ui/plugins/jquery.datetimebox.ex.js"></script>
	<script type="text/javascript" src="<%=basePath%>public/ui/datagrid-detailview.js"></script>
	<script type="text/javascript" src="<%=basePath%>public/ui/jquery.easyui.plus.js"></script>
	<script type="text/javascript" src="<%=basePath%>public/ui/locale/easyui-lang-zh_CN.js"></script>
	   
	<script type="text/javascript" charset="utf-8" src="<%=basePath%>public/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=basePath%>public/ueditor/ueditor.all.min.js"> </script> 
    <script type="text/javascript" charset="utf-8" src="<%=basePath%>public/ueditor/lang/zh-cn/zh-cn.js"></script>
      
	
	<link rel="icon" href="<%=basePath%>favicon.ico" type="image/x-icon" />
	<link rel="shortcut icon" href="<%=basePath%>favicon.ico" type="image/x-icon" />
  </head>
</html>

<!-- 在线用户分析 -->
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-54750593-1', 'auto');
  ga('send', 'pageview');
</script>
