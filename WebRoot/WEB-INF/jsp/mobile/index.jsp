<%@ page language="java" import="java.util.*,
	javax.servlet.*,
	javax.servlet.http.*,
	org.springframework.web.servlet.support.RequestContextUtils,
	com.centling.conference.base.Constant,
	com.centling.conference.user.entity.ConfUser" 
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Locale locale = RequestContextUtils.getLocale(request);
	ConfUser confUser = (ConfUser)request.getSession().getAttribute(Constant.SESSION_CONF_USER);
	String meetingId="";
	// 跳转到登录页面
	if (confUser==null) {
		meetingId = (String)request.getSession().getAttribute(Constant.FRONT_SESSION_MEETING_ID);
	%>
		<script>
			/**layer.alert("您未登录或登录已失效，请重新登录",8,"提示",function () {
				window.location.href="<%=basePath%>front/reg/reLogin_app?meetingId=<%=meetingId%>";
			});
			**/
			window.location.href="<%=basePath%>front/reg/reLogin_app?meetingId=<%=meetingId%>";
		</script>
	<%
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
	<meta name="viewport" content="width = device-width, user-scalable=no minimum-scale=1.0 maximum-scale=1.0" />
	<title><spring:message code="titleInfo.meetingTitle" /></title>
	<link rel="stylesheet" href="<%=basePath%>css/plugins/mobile/jquery.mobile-1.4.4.min.css">
	<link rel="stylesheet" href="<%=basePath%>css/common-mobile.css">
	<link rel="stylesheet" href="<%=basePath%>public/layer/skin/layer.css">
	<style type="text/css">
		a img {
		width: auto;
		height: auto;
		max-width: 49px;
		max-height: 61px;
		}
		.ui-grid-c a:visited {
		color: #182240;
		}
		.ui-grid-c a:link {
		color: #182240;
		}
		.ui-grid-c a:hover {
		color: #182240;
		}
		.ui-grid-c a{
		padding: 0.5em 0 0.5em 0;
		display: block;
		text-align: center;
		text-decoration: none;
		font-size: 0.5em;
		}
		.ui-grid-c div{
		height:100px;
		}
	</style>
	<script src="<%=basePath%>public/mobile/jquery.js"></script>
	<script src="<%=basePath%>public/mobile/jquery.mobile-1.4.4.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>public/layer/layer.min.js"></script>
	<script type="text/javascript">
		var LINE_NUM=4;
		$(document).on("pagecreate","#indexPage",function() {
			loadMenu();
		});
		function loadMenu() {
			$.mobile.loading("show");
			$.ajax({
				url : "<%=basePath%>funcSetting/l",
				type : "get",
				success : function(result) {
					$.mobile.loading("hide");
					var html="";
					$(result).each(function(i, obj) {
						var index=i%LINE_NUM;
						html+=getHtml(index, obj);
						index++;
					});
					$(".ui-grid-c").html(html);
				}
			});
		}
		function getHtml(num,obj){
			var html ="";
			var blockClass="";
			switch(num){
				case 0:blockClass="ui-block-a";break;
				case 1:blockClass="ui-block-b";break;
				case 2:blockClass="ui-block-c";break;
				case 3:blockClass="ui-block-d";break;
			}
			html+="<div class=\""+blockClass+" center\"> ";
			html+="<a  class=\"ui-bar\" data-role=\"none\" data-transition=\"slide\" data-ajax=\"false\" href=\"<%=basePath%>"+obj.refUrl+"\" data-icon=\"custom\"> ";
			if ("en_US"=="<%=locale%>") {//英文
				html+="<img src=\"<%=basePath%>"+obj.icon+"\" ><p>"+obj.funcEname+"</p></a></div> ";
			}else{//中文
				html+="<img src=\"<%=basePath%>"+obj.icon+"\" ><p>"+obj.funcName+"</p></a></div> ";
			}
		    return html;
		}
		
		$(document).ready(function(){ 
	    	$("#btnLogout").bind('click',function(){
	    		layer.confirm('<spring:message code="mobile.confirm.exit" />', function(){
	 			$.ajax({
	                url : '<%=basePath%>front/reg/exit_app',
	                type : "POST",
	                success : function(msg) {
	                    //跳转到登陆页
	                    //jQuery.mobile.changePage("<%=basePath%>r/mobile/login");
	                    window.location.href="<%=basePath%>front/reg/reLogin_app?meetingId=<%=meetingId%>";
	                }
	            });
	 		});
	    	}); 
		});
	</script>
</head>

<body>
	<div data-role="page" id ="indexPage" data-dom-cache="true">
		<!-- 图片 -->
		<jsp:include page="../../../include/banner-mobile.jsp"></jsp:include>
		<div data-role="content">
			<div class="ui-grid-c" style="text-align: center"></div>
			<!-- <div style="width:80px;float:right;"> -->
			  <input id="btnLogout" type="button" value="<spring:message code="mobile.exit" />">
			<!-- </div> -->
		</div>
	</div>
</body>
</html>