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
    <!-- 首先需要引入plupload的源代码 -->
    <script src="<%=basePath%>js/plupload.full.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/fileupload.js"></script>
    <script type="text/javascript">
		var datagrid;
		var selectBoothState = "";
		var selectCompanyName = "";
		var selectBoothType = "";
		$(document).ready(function(){
			loadBoothType();
	    	setBoothTypes();
		    closeEditor();
		    $('#dg').datagrid('hideColumn','boothId');
		    $('#dg').datagrid('hideColumn','meetingId');
		    $("#user_submitSearch").bind('click',function(){
	            if (!$("#select").form('validate')) {
	                return false;
	            }else {
	                selectCompanyName = $("#selectBoothCompany").val().replace(" ","");
	                selectBoothState = $("#selectBoothState").combobox("getValue");
	                selectBoothType = $("#selectBoothType").combobox("getValue");
	                datagrid.datagrid({
	                    pageNumber : 1
	                });
            }
        });
        $("#user_resetSearch").bind('click',function(){
            $("#selectBoothCompany").val("");
            $("#selectBoothState").combobox('setValue',"");
            $("#selectBoothType").combobox('setValue',"");
        });
		});  
		function loadGrid()  {
			datagrid = $('#dg').datagrid({
				url:'confBoothController/r',
				method:'post',
				nowrap:false,
				loadMsg:'加载中，请稍候...',
				fitColumns:true,
				striped:true,
				rownumbers:true,
				singleSelect:false, 
 				multiSort:true,
 				fit:true,
 				nowrap:false,
 				toolbar:'#toolbar',
 				pagination:'true',
				pagination : true,//页码
				pageNumber : 1,//初始页码
				pageSize : 15,
				pageList : [15,30,45,60],
				detailFormatter:function(index,row){
					return '<div style="padding:5px"><table id="ddv-' + index + '"></table></div>';
				},
				pagination:true,
				onBeforeLoad:function(param){
	                param.boothCompany = selectCompanyName;
	                param.boothState = selectBoothState;
	                param.parentId = selectBoothType;
            	},
            	onLoadSuccess:function(data){
            		//重新加载
            		loadBoothType();
        	    	setBoothTypes();
            	}
			});
			
		}

	</script>
  </head>
  
  <body>
  	
  	<div style="width:100%; height:100%;" id="tablegrid">
		<table id="dg" title="展位管理" style="width:100%;height:100%">
	        <thead>
	            <tr>
	                <th data-options="field:'ck',checkbox:true"></th>
	                <th data-options="field:'boothId'"></th>
	                <th data-options="field:'parentId', formatter:getBoothType">展位类型名称</th>
	                <th data-options="field:'meetingId'"></th>
	                <th data-options="field:'exhibitorId', formatter:getContactName">联络人姓名</th>
	                <th data-options="field:'exhibitorContactTel', formatter:getContactTel">联络人电话</th>
	                <th data-options="field:'boothCompany', width:'80' ">公司名称</th>
	                <th data-options="field:'boothName', width:'100'">展位名称</th>
	                <th data-options="field:'boothLintelName', width:'120'">楣板名称</th>
	                <th data-options="field:'boothState', width:'150',formatter:getBoothState">展位状态</th>
	            </tr>
	        </thead>
	    </table>
	</div>
	
	<div id="toolbar">
	<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true" onclick="addBoothInfo()" >添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="editBoothInfo()">修改</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true" onclick="delBoothInfo()">删除</a>
		<div float:right>
            <form id="select" method="post">
            	<label>展位类型名称:</label>
            	<input id="selectBoothType" class="easyui-combobox" editable="false" name="selectBoothType" maxlength="50" />
                <label>公司名称:</label>
                <input name="selectBoothCompany" type="text" id="selectBoothCompany"  class="easyui-validatebox"  validType="searchParm"/>
                <label>展位状态:</label>
                <input id="selectBoothState" class="easyui-combobox" editable="false" name="selectBoothState" maxlength="50" />
                <input id="user_submitSearch" type="button" value="查询" class="search-btn"/>
                <input id="user_resetSearch" type="button" value="重置" class="search-btn"/>
            </form>
        </div>
	</div>
	
	<div id="ueditor-win" class="easyui-dialog" style="width:50%;height:60%;padding:10px 20px" closed="true" maximizable="true" resizable="true" left="150" top="0" buttons="#dlg-buttons">
		<div align="center">
			<form id="ff" method="post">
				<table cellpadding="5">
					<tr>
						<td></td>
						<td><input class="easyui-validatebox" type="hidden" name="boothId" value="" readonly="true"></input>
						</td>
					</tr>
					<tr>
						<td>展位名称</td>
						<td><input class="easyui-validatebox" required="true" id="boothName" name="boothName" style="width:320px;" value=""></input>
						</td>
					</tr>
					<tr>
						<td>展位类型</td>
						<td><input class="easyui-combobox" id="parentId" name="parentId" editable="false" required="true" style="width:320px;" ></input>
						</td>
					</tr>
					<tr>
						<td>展位状态</td>
						<td><input class="easyui-combobox" id="boothState" name="boothState" editable="false" required="true" style="width:320px;" ></input>
						</td>
					</tr>
					<tr id="boothCompanyDiv">
						<td>展商公司名称</td>
						<td><input class="easyui-validatebox" type="text" id = "boothCompany" name="boothCompany" value="" style="width: 320px;"></input>
						</td>
					</tr>
					<tr id="contactNameDiv">
						<td>展商联络人姓名</td>
						<td><input class="easyui-validatebox" type="text" id = "contactName" name="contactName" readonly="true" value="" style="width: 320px;"></input>
						</td>
					</tr>
					<tr id="contactTelDiv">
						<td>展商联络人电话</td>
						<td><input class="easyui-validatebox" type="text" id = "contactTel" name="contactTel" readonly="true" value="" style="width: 320px;"></input>
						</td>
					</tr>
					<tr id="boothLintelNameDiv">
						<td>楣板名称</td>
						<td><input class="easyui-validatebox" id="boothLintelName" name="boothLintelName" style="width: 320px;"  value="" ></input>
						</td>
					</tr>
				</table>
			</form>
		
		    <div id="dlg-buttons">
					<button class="easyui-linkbutton" id="add" iconCls="icon-ok" onclick="add()" style="width:90px">添加</button>
					<button class="easyui-linkbutton" id="edit" iconCls="icon-ok" onclick="edit()" style="width:90px">修改</button>
					<button class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#ueditor-win').dialog('close')" style="width:90px">关闭</button>
			</div>
		</div>
 	</div>
	<script type="text/javascript">
		var boothState;
		var boothType;
		var contact;
		
	    function loadBoothType(){
	    	//获取展位状态
	    	$.ajax({
	    		url: 'dict/r/booth_state',
	    		method:'get',
	    		async:false,
	    		success:function(data) {
	    			boothState = data;
	    		}
	    	});
	    	//获取展位类型
	    	$.ajax({
	    		url: 'confExhibitionController/getTypeList',
	    		method:'get',
	    		async:false,
	    		success:function(data) {
	    			boothType = data;
	    		}
	    	});
	    	//获取申请公司联络人信息
	    	$.ajax({
	    		url: 'front/exhibitCompany/getContactList',
	    		method:'get',
	    		async:false,
	    		success:function(data) {
	    			contact = data;
	    		}
	    	});
	    }
	    
	    function setBoothTypes(){
	    	//将数据加载到控件上
	    	$("#selectBoothState").combobox({
	           	data:boothState,
	           	valueField:'code',
	           	textField:'name',
	           	multiple:false,
	            editable:false,  
	            panelHeight:'auto'
	        });
	    	
	    	$("#selectBoothType").combobox({
	        	data:boothType,
	        	valueField:'exhibitionId',
	        	textField:'exhibitionName',
	        	multiple:false,
	        	editable:false,
	        	panelHeight:'auto'
	        });
	    	
	    	$("#boothState").combobox({
	           	data:boothState,
	           	valueField:'code',
	           	textField:'name',
	           	multiple:false,
	            editable:false,  
	            panelHeight:'auto'
	        });
	    	
	        $("#parentId").combobox({
	        	data:boothType,
	        	valueField:'exhibitionId',
	        	textField:'exhibitionName',
	        	multiple:false,
	        	editable:false,
	        	panelHeight:'auto'
	        });
	    }
	    
		function addBoothInfo() {
			clearForm();
			$('#ff').form('reset');
			$('#boothCompany').val('');
			$('#boothName').val('');
		    $('#add').css('display','inline');
		    $('#edit').css('display','none');
		    openEditor();
		    // 初始化展位类型
		    $("#contactNameDiv").hide();
		    $("#contactTelDiv").hide();
		    $("#boothLintelNameDiv").hide();
		    $("#boothCompanyDiv").hide();
		}
		
		function add() {
			$('#ff').form('submit', {
				url:'<%=basePath%>confBoothController/add',
				method:'POST',
				success:function(data){
						$.messager.alert('提示',data);
						closeEditor();
					}
				});
		}
		
		function getBoothState(value,row,index) {
			for (var i=0; i<boothState.length; i++) {
        		if (value == boothState[i].code) {
        			return boothState[i].name;
        		}
        	}
		}
		function getBoothType(value,row,index){
			for(var i = 0; i<boothType.length; i++){
				if(value == boothType[i].exhibitionId){
					return boothType[i].exhibitionName;
				}
			}
		}
		function getContactName(value,row,index){
			for(var i =0; i<contact.length;i++){
				if(value == contact[i].companyId){
					return contact[i].contactPerson;
				}
			}
		}
		function getContactTel(value,row,index){
			for(var i = 0; i<contact.length; i++){
				if(row.exhibitorId == contact[i].companyId){
					return contact[i].contactTel;
				}
			}
		}
		
		function delBoothInfo(){
			var ids = "";
			var rows = $('#dg').datagrid('getSelections');
			var num=rows.length;//获取要删除信息的个数
			if(num > 0){
				for(var i=0; i<rows.length; i++){//组成一个字符串，ID主键之间用逗号隔开
					if(i!=rows.length-1){
						ids=ids+rows[i].boothId+",";
				    }else{
				   		ids=ids+rows[i].boothId;
				    }
				}
				$.messager.confirm("提示", "是否删除选中的"+num+"条数据?", function (r) {
				if(r){
					 $.ajax({  
				          type:"GET",  
				          url:'<%=basePath%>confBoothController/delete',  
				          data: {id:ids},  
				          success:function(msg){  
				             $.messager.alert(msg);  
				             loadGrid();
				          }
				     });
				}
				});
			}else{
				$.messager.alert('错误','请选中要删除的模板！');
			}
			
		}
		
		function editBoothInfo(){
			$('#add').css('display','none');
			$('#edit').css('display','inline');
			clearForm();
			var row = $('#dg').datagrid('getSelections');
			var num  = row.length;
			if(num > 1 || num == 0){
				$.messager.alert('提示',"请选中一条记录进行修改");
				return;
			}
			openEditor();
			$("#ff").form("load", row[0]);
			$("#contactNameDiv").show();
		    $("#contactTelDiv").show();
		    $("#boothLintelNameDiv").show();
		    $("#boothCompanyDiv").show();
			for(var i = 0; i<contact.length; i++){
				if(row[0].exhibitorId == contact[i].companyId){
					$("#contactName").val(contact[i].contactPerson);
					$("#contactTel").val(contact[i].contactTel);
				}
			}
			
		}
		
		function edit() {
			$('#ff').form('submit', {
				url:'<%=basePath%>confBoothController/update',
				method:'POST',
				success:function(data){
						closeEditor();
						$.messager.alert('提示',data);
					}
				});
		}
		
		function clearForm() {
			$("#contactName").val("");
			$("#contactTel").val("");
		}
		
		function closeEditor() {
			$('#ueditor-win').dialog('close');
			loadGrid();
		}
		
		function openEditor() {
			$("#ueditor-win").show();
            $('#ueditor-win').dialog('open').dialog('setTitle', '展位信息');
		}


		
	</script>
    
  </body>
</html>
 
