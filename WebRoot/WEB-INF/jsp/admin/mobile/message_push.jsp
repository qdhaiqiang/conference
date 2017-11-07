<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	response.setDateHeader("Expires", 0);
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<style type="text/css">
	.fitem {
	    margin-bottom: 5px;
	}
	</style>
	<jsp:include page="../../../../include/sys-common.jsp" />
	
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <script type="text/javascript" src="<%=basePath%>js/InsertContent.js"></script>
    <script type="text/javascript">
		var datagrid;
		var selectUserType = "";
		var selectUserCompany = "";
		var selectUserName ="";
		$(document).ready(function(){
			addLabel();
		    closeEditor();
		    $('#dg').datagrid('hideColumn','id');
		    $("#user_submitSearch").bind('click',function(){
	            if (!$("#select").form('validate')) {
	                return false;
	            }else {
	                selectUserName = $("#selectUserName").val().replace(" ","");
	                selectUserType = $("#selectUserType").combobox("getValues");
	                selectUserType+="";
	                selectUserCompany = $("#selectUserCompany").val().replace(" ","");
	                datagrid.datagrid({
	                    pageNumber : 1
	                });
	            }
	        });
	        $("#user_resetSearch").bind('click',function(){
	            $("#selectUserName").val("");
	            $("#selectUserType").combobox('setValue',"");
	            $("#selectUserCompany").val("");
	        });
		});  
		function loadGrid()  {
			datagrid = $('#dg').datagrid({
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
				onBeforeLoad:function(param){
	                param.cname = selectUserName;
	                param.company = selectUserCompany;
	                param.userType = selectUserType;
            },
			});
			$('#dg').datagrid("clearSelections");
		}

	</script>
  </head>
  
  <body>
  	
  	<div style="width:100%; height:100%;" id="tablegrid">
		<table id="dg" title="会议通知" style="width:100%;height:100%"
        	data-options="striped:true,rownumbers:true,singleSelect:false,url:'confUserMessagePushController/r',method:'post', idField:'id',
       				 multiSort:true,fit:true,nowrap:false,toolbar:'#toolbar',pagination:'true'">
	        <thead>
	            <tr>
	                <th data-options="field:'ck',checkbox:true"></th>
	                <th data-options="field:'id'"></th>
	                <th data-options="field:'cname', width:'15'">姓名</th>
	                <th data-options="field:'sex', width:'4', formatter:getUserSex">性别</th>
	                <th data-options="field:'user_type', formatter:getUserType, width:'15' ">类型</th>
	                <th data-options="field:'mobile', width:'15'">手机号码</th>
	                <th data-options="field:'email', width:'25'">电子邮箱</th>
	                <th data-options="field:'company', width:'56'">公司</th>
	            </tr>
	        </thead>
	    </table>
	</div>
	
	<div id="toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true" onclick="pushMessage()" >推送</a>
			<!-- <a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true" onclick="pushToAll()" >全部推送</a> -->
		<div float:right>
           	<form id="select" method="post">
           		<label>姓名:</label><input name="selectUserName" id="selectUserName" type="text"  class="easyui-validatebox"  validType="searchParm"/>
           		<label>用户类型:</label><input name="selectUserType" id="selectUserType" class="easyui-combobox" editable="false"  maxlength="50"
               		data-options="valueField:'code',textField:'name', url:'dict/r/user_type',method:'get'" />
               	<label>公司名称:</label><input name="selectUserCompany" type="text" id="selectUserCompany"  class="easyui-validatebox"  validType="searchParm"/>
               	<input class="search-btn" id="user_submitSearch" type="button" value="查询" />
               	<input class="search-btn" id="user_resetSearch" type="button" value="重置" />
           	</form>
        </div>
	</div>
	
	<div id="ueditor-win" class="easyui-dialog"
        style="width:70%;height:85%;padding:10px 20px" closed="true"
        maximizable="true" resizable="true"
        left="150" top="0"
        buttons="#dlg-buttons">
		<div align="center">
			<form id="ff" method="post">
				<table cellpadding="5">
					<tr>
						<td>标签:</td>
						<td><a id="label_name" style="cursor:pointer; font-style: italic; color: Gray;">姓名</a>
						<a id="label_meeting" style="cursor:pointer; font-style: italic; color: Gray;">会议名称</a>
						<!-- <a id="label_time" style="cursor:pointer; font-style: italic; color: Gray;">开始时间</a>
						<a id="label_location" style="cursor:pointer; font-style: italic; color: Gray;">地点</a> -->
						<a style="color:Red">  (点击标签在光标处添加相应动态信息)</a>
						</td>
					</tr>
					<tr>
						<td>标题:</td>
						<td><input type="text" id="title" name="title" style="width:540px" data-options="required: false"></textarea> </td>
					</tr>
					<tr>
						<td>推送内容:</td>
						<td><textarea id="content" name="content" maxlength="1024"
								style="width:540px;height:160px" data-options="required: false"></textarea> </td>
					</tr>
					<tr>
						<td>预览:</td>
						<td><textarea id="preview" name="preview" maxlength="1024"
								style="width:540px;height:160px" data-options="required: false" readonly="readonly"></textarea></td>
					</tr>
					<tr>
						<td>推送类型:</td>
						<td>
							<input id="smsPush" type="checkbox"></input>短信
							<input id="emailPush" type="checkbox"></input>邮件
							<input id="appPush" type="checkbox"></input>APP
						</td>
					</tr>
				</table>
			</form>
		
		    <div id="dlg-buttons">
					<button class="easyui-linkbutton" id="saveId" iconCls="icon-ok" onclick="push()" style="width:90px">推送</button>
					<button class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#ueditor-win').dialog('close')" style="width:90px">关闭</button>
			</div>
		</div>
 	</div>
	<script type="text/javascript">
		var userSex;
		var userType;
		var rows;
	    $(function(){
	    	// 获取性別
	    	$.ajax({
	    		url: 'dict/r/sex',
	    		method:'get',
	    		async:false,
	    		success:function(data) {
	    			userSex = data;
	    		}
	    	});
	    	
	    });
	    
	    $(function(){
	    	// 获取用戶類型
	    	$.ajax({
	    		url: 'dict/r/user_type',
	    		method:'get',
	    		async:false,
	    		success:function(data) {
	    			userType = data;
	    		}
	    	});
	    });
	    
	    $("#selectUserType").combobox({
            	url:'dict/r/user_type',
            	method:'get',
            	valueField:'code',
            	textField:'name',
            	multiple:true,
                editable:false,
                panelHeight:'auto'
            });
        function addLabel(){
	         $("#label_name").click(function(){
	         	$("#content").insertContent("{user}");
	         	return false;
	         });
	         $("#label_meeting").click(function(){
	         	$("#content").insertContent("{meeting_name}");
	         	return false;
	         });
// 	         $("#label_date").click(function(){
// 	         	$("#content").insertContent("{meeting_start_date}");
// 	         	return false;
// 	         });
	         $("#label_time").click(function(){
	         	$("#content").insertContent("{meeting_start_time}");
	         	return false;
	         });
	         $("#label_location").click(function(){
	         	$("#content").insertContent("{meeting_location}");
	         	return false;
	         });
	    }

		function pushMessage() {
			clearForm();
			$("#saveId").linkbutton("enable");
			$('#ff').form('reset');
 		    rows = $("#dg").datagrid('getSelections');
 		    if (rows.length==0) {
              	$.messager.alert('提示', "未选中！");
              	return;
           	} else {
		    	openEditor();
			}
		}
		function pushToAll(){
			clearForm();
			rows = getAllUser();
			if(rows.length==0) {
				$.messager.alert('提示',"无用户!");
			} else {
				openEditor();
			}
		}
		function getAllUser(){
			var allUser;
			$.ajax({
				url:'confUserMessagePushController/all',
				method:'post',
				async:false,
				success:function(data){
					allUser = data;
				}
			});
			return allUser;
		}
		function push() {
			$("#saveId").linkbutton("disable");
			var ids="";
			var gateway = "";
			var message="";
			var title="";
			for(var i=0;i<rows.length;i++){
				ids += rows[i].id+",";
			}
			if($("#smsPush").prop("checked")){
				gateway += "SMS,";
			}
			if ($("#emailPush").prop("checked")) {
				gateway += "EMAIL,";			
			}
			if($("#appPush").prop("checked")){
				gateway += "APP";
			}
			if(gateway ==""){
				$.messager.alert("提示","未选择发送方式");
				$("#saveId").linkbutton("enable");
				return;
			}
			message = $("#content").val();
			title = $("#title").val();
			$.ajax({
				url:'<%=basePath%>confUserMessagePushController/push',
				method:'POST',
				data:{'ids':ids, 'message':message, 'gateway':gateway, 'title':title},
				success:function(result){
						$.messager.alert('提示',result);
						closeEditor();
				}
			});
			
		}
		
		function getUserSex(value,row,index) {
			for (var i=0; i<userSex.length; i++) {
        		if (value == userSex[i].code) {
        			return userSex[i].name;
        		}
        	}
		}
		function getUserType(value, row, index){
			for (var i=0; i<userType.length; i++){
				if( value == userType[i].code){
					return userType[i].name;
				}
			}
		}
		
		function synchronize(){
			var txt = $("#content").val();
			var replaceContent = txt.replace(/{user}/g,"XXX").replace(/{meeting_name}/g,"中山会议").replace(/{meeting_start_time}/g,"2014/11/13 09:00").replace(/{meeting_location}/g,"国贸大厦");
// 			 replaceContent = txt.replace(/{meeting_name}/g,"中山会议");
// 			 replaceContent = txt.replace(/{meeting_start_date}/g,"2014/11/13");
// 			 replaceContent = txt.replace(/{meeting_start_time}/g,"2014/11/13 09:00");
// 			 replaceContent = txt.replace(/{meeting_location}/g,"国贸大厦");
			$("#preview").val(replaceContent);
			setTimeout("synchronize()",100);
		}
		
		function clearForm() {
			return;
		}
		
		function closeEditor() {
			$('#ueditor-win').dialog('close');
			loadGrid();
			//$('#dg').datagrid('reload');
		}
		
		function openEditor() {
			$("#ueditor-win").show();
            $('#ueditor-win').dialog('open').dialog('setTitle', '推送信息');
			synchronize();
		}


		
	</script>
    
  </body>
</html>
 
