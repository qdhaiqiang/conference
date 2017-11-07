<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">
			<spring:message code="basicInfo.contactHeader" />
		</h3>
	</div>
	<div class="panel-body bs-step-inner">

		<!-- 通信地址 -->
		<div class="col-sm-8">
			<label class="required-label">*</label>
			<spring:message code="basicInfo.address" />
			<input type="text" class="form-control" maxlength="100" id="address"
				value="${user.address}" name="address">
		</div>

		<div class="col-sm-4 help">
			<h4>
				<span style="color:#891300;"><spring:message
						code="basicInfo.help" /> </span> <span><spring:message
						code="basicInfo.address" /> </span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.address" /> </span>
			</p>
		</div>

		<div class="clearfix"></div>

		<!-- 邮政编码 -->
		<div class="col-sm-8">
			<!-- <label class="required-label">*</label> -->
			<spring:message code="basicInfo.postCode" />
			<input type="text" class="form-control" maxlength="50" id="postCode"
				value="${user.postcode}" name="postcode">
		</div>

		<div class="col-sm-4 help">
			<h4>
				<span style="color:#891300;"><spring:message
						code="basicInfo.help" /> </span> <span><spring:message
						code="basicInfo.postCode" /> </span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.postCode" /> </span>
			</p>
		</div>

		<div class="clearfix"></div>
		<!-- 固定电话 -->
		<div class="col-sm-8">
			 
			<spring:message code="basicInfo.telephone" />
			<input type="text" class="form-control" maxlength="50" id="label"
				value="${user.tele}" name="tele">
		</div>

		<div class="col-sm-4 help">
			<h4>
				<span style="color:#891300;"><spring:message
						code="basicInfo.help" /> </span> <span><spring:message
						code="basicInfo.telephone" /> </span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.telephone" /> </span>
			</p>
		</div>

		<div class="clearfix"></div>
		<!-- 移动电话 -->
		<div class="col-sm-8">
			<label class="required-label mobile">*</label>
			<spring:message code="basicInfo.mobilePhone" />
			<input type="text" class="form-control" maxlength="50"
				value="${user.mobile}" id="mobile" name="mobile">
		</div>

		<div class="col-sm-4 help">
			<h4>
				<span style="color:#891300;"><spring:message
						code="basicInfo.help" /> </span> <span><spring:message
						code="basicInfo.mobilePhone" /> </span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.mobilePhone" /> </span>
			</p>
		</div>

		<!-- 此处空缺电子邮箱位置 -->
		<div class="clearfix"></div>
		<div class="col-sm-8">
			<spring:message code="basicInfo.email" />
			<input type="text" class="form-control" readonly="readonly"
				name="email" id="email" value="${user.email}">
		</div>
		<div class="col-sm-4 help">
			<h4>
				<span style="color:#891300;"><spring:message
						code="basicInfo.help" /> </span> <span><spring:message
						code="basicInfo.email" /> </span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.email" /> </span>
			</p>
		</div>
		<div class="clearfix"></div>
		<!-- 传真号码 -->
		<div class="col-sm-8">
			 
			<spring:message code="basicInfo.faxNo" />
			<input type="text" class="form-control" maxlength="50" id="fax"
				value="${user.fax}" name="fax">
		</div>

		<div class="col-sm-4 help">
			<h4>
				<span style="color:#891300;"><spring:message
						code="basicInfo.help" /> </span> <span><spring:message
						code="basicInfo.faxNo" /> </span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.faxNo" /> </span>
			</p>
		</div>

		<div class="clearfix"></div>


		<div class="col-sm-8">
			<h4>
				<spring:message code="basicInfo.contactPersonInfo" />
			</h4>
			<input type="radio" name="contactRadio" id="contact-no" value="0"
				checked>
			<spring:message code="NO" />
			&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name=contactRadio
				id="contact-yes" value="1">
			<spring:message code="YES" />
		</div>

		<div class="col-sm-4"></div>
		<div class="clearfix"></div>

		<div id="contact-div">

			<input type="hidden" id="contactPerson" name="contactPerson"
				value="${user.contactPerson}">
			<!--  指定联系人姓名-->
			<div class="col-sm-8">
				<label class="required-label">*</label>
				<spring:message code="basicInfo.name" />
				<input type="text" class="form-control" maxlength="50"
					id="s-full-name" name="s-full-name">
			</div>

			<div class="col-sm-4 help">
				<h4>
					<span style="color:#891300;"><spring:message
							code="basicInfo.help" /> </span> <span><spring:message
							code="basicInfo.name" /> </span>
				</h4>
				<p>
					<span><spring:message code="basicInfo.nameHelp" /> </span>
				</p>
			</div>

			<div class="clearfix"></div>
			<!--指定联系人性别-->
			<div class="col-sm-8">
				<label class="required-label">*</label>
				<spring:message code="basicInfo.Gender" />
				： <input type="radio" name="s-sex" id="s-male" value="1" checked>
				<spring:message code="basicInfo.Male" />
				&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name="s-sex"
					id="s-female" value="2">
				<spring:message code="basicInfo.Female" />
				&nbsp;&nbsp;&nbsp;&nbsp;
			</div>

			<div class="col-sm-4 help">
				<h4>
					<span style="color:#891300;"><spring:message
							code="basicInfo.help" />
					</span> <span><spring:message code="basicInfo.genderHelp" />
					</span>
				</h4>
				<p>
					<span> <spring:message code="basicInfo.genderHelp" />
					</span>
				</p>
			</div>

			<div class="clearfix"></div>

			<!--指定联系人固定电话-->
			<div class="col-sm-8">
				<spring:message code="basicInfo.telephone" />
				<input type="text" class="form-control" maxlength="50"
					id="s-contact-no" name="s-contact-no">
			</div>

			<div class="col-sm-4 help">
				<h4>
					<span style="color:#891300;"><spring:message
							code="basicInfo.help" /> </span> <span><spring:message
							code="basicInfo.telephone" /> </span>
				</h4>
				<p>
					<span><spring:message code="basicInfo.telephoneHelp" /> </span>
				</p>
			</div>

			<div class="clearfix"></div>

			<!--指定联系人移动电话-->
			<div class="col-sm-8">
				<label class="required-label">*</label>
				<spring:message code="basicInfo.mobilePhone" />
				<input type="text" class="form-control" maxlength="50" id="s-mobile"
					name="s-mobile">
			</div>

			<div class="col-sm-4 help">
				<h4>
					<span style="color:#891300;"><spring:message
							code="basicInfo.help" /> </span> <span><spring:message
							code="basicInfo.mobilePhone" /> </span>
				</h4>
				<p>
					<span><spring:message code="basicInfo.mobileHelp" /> </span>
				</p>
			</div>
			<div class="clearfix"></div>

			<!--指定联系人电子信箱-->
			<div class="col-sm-8">
				<spring:message code="basicInfo.email" />
				<input type="text" class="form-control" maxlength="50" id="s-email"
					name="s-email">
			</div>
			<div class="col-sm-4 help">
				<h4>
					<span style="color:#891300;"><spring:message
							code="basicInfo.help" /> </span> <span><spring:message
							code="basicInfo.email" /> </span>
				</h4>
				<p>
					<span><spring:message code="basicInfo.emailHelp" /> </span>
				</p>
			</div>

			<div class="clearfix"></div>

			<!--指定联系人传真-->
			<div class="col-sm-8">
				<spring:message code="basicInfo.faxNo" />
				<input type="text" class="form-control" maxlength="50" id="s-fax-no"
					name="s-fax-no">
			</div>

			<div class="col-sm-4 help">
				<h4>
					<span style="color:#891300;"><spring:message
							code="basicInfo.help" /> </span> <span><spring:message
							code="basicInfo.faxNo" /> </span>
				</h4>
				<p>
					<span><spring:message code="basicInfo.faxHelp" /> </span>
				</p>
			</div>

			<div class="clearfix"></div>

			<!--指定联系人职务-->
			<div class="col-sm-8">
				<spring:message code="basicInfo.Position" />
				<input type="text" class="form-control" maxlength="50"
					id="s-position" name="s-position">
			</div>
			<div class="col-sm-4 help">
				<h4>
					<span style="color:#891300;"><spring:message
							code="basicInfo.help" />
					</span> <span><spring:message code="basicInfo.Position" />
					</span>
				</h4>
				<p>
					<span><spring:message code="basicInfo.contactPosition" />
					</span>
				</p>
			</div>
			<div class="clearfix"></div>

		</div>
	</div>
</div>

<script type="text/javascript"> 
// 

$(document).ready(function(){
	
	
	//处理紧急联系人。若有显示联系人，并填写联系人信息。姓名，移动电话必填
	var sContact = ${user.contactPerson};
	if ($("#contactPerson").val() != "{}") {
		$("#contact-yes").attr("checked", true);
		$("#contact-div").show();
		$("#s-full-name").val(sContact.fullName);
		$("#s-contact-no").val(sContact.contactNo);
		$("#s-email").val(sContact.email);
		$("#s-fax-no").val(sContact.faxNo);
		$("#s-mobile").val(sContact.mobile);
		$("#s-position").val(sContact.position);
		
		if (sContact.sex == "2") {
			$("#s-female").attr("checked", true);
		} else {
			$("#s-male").attr("checked", true);
		}

	} else {
		$("#contact-no").attr("checked", true);
		$("#contact-div").hide();
	}

		//radio控制指定联系人是否显示
		$("[name='contactRadio']").click(function() {
			if (this.value == 1) {
				$("#contact-div").slideDown();
			} else {
				$("#contact-div").slideUp();
				$("#s-full-name").val("");
				$("#s-contact-no").val("");
				$("#s-email").val("");
				$("#s-fax-no").val("");
				$("#s-mobile").val("");
				$("#s-position").val("");
			}
		});
	});

	var check_total_tab2 = function() {
		if ($("[name='address']").val().Trim() == "") {
			//提示基本信息不完整
			alert("<spring:message code='basicInfo.address' />" + "  "
					+ "<spring:message code='basicInfo.lackOfInfo'/>");
			$("[name='address']").focus(function() {
				$("[name='address']").val($("[name='address']").val());
			});
			$("[name='address']").focus();
			return false;
		}

		var postcode = $("[name='postcode']").val().Trim();
		
		/**
		if (postcode == "") {
			//提示基本信息不完整
			alert("<spring:message code='basicInfo.postCode' />" + "  "
					+ "<spring:message code='basicInfo.lackOfInfo'/>");
			$("[name='postcode']").focus(function() {
				$("[name='postcode']").val($("[name='postcode']").val());
			});
			$("[name='postcode']").focus();
			return false;
		}
		**/
		if(postcode != "" && !isNumReg(postcode)){
			alert("<spring:message code='basicInfo.postCode' />"+"  "+"<spring:message code='basicInfo.invalidType' />");
			$("[name='postcode']").focus(function(){  
	            $("[name='postcode']").val($("[name='postcode']").val());  
	        });    
	        $("[name='postcode']").focus();
			return false;
		}

		var tele = $("[name='tele']").val().Trim();
		if(tele!=""&&!isTeleReg(tele)){
			alert("<spring:message code='basicInfo.telephone' />"+"  "+"<spring:message code='basicInfo.invalidType' />");
			$("#tele").focus(function(){  
	            $("[name='tele']").val($("[name='tele']").val());  
	        });    
	        $("[name='tele']").focus();
			return false;
		}
		/*if ($("[name='tele']").val().Trim() == "") {
			//提示基本信息不完整
			alert("<spring:message code='basicInfo.telephone' />" + "  "
					+ "<spring:message code='basicInfo.lackOfInfo'/>");
			$("#tele").focus(function() {
				$("[name='tele']").val($("[name='tele']").val());
			});
			$("[name='tele']").focus();
			return false;
		}*/

		var mobile = $("[name='mobile']").val().Trim();
		
		if (mobile == "") {
			//提示基本信息不完整
			alert("<spring:message code='basicInfo.mobilePhone' />" + "  "
					+ "<spring:message code='basicInfo.lackOfInfo'/>");
			$("[name='mobile']").focus(function() {
				$("[name='mobile']").val($("[name='mobile']").val());
			});
			$("[name='mobile']").focus();
			return false;
		}
		if(!isTeleReg(mobile)){
			alert("<spring:message code='basicInfo.mobilePhone' />"+"  "+"<spring:message code='basicInfo.invalidType' />");
			$("[name='mobile']").focus(function(){  
	            $("[name='mobile']").val($("[name='mobile']").val());  
	        });    
	        $("[name='mobile']").focus();
			return false;
		}

		var fax = $("[name='fax']").val().Trim();
		if(fax!=""&&!isTeleReg(fax)){
			alert("<spring:message code='basicInfo.faxNo' />"+"  "+"<spring:message code='basicInfo.invalidType' />");
			$("[name='fax']").focus(function(){  
	            $("[name='fax']").val($("[name='fax']").val());  
	        });    
	        $("[name='fax']").focus();
			return false;
		}
		/*if ($("[name='fax']").val().Trim() == "") {
			//提示基本信息不完整
			alert("<spring:message code='basicInfo.faxNo' />" + "  "
					+ "<spring:message code='basicInfo.lackOfInfo'/>");
			$("[name='fax']").focus(function() {
				$("[name='fax']").val($("[name='fax']").val());
			});
			$("[name='fax']").focus();
			return false;
		}*/

		//处理指定联系人
		if ($("[name='contactRadio']:checked").val() == 1) {
			if ($("#s-full-name").val().Trim() == "") {
				//提示基本信息不完整
				alert("<spring:message code='basicInfo.nameHelp' />" + "  "
						+ "<spring:message code='basicInfo.lackOfInfo'/>");
				$("#s-full-name").focus(function() {
					$("#s-full-name").val($("#s-full-name").val());
				});
				$("#s-full-name").focus();
				return false;
			}

			var smobile = $("#s-mobile").val().Trim();
			
			if (smobile == "") {
				//提示基本信息不完整
				alert("<spring:message code='basicInfo.mobileHelp' />" + "  "
						+ "<spring:message code='basicInfo.lackOfInfo'/>");
				$("#s-mobile").focus(function() {
					$("#s-mobile").val($("#s-mobile").val());
				});
				$("#s-mobile").focus();
				return false;
			}
			
			if(!isTeleReg(smobile)){
				alert("<spring:message code='basicInfo.mobilePhone' />"+"  "+"<spring:message code='basicInfo.invalidType' />");
				$("#s-mobile").focus(function() {
					$("#s-mobile").val($("#s-mobile").val());
				});
				$("#s-mobile").focus();
				return false;
			}
			
			
			var scontactno = $("#s-contact-no").val().Trim();
			if (scontactno!= ""&&!isTeleReg(scontactno)) {
				//提示基本信息不完整
				alert("<spring:message code='basicInfo.telephoneHelp' />" + "  "
						+ "<spring:message code='basicInfo.invalidType'/>");
				$("#s-contact-no").focus(function() {
					$("#s-contact-no").val($("#s-contact-no").val());
				});
				$("#s-contact-no").focus();
				return false;
			}
			
			var sfaxno = $("#s-fax-no").val().Trim();
			if (sfaxno != ""&&!isTeleReg(sfaxno)) {
				//提示基本信息不完整
				alert("<spring:message code='basicInfo.faxHelp' />" + "  "
						+ "<spring:message code='basicInfo.invalidType'/>");
				$("#s-fax-no").focus(function() {
					$("#s-fax-no").val($("#s-fax-no").val());
				});
				$("#s-fax-no").focus();
				return false;
			}

			
			var semail = $("#s-email").val().Trim();
			if(semail!=""&&!isEmailReg(semail)){
				alert("<spring:message code='basicInfo.emailHelp' />" + "  "
						+ "<spring:message code='basicInfo.invalidType'/>");
				$("#s-email").focus(function() {
					$("#s-email").val($("#s-email").val());
				});
				$("#s-email").focus();
				return false;
			}
			
			var contactPerson = "{fullName:'" + $("#s-full-name").val()
					+ "',position:'" + $("#s-position").val() + "',sex:'"
					+ $("[name='s-sex']:checked").val() + "',contactNo:'"
					+ $("#s-contact-no").val() + "',email:'"
					+ $("#s-email").val() + "',faxNo:'" + $("#s-fax-no").val()
					+ "',mobile:'" + $("#s-mobile").val() + "'}";
			$("#contactPerson").val(contactPerson);
		} else {
			$("#contactPerson").val("{}");
		}
		return true;
	};
</script>
