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
  		var userDg;
  		var datagrid;
  		// 获取用户类型
        var userType;
		$(document).ready(function(){  
	    	loadGrid(); 
	    	$("#user_search").bind('click',function(){
	    		datagrid.datagrid({
                    pageNumber : 1
                });
	    	});  
	    	$('#dlg').window({   
	          width:500,   
	          height:330,   
	          resizable:false  
	          });
	    	$('#dlg').window("close");
	    	$("#importDlg").window({
	    		width:700,   
	          	height:430,   
	          	resizable:false  
	    	});
	    	$('#importDlg').window("close");
	    	$("#submit_search").bind('click',function(){
	    		userDg.datagrid({
	    			queryParams:{
    	        		mail:$("#s_mail").val(),
    	        		loginName:$("#s_loginName").val()
    				}
	    		});
	    	});
	    	// 获取用户类型
	    	$.ajax({
                url: 'dict/r/user_type',
                method:'get',
                async:false,
                success:function(data) {
                    userType = data;
                }
            });
		});
		
		function loadGrid()  {
	    	datagrid=$('#dg').datagrid({
	        	nowrap:false,
	        	method:'POST',
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
	        		param.name=$("#name").val();
	        		param.mail=$("#mail").val();
	        	},
	        	onSelect : function(rowIndex,rowData){
	        		setRolebyUser(rowData.id);
	        	}
	    	});
		}
		
		/**
           	初始化右侧角色树
        **/
		function setRolebyUser(sysUserId) {
			$("#role_tree").tree({
				checkbox : true,
				url : '<%=basePath%>roleUser/getRole',
				queryParams : {sysUserId: sysUserId}
			});
			// 将roleId设置到隐藏域中
			$("#sysUserId").val(sysUserId);
		}
		
		/**
		   	保存用户角色节点
		**/
		function saveRoleTree() {
			var selectNodes = GetNode();
			// 提交
			$.ajax({        
        		type: "POST",                                     
        		url: "<%=basePath%>roleUser/saveRoleUser",                                  
        		data: "sysUserId="+$("#sysUserId").val()+"&selectNodes="+selectNodes,   
        		success: function(result){ 
        			$.messager.alert('提示', result);                 
        		}   
      		}); 
		}
		
		/**
		   	获取选中的节点
		**/
		function GetNode() {
			var node = $('#role_tree').tree('getChecked');
			var cnodes = '';
			for ( var i = 0; i < node.length; i++) {
				cnodes += node[i].id + ',';
			}
			cnodes = cnodes.substring(0, cnodes.length - 1);
			return cnodes;
		}
	</script>
  </head>
  
  <body class="easyui-layout" fit="true">
  	<div data-options="region:'north',border:false,title:'查询条件'" style="height:61px;">
		<form id="search_form" style="margin-left: 20px;vertical-align: middle;">
			<label>姓名:</label>
	        <input name="name" type="text" id="name" class="easyui-textbox" style="width: 300px;" />
			<label>电子邮箱:</label>
	        <input name="mail" type="text" id="mail" class="easyui-textbox" style="width: 300px;" />
               <input id="user_search" type="button" value="查询" class="search-btn"/>
		</form>
	</div>
  	<div data-options="region:'center',border:false">
	<table id="dg" title="用户管理" style="width:100%;height:100%"
			data-options="striped:true,rownumbers:true,singleSelect:true,url:'confSysUser/r',method:'post', 
       				 multiSort:true,fit:true,nowrap:false,toolbar:'#toolbar',pagination:'true'">
		<thead>
			<tr>
			    <th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field: 'id', hidden:'true'">用户编号</th>
				<th data-options="field: 'loginName', width:'200'">用户名</th>
				<th data-options="field: 'name', width:'200'">姓名</th>
				<th data-options="field: 'mail', width:'150'">电子邮箱</th>
				<th data-options="field: 'company', width:'200'">单位名称</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:'true'" onclick="newSysUser()">新建</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:'true'" onclick="importSysUser()">工作人员导入</a>
        <!-- <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:'true'" onclick="editSysUser()">编辑</a> -->
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:'true'" onclick="deleteSysUser()">删除</a>
    </div>
    <div id="dlg" class="easyui-window" style="width:300px;height:600px;padding:10px 20px" data-options="closed:'true',buttons:'#dlg-buttons'">
      <div class="easyui-layout" data-options="fit:true,border:false">
	      <div data-options="region:'center'">
	        <form id="ff" method="post">
	        	<div class="fitem">
	        		<input type="hidden" id="id" name="id">
	        	</div>
	            <div class="fitem">
	                <label>用户名:</label>
	                <input id="loginName" name="loginName" class="easyui-validatebox" data-options="required:true,validType:{length:[4,50],remote:['<%=basePath %>confSysUser/checkUserName','loginName']}" style="width: 180px;" invalidMessage="用户名已存在"/>
	            </div>
	            <div class="fitem" id="pwd">
	                <label>密码:</label>
	                <input type="password" id="loginPassword" name="loginPassword" class="easyui-validatebox" data-options="required: true" style="width: 180px;" validType="length[6,30]"/>
	            </div>
	            <div class="fitem" id="confirmPwd">
	            	<label>确认密码:</label>
	            	<input type="password" id="comfirmPassword" name="comfirmPassword" class="easyui-validatebox" data-options="required: true" style="width: 180px;" validType="equals['#loginPassword']"/>
	            </div>
	            <div class="fitem">
	                <label>姓名:</label>
	                <input id="name" name="name" class="easyui-validatebox" data-options="required:true,validType:{length:[2,50]}" style="width: 180px;"/>
	            </div>
	            <div class="fitem">
	            	<label>电子邮箱:</label>
	            	<input id="mail" name="mail" class="easyui-validatebox" style="width: 180px;" data-options="validType:'email'" />
	            </div>
	            <div class="fitem">
	            	<label>单位名称:</label>
	            	<input id="company" name="company" class="easyui-validatebox" style="width: 180px;"/>
	            </div>
	        </form>
	      </div>
	      <div id="dlg-buttons" data-options="region:'south',border:false" style="text-align: right;margin-bottom: 15px;">
	        <button class="search-btn" id="save-btn" data-options="iconCls:'icon-ok'" onclick="saveForm()" style="width:90px">保存</button>
	        <button class="search-btn" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</button>
	        <button class="search-btn" iconCls="icon-remove" onclick="clearForm()" style="width:90px">重置</button>
	      </div>
      </div>
    </div>
    <div id="importDlg" class="easyui-window" style="width:300px;height:600px;padding:10px 20px" data-options="closed:'true'">
    	<div class="easyui-layout" data-options="region:'center',border:false,fit:true">
    		<div data-options="region:'north',border:false,title:'查询条件'" style="height:61px;">
				<form id="search_form" style="margin-left: 20px;vertical-align: middle;">
					<label>用户姓名:</label>
			        <input name="s_loginName" type="text" id="s_loginName" class="easyui-textbox" style="width: 180px;" />
					<label>电子邮箱:</label>
			        <input name="s_mail" type="text" id="s_mail" class="easyui-textbox" style="width: 180px;" />
	                <input id="submit_search" type="button" value="查询" class="search-btn"/>
				</form>
			</div>
			<div data-options="region:'center',border:false">
				<table id="userDg"></table>
			</div>
    	</div>
    </div>
    </div>
    <div data-options="region:'east',fix:true,title:'对应角色',tools:[{iconCls:'icon-save',handler:function(){saveRoleTree()}}]" style="width: 300px;">
		<input type="hidden" id="sysUserId" name="sysUserId">
		<ul id="role_tree"></ul>
	</div>
    
    <script type="text/javascript">
    	var url;
    	/**
			创建新表单
		**/
        function newSysUser(){
        	// 密码框显示
            $("#pwd").show();
            $("#confirmPwd").show();
            $('#ff').form('clear');
            $('#dlg').window('open').dialog('setTitle','新建用户');
            url = '<%=basePath%>confSysUser/user/save';
        }
    	
    	/**
    	   	编辑表单
    	**/
    	function editSysUser() {
    		$("#save-btn").attr("disabled","true");
    		var row = $('#dg').datagrid('getSelected');
            if (!row) {
               $.messager.alert('提示', "请选中一条记录再进行修改！");
               return;
            } else {
            	$("#save-btn").removeAttr('disabled');
    			$('#ff').form('clear');
            	$('#dlg').window('open').dialog('setTitle','编辑用户');
            	$('#ff').form('load', row);
            	// 密码框隐藏
            	$("#pwd").hide();
            	$("#confirmPwd").hide();
            	$("#pwd").validatebox({required:false}); 
            	$("#confirmPwd").validatebox({required:false});
            }
            url='<%=basePath%>confSysUser/user/update';
    	}
    	
    	/**
    	   保存Form表单
    	**/
    	function saveForm() {
    		$('#ff').form('submit', {
				url : url,
				method : "POST",
				success : function(result) {
					$.messager.alert('提示', result);
					$("#dlg").window('close'); // close the sysuser form
					$('#dg').datagrid('reload'); // reload the sysuser data
				}
			});
    	}
    	
    	/**
    	   	删除用户
    	**/
    	function deleteSysUser() {
    		var row = $('#dg').datagrid('getSelected');
            if (!row) {
               $.messager.alert('提示', "请选中一条记录再进行删除！");
               return;
            } else {
               $.messager.confirm("提示", "是否删除选中的数据?", function (r) {
    		       if(r){
    			       $.ajax({  
    				       type:"POST",  
    				       url:"<%=basePath%>confSysUser/user/delete/"+row.id,  
    				       success:function(msg){ 
    				    	   $.messager.alert('提示', msg);
    				           loadGrid();
    				       }  
    				   });
    				}
    			});
            }
    	}
    	
    	/**
    	         清空表单
    	**/
        function clearForm() {
            $('#ff').form('clear');
        }
        
        /**
           	工作人员导入
        **/
        function importSysUser() {
        	$('#importDlg').window('open').dialog('setTitle','工作人员导入');
        	userDg=$('#userDg').datagrid({
        		url:'<%=basePath%>user/getUnimportUser',
        		columns:[[
        		    {field:'ck',checkbox:true},
        		    {field:'id',title:'id',hidden:true},
        			{field:'cname',title:'姓名',width:100},
        			{field:'sex',title:'性别',width:100,formatter:getSexValue},
        			{field:'userType',title:'用户类型',width:100,formatter:getUserType},
        			{field:'mobile',title:'联系电话',width:100},
        			{field:'email',title:'电子邮箱',width:200}
    			]],
	        	toolbar : [{
					text:'导入',
					iconCls:'icon-add',
					handler : function(){
						user_addFun();
					}
				}]
        	});
        }
        
        //用户类型匹配
        function getUserType(value, row , index) {
           for (var i=0; i<userType.length; i++) {
               if (value == userType[i].code) {
                   return userType[i].name;
               }
           }
       }
       
       /**
         	 匹配性别
       **/
       function getSexValue(value, row, index){
            if (row.sex == 1) {
                return "男";
            } else if (row.sex == 2) {
                return "女";
            }
        }
        
        /**
           	导入后台用户
        **/
        function user_addFun() {
       		var rows = $('#userDg').datagrid('getSelections');
            if (rows=="") {
                $.messager.alert('提示', "请选中一条记录再进行导入");
                return;
            }
            var num=rows.length;//获取要删除信息的个数
            var sn = "";
            for(var i=0; i<rows.length; i++){//组成一个字符串，ID主键之间用逗号隔开
                if(i!=rows.length-1){
                    sn+=rows[i].id+",";
                 }else{
                     sn=sn+rows[i].id;
                 }
            }
            
            $.ajax({
	            url : '<%=basePath%>confSysUser/importUser',
	            type : "POST",
	            data: {'userIds':sn},
	            success : function(msg) {
	                $.messager.alert('提示', msg,'info', function() {
	                	$('#userDg').datagrid('clearSelections');
	                	$('#importDlg').window("close");
	                	$('#dg').datagrid('reload'); // reload the user data,loadGrid();
	                });
	            }
	        });
        }
    </script>
  </body>
</html>