<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">
			<spring:message code="basicInfo.otherInfo" />
		</h3>
	</div>
	<div class="panel-body bs-step-inner">
		<div class="col-sm-8">
			<p>
				<spring:message code="basicInfo.showRealName" />
			</p>
			<input type="radio" name="useRealname" id="use_realName_yes"
				value="1">
			<spring:message code="YES" />
			&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name="useRealname"
				id="use_realName_no" value="0" checked>
			<spring:message code="NO" />
		</div>
		<div class="col-sm-4 help">
			<h4>
				<span style="color:#891300;"><spring:message
						code="basicInfo.help" />
				</span> <span><spring:message code="basicInfo.RealName" />
				</span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.showRealName" />
				</span>
			</p>
		</div>

		<div class="col-sm-8">
			<p>
				<spring:message code="basicInfo.useAlias" />
			</p>
			<input type="radio" name="useAlias" id="use_alias_yes" value="1">
			<spring:message code="YES" />
			&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name="useAlias"
				id="use_alias_no" value="0" checked>
			<spring:message code="NO" />
			<div id="ualias-div">
				<p>
					<spring:message code="basicInfo.inputAlias" />
				</p>
				<input type="text" class="form-control" maxlength="50" id="ualias"
					name="ualias" value="${user.ualias}">
			</div>
		</div>
		<div class="col-sm-4 help" style="height: 120px">
			<h4>
				<span style="color:#891300;"><spring:message
						code="basicInfo.help" />
				</span> <span><spring:message code="basicInfo.alias" />
				</span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.aliasHelp" />
				</span>
			</p>
		</div>
		<div class="col-sm-8">
			<p>
				<spring:message code="basicInfo.useOtherPositionTitle" />
			</p>
			<input type="radio" name="useOtherPtitle" id="use_other_ptitle_yes"
				value="1">
			<spring:message code="YES" />
			&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name="useOtherPtitle"
				id="use_other_ptitle_no" value="0" checked>
			<spring:message code="NO" />
			<div id="otherTitle-div">
				<p>
					<spring:message code="basicInfo.inputPositionTitle" />
				</p>
				<input type="text" class="form-control" maxlength="50"
					id="positionTitle" name="positionTitle"
					value="${user.positionTitle}">
			</div>
		</div>
		<div class="col-sm-4 help" style="height: 120px">
			<h4>
				<span style="color:#891300;"><spring:message
						code="basicInfo.help" />
				</span> <span><spring:message code="basicInfo.positionTitle" />
				</span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.inputPositionTitle" />
				</span>
			</p>
		</div>
		<div class="col-sm-8">
			<div id="remark-div">
				<p>
					<spring:message code="basicInfo.remarks" />
				</p>
				<textarea rows="4" style="width: 90%;display:block" name="remark"
					class="max-len-100">${user.remark}</textarea>
			</div>
		</div>
		<div class="col-sm-4 help" style="height: 120px">
			<h4>
				<span style="color:#891300;"><spring:message
						code="basicInfo.help" />
				</span> <span><spring:message code="basicInfo.remarks" />
				</span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.remarksHelp" />
				</span>
			</p>
		</div>

		<div class="clearfix"></div>


		<button id="last-back" type="button" class="btn btn-default">
			<spring:message code="goBack" />
		</button>
		<button id="btn-basic-submit" type="button"
			class="btn btn-primary submit-btn">
			<spring:message code="Submit" />
		</button>

	</div>
</div>


<script type="text/javascript">

$(document).ready(function(){
	
	$("#ualias").attr("readonly","readonly");
	$("#positionTitle").attr("readonly","readonly");
	
	var useAlias = '${user.useAlias}';
	if (useAlias==1) {
		//填写了此字段，选择yes是
		$("#use_alias_yes").attr("checked", true);
		$("#ualias").removeAttr("readonly");
	}else{
		$("#use_alias_no").attr("checked", true);
		$("#ualias").attr("readonly","readonly");
	}
	
	var useOtherPtitle = '${user.useOtherPtitle}';
	if (useOtherPtitle==1) {
		//填写了此字段，选择yes是
		$("#use_other_ptitle_yes").attr("checked", true);
		$("#positionTitle").removeAttr("readonly");
	}else{
		$("#use_other_ptitle_no").attr("checked", true);
		$("#positionTitle").attr("readonly","readonly");
	}
	
	$("[name='useAlias']").click(function() {
		if (this.value == 1) {
			$("#ualias").removeAttr("readonly");
		} else {
			$("#ualias").attr("readonly","readonly");
			
			$("#ualias").val("");
		}
	});

	$("[name='useOtherPtitle']").click(function() {
		if (this.value == 1) {
			$("#positionTitle").removeAttr("readonly");
		} else {
			$("#positionTitle").attr("readonly","readonly");
			$("#positionTitle").val("");
		}
	});
	
});

var check_tab7 = function(){
	
	if($("[name='useAlias']:checked").val() == 1 && $("[name='ualias']").val()=="") {

		alert("<spring:message code='basicInfo.inputAlias' />"+"  "+"<spring:message code='basicInfo.lackOfInfo'/>");		
		return false;
		
	}
	if($("[name='useOtherPtitle']:checked").val() == 1 && $("[name='positionTitle']").val()=="") {
		 
		alert("<spring:message code='basicInfo.useOtherPositionTitle' />"+"  "+"<spring:message code='basicInfo.lackOfInfo'/>");		
		return false;	 
	}
	
	return true;
};


</script>