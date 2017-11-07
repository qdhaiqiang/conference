<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../../../../include/sys-common.jsp" />
<!DOCTYPE html >
<html>
<style type="text/css">
form label {
	display: inline-block;
	width: 60px;
	padding-left:5px;
}
form .combo {
    width: 150px !important;
}
form .textbox{
	width: 150px !important;
}
</style>
  <head>
  	<script type="text/javascript">
  	var cnamef = "";
  	var userTypef = "";
  	var roomTypef = "";
  	var checkInDatef = "";
  	var checkOutDatef = "";
  	var organizerPayf = "";
  	
		var userType;
		var roomType;
		var datagrid;
		$(document).ready(function(){  
	    	loadGrid(); 
	    	
        	
        	loadFindDict();
        	setFindDict();
		});
		
		function loadGrid()  {
			datagrid = $('#dg').datagrid({
	    		url:'meetingUser/getRoomStatic',
	    		method:'POST',
	        	nowrap:false,
	        	loadMsg:'加载中，请稍候...',
	        	fitColumns:true,
	        	pagination : true,//页码
	 		    pageNumber : 1,//初始页码
	 		    pageSize : 15,
	 		    pageList : [15,30,45,60],
	        	detailFormatter:function(index,row){
	            	return '<div style="padding:5px"><table id="ddv-' + index + '"></table></div>';
	        	},
	        	pagination:true,
	        	striped:true,
	        	rownumbers:true,
	        	singleSelect:true, 
  				multiSort:true,
  				fit:true,
  				nowrap:false,
  				toolbar:'#toolbar',
  				pagination:'true',
  				onBeforeLoad:function(param){
					param.cname = cnamef;
					param.userType = userTypef;
					param.roomType = roomTypef;
					param.checkInDate = checkInDatef;
					param.checkOutDate = checkOutDatef;
					param.organizerPay = organizerPayf;
				}
	    	});
		}
	</script>
  </head>
  
  <body>
	<table id="dg" title="酒店导出" style="width:100%;height:100%">
		<thead>
			<tr>
			    <th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field: 'cname'">姓名</th>
				<th data-options="field: 'userType',formatter:getUserType">用户类型</th>
				<th data-options="field: 'certValue'">证件号码</th>
				<th data-options="field: 'mobile'">联系方式</th>
				<th data-options="field: 'roomType',formatter:getRoomType">房间类型</th>
				<th data-options="field: 'checkInDate'">入住日期</th>
				<th data-options="field: 'checkOutDate'">退房日期</th>
				<th data-options="field: 'diffDate'">入住天数</th>
				<th data-options="field: 'organizerPay',formatter:getFeeType">备注</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<form id="select">
			<!-- cname userType roomType checkInDate checkOutDate organizerPay -->
			<!-- 姓名、 用户类型、 房间类型、 入住日期、 退房日期、 费用类型 -->
			<label>姓名:</label> 
				<input name="cname" id="findCname" class="easyui-textbox" validType="searchParm" /> 
			<label>用户类型:</label>
				<input name="userType" id="findUserType" class="easyui-combobox" validType="searchParm" editable="false" /> 
			<label>房间类型:</label> 
				<input name="roomType" id="findroomType" class="easyui-combobox" validType="searchParm" editable="false" /> 
			<label>入住日期:</label>
				<input name="checkInDate" id="findCheckInDate" class="easyui-datebox" validType="searchParm" editable="false" /> 
			<label>退房日期:</label>
				<input name="checkOutDate" id="findCheckOutDate" class="easyui-datebox" validType="searchParm" editable="false" /> 
			<br>
			<label>费用类型:</label> 
				<input name="organizerPay" id="findOrganizerPay" class="easyui-combobox" validType="searchParm" editable="false" />
			
			<input class="search-btn" onclick="setTravelStatic()" type="button" value="查询" style="margin-top: 2px;margin-left: 10px;"/> 
			<input class="search-btn" onclick="resetTravelStatic()" type="button" value="重置" style="margin-top: 2px;margin-left: 10px;"/>
			
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:'true'" onclick="exportRoom()" style="float:right">导出</a>
		</form>
	</div>
	<form id="downform" method="post"></form>
    <script type="text/javascript">
    	/**
			创建新表单
		**/
        function exportRoom() {
        	getSearchParams();
        	$('#downform').form('submit', {
	            url : "meetingUser/exportRoomStatic",
	            method : "POST",
	            onSubmit: function(param){
	            	param.cname = cnamef;
					param.userType = userTypef;
					param.roomType = roomTypef;
					param.checkInDate = checkInDatef;
					param.checkOutDate = checkOutDatef;
					param.organizerPay = organizerPayf;
	            },
	            success : function(result) {
	                $.messager.alert('提示', result);
	            }
	        });
        }
        
        /**
          	 用户类型匹配
        **/
        function getUserType(value, row , index) {
        	for (var i=0; i<userType.length; i++) {
        		if (value == userType[i].code) {
        			return userType[i].name;
        		}
        	}
        }
        
        /**
           	获取房间类型
        **/
        function getRoomType(value, row, index) {
        	for (var i=0; i<roomType.length; i++) {
        		if (value == roomType[i].code) {
        			return roomType[i].name;
        		}
        	}
        }
        
        /**
           	获取备注类型
        **/
        function getFeeType(value, row, index) {
        	if (value=="0") {
        		return "参会人员承担";
        	} else if (value=="1") {
        		return "主办方承担";
        	}
        }
        
        function loadFindDict(){
        	// 获取用户类型
        	$.ajax({
        		url: 'dict/r/user_type',
        		method:'get',
        		async:false,
        		success:function(data) {
        			userType = data;
        		}
        	}); 
        	// 获取房间类型
        	$.ajax({
        		url: 'dict/r/room_type',
        		method:'get',
        		async:false,
        		success:function(data) {
        			roomType = data;
        		}
        	});
        	
        	
        }
        
        function setFindDict(){
       	 	$("#findUserType").combobox({
                data: userType,//json格式的数据
                valueField:'code',
                textField:'name'
            });
       	 	$("#findroomType").combobox({
                data: roomType,//json格式的数据
                valueField:'code',
                textField:'name'
            });
       	 	var payParam0 = {};
       	 	payParam0["code"] = "";
      		payParam0["name"] = "不选择";
       	 	var payParam1 = {};
     		payParam1["code"] = "0";
     		payParam1["name"] = "参会人员承担";
     		var payParam2 = {};
     		payParam2["code"] = "1";
     		payParam2["name"] = "主办方承担";
     		var organizerPayList = new Array();
     		organizerPayList[0] = payParam0;
     		organizerPayList[1] = payParam1;
     		organizerPayList[2] = payParam2;
			$("#findOrganizerPay").combobox({
            	data: organizerPayList,//json格式的数据
            	valueField:'code',
            	textField:'name'
        	});
       }
        
        function setTravelStatic(){
        	if (!$("#select").form('validate')) {
    			return false;
    		}else {
    			getSearchParams();
              	//重新请求加载数据
              	datagrid.datagrid({
    				pageNumber : 1
    			});
    		}
        }
        
        function getSearchParams(){
        	cnamef = $("#findCname").val().replace(" ","");
          	userTypef = $("#findUserType").combobox('getValue');
          	roomTypef = $("#findroomType").combobox('getValue');
          	checkInDatef = $("#findCheckInDate").datetimebox("getText");
          	checkOutDatef = $("#findCheckOutDate").datetimebox("getText");
          	organizerPayf = $("#findOrganizerPay").combobox('getValue');
        }
        
        function resetTravelStatic(){
        	$("#select").form('clear');
        	cnamef = "";
          	userTypef = "";
          	roomTypef = "";
          	checkInDatef = "";
          	checkOutDatef = "";
          	organizerPayf = "";
        }
    </script>
  </body>
</html>