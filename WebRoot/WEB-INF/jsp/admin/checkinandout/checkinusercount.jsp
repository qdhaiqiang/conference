<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="../../../../include/sys-common.jsp" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
      var datagrid;
      var userType;
      var suserType = "";
      var suserName = "";
      var sschedule = "";
		$(document).ready(function(){  
	    	loadGrid();  
	       
	       var p = $('#dg').datagrid('getPager'); 
	       $(p).pagination({ 
	           pageSize: 10,//每页显示的记录条数，默认为10
	           pageList:[5,10,15,20],//每页显示几条记录
	           beforePageText: '第',//页数文本框前显示的汉字
	           afterPageText: '页    共 {pages} 页',
	           displayMsg: '当前显示 {from} - {to} 条记录    共 {total} 条记录'
	       });
	       
	   	//获取用户类型数据
	         $.ajax({
	             url: 'dict/r/user_type',
	             method:'get',
	             async:false,
	             success:function(data) {
	                 userType = data;
	             }
	         });
	   	
	         $("#suserType").combobox({
	             data : userType,
	             valueField:'code',
	             textField:'name',
	             multiple:true,
	             editable:false
	        });

	       
	   	
	         $("#user_submitSearch").bind('click',function(){
	             if (!$("#select").form('validate')) {
	                 return false;
	             }else {
	            	 sschedule = $("#sschedule").combobox("getValues");
	                 suserType = $("#suserType").combobox("getValues");
	                 suserName = $("#sname").val().replace(" ","");
	                 datagrid.datagrid({
	                     pageNumber : 1
	                 });
	             }
	         });
	         $("#user_resetSearch").bind('click',function(){
	        	 $("#sschedule").combobox('setValue',"");
	             $("#suserType").combobox('setValue',"");
	             $("#sname").val("");
	         });
	         });
		
		function loadGrid()  {
			 datagrid =$('#dg').datagrid({
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
		            var userTypes = "";
			        for(var i=0;i<suserType.length;i++){
			            userTypes += suserType[i] + ",";
			        }
	                param.userType = userTypes;
	                param.cname = suserName;
	                param.scheduleId = $("#sschedule").combobox("getValue");
	            },

	        	
	    	});
		}
		
		function getUserType(value){
			for (var i=0; i<userType.length; i++) {
	            if (value == userType[i].code) {
	                return userType[i].name;
	            }
	        }
		}
		function showCname(value,row,index){
			return new Object(row["confUser"]).cname;
		} 
		function showEname(value,row,index){
			return new Object(row["confUser"]).ename;
		} 
	
		function showIfHeadset(value,row,index){
            if (row.isheadset == 1) {
                return "有";
            } else if (row.isheadset == 0) {
                return "无";
            }
        }
		
	</script>
  </head>
  
  <body>
  <div  id="cc" class ="easyui-layout" data-options="region:'center',border:false,fit:true">
    <div data-options="region:'north',border:false,title:'查询条件'" style="height:55px">
            <form id="search_form">
            <label style="margin-left:20px;">日程信息:</label>
                <select id="sschedule" class="easyui-combobox" name="sschedule" class="easyui-validatebox" >
                <option value="">--请选择--</option>
                <c:forEach var="schedule" items="${scheduleList}" >
                    <option value="${schedule.id}">${schedule.title}</option>
	            </c:forEach>
	            </select>
            <label style="margin-left:20px;">用户类型:</label>
                <input id="suserType" class="easyui-combobox" editable="false" name="suserType" class="easyui-validatebox" />
                <label style="margin-left:20px;">姓名:</label>
                <input id="sname" class="easyui-text" name="sname" class="easyui-validatebox" />
                <input id="user_submitSearch" type="button" value="查询" class="search-btn"/>
                <input id="user_resetSearch" type="button" value="重置" class="search-btn"/>
            </form>
        </div>
     <div data-options="region:'center',border:false">
    <table id="dg" title="会场人员显示" style="width:100%;height:100%"
			data-options="striped:true,rownumbers:true,singleSelect:true,url:'curcheckinuser/r',method:'post', 
       				 multiSort:true,fit:true,nowrap:false,toolbar:'#toolbar',pagination:'true'">
		<thead>
			<tr>
			    <th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field: 'cname', width:'90',formatter:showCname">中文姓名</th>
				<th data-options="field: 'ename', width:'90', formatter:showEname">英文姓名</th>
                <th data-options="field:'userType', width:'100', formatter:getUserType">用户类型</th>
				<th data-options="field:'isheadset',width:'40', formatter:showIfHeadset">耳机</th>
			</tr>
		</thead>
	</table>
	<div id = "toolbar"> 
	
	</div>
	</div>
	</div>
  </body>
</html>
