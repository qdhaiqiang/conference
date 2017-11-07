<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
<!--     <meta http-equiv="refresh" content="5"> -->
    <title>南光会议会展管理及智能营销系统</title>
	<style type="text/css">
	body{
		width:1024px;
		margin:0 auto;
		background:#fff;
	}
	#question{				
		background: #F4F2E5;
		margin-top: 0px;
		height: 768px;
	}
	#question h2{
	    vertical-align:middle; 
        line-height:34px;
        TEXT-ALIGN: left;
        font-size: 28px;        
	    color:#5d0d08;
	    line-height:58px;
	    font-weight:bold;	 
	    margin:0px;   
		padding:40px 100px;
		height:440px;
		background: #F4F2E5;
	}
	.tl {
	   TEXT-ALIGN: center;
	}

	#QRimg img{
	   float:right;
	   margin-right:0px;
	}
	h3 {
	   font-size: 24px;
	   color:#F29409;
	   font-weight:bold;
	   color:#fff;
	   padding:10px;
	   text-align:left;
	   margin:0px 0px 10px 0px;
	   background: #F29409;
	}

	</style>
  </head>
  
  <body>
      <div id="question">
      <img src='<%=basePath%>images/reg_banner.jpg' style="width:1024px;margin-top:2px;margin-bottom:10px;" />
        <c:forEach items="${questions }" var="question">
            <h3>现场观众提问(Questions)：</h3>
            <h2><img src="<%=basePath%>images/meeting_wall/question_tb.jpg" width="100px" /> ${question.content }
                <p style="margin-left:110px;">${question.contentEn }</p>
            </h2>
        </c:forEach>
		<div id="QRimg" style="width:100%;height:120px;margin-bottom:0;background: #F4F2E5;">
        	<img id="img" alt="" height="120" src="<%=basePath%>images/meeting_wall/phone_QR.jpg">
        </div>
      </div>
  </body>
</html>
