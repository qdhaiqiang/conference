<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../../../../include/sys-common.jsp" />
<!DOCTYPE html >
<html>
<style type="text/css">
form label {
	display: inline-block;
	width: 60px;
	padding-left:5px;
}
form .combo {
    width: 150px !important;
}
form .textbox{
	width: 150px !important;
}
</style>
  <head>
  	<script type="text/javascript">
		var datagrid;
		$(document).ready(function(){  
	    	loadGrid(); 
        	$("#submit_search").bind('click',function(){
	    		datagrid.datagrid({
                    pageNumber : 1
                });
	    	});
		});
		
		function loadGrid()  {
			datagrid = $('#dg').datagrid({
	    		url:'confYachtUser/getUser',
	    		method:'POST',
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
	        	striped:true,
	        	rownumbers:true,
	        	singleSelect:true, 
  				multiSort:true,
  				fit:true,
  				nowrap:false,
  				toolbar:'#toolbar',
  				pagination:'true',
  				onBeforeLoad:function(param){
					param.cname=$("#findCname").val();
					param.userType=$("#userType").combobox("getValue");
				}
	    	});
		}
	</script>
  </head>
  
  <body>
	<table id="dg" title="游艇用户导出" style="width:100%;height:100%">
		<thead>
			<tr>
			    <th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field: 'cname'">姓名</th>
				<th data-options="field: 'userType'">用户类型</th>
				<th data-options="field: 'sex'">性别</th>
				<th data-options="field: 'nation'">国家</th>
				<th data-options="field: 'certValue'">证件号码</th>
				<th data-options="field: 'birth'">出生日期</th>
				<th data-options="field: 'mobile'">联系方式</th>
				<th data-options="field: 'email'">电子邮箱</th>
				<th data-options="field: 'company'">工作单位</th>
				<th data-options="field: 'position'">职务</th>
				<th data-options="field: 'address'">邮寄地址</th>
				<th data-options="field: 'arriveNum'">抵达航班</th>
				<th data-options="field: 'arriveTime'">抵达时间</th>
				<th data-options="field: 'pickLocation'">接送位置</th>
				<th data-options="field: 'veriCode'">验证码</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<form id="search_form">
			<!-- 姓名、 用户类型  -->
			<label>姓名:</label> 
			<input name="cname" id="findCname" class="easyui-textbox"/> 
			<label>用户类型:</label>
			<select id="userType" name="userType" class="easyui-combobox" style="width:200px;" editable="false">
			    <option value="">请选择</option>
			    <option value="1">买家</option>
			    <option value="2">嘉宾</option>
			    <option value="3">游客</option>
			</select>
			<input id="submit_search" type="button" value="查询" class="search-btn"/>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:'true'" onclick="exportUser()" style="float:right">导出</a>
		</form>
	</div>
	<form id="downform" method="post"></form>
    <script type="text/javascript">
    	/**
			创建新表单
		**/
        function exportUser() {
        	$('#downform').form('submit', {
	            url : "confYachtUser/exportUser",
	            method : "POST",
	            onSubmit: function(param){
	            	param.cname=$("#findCname").val();
					param.userType=$("#userType").combobox("getValue");
	            },
	            success : function(result) {
	                $.messager.alert('提示', result);
	            }
	        });
        }
    </script>
  </body>
</html>