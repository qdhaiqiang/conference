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
			<label class="required-label">*</label><spring:message code="basicInfo.address" />
			<input type="text" class="form-control" maxlength="100" id="label"
				value="${user.address}" name="address">
		</div>

		<div class="col-sm-4 help">
			<h4>
				<span style="color:#891300;"><spring:message
						code="basicInfo.help" />
				</span> <span><spring:message code="basicInfo.address" />
				</span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.address" />
				</span>
			</p>
		</div>

		<div class="clearfix"></div>
		
		<!-- 邮政编码 -->
		<div class="col-sm-8">
			<!-- <label class="required-label">*</label> -->
			<spring:message code="basicInfo.postCode" />
			<input type="text" class="form-control" maxlength="50" id="label"
				value="${user.postcode}" name=postcode>
		</div>

		<div class="col-sm-4 help">
			<h4>
				<span style="color:#891300;"><spring:message
						code="basicInfo.help" />
				</span> <span><spring:message code="basicInfo.postCode" /> </span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.postCode" />
				</span>
			</p>
		</div>

		<div class="clearfix"></div>
	<!-- 固定电话 -->
		<div class="col-sm-8">
			<spring:message code="basicInfo.telephone" />
			<input type="text" class="form-control" maxlength="50" id="label" onblur="isTeleReg(this.value)" style="ime-mode:disabled"
				value="${user.tele}" name="tele">
		</div>

		<div class="col-sm-4 help">
			<h4>
				<span style="color:#891300;"><spring:message
						code="basicInfo.help" />
				</span> <span><spring:message code="basicInfo.telephone" /> </span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.telephone" />
				</span>
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
						code="basicInfo.help" />
				</span> <span><spring:message code="basicInfo.mobilePhone" />
				</span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.mobilePhone" />
				</span>
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
						code="basicInfo.help" />
				</span> <span><spring:message code="basicInfo.faxNo" />
				</span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.faxNo" />
				</span>
			</p>
		</div>

		<div class="clearfix"></div>
	

	</div>
</div>

<script type="text/javascript">
	
	$(document).ready(function(){
	 
	});
	 
	var check_tab2 = function(){
		//检查必填项
		var userType = $("#userType").val();
		var mobile = $("[name='mobile']").val().Trim();
		
		if(!isTeleReg(mobile)){
			alert("<spring:message code='basicInfo.mobilePhone' />"+"  "+"<spring:message code='basicInfo.invalidType' />");
			$("[name='mobile']").focus(function(){  
	            $("[name='mobile']").val($("[name='mobile']").val());  
	        });    
	        $("[name='mobile']").focus();
			return false;
		}
		
		if(userType=="23"||userType=="24"){ //配偶只检查移动电话
			
			if( mobile==""){
				//提示基本信息不完整
				alert("<spring:message code='basicInfo.mobilePhone' />"+"  "+"<spring:message code='basicInfo.lackOfInfo'/>");
				$("[name='mobile']").focus(function(){  
		            $("[name='mobile']").val($("[name='mobile']").val());  
		        });    
		        $("[name='mobile']").focus();
				return false;
			}
		}else{
			if ($("[name='address']").val().Trim()=="") {
				//提示基本信息不完整
				alert("<spring:message code='basicInfo.address' />"+"  "+"<spring:message code='basicInfo.lackOfInfo'/>");
				$("[name='address']").focus(function(){  
		            $("[name='address']").val($("[name='address']").val());  
		        });    
		        $("[name='address']").focus();
				return false;	
			}
			
			var postcode = $("[name='postcode']").val().Trim();
			if(postcode != "" && !isNumReg(postcode)){
				alert("<spring:message code='basicInfo.postCode' />"+"  "+"<spring:message code='basicInfo.invalidType' />");
				$("[name='postcode']").focus(function(){  
		            $("[name='postcode']").val($("[name='postcode']").val());  
		        });    
		        $("[name='postcode']").focus();
				return false;
			}
			
			/**
			if (postcode=="") {
				//提示基本信息不完整
				alert("<spring:message code='basicInfo.postCode' />"+"  "+"<spring:message code='basicInfo.lackOfInfo'/>");
				$("[name='postcode']").focus(function(){  
		            $("[name='postcode']").val($("[name='postcode']").val());  
		        });    
		        $("[name='postcode']").focus();
				return false;	
			}
			**/
			
			var tele = $("[name='tele']").val().Trim();
			if(tele!=""&&!isTeleReg(tele)){
				alert("<spring:message code='basicInfo.telephone' />"+"  "+"<spring:message code='basicInfo.invalidType' />");
				$("#tele").focus(function(){  
		            $("[name='tele']").val($("[name='tele']").val());  
		        });    
		        $("[name='tele']").focus();
				return false;
			}
			/*if (tele=="") {
				//提示基本信息不完整
				alert("<spring:message code='basicInfo.telephone' />"+"  "+"<spring:message code='basicInfo.lackOfInfo'/>");
				$("#tele").focus(function(){  
		            $("[name='tele']").val($("[name='tele']").val());  
		        });    
		        $("[name='tele']").focus();
				return false;	
			}*/
			
			if (mobile=="") {
				//提示基本信息不完整
				alert("<spring:message code='basicInfo.mobilePhone' />"+"  "+"<spring:message code='basicInfo.lackOfInfo'/>");
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
			/*if (fax=="") {
				//提示基本信息不完整
				alert("<spring:message code='basicInfo.faxNo' />"+"  "+"<spring:message code='basicInfo.lackOfInfo'/>");
				$("[name='fax']").focus(function(){  
		            $("[name='fax']").val($("[name='fax']").val());  
		        });    
		        $("[name='fax']").focus();
				return false;	
			}*/
		}
		return true;	
		
	};
	
	

</script>