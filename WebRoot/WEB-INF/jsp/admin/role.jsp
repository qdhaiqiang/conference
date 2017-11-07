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
		$(document).ready(function(){  
	    	loadGrid();  
	    	$('#dlg').window({   
	          width:500,   
	          height:330,   
	          resizable:false  
	          });
	    	$('#dlg').window("close");
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
	        	onSelect : function(rowIndex,rowData){
	        		setMenubyrole(rowData.id);
	        		setUserByRole(rowData.id);
	        	}
	    	});
		}
        
        /**
           	初始化右侧菜单树
        **/
		function setMenubyrole(roleId) {
			$("#menu_tree").tree({
				checkbox : true,
				url : '<%=basePath%>roleMenu/getMenu',
				queryParams : {roleId: roleId},
				onLoadSuccess : function(node) {
					expandAll();
				}
			});
			// 将roleId设置到隐藏域中
			$("#roleId").val(roleId);
		}
		
		//初始化右侧用户列表
		function setUserByRole(roleId) {
		  $("#user_list").datagrid({
		      url: '<%=basePath%>roleMenu/getUser',
		      singleSelect:true,
		      rownumbers : true,
		      columns:[[
                {field:'id',title:'id',hidden:true},
                {field:'name',title:'姓名',width:100},
              ]],
		      queryParams : {roleId: roleId},
		      
		  });
		} 
		
		function expandAll() {
			var node = $('#menu_tree').tree('getSelected');
			if (node) {
				$('#menu_tree').tree('expandAll', node.target);
			} else {
				$('#menu_tree').tree('expandAll');
			}
		}
		
		/**
		  	保存角色菜单权限
		**/
		function saveMenuTree() {
			var selectNodes = GetNode();
			// 提交
			$.ajax({        
        		type: "POST",                                     
        		url: "<%=basePath%>roleMenu/saveRoleMenu",                                  
        		data: "roleId="+$("#roleId").val()+"&selectNodes="+selectNodes,   
        		success: function(result){ 
        			$.messager.alert('提示', result);                 
        		}   
      		});   
   		}   
		
		/**
		   	获取选中节点
		**/
		function GetNode() {
			var node = $('#menu_tree').tree('getChecked');
			var cnodes = '';
			var pnodes = '';
			var prevNode = ''; //保存上一步所选父节点
			for ( var i = 0; i < node.length; i++) {
				if ($('#menu_tree').tree('isLeaf', node[i].target)) {
					cnodes += node[i].id + ',';
					var pnode = $('#menu_tree').tree('getParent', node[i].target); //获取当前节点的父节点
					if (prevNode != pnode.id) //保证当前父节点与上一次父节点不同
					{
						pnodes += pnode.id + ',';
						prevNode = pnode.id; //保存当前节点
					}
				}
			}
			cnodes = cnodes.substring(0, cnodes.length - 1);
			pnodes = pnodes.substring(0, pnodes.length - 1);
			return cnodes + "," + pnodes;
		};
	</script>
  </head>
  
  <body>
  <div class="easyui-layout" fit="true" >
    <div region="center" border="false" style="padding: 1px;">
        <table id="dg" title="角色管理" style="width:100%;height:100%"
            data-options="striped:true,rownumbers:true,singleSelect:true,url:'role/r',method:'get', 
                     multiSort:true,fit:true,nowrap:false,toolbar:'#toolbar',pagination:'true'">
          <thead>
            <tr>
                <th data-options="field:'ck',checkbox:true"></th>
                <th data-options="field: 'id', hidden:'true'">角色ID</th>
                <th data-options="field: 'code', width:'200'">角色编号</th>
                <th data-options="field: 'name', width:'300'">角色名称</th>
            </tr>
          </thead>
        </table>
        <div id="toolbar">
            <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:'true'" onclick="newRole()">新建</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:'true'" onclick="editRole()">编辑</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:'true'" onclick="deleteRole()">删除</a>
        </div>
        <div id="dlg" class="easyui-window" style="width:200px;height:600px;padding:10px 20px" data-options="closed:'true',buttons:'#dlg-buttons'">
          <div class="easyui-layout" data-options="fit:true,border:false">
              <div data-options="region:'center'">
                <form id="ff" method="post">
                    <div class="fitem">
                        <input id="id" name="id" type="hidden">
                    </div>
                    <div class="fitem">
                        <label>角色编号:</label>
                        <input id="code" name="code" class="easyui-validatebox" data-options="required: true" style="width: 180px;"/>
                    </div>
                    <div class="fitem">
                        <label>角色名称:</label>
                        <input id="name" name="name" class="easyui-validatebox" data-options="required: true" style="width: 180px;"/>
                    </div>
                </form>
              </div>
              <div id="dlg-buttons" data-options="region:'south',border:false" style="text-align: right;margin-bottom: 15px;">
                <button class="easyui-linkbutton" id="save-btn" data-options="iconCls:'icon-ok'" onclick="saveForm()" style="width:90px">保存</button>
                <button class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</button>
                <button class="easyui-linkbutton" iconCls="icon-remove" onclick="clearForm()" style="width:90px">重置</button>
              </div>
          </div>
        </div>              
    </div>
    <div region="east" border="false" style="width: 400px;">
        <div class="easyui-layout" data-options="fit:true,border:false">
            <div data-options="region:'center',fix:true,title:'菜单设置',tools:[{iconCls:'icon-save',handler:function(){saveMenuTree()}}]" style="width: 250px;">
                <input type="hidden" id="roleId" name="roleId">
                <ul id="menu_tree"></ul>
            </div>
            <div data-options="region:'east',fix:true,title:'用户列表'" style="width: 200px;">
                <ul id="user_list"></ul>
            </div>
        </div>
    </div>
</div>
    <script type="text/javascript">
    	var url;
    	/**
			创建角色
		**/
        function newRole(){
            $('#ff').form('clear');
            $('#dlg').window('open').dialog('setTitle','新建角色');
            url = '<%=basePath%>role/c';
        }
        
        /**
        	修改角色
        **/
        function editRole() {
        	$("#save-btn").attr("disabled","true");
    		var row = $('#dg').datagrid('getSelected');
            if (!row) {
               $.messager.alert('提示', "请选中一条记录再进行修改！");
               return;
            } else {
            	$("#save-btn").removeAttr('disabled');
    			$('#ff').form('clear');
            	$('#dlg').window('open').dialog('setTitle','编辑角色');
            	$('#ff').form('load', row);
            }
            url='<%=basePath%>role/u';
        }
        
        /**
           	删除角色
        **/
        function deleteRole() {
        	var row = $('#dg').datagrid('getSelected');
            if (!row) {
               $.messager.alert('提示', "请选中一条记录再进行删除！");
               return;
            } else {
               $.messager.confirm("提示", "是否删除选中的数据?", function (r) {
    		       if(r){
    			       $.ajax({  
    				       type:"POST",  
    				       url:"<%=basePath%>role/d/"+row.id,  
    				       success:function(msg){ 
    				    	   $.messager.alert('提示', msg);
    				           loadGrid();
    				       }  
    				   });
    				}
    			});
            }
        }
        
        function saveForm() {
        	$('#ff').form('submit', {
				url : url,
				method : "POST",
				onSubmit : function() {
					return $(this).form('validate');
				},
				success : function(result) {
					$.messager.alert('提示', result);
					$("#dlg").window('close'); // close the role form
					$('#dg').datagrid('reload'); // reload the role data
				}
			});
        }
    </script>
  </body>
</html>