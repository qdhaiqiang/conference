<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../../../include/sys-common.jsp" />

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html >
<html>
  <head>
  	<style type="text/css">
        #ff{
            margin:0;
            padding:10px 30px;
        }
        .ftitle{
            font-size:14px;
            font-weight:bold;
            padding:5px 0;
            margin-bottom:10px;
            border-bottom:1px solid #ccc;
        }
        .fitem{
            margin-bottom:5px;
        }
        .fitem label{
            display:inline-block;
            width:80px;
        }
        .fitem input{
            width:160px;
        }
    </style>
    <script type="text/javascript">
		$(function() {
			$('#tg').treegrid({
				idField: 'id',
				treeField: 'text',
				title: '菜单管理',
				url: 'menu/r',
				fit: true,
				loadMsg: '数据加载中...',
				pagination: false,
				sortOrder: 'asc',
				rownumbers: true,
				singleSelect: true,
				fitColumns: true,
				showFooter: true,
				toolbar:'#toolbar',
				frozenColumns: [[]],
				columns: [[
					{field: 'id',title: '编号',width: 30,hidden: true},
					{field: 'text',title: '菜单名称',width: 100},
					{field: 'code',title: '图标',width: 30,formatter: function(value, rec, index) {return '<img border="0" src="<%=basePath%>css/plugins/images/' + value + '.png"/>'}},
					{field: 'src',title: '菜单地址',width: 60},
					{field: 'order',title: '菜单顺序',width: 60},
				]],
				onClickRow: function(rowData) {
					rowid = rowData.id;
					gridname = 'tg';
				}
			});
			$('#dlg').window({   
	          width:500,   
	          height:330,   
	          resizable:false  
	          });
	    	$('#dlg').window("close");
		});
		function reloadTable() {
			$('#' + gridname).treegrid('reload');
		}
		function reloadmenuList() {
			$('#tg').treegrid('reload');
		}
		function getmenuListSelected(field) {
			return getSelected(field);
		}
		function getSelected(field) {
			var row = $('#' + gridname).treegrid('getSelected');
			if (row != null) {
				value = row[field];
			} else {
				value = '';
			}
			return value;
		}
		function getmenuListSelections(field) {
			var ids = [];
			var rows = $('#tg').treegrid('getSelections');
			for (var i = 0; i < rows.length; i++) {
				ids.push(rows[i][field]);
			}
			ids.join(',');
			return ids
		};
	</script>
  </head>
  
  <body>
	<div id="tg" title="菜单管理" style="width:100%;height:100%"></div>
	<div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:'true'" onclick="newMenu()">新建</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:'true'" onclick="editMenu()">编辑</a>
<!--         <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:'true'" onclick="deleteMenu()">删除</a> -->
    </div>
    <div id="dlg" class="easyui-window" style="width:300px;height:600px;padding:10px 20px" data-options="closed:'true',buttons:'#dlg-buttons'">
      <div class="easyui-layout" data-options="fit:true,border:false">
	      <div data-options="region:'center'">
	        <form id="ff" method="post">
	        	<div class="fitem">
	        		<input id="id" name="id" type="hidden">
	        	</div>
	            <div class="fitem">
	                <label>菜单名称:</label>
	                <input id="name" name="name" class="easyui-textbox easyui-validatebox" data-options="required: true" style="width:180px;"/>
	            </div>
	            <div class="fitem">
	                <label>菜单等级:</label>
	                <select id="level" name="level" class="easyui-combobox" data-options="required: true,onChange:initParentMenu" style="width:200px;">
	                	<option value="1">一级菜单</option>
	                	<option value="2">二级菜单</option>
	                </select>
	            </div>
	            <div class="fitem" id="parentMenu" style="display: none;">
	                <label>父菜单:</label>
	                <input id="pid" name="pid" class="easyui-combobox" style="width:200px;">
	            </div>
	            <div class="fitem">
	            	<label>菜单地址:</label>
	            	<input id="menuUrl" name="menuUrl" class="easyui-textbox" style="width:180px;"/>
	            </div>
	            <div class="fitem">
	            	<label>图标名称:</label>
	            	<select id="icon" name="icon" class="easyui-combobox" data-options="required: true" style="width:200px;">
	            		<option value="folder">文件夹</option>
	            		<option value="pictures">图片</option>
	            		<option value="group_add">组</option>
	            		<option value="pencil">笔</option>
	            	</select>
	            </div>
	            <div class="fitem">
	            	<label>菜单顺序:</label>
	            	<input id="showOrder" name="showOrder" class="easyui-numberbox easyui-validatebox" data-options="required: true" style="width:180px;">
	            </div>
	        </form>
	      </div>
	      <div id="dlg-buttons" data-options="region:'south',border:false" style="text-align: right;margin-bottom: 15px;">
	        <button class="easyui-linkbutton" id="save-btn" data-options="iconCls:'icon-ok'" onclick="saveForm()" style="width:90px">保存</button>
	        <button class="easyui-linkbutton" iconCls="icon-remove" onclick="clearForm()" style="width:90px">重置</button>
	        <button class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</button>
	      </div>
      </div>
    </div>
    
    <script type="text/javascript">
    	var url;
    	
    	/**
			创建新表单
		**/
        function newMenu(){
            $('#ff').form('clear');
            $('#dlg').window('open').dialog('setTitle','新建菜单');
            url = '<%=basePath%>menu/c';
        }
        
        /**
        	编辑菜单
        **/
        function editMenu() {
        	$("#save-btn").attr("disabled","true");
    		var row = $('#tg').treegrid('getSelected');
            if (!row) {
               $.messager.alert('提示', "请选中一条记录再进行修改！");
               return;
            } else {
            	$("#save-btn").removeAttr('disabled');
    			$('#ff').form('clear');
            	$('#dlg').window('open').dialog('setTitle','编辑菜单');
            	// 表单赋值
            	$("#id").val(row.id);
            	$("#name").textbox("setValue",row.text);
            	if(row.parentId=="") {
            		$("#level").combobox("setValue",'1');
            	} else {
            		$("#level").combobox("setValue",'2');
            	}
            	$("#pid").combobox("setValue",row.parentId);
            	$("#menuUrl").textbox("setValue",row.src);
            	$("#icon").combobox("setValue", row.code);
            	$("#showOrder").numberbox("setValue",row.order);
            }
            url='<%=basePath%>menu/u';
        }
        
        /**
    	   保存Form表单
    	**/
    	function saveForm() {
    		$('#ff').form('submit', {
				url : url,
				method : "POST",
				onSubmit : function() {
					return $(this).form('validate');
				},
				success : function(result) {
					$.messager.alert('提示', result);
					$("#dlg").window('close'); // close the menu form
					$('#tg').treegrid('reload'); // reload the menu data
				}
			});
    	}
    	
        /**
           	初始化父菜单
        **/
        function initParentMenu() {
        	// 得到菜单等级
        	var level = $("#level").combobox("getValue");
        	// 一级菜单
        	if (level==1) {
        		// 隐藏父级菜单
        		$("#parentMenu").hide();
        		// 清空父菜单值
        		$("#pid").combobox('setValue',"");
        	// 二级菜单
        	} else if(level==2) {
        		// 显示父级菜单
        		$("#parentMenu").show();
        		// 父菜单赋值
        		$("#pid").combobox({
        			url:'<%=basePath%>menu/getFirstLevelMenu',
        			valueField: 'id',
       				textField: 'name'
        		});
        	}
        }
    </script>
  </body>
</html>