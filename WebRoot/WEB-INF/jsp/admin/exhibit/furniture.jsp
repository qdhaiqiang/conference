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
	<table id="dg" title="家具管理" style="width:100%;height:100%"
			data-options="striped:true,rownumbers:true,singleSelect:true,url:'furniture/r',method:'get', 
       				 multiSort:true,fit:true,nowrap:false,toolbar:'#toolbar',pagination:'true'">
		<thead>
			<tr>
			    <th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field: 'furnitureId', hidden:'true'">家具编号</th>
				<th data-options="field: 'meetingId', hidden:'true'">会议编号</th>
				<th data-options="field: 'furnitureCode', width:'50'">家具编号</th>
				<th data-options="field: 'furnitureName', width:'250'">家具名称</th>
				<th data-options="field: 'furnitureNameEn', width:'150',hidden:'true'">家具名称英文</th>
				<th data-options="field: 'furnitureTotalMount', width:'50'">总数量</th>
				<th data-options="field: 'furnitureRentMount', width:'50'">预订数量</th>
				<th data-options="field: 'furniturePrice', width:'100'">人民币</th>
				<th data-options="field: 'furniturePriceEn', width:'100'">葡币</th>
				<th data-options="field: 'furnitureDesc', width:'150'">描述</th>
				<th data-options="field: 'furnitureDescEn', width:'150',hidden:'true'">英文描述</th>
				<th data-options="field: 'memo', width:'100',hidden:'true'">备注</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:'true'" onclick="newFurniture()">新建</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:'true'" onclick="editFurniture()">编辑</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:'true'" onclick="deleteFurniture()">删除</a>
    </div>
     <div id="dlg" class="easyui-dialog" style="width:600px;height:400px;padding:10px 20px" closed="true" maximizable="true" resizable="false" left="150" top="10" buttons="#dlg-buttons"> 
	      <div data-options="region:'center'">
	        <form id="ff" method="post">
	        	<div class="fitem">
	        		<input type="hidden" id="furnitureId" name="furnitureId">
	        		<input type="hidden" id="meetingId" name="meetingId">
	        	</div>
	            <div class="fitem">
	                <label>家具编号:</label>
	                <input id="furnitureCode" name="furnitureCode" class="easyui-validatebox" data-options="required: true" style="width: 250px;" validType="length[2,10]"/>
	            </div>
	            <div class="fitem">
	                <label>家具名称:</label>
	                <input id="furnitureName" name="furnitureName" class="easyui-validatebox" data-options="required: true" style="width: 250px;" validType="length[1,100]"/>
	            </div>
	            <div class="fitem">
	                <label>英文家具名称:</label>
	                <input id="furnitureNameEn" name="furnitureNameEn" class="easyui-validatebox" data-options="required: true" style="width: 250px;" validType="length[1,200]"/>
	            </div>
	            <div class="fitem">
	                <label>总数量:</label>
	                <input id="furnitureTotalMount" name="furnitureTotalMount" class="easyui-numberbox" data-options="required: true" style="width: 250px;"/>
	            </div>
	            <div class="fitem">
	                <label>预订数量:</label>
	                <input id="furnitureRentMount" name="furnitureRentMount" class="easyui-numberbox" data-options="required: true" style="width: 250px;"/>
	            </div>
	            <div class="fitem">
	            	<label>人民币:</label>
	            	<input id="furniturePrice" name="furniturePrice" class="easyui-numberbox" data-options="required: true,precision:2" style="width: 250px;"/>
	            </div>
	            <div class="fitem">
	            	<label>葡币:</label>
	            	<input id="furniturePriceEn" name="furniturePriceEn" class="easyui-numberbox" data-options="required: true,precision:2"" style="width: 250px;"/>
	            </div>
	            <div class="fitem">
	            	<label>描述:</label>
	            	<input id="furnitureDesc" name="furnitureDesc" class="easyui-textbox" style="width: 250px;"/>
	            </div>
	            <div class="fitem">
	            	<label>英文描述:</label>
	            	<input id="furnitureDescEn" name="furnitureDescEn" class="easyui-textbox" style="width: 250px;"/>
	            </div>
	        </form>
	      </div>
	      <div id="dlg-buttons" data-options="region:'south',border:false" style="text-align: right;margin-bottom: 15px;">
	        <button class="easyui-linkbutton" id="save-btn" data-options="iconCls:'icon-ok'" onclick="saveForm()" style="width:90px">保存</button>
	        <button class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</button>
	        <button class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="clearForm()" style="width:90px">重置</button>
	      </div>
    </div>
    <script type="text/javascript">
    	var url;
    	/**
			创建新表单
		**/
        function newFurniture(){
            $('#ff').form('clear');
            $('#dlg').window('open').dialog('setTitle','新建家具');
            url = '<%=basePath%>furniture/c';
        }
    	
    	/**
    	   	编辑表单
    	**/
    	function editFurniture() {
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
            url='<%=basePath%>furniture/u';
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
    	function deleteFurniture() {
    		var row = $('#dg').datagrid('getSelected');
            if (!row) {
               $.messager.alert('提示', "请选中一条记录再进行删除！");
               return;
            } else {
               $.messager.confirm("提示", "是否删除选中的数据?", function (r) {
    		       if(r){
    			       $.ajax({  
    				       type:"POST",  
    				       url:"<%=basePath%>furniture/d",  
    				       data: {furnitureId:row.furnitureId},  
    				       success:function(msg){
    				    	   $.messager.alert('提示', msg.info);
    				    	   if(msg.data=="success"){
    				    		   loadGrid();
    				    	   }
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