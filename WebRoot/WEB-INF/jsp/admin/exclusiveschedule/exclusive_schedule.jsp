<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<jsp:include page="../../../../include/sys-common.jsp" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<style type="text/css">
.m-info {
	margin: 0;
	padding: 4px;
	vertical-align: top;
	border: 1px solid #95B8E7;
	-moz-border-radius: 5px 5px 5px 5px;
	-webkit-border-radius: 5px 5px 5px 5px;
	border-radius: 5px 5px 5px 5px;
	border-left: 0px;
	border-top: 0px;
	border-right: 0px;
	border-bottom: 0px;
	border-bottom-color: Black;
}

h4 {
	margin: 0 0 7px -12px;
	padding: 7px 0 7px 5px;
	font: bold 12px Times, serif;
	color: #891300;
	border-top: 0;
	background: #f5f9fd;
	border-bottom: 1px dotted #dddad9;
}

.fitem {
	margin-bottom: 5px;
	border-bottom:1px dotted #8CC63F;
}
.fitem label {
    display: inline-block;
    width: 120px;
}

.fitem input {
    width: 300px;
}
.fitem2 label {
    display: inline-block;
    width: 230px;
}

.fitem2{
	border-bottom:1px dotted #8CC63F;
	padding:4px 0 2px 0;
}

.fitem2 input {
    width: 170px;
}
.app-top-td{
	width:450px;
}
</style>
<script type="text/javascript">
var userType;
var roomType;
var nation;
var certType;
var entryType;
var companyType;
var industry;
var suserType;
var snation;
var sroomType;
var scertType;
var scompnyType;
var sindustry;
$(document).ready(function(){
	// 获取用户类型
	$.ajax({
		url: 'dict/r/user_type',
		method:'get',
		async:false,
		success:function(data) {
			userType = data;
		}
	}); 
	//房间类型
	$.ajax({
		url: 'dict/r/room_type',
		method:'get',
		async:false,
		success:function(data) {
			roomType = data;
		}
	}); 
	//获取国籍数据
    $.ajax({
        url: 'dict/r/nation',
        method:'get',
        async:false,
        success:function(data) {
       	 nation = data;
        }
    });
	//获取身份证件类型数据
    $.ajax({
        url: 'dict/r/cert_type',
        method:'get',
        async:false,
        success:function(data) {
       	 certType = data;
        }
    });
	//获取入澳证件类型数据
    $.ajax({
        url: 'dict/r/entry_type',
        method:'get',
        async:false,
        success:function(data) {
       	 entryType = data;
        }
    });
   // 获取单位性质
    $.ajax({
        url: 'dict/r/company_type',
        method:'get',
        async:false,
        success:function(data) {
            companyType = data;
        }
    });
   // 获取行业类型
   $.ajax({
       url: 'dict/r/industry',
       method:'get',
       async:false,
       success:function(data) {
           industry = data;
       }
   });
   
   	setUserDatas();
	getUserType();
	getNationType();
	getCertType();
	getCompanyType();
	getIndustryType();
	getContactPerson();
	getRoomType();
	
	$("input,textarea").attr("readonly",true);
});

function setUserDatas(){
	
	suserType = "${vsInfo.userType }";
	snation = "${vsInfo.nation}";
	sroomType = "${vsInfo.roomType}";
	scertType = "${vsInfo.certType}";
	scompnyType = "${vsInfo.compnyType}";
	sindustry = "${vsInfo.industry}";
}

/**
 * 用户类型
 */
function getUserType(){
    for (var i=0; i<userType.length; i++) {
        if (suserType == userType[i].code) {
            $('#userType').val(userType[i].name);
            break;
        }
    }
}
/**
 * 国籍
 */
function getNationType(){
    for (var i=0; i<nation.length; i++) {
        if (snation == nation[i].code) {
            $('#nationType').val(nation[i].name);
            break;
        }
    }
}
/**
 * 房间类型
 */
function getRoomType(){
    for (var i=0; i<roomType.length; i++) {
        if (sroomType == roomType[i].code) {
            $('#roomType').val(roomType[i].name);
            break;
        }
    }
}
/**
 * 有效证件类型
 */
function getCertType(){
    for (var i=0; i<certType.length; i++) {
        if (scertType == certType[i].code) {
            $('#validCertType').val(certType[i].name);
            break;
        }
    }
}
/**
 * 单位性质
 */
function getCompanyType(){
    for (var i=0; i<companyType.length; i++) {
        if (scompnyType == companyType[i].code) {
            $('#companyType').val(companyType[i].name);
            break;
        }
    }
}
/**
 * 行业性质
 */
function getIndustryType(){
    for (var i=0; i<industry.length; i++) {
        if (sindustry == industry[i].code) {
            $('#industryType').val(industry[i].name);
            break;
        }
    }
}
/**
 * 指定联系人
 */
function getContactPerson(){
    var sContact = "${vsInfo.contactPerson}";
    if (sContact != "") {
        if (jsonObj != "{}") {
        	var jsonObj = ${vsInfo.contactPerson};
        	//var jsonObj = eval('(' + sContact + ')');
            $("#det_s_full_name").val(jsonObj.fullName);

            if(jsonObj.sex == 1){
                $("#det_s_sex").val("男");
            }else if(jsonObj.sex == 2){
                $("#det_s_sex").val("女");
            }
            $("#det_s_position").val(jsonObj.position);
            $("#det_s_contact_no").val(jsonObj.contactNo);
            $("#det_s_email").val(jsonObj.email);
            $("#app_s_fax_no").val(jsonObj.faxNo);
            $("#det_s_mobile").val(jsonObj.mobile);
        }
    }
}
</script>

</head>
<body>
	<div id="dlg" left="100px" top="0">
		<table cellspacing="30px">
			<tr>
				<td class="app-top-td" valign="top" style="border:2px solid #8CC63F;padding:4px;">
					<div class="fitem">
	      				<label>用户类型:</label> <input id="userType" class="m-info" />
					</div>
					<div class="fitem">
						<label>英文姓名:</label> <input value="${vsInfo.ename}" class="m-info" />
					</div>
					<div class="fitem">
						<label>中文姓名:</label> <input value="${vsInfo.cname}" class="m-info" />
					</div>
					<div class="fitem">
						<label>邮箱:</label> <input value="${vsInfo.email}" class="m-info" />
					</div>
					<div class="fitem">
						<c:if test="${vsInfo.sex==1}">
	      					<label>性别:</label> <input value="男" class="m-info">
	      				</c:if>
						<c:if test="${vsInfo.sex==2}">
	      					<label>性别:</label> <input value="女" class="m-info">
	      				</c:if>
					</div>
					<div class="fitem">
						<label>出生日期:</label> <input value="${vsInfo.birth}" class="m-info">
					</div>
					<div class="fitem">
	      				<label>国籍:</label> <input id="nationType" class="m-info">
					</div>
					<div class="fitem">
						<label>尊教信仰:</label> <input value="${vsInfo.religion}" class="m-info">
					</div>
					<div class="fitem">
						<label>饮食禁忌:</label> <input value="${vsInfo.dietTaboo}" class="m-info">
					</div>
					<div class="fitem">
						<label>慢性病史:</label> <input value="${vsInfo.medlHis}" class="m-info">
					</div>
					<div class="fitem">
						<label>过敏食物:</label> <input value="${vsInfo.foodAllergies}" class="m-info">
					</div>
					<div class="fitem">
						<label>通讯地址:</label> <input value="${vsInfo.address}" class="m-info">
					</div>
					<div class="fitem">
						<label>邮编:</label> <input value="${vsInfo.postcode}" class="m-info">
					</div>
					<div class="fitem">
						<label>固定电话:</label> <input value="${vsInfo.tele}" class="m-info">
					</div>
					<div class="fitem">
						<label>移动电话:</label> <input value="${vsInfo.mobile}" class="m-info">
					</div>
					<div class="fitem">
						<label>传真:</label> <input value="${vsInfo.fax}" class="m-info">
					</div>
					<div class="fitem">
						<label>指定联系人姓名:</label> <input id="det_s_full_name"
							name="s-full-name" class="m-info">
					</div>
					<div class="fitem">
						<label>指定联系人性别:</label> <input id="det_s_sex" name="s-sex"
							class="m-info">
					</div>
					<div class="fitem">
						<label>指定联系人电话:</label> <input id="det_s_contact_no"
							name="s-contact-no" class="m-info">
					</div>
					<div class="fitem">
						<label>指定联系人手机:</label> <input type="text" class="m-info"
							id="det_s_mobile" name="s-mobile">
					</div>
					<div class="fitem">
						<label>指定联系人邮箱:</label> <input type="text" class="m-info"
							id="det_s_email" name="s-email">
					</div>
					<div class="fitem">
						<label>指定联系人传真:</label> <input type="text" class="m-info"
							id="app_s_fax_no" name="s-fax-no">
					</div>
					<div class="fitem">
						<label>指定联系人职务:</label> <input id="det_s_position"
							name="s-position" class="m-info">
					</div>
					<div class="fitem">
						<label>嘉宾配偶信息:</label>
						<textarea id="spouseInfo" value="${vsInfo.spouseInfo}" class="m-info"
							style="height:50px;width:220px;"></textarea>
					</div>
					<div class="fitem">
						<label>嘉宾随行人员信息:</label>
						<textarea value="${vsInfo.entourageInfo}" class="m-info" style="height:180px;width:220px;"></textarea>
					</div>
					<div class="fitem">
						<label>有效证件类型:</label> <input id="validCertType" class="m-info">
					</div>
					<div class="fitem">
						<label>有效证件号码:</label> <input value="${vsInfo.certValue}" class="m-info">
					</div>
					<div class="fitem">
						<c:if test="${vsInfo.entryType==1}">
	      					<label>入澳证件类型:</label> <input value="" class="m-info">
	      				</c:if>
						<c:if test="${vsInfo.entryType==2}">
	      					<label>入澳证件类型:</label> <input value="" class="m-info">
	      				</c:if>
					</div>
					<div class="fitem">
						<label>签发地点:</label> <input value="${vsInfo.entryPlace}" class="m-info">
					</div>
					<div class="fitem">
						<label>签发日期:</label> <input value="${vsInfo.entryDate}" class="m-info">
					</div>
					<div class="fitem">
						<label>有效期至:</label> <input value="${vsInfo.entryValidity}" class="m-info">
					</div>
					<div class="fitem">
						<label>证件号码:</label> <input value="${vsInfo.entryNum}" class="m-info">
					</div>
					<div class="fitem">
						<label>有效签注签发日期:</label> <input value="${vsInfo.entryEndmtDate}"
							class="m-info">
					</div>
					<div class="fitem">
						<label>有效签注有效期至:</label> <input value="${vsInfo.entryEndmtValidity}"
							class="m-info">
					</div>
					<div class="fitem">
						<label>所在单位名称:</label> <input value="${vsInfo.company}" class="m-info">
					</div>
					<div class="fitem">
						<label>单位性质:</label> <input id="companyType" class="m-info">
					</div>
					<div class="fitem">
						<label>所在单位所属行业:</label> <input id="industryType" class="m-info">
					</div>
					<div class="fitem">
						<label>个人职务:</label> <input value="${vsInfo.position}" class="m-info">
					</div>
					<div class="fitem">
						<label>自我介绍-中文:</label>
						<textarea value="${vsInfo.selfIntro}" class="m-info"
							style="height:100px;width:200px;"></textarea>
					</div>
					<div class="fitem">
						<label>自我介绍-英文:</label>
						<textarea value="${vsInfo.selfIntroEn}" class="m-info"
							style="height:100px;width:200px;"></textarea>
					</div></td>
				<td class="app-top-td" valign="top" style="border:2px solid #8CC63F;padding:4px;">
					<div class="fitem">
						<label>化名:</label> <input value="${vsInfo.ualias}" class="m-info">
					</div>
					<div class="fitem">
						<label>职务名称:</label> <input value="${vsInfo.positionTitle}" class="m-info">
					</div>
					<div class="fitem">
						<label>备注信息:</label> <input value="${vsInfo.remark}" class="m-info">
					</div>
					
					<div class="fitem">
						<label>送关服务:</label> <input value="${vsInfo.checkpoint}" class="m-info">
					</div>
					<div class="fitem">
						<label>出行方式:</label>
						<c:if test="${vsInfo.typeCome==1}">
	      					<input value="飞机" class="m-info">
	      				</c:if>
						<c:if test="${vsInfo.typeCome==2}">
	      					<input value="高铁" class="m-info">
	      				</c:if>
					</div>
					<div class="fitem">
						<c:if test="${vsInfo.typeCome==1}">
	      					<label>航班号:</label> <input value="${vsInfo.numberCome}" class="m-info">
	      				</c:if>
						<c:if test="${vsInfo.typeCome==2}">
	      					<label>高铁号:</label> <input value="${vsInfo.numberCome}" class="m-info">
	      				</c:if>
					</div>
					<div class="fitem">
						<label>始发站:</label> <input value="${vsInfo.startPlaceCome}" class="m-info">
					</div>
					<div class="fitem">
						<label>终点站:</label> <input value="${vsInfo.endPlaceCome}" class="m-info">
					</div>
					<div class="fitem">
						<label>始发时间:</label> <input value="${vsInfo.startTimeCome}" class="m-info">
					</div>
					<div class="fitem">
						<label>到达时间:</label> <input value="${vsInfo.endTimeCome}" class="m-info">
					</div>
					<div class="fitem">
						<label>返程方式:</label>
						<c:if test="${vsInfo.typeGo==1}">
	      					<input value="飞机" class="m-info">
	      				</c:if>
						<c:if test="${vsInfo.typeGo==2}">
	      					<input value="高铁" class="m-info">
	      				</c:if>
					</div>
					<div class="fitem">
						<c:if test="${vsInfo.typeGo==1}">
	      					<label>航班号:</label> <input value="${vsInfo.numberGo}" class="m-info">
	      				</c:if>
						<c:if test="${vsInfo.typeGo==2}">
	      					<label>高铁号:</label> <input value="${vsInfo.numberGo}" class="m-info">
	      				</c:if>
					</div>
					<div class="fitem">
						<label>始发站:</label> <input value="${vsInfo.startPlaceGo}" class="m-info">
					</div>
					<div class="fitem">
						<label>终点站:</label> <input value="${vsInfo.endPlaceGo}" class="m-info">
					</div>
					<div class="fitem">
						<label>始发时间:</label> <input value="${vsInfo.startTimeGo}" class="m-info">
					</div>
					<div class="fitem">
						<label>到达时间:</label> <input value="${vsInfo.endTimeGo}" class="m-info">
					</div>
					<div class="fitem">
						<label>酒店名称:</label> <input value="${vsInfo.restaurant}" class="m-info">
					</div>
					<div class="fitem">
						<label>房间类型:</label> <input id="roomType" class="m-info">
					</div>
					<div class="fitem">
						<label>房间号:</label> <input value="${vsInfo.roomNum}" class="m-info">
					</div>
					<div class="fitem">
						<label>入住时间:</label> <input value="${vsInfo.checkInDate}" class="m-info">
					</div>
					<div class="fitem">
						<label>退房时间:</label> <input value="${vsInfo.checkOutDate}" class="m-info">
					</div>
					<c:forEach items="${scheduleInfo}" var="schedule">
						<div class="fitem">
							<label>会议信息:</label>
							<textarea class="m-info" style="height:50px;width:300px;">${schedule.title}&nbsp;(${schedule.startTime}--${schedule.endTime})</textarea>
						</div>
					</c:forEach>
					<c:forEach items="${eventInfo}" var="event">
						<div class="fitem">
							<label>事件信息:</label>
							<textarea class="m-info" style="height:50px;width:300px;">${event.name}&nbsp;(${event.startTime}--${event.endTime})</textarea>
						</div>
					</c:forEach>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
