<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<table class="swtable" style="width:98%;">
	<tr style="border-bottom:2px solid #8CC63F;">
		<th><spring:message code="exhibition.boothType"/></th>
		<th><spring:message code="exhibition.price"/><br>(葡币)</th>
		<th><spring:message code="exhibition.price"/><br>(<spring:message code="exhibition.RMB"/>)</th>
		<th><spring:message code="exhibition.rentBooth"/></th>
		<th><spring:message code="exhibition.chargeFee"/><br>(葡币)</th>
		<th><spring:message code="exhibition.chargeFee"/><br>(<spring:message code="exhibition.RMB"/>)</th>
	</tr>
	<c:forEach items="${exhibitions}" var="exhibition" varStatus="exhibitionStatus">
		<tr style="line-height:30px;">
			<td width="10%">${exhibition.exhibitionName }</td>
			<td width="10%">&nbsp;</td>
			<td width="10%">&nbsp;</td>
			<td width="50%">&nbsp;</td>
			<td width="10%">&nbsp;</td>
			<td width="10%">&nbsp;</td>
		</tr>
		<tr class="exhibitionLine" style="line-height:30px;border-bottom:1px dashed #8CC63F;">
			<td>
				<img src="<%=basePath %>${exhibition.exhibitionImage}" height="50px" width="50px" onclick="showExhibitionImage(this)"/>
			</td>
			<td><font style="color:#EA8511;font-weight:bold;"><span id="exhibitionPriceEn${exhibitionStatus.index}">${exhibition.exhibitionServiceCharge }</span></font>&nbsp;<spring:message code="exhibition.Yuan"/></td>
			<td><font style="color:#EA8511;font-weight:bold;"><span id="exhibitionPrice${exhibitionStatus.index}">${exhibition.exhibitionPrice }</span></font>&nbsp;<spring:message code="exhibition.Yuan"/></td>
			<td>
				<c:forEach items="${booths}" var="booth" varStatus="status">
					<c:if test="${booth.parentId eq exhibition.exhibitionId}">
						<input type="hidden" name="booths[${status.index }].boothName" value="${booth.boothName }">
						<input type="hidden" name="booths[${status.index }].parentId" value="${booth.parentId }">
						<input type="hidden" name="booths[${status.index }].boothId" value="${booth.boothId}">
						
						<c:if test='${booth.boothState eq "1"}'>
							<input type="checkbox" name="booths[${status.index}].boothState" id="boothState${status.index}" class="boothsCheckbox" 
								value="${booth.boothState }" onchange="boothStateChange(this)" >${booth.boothName }
							<c:if test="${(status.index + 1) % 10 eq 0}">
								<br />
							</c:if>
						</c:if>
						<c:if test='${(booth.boothState eq "0") && (booth.exhibitorId eq company.companyId)}'>
							<input type="checkbox" name="booths[${status.index}].boothState" id="boothState${status.index}" class="boothsCheckbox" 
								value="${booth.boothState }" checked="checked" onchange="boothStateChange(this)" >${booth.boothName }
							<c:if test="${(status.index + 1) % 10 eq 0}">
								<br />
							</c:if>
						</c:if>
					</c:if>
				</c:forEach>
				</td>
			<td><font style="color:#EA8511;font-weight:bold;">&nbsp;<span id="exhibitionPriceAmountEn${exhibitionStatus.index}"> </span></font>&nbsp;</td>
			<td><font style="color:#EA8511;font-weight:bold;">&nbsp;<span id="exhibitionPriceAmount${exhibitionStatus.index}"></span></font>&nbsp;</td>
		</tr>
	</c:forEach>
</table>
<div align="right">
	<label>
		<font style="color:#EA8511;font-weight:bold;">
			<spring:message code="exhibition.totalFee"/>：
			葡币<font style="font-size:17px;"><span id="boothsAmountSumEn"></span></font>&nbsp;&nbsp;
			<spring:message code="exhibition.RMB"/><font style="font-size:17px;"><span id="boothsAmountSum"></span></font>			
		</font>
	</label>
</div>
<script type="text/javascript">
$(document).ready(function() {
	initBoothState();
	initBoothsAmountSum();
});

//初始化展位的选中状态
function initBoothState(){
	var boothsCheckbox = $(".boothsCheckbox");
	for(var index=0;index<boothsCheckbox.length;index++){
		var checkindex = boothsCheckbox.eq(index);
		var name = checkindex.attr("name");
		var optioncheck = checkindex.is(":checked");//false,true
		changeLintelNames(name,optioncheck);
	}
}

//初始化申请展位的总金额
function initBoothsAmountSum(){
	//exhibitionLine
	var exhibits = $(".exhibitionLine");
	if(exhibits.length > 0){
		var amountSum = 0;//所有类别展位的总金额
		var amountSumEn = 0;//葡币总金额
		for(var i=0;i<exhibits.length;i++){
			var exhibit = exhibits.eq(i);
			var priceEnStr = $("#exhibitionPriceEn"+i).text();
			var priceStr = $("#exhibitionPrice"+i).text();
			var priceEn = Number(priceEnStr);
			var price = Number(priceStr);
			var boothsAmountEn = 0;//总金额，葡币
			var boothsAmount = 0;//每类展位租用的总金额
			
			var booths = exhibit.find(".boothsCheckbox");
			for(var j=0;j<booths.length;j++){
				var checkindex = booths.eq(j);
				var optioncheck = checkindex.is(":checked");//false,true				
				if(optioncheck){
					boothsAmount += price;
					boothsAmountEn += priceEn;
					//changeLintelNames(checkindex.attr("name"),optioncheck);
				}
			}
			
			$("#exhibitionPriceAmountEn"+i).text(boothsAmountEn);//申请的每类展位的金额
			$("#exhibitionPriceAmount"+i).text(boothsAmount);//申请的每类展位的金额
			amountSum += boothsAmount;
			amountSumEn += boothsAmountEn;
		}
		
		$("#boothsAmountSumEn").text(amountSumEn);
		$("#boothsAmountSum").text(amountSum);//申请的所有展位的总金额		
	}
	//遍历，若有选中，则加上此价格
	//id:$("#exhibitionPriceAmount").html("");
	//遍历结束；最后，计算总价
	//id:boothsAmountSum
}

//点击展位或者取消选中时的方法，同时改变金额和总价
function boothStateChange(obj){
	var name = obj.name;//.attr("name");
	var optioncheck = obj.checked;//false,true
	changeLintelNames(name,optioncheck);
}

function changeLintelNames(name,optioncheck){
	var start = name.indexOf("[");
	var end = name.indexOf("]");
	var index = name.substring(start+1,end);
	var trData = $(".boothLintelNames").eq(index);
	
	var boothState = $("#boothState"+index);
	var priceEnDiv = boothState.parent().prev().prev().find("span");//葡币
	var priceDiv = boothState.parent().prev().find("span");//人民币
	var priceAmountEnDiv = boothState.parent().next().find("span");
	var priceAmountDiv = boothState.parent().next().next().find("span");
	
	var priceEn = Number(priceEnDiv.text());
	var priceAmountEn = Number(priceAmountEnDiv.text());
	var price = Number(priceDiv.text());
	var priceAmount = Number(priceAmountDiv.text());
	var amountSumEn = Number($("#boothsAmountSumEn").text());
	var amountSum = Number($("#boothsAmountSum").text());
	
	if(optioncheck){
		//如果选中，金额加上相应的数目
		priceAmountEnDiv.text(priceAmountEn+priceEn);
		$("#boothsAmountSumEn").text(amountSumEn+priceEn);
		
		priceAmountDiv.text(priceAmount+price);
		$("#boothsAmountSum").text(amountSum+price);
		
		//相应的楣板显示
		trData.show();
	}else{
		//如果取消选中，金额减去相应数目
		priceAmountDiv.text(priceAmount-price);
		$("#boothsAmountSum").text(amountSum-price);
		
		priceAmountEnDiv.text(priceAmountEn-priceEn);
		$("#boothsAmountSumEn").text(amountSumEn-priceEn);
		//楣板名称先删除再隐藏，以免保存的时候出现问题
		//trData.find("textarea").eq(0).val("");//此处防止之前填写了楣板，保存时提示有些展位被申请，更换展位忘记填写楣板
		trData.hide();
	}
}

function showExhibitionImage(obj){
	var path = obj.src;         
    window.open(path+'?openwin=true', "展位设计图","height=610,width=1000,toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no,status=no");      
	
}
//分步验证
function checkTab2(){
	return true;
}

</script>