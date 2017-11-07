<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<style>
.combo {
	display: block;
}
</style>

<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">
			<spring:message code="basicInfo.certification" />
		</h3>
	</div>
	
	<div class="panel-body bs-step-inner">
		<!-- 有效身份证件类型 -->
		<div class="col-sm-8">
			<label class="required-label">*</label>
			<spring:message code="basicInfo.certificationType" />
			<input id="certType" class="easyui-combobox" name="certType" editable="false" value="${user.certType}" data-options="valueField:'code',textField:'name',url:'dict/r/cert_type',method:'get',required:'true'" />
		</div>
		<div class="col-sm-4 help">
			<h4>
				<span style="color:#891300;"><spring:message code="basicInfo.help" /></span>
				<span><spring:message code="basicInfo.certificationType" /></span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.certificationTypeHelp" />
				</span>
			</p>
		</div>
		<div class="clearfix"></div>
		
		<!-- 有效身份证件号码 -->
		<div class="col-sm-8">
			<label class="required-label">*</label>
			<spring:message code="basicInfo.certificationNo" />
			<input type="text" class="form-control" maxlength="50" id="certificationNo" name="certValue" value="${user.certValue}">
		</div>
		<div class="col-sm-4 help">
			<h4>
				<span style="color:#891300;"><spring:message code="basicInfo.help" /></span> 
				<span><spring:message code="basicInfo.certificationNo" /></span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.certificationNo" /></span>
			</p>
		</div>
		<div class="clearfix"></div>
		<br />
		<!-- 5、入澳证件信息 -->
		<div class="col-sm-8"><spring:message code="basicInfo.HK/MacauIntro"/></div>
		<div class="col-sm-4 help"></div>
		
		<br />
		<!-- 入澳证件类型 -->
		<div class="col-sm-8">
			<!-- <label class="required-label">*</label> -->
			<spring:message code="basicInfo.HK/MacauEntryType" />
			<input id="entryType" class="easyui-combobox" name="entryType" editable="false" value="${user.entryType}" data-options="valueField:'code',textField:'name',url:'dict/r/entry_type',method:'get',onChange:changeimage" />
		</div>
		<div class="col-sm-4 help">
			<h4>
				<span style="color:#891300;"><spring:message code="basicInfo.help" /></span> 
				<span><spring:message code="basicInfo.HK/MacauEntryType" /></span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.HK/MacauEntryTypeHelp" /></span>
			</p>
		</div>
		<div class="clearfix"></div>
		
		<!-- 入澳证件签发日期 -->
		<div class="col-sm-8">
			<!-- <label class="required-label">*</label> -->
			<spring:message code="basicInfo.HK/MacauEntryDate" />
			<input type="text" class="easyui-datebox" id="entryDate" data-options="formatter:dateFormatter,parser:dateParser" editable="false" name="entryDate" value="${user.entryDate}">
		</div>
		<div class="col-sm-4 help">
			<h4>
				<span style="color:#891300;"><spring:message code="basicInfo.help" /></span>
				<span><spring:message code="basicInfo.HK/MacauEntryDate" /></span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.HK/MacauEntryDate" /></span>
			</p>
		</div>
		<div class="clearfix"></div>
		
		<!-- 入澳证件有效期 -->
		<div class="col-sm-8">
			<!-- <label class="required-label">*</label> -->
			<spring:message code="basicInfo.HK/MacauEntryValidity" />
			<input type="text" class="easyui-datebox" data-options="formatter:dateFormatter,parser:dateParser" editable="false" id="entryValidity" name="entryValidity" value="${user.entryValidity}">
		</div>
		<div class="col-sm-4 help">
			<h4>
				<span style="color:#891300;"><spring:message code="basicInfo.help" /></span>
				<span><spring:message code="basicInfo.expiryDate" /></span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.expiryDateHelp" /></span>
			</p>
		</div>
		<div class="clearfix"></div>

		<div class="col-sm-8">
			<!-- <label class="required-label">*</label> -->
			<spring:message code="basicInfo.HK/MacauEntryPlace" />
			<input type="text" class="form-control" maxlength="50" id="entryPlace" name="entryPlace" value="${user.entryPlace}">
		</div>

		<div class="col-sm-4 help">
			<h4>
				<span style="color:#891300;"><spring:message code="basicInfo.help" /></span>
				<span><spring:message code="basicInfo.HK/MacauEntryPlace" /></span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.HK/MacauEntryPlace" /></span>
			</p>
		</div>
		<div class="clearfix"></div>

		<div class="col-sm-8">
			<!-- <label class="required-label">*</label> -->
			<spring:message code="basicInfo.HK/MacauEntryNum" />
			<input type="text" class="form-control" maxlength="50" id="entryNum" name="entryNum" value="${user.entryNum}">
		</div>

		<div class="col-sm-4 help">
			<h4>
				<span style="color:#891300;"><spring:message code="basicInfo.help" /></span>
				<span><spring:message code="basicInfo.HK/MacauEntryNum" /></span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.HK/MacauEntryNum" /></span>
			</p>
		</div>
		<div class="clearfix"></div>

		<div class="col-sm-8">
			<!-- <label class="required-label">*</label> -->
			<spring:message code="basicInfo.HK/MacauEntryEndmtDate" />
			<input type="text" class="easyui-datebox" data-options="formatter:dateFormatter,parser:dateParser" editable="false" id="entryEndmtDate" name="entryEndmtDate" value="${user.entryEndmtDate}">
		</div>

		<div class="col-sm-4 help">
			<h4>
				<span style="color:#891300;"><spring:message code="basicInfo.help" /></span>
				<span><spring:message code="basicInfo.HK/MacauEntryEndmtDate" /></span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.HK/MacauEntryEndmtDate" /></span>
			</p>
		</div>
		<div class="clearfix"></div>

		<div class="col-sm-8">
			<!-- <label class="required-label">*</label> -->
			<spring:message code="basicInfo.HK/MacauEntryEndmtValidity" />
			<input type="text" class="easyui-datebox" data-options="formatter:dateFormatter,parser:dateParser" editable="false" id="entryEndmtValidity" name="entryEndmtValidity" value="${user.entryEndmtValidity}">
		</div>

		<div class="col-sm-4 help">
			<h4>
				<span style="color:#891300;"><spring:message code="basicInfo.help" /></span>
				<span><spring:message code="basicInfo.HK/MacauEntryEndmtValidity" /></span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.HK/MacauEntryEndmtValidity" /></span>
			</p>
		</div>
		<div class="clearfix"></div>

		<div id="pic1fileuploaddiv" class="col-sm-8">
			<p>
				<spring:message code="basicInfo.HK/MacauEntryPic1" />
				<spring:message code="basicInfo.imgType" />
			</p>
			<div id="pic1filelist"></div>
			<div id="pic1imgfileshow">
				<img src="<%=basePath%>${user.entryPic1}" alt="image">
			</div>
			<a id="pic1browse" href="javascript:;">[<spring:message code="basicInfo.selectFile" />]</a> 
			<input id="pic1input" name="entryPic1" value="${user.entryPic1}" type="hidden">
		</div>
		<div class="col-sm-4 help">
			<h4>
				<span style="color:#891300;"><spring:message code="basicInfo.help" /></span>
				<span><spring:message code="basicInfo.HK/MacauEntryPic1" /></span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.frontSidePhotoHelp" /></span>
				<br>
				<img id="pic1photoexample" src="<%=basePath%>images/photoexamples/regionalpass1.jpg" onload="loadImage(this,'pic1photoexample')" width=0 height=0 >
			</p>
			<!--  onload="loadfrontImage(this,'pic1photoexample')"  width=0 height=0-->
		</div>
		<div class="clearfix"></div>
		
		<div id="pic2fileuploaddiv" class="col-sm-8">
			<p>
				<spring:message code="basicInfo.HK/MacauEntryPic2" />
				<spring:message code="basicInfo.imgType" />
			</p>
			<div id="pic2filelist"></div>
			<div id="pic2imgfileshow">
				<img src="<%=basePath%>${user.entryPic2}" alt="image">
			</div>
			<a id="pic2browse" href="javascript:;">[<spring:message code="basicInfo.selectFile" />]</a>
			<input id="pic2input" name="entryPic2" value="${user.entryPic2}" type="hidden">
		</div>

		<div class="col-sm-4 help">
			<h4>
				<span style="color:#891300;"><spring:message code="basicInfo.help" /></span>
				<span><spring:message code="basicInfo.HK/MacauEntryPic2" /></span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.backSidePhotoHelp" /></span>
				<br>
				<img id="pic2photoexample" src="<%=basePath%>images/photoexamples/regionalpass2.jpg" onload="loadImage(this,'pic2photoexample')" width=0 height=0 >
			</p>
			<!-- onload="loadbacksideImage(this,'pic2photoexample')"  width=0 height=0-->
		</div>
		<div class="clearfix"></div>
	</div>
</div>


<script type="text/javascript">
$(function() {
	//主动触发onload事件
	$("#pic1photoexample").attr("src","<%=basePath%>images/photoexamples/regionalpass1.jpg");
	$("#pic2photoexample").attr("src","<%=basePath%>images/photoexamples/regionalpass2.jpg");
	
});

function changeimage(){
	var type = $("#entryType").combobox("getValue");
	//pic2photoexample pic1photoexample
	/*if(type == "1"){
		$("#pic1photoexample").attr("src","<%=basePath%>images/photoexamples/regionalpass.jpg");
		$("#pic2photoexample").attr("src","<%=basePath%>images/photoexamples/regionalpass.jpg");
	}else if(type == "2"){
		$("#pic1photoexample").attr("src","<%=basePath%>images/photoexamples/passport.jpg");
		$("#pic2photoexample").attr("src","<%=basePath%>images/photoexamples/passport.jpg");
	}else if(type == "3"){
		$("#pic1photoexample").attr("src","<%=basePath%>images/photoexamples/regionalidcard.jpg");
		$("#pic2photoexample").attr("src","<%=basePath%>images/photoexamples/regionalidcard.jpg");
	}*/
}

var fileuploadurl ="<%=basePath%>fileload/c/upload";
//入澳证件正反面照
var pic1_params = {
		optiontype:"user",
		sign:"${user.email}",
		filecategory:'front',
	};
var pic2_params = {
		optiontype:"user",
		sign:"${user.email}",
		filecategory:'back',
	};
initUploader("pic1browse","pic1filelist","pic1imgfileshow","pic1input","pic1fileuploaddiv",fileuploadurl,false,420,600,true,pic1_params);
initUploader("pic2browse","pic2filelist","pic2imgfileshow","pic2input","pic2fileuploaddiv",fileuploadurl,false,420,300,true,pic2_params);


var check_total_tab3 = function(){
	var entryDate = $("[name='entryDate']").val();
	var entryValidity = $("[name='entryValidity']").val();
	var entryEndmtDate = $("[name='entryEndmtDate']").val();
	var entryEndmtValidity = $("[name='entryEndmtValidity']").val();
	if ($("[name='certType']").val().Trim()=="") {
		//提示基本信息不完整
		alert("<spring:message code='basicInfo.certificationType' />"+"  "+"<spring:message code='basicInfo.lackOfInfo'/>");
		return false;	
	}
	
	if ($("[name='certValue']").val().Trim()=="") {
		//提示基本信息不完整
		alert("<spring:message code='basicInfo.certificationNo' />"+"  "+"<spring:message code='basicInfo.lackOfInfo'/>");
		document.getElementById("certificationNo").focus();
		return false;	
	}
	
	/**
	if ($("[name='entryType']").val().Trim()=="") {
		//提示基本信息不完整
		alert("<spring:message code='basicInfo.HK/MacauEntryType' />"+"  "+"<spring:message code='basicInfo.lackOfInfo'/>");
		return false;	
	}
	
	if (entryDate =="") {
		//提示基本信息不完整
		alert("<spring:message code='basicInfo.HK/MacauEntryDate' />"+"  "+"<spring:message code='basicInfo.lackOfInfo'/>");
		return false;	
	}
	
	if (entryValidity=="") {
		//提示基本信息不完整
		alert("<spring:message code='basicInfo.HK/MacauEntryValidity' />"+"  "+"<spring:message code='basicInfo.lackOfInfo'/>");
		return false;	
	}**/
	if(entryDate!="" && entryValidity!="" && dateLargeThan(entryDate,entryValidity)){
		alert("<spring:message code='basicInfo.HK/MacauEntryDate' /> "+"<spring:message code='basicInfo.timeCheck' />"+" <spring:message code='basicInfo.HK/MacauEntryValidity' />");
		return false;
	}
	
	/**
	if ($("[name='entryPlace']").val().Trim()=="") {
		//提示基本信息不完整
		alert("<spring:message code='basicInfo.HK/MacauEntryPlace' />"+"  "+"<spring:message code='basicInfo.lackOfInfo'/>");
		document.getElementById("entryPlace").focus();
		return false;	
	}
	
	if ($("[name='entryNum']").val().Trim()=="") {
		//提示基本信息不完整
		alert("<spring:message code='basicInfo.HK/MacauEntryNum' />"+"  "+"<spring:message code='basicInfo.lackOfInfo'/>");
		document.getElementById("entryNum").focus();
		return false;	
	}
	
	if (entryEndmtDate=="") {
		//提示基本信息不完整
		alert("<spring:message code='basicInfo.HK/MacauEntryEndmtDate' />"+"  "+"<spring:message code='basicInfo.lackOfInfo'/>");
		return false;	
	}
	
	if (entryEndmtValidity=="") {
		//提示基本信息不完整
		alert("<spring:message code='basicInfo.HK/MacauEntryEndmtValidity' />"+"  "+"<spring:message code='basicInfo.lackOfInfo'/>");
		return false;	
	}
	**/
	
	if(entryEndmtDate!="" && entryEndmtValidity!="" && dateLargeThan(entryEndmtDate,entryEndmtValidity)){
		alert("<spring:message code='basicInfo.HK/MacauEntryEndmtDate' /> "+"<spring:message code='basicInfo.timeCheck' />"+" <spring:message code='basicInfo.HK/MacauEntryEndmtValidity' />");
		return false;
	}
	
	/*if ($("[name='entryPic1']").val().Trim()=="") {
		//提示基本信息不完整
		alert("<spring:message code='basicInfo.HK/MacauEntryPic1' />"+"  "+"<spring:message code='basicInfo.lackOfInfo'/>");
		return false;	
	}
	
	if ($("[name='entryPic2']").val().Trim()=="") {
		//提示基本信息不完整
		alert("<spring:message code='basicInfo.HK/MacauEntryPic2' />"+"  "+"<spring:message code='basicInfo.lackOfInfo'/>");
		return false;	
	}*/
	return true;
};

</script>