<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<style>
.swtable td{
	padding:6px;
	border:1px solid #eee;
}
.actBar h3 {
	margin-bottom: 0px;
	padding:3px 0;
}
</style>
<html>
	<div style="height:600px">
		<table id="formPreview" class="swtable">
			
		</table>
		<!-- <button id='btn-submit' type='button' class='btn btn-primary submit-btn'><spring:message code='Submit'/></button> -->
	</div>
	
	<script type="text/javascript">
		function preView(){
			var form = $("#exhibition-info-form");
			//通过ajax请求后台做数据的处理 ,Ajax提交保存
			$.ajax({
				  type:"post",
				  url:'front/exhibitCompany/exhibitionPreview',
				  data:form.serialize(),
				  success:function(result){
					  showPreview(result);
				 }
			});
		}
		//在页面显示预览的内容
		function showPreview(data){
			var htmltr = "<tr><td colspan='7' style='background:rgb(244, 242, 229);'><h3><spring:message code='exhibition.companyInfo'/></h3></td></tr>";
			//user		
			htmltr += "<tr><td width='18%'><spring:message code='exhibition.companyName'/></td><td colspan='6'>"+data.company.companyNameCn+"</td></tr>";
			htmltr += "<tr><td><spring:message code='exhibition.contact' /></td><td colspan='6'>"+data.company.contactPerson+"</td></tr>";
			htmltr += "<tr><td><spring:message code='exhibition.title' /></td><td colspan='6'>"+data.company.contactJob+"</td></tr>";
			htmltr += "<tr><td><spring:message code='basicInfo.email'/></td><td colspan='6'>"+data.company.contactEmail+"</td></tr>";
			htmltr += "<tr><td><spring:message code='basicInfo.telephone' /></td><td colspan='6'>"+data.company.contactTel+"</td></tr>";
			htmltr += "<tr><td><spring:message code='basicInfo.faxNo' /></td><td colspan='6'>"+data.company.contactFax+"</td></tr>";
			htmltr += "<tr><td><spring:message code='exhibition.companyName'/>(<spring:message code='exhibition.Chinese'/>)</td><td colspan='6'>"+data.company.companyNameCn+"</td></tr>";
			htmltr += "<tr><td><spring:message code='exhibition.companyName' />(<spring:message code='exhibition.English'/>)</td><td colspan='6'>"+data.company.companyNameEn+"</td></tr>";
			htmltr += "<tr><td><spring:message code='exhibition.address' /></td><td colspan='6'>"+data.company.companyAddress+"</td></tr>";
			htmltr += "<tr><td><spring:message code='exhibition.tele'/></td><td colspan='6'>"+data.company.companyTel+"</td></tr>";
			htmltr += "<tr><td><spring:message code='basicInfo.faxNo' /></td><td colspan='6'>"+data.company.companyFax+"</td></tr>";
			htmltr += "<tr><td><spring:message code='basicInfo.email' /></td><td colspan='6'>"+data.company.companyEmail+"</td></tr>";
			htmltr += "<tr><td><spring:message code='exhibition.website'/></td><td colspan='6'>"+data.company.companyWebsite+"</td></tr>";
			htmltr += "<tr><td><spring:message code='exhibition.itemType' /></td><td colspan='6'>"+data.company.companyExhibitType+"</td></tr>";
			htmltr += "<tr><td><spring:message code='exhibition.shortSummary' /></td><td colspan='6'>"+data.company.companyIntroduction+"</td></tr>";
									
			htmltr += "<tr style='line-height:30px;'> <td height='20' colspan='7'></td></tr>";
			//exhibitionForm展位
			var booths = data.exhibitionForm.booths;
			var exhibitions = eval(${exhibitionStr});
			htmltr += "<tr><td colspan='7' style='background:rgb(244, 242, 229);'><h3><spring:message code='exhibition.boothRentDetail'/></h3></td></tr>";
			htmltr += "<tr><td><spring:message code='exhibition.boothNo'/></td>"+
					"<td><spring:message code='exhibition.boothBoard'/></td>"+					
					"<td colspan='2'><spring:message code='exhibition.totalPrice'/><br>(葡币)</td>"+
					"<td colspan='3'><spring:message code='exhibition.totalPrice'/><br>(<spring:message code='exhibition.RMB'/>)</td></tr>";
			
			var exhibitionTotal=0;//展位和额外家具的总额
			var exhibitionTotalEn=0;//展位和家具总额，葡币
			var boothToalAmount=0;//展位金额
			var boothToalAmountEn=0;
			for(var boothIndex=0;boothIndex<booths.length;boothIndex++){
				var booth = booths[boothIndex];
				if(booth.boothState == "0" || booth.boothState == "1"){
					//展位未申请为null,0代表登录前已申请的，1代表登录前未申请现在申请的
					htmltr += "<tr><td>"+booth.boothName+"</td>";
					htmltr += "<td>"+booth.boothLintelName+"</td>";
					//价格需要展示
					for(var exhibitionIndex=0;exhibitionIndex<exhibitions.length;exhibitionIndex++){
						if(exhibitions[exhibitionIndex].exhibitionId == booth.parentId){
							htmltr += "<td colspan='2'>"+exhibitions[exhibitionIndex].exhibitionServiceCharge+"</td>";
							htmltr += "<td colspan='3'>"+exhibitions[exhibitionIndex].exhibitionPrice+"</td>";
							
							boothToalAmount += Number(exhibitions[exhibitionIndex].exhibitionPrice);
							boothToalAmountEn += Number(exhibitions[exhibitionIndex].exhibitionServiceCharge);
						}
					}
					htmltr += "</tr>";
				}
			}
			exhibitionTotal += boothToalAmount;
			exhibitionTotalEn += boothToalAmountEn;
			htmltr += "<tr><td></td><td colspan='6'><font style='color:#EA8511;font-weight:bold;'><spring:message code='exhibition.totalFee'/>:葡币"+boothToalAmountEn+
						",&nbsp;&nbsp;<spring:message code='exhibition.RMB'/>"+boothToalAmount+"</font></td></tr>";
			htmltr += "<tr style='line-height:30px;'> <td height='20' colspan='7'></td></tr>";
			
			//exhibitionForm额外家具
			var furnitures = data.exhibitionForm.furnitures;
			htmltr += "<tr><td colspan='7' style='background:rgb(244, 242, 229);'><h3><spring:message code='exhibition.extraFurniture'/></h3></td></tr>";
			htmltr += "<tr><td><spring:message code='exhibition.code'/></td>"+
					"<td><spring:message code='exhibition.itemName'/></td>"+
					"<td><spring:message code='exhibition.price'/><br>(葡币)</td>"+
					"<td><spring:message code='exhibition.price'/><br>(<spring:message code='exhibition.RMB'/>)</td>"+
					"<td><spring:message code='exhibition.rentAmount'/></td>"+
					"<td><spring:message code='exhibition.totalPrice'/><br>(葡币)</td>"+
					"<td><spring:message code='exhibition.totalPrice'/><br>(<spring:message code='exhibition.RMB'/>)</td></tr>";
			
			var furnitureAmount=0;//展位金额
			var furnitureAmountEn=0;
			for(var furnitureIndex=0;furnitureIndex<furnitures.length;furnitureIndex++){
				var furniture = furnitures[furnitureIndex];
				if(furniture.rentMount > 0){
					htmltr += "<tr><td>"+furniture.furnitureCode+"</td>";
					htmltr += "<td>"+furniture.furnitureName+"</td>";
					htmltr += "<td>"+furniture.furniturePriceEn+"</td>";
					htmltr += "<td>"+furniture.furniturePrice+"</td>";
					htmltr += "<td>"+furniture.rentMount+"</td>";
					htmltr += "<td>"+furniture.rentChargeEn+"</td>";
					htmltr += "<td>"+furniture.rentCharge+"</td></tr>";
					furnitureAmount += Number(furniture.rentCharge);
					furnitureAmountEn += Number(furniture.rentChargeEn);
				}
			}
			exhibitionTotal += furnitureAmount;
			exhibitionTotalEn += furnitureAmountEn;
			htmltr += "<tr><td></td><td colspan='6'><font style='color:#EA8511;font-weight:bold;'><spring:message code='exhibition.totalFee'/>:葡币"+furnitureAmountEn+
						",&nbsp;&nbsp;<spring:message code='exhibition.RMB'/>"+furnitureAmount+"</font></td></tr>";
			htmltr += "<tr style='line-height:30px;'> <td height='20' colspan='7'></td></tr>";
			
			//exhibitionForm展品
			var exhibits = data.exhibitionForm.exhibits;
			htmltr += "<tr><td colspan='7' style='background:rgb(244, 242, 229);'><h3><spring:message code='exhibition.itemInfo'/></h3></td></tr>";
			for(var exhibitIndex=0;exhibitIndex<exhibits.length;exhibitIndex++){
				var exhibit = exhibits[exhibitIndex];
				if( typeof(exhibit.exhibitName) != "undefined" && exhibit.exhibitName.replace(" ", "") != ""){
					htmltr += "<tr><td>*<spring:message code='exhibition.itemName'/></td><td>"+exhibit.exhibitName+"</td></tr>";
					htmltr += "<tr><td><spring:message code='exhibition.standard'/></td><td>"+exhibit.exhibitSpecifications+"</td></tr>";
					htmltr += "<tr><td><spring:message code='exhibition.amount'/></td><td>"+exhibit.exhibitMount+"</td></tr>";
					htmltr += "<tr><td><spring:message code='exhibition.unitPrice'/></td><td>"+exhibit.exhibitPrice+"</td></tr>";
					htmltr += "<tr><td><spring:message code='exhibition.itemPhoto'/></td><td><img src='<%=basePath%>"+exhibit.exhibitImage+"'></td></tr>";
					htmltr += "<tr><td><spring:message code='exhibition.itemSummary'/></td><td>"+exhibit.exhibitIntro+"</td></tr>";
					htmltr += "<tr><td><spring:message code='exhibition.comments'/></td><td>"+exhibit.exhibitOther+"</td></tr>";
				}
			}
			htmltr += "<tr style='line-height:30px;'> <td height='20' colspan='7'></td></tr>";
			
			//exhibitionForm物流需求
			var expressNeeds = data.exhibitionForm.expressNeeds;
			var isNeed="未填写";
			if(expressNeeds.expressNeed == "Y"){
				isNeed = "yes,是";
			}else if(expressNeeds.expressNeed == "N"){
				isNeed = "no,否";
			}
			htmltr += "<tr><td colspan='7' style='background:rgb(244, 242, 229);'><h3><spring:message code='exhibition.logisticsNeed'/></h3></td></tr>";
			htmltr += "<tr><td><spring:message code='exhibition.tab7.info2'/></td><td colspan='6'>"+isNeed+"</td></tr>";
			htmltr += "<tr style='line-height:30px;'> <td height='20' colspan='7'></td></tr>";
			
			//最终的计算总额
			htmltr += "<tr><td colspan='7' style='background:rgb(244, 242, 229);'><h3><spring:message code='exhibition.totalFee'/></h3></td></tr>";
			htmltr += "<tr><td></td><td colspan='6'><font style='color:#EA8511;font-weight:bold;'><spring:message code='exhibition.totalFee'/>:葡币"+exhibitionTotalEn+
					",&nbsp;&nbsp;<spring:message code='exhibition.totalFee'/>:<spring:message code='exhibition.RMB'/>"+exhibitionTotal+"</font></td></tr>";					
			
			$("#formPreview").html("");
			$("#formPreview").append(htmltr);
		}
		
		//分步验证
		function checkTab7(){
			return true;
		}
	</script>
</html>