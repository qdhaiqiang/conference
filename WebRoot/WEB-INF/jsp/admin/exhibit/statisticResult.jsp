<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<jsp:include page="../../../../include/sys-common.jsp" />
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">   
    <title>投票结果统计</title>
    <script type="text/javascript">
   	$(document).ready(function(){
   		$.ajax({
            url: 'front/exhibitCompany/statisticResult',
            method:'get',
            success:function(data) {
            	//exhibitBooths furniture expressNeeds
            	var exhibitBooths = data.exhibitBooths;
            	var furniture = data.furniture;
            	var expressNeeds = data.expressNeeds;
            	setBooths(exhibitBooths);
            	setFurniture(furniture);
            	setExpressNeeds(expressNeeds);
            }
        });
   	});
   	
   	function setBooths(exhibitBooths){
   		$("#Booths").datagrid({
   			data:exhibitBooths,
   			rownumbers:true,
   			onClickRow : function(rowIndex,rowData) {
				$(this).datagrid('unselectRow',rowIndex);
			}
   		});	
   	}
   	
   	function setFurniture(furniture){
   		$("#furnitures").datagrid({
   			data:furniture,
   			rownumbers:true,
   			onClickRow : function(rowIndex,rowData) {
				$(this).datagrid('unselectRow',rowIndex);
			},
   		});	
   	}
   	
   	function setExpressNeeds(expressNeeds){
   		$("#expressNeeds").html("  总计有 "+expressNeeds+" 个展商有物流需求");
   	}
   	
	</script>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head> 
  
  <body>
  	<p>展会分类统计汇总</p>
    <table id="Booths" title="1.展位统计表格" style="width:100%;">
		<thead>
			<tr>
				<th data-options="field:'resultName', width:'800'">展位名称</th>
				<th data-options="field:'resultAllNum', width:'100'">总数量</th>
				<th data-options="field:'resultRentNum', width:'100'">预定数量</th>
			</tr>
		</thead>
	</table>
	<table id="furnitures" title="2.额外家具分类汇总表格" style="width:100%;">
		<thead>
			<tr>
				<th data-options="field:'furnitureName', width:'500'">家具名称</th>
				<th data-options="field:'furnitureNameEn', width:'300'">英文名称</th>
				<th data-options="field:'furnitureTotalMount', width:'100'">总数量</th>
				<th data-options="field:'furnitureRentMount', width:'100'">预定数量</th>
			</tr>
		</thead>
	</table>
    
    <div class="panel-header" style="width:100%;">
		<div class="panel-title">3.展商物流需求汇总</div>
		<div class="datagrid-cell" id="expressNeeds"></div>
	</div>

  </body>
</html>
