<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../../../include/sys-common.jsp" />
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html >
<html>
<head>
<script src="<%=basePath%>js/common.js"></script>
<style type="text/css">
.info {
	padding-left: 5px;
	display: inline-block;
	/*border: 1px solid #95B8E7;*/
}

.info label {
	width: 120px;
	display: inline-block;
	text-align: left;
	padding-left:10px;
}

.info div {
	float: left;
	width: 49%;
	display: flex;
	line-height: 26px;
	padding-bottom:4px;
}

.info input,select {
	width: 200px;
}

select {
	border: 1px solid #95B8E7;
	-moz-border-radius: 5px 5px 5px 5px;
	-webkit-border-radius: 5px 5px 5px 5px;
	border-radius: 5px 5px 5px 5px;
}

input {
	border: 1px solid #95B8E7;
	background-color: #fff;
	vertical-align: middle;
	display: inline-block;
	overflow: hidden;
	white-space: nowrap;
	margin: 0;
	padding: 4px;
	-moz-border-radius: 5px 5px 5px 5px;
	-webkit-border-radius: 5px 5px 5px 5px;
	border-radius: 5px 5px 5px 5px;
}
h4{
	padding:5px 0 5px 15px;
}
</style>

</head>

<body>
	<form id="travel-info">
		<div style="height:40px;padding:15px 0 0 20px;background:#eff8ff;">
			<label>请输入电子邮箱地址：</label>
			<input class="easyui-validatebox" type="email" name="email" id="email">
			<label>请输入姓名：</label>
			<input class="easyui-validatebox" type="text" name="s_cname" id="s_cname">
			<button id="search" type="button" class="search-btn">查询</button>
		</div>
		<div class="info">
			<h4>基本信息</h4>
			<div>
				<label class="">姓名：</label><input id="cname" name="cname"
					readonly="readonly" />
			</div>
			<div>
				<label>证件号：</label><input readonly="readonly" id="certValue" />
			</div>
			<div>
				<label class="">用户类型：</label><input id="userTypeName" 
					readonly="readonly" />
			</div>
			<div>
				<label>移动电话：</label><input readonly="readonly" id="mobile" name="mobile" />
			</div>
			<div>
				<label>启程出发地：</label><input readonly="readonly" id="departurePlace" />
			</div>
			<div>
				<label>返程目的地：</label><input id="returnPlace" readonly="readonly" />
			</div>
			<div>
				<label>启程出发日期：</label><input id="departureDate" readonly="readonly" />
			</div>
			<div>
				<label>返程出发日期：</label><input id="returnDate" readonly="readonly" />
			</div>
			<div>
				<label>启程出发时间段：</label><input id="departureTimePeriod"
					readonly="readonly" />
			</div>
			<div>
				<label>返程出发时间段：</label><input id="returnTimePeriod"
					readonly="readonly" />
			</div>

		</div>
		<input type="hidden" name="id" id="travelId">
		<input type="hidden" name="userId" id="userId">
		<input type="hidden" name="userType" id="userType">
		<div class="info">
			<h4>出发行程</h4>
			<div>
				<label class="">交通类型：</label> <select name="typeCome">
					<option value="1">飞机</option>
					<option value="2">高铁</option>
				</select>
			</div>
			<div> 
				<label>班次：</label><input class="easyui-textbox" name="numberCome" />
			</div>
			<div>
				<label>出发地：</label><input class="easyui-textbox"
					name="startPlaceCome" />
			</div>
			<div>
				<label>目的地：</label><input class="easyui-textbox" name="endPlaceCome" />
			</div>
			<div>
				<label>出发时间：</label> 
				<input id="startTimeCome" name="startTimeCome" class="easyui-datetimebox" editable="false" data-options="showSeconds:false"/>
			</div>
			<div>
				<label>到达时间：</label> 
				<input id="endTimeCome" name="endTimeCome" class="easyui-datetimebox" editable="false" data-options="showSeconds: false"/>
			</div>
			<div style="width:100%">
				<label>备注：</label>
				<textarea rows="2" cols="76" name="remarkCome"></textarea>
			</div>

		</div>
		<div class="info">
			<h4>返回行程</h4>
			<div>
				<label class="">交通类型：</label> <select name="typeGo">
					<option value="1">飞机</option>
					<option value="2">高铁</option>
				</select>
			</div>
			<div>
				<label>班次：</label><input class="easyui-textbox" name="numberGo" />
			</div>
			<div>
				<label>出发地：</label><input class="easyui-textbox" name="startPlaceGo" />
			</div>
			<div>
				<label>目的地：</label><input class="easyui-textbox" name="endPlaceGo" />
			</div>
			<div>
				<label>出发时间：</label> 
				<input id="startTimeGo" name="startTimeGo" class="easyui-datetimebox" editable="false" data-options="showSeconds: false"/>
			</div>
			<div>
				<label>到达时间：</label>
				<input id="endTimeGo" name="endTimeGo" class="easyui-datetimebox" editable="false" data-options="showSeconds: false"/>
			</div>

			<div style="width:100%">
				<label>备注：</label>
				<textarea rows="2" cols="76" name="remarkGo"></textarea>
			</div>
		</div>
		<div class="info" style="width:100%;padding:0;">
			<h4 style="padding-left:20px;">通知</h4>
			<div>
				<label>通知类型：</label><input type='checkbox' name='noticeEmail' style="width:20px;margin-top:7px" value="1">邮件通知<br>
				<input type='checkbox' name='noticeMsg' style="width:20px;margin-top:7px" value="2">短信通知

			</div>
			
		</div>
		<button id="submit" type="button"
				style="margin:5px auto;display: block" class="search-btn">提交</button>
	</form>
	<script type="text/javascript">
	
	$(document).ready(function() {
		//根据email查询参会人员信息
		$("#search").click(function() {
			var email = $("#email").val();
			var scname = $("#s_cname").val();
			clearForm();
			$("#email").val(email);
			$("#s_cname").val(scname);
			if(email=="" && scname==""){
				$.messager.alert("提示","请输入电子邮箱或姓名");
				return;
			}
			$.ajax({
				type : "post",
				url : 'travel/r',
				data : {
					email : email,
					cname : scname
				},
				success : function(result) {
					if(result.status=="1"){
						$("#userId").val(result.userId);
						$("#userType").val(result.userType);
						$("#userTypeName").val(getUserType(result.userType));
						$("#certValue").val(result.certValue);
						$("#cname").val(result.cname);
						$("#mobile").val(result.mobile);
						$("#departurePlace").val(result.departurePlace);
						$("#departureDate").val(result.departureDate);
						$("#departureTimePeriod").val(result.departureTimePeriod);
						$("#returnPlace").val(result.returnPlace);
						$("#returnDate").val(result.returnDate);
						$("#returnTimePeriod").val(result.returnTimePeriod);
						$('#travel-info').form('load', result.travel);
						$("#startTimeCome").datetimebox('setText',result.travel.startTimeCome);
						$("#endTimeCome").datetimebox('setText',result.travel.endTimeCome);
						$("#startTimeGo").datetimebox('setText',result.travel.startTimeGo);
						$("#endTimeGo").datetimebox('setText',result.travel.endTimeGo);
						//行程信息不能修改了。。。
						/*if($("#travelId").val()!=""){
							$("#submit").attr({"disabled":"disabled"});
						}else{
							$("#submit").removeAttr("disabled");//将按钮可用
						}*/						
					}else{
					    //$("#submit").removeAttr("disabled");//将按钮可用
						$.messager.alert("提示","未找到该参会人员信息，请核对邮箱或姓名是否正确！");
						clearForm();
						$("#email").val(email);
					}
				}
			});
		});

		//提交行程信息。
		$("#submit").click(function() {
			
			$("#submit").attr({"disabled":"disabled"});
			var startTimeCome = $("#startTimeCome").datetimebox("getText");
			var endTimeCome = $("#endTimeCome").datetimebox("getText");
			if(dateLargeThan(startTimeCome,endTimeCome)){
				$.messager.alert("提示","启程 出发时间不能大于到达时间！");
				$("#submit").removeAttr("disabled");//将按钮可用
				return false;
			}
			
			/*if(endTimeCome!="" && dateLargeThan(new Date(),endTimeCome)){
				alert("启程 到达时间应晚于当前时间！");
				return false;
			}*/
			
			var startTimeGo = $("#startTimeGo").datetimebox("getText");
			var endTimeGo = $("#endTimeGo").datetimebox("getText");
			if(dateLargeThan(startTimeGo,endTimeGo)){
				$.messager.alert("提示","返程 出发时间不能大于到达时间！");
				$("#submit").removeAttr("disabled");//将按钮可用
				return false;
			}
			
			if($("#userId").val()==""){
				$.messager.alert("提示","请先查询出用户基本信息！");
				$("#submit").removeAttr("disabled");//将按钮可用
				return;
			}
			
			var msg = $("input[name='noticeMsg']:checked").val(); 
			
			if($("#mobile").val()=="" && msg!=undefined){
				$.messager.alert("提示","该用户未维护移动手机号码，不能发送短消息，请重新选择提醒方式！");
				$("#submit").removeAttr("disabled");//将按钮可用
				return;
			}
			
			var form = $("#travel-info");

			$.ajax({
				url : 'travel/save',
				method : "post",
				data : form.serialize(),
				success : function(result) {
					$.messager.alert("提示","保存成功！");
					$("#email").val("");
					$("#email").focus();
					clearForm();
				}
			});

		});
		
		var clearForm = function(){
			$("#travel-info").form('clear');
			$("[name='typeCome']").val("1");
			$("[name='typeGo']").val("1");
			$("#submit").removeAttr("disabled");//将按钮可用
		};
		
		
		// 获取用户类型
	    var userType;
	    $(function(){
	        $.ajax({
	            url: 'dict/r/user_type',
	            method:'get',
	            async:false,
	            success:function(data) {
	                userType = data;
	            }
	        });
	    });
	     
	    //用户类型匹配
	    function getUserType(value) {
	        for (var i=0; i<userType.length; i++) {
	            if (value == userType[i].code) {
	                return userType[i].name;
	            }
	        }
	    }

	});
</script>
</body>

</html>
