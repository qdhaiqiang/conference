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
<script type="text/javascript" src="<%=basePath%>js/common.js"></script>

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
}
 
</style>

</head>

<body>
	<table id="room-dg" title="房型调整" style="width:100%;height:100%"
		data-options="striped:true,rownumbers:true,singleSelect:true,idField:'id',url:'roomassign/finduserroom',method:'post',
       				 multiSort:true,fit:true,nowrap:false,toolbar:'#toolbar'">
		<thead>
			<tr>
			    <th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'id', hidden:'true'"></th>
				<th data-options="field:'cname',width:'100'">姓名</th>
				<th data-options="field:'ename',width:'80'">其他名</th>
				<th data-options="field:'userType',width:'70',formatter:getUserType">用户类型</th>
				<th data-options="field:'sex',width:'30',formatter:getSexName">性別</th>
				<th data-options="field:'email',width:'130'">邮箱</th>
				<th data-options="field:'mobile',width:'100'">移动电话</th>
				<th data-options="field:'roomType',width:'130',formatter:getRoomType">房间类型</th>
				<th data-options="field:'organizerPay',width:'80',formatter:getPayName">房间费用</th>
				<th data-options="field:'checkInDate',width:'70'">入住时间</th>
				<th data-options="field:'checkOutDate',width:'70'">离开时间</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:'true'" onclick="addroom()">新增</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:'true'" onclick="editroom()">编辑</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-remove',plain:'true'"
			onclick="deleteroom()">删除</a>
		<a href="javascript:void(0)"
			class="easyui-linkbutton" style="float:right"
			data-options="iconCls:'icon-add',plain:'true'" onclick="assignCheckDate()">一键安排入住时间</a>
		<div>
			<form id="select">
				<label>姓名:</label><input name="sname" type="text" id="sname"
					class="easyui-validatebox" validType="searchParm" /> <label>邮箱:</label><input
					name="semail" type="text" id="semail" class="easyui-validatebox"
					validType="searchParm" /> <label>用户类型:</label><input id="suserType"
					class="easyui-combobox" editable="false" name="suserType"
					maxlength="50"
					data-options="valueField:'code',textField:'name',
                    url:'dict/r/user_type',method:'get'" />
				<input id="search" type="button" value="查询" class="search-btn" /> <input id="reset"
					type="button" value="重置" class="search-btn" />
			</form>
		</div>
	</div>

	<div id="dlg" class="easyui-dialog"
		style="width:580px;height:400px;padding:20px 80px" closed="true"
		maximizable="true" resizable="true" left="150" top="0"
		buttons="#dlg-buttons">
		<div data-options="region:'center'">
			<form id="room-form" method="post">
				<div class="fitem">
					<input id="id" name="id" type="hidden">
				</div>

				<div class="fitem">
					<label>邮箱:</label><input id="email" name="email"/>
				</div>
				
				<div class="fitem">
					<label>姓名:</label><input id="cname" name="cname" readonly="readonly"/>
				</div>
				
				<div class="fitem">
					<label>移动电话:</label><input id="mobile" name="mobile" readonly="readonly"/>
				</div>
				
				<div class="fitem"> 
					<label>入住时间:</label><input id="checkInDate" name="checkInDate" class="easyui-datebox" editable="false" />
				</div>
				
				<div class="fitem"> 
					<label>离开时间:</label><input id="checkOutDate" name="checkOutDate" class="easyui-datebox" editable="false" />
				</div>
				
				<div class="fitem">
					<label>房间类型:</label><input id="roomType" class="easyui-combobox"
						name="roomType" editable="false"
						data-options="valueField:'code',textField:'name', url:'dict/r/room_type',method:'get',required:'true'" />
				</div>

				<div class="fitem">
					<label>房间费用:</label><select class="easyui-combobox"
						name="organizerPay" required="true" style="width:120px"
						editable="false">
						<option value="1" selected="selected">主办方承担</option>
						<option value="0">参会人员承担</option>
					</select>
				</div>

			</form>
		</div>
		<div id="dlg-buttons">
			<button class="easyui-linkbutton" id="submit" iconCls="icon-ok"
				onclick="saveForm()" style="width:90px">保存</button>
			<button class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</button>
			<button class="easyui-linkbutton" iconCls="icon-remove" onclick="clearForm()"
				style="width:90px">重置</button>
		</div>
	 
	</div>
<script type="text/javascript">
	 
	var datagrid;
	var sname="";
    var semail="";
    var suserType="";
    
	$(document).ready(function(){
		
		//加载表格数据
		loadGrid();
		
	});

	function loadGrid()  {
		datagrid = $("#room-dg").datagrid({
			nowrap:false,
			loadMsg:"加载中，请稍候...",
            fitColumns:true,
            pagination : true,//页码
            pageNumber : 1,//初始页码
            pageSize : 15,
            pageList : [15,30,45,60],
			fitColumns:true,
			detailFormatter:function(index,row){
				return "<div style='padding:5px'><table id='ddv-" + index + "'></table></div>";
			},
			onBeforeLoad:function(param){
                param.sname = sname;
                param.semail = semail;
                param.suserType = suserType;
            } 
		});
	}
	
	
    $("#search").click(function(){
    	 sname = $("#sname").val().replace(" ","");
         semail = $("#semail").val().replace(" ","");
         suserType = $("#suserType").combobox("getValue");
         datagrid.datagrid({
             pageNumber : 1
         });
    });
    		 
    $("#reset").click(function(){
        $("#sname").val("");
        $("#semail").val("");
        $("#suserType").combobox('setValue',"");
    });

   // 新建嘉宾
	function addroom() {
		//alert("addroom");
		$("#dlg").dialog("open").dialog("setTitle", "新增房间安排");
		$("#room-form").form("clear");
		$("#email").removeAttr("readonly");
	}
	
	$("#email").blur(function(){
		$.ajax({
			url : "roomassign/finduserinfo",
			type : "GET",
			data: {"email":$("#email").val()},
			success : function(result) {
				 if(result.status=="0"){
					 $.messager.alert("提示", "本次会议找不到该参会人员！");
				 }else if(result.status=="2"){
					 $.messager.alert("提示", "该用户房型已调整！");
					 $("#submit").attr("disabled",true);
				 }else {
					 $("#submit").attr("disabled",false);
					 $("#id").val(result.id);
					 $("#cname").val(result.cname);
					 $("#mobile").val(result.mobile);
				 }
			}
		});
	});
	
   	// 编辑表单
   	function editroom() {
   		 //alert("edit");
   		var row = $("#room-dg").datagrid("getSelected");
   		
        if (!row) {
           $.messager.alert("提示", "请选中一条记录再进行修改！");
           return;
        } else {
			$("#room-form").form("clear");
        	$("#dlg").window("open").dialog("setTitle","编辑房间安排");
        	$("#room-form").form("load", row);
        }
        //编辑的时候邮箱不可用
        $("#email").attr("readonly","readonly");
   	}
   	
   	//保存Form表单
   	function saveForm() {
   		//alert("save");
   		if (!$("#room-form").form('validate')) {
			return false;
		}
   		var checkInDate = $("#checkInDate").datetimebox("getText");
		var checkOutDate = $("#checkOutDate").datetimebox("getText");
		if(dateLargeThan(checkInDate,checkOutDate)){
			$.messager.alert("提示","入住时间不能大于离开时间！");
			return false;
		}
		
   		$("#room-form").form("submit", {
			url : "roomassign/updateuserroom",
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
		 
		$.messager.confirm("确认", "确定要删除这条记录吗？", function(r) {
			if (r) {
 				//alert("delete");
				$.ajax({
					url : "roomassign/deluserroom",
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
   	
    function assignCheckDate(){
        $.ajax({
            url: "roomassign/checkdate",
            method:"post",
            async:false,
            success:function(result) {
            	$.messager.alert("提示", "入住时间自动分配成功！");
            	$("#room-dg").datagrid("reload");
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
    function getUserType(value) {
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
    function getRoomType(value) {
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
	   }else if(value=="0"){
		   return "参会人员承担";
	   } 
   }
   
   function getSexName(value){
	   if(value == "2"){
		   return "女";
	   }
	   return "男";
   }
    
</script>

</body>
</html>
