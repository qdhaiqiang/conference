<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<table class="swtable" width="98%">
	<tr style="border-bottom:2px solid #8CC63F;">
		<th><spring:message code="exhibition.code"/></th>
		<th><spring:message code="exhibition.itemName"/></th>
		<th><spring:message code="exhibition.price"/><br>(葡币)</th>
		<th><spring:message code="exhibition.price"/><br>(<spring:message code="exhibition.RMB"/>)</th>
		<th><spring:message code="exhibition.rentAmount"/></th>
		<th><spring:message code="exhibition.totalPrice"/><br>(葡币)</th>
		<th><spring:message code="exhibition.totalPrice"/><br>(<spring:message code="exhibition.RMB"/>)</th>
	</tr>
	
	<!-- 展示出所有的家具，数量和总数要查看展商有无申请 -->
	<c:forEach items="${furnitures}" var="furniture" varStatus="status">
		<input type="hidden" name="furnitures[${status.index}].furnitureId" value="${furniture.furnitureId }" />
		<input type="hidden" name="furnitures[${status.index}].rentId" value="${furniture.rentId }">
		<input type="hidden" name="furnitures[${status.index}].furnitureCode" value="${furniture.furnitureCode }">
		<input type="hidden" name="furnitures[${status.index}].furnitureName" value="${furniture.furnitureName }">
		<input type="hidden" name="furnitures[${status.index}].furniturePrice" value="${furniture.furniturePrice }">
		<input type="hidden" name="furnitures[${status.index}].furniturePriceEn" value="${furniture.furniturePriceEn }">
		
		<tr style="line-height:30px;">
			<td width="5%">${furniture.furnitureCode }</td>
			<td>${furniture.furnitureName }</td>
			<td width="10%"><font style="color:#EA8511;font-weight:bold;"><span id="furniturePriceEn${status.index}">${furniture.furniturePriceEn }</span></font></td>
			<td width="10%"><font style="color:#EA8511;font-weight:bold;"><span id="furniturePrice${status.index}">${furniture.furniturePrice }</span></font></td>
			<td width="10%">
				<c:choose>
					<c:when test="${furniture.furnitureRentType eq 1}">
						<!-- 租用数量类型，0整数，1小数 -->
					</c:when>
					<c:otherwise>
					
					</c:otherwise>
				</c:choose>
				
				<input class="furnituresRentMount" type="number" class="easyui-numberbox" name="furnitures[${status.index}].rentMount" onchange="rentMountChange(this)" value="${furniture.rentMount }">
			</td>
			<td width="10%"><font style="color:#EA8511;font-weight:bold;"><span id="rentChargeEn${status.index}">0</span><input type="hidden" name="furnitures[${status.index}].rentChargeEn" value="0"></font></td>	
			<td width="10%"><font style="color:#EA8511;font-weight:bold;"><span id="rentCharge${status.index}">0</span><input type="hidden" name="furnitures[${status.index}].rentCharge" value="0"></font></td>	
		</tr>
	</c:forEach>
</table>
<div align="right">
	<label>
		<font style="color:#EA8511;font-weight:bold;">
			<spring:message code="exhibition.totalFee"/>：
			葡币<font style="font-size:17px;"><span id="furnituresAmountSumEn">0</span></font>&nbsp;&nbsp;
			<spring:message code="exhibition.RMB"/><font style="font-size:17px;"><span id="furnituresAmountSum">0</span></font>			
		</font>
	</label>
</div>

<script type="text/javascript">
$(function(){
	initRentCharge();
});

//初始化每条租用的金额
function initRentCharge(){
	var furnituresRentMount = $(".furnituresRentMount");
	for(var i=0;i<furnituresRentMount.length;i++){
		var rentMountDiv = furnituresRentMount.eq(i);
		var rentMount = Number(rentMountDiv.val());
		changeRentCharge(rentMountDiv.attr("name"),rentMount);
	}
}

//改变租用数量时，调用方法改变金额
function rentMountChange(obj){
	var name=obj.name;
	var rentMount = Number(obj.value);
	changeRentCharge(name,rentMount)
}

//改变金额的方法
function changeRentCharge(name,rentMount){
	var start = name.indexOf("[");
	var end = name.indexOf("]");
	var index = name.substring(start+1,end);
	//记录改变输入之前的数量或者金额
	var priceEnSpan = $("#furniturePriceEn"+index);
	var priceSpan = $("#furniturePrice"+index);
	var priceEn = Number(priceEnSpan.text());
	var price = Number(priceSpan.text());
	
	var chargeAmountEn = priceEn*rentMount;
	var chargeAmount = price*rentMount;//保留几位小数Math.floor(num * 100) / 100或者Math.round四舍五入取小数
	var chargeEnSpan = $("#rentChargeEn"+index);
	var chargeSpan = $("#rentCharge"+index);
	var chargeInputEn = chargeEnSpan.next();
	var chargeInput = chargeSpan.next();
	//同时改变总金额，改变总金额，需要获取之前的金额和现在的金额差，此变量为了计算
	var beforeChangeAmount = Number(chargeSpan.text());
	var beforeChangeAmountEn = Number(chargeEnSpan.text());
	var furnituresAmountSum = Number($("#furnituresAmountSum").text());
	var furnituresAmountSumEn = Number($("#furnituresAmountSumEn").text());
	
	chargeEnSpan.text(chargeAmountEn);
	chargeInputEn.val(chargeAmountEn);
	chargeSpan.text(chargeAmount);
	chargeInput.val(chargeAmount);
	$("#furnituresAmountSum").text(furnituresAmountSum-beforeChangeAmount+chargeAmount);
	$("#furnituresAmountSumEn").text(furnituresAmountSumEn-beforeChangeAmountEn+chargeAmountEn);	
}

//分步验证
function checkTab3(){
	return true;
}
</script>
