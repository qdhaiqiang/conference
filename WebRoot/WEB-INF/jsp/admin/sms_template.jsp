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
	<jsp:include page="../../../include/sys-common.jsp" />
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <script type="text/javascript">
	
		$(document).ready(function(){  
		    closeEditor();
		    $('#dg').datagrid('hideColumn','id');
		    // $('#dg').datagrid('hideColumn', 'name');
		});  
		function loadGrid()  {
			$('#dg').datagrid({
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
			});
		}

	</script>
  </head>
  
  <body>
  	
  	<div style="width:100%; height:100%;" id="tablegrid">
		<table id="dg" title="短信模板管理" style="width:100%;height:100%"
			data-options="striped:true,rownumbers:true,singleSelect:true,url:'confSmsTempalteController/r',method:'get', 
       				 multiSort:true,fit:true,nowrap:false,toolbar:'#toolbar',pagination:'true'">
			<thead>
				<tr id="tr">
					<th data-options="field:'ck',checkbox:true"></th>
					<th data-options="field:'id',width:$(this).width() * 0,nowrap:'false',align:'left'">模板Id</th>
					<th data-options="field:'name',width:$(this).width() * 0.2,nowrap:'false',align:'left'">模板名称</th>
					<th data-options="field:'userType',width:$(this).width() * 0.2,nowrap:'false',align:'left',formatter:convertUserType">用户类型</th>
					<th data-options="field:'messageType',width:$(this).width() * 0.2,nowrap:'false',align:'left',formatter:getTemplateType">模板类型</th>
					<!-- <th data-options="field:'content',width:$(this).width() * 0.6,nowrap:'false',align:'left',formatter:reduceContent">模板内容</th> -->
				</tr>
			</thead>
		</table>
	</div>
	
	<div id="toolbar">
	<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true" onclick="addTemplateInfo()" >添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="editTemplateInfo()">修改</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true" onclick="delTemplateInfo()">删除</a>
	</div>
	
	<div id="ueditor-win" class="easyui-dialog"
        style="width:50%;height:85%;padding:10px 20px" closed="true"
        maximizable="true" resizable="true"
        left="150" top="0"
        buttons="#dlg-buttons">
		<div align="center">
			<form id="ff" method="post">
				<table cellpadding="5">
					<tr>
						<td></td>
						<td><input class="easyui-validatebox" type="hidden" name="id" value="" readonly="true"></input>
						</td>
					</tr>
					<tr>
						<td>模板名称:</td>
						<td><input class="easyui-validatebox" type="text" name="name" value="" data-options="required: true" style="width: 320px;"></input>
						</td>
					</tr>
					<tr>
						<td>用户类型:</td>
						<td><input class="easyui-combobox" id="userType" name="userType" style="width:540px;" data-options="required: true" value=""></input>
						</td>
					</tr>

					<tr>
						<td>模板类型:</td>
						<td><input class="easyui-combobox" id="messageType" name="messageType" style="width: 540px;" data-options="required: true" value="" ></input>
						</td>
					</tr>
					<tr>
						<td>模板内容:</td>
						<td>
							<textarea id="content" name="content" maxlength="1024"
								style="width:320px;height:200px" data-options="required: true"></textarea> 
    							
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
		var templateType;
		var userType;
	    $(function(){
	    	// 获取短信模板类型
	    	$.ajax({
	    		url: 'dict/r/sms_type',
	    		method:'get',
	    		async:false,
	    		success:function(data) {
	    			templateType = data;
	    		}
	    	});
	    	
	    	// 获取用户类型
	    	$.ajax({
	    		url: 'dict/r/user_type',
	    		method:'get',
	    		async:false,
	    		success:function(data) {
	    			userType = data;
	    		}
	    	});
	    });
		function addTemplateInfo() {
			clearForm();
			$('#ff').form('reset');
			$('#name').val('');
			$('#content').val('');
		    $('#add').css('display','inline');
		    $('#edit').css('display','none');
		    openEditor();
		    // 初始化用户类型
			$("#userType").combobox({
            	url:'dict/r/user_type',
            	method:'get',
            	valueField:'code',
            	textField:'name',
            	multiple:true,  
                editable:false,  
                panelHeight:'auto'
            });
			$("#messageType").combobox({
			url:'dict/r/sms_type',
            	method:'get',
            	valueField:'code',
            	textField:'name',
            	multiple:false,  
                editable:false,  
                panelHeight:'auto'
			});
		}
		
		function add() {
			if($("#content").val()==""){
				$.messager.alert('提示',"请输入短信内容");
				return;
			}
			$('#ff').form('submit', {
				url:'<%=basePath%>confSmsTempalteController/add',
				method:'POST',
				success:function(data){
						$.messager.alert('提示',data);
						closeEditor();
					}
				});
		}
		
		function getTemplateType(value,row,index) {
			for (var i=0; i<templateType.length; i++) {
        		if (value == templateType[i].code) {
        			return templateType[i].name;
        		}
        	}
		}
		
		function convertUserType(value) {
			var retValue = "";
			if (value!=null) {
				var checkedUserType = value.split(",");
				for (var i=0; i<userType.length;i++) {
					for (var j=0; j<checkedUserType.length; j++) {
						if (checkedUserType[j]==userType[i].code) {
							retValue += userType[i].name+",";
							break;
						}
					}
				}
			}
			return retValue.substring(0,retValue.length-1);
		}
		
		function delTemplateInfo(){
			var ids = "";
			var rows = $('#dg').datagrid('getSelections');
			var num=rows.length;//获取要删除信息的个数
			if(num > 0){
				for(var i=0; i<rows.length; i++){//组成一个字符串，ID主键之间用逗号隔开
					if(i!=rows.length-1){
						ids=ids+rows[i].id+",";
				     }else{
				    	 ids=ids+rows[i].id;
				     } 
					} 
					$.messager.confirm("提示", "是否删除选中的"+num+"条数据?", function (r) {
					if(r){
						 $.ajax({  
					          type:"GET",  
					          url:'<%=basePath%>confSmsTempalteController/delete',  
					          data: {id:ids},  
					          success:function(msg){  
					             $.messager.alert('提示',msg);  
					             loadGrid();
					          }  
					       });
					}
					});
			}else{
				$.messager.alert('错误','请选中要删除的模板！');
			}
			
		}
		
		function editTemplateInfo(){
			$('#add').css('display','none');
			$('#edit').css('display','inline');
			var row = $('#dg').datagrid('getSelections');
			var num  = row.length;
			if(num > 1 || num == 0){
				$.messager.alert('提示',"请选中一条记录进行修改");
				return;
			}
			openEditor();
			$("#ff").form("load", row[0]);
			for (var i=0; i<templateType.length; i++) {
        		if (row[0].name == templateType[i].code) {
        			$("#lblTemplateName").html(templateType[i].name);
        		}
        	}
			// 初始化用户类型
			$("#userType").combobox({
            	url:'dict/r/user_type',
            	method:'get',
            	valueField:'code',
            	textField:'name',
            	multiple:true,  
                editable:false,  
                panelHeight:'auto'
            });
			// 绑定用户类型
			$("#userType").combobox('setValues',row[0].userType.split(','));
			$("#messageType").combobox({
			url:'dict/r/sms_type',
            	method:'get',
            	valueField:'code',
            	textField:'name',
            	multiple:false,  
                editable:false,  
                panelHeight:'auto'
			});
			$("#messageType").combobox('setValues',row[0].messageType);
		}
		
		function edit() {
			if($("#content").val()==""){
				$.messager.alert('提示',"请输入短信内容！");
				return;
			}
			$('#ff').form('submit', {
				url:'<%=basePath%>confSmsTempalteController/update',
				method:'POST',
				success:function(data){
						closeEditor();
						$.messager.alert('提示',data);
					}
				});
		}
		
		function clearForm() {
		}
		
		function closeEditor() {
			$('#ueditor-win').dialog('close');
			loadGrid();
		}
		
		function openEditor() {
			$("#ueditor-win").show();
            $('#ueditor-win').dialog('open').dialog('setTitle', '短信模板');
		}
		
		function reduceContent(value) {
			value = delHtmlTag(value);
			if (value.indexOf("。") !== -1) {
				return value.split("。")[0] +"...";
			}
			else if (value.indexOf(".") !== -1) {
				return value.split(".")[0] +"...";
			}
			return value;
		}
		
		 function delHtmlTag(str){
  			return str.replace(/<[^>]+>/g,"");//去掉所有的html标记
 		}
		
	</script>
    
  </body>
</html>
