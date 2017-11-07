<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../../../../include/sys-common.jsp" />
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html >
<html>
  <head>
  	<script type="text/javascript">
		$(document).ready(function(){  
	    	loadGrid();  
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
		
		/**
		   	分会场记下载
		**/
		function showDownBtn(value,row,index) {
			return '<form id="'+row.id+'"><a onclick="downloadLog(\'' + row.id+ '\')" style="cursor:pointer;">' + "下载" +'</a></form>';
		}
		
		/**
		   	下载分会场记
		**/
		function downloadLog(scheduleId) {
			$('#'+scheduleId).form('submit', {
	            url : "<%=basePath%>schedule/exportBranchLog",
	            method : "GET",
	            queryParams:{scheduleId:scheduleId},
	            success : function(result) {
	                $.messager.alert('提示', result);
	            }
	        });
		}
	</script>
  </head>
  
  <body>
	<table id="dg" title="分会场管理" style="width:100%;height:100%"
			data-options="striped:true,rownumbers:true,singleSelect:true,url:'schedule/r',method:'get', 
       				 multiSort:true,fit:true,nowrap:false,pagination:'true'">
		<thead>
			<tr>
			    <th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field: 'id', hidden:'true'">日程编号</th>
				<th data-options="field: 'meetingId', hidden:'true'">会议编号</th>
				<th data-options="field: 'location', width:'200'">分会场</th>
				<th data-options="field: 'title', width:'250'">日程</th>
				<th data-options="field: 'startTime', width:'150'">开始时间</th>
				<th data-options="field: 'endTime', width:'150'">结束时间</th>
				<th data-options="field: 'aa', width:'100',formatter:showDownBtn">操作</th>
			</tr>
		</thead>
	</table>
  </body>
</html>