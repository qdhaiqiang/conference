

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<base href="<%=basePath%>">

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
label,ul,ol,dl,li,p {
	padding: 0;
	margin: 0;
	list-style: none;
	font-size: 15px;
	line-height: 30px;
}

.tl {
	width: 1140px;
	border: 1px solid #f0f0f0;
	margin: 0 auto;
	margin-bottom: 10px;
}

.cont .tl img {
	height: 250px;
}

.qd {
	width: 1110px;
	padding: 14px 14px 50px;
	margin: 0 auto;
	border: 1px solid #ccc;
	background:#fcfdfe;
	height: 430px;
	font-size: 20px;
	font-family: "Microsoft YaHei", "宋体", "Georgia", "Times New Roman",
		"Times", "serif";
}

.query {
	width: 300px;
	height: 460px;
	float: left;
	border-right: 1px dotted #c5c5c5;
	text-align: center;
}

.user {
	width: 450px;
	float: left;
	height: 460px;
	text-align: center;
	padding-left:10px;
}
.fitem {
	text-align: left;
}
.fitem {
	border-bottom: none;
}
.fitem label {
	display: inline-block;
	width: 120px;
	background: none;
	padding: 5px;
	border-right: none;
}
.phostyle img{
	width:192px;
	height:255px;
}
.ljm_search {
	width: 50px;
	height: 30px;
	background: #EA8511;
	padding: 4px 15px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px border:     1px solid #EA8511;
	color: #F8F8F8;
}

.sign_control {
	width: 88%;
	border: 1px solid #95B8E7;
	height: 32px;
	padding: 2px 10px;
	color: #555;
	background-color: #fff;
	border-radius: 4px;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	-webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow
		ease-in-out .15s;
	-o-transition: border-color ease-in-out .15s, box-shadow ease-in-out
		.15s;
	transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
}
table td{
	padding-left:0;
}

</style>
</head>

<script type="text/javascript">
	var userType;
	
	var cardType;
	$(document).ready(function(){
			$("#userid")[0].focus();
			$("#userid").val("");
			$("#meal_ticket").val("0");
			$("#gift").val("0");
			$("#ifDocument").val("0");
			$("#ifReceipt").val("0");
			$("#restaurant").val("");
			$("#room_num").val("");
			$("#mail").val("");
			$("#postScript").val("");
			$("#photo").html("");
			$("#meal_ticket").prop("checked", false);
			$("#gift").prop("checked", false);
			$("#ifDocument").prop("checked", false);
			$("#ifReceipt").prop("checked", false);
			
			
			$(document).keydown(function(event){
			 	if(event.keyCode == 13){
				  loadInfoByQrcode();
				  //$("#userid").val("");
			 	}
			});
		
		 	$("#user_submit_query").bind('click',function(){
				loadInfoByQrcode();
				//$("#userid").val("");
		 	});			
		 	$("#mail_submit_query").bind('click',function(){
				loadInfoByMail();
				//$("#mail").val("");
		 	});			
		 	$("#signin").bind('click',function(){
				signin();
			
		 	});	
		 	
		 	$("#meal_ticket").bind('click',function(){
		 		if ($("#meal_ticket").val() == 0) {
		 			$("#meal_ticket").prop("checked", true);
		 			$("#meal_ticket").prop("value", 1);
				}else if ($("#meal_ticket").val() == 1) {
		 			$("#meal_ticket").prop("checked", false);
		 			$("#meal_ticket").prop("value", 0);
				}
		 	});	
		 	$("#gift").bind('click',function(){
		 		if ($("#gift").val() == 0) {
		 			$("#gift").prop("checked", true);
		 			$("#gift").prop("value", 1);
				}else if ($("#gift").val() == 1) {
		 			$("#gift").prop("checked", false);
		 			$("#gift").prop("value", 0);
				}
		 	});	
		 	$("#ifDocument").bind('click',function(){
		 		if ($("#ifDocument").val() == 0) {
		 			$("#ifDocument").prop("checked", true);
		 			$("#ifDocument").prop("value", 1);
				}else if ($("#ifDocument").val() == 1) {
		 			$("#ifDocument").prop("checked", false);
		 			$("#ifDocument").prop("value", 0);
				}
		 	});	
		 	$("#ifReceipt").bind('click',function(){
		 		if ($("#ifReceipt").val() == 0) {
		 			$("#ifReceipt").prop("checked", true);
		 			$("#ifReceipt").prop("value", 1);
				}else if ($("#ifReceipt").val() == 1) {
		 			$("#ifReceipt").prop("checked", false);
		 			$("#ifReceipt").prop("value", 0);
				}
		 	});	
		 	
		 	//获取用户类型数据
	         $.ajax({
	             url: 'dict/r/user_type',
	             method:'get',
	             async:false,
	             success:function(data) {
	                 userType = data;
	             }
	         });
	       
	         $.ajax({
	             url: 'dict/r/card_type',
	             method:'get',
	             async:false,
	             success:function(data) {
	                 cardType = data;
	             }
	         });
		});
	
		function loadInfoByQrcode(){
			$.ajax({
				//url : "%=basePath%>signinuser/findByUserId?userId="+$("#userid").val(),
				url : "<%=basePath%>signinuser/f/"+$("#userid").val(),
				type : "get",
				success : function(data) {
					if(data.OperatorState == "false" && data.Message == "该用户没有注册该会议或者没有审核通过"){
						alert(data.Message);
						window.location.reload();
					}else if(data.OperatorState == "false" && data.Message == "该用户已经签到过"){
						//alert(data.Message);
						$("#signState").text("是");
						$("#cardType").text(getCardType(data.confSigninUser.confUser.cardType));
						$("#app-userType").text(getUserType(data.confSigninUser.userType));
						$("#cname").text(data.confSigninUser.confUser.cname);
						$("#company").text(getCompany(data.confSigninUser.confUser.company));
						$("#position").text(getPosition(data.confSigninUser.confUser.position));
						$("#mobile").text(getMobile(data.confSigninUser.confUser.mobile));
						$("#ename").text(data.confSigninUser.confUser.ename);
						$("#sex").text(getSex(data.confSigninUser.confUser.sex));
						$("#useridCopy").prop("value",data.confSigninUser.confUser.id);
						$("#signinUserid").prop("value",data.confSigninUser.id);
						
						$("#photo").html("<img src='"+getPhoto(data.confSigninUser.confUser.photo)+"'>");
						//window.location.reload();
						$("#restaurant").val(data.confSigninUser.restaurant);
						$("#room_num").val(data.confSigninUser.roomNum);
						$("#postScript").val(data.confSigninUser.postscript);
						
						if(data.confSigninUser.mailTicket==1){
							$("#meal_ticket").prop("checked", true);
							$("#meal_ticket").val("1");
						}else if(data.confSigninUser.mailTicket==0){
							$("#meal_ticket").prop("checked", false);
							$("#meal_ticket").val("0");
						}
						if(data.confSigninUser.ifdocument==1){
							$("#ifDocument").prop("checked", true);
							$("#ifDocument").val("1");
						}else if(data.confSigninUser.ifdocument==0){
							$("#ifDocument").prop("checked", false);
							$("#ifDocument").val("0");
						}
						if(data.confSigninUser.ifreceipt==1){
							$("#ifReceipt").prop("checked", true);
							$("#ifReceipt").val("1");
						}else if(data.confSigninUser.ifreceipt==0){
							$("#ifReceipt").prop("checked", false);
							$("#ifReceipt").val("0");
						}
						if(data.confSigninUser.gift==1){
							$("#gift").prop("checked", true);
							$("#gift").val("1");
						}else if(data.confSigninUser.gift==0){
							$("#gift").prop("checked", false);
							$("#gift").val("0");
						}
						
					}else if(data.OperatorState == "true"){
						$("#signState").text("否");
						
						$("#cardType").text(getCardType(data.confUser.cardType));
						$("#app-userType").text(getUserType(data.userType));
						$("#cname").text(data.confUser.cname);
						$("#company").text(getCompany(data.confUser.company));
						$("#position").text(getPosition(data.confUser.position));
						$("#mobile").text(getMobile(data.confUser.mobile));
						$("#ename").text(data.confUser.ename);
						$("#sex").text(getSex(data.confUser.sex));
						//$("#useridCopy").prop("value",$("#userid").val());
						$("#useridCopy").prop("value",data.confUser.id);
						$("#signinUserid").prop("value","");
						//alert(data.confUser.id);
						$("#photo").html("<img src='"+getPhoto(data.confUser.photo)+"'>");
						$("#meal_ticket").prop("checked", true);
						$("#ifDocument").prop("checked", true);
						$("#meal_ticket").val("1");
						$("#ifDocument").val("1");
						$("#gift").prop("checked", false);
						$("#gift").val("0");
						$("#ifReceipt").prop("checked", false);
						$("#ifReceipt").val("0");
						$("#restaurant").val("");
						$("#room_num").val("");
						$("#postScript").val("");
					}
					$("#userid").val("");
					//window.location.reload();
				}
			});
		}
		
		function loadInfoByMail(){
			//alert($("#mail").val());
			$.ajax({
				url : "<%=basePath%>signinuser/mf?mail="+$("#mail").val(),
				//url : "<%=basePath%>signinuser/mf/"+$("#mail").val(),
				type : "get",
				success : function(data) {
					//alert(data.OperatorState);
					if(data.OperatorState == "false" && data.Message == "该用户没有注册该会议或者没有审核通过"){
						alert(data.Message);
						window.location.reload();
					}else if(data.OperatorState == "false" && data.Message == "该用户已经签到过"){
						$("#signState").text("是");
						$("#cardType").text(getCardType(data.confSigninUser.confUser.cardType));
						$("#app-userType").text(getUserType(data.confSigninUser.userType));
						$("#cname").text(data.confSigninUser.confUser.cname);
						$("#company").text(getCompany(data.confSigninUser.confUser.company));
						$("#position").text(getPosition(data.confSigninUser.confUser.position));
						$("#mobile").text(getMobile(data.confSigninUser.confUser.mobile));
						$("#ename").text(data.confSigninUser.confUser.ename);
						$("#sex").text(getSex(data.confSigninUser.confUser.sex));
						$("#useridCopy").prop("value",data.confSigninUser.confUser.id);
						$("#signinUserid").prop("value",data.confSigninUser.id);
						$("#photo").html("<img src='"+getPhoto(data.confSigninUser.confUser.photo)+"'>");
						//window.location.reload();
						$("#restaurant").val(data.confSigninUser.restaurant);
						$("#room_num").val(data.confSigninUser.roomNum);
						$("#postScript").val(data.confSigninUser.postscript);
						
						if(data.confSigninUser.mailTicket==1){
							$("#meal_ticket").prop("checked", true);
							$("#meal_ticket").val("1");
						}else if(data.confSigninUser.mailTicket==0){
							$("#meal_ticket").prop("checked", false);
							$("#meal_ticket").val("0");
						}
						if(data.confSigninUser.ifdocument==1){
							$("#ifDocument").prop("checked", true);
							$("#ifDocument").val("1");
						}else if(data.confSigninUser.ifdocument==0){
							$("#ifDocument").prop("checked", false);
							$("#ifDocument").val("0");
						}
						if(data.confSigninUser.ifreceipt==1){
							$("#ifReceipt").prop("checked", true);
							$("#ifReceipt").val("1");
						}else if(data.confSigninUser.ifreceipt==0){
							$("#ifReceipt").prop("checked", false);
							$("#ifReceipt").val("0");
						}
						if(data.confSigninUser.gift==1){
							$("#gift").prop("checked", true);
							$("#gift").val("1");
						}else if(data.confSigninUser.gift==0){
							$("#gift").prop("checked", false);
							$("#gift").val("0");
						}
					}else if(data.OperatorState == "true"){
						//alert("111");
						$("#signState").text("否");
						$("#signinUserid").prop("value","");
						$("#cardType").text(getCardType(data.confUser.cardType));
						$("#app-userType").text(getUserType(data.userType));
						$("#cname").text(data.confUser.cname);
						$("#company").text(getCompany(data.confUser.company));
						$("#position").text(getPosition(data.confUser.position));
						$("#mobile").text(getMobile(data.confUser.mobile));
						$("#ename").text(data.confUser.ename);
						$("#sex").text(getSex(data.confUser.sex));
						$("#useridCopy").prop("value",data.confUser.id);
						$("#photo").html("<img src='"+getPhoto(data.confUser.photo)+"'>");
						$("#meal_ticket").prop("checked", true);
						$("#ifDocument").prop("checked", true);
						$("#meal_ticket").val("1");
						$("#ifDocument").val("1");
						$("#gift").prop("checked", false);
						$("#gift").val("0");
						$("#ifReceipt").prop("checked", false);
						$("#ifReceipt").val("0");
						$("#restaurant").val("");
						$("#room_num").val("");
						$("#postScript").val("");
					}
					$("#mail").val("");
					//window.location.reload();
				}
			});
		}
		
		function signin(){
			if ($("#useridCopy").val()!=null && $("#useridCopy").val().length==32) {
				$.ajax({
					url : "<%=basePath%>signinuser/s",
					type : "POST",
					
					data:"confUser.id="+$("#useridCopy").val()+"&mailTicket="+$("#meal_ticket").val()+"&gift="+$("#gift").val()+"&restaurant="+$("#restaurant").val()+"&roomNum="+$("#room_num").val()+"&ifdocument="+$("#ifDocument").val()+"&ifreceipt="+$("#ifReceipt").val()+"&postscript="+$("#postScript").val()+"&id="+$("#signinUserid").val(),
					success : function(data) {
						if(data == "Success"){
							alert("签到成功");
							window.location.reload();
							//$.messager.alert();
							//$.messager.show();
						}
					}
				});
			} else {
				alert("数据格式错误，请点击确认后再试一次");
				window.location.reload();
			}
		}
		
		function getSex(sex){
			if (sex == 1) {
	 			return "男";
	 		} else if (sex == 2) {
	 			return "女";
	 		}
		}
		
		function getUserType(value){
			for (var i=0; i<userType.length; i++) {
	            if (value == userType[i].code) {
	                return userType[i].name;
	            }
	        }
		}
	
		
		function getCardType(value){
			if (value=="" || value == null) {
				return "";
			}
			for (var i=0; i<cardType.length; i++) {
				
	            if (value == cardType[i].code) {
	                return cardType[i].name;
	            }
	        }
		}
		
		function getPosition(value){
			if (value=="" || value == null) {
				return "";
			}else{
				return value;
			}
		}
		
		function getCompany(value){
			if (value=="" || value == null) {
				return "";
			}else{
				return value;
			}
		}
		
		function getPhoto(value){
			if (value=="" || value == null) {
				return "";
			}else{
				return value;
			}
		}
		
		function getMobile(value){
			if (value=="" || value == null) {
				return "";
			}else{
				return value;
			}
		}
		
		
		
</script>
<body>
	<div class="tl">
		<img src='images/reg_banner.jpg' />
	</div>


	<div class="qd">

		<div class="query">
			<div>
				<table>
					<tbody>
						<tr>
							<td colspan="2" style="text-align:left;"><label>二维码签到:</label></td>
						</tr>
						<tr>
							<td width="200"><input class="sign_control" name="userId"
								type="text" id="userid" />
							</td>
							<td><label id="user_submit_query" class="ljm_search">查询</label>
							</td>
						</tr>
						<tr>
							<td height="10"></td>
							<td></td>
						</tr>
						<tr>
							<td colspan="2" style="text-align:left;"><label>邮箱签到:</label>
							</td>
						</tr>
						<tr>
							<td><input class="sign_control" name="mail" type="text"
								id="mail" />
							</td>
							<td><label id="mail_submit_query" class="ljm_search">查询</label>
							</td>
						</tr>
					</tbody>
				</table>
			</div>

			<div style="margin-top:20px;">
				<table>
					<tr>
						<td colspan="2">
							<input type="checkbox" name="mailTicket" id="meal_ticket" value="0"><label>餐券</label>
							<input type="checkbox" name="ifDocument" id="ifDocument" value="0"><label>证件</label>
							<input type="checkbox" name="gift" id="gift" value="0"><label>礼品</label>
							<input type="checkbox" name="ifReceipt" id="ifReceipt" value="0"><label>报销</label>
						</td>
					</tr>
					<tr>
						<td colspan="2" style="text-align:left;"><label>酒店</label><input type="text" class="sign_control"
							name="restaurant" id="restaurant">
						</td>
					</tr>
					<tr>
						<td colspan="2" style="text-align:left;"><label>房间号</label><input type="text" class="sign_control"
							name="roomNum" id="room_num">
						</td>
					</tr>
					<tr>
						<td colspan="2" style="text-align:left;"><label>备注</label><textarea class="sign_control" name="postScript" id="postScript" style="height:64px;"></textarea></td>
					</tr>
					<tr>
						<td colspan="2" style="text-align:center;padding-top:10px;"><label id="signin" class="ljm_search">签到</label></td>
					</tr>
				</table>
			</div>
		</div>
		<div id="userinfo" class="user">
			<div>
				<div class="fitem">
					<label>签到状态:</label> <label id="signState">
				</div>
				<div class="fitem">
					<label>身份卡类型:</label> <label id="cardType">
				</div>
				<div class="fitem">
					<label>用户类型:</label> <label id="app-userType">
				</div>
				<div class="fitem">
					<label>姓名全称:</label> <label id="cname" name="cname">
				</div>
				<div class="fitem">
					<label>中（英）文名:</label> <label id="ename" name="ename">
				</div>
				<div class="fitem">
					<label>性别:</label> <label id="sex">
				</div>
				<div class="fitem">
					<label>单位名称:</label> <label id="company" name="company">
				</div>
				<div class="fitem">
					<label>职务:</label> <label id="position" name="position">
				</div>
				<div class="fitem">
					<label>联系方式:</label> <label id="mobile" name="mobile">
				</div>
				<input name="useridCopy" id="useridCopy" type ="hidden"/>	
				<input name="signinUserid" id="signinUserid" type ="hidden"/>	
			</div>

		</div>
		<div style="float:left;">
			<label>照片:</label>
			<div class="phostyle" id="photo" name="photo"></div>
		</div>
	</div>
</body>
</html>