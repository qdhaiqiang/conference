<%@ page language="java" import="java.util.*,javax.servlet.*,javax.servlet.http.*,org.springframework.web.servlet.support.RequestContextUtils" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Locale locale = RequestContextUtils.getLocale(request);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta name="viewport" content="width = device-width, user-scalable=no minimum-scale=1.0 maximum-scale=1.0" />
		<title><spring:message code="titleInfo.meetingTitle" /></title>
		<link rel="stylesheet" href="<%=basePath%>css/plugins/mobile/jquery.mobile-1.4.4.min.css">
		<link rel="stylesheet" href="<%=basePath%>css/common-mobile.css">
		<link rel="stylesheet" href="<%=basePath%>public/layer/skin/layer.css">
		<script src="<%=basePath%>public/mobile/jquery.js"></script>
		<script src="<%=basePath%>public/mobile/jquery.mobile-1.4.4.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>public/layer/layer.min.js"></script>
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
		</style>
		<script type="text/javascript">
			$(document).ready(function(){
				$("#okButton").hide();
				var votelist = ${votesList};
				setVotesListHtml(votelist);
			});
			
			function setVotesListHtml(VotesList){
				//var list = eval(VotesList);
				var htmlValue = '';
				if(VotesList.length < 1){
					layer.alert("<spring:message code='mobile.vote.noquestion'/>",8,"<spring:message code='mobile.vote.tips' />",function () {
						voteCancle();
					});
					return;
				}
				for(var i in VotesList){
					var num = (parseInt(i)+1);
					htmlValue += '<li><p style="white-space:pre-wrap;"><strong>'+
								num+'.'+"<spring:message code='mobile.vote.question'/>"+
								VotesList[i].name+'</strong></p>';
					var options = eval(VotesList[i].options);
					
					if(VotesList[i].type == "text"){
						htmlValue += '<input type="text">';
					}else{
						for(var j in options){
							htmlValue += '<input type="'+VotesList[i].type+
									'" name="'+VotesList[i].type+i+
									'">'+options[j].label+'<br>';
						}
					}
					htmlValue += '</li>'
				}
				$("#okButton").show();
				$("#voteData").html(htmlValue);
				$("#voteData").listview("refresh");
			}
			
			function checkIsSelect(){
				var list = $("#voteData").find("li");
				for(var i=0;i<list.length;i++){
					var li = list.eq(i);
					var isPut = "";//jstify the vote is select or not
					
	    			if(li.find("input[type='text']").length > 0){
	    				isPut = li.find("input[type='text']").first().val();
	    			}else{
	    				var inputType = "";
	    				var radio = "input[type='radio']";
	    				var checkbox = "input[type='checkbox']";
	    				if(li.find(radio).length > 0){
	    					inputType = radio;
	    				}else if(li.find(checkbox).length > 0){
	    					inputType = checkbox;
	    				}else{
	    					isPut = "no votes.";
	    					continue;
	    				}
	    				var input = li.find(inputType);
	    				for(var j=0;j<input.length;j++){
	    					var option = input.eq(j);
	    					var optioncheck = option.is(":checked");//false,true
	    					if(optioncheck){
	    						isPut += option.attr("value");
	    					}
	    				}
	    			}
	    			if(isPut == ""){
	    				return false;
	    			}
				}
				return true;
			}

			function voteOk(){
				if(checkIsSelect() == true){
					layer.alert("<spring:message code='mobile.vote.success'/>",1,"<spring:message code='mobile.vote.tips' />",function () {
						voteCancle();
					});
				}else{
					layer.alert("<spring:message code='mobile.vote.unSelect'/>");
				}
			}
			function voteCancle(){
				window.history.go(-1);
			}
		</script>
	</head>

	<body>
		<div data-role="page" id="pageone" data-theme="a" style="margin-top: 30px;">
			<input id="title" value="<spring:message code="mobile.vote.title" />" type="hidden"/>
			<jsp:include page="../../../include/header-mobile.jsp"/>
			<div data-role="content">
				<div id="showVotes">
					<div>
						<ul data-role="listview" data-inset="true" id="voteData"></ul>
					</div>
					<button id="okButton" onClick="voteOk()"><spring:message code='mobile.vote.btnOK'/></button>
					<button onClick="voteCancle()"><spring:message code='mobile.vote.btnCancle'/></button>
				</div>
			</div>
		</div>
	</body>
</html>