<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	<jsp:include page="../../../../include/sys-common.jsp" />
	<style type="text/css">
	.fitem {
		padding: 8px;
	}
	
	.fitem label {
		display: inline-block;
		width: 80px;
		vertical-align: top;
	}
	
	a{
		color: blue;
		text-decoration: underline;
	}
	
	a:hover{
		cursor: pointer;
	} 
	
	a:visited{
		color: gray;
	}
	
	</style>

  </head>
  <body>
  
  <!-- 显示日程列表div -->
	<div id="schedule-div" style="width:100%; height:100%;">
		<table id="schedule-dg" title="嘉宾指派" style="width:100%;height:100%"
			data-options="striped:true,rownumbers:true,singleSelect:true,url:'schedule/r',method:'get', 
       				 multiSort:true,fit:true,nowrap:false, pagination:'true'">
			<thead>
				<tr>
					<th data-options="field: 'title', width:'200'">标题</th>
					<th data-options="field:'id', width:'80', formatter:assignList">操作</th>
				</tr>
			</thead>
		</table>
	</div>

<!-- 显示选中日程的分配信息 -->
	<div id="assign-div" style="width:100%; height:100%;">
		<table id="assign-dg" title="嘉宾指派" style="width:100%;height:100%"
			data-options="striped:true,rownumbers:true,singleSelect:true,
	       				 multiSort:true,fit:true,nowrap:false,toolbar:'#toolbar'">
			<thead>
				<tr>
				    <th data-options="field:'ck',checkbox:true"></th>
					<th data-options="field:'id',hidden:'true'">id</th>
					<th data-options="field:'cname',width:'100'">姓名</th>
					<th data-options="field:'email',width:'130'">邮箱</th>
					<th data-options="field:'speech_topic',width:'300'">演讲题目</th>
					<th data-options="field:'speech_order',width:'40'">演讲顺序</th>
				</tr>
			</thead>
		</table>
		<div id="toolbar">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:'true'" onclick="addAssign()">新增</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:'true'" onclick="editAssign()">编辑</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:'true'" onclick="deleteAssign()">删除</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'',plain:'true'" onclick="goBack()" style="float:right;height:26px;margin-top:3px" ><img src="images/go_back.jpg" width="22" height="21" />&nbsp;返回</a>
		</div>
 
	</div>
	
	
<!-- 新增分配信息 -->
	<div id="assign-dlg" class="easyui-dialog" style="width:580px;height:360px;padding:20px 40px" closed="true" maximizable="true" resizable="true" left="150" top="0" buttons="#dlg-buttons">
		<div data-options="region:'center'">
			<form id="assign-form" method="post">
				<div class="fitem">
					<input id="id" name="id" type="hidden">
					<input id="schduleId" name="schduleId" type="hidden">
				</div> 
				
				<div class="fitem">
					<label>姓名:</label><input id="cname" class="easyui-validatebox" readonly="readonly"/> 
					<input id="userId" name="userId" type="hidden"/><a id="showSpeakers" >选择演讲嘉宾</a>
				</div>
				
				<div class="fitem">
					<label>演讲题目:</label><input id="speechTopic" name="speechTopic" class="easyui-validatebox" required = "true" style="width:360px;"/>
				</div>
				
				<div class="fitem">
					<label>演讲顺序:</label><input id="speechOrder" name="speechOrder" class="easyui-numberbox" required = "true"/>
				</div>
				
			</form>
		</div>
		<div id="dlg-buttons">
			<button class="easyui-linkbutton" iconCls="icon-ok" onclick="saveAssign()" style="width:90px">保存</button>
			<button class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#assign-dlg').dialog('close')" style="width:90px">取消</button>
			<button class="easyui-linkbutton" iconCls="icon-remove" onclick="clearForm()" style="width:90px">重置</button>
		</div>
	 
	</div>

	<div id="speakers-dlg" class="easyui-dialog" style="width:540px;height:400px;" left="170" top="30" data-options="closed:'true'">
		 
		<table id="speakers-dg" style="width:100%;height:100%" data-options="singleSelect:true,multiSort:true,fit:true,nowrap:false">
			<thead>
				<tr>
					<th data-options="field:'cname',width:'100'">姓名</th>
					<th data-options="field:'user_type',width:'80',formatter:getUserType">嘉宾类型</th>
					<th data-options="field:'sex',width:'30',formatter:getSexName">性別</th>
					<th data-options="field:'email',width:'130'">邮箱</th>
					<th data-options="field:'id',width:'30',formatter:passIdAndName"> </th>
				</tr>
			</thead>
		</table> 
	</div>
	

	<script type="text/javascript">
	
		var scheduleId;
			
		$(document).ready(function(){  
			$("#assign-div").hide();
	    	loadGrid();  
	    	
		});
		//加载日程列表
		function loadGrid()  {
	    	$("#schedule-dg").datagrid({
	        	nowrap:false,
	        	loadMsg:'加载中，请稍候...',
	        	fitColumns:true,
	 		    pageNumber : 1,//初始页码
	 		    pageSize : 15,
	 		    pageList : [15,30,45,60] 
	    	});
		}
		
		//日程列表最后一列显示
		function assignList(value,row){
			//alert(value)
			return "<a onclick='showAssign(\""+value+"\")' style='cursor:pointer;'><span style='color:blue;'>嘉宾指派</span></a>";
		}
		
		//点击按钮，查看已经分配的演讲嘉宾
		function showAssign(id){
			
			scheduleId = id;
			$("#assign-div").show();
			$("#schedule-div").hide();
			loadAssignGrid(scheduleId);
			
		}
		
		//加载各个日程下的分配
		function loadAssignGrid(id)  {
			//alert(id);
	    	$("#assign-dg").datagrid({
	    		url:"schedulelog/userAssign/assignlist/"+id,
	    		method:"get",
	        	nowrap:false,
	        	loadMsg:'加载中，请稍候...',
	        	fitColumns:true,
	    		pagination:'true',
	 		    pageNumber : 1,//初始页码
	 		    pageSize : 15,
	 		    pageList : [15,30,45,60]
	        	 
	    	});
		}
		
		
		var assignUrl = "";
		//新增嘉宾指派
		function addAssign(){
			$("#assign-form").form("clear");
			$("#assign-dlg").window("open").dialog("setTitle","新增嘉宾指派");
			$("#schduleId").val(scheduleId);
			$("#showSpeakers").show();
			assignUrl = "schedulelog/userAssign/save";
		}
		
		//编辑嘉宾指派
		function editAssign(){
			var row = $("#assign-dg").datagrid("getSelected");
	   		
	        if (!row) {
	           $.messager.alert("提示", "请选中一条记录再进行修改！");
	           return;
	        }  
			$("#assign-form").form("clear");
        	$("#assign-dlg").window("open").dialog("setTitle","编辑嘉宾指派");
        	
        	$("#id").val(row.id);
        	$("#cname").val(row.cname); 
        	$("#userId").val(row.user_id); 
        	$("#speechTopic").val(row.speech_topic); 
        	//$("#speechOrder").val(row.speech_order); 
        	//对number-box赋值方法。
        	$("#speechOrder").numberbox("setValue", row.speech_order);
        	
	        $("#schduleId").val(scheduleId);
	        //编辑的时候不能修改演讲者基本信息
	        $("#showSpeakers").hide();
	        
	        assignUrl = "schedulelog/userAssign/update";
		}
		
		//保存分派信息
		function saveAssign(){
			
			if (!$("#assign-form").form('validate')) {
                return false;
            }
			
			$("#assign-form").form("submit",{
				url:assignUrl,
				method:"post",
				success : function(result) {
					$.messager.alert("提示", result);
					$("#assign-dlg").dialog("close"); 
					$("#assign-dg").datagrid("reload");
				}
			});
			
		}
		
		//删除嘉宾指派
		function deleteAssign(){
			
			var row = $("#assign-dg").datagrid("getSelected");
			if (!row) {
				$.messager.alert("提示", "请选中一条记录再进行删除");
				return;
			}
			 
			$.messager.confirm("确认", "确定要删除这条记录吗？", function(r) {
				if (r) {
	 				//alert("delete");
					$.ajax({
						url : "schedulelog/userAssign/del",
						type : "POST",
						data: {"id":row.id},
						success : function(msg) {
							$.messager.alert("提示", msg);
							$("#assign-dg").datagrid("reload"); // reload the user data,loadGrid();
							$("#assign-dg").datagrid("clearSelections");
						}
					});
				}
			});
			
		}
		
		function goBack(){
			$("#assign-div").hide();
			$("#schedule-div").show();
		}
		
		
		$("#showSpeakers").click(function(){
			$("#speakers-dlg").dialog("open").dialog("setTitle","选择演讲嘉宾");;
			$("#speakers-dg").datagrid({
	    		url:"schedulelog/userAssign/speakers/"+scheduleId,
	    		method:"get",
	        	nowrap:false,
	        	loadMsg:'加载中，请稍候...',
	        	fitColumns:true,
	    	});
		});
		
		//性别
		function getSexName(value){
			if(value == "2"){
				return "女";
			}
			return "男";
		}
		
		function passIdAndName(value,row){
			var cname = row.cname;
			return "<a onclick='passValue(\""+value+"\",\""+cname+"\")' style='cursor:pointer;'><span style='color:blue;'>选择</span></a>";
		}
		
		function passValue(id,cname){
			//alert(id+cname);
			$("#speakers-dlg").dialog("close"); 
			$("#userId").val(id);
			$("#cname").val(cname);
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
	    
	    //重置按钮。。。
	   function clearForm(){
		   $("#assign-form").form("clear");
	   }
		
	</script>
  </body>
</html>
