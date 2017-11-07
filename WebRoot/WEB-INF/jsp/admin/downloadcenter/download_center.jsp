<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	response.setDateHeader("Expires", 0);
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
%>
<jsp:include page="../../../../include/sys-common.jsp" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
	</script>
  </head>
  
  <body>
	<table id="dg" title="资源列表" style="width:100%;height:100%"
			data-options="striped:true,rownumbers:true,singleSelect:true,url:'downloadCenter/getResList',method:'get', 
       				 multiSort:true,fit:true,nowrap:false,toolbar:'#toolbar',pagination:'true'">
		<thead>
			<tr>
			    <th data-options="field: 'ck',checkbox:true"></th>
				<th data-options="field: 'cname', width:'120'">上传者</th>
				<th data-options="field: 'name', width:'200', formatter:getResPath">资源名称</th>
			</tr>
		</thead>
	</table>
	
    <script type="text/javascript">
  	    //资源链接拼写
	    function getResPath(value, row, index) {
  	    	var res = row.value.substring(0, row.value.lastIndexOf(";"));
  	    	var pathList = res.split(";");
  	    	var url = pathList[pathList.length-1];
	        return "<a href='<%=basePath%>"+url+"'style='cursor:pointer;'><span style='color:blue;'>"+value+"</span></a>";
	    }
    </script>
  </body>
</html>