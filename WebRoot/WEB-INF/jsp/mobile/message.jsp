<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<meta name="viewport" content="width = device-width, user-scalable=no minimum-scale=1.0 maximum-scale=1.0" />
  	<link rel="stylesheet" href="<%=basePath%>css/plugins/mobile/jquery.mobile-1.4.4.min.css">
	<link rel="stylesheet" href="<%=basePath%>css/common-mobile.css">
	<script src="<%=basePath%>public/mobile/jquery.js"></script>
	<script src="<%=basePath%>public/mobile/jquery.mobile-1.4.4.min.js"></script>
	<style type="text/css">
		.ui-content {
		border-width: 0;
		overflow: visible;
		overflow-x: hidden;
		padding: 10px;
		}
		.warnning {
			text-align: center;
			color: red;
			font-size: 15px;
			line-height: 10px;
		}
		.ui-listview > li p {
			white-space: normal;
		}
		body {
			margin: 0px;
			padding: 0px;
			font-family: "微软雅黑", "宋体";
			font-size: 15px;
			background-color: #fff;
		}
	</style>
	
    <title><spring:message code="mobile.message.tile" /></title>
  	<script type="text/javascript">
  		var messageList = new Array();
  		$(document).ready(function(){
  			$.ajax({
  				url:"<%=basePath%>confUserMessagePushController/retrieve",
  				method:"post",
  				async:false,
  				success:function(data){
  					messageList = data;
  				}
  			});
  			loadMessageList(messageList,"0");
  			
  		});
  		
  		function loadMessageList(data,id){
  			setMessageListHtml(data,id);
  		}
  		function setMessageListHtml(data,id){
  			var html = "";
  			var messagePreview="";
  			for(var i in data){
  				if(id==data[i].id){
  					messagePreview = getClickedMessageContent(data[i].id);
  				}else{
  					messagePreview = setPreview(data[i].id);
  				}
  				if(data[i].state==0){
  					html += '<li id="'+data[i].id+'" onclick="showMessageContent(this)"><p class = "labelArea" style="font-weight:bold">'+messagePreview+'</p></li>';
  				}else{
  					html += '<li id="'+data[i].id+'" onclick="showMessageContent(this)"><p class = "labelArea">'+messagePreview+'</p></li>';
  				}
  			}
  			$("#messageList").html(html);
  			$("#messageList").listview("refresh");
  		}
  		function setPreview(messageId){
  			for(var i = 0; i < messageList.length; i++){
  				if(messageId == messageList[i].id){
  					if(messageList[i].content.length>25){
  						return messageList[i].content.substring(0,24)+"…";
  					}else{
  						return messageList[i].content;
  					}
  				}
  			}
  		}
  		function getClickedMessageContent(messageId){
  			for(var i = 0; i < messageList.length; i++){
  				if(messageId == messageList[i].id){
  					return messageList[i].content;
  				}
  			}
  		}
  		function showMessageContent(message){
  			updateMessageState(message.id);
  			loadMessageList(messageList,message.id);
  		}
  		function updateMessageState(id){
  			$.ajax({
  				url:"<%=basePath%>confUserMessagePushController/read",
  				method:'post',
  				async:false,
  				data:{messageId:id},
  				success:function(data){
  				}
  			});
  			for(var i in messageList){
  				if(id == messageList[i].id){
  					messageList[i].state = "1";
  				}
  			}
  			
  		}
  	</script>

  </head>
  
  <body>
  <div data-role="page" style="margin-top: 30px;">
		<input id="title" value="<spring:message code="mobile.message.tile" />" type="hidden"/>
		<jsp:include page="../../../include/header-mobile.jsp"></jsp:include>
		<!-- 表单 -->
		<div data-role="content" data-theme="c" >
			<div id="showSchedule">
				<ul data-role="listview" data-inset="true" id="messageList">
					<li id="scheduleId" onclick="showVotesBySchedule(this)">
						<label for="fInputEmail" class="lableArea"> 
							<spring:message code="mobile.message.error" />
						</label>
					</li>
				</ul>
			</div>
		</div>
	</div>
  </body>
</html>
