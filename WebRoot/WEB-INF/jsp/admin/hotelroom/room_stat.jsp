<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<jsp:include page="../../../../include/sys-common.jsp" />

</head>

<body>
	<table id="room-dg"  title="房型统计" style="width:100%;height:100%" >
		<thead>
			<tr>
			    <th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'roomType', width:'200',formatter:getRoomType">房间类型</th>
				<th data-options="field:'organizerPay', width:'120',formatter:getPayName">房间费用</th>
				<th data-options="field:'count', width:'80'">人数统计</th>
			</tr>
		</thead>
	</table>
 	<div id="toolbar">
		<form id="select">
			<label>起止时间:</label> <input name="startDate" id="startDate"
				class="easyui-datebox" editable="false" validType="searchParm" />~ <input
				name="endDate" id="endDate" class="easyui-datebox"
				editable="false" validType="searchParm" /> <input id="search"
				type="button" value="查询" class="search-btn" /> <input id="reset" type="button"
				value="重置" class="search-btn" />
		</form>
	</div>
<script type="text/javascript">
	 
	var startDate="";
	var endDate="";

	$(document).ready(function(){
		//加载表格数据
		loadGrid();
		
	});

	function loadGrid()  {
		datagrid = $("#room-dg").datagrid({
			rownumbers:true,
			url:'roomassign/roomstat',
			method:'get',
			loadMsg:"加载中，请稍候...",
			toolbar:'#toolbar',
			detailFormatter:function(index,row){
				return "<div style='padding:5px'><table id='ddv-" + index + "'></table></div>";
			},
			onBeforeLoad:function(param){
                param.startDate = startDate;
                param.endDate = endDate; 
            }
		});
	}

    
 	// 获取房间类型
    var roomType;
    $(function(){
        $.ajax({
            url: "dict/r/room_type",
            method:"get",
            async:false,
            success:function(data) {
            	roomType = data;
            }
        });
    });
    //用户类型匹配
    function getRoomType(value, row , index) {
       for (var i=0; i<roomType.length; i++) {
           if (value == roomType[i].code) {
               return roomType[i].name;
           }
       }
   }
    
   //承担费用
   function getPayName(value){
	   if(value=="1"){
		   return "主办方承担";
	   }else{
		   return "参会人员承担";
	   }
   }
   
   
   $("#search").click(function(){
     startDate = $("#startDate").datetimebox("getText");
     endDate = $("#endDate").datetimebox("getText");
     //alert(startDate+endDate);
     $("#room-dg").datagrid("reload");
  });
   
   $("#reset").click(function(){
	   $("#startDate").datetimebox("setText","");
	   $("#endDate").datetimebox("setText","");
   });
    
</script>

</body>
</html>
