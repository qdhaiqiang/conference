<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="panel panel-default">
	<div class="panel-heading panel-bar">
		<h3 class="panel-title" style="color:#fff">
			<spring:message code="basicInfo.fillBasicInfo" />
		</h3>
	</div>
	<div class="panel-body bs-step-inner">

	<div class="clearfix" style="padding-top:10px;"></div>
		<!-- 姓名全称 -->
		<div class="col-sm-8" id="cname-div">
			<label class="required-label">*</label><spring:message code="basicInfo.ChineseName" />
			<input type="text" class="form-control" maxlength="50" id="cname" value="${user.cname}" name="cname">
		</div>
		<div class="col-sm-4 help">
			<h4>
				<span style="color:#891300;"><spring:message code="basicInfo.help" /></span> 
				<span><spring:message code="basicInfo.ChineseName" /></span>
			</h4>
			<p>
				<span><spring:message code="staffInfo.ChineseNameIntro" /></span>
			</p>
		</div>
		<div class="clearfix"></div>
		
		<!-- 英文名 -->
		<div class="col-sm-8">
			<br>
			<spring:message code="basicInfo.englishName" />
			<input type="text" class="form-control" id="ename" maxlength="50" value="${user.ename}" name="ename">
		</div>
		<div class="col-sm-4 help">
			<h4>
				<span style="color:#891300;"><spring:message code="basicInfo.help" /></span>
				<span><spring:message code="basicInfo.englishName" /></span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.surnameHelp" /> </span>
			</p>
		</div>
		<div class="clearfix"></div>
		
		<!-- 姓&名 -->
		<div class="col-sm-8">
			<label class="required-label">*</label>
			<spring:message code="basicInfo.firstName" />
			<input type="text" class="form-control" id="firstname" maxlength="50" value="${user.firstName}" name="firstName">
			<label class="required-label">*</label>
			<spring:message code="basicInfo.lastName" />
			<input type="text" class="form-control" id="lastname" maxlength="50" value="${user.lastName}" name="lastName">
		</div>
		<div class="col-sm-4 help">
			<h4>
				<span style="color:#891300;"><spring:message code="basicInfo.help" /></span>
				<span><spring:message code="basicInfo.firstName" /></span>&amp;<span><spring:message code="basicInfo.lastName" /></span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.LFname" /></span>
			</p>
		</div>
		<div class="clearfix"></div>
		
		<!-- 密码 -->
		<div class="col-sm-8 password-div">
			<p>
				<label class="required-label">*</label>
				<spring:message code="enterPassword" />
				<input type="password" name="password" id="password1" value="${user.password}" class="form-control" maxlength="50">
			</p>
			<p>
				<label class="required-label">*</label>
				<spring:message code="Repeat" />
				<input type="password" id="password2" class="form-control" value="${user.password}">
			</p>
			<label class="pw-label error"><spring:message code="pwdnotmatch" /></label>
		</div>
		<div class="col-sm-4 help password-div">
			<h4>
				<span style="color:#891300;"><spring:message code="basicInfo.help" /></span>
				<span><spring:message code="enterPassword" /></span>
			</h4>
			<p>
				<span><spring:message code="passwordHelp" /> </span>
			</p>
		</div>
		<div class="clearfix"></div>
		
		<!-- 性别 -->
		<div class="col-sm-8" style="padding-top:20px">
			<label class="required-label">*</label>
			<spring:message code="basicInfo.Gender" /><br /> 
			<input type="radio" name="sex" id="male" value="1" checked><spring:message code="basicInfo.Male" />&nbsp;&nbsp;&nbsp;&nbsp; 
			<input type="radio" name="sex" id="female" value="2"><spring:message code="basicInfo.Female" /><br />
		</div>
		<div class="col-sm-4 help">
			<h4>
				<span style="color:#891300;"><spring:message code="basicInfo.help" /></span> 
				<span><spring:message code="basicInfo.genderHelp" /></span>
			</h4>
			<p>
				<span> <spring:message code="basicInfo.genderHelp" /> </span>
			</p>
		</div>
		<div class="clearfix"></div>
		
		<!-- 出生日期  -->
		<div class="col-sm-8">
			<p>
				<label class="required-label">*</label>
				<spring:message code="basicInfo.birth" /><br />
				<input id="birth" name="birth" value="${user.birth}" class="easyui-datebox" data-options="formatter:dateFormatter,parser:dateParser" editable="false" >
			</p>
		</div>
		<div class="col-sm-4 help">
			<h4>
				<span style="color:#891300;"><spring:message code="basicInfo.help" /></span>
				<span><spring:message code="basicInfo.birthHelp" /></span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.birthDesc" /></span>
			</p>
		</div>
		<div class="clearfix"></div>
		
		<!-- 所属国家 -->
		<div class="col-sm-8">
			<label class="required-label">*</label>
			<spring:message code="basicInfo.nation" /><br />
			<input id="nation" class="easyui-combobox" name="nation" editable="false" style="width:300px" value="${user.nation}" data-options="valueField:'code',textField:'name', url:'dict/r/nation',method:'get',required:'true'" />
		</div>
		<div class="col-sm-4 help">
			<h4>
				<span style="color:#891300;"><spring:message code="basicInfo.help" /></span>
				<span><spring:message code="basicInfo.nationHelp" /></span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.nationDesc" /> </span>
			</p>
		</div>
		<div class="clearfix"></div>
		
		<!-- 照片 -->
		<div id="fileuploaddiv" class="col-sm-8 photo-div">
			<p>
				<label class="required-label">*</label>
				<spring:message code="basicInfo.photo" />
				<spring:message code="basicInfo.imgType" />
			</p>
			<div id="photofilelist"></div>
			<div id="photoimgfileshow">
				<img src="<%=basePath%>${user.photo}" alt="image">
			</div>
			<a id="photobrowse" href="javascript:;">[<spring:message code="basicInfo.selectFile" />]</a>
			<input id="photoinput" name="photo" value="${user.photo}" type="hidden">
		</div>

		<div class="col-sm-4 help photo-div">
			<h4>
				<span style="color:#891300;"><spring:message code="basicInfo.help" /> </span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.photoHelp" /> </span>
				<br><img id="photoexample" onload="loadImage(this,'photoexample')" src="<%=basePath%>images/photoexamples/photo.jpg" width=0 height=0>
			</p>
		</div>
		<div class="clearfix"></div>
	</div>
</div>

<script type="text/javascript" src="<%=basePath%>js/fileuploaduser.js"></script>
<script src="<%=basePath%>js/plupload.full.min.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	$(".pw-label").hide();
	//新注册用户不能修改密码
	if($("#id").val()==""){
		$("#updatePwd").attr("style","display:none");
	} 

	var sex = '${user.sex}';
	if (sex != "") {
		if (sex == '2') {
			$("#female").attr("checked", true);

		} else {
			$("#male").attr("checked", true);
		}
	}

	//判断密码非空，相等。若id非空，则该用户为再次登录，不显示密码框。
	var id = $("#id").val();
	if (id != "") {
		$(".password-div").hide();
	}

	$(".pw-label").hide();
	//两密码一致
	$("#password2").blur(function() {
		var pw1 = $("#password1").val().LTrim().RTrim();
		var pw2 = $("#password2").val().LTrim().RTrim();
		if(pw1.Trim()==""){
			$(".pw-label").text("<spring:message code='basicInfo.passwordEmpty'/>");
			$(".pw-label").show();
			return;
		}
		if (pw1!=pw2) {
			$(".pw-label").text("<spring:message code='pwdnotmatch'/>");
			$(".pw-label").show();
			$("#password2").val("");
			return;
		} 
		$(".pw-label").hide();
	});
	
	//主动触发onload事件
	$("#photoexample").attr("src","<%=basePath%>images/photoexamples/photo.jpg");
	
	//参会观众不上传照片
	var userTpye = $("#userType").val();
	if(userTpye=="4"||userTpye=="15"){
		$(".photo-div").hide();
	}
});

	var fileuploadurl ="<%=basePath%>fileload/c/upload";
	//browse bannerPic filelist imgfileshow,上传图片的附加参数,sign需要修改成会议的唯一标识
	var multipart_params = {
		optiontype:"user",
		sign:"${user.email}",
		filecategory:'photo',
	};
	initUploader("photobrowse","photofilelist","photoimgfileshow","photoinput","fileuploaddiv",fileuploadurl,false,390,567,true,multipart_params);			

function loadImage(obj,divid){
	var img = new Image();
	img.src = obj.src;
	$("#"+divid).attr("width",img.width/2);
	$("#"+divid).attr("height",img.height/2);
}
var check_tab1 = function(){
	if ($("[name='cname']").val().Trim()=="") {
		//提示基本信息不完整
		alert("<spring:message code='basicInfo.ChineseName' />"+"  "+"<spring:message code='basicInfo.lackOfInfo'/>");
		$("#cname").focus(function(){  
            $("#cname").val($("#cname").val());  
        });    
        $("#cname").focus();
		return false;	
	}
	
	/*if ($("[name='ename']").val().Trim()=="") {
		//提示基本信息不完整
		alert("<spring:message code='basicInfo.englishName' />"+"  "+"<spring:message code='basicInfo.lackOfInfo'/>");
		$("#ename").focus(function(){  
            $("#ename").val($("#ename").val());  
        });    
        $("#ename").focus();
		return false;	
	}*/

	if ($("[name='firstName']").val().Trim()=="") {
		//提示基本信息不完整
		alert("<spring:message code='basicInfo.firstName' />"+"  "+"<spring:message code='basicInfo.lackOfInfo'/>");
		$("#firstname").focus(function(){  
            $("#firstname").val($("#firstname").val());  
        });    
        $("#firstname").focus();
		return false;	
	}
	
	if ($("[name='lastName']").val().Trim()=="") {
		//提示基本信息不完整
		alert("<spring:message code='basicInfo.lastName' />"+"  "+"<spring:message code='basicInfo.lackOfInfo'/>");
		$("#lastname").focus(function(){  
            $("#lastname").val($("#lastname").val());  
        });    
        $("#lastname").focus();
		return false;	
	}
	
	var pw1 = $("#password1").val().LTrim().RTrim();
	var pw2 = $("#password2").val().LTrim().RTrim();
	if(pw2==""&&$("#id").val()==""){
		alert("<spring:message code='basicInfo.passwordEmpty'/>");
		$("#password1").focus(function(){  
            $("#password1").val($("#password1").val());  
        });    
        $("#password1").focus();
		return false;
	}

	if ($("[name='birth']").val()=="") {
		//提示基本信息不完整
		alert("<spring:message code='basicInfo.birth' />"+"  "+"<spring:message code='basicInfo.lackOfInfo'/>");
		return false;	
	}
	
	if ($("[name='nation']").val().Trim()=="") {
		//提示基本信息不完整
		alert("<spring:message code='basicInfo.nation' />"+"  "+"<spring:message code='basicInfo.lackOfInfo'/>");
		return false;
	}
	
	var userTpye = $("#userType").val();
	if(userTpye!="4" && userTpye!="15"){
		if($("[name='photo']").val()==""){
			 alert("<spring:message code='basicInfo.photo' />"+"  "+"<spring:message code='basicInfo.lackOfInfo'/>");
			 return false;
		} 
	}
	
	return true;	
};
</script>