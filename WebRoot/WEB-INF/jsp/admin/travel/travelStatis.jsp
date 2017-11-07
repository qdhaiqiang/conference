<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../../../../include/sys-common.jsp" />
<!DOCTYPE html >
<html>
<style type="text/css">
form label {
	display: inline-block;
	width: 80px;
	padding-left:5px;
}
form .combo {
    width: 150px !important;
}
form .textbox{
	width: 150px !important;
}
form label{
	margin-top: 2px;
}
</style>
  <head>
  	<script type="text/javascript">
	  	var cnamef = ""; 
	  	var userTypef = ""; 
	  	var checkpointf = ""; 
	  	var messageSendf = "";
	  	var typeComef = ""; 
	  	var numberComef = ""; 
	  	var startPlaceComef = ""; 
	  	var endPlaceComef = ""; 
	  	var startTimeComef = ""; 
	  	var endTimeComef = ""; 
	  	var typeGof = ""; 
	  	var numberGof = ""; 
	  	var startPlaceGof = ""; 
	  	var endPlaceGof = ""; 
	  	var startTimeGof = ""; 
	  	var endTimeGof = ""; 
  	
		var userType;
		var datagrid;
		$(document).ready(function(){  
	    	loadGrid();
	    	// 获取所有的字典，combobox用的字典
        	loadFindDict();
        	setFindDict();
		});
		
		function loadGrid()  {
			datagrid = $('#dg').datagrid({
	    		url:'travel/getTravelStatic',
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
	        	resizable:true,
	        	onBeforeLoad:function(param){
					param.cname = cnamef;
					param.userType = userTypef;
					param.checkpoint = checkpointf;
					param.messageSend = messageSendf;
					
					param.typeCome = typeComef;
					param.numberCome = numberComef;
					param.startPlaceCome = startPlaceComef;
					param.endPlaceCome = endPlaceComef;
					param.startTimeCome = startTimeComef;
					param.endTimeCome = endTimeComef;
					
					param.typeGo = typeGof;
					param.numberGo = numberGof;
					param.startPlaceGo = startPlaceGof;
					param.endPlaceGo = endPlaceGof;
					param.startTimeGo = startTimeGof;
					param.endTimeGo = endTimeGof;
				}
	    	});
		}
	</script>
  </head>
  
  <body>
  	<table id="dg" title="票务统计" style="width:100%;height:100%" >
        <thead>
            <tr>
                <th data-options="field:'ck',checkbox:true"></th>
                <th data-options="field: 'aa', formatter:jumpMessagePage">票务通知</th>
                <th data-options="field: 'cname'">姓名</th>
                <th data-options="field: 'userType',formatter:getUserType">用户类型</th>
                <th data-options="field: 'emailSend',formatter:getSendMessage">邮件通知</th>
                <th data-options="field: 'messageSend',formatter:getSendMessage">短信通知</th>
                <th data-options="field: 'typeCome',formatter:getTraveType">启程方式</th>
                <th data-options="field: 'numberCome'">启程航班号</th>
                <th data-options="field: 'startPlaceCome'">启程出发地</th>
                <th data-options="field: 'endPlaceCome'">启程目的地</th>
                <th data-options="field: 'startTimeCome'">启程出发时间</th>
                <th data-options="field: 'endTimeCome'">启程到达时间</th>
                <th data-options="field: 'typeGo',formatter:getTraveType">返程方式</th>
                <th data-options="field: 'numberGo'">返程航班号</th>
                <th data-options="field: 'startPlaceGo'">返程出发地</th>
                <th data-options="field: 'endPlaceGo'">返程目的地</th>
                <th data-options="field: 'startTimeGo'">返程出发时间</th>
                <th data-options="field: 'endTimeGo'">返程到达时间</th>
                <th data-options="field: 'checkpoint'">送关服务</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
		<form id="select">
	    	<!-- 姓名  用户类型 是否需要送关 -->
	    	<h4 class="h_prompt">基本信息条件</h4>
	        <label>姓名:</label>
            <input name="userName" id="findUserName" class="easyui-textbox" validType="searchParm" />               
            <label>用户类型:</label>
            <input name="userType" id="findUserType" class="easyui-combobox" validType="searchParm" editable="false"/>
            <label>送关服务:</label>
            <input name="checkpoint" id="findCheckpoint" class="easyui-combobox" validType="searchParm" editable="false"/>      
            <label>票务通知:</label>
            <input name="messageSend" id="findMessageSend" class="easyui-combobox" validType="searchParm" editable="false"/>      
            <br><!-- 启程出行方式  启程航班号  启程出发地  启程目的地  启程出发时间  启程到达时间 -->
            <h4 class="h_prompt">出行信息条件</h4>
            <label>启程出行方式:</label>
            <input name="typeCome" id="findTypeCome" class="easyui-combobox" validType="searchParm" editable="false"/>
            <label>启程航班号:</label>
            <input name="numberCome" id="findNumberCome" class="easyui-textbox" validType="searchParm" />
            <label>启程出发地:</label>
            <input name="startPlaceCome" id="findStartPlaceCome" class="easyui-textbox" validType="searchParm" />
            <label>启程目的地:</label>
            <input name="endPlaceCome" id="findEndPlaceCome" class="easyui-combobox" validType="searchParm" data-options="valueField:'end_place_come',textField:'end_place_come',url:'travel/getDistinctEndPlaceCome'"/>
            <br><br>
            <label>启程出发时间:</label>
            <input name="startTimeCome" id="findStartTimeCome" class="easyui-datebox" editable="false" validType="searchParm" />
            <label>启程到达时间:</label>
            <input name="endTimeCome" id="findEndTimeCome" class="easyui-datebox" editable="false" validType="searchParm" />
            <br><!-- 返程出行方式  返程航班号  返程出发地  返程目的地  返程出发时间  返程到达时间  -->
            <h4 class="h_prompt">返程信息条件</h4>
            <label>返程出行方式:</label>
            <input name="typeGo" id="findTypeGo" class="easyui-combobox" validType="searchParm" editable="false"/>
            <label>返程航班号:</label>
            <input name="numberGo" id="findNumberGo" class="easyui-textbox" validType="searchParm" />
            <label>返程出发地:</label>
            <input name="startPlaceGo" id="findStartPlaceGo" class="easyui-combobox" validType="searchParm" data-options="valueField:'start_place_go',textField:'start_place_go',url:'travel/getDistinctStartPlaceGo'"/>
            <label>返程目的地:</label>
            <input name="endPlaceGo" id="findEndPlaceGo" class="easyui-textbox" validType="searchParm" />
            <br><br>
            <label>返程出发时间:</label>
            <input name="startTimeGo" id="findStartTimeGo" class="easyui-datebox" editable="false" validType="searchParm" />
            <label>返程到达时间:</label>
            <input name="endTimeGo" id="findEndTimeGo" class="easyui-datebox" editable="false" validType="searchParm" />
            <input class="search-btn" id="find_submit" onclick="setTravelStatic()" type="button" value="查询"/> 
            <input class="search-btn" id="find_reset" onclick="resetTravelStatic()" type="button" value="重置"/>
            <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:'true'" onclick="exportTravel()" style="float:right">导出</a>
        </form>
	            
        <form id="downform" method="post"> </form>
    </div>
    <div id="dlg" class="easyui-dialog"	style="width:500px;height:200px;padding:20px 40px" closed="true" maximizable="true" resizable="true" buttons="#dlg-buttons">
	      <div data-options="region:'center'">
	        <form id="ff" method="post">
	        	<div class="fitem">
	        		<input id="travelId" name="travelId" type="hidden">
	        		<input id="userType" name="travelUserType" type="hidden">
	        	</div>
	            <div class="fitem">
	            	<label>通知类型：</label>
	            	<input type='checkbox' name='noticeEmail' style="width:20px;margin-top:7px" value="1">邮件通知&nbsp;&nbsp;
					<input type='checkbox' name='noticeMsg' style="width:20px;margin-top:7px" value="2">短信通知
	            </div>
	        </form>
	      </div>
	      <div id="dlg-buttons" data-options="region:'south',border:false" style="text-align: right;margin-bottom: 15px;">
	        <button class="easyui-linkbutton" id="save-btn" data-options="iconCls:'icon-ok'" onclick="sendMessage()" style="width:90px">发送</button>
	        <button class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</button>
	      </div>
    </div>
    <script type="text/javascript">
    	/**
			创建新表单
		**/
        function exportTravel() {
        	getSearchParams();
            $('#downform').form('submit', {
	            url : "travel/exportTravelStatic",
	            method : "POST",
	            onSubmit: function(param){
	            	param.cname = cnamef;
					param.userType = userTypef;
					param.checkpoint = checkpointf;
					param.messageSend = messageSendf;
					
					param.typeCome = typeComef;
					param.numberCome = numberComef;
					param.startPlaceCome = startPlaceComef;
					param.endPlaceCome = endPlaceComef;
					param.startTimeCome = startTimeComef;
					param.endTimeCome = endTimeComef;
					
					param.typeGo = typeGof;
					param.numberGo = numberGof;
					param.startPlaceGo = startPlaceGof;
					param.endPlaceGo = endPlaceGof;
					param.startTimeGo = startTimeGof;
					param.endTimeGo = endTimeGof;
	            },
	            success : function(result) {
	                $.messager.alert('提示', result);
	            }
	        });
        }
        
        /**
           	出行方式匹配
        **/
        function getTraveType(value,row,index) {
        	if (value=="1") {
        		return "飞机";
        	} else if (value=="2") {
        		return "高铁";
        	}
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
        
        function getSendMessage(value,row,index) {
        	if (value=="0") {
        		return "否";
        	} else if (value="1") {
        		return "是";
        	}
        	return "";
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
        }
        
        function setFindDict(){
        	$("#findUserType").combobox({
                 data: userType,//json格式的数据
                 valueField:'code',
                 textField:'name'
            });
        	var payParam0 = {};
        	payParam0["code"] = "";
       		payParam0["name"] = "不选择";
        	var payParam1 = {};
      		payParam1["code"] = "是";
      		payParam1["name"] = "是";
      		var payParam2 = {};
      		payParam2["code"] = "否";
      		payParam2["name"] = "否";
      		var organizerPayList = new Array();
      		organizerPayList[0] = payParam0;
      		organizerPayList[1] = payParam1;
      		organizerPayList[2] = payParam2;
        	//送关
        	$("#findCheckpoint").combobox({
            	data: organizerPayList,//json格式的数据
            	valueField:'code',
            	textField:'name'
        	});
			
			var messageParam0 = {};
        	messageParam0["code"] = "";
       		messageParam0["name"] = "不选择";
        	var messageParam1 = {};
      		messageParam1["code"] = "1";
      		messageParam1["name"] = "是";
      		var messageParam2 = {};
      		messageParam2["code"] = "0";
      		messageParam2["name"] = "否";
      		var messageList = new Array();
      		messageList[0] = messageParam0;
      		messageList[1] = messageParam1;
      		messageList[2] = messageParam2;
        	//票务通知
        	$("#findMessageSend").combobox({
            	data: messageList,//json格式的数据
            	valueField:'code',
            	textField:'name'
        	});
        	   	
        	var travelTypeParam0 = {};
        	travelTypeParam0["code"] = "";
       		travelTypeParam0["name"] = "不选择";
        	var travelTypeParam1 = {};
      		travelTypeParam1["code"] = "1";
      		travelTypeParam1["name"] = "飞机";
      		var travelTypeParam2 = {};
      		travelTypeParam2["code"] = "2";
      		travelTypeParam2["name"] = "高铁";
      		var travelTypeList = new Array();
      		travelTypeList[0] = travelTypeParam0;
      		travelTypeList[1] = travelTypeParam1;
      		travelTypeList[2] = travelTypeParam2;
         	//出行方式
         	$("#findTypeCome").combobox({
            	data: travelTypeList,//json格式的数据
            	valueField:'code',
            	textField:'name'
        	});
         	$("#findTypeGo").combobox({
            	data: travelTypeList,//json格式的数据
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
//               	hideOrShowForms();
                $('#cc').layout('collapse','north');
              	datagrid.datagrid({
    				pageNumber : 1
    			});
    		}
        }
        
        function getSearchParams(){
        	cnamef = $("#findUserName").val().replace(" ","");
          	userTypef = $("#findUserType").combobox('getValue');
          	checkpointf = $("#findCheckpoint").combobox('getValue');
          	messageSendf = $("#findMessageSend").combobox('getValue');
          	
          	typeComef = $("#findTypeCome").combobox('getValue');
          	numberComef = $("#findNumberCome").val().replace(" ","");
          	startPlaceComef = $("#findStartPlaceCome").val().replace(" ","");
          	endPlaceComef = $("#findEndPlaceCome").combobox('getValue');
          	startTimeComef = $("#findStartTimeCome").datetimebox("getText");
          	endTimeComef = $("#findEndTimeCome").datetimebox("getText");
          	
          	typeGof = $("#findTypeGo").combobox('getValue');
          	numberGof = $("#findNumberGo").val().replace(" ","");
          	startPlaceGof = $("#findStartPlaceGo").combobox('getValue');
          	endPlaceGof = $("#findEndPlaceGo").val().replace(" ","");
          	startTimeGof = $("#findStartTimeGo").datetimebox("getText");
          	endTimeGof = $("#findEndTimeGo").datetimebox("getText");
        }
        
        function resetTravelStatic(){
        	$("#select").form('clear');
        	cnamef = "";
          	userTypef = "";
          	checkpointf = "";
          	messageSendf = "";
          	
          	typeComef = "";
          	numberComef = "";
          	startPlaceComef = "";
          	endPlaceComef = "";
          	startTimeComef = "";
          	endTimeComef = "";
          	typeGof = "";
          	numberGof = "";
          	startPlaceGof = "";
          	endPlaceGof = "";
          	startTimeGof = "";
          	endTimeGof = "";
        }
        
        // 跳转到会议通知页面
        function jumpMessagePage(value,row,index) {
        	return '<a onclick="goMessagePage(\'' + row.id+ '\',\'' + row.userType+ '\')" style="cursor:pointer;"><span style="color:blue;">发送</span></a>';
        }
        
        function goMessagePage(travelId,userType) {
        	$('#ff').form('clear');
        	$("#travelId").val(travelId);
        	$("#userType").val(userType);
            $('#dlg').window('open').dialog('setTitle','票务发送');
        }
        
        // 发送通知
        function sendMessage() {
        	$("#save-btn").attr("disabled","true");
        	var email_chk_value =[];    
			$('input[name="noticeEmail"]:checked').each(function(){    
			   email_chk_value.push($(this).val());    
			}); 
			var message_chk_value =[];    
			$('input[name="noticeMsg"]:checked').each(function(){    
			   message_chk_value.push($(this).val());    
			});
			if (email_chk_value.length==0 && message_chk_value.length==0) {
				$("#save-btn").removeAttr('disabled');
                $.messager.alert('提示', "请选择通知类型");
                return;
			}
        	$('#ff').form('submit', {
				url : 'travel/sendMessage',
				method : "POST",
				success : function(result) {
					$.messager.alert('提示', result);
					$("#dlg").window('close'); // close the schedule form
					$('#dg').datagrid('reload'); // reload the schedule data
				}
			});
        }
        //切换隐藏与显示
       /*  function hideOrShowForms(){
        	$("#select").slideToggle("fast");//切换隐藏与显示
        	if($("#toolbar").css("disply") == "none"){
        		$("#toolbar").css("disply","block");
        	}else{
        		$("#toolbar").css("disply","none");
        	}
        } */
    </script>
  </body>
</html>