<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<jsp:include page="../../../../include/sys-common.jsp" />

<html>
<style type="text/css">
.fitem {
	margin-bottom: 5px;
	width: 100%;
}

.fitem label {
	display: inline-block;
	width: 30%;
}

.fitem .combo {
    width: 60% !important;
}
.fitem .textbox{
	width: 60% !important;
}
.fitem .textbox-text{
	width: 90% !important;
}

#select .combo {
    width: 30% !important;
}
#select .textbox-text{
	width: 90% !important;
}
</style>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript">
	var scheduleIdF = "";//查询条件，日程id
	
	var url="";
	var schduleList = new Array();
	var materialTypeList = new Array();
	var RequiredList = new Array();
   	var voteType;
   	$(document).ready(function(){
   		loadGrid();
   		loadSchduleList();
   		setSchduelSearch();
   		$("#showEditorVote").hide(); 		
   	});
	</script>
  </head>
  
  <body>
    <table id="dg" title="" style="width:100%;height:100%">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'id', hidden:'true'"></th>
				<th data-options="field:'meetingId', hidden:'true'"></th>
				<th data-options="field:'options',hidden:'true'"></th>
				<th data-options="field:'scheduleId',width:'150',formatter:setSchduelName">日程</th>
				<th data-options="field:'orderNum', width:'100'">排序</th>
				<th data-options="field:'type', width:'90',formatter:setmaterialType">类别</th>
				<th data-options="field:'name', width:'100'">名称</th>
				<th data-options="field:'description', width:'100'">描述</th>
				<th data-options="field:'isShow', width:'100',formatter:setcheckRequired">前台显示</th>
				<th data-options="field:'required', width:'100',formatter:setcheckRequired">是否必选</th>
				<th data-options="field:'time', width:'80', formatter:operation">操作</th>
			</tr>
		</thead>
	</table>
	
	<div id="toolbar">
		
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newVoteField()">新建</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editVoteField()">编辑</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteVoteField()">删除</a> 		
		<form id="select">
			会场:
			<input id="scheduleF" name="scheduleF" class="easyui-combobox" editable="false"/>
			<input id="searchF" type="button" value="查询" class="search-btn" onclick="setVoteFind()"/> 
			<input id="reset" type="button" value="重置" class="search-btn" onclick="resetVoteFind()"/>
		</form>
	</div>
	
	<div id="saveDialog" class="easyui-dialog" style="width:600px;height:400px;padding:10px 20px" closed="true"
		maximizable="true" resizable="true" left="150px" top="10px" buttons="#dlg-buttons">
		<form id="savefm"  method="post"  style="width:95%;">
			<table style="width:100%;">
				<tr>
					<td valign="top" style="width:100%">
						<div class="fitem">
							<input name="id" type='hidden'>
							<input name="meetingId" type='hidden'>
							<input name="options" id="editOptions" type='hidden'>
						</div>
						<div class="fitem">
							<label>选择日程</label>
							<input name="scheduleId" id="editSchduelId" class="easyui-combobox" editable="false" required="true" maxlength="50">
						</div>
						<div class="fitem">
							<label>排序</label>
							<input name="orderNum" id="editOrderNum" type="number" class="easyui-textbox" required="true" maxlength="50">
						</div>
						<div class="fitem">
							<label>名称</label>
							<input name="name" type="text" class="easyui-textbox" required="true"><!-- onchange="voteNameChange(this)" -->
						</div>
						<div class="fitem">
							<label>描述</label>
							<input name="description" type="text" class="easyui-textbox"><!-- onchange="voteDescChange(this)" -->
						</div>
						<div class="fitem">
							<label>是否必选</label>
							<input name="required" id="checkRequired" class="easyui-combobox" editable="false" required="true" maxlength="50">
						</div>
						<div class="fitem">
							<label>前台显示</label>
							<input name="isShow" id="checkIsShow" class="easyui-combobox" editable="false" required="true" maxlength="50">
						</div>
						<div class="fitem">
							<label>类别</label>
							<input name="type" id="editVoteType" class="easyui-combobox" editable="false" required="true" maxlength="50">
						</div>
					</td>
				</tr>
			</table>
		</form>
		<!-- 此处仅作为显示内容，不可直接在此处编辑 -->
		<div style="padding-left:20px;padding-right:20px;width:95%;">
			<div id="showEditorVote">
				<!-- 此处为显示区域，区分显示text,checkbox,radio -->
				投票标题：<label id="editorVoteName"></label><br>
				描述：<font style="font-size:10px"><label id="editorvoteDesc"></label></font><br><br>
				<div id="editorVoteOptions"  onClick="clickVoteOptions(this)">
				<!-- 此处为点击事件 -->
				</div>
			</div>
			<div id="editVoteOptions">
				<!-- 此处为编辑选项区域，区分显示text,checkbox,radio，每个选项后面有默认的 -->
				<button class="search-btn" onClick="addVoteOption(this)">+增加</button>
				<div class="fitem">
					选项内容：<input type="text">
					&nbsp;设置投票人数<input type="text">
					&nbsp;<button id="delOptions0" onClick="delVoteOptions(this)" class="search-btn">-删除</button>
				</div>
			</div>
		</div>
		
	</div>
	<div id="dlg-buttons">
		<button class="easyui-linkbutton" iconCls="icon-ok" onclick="saveVoteField()" style="width:90px">保存</button>
		<button class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#saveDialog').dialog('close')" style="width:90px">关闭</button>
		<button class="easyui-linkbutton" iconCls="icon-remove" onclick="clearForm()" style="width:90px">重置</button>
	</div>
	
	
	<script type="text/javascript">
	function loadGrid()  {
		datagrid = $('#dg').datagrid({
			url:'confVote/findVoteField',
			method:'post',
			idField:'id',
			striped:true,
			rownumbers:true,
			singleSelect:false,
			nowrap:false,
			loadMsg:'加载中请稍等...',
			fit:true,
			fitColumns:true,
			multiSort:true,
			pagination : true,//页码
			pageNumber : 1,//初始页码
			pageSize : 15,
			pageList : [15,30,45,60],
			pagination:true,
			toolbar:'#toolbar',
			onBeforeLoad:function(param){
				param.scheduleId = scheduleIdF;
			},
		});
	}
	
	function newVoteField() {
		$('#savefm').form('clear');
		clearVoteOptions();
		$('#saveDialog').dialog('open').dialog('setTitle', '新建投票');
		url = "confVote/saveOrUpdate";
	}
	
	function editVoteField() {
		var row = datagrid.datagrid('getSelected');
        if (!row) {
           $.messager.alert('提示', "请选中一条记录再进行修改！");
           return;
        }
        $('#savefm').form('load', row);
        clearVoteOptions();
        setEditVoteOptions(eval(row.options),row.type);
        $('#saveDialog').dialog('open').dialog('setTitle', '修改投票');
		url = "<%=basePath%>confVote/saveOrUpdate";
	}
	
	function saveVoteField(){
		var numValue = $("#editOrderNum").val();
		var numInt = Math.abs(Number(numValue));
		numInt = Math.round(numInt);
		$("#editOrderNum").textbox("setValue",numInt);
		var options = JSON.stringify(getEditVoteOptions());
		$("#editOptions").val(options);
		$('#savefm').form('submit', {
			url : url,
			method : "POST",
			success : function(result) {
				$.messager.alert('提示', result);
				$('#saveDialog').dialog('close'); // close the dialog
				$('#dg').datagrid('clearSelections');
				datagrid.datagrid('reload'); // reload the meeting data
			}
		});
	}
	
	function deleteVoteField() {
		var rows = datagrid.datagrid('getSelections');
		if (!rows || rows.length < 1) {
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
					url : 'confVote/delete',
					type : "GET",
					data: {'voteIds':sn},
					success : function(msg) {
						$.messager.alert('删除提示', msg.info);
						$('#dg').datagrid('reload');
						$('#dg').datagrid('clearSelections');
					}
				});
			}
		});
	}
	
	function loadSchduleList(){
    	$.ajax({
            url: 'schedule/findByMeetingId',
            method:'get',
            async:false,
            success:function(data) {
            	schduleList = data;
            }
        });
    	
    	//初始化类型，0-text 1-checkbox 2-radio
    	var materialTypeParam1 = {};
    	materialTypeParam1["code"] = "text";
    	materialTypeParam1["name"] = "文本";
    	var materialTypeParam2 = {};
    	materialTypeParam2["code"] = "checkbox";
    	materialTypeParam2["name"] = "多选框";
    	var materialTypeParam3 = {};
    	materialTypeParam3["code"] = "radio";
    	materialTypeParam3["name"] = "单选框";
    	materialTypeList[0] = materialTypeParam1;
    	materialTypeList[1] = materialTypeParam2;
    	materialTypeList[2] = materialTypeParam3;
    	
    	var RequiredParam0 = {};
    	RequiredParam0["code"] = "false";
    	RequiredParam0["name"] = "否";
    	var RequiredParam1 = {};
    	RequiredParam1["code"] = "true";
    	RequiredParam1["name"] = "是";
    	RequiredList[0] = RequiredParam0;
    	RequiredList[1] = RequiredParam1;
    }
	
	//根据加载来的schduleList，将其放在日程查询的条件中
    function setSchduelSearch(){
    	$("#editSchduelId").combobox({
            data: schduleList,//json格式的数据
            valueField:'id',
            textField:'title'
        });
    	
    	$("#scheduleF").combobox({
            data: schduleList,//json格式的数据
            valueField:'id',
            textField:'title'
        });
    	
    	$("#editVoteType").combobox({
            data: materialTypeList,//json格式的数据
            valueField:'code',
            textField:'name',
            onChange:changeVoteType
        });
    	
    	$("#checkRequired").combobox({
            data: RequiredList,//json格式的数据
            valueField:'code',
            textField:'name'
        });
    	
    	$("#checkIsShow").combobox({
            data: RequiredList,//json格式的数据
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
  
  function setcheckRequired(value, row , index){
	  for (var i=0; i<RequiredList.length; i++) {
          if (value == RequiredList[i].code) {
              return RequiredList[i].name;
          }
      }
  }
  
  function voteNameChange(obj){
	  var value = obj.value;
	  $("#editorVoteName").html(value);
  }
  function voteDescChange(obj){
	  var value = obj.value;
	  $("#editorvoteDesc").html(value);
  } 
  
  function changeVoteType(n,o){
	  voteType = n;
	  //0-text 1-checkbox 2-radio
	  $("#editorVoteOptions").html("");
	  var options = eval($("#editOptions").val());
	  var htmlsValue = '<input type="';
	  htmlsValue += voteType;
	  htmlsValue += '">';
	  $("#editorVoteOptions").html(htmlsValue);
	  
	  var optionsValue = '';
	  if(voteType != "text"){
		  optionsValue += '<button class="search-btn" onClick="addVoteOption(this)">+增加</button>';
	  }
	  $("#editVoteOptions").html(optionsValue);
	  return;
  }
  
  function clearVoteOptions(){
	  $("#editorVoteOptions").html("");
	  $("#editVoteOptions").html("");
  }
  
  function setEditVoteOptions(options,type){
	  voteType = type;
	  if(type != "text"){
		  var voteOptions = $("#editVoteOptions");
		  var htmlValue = '<button class="search-btn" onClick="addVoteOption(this)">+增加</button>';
		  for(var i in options){
			  var option = options[i];
			  htmlValue += '<div class="fitem">选项内容：<input type="text" value="';
			  htmlValue += option.label;
			  htmlValue += '">&nbsp;设置投票人数<input type="text" value="';
			  htmlValue += option.num;
			  htmlValue += '">&nbsp;<button id="delOptions';
			  htmlValue += i;
			  htmlValue += '" onClick="delVoteOptions(this)" class="search-btn">-删除</button></div>';
		  }
		  voteOptions.html(htmlValue);
	  }
	  
	  
	  /*for(var i in options){
	  htmlsValue += '<input name="checkboxOptions" type="checkbox" value="';
	  htmlsValue += options[i].label;
	  htmlsValue += '">';
	  htmlsValue += options[i].label;
	  htmlsValue += '<br>';
  		}*/
  }
  
  function getEditVoteOptions(){
	  var optiondatas = new Object();
	  if(voteType != "text"){
		  optiondatas = new Array();
		  var voteOptions = $("#editVoteOptions div");
		  for(var i=0;i<voteOptions.length;i++){
			  var optionInputs = voteOptions.eq(i).children("input");
			  var optioni = {};
			  var label = optionInputs.eq(0).val();
			  if(label != ""){
				  optioni["label"] = label;
				  optioni["num"] = optionInputs.eq(1).val();
				  optiondatas[i] = optioni;
			  }
		  }
	  }
	  return optiondatas;
  }
  
  function clickVoteOptions(obj){
	  /*$("#editVoteOptions").html('<button class="search-btn" onClick="addVoteOption(this)">+增加</button>');
	  if(voteType == "checkbox"){ }else if(voteType == "radio"){}*/
  }
  
  //addVoteOption,editVoteOptions
  function addVoteOption(obj){
	  var voteOptions = $("#editVoteOptions");
	  var num = voteOptions.find("div").length;
	  var htmlValue = '<div class="fitem">选项内容：<input type="text">&nbsp;设置投票人数<input type="text">&nbsp;<button id="delOptions';
	  htmlValue += num;
	  htmlValue += '" onClick="delVoteOptions(this)" class="search-btn">-删除</button></div>';
	  voteOptions.append(htmlValue);
  }
  //delVoteOptions
  function delVoteOptions(obj){
	  var id = obj.id;
	  var parent = $("#"+id).parent();
	  parent.remove();
  }
  
  function setVoteFind(){
	  scheduleIdF = $("#scheduleF").combobox("getValue");
	  datagrid.datagrid({
		  pageNumber : 1
	  });
  }
  
  function resetVoteFind(){
	  $("#select").form("clear");
	  scheduleIdF = "";
  }
  
    //上墙操作: true 上墙，false 下墙
    function operation(value,row) {
        var id = row.id;
        var isShow = row.isShow;
        if(isShow == "false"){
            return "<a onclick='updateMeetingWallState(\""+id+"\",\""+isShow+"\")' style='cursor:pointer;'><span style='color:blue;'>顶上墙</span></a>";
        } else {
            return "<a onclick='updateMeetingWallState(\""+id+"\",\""+isShow+"\")' style='cursor:pointer;'><span style='color:blue;'>拉下墙</span></a>";
        }
    }
    
    //修改上下墙状态
    function updateMeetingWallState(id, isShow){
        var row = $("#dg").datagrid("getSelected");
        if (!row) {
           $.messager.alert("提示", "请选中一条记录再操作！");
           return;
        } else {
            $.ajax({
                url:"confVote/upMeetingWallState",
                method:"POST",
                data:{id:row.id,isShow:row.isShow},
                success:function(result){
                    $.messager.alert("提示", result);
                    $("#dg").datagrid("reload");
                }
            });

        }
    }
	
</script>
  </body>
</html>
