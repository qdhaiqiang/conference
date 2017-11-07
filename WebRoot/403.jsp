<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>找不到目标网页</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<style >
h1,h2 {
    color: inherit;
    font-family: inherit;
    font-weight: 500;
    line-height: 1.1;
}
.container {
    margin: 0 auto;
    max-width: 1200px;
    position: relative;
}
.f404 {
    margin: 2px 0;
    text-align: center;
}
img {
    vertical-align: middle;
    border: 0 none;
}
</style>
</head>
<body>
	<section class="container">
	<div class="f404">
		<img src="<%=basePath%>images/meeting_banner.jpg">
		<DIV id=container>
			<br />
			<br />
			<h2 style="text-align:center">403</h2>
			<hr />
			<h4 style="text-align:center">请求被禁止，没有访问权限。</h4>
			<h4 style="text-align:center">请不要尝试渗透攻击本系统。</h4>
		</DIV>
	</div>
	</section>
</body>
</html>
