

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	background:#f4f9fd;
	border: 1px solid #ccc;
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
	width: 780px;
	float: right;
	height: 460px;
	text-align: center;
}

.fitem {
	text-align: left;
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
			$("#mail").val("");
			$("#isHeadset").val("0");
			$("#isHeadset").prop("checked", false);
			
			
			$(document).keydown(function(event){
			 	if(event.keyCode == 13){
				  loadInfoByQrcode();
			 	}
			});
		
		 	$("#user_submit_query").bind('click',function(){
				loadInfoByQrcode();
			
		 	});		
		 	
		 	$("#mail_submit_query").bind('click',function(){
				loadInfoByMail();
				
		 	});	
		 	$("#checkin").bind('click',function(){
		 		checkIn();
			
		 	});	
		 	
		 	$("#isHeadset").bind('click',function(){
		 		if ($("#isHeadset").val() == 0) {
		 			$("#isHeadset").prop("checked", true);
		 			$("#isHeadset").prop("value", 1);
				}else if ($("#isHeadset").val() == 1) {
		 			$("#isHeadset").prop("checked", false);
		 			$("#isHeadset").prop("value", 0);
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
				//url : "<%=basePath%>signinuser/findByUserId?userId="+$("#userid").val(),
				url : "<%=basePath%>checkinuser/fforCheckin/"+$("#userid").val(),
				type : "get",
				//$("#userid")[0].focus();
				//data:"meal_ticket="+$("#userid").val()+"&xxx=",
				success : function(data) {
					
					if(data.OperatorState == "false"){	
						$("#state").val("0");//签入失败，状态为0
						$("#isHeadset").prop("disabled",false);
						$("#isHeadset").val("0");
						$("#isHeadset").prop("checked", false);
						$("#useridCopy").prop("value", $("#userid").val());
						clear();
						
						if(data.Message == "该用户没有注册该会议日程"){
							alert(data.Message);
						}else if(data.Message == "该用户没有注册会议或没有通过审核。请联系组委会确认。"){
							alert(data.Message);
						}
						
						checkIn();//没有注册，同样记录一笔签入失败的记录
						//window.location.reload();
					}else if(data.OperatorState == "true" && data.ifHeadset == "false"){
						$("#state").val("1");
						$("#cardType").text(getCardType(data.confUser.cardType));
						$("#app-userType").text(getUserType(data.userType));
						$("#cname").text(data.confUser.cname);
						$("#ename").text(data.confUser.ename);
						$("#sex").text(getSex(data.confUser.sex));
						$("#company").text(getCompany(data.confUser.company));
						$("#position").text(getPosition(data.confUser.position));
						$("#mobile").text(getMobile(data.confUser.mobile));
						//$("#photo").html("<img src='"+data.confUser.photo+"'>");
						$("#isHeadset").prop("disabled",false);
						$("#isHeadset").prop("checked", true);
						$("#isHeadset").val("1");
						$("#useridCopy").prop("value", data.confUser.id);
						
					}else if(data.OperatorState == "true" && data.ifHeadset == "true"){
						$("#state").val("1");
						$("#cardType").text(getCardType(data.confUser.cardType));
						$("#app-userType").text(getUserType(data.userType));
						$("#cname").text(data.confUser.cname);
						$("#ename").text(data.confUser.ename);
						$("#sex").text(getSex(data.confUser.sex));
						$("#company").text(getCompany(data.confUser.company));
						$("#position").text(getPosition(data.confUser.position));
						$("#mobile").text(getMobile(data.confUser.mobile));
						//$("#photo").html("<img src='"+data.confUser.photo+"'>");
						$("#isHeadset").prop("checked", false);
						$("#isHeadset").val("0");
			 			$("#isHeadset").prop("disabled",true);
			 			$("#useridCopy").prop("value", data.confUser.id);
			 			alert("该参会人员已签入且已持有同声传译耳机");
					}
					$("#userid").val("");
					//window.location.reload();
				}
			});
		}
		
		function loadInfoByMail(){
			
			$.ajax({
				url : "<%=basePath%>checkinuser/fforCheckinbymail?mail="+$("#mail").val(),
				//url : "<%=basePath%>signinuser/mf/"+$("#mail").val(),
				type : "get",
				success : function(data) {
					if(data.OperatorState == "false"){
						$("#state").val("0");//签入失败，状态为0
						$("#isHeadset").prop("disabled",false);
						$("#isHeadset").val("0");
						$("#isHeadset").prop("checked", false);
						$("#useridCopy").prop("value", $("#userid").val());
						clear();
						
						if(data.Message == "该用户没有注册该会议日程"){
							alert(data.Message);
						}else if(data.Message == "该用户没有注册会议或没有通过审核。请联系组委会确认。"){
							alert(data.Message);
						}
						
						checkIn();//没有注册，同样记录一笔签入失败的记录
						//window.location.reload();
					}else if(data.OperatorState == "true" && data.ifHeadset == "false"){
						$("#state").val("1");
						$("#cardType").text(getCardType(data.confUser.cardType));
						$("#app-userType").text(getUserType(data.userType));
						$("#cname").text(data.confUser.cname);
						$("#ename").text(data.confUser.ename);
						$("#sex").text(getSex(data.confUser.sex));
						$("#company").text(getCompany(data.confUser.company));
						$("#position").text(getPosition(data.confUser.position));
						$("#mobile").text(getMobile(data.confUser.mobile));
						//$("#photo").html("<img src='"+data.confUser.photo+"'>");
						$("#isHeadset").prop("disabled",false);
						$("#isHeadset").prop("checked", true);
						$("#isHeadset").val("1");
						$("#useridCopy").prop("value",data.confUser.id);
						
					}else if(data.OperatorState == "true" && data.ifHeadset == "true"){
						$("#state").val("1");
						$("#cardType").text(getCardType(data.confUser.cardType));
						$("#app-userType").text(getUserType(data.userType));
						$("#cname").text(data.confUser.cname);
						$("#ename").text(data.confUser.ename);
						$("#sex").text(getSex(data.confUser.sex));
						$("#company").text(getCompany(data.confUser.company));
						$("#position").text(getPosition(data.confUser.position));
						$("#mobile").text(getMobile(data.confUser.mobile));
						//$("#photo").html("<img src='"+data.confUser.photo+"'>");
						$("#isHeadset").prop("checked", false);
						$("#isHeadset").val("0");
						$("#isHeadset").prop("disabled",true);
			 			$("#useridCopy").prop("value", data.confUser.id);
			 			alert("该参会人员已签入且已持有同声传译耳机");
					}
					$("#mail").val("");
					//window.location.reload();
				}
			});
		}
		
		function checkIn(){
			if ($("#useridCopy").val()!=null && $("#useridCopy").val().length==32) {
				$.ajax({
					url : "<%=basePath%>checkinuser/s",
					type : "GET",
				
					data:"userId="+$("#useridCopy").val()+"&isHeadset="+$("#isHeadset").val()+"&checkState="+$("#checkState").val()+"&state="+$("#state").val(),
					success : function(data) {
						if(data == "Success"){
						//alert("签入成功");
							window.location.reload();
						}	
					}
				});
			} else {
				alert("数据格式错误，请点击确认后再试一次");
				window.location.reload();
			}
		}
		
		//清空用户信息字段值
		function clear(){
			$("#cardType").text("");
			$("#app-userType").text("");
			$("#cname").text("");
			$("#ename").text("");
			$("#sex").text("");
			$("#company").text("");
			$("#position").text("");
			$("#mobile").text("");
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
	<div  class="qd">
    	<div class="query">
			<table>
				<tbody>
					<tr>
						<td colspan="2" style="text-align:left;">
							<label>二维码签入:</label>
						</td>
					</tr>
					<tr>
						<td width="200">
							<input name="userId" type="text" id="userid"  class="sign_control"/></td>
						<td>
							<label id="user_submit_query"  class="ljm_search">查询</label>
						</td>
					</tr>
					<tr>
						<td height="10"></td>
						<td></td>
					</tr>
					<tr>
						<td colspan="2" style="text-align:left;"><label>邮箱签入:</label></td>
					</tr>
					<tr>
						<td><input class="sign_control" name="mail" type="text" id="mail" />
						</td>
						<td><label id="mail_submit_query" class="ljm_search">查询</label>
						</td>
					</tr>
					<tr>
						<td>
							<input type="checkbox" name="isHeadset" id="isHeadset" value="0"><label>同声传译耳机</label>
							<input name="checkState" id="checkState" type ="hidden" value ="1"/>
							<input name="state" id="state" type ="hidden" value ="1"/>
						</td>
						<td style="text-align:center;padding-top:10px;">
							<label id="checkin" class="ljm_search">签入</label>	
						</td>
					</tr>
					
				</tbody>
			</table>
		</div>

		<div class="user">
				<div class="fitem">
					<label>身份卡类型:</label> <label id="cardType">
				</div>
				<div class="fitem">
					<label>用户类型:</label>
					<label id="app-userType">
				</div>
				<div class="fitem">
					<label>姓名全称:</label>
					<label id = "cname" name="cname" >
				</div>
				<div class="fitem">
					<label>中（英）文名:</label>
					<label id = "ename" name="ename">
				</div>
				<div class="fitem">
					<label>性别:</label>
					<label id="sex" >
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
				<%--
				<div class="fitem">
					<label>照片:</label>
					<div id="photo" name="photo"></div>
				</div>	
				--%>
				<input name="useridCopy" id="useridCopy" type ="hidden"/>
		</div>
		</div>
  	</body>
</html>