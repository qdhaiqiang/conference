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
<jsp:include page="../../../../include/sys-common.jsp" />
<style type="text/css">
#room-form {
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

</head>

<body>
	<table id="room-dg" title="房型分配" style="width:100%;height:100%"
		data-options="striped:true,rownumbers:true,singleSelect:true,idField:'id',url:'roomassign/r',method:'get',
       				 multiSort:true,fit:true,nowrap:false,toolbar:'#toolbar'">
		<thead>
			<tr>
			    <th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'id', hidden:'true'"></th>
				<th data-options="field:'userType', width:'80',formatter:getUserType">用户类型</th>
				<th data-options="field:'roomType', width:'80',formatter:getRoomType">房间类型</th>
				<th data-options="field:'organizerPay', width:'80',formatter:getPayName">房间费用</th>
				<th data-options="field:'remarks', width:'80'">备注</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:'true'" onclick="addroom()">新建</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:'true'" onclick="editroom()">编辑</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-remove',plain:'true'"
			onclick="deleteroom()">删除</a> <a href="javascript:void(0)"
			class="easyui-linkbutton" style="float:right"
			data-options="iconCls:'icon-add',plain:'true'" onclick="assignRoom()">一键安排房型</a>
	</div>

	<div id="dlg" class="easyui-dialog"
		style="width:500px;height:300px;padding:20px 80px" closed="true"
		maximizable="true" resizable="true" left="150" top="0"
		buttons="#dlg-buttons">
		<div data-options="region:'center'">
			<form id="room-form" method="post">
				<div class="fitem">
					<input id="id" name="id" type="hidden">
				</div>

				<div class="fitem">
					<label>用户类型:</label>
					<input id="userType" class="easyui-combobox" editable="false" name="userType" required='true' />
				</div>

				<div class="fitem">
					<label>房间类型:</label>
					<input id="roomType" class="easyui-combobox" name="roomType" editable="false" data-options="valueField:'code',textField:'name', url:'dict/r/room_type',method:'get',required:'true'" />
				</div>

				<div class="fitem">
					<label>房间费用:</label>
					<select class="easyui-combobox" name="organizerPay" required="true" style="width:120px" editable="false">
						<option value="1" selected="selected">主办方承担</option>
						<option value="0">参会人员承担</option>
					</select>
				</div>

				<div class="fitem">
					<label>备注:</label>
					<textarea id="remarks" name="remarks" maxlength="200"
						style="width:180px;height:50px"></textarea>
				</div>
			</form>
		</div>
		<div id="dlg-buttons">
			<button class="easyui-linkbutton" id="saveId" iconCls="icon-ok"
				onclick="saveForm()" style="width:90px">保存</button>
			<button class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</button>
			<button class="easyui-linkbutton" iconCls="icon-remove" onclick="clearForm()"
				style="width:90px">重置</button>
		</div>
	 
	</div>
<script type="text/javascript">
	 
	var datagrid;

	$(document).ready(function(){
		
		//加载表格数据
		loadGrid();
		
	});

	function loadGrid()  {
		datagrid = $("#room-dg").datagrid({
			nowrap:false,
			loadMsg:"加载中，请稍候...",
			fitColumns:true,
			detailFormatter:function(index,row){
				return "<div style='padding:5px'><table id='ddv-" + index + "'></table></div>";
			} 
		});
	}

   // 新建嘉宾
	function addroom() {
		$("#dlg").dialog("open").dialog("setTitle", "新增房间安排");
		$("#room-form").form("clear");
		
		var userTypeList = new Array();
		$.ajax({
            url: "roomassign/usertype",
            method:"get",
            async:false,
            success:function(data) {
            	userTypeList = data;
            }
        });
		$("#userType").combobox({
	    	data:userTypeList,
	    	valueField:'code',
        	textField:'name'
    	});
	}
	
   	// 编辑表单
   	function editroom() {
   		var row = $("#room-dg").datagrid("getSelected");
        if (!row) {
           $.messager.alert("提示", "请选中一条记录再进行修改！");
           return;
        } else {
			$("#room-form").form("clear");
        	$("#dlg").window("open").dialog("setTitle","编辑房间安排");
        	$("#room-form").form("load", row);        	
        	$("#userType").combobox({
        		data:[getAnUserType(row.userType)],
            	valueField:'code',
            	textField:'name'
	        });
        	// 绑定用户类型
        	$("#userType").combobox('setValue',row.userType);
        }
   	}
   	
   	//保存Form表单
   	function saveForm() {
   		$("#room-form").form("submit", {
			url : "roomassign/add",
			method : "POST",
			success : function(result) {
				$.messager.alert("提示", result);
				$("#dlg").dialog("close"); 
				$("#room-dg").datagrid("reload");
			}
		});
   	}
   	
   	//删除
   	function deleteroom() {
   		var row = $("#room-dg").datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示", "请选中一条记录再进行删除");
			return;
		}
		
		$.messager.confirm("确认", "请慎重删除，如果需要删除，应再次点击'一键分配房间'，之前所有分配好的房间会重新分配！确定要删除这条记录吗？", function(r) {
			if (r) {
 				//alert("delete");
				$.ajax({
					url : "roomassign/delete",
					type : "POST",
					data: {"id":row.id},
					success : function(msg) {
						$.messager.alert("提示", msg);
						$("#room-dg").datagrid("reload"); // reload the user data,loadGrid();
						$("#room-dg").datagrid("clearSelections");
					}
				});
			}
		});
	}
   	
   	// 清空表单
    function clearForm() {
        $("#room-form").form("clear");
    }
   	
    function assignRoom(){
    	$.messager.confirm("提示", "所有已分配户型都会按此规则进行重新分配，请谨慎操作", function(r) {
    		if(r){
    			$.ajax({
    	            url: "roomassign/updateuser",
    	            method:"post",
    	            async:false,
    	            success:function(result) {
    	            	$.messager.alert("提示", "分配成功！");  
    	            }
    	        });
    		}
    	});
    }
	
	// 获取用户类型
    var userType;
    $(function(){
        $.ajax({
            url: "dict/r/user_type",
            method:"get",
            async:false,
            success:function(data) {
                userType = data;
            }
        });
    });
    //用户类型匹配
    function getUserType(value, row , index) {
       for (var i=0; i<userType.length; i++) {
           if (value == userType[i].code) {
               return userType[i].name;
           }
       }
   }
    
    function getAnUserType(value) {
        for (var i=0; i<userType.length; i++) {
            if (value == userType[i].code) {
                return userType[i];
            }
        }
    }
    
 	// 获取房间类型
    var roomType;
    $(function(){
        $.ajax({
            url: "dict/r/room_type",
            method:"get",
            async:false,
            success:function(data) {
            	roomType = data;
            }
        });
    });
    //用户类型匹配
    function getRoomType(value, row , index) {
       for (var i=0; i<roomType.length; i++) {
           if (value == roomType[i].code) {
               return roomType[i].name;
           }
       }
   }
    
   //承担费用
   function getPayName(value){
	   if(value=="1"){
		   return "主办方承担";
	   }else{
		   return "参会人员承担";
	   }
   }
    
</script>

</body>
</html>
