<%@ page language="java" import="java.util.*,javax.servlet.*,javax.servlet.http.*,org.springframework.web.servlet.support.RequestContextUtils" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
<title><spring:message code="titleInfo.meetingTitle" /></title>
<link href="<%=basePath%>css/exhibition_index.css" rel="stylesheet">
<script src="<%=basePath%>js/jquery-1.7.2.min.js"></script>
<style type="text/css">
.menu-two .selected {
	background-color: #cbe9ef;
}
.zwapply{
	color:#fff;
	background:#F9970B;
	border-radius:10px;
	font-size:12px;
	float:right;
	padding:1px 10px;
	margin-right:10px;
}
.currentpo a:hover{
	text-decoration:none;
}

#maincontent{
	min-height:395px;
	background:rgb(244, 242, 229);
}
#maincontent a{
	padding-left:25px;
	padding-top:10px;
	line-height:27px;
	color:#5d0d08;
	font-size:13px;
	background:url(<%=basePath%>images/list_a.jpg) no-repeat 12 17;
}
#maincontent a:hover{
	color:#F9970B;
}
</style>
</head>
<body>
	<!-- 按钮及中英文切换 -->
	<div id="top_1_0">
		<div class="clearfix">
			<ul class="top_1_ul">
				<li><a target="_blank" href="<%=basePath %>r/front/contact_us"
					title="<spring:message code="index.contactUs"/>"><spring:message
							code="index.contactUs" />&nbsp;&nbsp;</a></li>
				<li><a target="_blank" id="a11" href="<%=basePath %>front/reg/emailCheck/2"
					title="<spring:message code="index.participantenter"/>"><spring:message
							code="index.participantenter" />&nbsp;&nbsp;</a></li>
				<li><a target="_blank" id="a12" href="<%=basePath %>front/reg/emailCheck/1"
					title="<spring:message code="index.staffenter"/>"><spring:message
							code="index.staffenter" />&nbsp;&nbsp;</a></li>
				<li><a target="_blank" id="a13" href="<%=basePath %>r/front/login"
					title="<spring:message code="index.tologin"/>"
					style="border-right:none;"><spring:message code="index.tologin" /></a>
				</li>
			</ul>
			<ul class="top_1_ul" style="float:right;margin-right:145px;">
				<%-- <li><a href="<%=basePath %>r/front/exhibition/exhibition_index?locale=en_US">English&nbsp;&nbsp;</a>
				</li> --%>
				<li><a href="<%=basePath %>r/front/exhibition/exhibition_index?locale=zh_CN"
					style="border-right:none;">中文</a></li>
			</ul>
		</div>
	</div>

	<div id="maincontainer">
		<img src="<%=basePath%>images/reg_banner.jpg" width="950" height="117" />  
		<div style="margin-top:6px">
			<div class="content">
				<ul class="menu-one">
					<c:forEach items="${confEssayMenu['essayTypeList'] }" var="essayType">
			      	<li>
			      		<div class="header">
			      			<span class="txt">
				      		<% if ("en_US".equals(locale.toString())) {  %>
				      			<a class="menulever1" id="${essayType.id}">${essayType.typeNameEn }</a>
				      			<!-- onclick="javascript:showMenuName(this)"  -->
				      		<% } else { %>
				      			<a class="menulevel1" id="${essayType.id}">${essayType.typeName }</a>
				      		<% } %>
			      			</span>
			      		</div>		
			      		
			      		<ul class="menu-two">
			      		<c:forEach items="${confEssayMenu['essayMap'] }" var="essayMap">
			      			<c:if test="${essayMap.key==essayType.id }">
			      				<c:forEach items="${essayMap.value }" var="secondEssayType">
			      					<li>
			      						<% if ("en_US".equals(locale.toString())) {  %>
			      							<a id="${secondEssayType.id }" onclick="javascript:showContent('${secondEssayType.id }','${secondEssayType.infoType }',this)">${secondEssayType.typeNameEn }</a>
			      						<% } else { %>
			      							<a id="${secondEssayType.id }" onclick="javascript:showContent('${secondEssayType.id }','${secondEssayType.infoType }',this)">${secondEssayType.typeName }</a>
			      						<% } %>
			      					</li>
			      				</c:forEach>
			      			</c:if>
			      		</c:forEach>
			      		</ul>
			      	</li>
			      </c:forEach>
				</ul>
			</div>

			<div id="mainright">
				<span class="currentpo"><spring:message code="index.location"/> : <label id="lb1"></label> 
					<font style="color:#F9970B;"><label id="lb2"></label> </font>
					<a class="zwapply" href="<%=basePath %>r/front/exhibition/login" target="_blank">
						<spring:message code="index.boothapply"/> 
					</a>
				</span>
				<div id="maincontent">
				
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../footer.jsp"></jsp:include>
	<script type="text/javascript">
	
	$(document).ready(function () {
		
		var aMenuOneLi = $(".menu-one > li");
		var aMenuTwo = $(".menu-two");
		$(".menu-one > li > .header").each(function (i) {
			$(this).click(function () {
				if ($(aMenuTwo[i]).css("display") == "block") {
					$(aMenuTwo[i]).slideUp(300);
					$(aMenuOneLi[i]).removeClass("menu-show");
					$("#maincontent").html("");
				} else {
					for (var j = 0; j < aMenuTwo.length; j++) {
						$(aMenuTwo[j]).slideUp(300);
						$(aMenuOneLi[j]).removeClass("menu-show");
					}
					$(aMenuTwo[i]).slideDown(300);
					$(aMenuOneLi[i]).addClass("menu-show")
				}
			});
		});
		
		// 初始化文章信息
		var secondEssayTypeId = '${secondEssayTypeId}';
		if (secondEssayTypeId!="") {
		    // 父级菜单点击
		    $("#"+secondEssayTypeId).parent().parent().prev().find("a").click();
			// 事件点击
			$("#"+secondEssayTypeId).click();
		}
	});
	
	var menu1 = "";
	$(".menulevel1").click(function(){
		menu1 = this.innerHTML;
		//setBg(this);
		$("#lb1").html(menu1+" -> ");
		$("#lb2").html("");
	});
	
	$(".menulever1").click(function(){
		menu1 = this.innerHTML;
		$("#lb1").html(menu1+" -> ");
		$("#lb2").html("");
	});
	
	function showContent(secondEssayTypeId,infoType,obj){
		// ajax请求获取文章内容
		$.ajax({
			url: '<%=basePath%>essay/getEssayBySecondTypeId',
			type: 'POST',
			data:{secondEssayTypeId:secondEssayTypeId,infoType:infoType},
			success: function(result){
				// 文章
				if (infoType==1) {
					if ("en_US"=="<%=locale%>") {
						$("#maincontent").html(result[0].essayContentEn);
					} else {
						$("#maincontent").html(result[0].essayContent);
					}
				// 列表
				} else {
					var htmlVar = "";
					for(var i in result) {
						htmlVar += "<a onclick='showDetail("+JSON.stringify(result[i])+")'>";
						if ("en_US"=="<%=locale%>") {
							htmlVar += result[i].essayTitleEn
						} else {
							htmlVar += result[i].essayTitle
						}
						htmlVar += "</a><br />";
					}
					$("#maincontent").html(htmlVar);
				}
				$("#lb1").html(menu1+" -> ");
				$("#lb2").html(obj.innerHTML);
				setBg(obj);
			}
		});
	}
	
	/*$(".menu-one a:not(.menulever1 a)").click(function(){
		setBg(this);
	});*/

	//选中更改背景
	function setBg(obj){
		// 操作标签
		var tag = $(".menu-one a");
		var taglength = tag.length;
		for(i=0; i<taglength; i++){
			if(obj ==tag[i]){
				obj.className = "selected";
			}
			else{
				tag[i].className = "";
			}
		}
	}
	
	function showDetail(obj) {
		if ("en_US"=="<%=locale%>") {
			$("#maincontent").html(obj.essayContentEn);
		} else {
			$("#maincontent").html(obj.essayContent);
		}
	}
</script>
</body>
</html>