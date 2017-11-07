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
			var schduleLists = new Array();
			$(document).ready(function(){
				$("#scheduleList").show();
				$("#showVotes").hide();
				loadSchduleList();
			});
			function loadSchduleList(){
				$.ajax({
			        url: '<%=basePath%>schedule/mobile/findByMeetingId',
			        method:'get',
			        async:false,
			        success:function(data) {
			        	schduleLists = data;
			        }
			    });
	
				$.ajax({
			        url: '<%=basePath%>schedule/mobile/frontUserSchedule',
			        method:'get',
			        async:false,
			        success:function(data) {
			        	setSchduleListHtml(data);
			        }
			    });
			}

			function setSchduleListHtml(schduleList){
				//#scheduleList
				var htmlValue = '<li data-role=\"list-divider\" ></li>';
				//var list = eval(schduleList);
				for(var i in schduleList){
					var schduelParam = setSchduelName(schduleList[i].scheduleId);
					htmlValue += '<li id="'+schduleList[i].scheduleId+'" style="cursor:pointer;" onclick="showVotesBySchedule(this)">'+
								 '<h2 style="white-space:pre-wrap;">'+schduelParam.title+'</h2>'+
								 '<p><strong>'+schduelParam.startTime+' ~ '+schduelParam.endTime+'</strong></p>'+
								 '<p>'+schduelParam.intro+'</p>'+
								 '</li>';
				}
				$("#scheduleList").html(htmlValue);
				$("#scheduleList").listview("refresh");
			}

			//每条加载的物料中根据日程id显示日程名称
			function setSchduelName(id) {
				var param = {}; 
   				for (var i=0; i<schduleLists.length; i++) {
			       if (id == schduleLists[i].id) {
			    	   param["location"] = schduleLists[i].location;
			    	   if ("en_US"=="<%=locale%>") {
			    	       param["title"] = schduleLists[i].titleEn;
			    	   } else {
			    	  	   param["title"] = schduleLists[i].title;
			    	   }
			    	   param["startTime"] = schduleLists[i].startTime;
			    	   param["endTime"] = schduleLists[i].endTime;
			    	   if ("en_US"=="<%=locale%>") {
			    	       param["intro"] = schduleLists[i].introEn;
			    	   } else {
			    	  	   param["intro"] = schduleLists[i].intro;
			    	   }
			    	   
			           return param;
			       }
			   	}
			}

			function showVotesBySchedule(obj){
				var id=obj.id;
				window.location.href='<%=basePath%>confVote/mobile/findVotesBySchduelId/'+id;
				/*$.ajax({
			        url: 'confVote/mobile/findVotesBySchduelId/'+id,
			        method:'get',
			        async:false,
			        success:function(data) {
			        	setVotesListHtml(data);
			        }
			    });*/
				//#showVotes
			}

			function setVotesListHtml(VotesList){
				//var list = eval(VotesList);
				var htmlValue = '';
				if(VotesList.length < 1){
					layer.alert("<spring:message code='mobile.vote.noquestion'/>");
					return;
				}
				for(var i in VotesList){
					var num = (parseInt(i)+1);
					htmlValue += '<li>'+num+'.'+"<spring:message code='mobile.vote.question'/>"+VotesList[i].name+'<br>';
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
				$("#voteData").html(htmlValue);
				$("#voteData").listview("refresh");
				$("#scheduleList").hide();
				$("#showVotes").show();
			}

			function voteOk(){
				layer.alert("<spring:message code='mobile.vote.success'/>",1);
				$("#scheduleList").show();
				$("#showVotes").hide();
			}
			function voteCancle(){
				$("#scheduleList").show();
				$("#showVotes").hide();
			}
		</script>
	</head>

	<body>
		<div data-role="page" id="pageone" data-theme="a" style="margin-top: 30px;">
			<input id="title" value="<spring:message code="mobile.vote.title" />" type="hidden"/>
			<jsp:include page="../../../include/header-mobile.jsp"/>
			<div data-role="content">
				<div id="showSchedule">
					<ul data-role="listview" data-inset="true" id="scheduleList">
						<li id="scheduleId" onclick="showVotesBySchedule(this)">
							<label for="fInputEmail" class="lableArea"></label>
						</li>
						<li id="scheduleId" onclick="showVotesBySchedule(this)">
							<label for="fInputEmail" class="lableArea"></label>
						</li>
					</ul>
				</div>
				<div id="showVotes">
					<div>
						<ul data-role="listview" data-inset="true" id="voteData"></ul>
					</div>
					<button onclick="voteOk()"><spring:message code='mobile.vote.btnOK'/></button>
					<button onclick="voteCancle()"><spring:message code='mobile.vote.btnCancle'/></button>
				</div>
			</div>
		</div>
	</body>
</html>