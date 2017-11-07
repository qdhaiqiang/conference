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
		<spring:message code="basicInfo.Position"/>
	</div>
	<div class="panel-body bs-step-inner">
		<div class="col-sm-8">
			<label class="required-label">*</label> <spring:message code="basicInfo.companyName" />
			<input type="text" class="form-control" maxlength="50" id="company"
				value="${user.company}" name="company">
		</div>
		<div class="col-sm-4 help">
			<h4>
				<span style="color:#891300;"><spring:message
						code="basicInfo.help" /> </span> <span><spring:message
						code="basicInfo.companyName" /> </span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.companyHelp" /> </span>
			</p>
		</div>
		
		<div class="clearfix hide1"></div>
		<div class="col-sm-8 hide1">
			<label class="required-label">*</label> <spring:message code="basicInfo.workContent" />
			<input type="text" class="form-control" maxlength="50" id="workContent"
				value="${user.workContent}" name="workContent">
		</div>
		<div class="col-sm-4 help hide1">
			<h4>
				<span style="color:#891300;">
					<spring:message code="basicInfo.help" /> </span> <span>
					<spring:message code="basicInfo.workContent" /> </span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.workContent" /> </span>
			</p>
		</div>
		
		<div class="clearfix hide1"></div>
		<div class="col-sm-8 hide1">
			<label class="required-label">*</label> <spring:message code="basicInfo.superviserName" />
			<input type="text" class="form-control" maxlength="50" id="superviserName"
				value="${user.superviserName}" name="superviserName">
		</div>
		<div class="col-sm-4 help hide1">
			<h4>
				<span style="color:#891300;"><spring:message
						code="basicInfo.help" /> </span> <span><spring:message
						code="basicInfo.superviserName" /> </span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.superviserName" /> </span>
			</p>
		</div>
		
		<div class="clearfix hide1"></div>
		<div class="col-sm-8 hide1">
			<label class="required-label">*</label> <spring:message code="basicInfo.superviserPhone" />
			<input type="text" class="form-control" maxlength="50" id="superviserPhone"
				value="${user.superviserPhone}" name="superviserPhone">
		</div>
		<div class="col-sm-4 help hide1">
			<h4>
				<span style="color:#891300;"><spring:message
						code="basicInfo.help" /> </span> <span><spring:message
						code="basicInfo.superviserPhone" /> </span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.superviserPhone" /> </span>
			</p>
		</div>
		
		<div class="clearfix hide3"></div>
		<div class="col-sm-8 hide3">
			<label class="required-label">*</label> <spring:message code="basicInfo.position" />
			<input type="text" class="form-control" maxlength="50" id="position"
				value="${user.position}" name="position">
		</div>
		<div class="col-sm-4 help hide3">
			<h4>
				<span style="color:#891300;"><spring:message
						code="basicInfo.help" /> </span> <span><spring:message
						code="basicInfo.position" /> </span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.companyPosition" /> </span>
			</p>
		</div>
		<div class="clearfix" id="last-div-staff"></div>
	</div>
</div>

<script type="text/javascript">



	var check_staff_tab4 = function() {

		var userType = $("#userType").val();
		//组委会单位职务必填
		if (userType == "7" || userType == "18") {
			if( $("[name='company']").val().Trim()==""){
				//提示基本信息不完整
				alert("<spring:message code='basicInfo.companyName'/>"+"  "+"<spring:message code='basicInfo.lackOfInfo'/>");
				return false;
			}
			if( $("[name='position']").val().Trim()==""){
				
				//提示基本信息不完整
				alert("<spring:message code='basicInfo.position'/>"+"  "+"<spring:message code='basicInfo.lackOfInfo'/>");
				return false;
			}
		} else {
			if( $("[name='company']").val().Trim()==""){
				//提示基本信息不完整
				alert("<spring:message code='basicInfo.company'/>"+"  "+"<spring:message code='basicInfo.lackOfInfo'/>");
				return false;
			}
			if( $("[name='workContent']").val().Trim()==""){
				
				//提示基本信息不完整
				alert("<spring:message code='basicInfo.workContent'/>"+"  "+"<spring:message code='basicInfo.lackOfInfo'/>");
				return false;
			}
			if( $("[name='superviserName']").val().Trim()==""){
				//提示基本信息不完整
				alert("<spring:message code='basicInfo.superviserPhone'/>"+"  "+"<spring:message code='basicInfo.lackOfInfo'/>");
				return false;
			}
			if( $("[name='superviserPhone']").val().Trim()==""){
				
				//提示基本信息不完整
				alert("<spring:message code='basicInfo.superviserPhone'/>"+"  "+"<spring:message code='basicInfo.lackOfInfo'/>");
				return false;
			}

			if (userType == "8" || userType == "19") {
				if ($("[name='position']").val().Trim() == "") {

					//提示基本信息不完整
					alert("<spring:message code='basicInfo.position'/>" + "  "
							+ "<spring:message code='basicInfo.lackOfInfo'/>");
					return false;
				}
			}
		}

		return true;
	};
</script>	