<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../../../include/sys-common.jsp" />
<!DOCTYPE html >
<html>
  <head>
  	<style type="text/css">
        .ftitle{
            font-size:14px;
            font-weight:bold;
            padding:5px 0;
            margin-bottom:10px;
            border-bottom:1px solid #ccc;
        }
        .fitem{
            margin-bottom:5px;
        }
        .fitem label{
            display:inline-block;
            width:80px;
        }
        .fitem input{
            width:160px;
        }
    </style>
    
  	<script type="text/javascript">
  		var userType;
		$(document).ready(function(){  
	    	loadGrid();  
	    	
	    	// 格式化日期控件
	    	datetimebox('time1','startTime');
	    	datetimebox('time2','endTime');
	    	
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
	<table id="dg" title="日程管理" style="width:100%;height:100%"
			data-options="striped:true,rownumbers:true,singleSelect:true,url:'schedule/r',method:'get', 
       				 multiSort:true,fit:true,nowrap:false,toolbar:'#toolbar',pagination:'true'">
		<thead>
			<tr>
			    <th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field: 'id', hidden:'true'">日程编号</th>
				<th data-options="field: 'meetingId', hidden:'true'">会议编号</th>
				<th data-options="field: 'title', width:'200'">标题</th>
				<th data-options="field: 'startTime', width:'150'">开始时间</th>
				<th data-options="field: 'endTime', width:'150'">结束时间</th>
				<th data-options="field: 'restrictAudience',width:'400',formatter:convertUserType">用户类型</th>
				<!-- <th data-options="field: 'intro', width:'300'">简介</th> -->
			</tr>
		</thead>
	</table>
	<div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:'true'" onclick="newSchedule()">新建</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:'true'" onclick="editSchedule()">编辑</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:'true'" onclick="deleteSchedule()">删除</a>
    </div>
    <div id="dlg" class="easyui-dialog"	style="width:500px;height:400px;padding:20px 40px" closed="true" maximizable="true" resizable="true" buttons="#dlg-buttons">
	      <div data-options="region:'center'">
	        <form id="ff" method="post">
	        	<div class="fitem">
	        		<input id="id" name="id" type="hidden">
	        		<input id="meetingId" name="meetingId" type="hidden">
	        	</div>
	            <div class="fitem">
	                <label>标题:</label>
	                <input id="title" name="title" class="easyui-validatebox" data-options="required: true" style="width: 250px;"/>
	            </div>
	            <div class="fitem">
	                <label>标题-英文:</label>
	                <input id="titleEn" name="titleEn" class="easyui-validatebox" data-options="required: true" style="width: 250px;"/>
	            </div>
	            <div class="fitem">
	            	<label>开始时间:</label>
	            	<input id="time1" name="time1" class="easyui-datetimebox" editable="false" data-options="required: true"/>
	            	<input type="hidden" id="startTime" name="startTime">
	            </div>
	            <div class="fitem">
	            	<label>结束时间:</label>
	            	<input id="time2" name="time2" class="easyui-datetimebox" editable="false" data-options="required: true">
	            	<input type="hidden" id="endTime" name="endTime">
	            </div>
	            <div class="fitem">
	            	<label>用户类型:</label>
	            	<input class="easyui-combobox" id="restrictAudience" name="restrictAudience"></input>
	            </div>
	            <div class="fitem">
	            	<label>日程类型:</label>
	            	<select class="easyui-combobox" id="scheduleType" name="scheduleType" data-options="required: true" style="width:200px;">
	            		<option value="1">大会日程</option>
	            		<option value="2">其他日程</option>
	            	</select>
	            </div>
	            <div class="fitem">
	            	<label>简介:</label>
	            	<textarea name="intro" rows="5" cols="45"></textarea>
	            </div>
	            <div class="fitem">
	            	<label>简介-英文:</label>
	            	<textarea name="introEn" rows="5" cols="45"></textarea>
	            </div>
	        </form>
	      </div>
	      <div id="dlg-buttons" data-options="region:'south',border:false" style="text-align: right;margin-bottom: 15px;">
	        <button class="easyui-linkbutton" id="save-btn" data-options="iconCls:'icon-ok'" onclick="saveForm()" style="width:90px">保存</button>
	        <button class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</button>
	        <button class="easyui-linkbutton" onclick="clearForm()" iconcls="icon-remove" style="width:90px">重置</button>
	      </div>
    </div>
    <script type="text/javascript">
    	var url;
    	/**
			创建新表单
		**/
        function newSchedule(){
            $('#ff').form('clear');
            $('#dlg').window('open').dialog('setTitle','新建日程');
             // 初始化用户类型
			$("#restrictAudience").combobox({
				data:userType,
            	valueField:'code',
            	textField:'name',
            	multiple:true,  
                editable:false,  
                panelHeight:'auto'
            });
            url = 'schedule/add';
        }
    	
    	/**
    	   	编辑表单
    	**/
    	function editSchedule() {
    		$("#save-btn").attr("disabled","true");
    		var row = $('#dg').datagrid('getSelected');
            if (!row) {
               $.messager.alert('提示', "请选中一条记录再进行修改！");
               return;
            } else {
            	$("#save-btn").removeAttr('disabled');
    			$('#ff').form('clear');
            	$('#dlg').window('open').dialog('setTitle','编辑日程');
            	$('#ff').form('load', row);
            	$('#time1').datetimebox('setText',row.startTime);
            	$('#time2').datetimebox('setText',row.endTime);
            }
            // 初始化用户类型
			$("#restrictAudience").combobox({
				data:userType,
            	valueField:'code',
            	textField:'name',
            	multiple:true,  
                editable:false,  
                panelHeight:'auto'
            });
			// 绑定用户类型
			$("#restrictAudience").combobox('setValues',row.restrictAudience.split(','));
            url='schedule/update';
    	}
    	
    	/**
    	   保存Form表单
    	**/
    	function saveForm() {
    		$('#ff').form('submit', {
				url : url,
				method : "POST",
				success : function(result) {
					$.messager.alert('提示', result);
					$("#dlg").window('close'); // close the schedule form
					$('#dg').datagrid('reload'); // reload the schedule data
				}
			});
    	}
    	
    	/**
    	   	删除日程
    	**/
    	function deleteSchedule() {
    		var row = $('#dg').datagrid('getSelected');
            if (!row) {
               $.messager.alert('提示', "请选中一条记录再进行删除！");
               return;
            } else {
               $.messager.confirm("提示", "是否删除选中的数据?", function (r) {
    		       if(r){
    			       $.ajax({  
    				       type:"POST",  
    				       url:"schedule/delete",  
    				       data: {scheduleId:row.id},  
    				       success:function(msg){ 
    				    	   $.messager.alert('提示', msg);
    				           loadGrid();
    				       }  
    				   });
    				}
    			});
            }
    	}
    	
    	/**
    	         清空表单
    	**/
        function clearForm() {
            $('#ff').form('clear');
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
    </script>
  </body>
</html>