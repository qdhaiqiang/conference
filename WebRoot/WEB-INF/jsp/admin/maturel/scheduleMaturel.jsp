<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="../../../../include/sys-common.jsp" />
<html>
<style type="text/css">
.fitem {
	margin-bottom: 5px;
}

.fitem label {
	display: inline-block;
	width: 80px;
}
 
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	//搜索查询条件
	var schduelId = "";
	var materialType = "";
	var materialName = "";
	var materialLeader = "";
	var datagrid;
	
	$(document).ready(function(){
		loadGrid();
		$("#find_submit").bind('click',setSearchForm);
		$("#find_reset").bind('click',resetSearchForm);
		loadSchduleList(); 
		setSchduelSearch();
	});
	function loadGrid()  {
		datagrid = $('#dg').datagrid({
			url:'materialSchedule/find',
			method:'post',
			idField:'id',
			striped:true,
			rownumbers:true,
			singleSelect:false,
			nowrap:false,
			loadMsg:'加载中，请稍候...',
			fit:true,
			fitColumns:true,
			multiSort:true,
			pagination : true,//页码
			pageNumber : 1,//初始页码
			pageSize : 15,
			pageList : [15,30,45,60],
			pagination:true,
			toolbar:'#toolbar',
			onBeforeLoad:function(param){
				param.schduelId = schduelId;
				param.materialType = materialType;
				param.materialName = materialName;
				param.materialLeader = materialLeader;
			},
			onLoadSuccess:function(data){
				loadSchduleList();
				setSchduelSearch();
			}
		});
	}
	
	
	//点击搜索时，放入搜索条件
	function setSearchForm(){
		if (!$("#select").form('validate')) {
			return false;
		}else {
			schduelId = $("#findSchduelId").combobox('getValue');
			materialType = $("#findMaterialType").combobox('getValue');
			if(materialType == "0"){ materialType = ""; }
			materialName = $("#findMaterialName").val().replace(" ","");
			materialLeader = $("#findMaterialLeader").val().replace(" ","");
			datagrid.datagrid({
				pageNumber : 1
			});
		}
	}
	//重置查询条件
	function resetSearchForm(){
		$("#select").form('clear');
		schduelId = "";
		materialType = "";
		materialName = "";
		materialLeader = "";
	}
	
    var schduleList = new Array();
    var materialRegList = new Array();
    var materialTypeList = new Array();
    var isStateList = new Array();
    function loadSchduleList(){
    	$.ajax({
            url: 'schedule/findByMeetingId',
            method:'get',
            async:false,
            success:function(data) {
            	schduleList = data;
            }
        });
    	
    	$.ajax({
            url: 'materialReg/findAll',
            method:'get',
            async:false,
            success:function(data) {
            	materialRegList = data;
            }
        });
    	
    	//初始化物料类型，分会场物料和场地建设
    	var materialTypeParam1 = {};
    	materialTypeParam1["code"] = "0";
    	materialTypeParam1["name"] = "未选择";
    	var materialTypeParam2 = {};
    	materialTypeParam2["code"] = "1";
    	materialTypeParam2["name"] = "会场物料";
    	var materialTypeParam3 = {};
    	materialTypeParam3["code"] = "2";
    	materialTypeParam3["name"] = "场地搭建";
    	materialTypeList[0] = materialTypeParam1;
    	materialTypeList[1] = materialTypeParam2;
    	materialTypeList[2] = materialTypeParam3;
    	
    	var RequiredParam0 = {};
    	RequiredParam0["code"] = "false";
    	RequiredParam0["name"] = "否";
    	var RequiredParam1 = {};
    	RequiredParam1["code"] = "true";
    	RequiredParam1["name"] = "是";
    	var RequiredParam2 = {};
    	RequiredParam2["code"] = "";
    	RequiredParam2["name"] = "不选择";
    	isStateList[0] = RequiredParam0;
    	isStateList[1] = RequiredParam1;
    	isStateList[2] = RequiredParam2;
    }
    
    //根据加载来的schduleList，将其放在日程查询的条件中
    function setSchduelSearch(){
    	$("#findSchduelId").combobox({
            data: schduleList,//json格式的数据
            valueField:'id',
            textField:'title'
        });
    	$("#editSchduelId").combobox({
            data: schduleList,//json格式的数据
            valueField:'id',
            textField:'title'
        });
    	
    	$("#findMaterialType").combobox({
            data: materialTypeList,//json格式的数据
            valueField:'code',
            textField:'name'
        });
    	
    	$("#editMaterialType").combobox({
            data: materialTypeList,//json格式的数据
            valueField:'code',
            textField:'name'
        });
    	
    	$("#editMaterialId").combobox({
            data: materialRegList,//json格式的数据
            valueField:'id',
            textField:'name',
            onChange:changeMaterialId
        });
    	
    	$("#materialStateInput").combobox({
            data: isStateList,//json格式的数据
            valueField:'code',
            textField:'name'
        });
    }
    
  	//每条加载的物料中根据日程id显示日程名称
    function setSchduelName(value, row , index) {
       for (var i=0; i<schduleList.length; i++) {
           if (value == schduleList[i].id) {
               return schduleList[i].title;
           }
       }
   }
    
  //每条加载的物料中根据日程id显示日程名称
    function setmaterialType(value, row , index) {
       for (var i=0; i<materialTypeList.length; i++) {
           if (value == materialTypeList[i].code) {
               return materialTypeList[i].name;
           }
       }
   }
  
  function setMaterialState(value, row , index) {
      for (var i=0; i<isStateList.length; i++) {
          if (value == isStateList[i].code) {
              return isStateList[i].name;
          }
      }
  }
  
  //changeMaterialId
  function changeMaterialId(n,o){
	  for (var i=0; i<materialRegList.length; i++) {
          if (materialRegList[i].id == n) {
        	  $("#editMaterialName").textbox("setValue",materialRegList[i].name);
              break;
          }
      }
  }

  function setUpdateState(value, row , index){
	  var html = '';
	  var ids = row.id;
	  html += '<a onclick="updateStateByIds()" style="cursor:pointer;"><span style="color:blue;">已到位</span></a>';
	  /*html += '<a onclick="doUpdateState(1,'+ids+')"><span style="color:blue;">已到位</span></a>';*/
	  return html;
  }
</script>
</head>
<body>
	<table id="dg" title="分会场物料管理" style="width:100%;height:100%">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'setUpdateState',width:'50',formatter:setUpdateState">到位</th>
				<th data-options="field:'schduelId',width:'150',formatter:setSchduelName">日程</th>
				<th data-options="field:'materialType', width:'90',formatter:setmaterialType">物料类别</th>
				<th data-options="field:'materialName', width:'100'">物料名称</th>
				<th data-options="field:'materialNum', width:'100'">物料数量</th>
				<th data-options="field:'materialLeader', width:'100'">物料负责人</th>
				<th data-options="field:'leaderMobile', width:'100'">负责人电话</th>
				<th data-options="field:'memo', width:'100',formatter:setMaterialState">是否到位</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newMaturel()">新建</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editMaturel()">修改</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteMaturel()">删除</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateStateByIds()">已到位</a>		   
		<div float:right>
			<form id="select">
				<label>选择会场:</label>
					<input id="findSchduelId" class="easyui-combobox" validType="searchParm" />	<!-- editable="false" -->					
				<label>物料类型(会场物料/场地搭建):</label>
					<input type="text" id="findMaterialType" class="easyui-combobox" validType="searchParm" />						
				
				<label>物料名称:</label>
					<input type="text" id="findMaterialName" class="easyui-validatebox" validType="searchParm" style="width:80px"/> 
				<label>负责人姓名:</label>
					<input type="text" id="findMaterialLeader" class="easyui-validatebox" validType="searchParm" style="width:80px"/> 
				<input class="search-btn" id="find_submit" type="button" value="查询" /> 
				<input class="search-btn" id="find_reset" type="button" value="重置" />
			</form>
		</div>
	</div>
	<div id="saveDialog" class="easyui-dialog" style="width:400px;height:400px;padding:10px 20px" closed="true"
		maximizable="true" resizable="true" left="350px" top="10px" buttons="#dlg-buttons">
		<form id="savefm"  method="post">
			<table>
				<tr>
					<td valign="top" style="width:300px">
						<div class="fitem">
							<input name="id" type='hidden'>
						</div>
						<div class="fitem">
							<label>选择会场:</label>
							<input name="schduelId" id="editSchduelId" class="easyui-combobox" editable="false" required="true" maxlength="50">
						</div>
						<div class="fitem">
							<label>物料类型:</label>
							<input name="materialType" id="editMaterialType" class="easyui-combobox" editable="false" required="true" maxlength="50">
						</div>
						<div class="fitem">
							<label>已登记物料:</label>
							<input name="materialId" id="editMaterialId" class="easyui-combobox" editable="false" required="true">
						</div>
						<div class="fitem">
							<label>物料别称:</label>
							<input name="materialName" id="editMaterialName" type="text" class="easyui-textbox" required="true" maxlength="50">
						</div>
						<div class="fitem">
							<label>物料数量:</label>
							<input id="materialNumInput" name="materialNum" type="number" class="easyui-textbox" required="true" maxlength="50">
						</div>
						<div class="fitem">
							<label>负责人:</label>
							<input name="materialLeader" type="text" class="easyui-textbox" required="true" maxlength="50">
						</div>
						<div class="fitem">
							<label>负责人电话:</label>
							<input name="leaderMobile" type="text" class="easyui-textbox" required="true" maxlength="50">
						</div>
						<div class="fitem">
							<label>物料是否到位:</label>
							<input name="memo" id="materialStateInput" class="easyui-combobox" editable="false" maxlength="50">
						</div>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="dlg-buttons">
		<button class="easyui-linkbutton" iconCls="icon-ok" onclick="saveMaturel()" style="width:90px">保存</button>
		<button class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#saveDialog').dialog('close')" style="width:90px">取消</button>
		<button class="easyui-linkbutton" iconCls="icon-remove" onclick="clearForm()" style="width:90px">重置</button>
	</div>
	<script type="text/javascript">
		var url="";
		function newMaturel() {
			$('#savefm').form('clear');
			$('#saveDialog').dialog('open').dialog('setTitle', '新建会场物料');
			url = "materialSchedule/saveOrUpdate";
		}
		
		function editMaturel() {
			var row = datagrid.datagrid('getSelected');
	        if (!row) {
	           $.messager.alert('提示', "请选中一条记录再进行修改！");
	           return;
	        }
	        $('#savefm').form('load', row);
	        $('#saveDialog').dialog('open').dialog('setTitle', '修改会场物料');
			url = "materialSchedule/saveOrUpdate";
		}
		
		function deleteMaturel() {
			var rows = datagrid.datagrid('getSelections');
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
						url : 'materialSchedule/delete',
						type : "GET",
						data: {'materialIds':sn},
						success : function(msg) {
							$.messager.alert('提示', msg.info);
							$('#dg').datagrid('reload');
							$('#dg').datagrid('clearSelections');
						}
					});
				}
			});
		}
		
		function saveMaturel() {
			var numValue = $("#materialNumInput").val();
			var numInt = Math.abs(Number(numValue));
			numInt = Math.round(numInt);
			$("#materialNumInput").textbox("setValue",numInt);
			
			$('#savefm').form('submit', {
				url : url,
				method : "POST",
				success : function(result) {
					var resultdatas =  JSON.parse(result);//eval(result);
					if(resultdatas.status == "success"){
						$.messager.alert('提示', resultdatas.info);
						$('#saveDialog').dialog('close'); // close the dialog
						$('#dg').datagrid('clearSelections');
						datagrid.datagrid('reload'); // reload the meeting data
					}else{
						$.messager.alert('提示', resultdatas.info);
					}
				}
			});
		}
		//清除表单
		function clearForm() {
			$('#savefm').form('clear');
		}
		
		//快速盘点
		function updateStateByIds(){
			var rows = datagrid.datagrid('getSelections');
			var num=rows.length;//获取要删除信息的个数
			
			if (!rows || num < 1) {
				$.messager.alert('提示', "请选中一条记录再修改成已到位");
				return;
			}
			var sn = "";
			for(var i=0; i<rows.length; i++){//组成一个字符串，ID主键之间用逗号隔开
				if(i!=rows.length-1){
					sn=sn+rows[i].id+",";
			     }else{
			    	 sn=sn+rows[i].id;
			     }
			}
			doUpdateState(num,sn)
		}
		
		function doUpdateState(num,ids){
			num = num + "";
			ids = ids + "";
			$.messager.confirm('确认', '确定要修改这'+ num+'条记录为已到位状态吗？', function(r) {
				if (r) {
					$.ajax({
						url : 'materialSchedule/updateStateByIds',
						type : "GET",
						data: {'materialIds':ids},
						success : function(msg) {
							$.messager.alert('提示', msg.info);
							$('#dg').datagrid('reload');
							$('#dg').datagrid('clearSelections');
						}
					});
				}
			});
		}
	</script>
</body>
</html>