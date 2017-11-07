<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../../../../include/sys-common.jsp" />
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html >
<html>
  <head>
  	<style type="text/css">
        #ff{
            margin:0;
            padding:10px 30px;
        }
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
		$(document).ready(function(){  
	    	loadGrid();  
	    	$('#dlg').window({   
	          width:500,   
	          height:430,   
	          resizable:false  
	          });
	    	$('#dlg').window("close");
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
	<table id="dg" title="物流公司管理" style="width:100%;height:100%"
			data-options="striped:true,rownumbers:true,singleSelect:true,url:'express/r',method:'get', 
       				 multiSort:true,fit:true,nowrap:false,toolbar:'#toolbar',pagination:'true'">
		<thead>
			<tr>
			    <th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field: 'expressId', hidden:'true'">物流编号</th>
				<th data-options="field: 'meetingId', hidden:'true'">会议编号</th>
				<th data-options="field: 'expressCompany', width:'150'">公司名称</th>
				<th data-options="field: 'expressCompanyEn', width:'150',hidden:'true'">英文公司名称</th>
				<th data-options="field: 'expressOrder', width:'50'">负责人</th>
				<th data-options="field: 'expressOrderEn', width:'50',hidden:'true'">负责人英文</th>
				<th data-options="field: 'expressTele', width:'100'">电话</th>
				<th data-options="field: 'expressFax', width:'100'">传真</th>
				<th data-options="field: 'expressMobile', width:'150'">手机号</th>
				<th data-options="field: 'expressAddress', width:'150'">地址</th>
				<th data-options="field: 'expressEmail', width:'100',hidden:'true'">邮箱</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:'true'" onclick="newExpress()">新建</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:'true'" onclick="editExpress()">编辑</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:'true'" onclick="deleteExpress()">删除</a>
    </div>
    <div id="dlg" class="easyui-dialog"
		style="width:500px;height:300px;padding:20px 40px" closed="true"
		maximizable="true" resizable="true" left="150" top="0"
		buttons="#dlg-buttons">
	      <div data-options="region:'center'">
	        <form id="ff" method="post">
	        	<div class="fitem">
	        		<input type="hidden" id="expressId" name="expressId">
	        		<input type="hidden" id="meetingId" name="meetingId">
	        	</div>
	            <div class="fitem">
	                <label>公司名称:</label>
	                <input id="expressCompany" name="expressCompany" class="easyui-validatebox" data-options="required: true" style="width: 250px;" validType="length[2,50]"/>
	            </div>
	            <div class="fitem">
	                <label>英文公司名称:</label>
	                <input id="expressCompanyEn" name="expressCompanyEn" class="easyui-validatebox" data-options="required: true" style="width: 250px;" validType="length[2,100]"/>
	            </div>
	            <div class="fitem">
	                <label>负责人:</label>
	                <input id="expressOrder" name="expressOrder" class="easyui-validatebox" data-options="required: true" style="width: 250px;" validType="length[1,50]"/>
	            </div>
	            <div class="fitem">
	                <label>负责人英文:</label>
	                <input id="expressOrderEn" name="expressOrderEn" class="easyui-validatebox" data-options="required: true" style="width: 250px;"/>
	            </div>
	            <div class="fitem">
	                <label>电话:</label>
	                <input id="expressTele" name="expressTele" class="easyui-validatebox" data-options="required: true" style="width: 250px;"/>
	            </div>
	            <div class="fitem">
	            	<label>传真:</label>
	            	<input id="expressFax" name="expressFax" class="easyui-validatebox" data-options="required: true,precision:2" style="width: 250px;"/>
	            </div>
	            <div class="fitem">
	            	<label>手机号:</label>
	            	<input id="expressMobile" name="expressMobile" class="easyui-validatebox" data-options="required: true,precision:2"" style="width: 250px;"/>
	            </div>
	            <div class="fitem">
	            	<label>地址:</label>
	            	<input id="expressAddress" name="expressAddress" class="easyui-textbox" style="width: 250px;"/>
	            </div>
	            <div class="fitem">
	            	<label>邮箱:</label>
	            	<input id="expressEmail" name="expressEmail" class="easyui-textbox" style="width: 250px;"/>
	            </div>
	        </form>
	      </div>
	      <div id="dlg-buttons" data-options="region:'south',border:false" style="text-align: right;margin-bottom: 15px;">
	        <button class="easyui-linkbutton" id="save-btn" data-options="iconCls:'icon-ok'" onclick="saveForm()" style="width:90px">保存</button>
	        <button class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</button>
	        <button class="easyui-linkbutton" iconCls="icon-remove" onclick="clearForm()" style="width:90px">重置</button>
	      </div>
      </div>
    <script type="text/javascript">
    	var url;
    	/**
			创建新表单
		**/
        function newExpress(){
            $('#ff').form('clear');
            $('#dlg').window('open').dialog('setTitle','新建物流');
            url = '<%=basePath%>express/c';
        }
    	
    	/**
    	   	编辑表单
    	**/
    	function editExpress() {
    		$("#save-btn").attr("disabled","true");
    		var row = $('#dg').datagrid('getSelected');
            if (!row) {
               $.messager.alert('提示', "请选中一条记录再进行修改！");
               return;
            } else {
            	$("#save-btn").removeAttr('disabled');
    			$('#ff').form('clear');
            	$('#dlg').window('open').dialog('setTitle','编辑家具');
            	$('#ff').form('load', row);
            }
            url='<%=basePath%>express/u';
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
    	function deleteExpress() {
    		var row = $('#dg').datagrid('getSelected');
            if (!row) {
               $.messager.alert('提示', "请选中一条记录再进行删除！");
               return;
            } else {
               $.messager.confirm("提示", "是否删除选中的数据?", function (r) {
    		       if(r){
    			       $.ajax({  
    				       type:"POST",  
    				       url:"<%=basePath%>express/d",  
    				       data: {expressId:row.expressId},  
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
    </script>
  </body>
</html>