<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <jsp:include page="../../../include/sys-common.jsp" />
<style type="text/css">
#ff {
	margin: 0;
	padding: 10px 30px;
}
.fitem {
	margin-bottom: 5px;
}

.fitem label {
	display: inline-block;
	width: 80px;
}

.fitem input {
	width: 180px;
}
.fitem input[type="checkbox"] {
	width: 20px;
}
</style>
<script type="text/javascript">

var name="";
var type="";
var chargePerson="";
$(document).ready(function(){
		loadGrid();
		$("#quantityFormDiv").hide();
		$("#submitSearch").bind('click',function(){
			if (!$("#select").form('validate')) {
				return false;
			}else {
				name = $("#name").val().replace(" ","");
				chaegePerson = $("#charge_person").val().replace(" ","");
				type = $("#type").val().replace(" ","");
				datagrid.datagrid({
					pageNumber : 1
				});
			}
		});
		$("#resetSearch").bind('click',function(){
			$("#name").val("");
			$("#chargePerson").val("");
			$("#type").val("");
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
				param.name = name;
				param.type = type;
				param.chargePerson = chargePerson;
			},
		});
	}
   	
	var url;
	function newMaterialReg(){
		$('#dlg').dialog('open').dialog('setTitle', '新建物料信息');
		$('#ff').form('clear');
		url = 'materialReg/add';
	}
  	// 编辑表单
   	function editMaterialReg() {
   		$("#save-btn").attr("disabled","true");
   		var row = $('#dg').datagrid('getSelected');
           if (!row) {
              $.messager.alert('提示', "请选中一条记录再进行修改！");
              return;
           } else {
           	$("#save-btn").removeAttr('disabled');
   			$('#ff').form('clear');
           	$('#dlg').window('open').dialog('setTitle','编辑物料信息');
           	$('#ff').form('load', row);
           }
           url='materialReg/update';
   	}
   	//删除
   	function deleteMaterialReg() {
		var rows = $('#dg').datagrid('getSelections');
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
				$.ajax({
					url : 'materialReg/delete',
					type : "GET",
					data: {'equipIds':sn},
					success : function(msg) {
						if(msg == "fail"){
							$.messager.alert('提示', '此物料在分会场中已存在,先删除分会场物料再删除！');
						}else{
							$.messager.alert('提示', '删除成功！');
							$('#dg').datagrid('reload'); // reload the user data,loadGrid();
							$('#dg').datagrid('clearSelections');
						}
						
					}
				});
			}
		});
	}
   	
   	// 清空表单
    function clearForm() {
        $('#ff').form('clear');
    }
   	//保存Form表单
   	function saveForm() {
   		$('#ff').form('submit', {
			url : url,
			method : "POST",
			success : function(result) {
				$.messager.alert('提示', result);
				$('#dlg').dialog('close'); 
				$('#dg').datagrid('reload');
			}
		});
   	}
	
</script>
  </head>
  
  <body>
	<table id="dg" title=物料登记 style="width:100%;height:100%"
		data-options="striped:true,rownumbers:true,singleSelect:false,url:'materialReg/r',method:'get', idField:'id',
       				 multiSort:true,fit:true,nowrap:false,toolbar:'#toolbar',pagination:'true'">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'id', hidden:'true'"></th>
				<th data-options="field:'name', width:'40'">物料名称</th>
				<th data-options="field:'type', width:'40'">物料类别</th>
				<!-- <th data-options="field:'quantity', width:'20'">数量</th> -->
				<th data-options="field:'chargePerson', width:'20'">负责人</th>
				<th data-options="field:'detail', width:'200'">备注</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:'true'" onclick="newMaterialReg()">新建</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:'true'" onclick="editMaterialReg()">编辑</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:'true'" onclick="deleteMaterialReg()">删除</a>
    
		<div>
			<form id="select">
				<label>名称:</label><input name="name" type="text" id="name"  class="easyui-validatebox"  validType="searchParm"/>
				<label>类型:</label><input name="type" type="text" id="type"  class="easyui-validatebox"  validType="searchParm"/>
				<label>负责人 :</label><input id="chargePerson" class="easyui-validatebox" name="chargePerson" validType="searchParm"/>
				<input id="submitSearch" type="button" value="查询" class="search-btn" />
				<input id="resetSearch" type="button" value="重置" class="search-btn" />
			</form>
		</div>
	</div>
	<div id="dlg" class="easyui-dialog"
		style="width:640px;height:500px;padding:10px 20px" closed="true"
		maximizable="true" resizable="true"
		left="150" top="0"
		buttons="#dlg-buttons">
	      <div data-options="region:'center'">
	        <form id="ff" method="post">
	        	<div class="fitem">
	        		<input id="id" name="id" type="hidden">
	        	</div>
	        	<div class="fitem">
	            	<label>物料名称:</label>
	            	<input id="name" name="name" class="easyui-textbox" data-options="required: true" style="width: 250px;"/>
	            </div>
	            <div class="fitem">
	                <label>物料类型:</label>
	                <input id="type" class="easyui-textbox" name="type" data-options="required: true" style="width: 250px;"/>
	            </div>
	            
	            <div class="fitem" id="quantityFormDiv">
	            	<label>数量:</label>
	            	<input name="quantity" type="text" value="0" />
	            </div>
	            <div class="fitem">
	            	<label>负责人:</label>
	            	<input name="chargePerson" class="easyui-textbox"/>
	            </div>
	            <div class="fitem">
	            	<label>备注:</label>
	            	<textarea id="detail" name="detail" maxlength="1024" style="width:300px;height:100px"></textarea>
	            </div>
	        </form>
	      </div>
		<div id="dlg-buttons">
			<button class="easyui-linkbutton" id="saveId" iconCls="icon-ok" onclick="saveForm()" style="width:90px">保存</button>
			<button class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</button>
			<button class="easyui-linkbutton" iconCls="icon-remove" onclick="clearForm()" style="width:90px">重置</button>
		</div>
    </div>

  </body>
</html>
