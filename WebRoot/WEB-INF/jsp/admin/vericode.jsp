<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../../../include/sys-common.jsp" />
<!DOCTYPE html >
<html>
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
	    	datagrid=$('#dg').datagrid({
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
	        	onBeforeLoad:function(param){
	        		param.email=$("#email").val();
	        	}
	    	});
		}
	</script>
  </head>
  
  <body>
  	<div id="cc" class ="easyui-layout" data-options="region:'center',border:false,fit:true">
		<div data-options="region:'north',border:false,titsle:'查询条件'" style="height:50px;background:#eff8ff;">
			<form id="search_form" style="margin-left: 20px;margin-top:10px;vertical-align: middle;">
				<label>请输入电子邮箱地址：</label>
		        <input name="email" type="text" id="email" class="easyui-validatebox" style="width:200px;" />
                <input id="submit_search" type="button" value="查询" class="search-btn"/>
			</form>
		</div>
		<div data-options="region:'center',border:false">
			<table id="dg" title="验证码查询" style="width:100%;height:100%"
					data-options="striped:true,rownumbers:true,singleSelect:true,url:'emailVericode/r',method:'get', 
		       				 multiSort:true,fit:true,nowrap:false,pagination:'true'">
				<thead>
					<tr>
					    <th data-options="field:'ck',checkbox:true"></th>
						<th data-options="field: 'id', hidden:'true'">验证码编号</th>
						<th data-options="field: 'email', width:'200'">电子邮箱</th>
						<th data-options="field: 'veriCode', width:'150'">验证码</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
  </body>
</html>