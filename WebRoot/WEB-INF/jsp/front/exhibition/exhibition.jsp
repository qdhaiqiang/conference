<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title><spring:message code="exhibition.applyBooth"/></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="<%=basePath%>css/exhibition.css" rel="stylesheet">
	<jsp:include page="../../../../include/common.jsp"/>
	<script src="<%=basePath%>js/jquery.min.js"></script>
	<script src="<%=basePath%>js/jquery.smartWizard-2.0.min.js"></script>
  </head>
  
  <body style="background:#FBFAF8;">
  	<div class="cont_exhi" style="height:auto;">
  	<img src="<%=basePath %>images/reg_banner.jpg" />
    <div class="actBar">
    	<h3 style="margin-top:10px;"><spring:message code="exhibition.applyBoothProcess"/></h3>
    	<form id="exhibition-info-form">
		<div id="wizard" class="swMain">
			<ul>
				<li><a href="#step-1"><span class="stepDesc"><spring:message code="exhibition.company"/> ></span></a></li>
				<li><a href="#step-2"><span class="stepDesc"><spring:message code="exhibition.magazineMaterial"/> ></span></a></li>
				<li><a href="#step-3"><span class="stepDesc"><spring:message code="exhibition.boothDetail"/> ></span></a></li>
				<li><a href="#step-4"><span class="stepDesc"><spring:message code="exhibition.otherBoard"/> ></span></a></li>
				<li><a href="#step-5"><span class="stepDesc"><spring:message code="exhibition.otherFurnitures"/> ></span></a></li>		
				<li><a href="#step-6"><span class="stepDesc"><spring:message code="exhibition.itemInfo"/> ></span></a></li>
				<li><a href="#step-7"><span class="stepDesc"><spring:message code="exhibition.logistics"/> ></span></a></li>
				<li><a href="#step-8"><span class="stepDesc"><spring:message code="exhibition.confirmApplication"/></span></a></li>
			</ul>
			<div id="step-1" style="text-align:center;">
				<jsp:include page="industry_tab1.jsp"/> 
			</div>
			<div id="step-2">
				<jsp:include page="company_tab2.jsp"/>
			</div>                      
			<div id="step-3">
				<jsp:include page="exhibitionDetail_tab3.jsp"/>
			</div>
			<div id="step-4">
				<jsp:include page="lintel_tab4.jsp"/>
			</div>
			<div id="step-5">
				<jsp:include page="furniture_tab5.jsp"/>
			</div>
			<div id="step-6">
				<jsp:include page="exhibitInfo_tab6.jsp"/>	
			</div>
			<div id="step-7">
				<jsp:include page="logistics_tab7.jsp"/>	
			</div>
			<div id="step-8">
				<jsp:include page="confirm_tab8.jsp"/>	
			</div>
    	</div>
	</form>
	</div>
	</div>
	<jsp:include page="../footer.jsp"></jsp:include>
  </body>
  
  <script type="text/javascript">
	$(document).ready(function() {
		// Smart Wizard,加分步验证和点击完成的事件 	
		$('#wizard').smartWizard({
			onLeaveStep:leaveAStepCallback,
			onShowStep:showStepCallback,
			onFinish:onFinishCallback
		});
		
		//保存参展商信息
		$('#btn-submit').click(function() {
			saveExhibitionForm();
		});
	});
	
	function saveExhibitionForm(){
		var form = $("#exhibition-info-form");
		//Ajax提交保存
		$.ajax({
			  type:"post",
			  url:'front/exhibitCompany/exhibitionsave',
			  data:form.serialize(),
			  success:function(result){
				  if(result.status=="1"){
					  alert("<spring:message code='basicInfo.saveSucceed'/>");
					  window.location.href = "../../../../../article/8ada99c148abaa050148cbf271a8078b";
				  }else if(result.status=="2"){
					  alert(result.info);
				  }else{
					  alert("保存 失败"); 
				  }
			 }
		});
	}

	function showStepCallback(obj){
		var step_num= obj.attr('rel');
		if(step_num == 8){
			 preView();
		}
		return true;
	}
	
	function leaveAStepCallback(obj){
		var step_num= obj.attr('rel'); // get the current step number
		return validateSteps(step_num); // return false to stay on step and true to continue navigation
	}
	
	function onFinishCallback(){
		//表单提交
		saveExhibitionForm();
	}
	
	// Your Step validation logic
    function validateSteps(stepnumber){
      var isStepValid = true;
      //alert("stepnumber:"+stepnumber);
   		// validate step 1
      if(stepnumber == 1){
        // Your step validation logic
        // set isStepValid = false if has errors
    	isStepValid = checkTab0();
      }else if(stepnumber == 2){
    	  isStepValid = checkTab1();
      }else if(stepnumber == 3){
    	  isStepValid = checkTab2();
      }else if(stepnumber == 4){
    	  isStepValid = checkTab3();
      }else if(stepnumber == 5){
    	  isStepValid = checkTab4();
      }else if(stepnumber == 6){
    	  isStepValid = checkTab5();
      }else if(stepnumber == 7){
    	  isStepValid = checkTab6();
      }else if(stepnumber == 8){
    	  isStepValid = checkTab7();
      }
      return isStepValid;
    }
  </script>
  
</html>
