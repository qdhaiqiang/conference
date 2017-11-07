<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<jsp:include page="../../../include/sys-common.jsp" />
<style type="text/css">
#provider-form {
	margin: auto;
}

.fitem {
	padding: 5px;
}

.fitem label {
	display: inline-block;
	width: 80px;
	vertical-align: top;
}

.fitem input {
	width: 250px;
	padding: 5px;
	border-radius: 5px;
}
 
</style>
<script type="text/javascript">
	 
	//初始化查询条件
	var providerName = "";
	var service = "";
	var manager = "";
 
	var datagrid;

	$(document).ready(function(){
		
		//加载表格数据
		loadGrid();
		
		//点击查询按钮
		$("#submitSearch").click(function(){
			//alert("search");
			 
			providerName = $("#providerName").val().replace(" ","");
			service = $("#service").val().replace(" ","");
			manager = $("#managerName").val().replace(" ","");
			datagrid.datagrid({
				pageNumber : 1
			});
			 
		});
 
		//重置查询条件
		$("#resetSearch").click(function(){
			//alert("reset");
			$("#providerName").val("");
			$("#service").val("");
			$("#managerName").val("");
		});
		 
	});

	function loadGrid()  {
		datagrid = $('#provider-dg').datagrid({
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
				param.providerName = providerName;
				param.service = service;
				param.manager = manager;
			} 
		});
	}

   // 新建嘉宾
	var url;
	function addProvider() {
		//alert("addProvider");
		$('#dlg').dialog('open').dialog('setTitle', '新增外包商');
		$('#provider-form').form('clear');
		url = 'serviceprovider/add';
	}
	
   	// 编辑表单
   	function editProvider() {
   		 //alert("edit");
   		var row = $('#provider-dg').datagrid('getSelected');
        if (!row) {
           $.messager.alert('提示', "请选中一条记录再进行修改！");
           return;
        } else {
			$('#provider-form').form('clear');
        	$('#dlg').window('open').dialog('setTitle','编辑租赁项目');
        	$('#provider-form').form('load', row);
        }
        url='serviceprovider/update';
   	}
   	
   	//保存Form表单
   	function saveForm() {
   		//alert("save");
   		$('#provider-form').form('submit', {
			url : url,
			method : "POST",
			success : function(result) {
				$.messager.alert('提示', result);
				$('#dlg').dialog('close'); 
				$('#provider-dg').datagrid('reload');
			}
		});
   	}
   	
   	//删除
   	function deleteProvider() {
		var rows = $('#provider-dg').datagrid('getSelections');
		if (!rows) {
			$.messager.alert('提示', "请选中一条记录再进行删除");
			return;
		}
		var num=rows.length;//获取要删除信息的个数
		var sn = "";
		for(var i=0; i<rows.length; i++){//组成一个字符串，ID主键之间用逗号隔开
			if(i!=rows.length-1){
				sn=sn+rows[i].id+",";
		     }else{
		    	 sn=sn+rows[i].id;
		     }
		}
		$.messager.confirm('确认', '确定要删除这'+ num+'条记录吗？', function(r) {
			if (r) {
 				//alert("delete");
				$.ajax({
					url : 'serviceprovider/delete',
					type : "GET",
					data: {'providerIds':sn},
					success : function(msg) {
						$.messager.alert('提示', msg);
						$('#provider-dg').datagrid('reload'); // reload the user data,loadGrid();
						$('#provider-dg').datagrid('clearSelections');
					}
				});
			}
		});
	}
   	
   	// 清空表单
    function clearForm() {
        $('#provider-form').form('clear');
    }
</script>
</head>

<body>
	<table id="provider-dg" title="外包服务商管理" style="width:100%;height:100%"
		data-options="striped:true,rownumbers:true,singleSelect:false,idField:'id',url:'serviceprovider/r',method:'get',
       				 multiSort:true,fit:true,nowrap:false,toolbar:'#toolbar',pagination:'true'">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'id', hidden:'true'"></th>
				<th data-options="field:'providerName', width:'100'">外包服务商名称</th>
				<th data-options="field:'service', width:'100'">服务</th>
				<th data-options="field:'manager', width:'40'">负责人</th>
				<th data-options="field:'managerContact', width:'50'">联系方式</th>
				<th data-options="field:'remarks', width:'70'">备注</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:'true'"
			onclick="addProvider()">新建</a> <a href="javascript:void(0)"
			class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:'true'"
			onclick="editProvider()">编辑</a> <a href="javascript:void(0)"
			class="easyui-linkbutton"
			data-options="iconCls:'icon-remove',plain:'true'"
			onclick="deleteProvider()">删除</a>
		<div>
			<form id="search-form">
				<label>外包服务商:</label><input name="providerName" type="text"
					id="providerName" class="easyui-validatebox" validType="searchParm" />
				<label>服务:</label><input name="service" type="text" id="service"
					class="easyui-validatebox" validType="searchParm" /> 
				<label>负责人:</label><input name="manager" type="text" id="managerName"
					class="easyui-validatebox" validType="searchParm" />
				<input id="submitSearch" type="button"
					value="查询" class="search-btn" /> <input id="resetSearch" type="button" value="重置" class="search-btn" />
			</form>
		</div>
	</div>
	<div id="dlg" class="easyui-dialog"
		style="width:600px;height:400px;padding:20px 100px" closed="true"
		maximizable="true" resizable="true" left="150" top="0"
		buttons="#dlg-buttons">
		<div data-options="region:'center'">
			<form id="provider-form" method="post">
				<div class="fitem">
					<input id="id" name="id" type="hidden">
				</div>
				<div class="fitem">
					<label>外包商名称:</label> <input id="providerName" name="providerName"
						class="easyui-validatebox" data-options="required: true"
						style="width: 250px;" maxlength="40"/>
				</div>
				<div class="fitem">
					<label>服务:</label> <input id="service" class="easyui-validatebox"
						name="service" data-options="required: true" style="width: 250px;" maxlength="200" />
				</div>
				<div class="fitem">
					<label>负责人:</label> <input name="manager" class="easyui-validatebox" maxlength="20"/>
				</div>
				<div class="fitem">
					<label>联系电话:</label> <input name="managerContact"
						class="easyui-validatebox"  maxlength="20"/>
				</div>
				<div class="fitem">
					<label>备注:</label>
					<textarea id="remarks" name="remarks" maxlength="200"
						style="width:250px;height:100px"></textarea>
				</div>
			</form>
		</div>
		<div id="dlg-buttons">
			<button class="easyui-linkbutton" id="saveId" iconCls="icon-ok" onclick="saveForm()" style="width:90px">保存</button>
			<button class="easyui-linkbutton" iconCls="" onclick="clearForm()" style="width:90px">清空</button>
			<button class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">关闭</button>
		</div>

	</div>

</body>
</html>
