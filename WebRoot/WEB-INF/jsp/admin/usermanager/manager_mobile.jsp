<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <jsp:include page="../../../../include/sys-common.jsp" />
<style type="text/css">
#ff {
	margin: 0;
	padding: 10px 30px;
}
.fitem {
	margin-bottom: 5px;
}

.fitem label {
	display: inline-block;
	width: 80px;
}

.fitem input {
	width: 180px;
}
.fitem input[type="checkbox"] {
	width: 20px;
}
</style>
<script type="text/javascript">
	var datagrid;
	$(document).ready(function(){
		loadGrid();
	});
</script>
  </head>
  
  <body>
	<table id="dg" title="嘉宾通知领导名单" style="width:100%;height:100%"
		data-options="striped:true,rownumbers:true,singleSelect:false,url:'managerMobile/r',method:'POST', idField:'id',
       				 multiSort:true,fit:true,nowrap:false,toolbar:'#toolbar',pagination:'true'">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'id', hidden:'true'"></th>
				<th data-options="field:'managerName', width:'100'">通知领导姓名</th>
				<th data-options="field:'managerPosition', width:'100'">职位</th>
				<th data-options="field:'managerMobile', width:'100'">手机号码</th>
				<th data-options="field:'remark', width:'150'">备注</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:'true'" onclick="newMobile()">新建</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:'true'" onclick="editMobile()">编辑</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:'true'" onclick="deleteMobile()">删除</a>
	</div>
	<div id="dlg" class="easyui-dialog"
		style="width:450px;height:350px;padding:10px 20px" closed="true"
		maximizable="true" resizable="true" collapsible="true"
		left="300" top="80"
		buttons="#dlg-buttons">
	      <div data-options="region:'center'">
	        <form id="ff" method="post">
	        	<div class="fitem">
	        		<input id="id" name="id" type="hidden">
	        	</div>
	            <div class="fitem">
	                <label>通知领导姓名:</label>
	                <input name="managerName" class="easyui-textbox" maxlength="50" data-options="required: true"/>
	            </div>
	            <div class="fitem">
	            	<label>职位:</label>
	            	<input name="managerPosition" class="easyui-textbox"  maxlength="50" data-options="required: true"/>
	            </div>
	            <div class="fitem">
	            	<label>手机号码:</label>
	            	<input name="managerMobile" class="easyui-numberbox" maxlength="50" data-options="required: true"/>
	            </div>
	            <div class="fitem">
	            	<label>备注:</label>
	            	<textarea id="remark" name="remark" maxlength="1024" style="width:300px;height:100px"></textarea>
	            </div>
	        </form>
	      </div>
		<div id="dlg-buttons">
			<button class="easyui-linkbutton" id="saveId" iconCls="icon-ok" onclick="saveForm()" style="width:70px">保存</button>
			<button class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:70px">取消</button>
			<button class="easyui-linkbutton" iconCls="icon-remove" onclick="clearForm()" style="width:70px">重置</button>
		</div>
    </div>
    
<script type="text/javascript">
    function loadGrid()  {
        datagrid = $('#dg').datagrid({
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
   
   // 新建嘉宾
    var url;
    function newMobile() {
        $('#dlg').dialog('open').dialog('setTitle', '新建嘉宾通知记录');
        $('#ff').form('clear');
        url = 'managerMobile/add';
    }
    
    // 编辑表单
    function editMobile() {
        $("#saveId").attr("disabled","true");
        var row = $('#dg').datagrid('getSelected');
           if (!row) {
              $.messager.alert('提示', "请选中一条记录再进行修改！");
              return;
           } else {
            $("#saveId").removeAttr('disabled');
            $('#ff').form('clear');
            $('#dlg').window('open').dialog('setTitle','修改手机号码');
            $('#ff').form('load', row);
           }
           url='managerMobile/update';
    }
    
    //保存Form表单
    function saveForm() {
        $("#saveId").attr("disabled","true");
        $('#ff').form('submit', {
            url : url,
            method : "POST",
            success : function(result) {
                $("#saveId").removeAttr('disabled');
                $.messager.alert('提示', result);
                $('#dlg').dialog('close'); 
                $('#dg').datagrid('reload');
            }
        });
        $("#saveId").removeAttr('disabled');
    }
    
    //删除
    function deleteMobile() {
        var rows = $('#dg').datagrid('getSelections');
        if (!rows) {
            $.messager.alert('提示', "请选中一条记录再进行删除");
            return;
        }
        var num=rows.length;//获取要删除信息的个数
        var sn = "";
        for(var i=0; i<rows.length; i++){//组成一个字符串，ID主键之间用逗号隔开
            if(i!=rows.length-1){
                sn=sn+rows[i].id+",";
             }else{
                 sn=sn+rows[i].id;
             }
        }
        $.messager.confirm('确认', '确定要删除这'+ num+'条记录吗？', function(r) {
            if (r) {
                $.ajax({
                    url : 'managerMobile/delete',
                    type : "GET",
                    data: {'ids':sn},
                    success : function(msg) {
                        $.messager.alert('提示', msg);
                        $('#dg').datagrid('reload');
                        $('#dg').datagrid('clearSelections');
                    }
                });
            }
        });
    }
    
    // 清空表单
    function clearForm() {
        $('#ff').form('clear');
    }
</script>
  </body>
</html>
