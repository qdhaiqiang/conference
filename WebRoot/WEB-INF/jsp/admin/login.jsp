<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../../../include/sys-common.jsp"></jsp:include>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <!--[if lt IE 9]>
   		<script src="easyui/plugin/login/js/html5.js"></script>
  	<![endif]-->
  	<!--[if lt IE 7]>
  		<script src="easyui/plugin/login/js/iepng.js" type="text/javascript"></script>
  		<script type="text/javascript">
			EvPNG.fix('div, ul, img, li, input'); //EvPNG.fix('包含透明PNG图片的标签'); 多个标签之间用英文逗号隔开。
		</script>
  	<![endif]-->
  	<link href="easyui/plugin/login/css/zice.style.css" rel="stylesheet" type="text/css" />
  	<link href="easyui/plugin/login/css/buttons.css" rel="stylesheet" type="text/css" />
  	<link href="easyui/plugin/login/css/icon.css" rel="stylesheet" type="text/css" />
  	<link rel="stylesheet" type="text/css" href="easyui/plugin/login/css/tipsy.css" media="all" />
  	<style type="text/css">
		html {
			background-image: none;
		}

		label.iPhoneCheckLabelOn span {
			padding-left: 0px
		}

		#versionBar {
			background-color: #212121;
			position: fixed;
			width: 100%;
			height: 35px;
			bottom: 0;
			left: 0;
			text-align: center;
			line-height: 35px;
			z-index: 11;
			-webk	it-box-shadow: black 0px 10px 10px -10px inset;
			-moz-box-shadow: black 0px 10px 10px -10px inset;
			box-shadow: black 0px 10px 10px -10px inset;
		}
		
		.copyright {
			text-align: center;
			font-size: 10px;
			color: #CCC;
		}
		
		.copyright a {
			color: #A31F1A;
			text-decoration: none
		}
		
		#login .logo {
			width: 500px;
			height: 51px;
		}
		</style>
  </head>
  
<body>
	<div id="alertMessage"></div>
	  <div id="successLogin"></div>
	  <div class="text_success">
	   <img src="easyui/plugin/login/images/loader_green.gif" alt="Please wait" />
	   <span>登陆成功!请稍后....</span>
	  </div>
	  <div id="login">
	   <div class="ribbon" style="background-image:url(easyui/plugin/login/images/typelogin.png);"></div>
	   <div class="inner">
	    <div class="logo">
	     <img src="easyui/plugin/login/images/toplogo-jeecg.png"/>
	    </div>
	    <div class="formLogin">
	     <form name="formLogin" id="formLogin" action="<%=basePath %>r/admin/main" check="<%=basePath %>confSysUser/checkUser" method="post">
	     <div class="tip">
	       <input class="userName" name='loginName' type="text" id="loginName" title="用户名" iscookie="true" nullmsg="请输入用户名!"/>
	      </div>
	      <div class="tip">
	       <input class="password" type="password" name='loginPassword' id="loginPassword" title="密码" nullmsg="请输入密码!"/>
	      </div>
	      <div class="loginButton">
	       
	       <div style="float: right; padding: 3px 0; margin-right: -12px;">
	        <div>
	         <ul class="uibutton-group">
	          <li>
	           <a class="uibutton normal" id="btn_login">登录</a>
	          </li>
	         </ul>
	        </div>
	       </div>
	       <div class="clear"></div>
	      </div>
	     </form>
	    </div>
	   </div>
	   <div class="shadow"></div>
	  </div>
	  <!--Login div-->
	  <div class="clear"></div>
	  <div id="versionBar">
	   <div class="copyright">
	    &copy; 版权所有
	   </div>
	  </div>
	    <!-- Link JScript-->
	  <script type="text/javascript" src="js/jquery.cookie.js"></script>
	  <script type="text/javascript" src="easyui/plugin/login/js/jquery-jrumble.js"></script>
	  <script type="text/javascript" src="easyui/plugin/login/js/jquery.tipsy.js"></script>
	  <script type="text/javascript" src="easyui/plugin/login/js/iphone.check.js"></script>
	  <script type="text/javascript" src="easyui/plugin/login/js/login.js"></script>
</body>
</html>
