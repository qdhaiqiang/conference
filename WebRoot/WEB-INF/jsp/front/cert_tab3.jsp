<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
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
			<spring:message code="basicInfo.certificationType" /><br />
			<input id="certType" class="easyui-combobox" name="certType" editable="false" value="${user.certType}" data-options="valueField:'code',textField:'name',url:'dict/r/cert_type',method:'get',required:'true',onChange:changeimage" />
		</div>
		<div class="col-sm-4 help">
			<h4>
				<span style="color:#891300;"><spring:message code="basicInfo.help" /></span>
				<span><spring:message code="basicInfo.certificationType" /></span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.certificationTypeHelp" /></span>
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

		<!-- 正面照 -->
		<div class="col-sm-8" id="pic1fileuploaddiv">
			<p>
				<label class="required-label">*</label>
				<spring:message code="basicInfo.frontSidePhoto" />
			</p>
			<div id="frontfilelist"></div>
			<div id="frontimgfileshow">
				<img src="<%=basePath%>${user.certPic1}" alt="image">
			</div>
			<a id="frontbrowse" href="javascript:;">[<spring:message code="basicInfo.selectFile" />]</a> 
			<input id="frontinput" name="certPic1" value="${user.certPic1}" type="hidden">
		</div>
		<div class="col-sm-4 help">
			<h4>
				<span style="color:#891300;"><spring:message code="basicInfo.help" /></span>
				<span><spring:message code="basicInfo.frontSidePhoto" /></span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.frontSidePhotoHelp" /></span><br>
				<img id="pic1photoexample" src="<%=basePath%>images/photoexamples/idcard1.jpg" onload="loadImage(this,'pic1photoexample')" width=0 height=0>
			</p>
		</div>
		<div class="clearfix"></div>

		<!-- 反面照 -->
		<div class="col-sm-8" id="pic2fileuploaddiv">
			<p>
				<label class="required-label">*</label>
				<spring:message code="basicInfo.backSidePhoto" />
			</p>
			<div id="backfilelist"></div>
			<div id="backimgfileshow">
				<img src="<%=basePath%>${user.certPic2}" alt="image">
			</div>
			<a id="backbrowse" href="javascript:;">[<spring:message code="basicInfo.selectFile" />]</a> 
			<input id="backinput" name="certPic2" value="${user.certPic1}" type="hidden">
		</div>
		<div class="col-sm-4 help">
			<h4>
				<span style="color:#891300;"><spring:message code="basicInfo.help" /></span>
				<span><spring:message code="basicInfo.backSidePhoto" /></span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.backSidePhotoHelp" /></span><br>
				<img id="pic2photoexample" src="<%=basePath%>images/photoexamples/idcard2.jpg" onload="loadImage(this,'pic2photoexample')" width=0 height=0>
			</p>
		</div>
		<div class="clearfix"></div>
	</div>
</div>

<script type="text/javascript"> 
var check_tab3 = function() {
	//检查必填项 
	if ($("[name='certType']").val()=="") {
		//提示基本信息不完整
		alert("<spring:message code='basicInfo.certificationType' />"+"  "+"<spring:message code='basicInfo.lackOfInfo'/>");
		return false;	
	}
	
	if ($("[name='certValue']").val()=="") {
		//提示基本信息不完整
		alert("<spring:message code='basicInfo.certificationNo' />"+"  "+"<spring:message code='basicInfo.lackOfInfo'/>");
		return false;	
	}
	
	if ($("[name='certPic1']").val()=="") {
		//提示基本信息不完整
		alert("<spring:message code='basicInfo.frontSidePhoto' />"+"  "+"<spring:message code='basicInfo.lackOfInfo'/>");
		return false;	
	}
	
	if ($("[name='certPic2']").val()=="") {
		//提示基本信息不完整
		alert("<spring:message code='basicInfo.backSidePhoto' />"+"  "+"<spring:message code='basicInfo.lackOfInfo'/>");
		return false;	
	}
	
	return true;
};
	
function changeimage(){
	var type = $("#certType").combobox("getValue");
	//有效证件类型：（中华人民共和国身份证、护照、港澳台地区身份证、军官证），示例图片会改变；pic2photoexample pic1photoexample
	$("#pic1photoexample").attr("src","<%=basePath%>images/photoexamples/idcard1.jpg");
	$("#pic2photoexample").attr("src","<%=basePath%>images/photoexamples/idcard2.jpg");
	
	/*if(type == "1"){
		$("#pic1photoexample").attr("src","<%=basePath%>images/photoexamples/idcard.jpg");
		$("#pic2photoexample").attr("src","<%=basePath%>images/photoexamples/idcard.jpg");
	}else if(type == "2"){
		$("#pic1photoexample").attr("src","<%=basePath%>images/photoexamples/passport.jpg");
		$("#pic2photoexample").attr("src","<%=basePath%>images/photoexamples/passport.jpg");
	}else if(type == "3"){
		$("#pic1photoexample").attr("src","<%=basePath%>images/photoexamples/regionalidcard.jpg");
		$("#pic2photoexample").attr("src","<%=basePath%>images/photoexamples/regionalidcard.jpg");
	}else if(type == "3"){
		$("#pic1photoexample").attr("src","<%=basePath%>images/photoexamples/officer.jpg");
		$("#pic2photoexample").attr("src","<%=basePath%>images/photoexamples/officer.jpg");
	}*/
}

var fileuploadurl ="<%=basePath%>fileload/c/upload";
//browse bannerPic filelist imgfileshow,上传图片的附加参数,sign需要修改成会议的唯一标识
//有效证件类型：（中华人民共和国身份证、护照、港澳台地区身份证、军官证），证件正反面照
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
initUploader("frontbrowse","frontfilelist","frontimgfileshow","frontinput","pic1fileuploaddiv",fileuploadurl,false,400,250,true,pic1_params);	
 
initUploader("backbrowse","backfilelist","backimgfileshow","backinput","pic2fileuploaddiv",fileuploadurl,false,400,250,true,pic2_params);	

</script>
