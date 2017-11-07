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
	var supplier = "";
	var principal = "";
	var seledEquipType = "";
	var datagrid;
	$(document).ready(function(){
		loadGrid();
		$("#submitSearch").bind('click',function(){
			if (!$("#select").form('validate')) {
				return false;
			}else {
				supplier = $("#supplier").val().replace(" ","");
				principal = $("#principal").val().replace(" ","");
				seledEquipType = $("#sequipType").combobox("getValues");
				datagrid.datagrid({
					pageNumber : 1
				});
			}
		});
		$("#resetSearch").bind('click',function(){
			$("#supplier").val("");
			$("#principal").val("");
			$("#sequipType").combobox('setValue',"");
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
				param.supplier = supplier;
				param.principal = principal;
				var equipTypes = "";
				for(var i=0;i<seledEquipType.length;i++){
					equipTypes += seledEquipType[i] + ",";
				}
				param.equipType = equipTypes.substring(0, equipTypes.length-1);
			},
		});
	}
	
	// 获取租赁设备类型
    var equipType;
    $(function(){
        $.ajax({
            url: 'dict/r/equip_type',
            method:'get',
            async:false,
            success:function(data) {
                equipType = data;
            }
        });
        //加载设备类型
        $("#sequipType").combobox({
	          	url:'dict/r/equip_type',
	          	method:'get',
	          	valueField:'code',
	          	textField:'name',
	          	multiple:true,  
	            editable:false,  
	            panelHeight:'auto'
	          });
    });

    // 租赁设备类型匹配
    function getEquipType(value, row , index) {
       for (var i=0; i<equipType.length; i++) {
           if (value == equipType[i].code) {
               return equipType[i].name;
           }
       }
   }
   
   // 新建嘉宾
	var url;
	function newEquipLeasing() {
		$('#dlg').dialog('open').dialog('setTitle', '新建租赁信息');
		$('#ff').form('clear');
		url = 'equipLease/add';
	}
	
   	// 编辑表单
   	function editEquipLeasing() {
   		$("#saveId").attr("disabled","true");
   		var row = $('#dg').datagrid('getSelected');
           if (!row) {
              $.messager.alert('提示', "请选中一条记录再进行修改！");
              return;
           } else {
           	$("#saveId").removeAttr('disabled');
   			$('#ff').form('clear');
           	$('#dlg').window('open').dialog('setTitle','编辑租赁项目');
           	$('#ff').form('load', row);
           	$('#dateStart').datetimebox('setText',row.dateStart);
           	$('#dateEnd').datetimebox('setText',row.dateEnd);
           }
           url='equipLease/update';
   	}
   	
   	//保存Form表单
   	function saveForm() {
   		$("#saveId").attr("disabled","true");
   		var dateStart = $('#dateStart').val();
   		var dateEnd = $('#dateEnd').val();
   		if (dateStart > dateEnd) {
   			$("#saveId").removeAttr('disabled');
   			$.messager.alert('提示', "您填写的开始时间和结束时间不符合逻辑，请重新填写。");
   			return;
   		}
   		$('#ff').form('submit', {
			url : url,
			method : "POST",
			success : function(result) {
				$("#saveId").removeAttr('disabled');
				$.messager.alert('提示', result);
				$('#dlg').dialog('close'); 
				$('#dg').datagrid('reload');
			}
		});
		$("#saveId").removeAttr('disabled');
   	}
   	
   	//删除
   	function deleteEquipLeasing() {
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
					url : 'equipLease/delete',
					type : "GET",
					data: {'equipIds':sn},
					success : function(msg) {
						$.messager.alert('提示', msg);
						$('#dg').datagrid('reload');
						$('#dg').datagrid('clearSelections');
					}
				});
			}
		});
	}
   	
   	// 清空表单
    function clearForm() {
        $('#ff').form('clear');
    }
</script>
  </head>
  
  <body>
	<table id="dg" title="设备租赁管理" style="width:100%;height:100%"
		data-options="striped:true,rownumbers:true,singleSelect:false,url:'equipLease/r',method:'POST', idField:'id',
       				 multiSort:true,fit:true,nowrap:false,toolbar:'#toolbar',pagination:'true'">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'id', hidden:'true'"></th>
				<th data-options="field:'equipName', width:'100'">设备名称</th>
				<th data-options="field:'equipType', width:'90', formatter:getEquipType">设备类别</th>
				<th data-options="field:'amount', width:'40'">数量</th>
				<th data-options="field:'supplier', width:'100'">供应商</th>
				<th data-options="field:'principal', width:'70'">负责人</th>
				<th data-options="field:'principalTel', width:'80'">联系电话</th>
				<th data-options="field:'dateStart', width:'80'">开租时间</th>
				<th data-options="field:'dateEnd', width:'80'">终租时间</th>
				<th data-options="field:'other', width:'150'">备注</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:'true'" onclick="newEquipLeasing()">新建</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:'true'" onclick="editEquipLeasing()">编辑</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:'true'" onclick="deleteEquipLeasing()">删除</a>
		<div>
			<form id="select">
				<label>供应商:</label><input name="supplier" type="text" id="supplier"  class="easyui-validatebox"  validType="searchParm"/>
				<label>负责人:</label><input name="principal" type="text" id="principal"  class="easyui-validatebox"  validType="searchParm"/>
				<label>设备类型:</label><input id="sequipType" class="easyui-combobox" editable="false" name="equipType" maxlength="50"/>
				<input id="submitSearch" type="button" value="查询" class="search-btn" />
				<input id="resetSearch" type="button" value="重置" class="search-btn" />
			</form>
		</div>
	</div>
	<div id="dlg" class="easyui-dialog"
		style="width:640px;height:500px;padding:10px 20px" closed="true"
		maximizable="true" resizable="true" collapsible="true"
		left="150" top="0"
		buttons="#dlg-buttons">
	      <div data-options="region:'center'">
	        <form id="ff" method="post">
	        	<div class="fitem">
	        		<input id="id" name="id" type="hidden">
	        	</div>
	            <div class="fitem">
	                <label>设备供应商:</label>
	                <input id="supplier" name="supplier" class="easyui-validatebox" data-options="required: true" style="width: 250px;"/>
	            </div>
	            <div class="fitem">
	                <label>设备类型:</label>
	                <input id="equipType" class="easyui-combobox" editable="false" name="equipType"
								data-options="valueField:'code',textField:'name',required:'true',
									url:'dict/r/equip_type',method:'get'" />
	            </div>
	            <div class="fitem">
	            	<label>设备名称:</label>
	            	<input name="equipName" class="easyui-textbox" data-options="required: true"/>
	            </div>
	            <div class="fitem">
	            	<label>数量:</label>
	            	<input name="amount" class="easyui-textbox" data-options="required: true"/>
	            </div>
	            <div class="fitem">
	            	<label>负责人:</label>
	            	<input name="principal" class="easyui-textbox"/>
	            </div>
	            <div class="fitem">
	            	<label>负责人联系方式:</label>
	            	<input name="principalTel" class="easyui-textbox"/>
	            </div>
	            <div class="fitem">
	            	<label>开租时间:</label>
	            	<input id="dateStart" name="dateStart" class="easyui-datebox" editable="false"/>
	            </div>
	            <div class="fitem">
	            	<label>结租时间:</label>
	            	<input id="dateEnd" name="dateEnd" class="easyui-datebox" editable="false"/>
	            </div>
	            <div class="fitem">
	            	<label>备注:</label>
	            	<textarea id="other" name="other" maxlength="1024" style="width:300px;height:100px"></textarea>
	            </div>
	        </form>
	      </div>
		<div id="dlg-buttons">
			<button class="easyui-linkbutton" id="saveId" iconCls="icon-ok" onclick="saveForm()" style="width:90px">保存</button>
			<button class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</button>
			<button class="easyui-linkbutton"  iconCls="icon-remove" onclick="clearForm()" style="width:90px">重置</button>
		</div>
    </div>
  </body>
</html>
