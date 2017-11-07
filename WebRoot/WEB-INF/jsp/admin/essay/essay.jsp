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
	<jsp:include page="../../../../include/sys-common.jsp" />
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <script type="text/javascript">
		var datagrid;
		$(document).ready(function(){  
		    closeEditor();
		    $("#submitSearch").bind('click',function(){
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
	        		param.essayTitle=$("#s_name").val();
	        		param.essayType=$("#parentMenuType").combobox("getValue");
	        	}
			});
		}

	</script>
  </head>
  
  <body>
  	
  	<div style="width:100%; height:100%;" id="tablegrid">
		<table id="dg" title="信息管理" style="width:100%;height:100%"
			data-options="striped:true,rownumbers:true,singleSelect:false,url:'essay/r',method:'post', 
       				 multiSort:true,fit:true,nowrap:false,toolbar:'#toolbar',pagination:'true'">
			<thead>
				<tr id="tr">
					<th data-options="field:'ck',checkbox:true"></th>
					<th data-options="field:'id',width:$(this).width() * 0,nowrap:'false',align:'left',hidden:'true'">Id</th>
					<th data-options="field:'meetingId',width:$(this).width() * 0,nowrap:'false',align:'left',hidden:'true'">会议编号</th>
					<th data-options="field:'essayTitle',width:$(this).width() * 0.15,nowrap:'false',align:'left'">文章标题</th>
					<th data-options="field:'essayTitleEn',width:$(this).width() * 0.1,nowrap:'false',align:'left',hidden:'true'">文章英文标题</th>
					<th data-options="field:'essayType',width:$(this).width() * 0.15,nowrap:'false',align:'left', formatter:getEssayType">文章分类</th>
					<th data-options="field:'createRealTime',width:$(this).width() * 0.2,nowrap:'false',align:'left'">创建时间</th>
					<th data-options="field:'lastUpdateRealTime',width:$(this).width() * 0.2,nowrap:'false',align:'left'">修改时间</th>
				</tr>
			</thead>
		</table>
	</div>
	
	<div id="toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addEssay()">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editEssay()">修改</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delEssay()">删除</a>
		<form id="select">
                <label>文章标题:</label><input name="s_name" type="text" id="s_name"  class="easyui-validatebox"  validType="searchParm"/>
                <label>文章分类:</label><input id="parentMenuType" class="easyui-combobox" editable="false" name="suserType" maxlength="50"
                        data-options="valueField:'id',textField:'typeName',url:'essay_type/r/menu2',method:'get'" />
            	<input class="easyui-linkbutton search-btn" id="submitSearch" type="button" value="查询" />
            </form>
	</div>
	
	<div id="ueditor-win">
			<div class="panel-header">
		    	<div class="panel-title">信息管理</div><div class="panel-tool"></div>
		    </div>
			<form id="ff" method="post">
				<table cellpadding="5">
					 <div class="fitem">
						<input class="easyui-validatebox textbox" type="hidden" name="id" value="" readonly="true"></input>
						<input class="easyui-validatebox textbox" type="hidden" name="meetingId" value="" readonly="true"></input>
					</div>
					<div class="fitem">
						<label>文章标题:</label>
							<input class="easyui-validatebox" type="text" name="essayTitle" style="width:300px;"
							data-options="required:true,missingMessage:'文章标题不能为空！'" value=""></input>
					</div>
					<div class="fitem">
						<label>文章英文标题:</label>
							<input class="easyui-validatebox" type="text" name="essayTitleEn" style="width:300px;"
							data-options="required:true,missingMessage:'文章英文标题不能为空！'" value=""></input>
					</div>
					<div class="fitem">
						<label>文章分类:</label>
							<input id="suserType" class="easyui-combobox" editable="false" name="essayType" maxlength="50"
								data-options="valueField:'id',textField:'typeName',
								url:'essay_type/r/menu2',method:'get'" />
					</div>
					<div class="fitem">
						<label>文章内容:</label>
						<!-- 加载编辑器的容器 -->
		    				<script id="container" name="essayContent" type="text/plain" style="height:300px;max-height:300px;overflow-y:auto; overflow-x:hidden;">
    							</script>
					</div>
					<div class="fitem">
						<label>文章英文内容:</label>
						<!-- 加载编辑器的容器 -->
		    				<script id="containerEn" name="essayContentEn" type="text/plain" style="height:300px;max-height:300px;overflow-y:auto; overflow-x:hidden;">
    							</script>
						
					</div>
					<div class="fitem">
						<label>显示顺序:</label>
							<input class="easyui-numberbox" type="text" name="showOrder" style="width:300px;"
							data-options="required:true,missingMessage:'显示顺序不能为空！'" value=""></input>
					</div>
					<div class="fitem">
						<input class="easyui-validatebox textbox" type="hidden" name="createTime" value="" readonly="true"></input>
						<input class="easyui-validatebox textbox" type="hidden" name="createRealTime" value="" readonly="true"></input>
						
					</div>
			</form>
		
		    <div style="text-align:center;padding:5px">
					<a href="javascript:void(0)" id="add" class="easyui-linkbutton" iconCls="icon-ok"  onclick="add()" style="padding:5px;">添加</a>
					<a href="javascript:void(0)" id="edit" class="easyui-linkbutton" iconCls="icon-ok" 
						onclick="edit()" style="padding:5px;">修改</a>
					<a id="closehref" href="javascript:void(0)" class="easyui-linkbutton"  iconCls="icon-cancel" onclick="closeEditor()">关闭</a>
			</div>
 	</div>
	<script type="text/javascript">
		function addEssay() {
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
				url:'<%=basePath%>essay/c',
				method:'POST',
				success:function(data){
						$.messager.alert('提示',data);
						closeEditor();
					}
				});
		}
		
		function delEssay(){
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
					          url:'<%=basePath%>essay/d',  
					          data: {id:ids},  
					          success:function(msg){  
					             $.messager.alert(msg);  
					             loadGrid();
					          }  
					       });
					}
					});
			}else{
				$.messager.alert('错误','请选中要删除的文章信息！');
			}
			
		}
		
		function editEssay(){
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
			ue.setContent(row[0].essayContent);
			ueen.setContent(row[0].essayContentEn);
		}
		
		function edit() {
			$('#ff').form('submit', {
				url:'<%=basePath%>essay/u',
				method:'POST',
				success:function(data){
						closeEditor();
						$.messager.alert('提示',data);
					}
				});
		}
		
		function clearForm() {
			ue.execCommand('cleardoc');
			ueen.execCommand('cleardoc');
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
		
		 function delHtmlTag(str){
  			return str.replace(/<[^>]+>/g,"");//去掉所有的html标记
 		}
 		
 		// 获取二级菜单
	    var essayType;
	    $(function(){
	        $.ajax({
	            url: 'essay_type/r/menu2',
	            method:'get',
	            async:false,
	            success:function(data) {
	                essayType = data;
	            }
	        });
	    });

	    //文章类型匹配
	    function getEssayType(value, row , index) {
	       for (var i=0; i<essayType.length; i++) {
	           if (value == essayType[i].id) {
	               return essayType[i].typeName;
	           }
	       }
	   }
		
	</script>
    <!-- 实例化编辑器 -->
    <script type="text/javascript">
        var ue = UE.getEditor('container');
        var ueen = UE.getEditor('containerEn');
    </script>
    
  </body>
</html>
