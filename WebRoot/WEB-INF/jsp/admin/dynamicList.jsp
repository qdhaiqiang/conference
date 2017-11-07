<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../../../include/sys-common.jsp" />
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
	    	/**
	    	$('#dlg').window({   
	          width:850,   
	          height:450, 
	          shadow: true,
	          modal:true,
	      	  minimizable:false,
	          maximizable:false,
	      	  collapsible:false
	        });
	    	$('#dlg').window("close");
	    	**/
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
	<table id="dg" title="注册表单设置" style="width:100%;height:100%"
			data-options="striped:true,rownumbers:true,singleSelect:true,url:'dynForm/r',method:'get', 
       				 multiSort:true,fit:true,nowrap:false,toolbar:'#toolbar',pagination:'true'">
		<thead>
			<tr>
			    <th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'userType',width:'150',formatter:getUserType">用户类型</th>
				<th data-options="field: 'aa', formatter:go">操作</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:'true'" onclick="newDynamicForm()">新建</a>
    </div>
    <script type="text/javascript">
		var userType;
        $(function(){
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
        
    	/**
			创建新表单
		**/
        function newDynamicForm(){
			var url = 'dynForm/r/getNewForm';            
            window.open(url+'?openwin=true', "新建表单","height=610,width=1000,toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no,status=no");
        }
    	
    	/**
    	   显示
    	**/
        function newFormBuilder() {
        	fb = new Formbuilder({
            	selector: '#formbuilder',
            	urlPath: 'dynForm/c/saveDynForm',
            	method: 'POST',
            	data: $("#userType").combobox('getValue')
            });
        }
    	
    	/**
    	         清空表单
    	**/
        function clearForm() {
            $('#ff').form('clear');
        }
        
    	/**
    	         显示表单
    	**/
        function showFormBuilder(selector, data, userType) {
			fb = new Formbuilder({
				selector : selector,
				bootstrapData : data.fields,
				urlPath: 'dynForm/c/saveDynForm',
            	method: 'POST',
            	data: userType
			});
		}
        
        function go(val, row) {
        	return '<a onclick="editDynForm(\'' + row.meetingId+ '\',\'' + row.userType+ '\')" style="cursor:pointer;">编辑</a>';
        }
        
        /**
                                  查看编辑表单
        **/
        function editDynForm(meetingId, userType) {
        	var url = 'dynForm/r/getForm2/'+meetingId+"/"+userType;
			window.open(url+'?openwin=true', "编辑表单","height=610,width=1000,toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no,status=no");
        }
        
        /**
          	 用户类型匹配
        **/
        function getUserType(value, row , index) {
        	for (var i=0; i<userType.length; i++) {
        		if (value == userType[i].code) {
        			return userType[i].name;
        		}
        	}
        }
    </script>
  </body>
</html>