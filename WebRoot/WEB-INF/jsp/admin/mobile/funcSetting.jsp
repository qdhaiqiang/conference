<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	response.setDateHeader("Expires", 0);
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>功能设置</title>
<jsp:include page="../../../../include/sys-common.jsp" />
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<link rel="stylesheet"
	href="<%=basePath%>css/plugins/iPhoneCheckContainer/original-ios/ios-checkboxes.css">
	<link rel="stylesheet"
	href="<%=basePath%>public/ui/themes/color.css">
<script src="<%=basePath%>public/iPhoneCheckContainer/ios-checkboxes.js"></script>
<style type="text/css">
</style>
<script type="text/javascript">
//存储更改的状态
var map = {}; 
//保存按钮
var toolbar = [{
    text:'保存',
    iconCls:'icon-save',
    handler:function(){
    	var status = "";
    	for(var key in map){  
    		status += key +":"+ map[key]+"@";  
    	}  
    	if(status.length>0){
    		status = status.substring(0,status.length-1)
    	}else{
    		$.messager.alert('提示', '没有要保存的修改');
    		return;
    	}
    	saveStatus(status);
   	}
}];
$(document).ready(function(){ 
	//加载datagride
	$('#dg').datagrid({
		url: '<%=basePath%>/funcSetting/r',
		method: 'get',
		nowrap:false,
		singleSelect:true,
    	loadMsg:'加载中，请稍候...',
    	fitColumns:true,
    	autoRowHeight:false,
    	toolbar:'#toolbar',
    	onLoadSuccess:function(){
    		$(".ipnoneStyle").iphoneStyle({
    	        onChange: function(elem, value) { 
    	        	var id= elem.parent().children().get(0).id;
    	        	if(id in map) { //判断是否存在  
    	        		  //存在则删除
    	        		delete map[id]; 
    	        	}else{
    	        		//不存在则新增
    	        		if(value){//true：开
    	        			map[id] = 1;
    	        		}else{
    	        			map[id] = 0;
    	        		}
    	        		
    	        	}  
    	            //alert(elem.parent().children().get(0).id);
    	            //alert(value);
    	          }
    	        });
    	}
	});
});
function getRequired(val,row){
    if (val == 1 ){
        return '<span style="color:red;">*</span>';
    } else {
        return "";
    }
}
function getStatus(val,row){
	if(row.required==1){
		return "";
	}
    if (val == 0 ){
    	return " <input type=\"checkbox\" id="+row.id+" class='ipnoneStyle'/>";
    } else {
    	return " <input type=\"checkbox\"  id="+row.id+" checked=\"checked\" class='ipnoneStyle'/>";
    }
}
function save(){
	var status = "";
	for(var key in map){  
		status += key +":"+ map[key]+"@";  
	}  
	if(status.length>0){
		status = status.substring(0,status.length-1)
	}else{
		$.messager.alert('提示', '没有要保存的修改');
		return;
	}
	saveStatus(status);
}
function saveStatus(status){
	
	 $.ajax({  
	       type:"POST",  
	       url:'<%=basePath%>funcSetting/update',  
	       data: {allStatus:status},  
	       success:function(msg){ 
	    	   $.messager.alert('提示', msg);
	    	   //清空
	    	   map={};
	    	   $('#dg').datagrid("reload");
	       }
	   });
}
</script>
</head>

<body>
<div style="width:100%;height: 100%">
	<!--功能列表 -->
	<table title="功能设置" id="dg"  style="height:100%;width: 100%"
		data-options="region:'center',border:false">
		<thead>
			<tr>
			    <th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'required',formatter:getRequired">必选项（<font
					style="color:red">*</font>）</th>
				<th data-options="field:'funcName', width:'50'">功能</th>
				<th data-options="field:'funcDes', width:'100'">描述</th>
				<th
					data-options="field:'status',align:'center', width:'50',
					styler: function(value,row,index){
					return 'align:center';
					},formatter:getStatus">状态</th>
			</tr>
		</thead>
	</table>
	<%--<div class="easyui-panel" style="padding:5px;text-align: right;font-weight: bold;">
        <a href="javascript:save()" class="easyui-linkbutton c8"  style="width:80px;margin-right: 17%">保存</a>
    </div>
    --%>
    <div id="toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="save()">保存</a> 
	</div>
    
    </div>
</body>
</html>
