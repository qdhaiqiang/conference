<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">
			<spring:message code="basicInfo.additional" />
		</h3>
	</div>
	<div class="panel-body bs-step-inner">

		<div class="col-sm-8">
			<p>
				<spring:message code="basicInfo.religion" />
			</p>
			<input type="radio" name="religionNeed" id="religionNeed-yes"
				value="1" checked>
			<spring:message code="YES" />
			&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name="religionNeed"
				id="religionNeed-no" value="0">
			<spring:message code="NO" />
			<div id="religiondiv">
				<input type="text" class="form-control" maxlength="50" id="religion"
					name="religion" value="${user.religion}">
			</div>

		</div>

		<div class="col-sm-4 help">
			<h4>
				<span style="color:#891300;"> <spring:message
						code="basicInfo.help" /> </span> <span> <spring:message
						code="basicInfo.religion" />
				</span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.religionHelp" />
				</span>
			</p>
		</div>

		<div class="clearfix"></div>

		<div class="col-sm-8">
			<p>
				<spring:message code="basicInfo.dietTaboo" />
			</p>
			<input type="radio" name="dietTabooNeed" id="dietTabooNeed-yes"
				value="1" checked>
			<spring:message code="YES" />
			&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name="dietTabooNeed"
				id="dietTabooNeed-no" value="0">
			<spring:message code="NO" />
			<div id="dietTaboodiv">
				<input type="text" class="form-control" maxlength="50"
					id="dietTaboo" name="dietTaboo" value="${user.dietTaboo}">
			</div>
		</div>

		<div class="col-sm-4 help">
			<h4>
				<span style="color:#891300;"><spring:message
						code="basicInfo.help" />
				</span> <span><spring:message code="basicInfo.dietTaboo" />
				</span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.dietTabooHelp" />
				</span>
			</p>
		</div>

		<div class="clearfix"></div>

		<div class="col-sm-8">
			<p>
				<spring:message code="basicInfo.medicalhis" />
			</p>
			<input type="radio" name="medicalhisNeed" id="medicalhisNeed-yes"
				value="1" checked>
			<spring:message code="YES" />
			&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name="medicalhisNeed"
				id="medicalhisNeed-no" value="0">
			<spring:message code="NO" />
			<div id="medlHisdiv">
				<input type="text" class="form-control" maxlength="50" id="medlHis"
					name="medlHis" value="${user.medlHis}">
			</div>
		</div>

		<div class="col-sm-4 help">
			<h4>
				<span style="color:#891300;"><spring:message
						code="basicInfo.help" />
				</span> <span><spring:message code="basicInfo.medicalhis" />
				</span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.medicalHelp" />
				</span>
			</p>
		</div>

		<div class="clearfix"></div>

		<div class="col-sm-8">
			<p>
				<spring:message code="basicInfo.foodallergies" />
			</p>
			<input type="radio" name="foodallergiesNeed"
				id="foodallergiesNeed-yes" value="1" checked>
			<spring:message code="YES" />
			&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name="foodallergiesNeed"
				id="foodallergiesNeed-no" value="0">
			<spring:message code="NO" />
			<div id="foodallergiesdiv">
				<input type="text" class="form-control" maxlength="50"
					id="foodallergies" name="foodAllergies"
					value="${user.foodAllergies}">
			</div>
		</div>

		<div class="col-sm-4 help">
			<h4>
				<span style="color:#891300;"><spring:message
						code="basicInfo.help" />
				</span> <span><spring:message code="basicInfo.foodallergies" />
				</span>
			</h4>
			<p>
				<span><spring:message code="basicInfo.foodallergiesHelp" />
				</span>
			</p>
		</div>

		<div class="clearfix" id="last_entourage_div" style="padding-bottom:20px"></div>

	</div>
</div>

<script type="text/javascript">

$(document).ready(function(){
	//处理radioname: religionNeed dietTabooNeed medicalhisNeed foodallergiesNeed
	// ${user.religion} ${user.dietTaboo} ${user.medlHis} ${user.foodAllergies}
	//div: religion  dietTaboo medlHis  foodallergies
	$("[name='religionNeed']").click(function() {
		if (this.value == 1) {
			$("#religion").removeAttr("readonly");
		} else {
			$("#religion").val("");
			$("#religion").attr("readonly","readonly");//$("#religion").removeAttr("readonly");
		}
	});
	$("[name='dietTabooNeed']").click(function() {
		if (this.value == 1) {
			$("#dietTaboo").removeAttr("readonly");//$("#religion").attr("readonly","readonly");
		} else {
			$("#dietTaboo").val("");
			$("#dietTaboo").attr("readonly","readonly");
		}
	});
	$("[name='medicalhisNeed']").click(function() {
		if (this.value == 1) {
			$("#medlHis").removeAttr("readonly");//$("#medlHis").attr("readonly","readonly");
		} else {
			$("#medlHis").val("");
			$("#medlHis").attr("readonly","readonly");
		}
	});
	$("[name='foodallergiesNeed']").click(function() {
		if (this.value == 1) {
			$("#foodallergies").removeAttr("readonly");//$("#foodallergies").attr("readonly","readonly");
		} else {
			$("#foodallergies").val("");
			$("#foodallergies").attr("readonly","readonly");
		}
	});
	//处理radioname: religionNeed dietTabooNeed medicalhisNeed foodallergiesNeed
	// ${user.religion} ${user.dietTaboo} ${user.medlHis} ${user.foodAllergies}
	//div: religion  dietTaboo medlHis  foodallergies
	var religionNeed = '${user.religion}';
	if (religionNeed) {
		//填写了此字段，选择yes是
		$("#religionNeed-yes").attr("checked", true);
	}else{
		$("#religionNeed-no").attr("checked", true);
		$("#religion").attr("readonly","readonly");
		//$("#religiondiv").hide();
	}
	
	var dietTabooNeed = '${user.dietTaboo}';
	if (dietTabooNeed) {
		//填写了此字段，选择yes是
		$("#dietTabooNeed-yes").attr("checked", true);
	}else{
		$("#dietTabooNeed-no").attr("checked", true);
		$("#dietTaboo").attr("readonly","readonly");
		//$("#dietTaboodiv").hide();
	}
	
	var medicalhisNeed = '${user.medlHis}';
	if (medicalhisNeed) {
		$("#medicalhisNeed-yes").attr("checked", true);
	}else{
		$("#medicalhisNeed-no").attr("checked", true);
		$("#medlHis").attr("readonly","readonly");
	}
	
	var foodallergiesNeed = '${user.foodAllergies}';
	if (foodallergiesNeed) {
		$("#foodallergiesNeed-yes").attr("checked", true);
	}else{
		$("#foodallergiesNeed-no").attr("checked", true);
		$("#foodallergies").attr("readonly","readonly");
	}
 
});

var check_tab5 = function(){
	
	//处理radioname: religionNeed dietTabooNeed medicalhisNeed foodallergiesNeed
		// ${user.religion} ${user.dietTaboo} ${user.medlHis} ${user.foodAllergies}
		//div: religion  dietTaboo medlHis  foodallergies
	if($("[name='religionNeed']:checked").val() == 1 && $("[name='religion']").val()=="") {
		//alert("没有填写宗教信仰");
		alert("<spring:message code='basicInfo.religion' />"+"  "+"<spring:message code='basicInfo.lackOfInfo'/>");		
		//$("#btn-basic-submit").removeAttr("disabled");//将按钮可用
		return false;	 
	}
	if($("[name='dietTabooNeed']:checked").val() == 1 && $("[name='dietTaboo']").val()=="") {
		//alert("没有填写饮食禁忌");
		alert("<spring:message code='basicInfo.dietTaboo' />"+"  "+"<spring:message code='basicInfo.lackOfInfo'/>");		
		//$("#btn-basic-submit").removeAttr("disabled");//将按钮可用
		return false;	 
	}
	if($("[name='medicalhisNeed']:checked").val() == 1 && $("[name='medlHis']").val()=="") {
		//alert("没有填写个人病史");
		alert("<spring:message code='basicInfo.medicalhis' />"+"  "+"<spring:message code='basicInfo.lackOfInfo'/>");		
		//$("#btn-basic-submit").removeAttr("disabled");//将按钮可用
		return false;	 
	}
	if($("[name='foodallergiesNeed']:checked").val() == 1 && $("[name='foodallergies']").val()=="") {
		//alert("没有填写过敏食物");
		alert("<spring:message code='basicInfo.foodallergies' />"+"  "+"<spring:message code='basicInfo.lackOfInfo'/>");				
		//$("#btn-basic-submit").removeAttr("disabled");//将按钮可用
		return false;	 
	}
	 
	return true;
};

</script>