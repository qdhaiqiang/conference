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
    <script src="<%=basePath %>js/marquee.js" type="text/javascript"></script>
<!--     <script type="text/javascript"> -->
<!--         SyntaxHighlighter.all(); -->
<!--     </script> -->
  </head>
  
  <body>
    <div class="wrapbox">
        <div class="tl">
           <img src='<%=basePath%>images/reg_banner.jpg' style="width:1024px;" />
        </div>
        <div id="content">
          <div id="content_inner">
          <h2 class="foruser">演讲嘉宾(Speaker)</h2>
            <ul id="slides1">
				<c:forEach var="user" items="${list }" varStatus="status">
                <li> 
                	<div class="imgbox">
	                	<img src="${user.photo }"/>
	                	<h3>${user.cname }</h3>
	                	<h5>(${user.nation })</h5>
               		</div>
	                <div class="content" id="odiv${status.index }">                
	                  <h4>${user.selfIntro }</h4>
	                  <p>${user.selfIntroEn }</p>
	                </div>
	                <div class="clear"></div>
	            </li>
	            </c:forEach>
            </ul>
          </div>
        </div>
    </div>
    <script language="javascript">
        var length = ${list.size() };
    	for (var i=0; i<length;i++) {
    		var mar = new Marquee("odiv"+i);
    		mar.width = 590; mar.height = 420;
	 		mar.step = 3;mar.interval = 200;
	 		mar.MoveUp();
    	}
    </script>
  </body>
</html>