<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	response.setDateHeader("Expires", 0);
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<jsp:include page="../../../include/sys-common.jsp" />
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <script type="text/javascript">
	
		$(document).ready(function(){  
		    closeEditor();
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
  	
  	<div style="width:100%; height:100%;" id="tablegrid">
		<table id="dg" title="注册前台内容" style="width:100%;height:100%"
			data-options="striped:true,rownumbers:true,singleSelect:true,url:'article/r',method:'get', 
       				 multiSort:true,fit:true,nowrap:false,toolbar:'#toolbar',pagination:'true'">
			<thead>
				<tr id="tr">
					<th data-options="field:'ck',checkbox:true"></th>
					<th data-options="field:'id',width:$(this).width() * 0,nowrap:'false',align:'left'">规章Id</th>
					<th data-options="field:'title',width:$(this).width() * 0.2,nowrap:'false',align:'left'">规章标题</th>
					<th data-options="field:'context',width:$(this).width() * 0.6,nowrap:'false',align:'left',formatter:reduceContent">规章内容</th>
				</tr>
			</thead>
		</table>
	</div>
	
	<div id="toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true" onclick="addArticleInfo()">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="editArticleInfo()">修改</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true" onclick="delArticleInfo()">删除</a>
	</div>
	
	<div id="ueditor-win">
		<div class="panel-header">
	    	<div class="panel-title">注册前台内容</div><div class="panel-tool"></div>
	    </div>
			<form id="ff" method="post">
				 <div class="fitem">
						<input class="easyui-validatebox textbox" type="hidden" name="id" value="" readonly="true"></input>
						
					</div>
					 <div class="fitem">
						<label>规章标题:</label>
							<input class="easyui-validatebox" type="text" name="title" style="width:600px;"
							data-options="required:true,missingMessage:'规章标题不能为空！'" value=""></input>
					</div>
					 <div class="fitem">
						<label>规章内容:</label>
						<!-- 加载编辑器的容器 -->
		    				<script id="container" name="context" type="text/plain" style="height:100%;max-height:100%;;overflow-y:auto; overflow-x:hidden;">
    							</script>
					</div>
			</form>
		
		    <div style="text-align:center;padding:5px">
					<a href="javascript:void(0)" id="add" class="easyui-linkbutton" onclick="add()" iconCls="icon-ok" style="padding:5px;">添加</a>
					<a href="javascript:void(0)" id="edit" class="easyui-linkbutton" iconCls="icon-ok" 
						onclick="edit()" style="padding:5px;">修改</a>
					<a id="closehref" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeEditor()">关闭</a>
			</div>
 	</div>
	<script type="text/javascript">
		function addArticleInfo() {
			clearForm();
			$('#ff').form('reset');
			$('#name').val('');
			$('#content').val('');
		    $('#add').css('display','inline');
		    $('#edit').css('display','none');
		    openEditor();
		}
		
		function add() {
			$('#ff').form('submit', {
				url:'<%=basePath%>article/c',
				method:'POST',
				success:function(data){
						$.messager.alert('提示',data);
						closeEditor();
					}
				});
		}
		
		function delArticleInfo(){
			var ids = "";
			var rows = $('#dg').datagrid('getSelections');
			var num=rows.length;//获取要删除信息的个数
			if(num > 0){
				for(var i=0; i<rows.length; i++){//组成一个字符串，ID主键之间用逗号隔开
					if(i!=rows.length-1){
						ids=ids+rows[i].id+",";
				     }else{
				    	 ids=ids+rows[i].id;
				     } 
					} 
					$.messager.confirm("提示", "是否删除选中的"+num+"条数据?", function (r) {
					if(r){
						 $.ajax({  
					          type:"GET",  
					          url:'<%=basePath%>article/d',  
					          data: {id:ids},  
					          success:function(msg){  
					             alert(msg);  
					             loadGrid();
					          }  
					       });
					}
					});
			}else{
				$.messager.alert('错误','请选中要删除的规章制度！');
			}
			
		}
		
		function editArticleInfo(){
			$('#add').css('display','none');
			$('#edit').css('display','inline');
			var row = $('#dg').datagrid('getSelections');
			var num  = row.length;
			if(num > 1 || num == 0){
				$.messager.alert('提示',"请选中一条记录进行修改");
				return;
			}
			        	
			openEditor();
			$("#ff").form("load", row[0]);
			ue.setContent(row[0].context);
		}
		
		function edit() {
			$('#ff').form('submit', {
				url:'<%=basePath%>article/u',
				method:'POST',
				success:function(data){
						closeEditor();
						$.messager.alert('提示',data);
					}
				});
		}
		
		function clearForm() {
			ue.execCommand('cleardoc');
		}
		
		function closeEditor() {
			$('#ueditor-win').hide();
			$('#closehref').hide();
			$('#tablegrid').show();
			loadGrid();
		}
		
		function openEditor() {
			$('#ueditor-win').show();
			$('#closehref').show();
			$('#tablegrid').hide();
		}
		
		function reduceContent(value) {
			value = delHtmlTag(value);
			if (value.indexOf("。") !== -1) {
				return value.split("。")[0] +"...";
			}
			else if (value.indexOf(".") !== -1) {
				return value.split(".")[0] +"...";
			}
			return value;
		}
		
		 function delHtmlTag(str){
  			return str.replace(/<[^>]+>/g,"");//去掉所有的html标记
 		}
		
	</script>
    <!-- 实例化编辑器 -->
    <script type="text/javascript">
        var ue = UE.getEditor('container');
    </script>
    
  </body>
</html>
