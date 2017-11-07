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
    <script src="<%=basePath%>js/common.js"></script>
<style type="text/css">
.fitem input[type="radio"] {
    width: 20px;
}
</style>
<script type="text/javascript">
	var datagrid;
    var userType;// 选择类型的检索条件的值
    var userDg;
    var dataList;//指派人员数据源
     $(function(){
        //获取用户类型数据
         $.ajax({
             url: 'dict/r/user_type',
             method:'get',
             async:false,
             success:function(data) {
                 userType = data;
             }
         });
        $("#userEventAdd").window({
                width:700,   
                height:430,   
                resizable:false
        });
        $('#userEventAdd').window("close");
     });
    
	$(document).ready(function(){
		loadGrid();
        $("#submit_search").bind('click',function(){
            userDg.datagrid({
                queryParams:{
                    userType:$("#s_userType").combobox("getValue"),
                    cname:$("#s_name").val()
                }
            });
        });
        $("#reset_earch").bind('click',function(){
            $("#s_name").val("");
            $("#s_userType").combobox('setValue',"");
        });
	});
</script>
  </head>
  
  <body>
    <div class="easyui-layout" fit="true" >
	    <div region="center" border="false" style="padding: 1px;">
			<table id="dg" title="定制事件列表" style="width:100%;height:100%"
				data-options="striped:true,rownumbers:true,singleSelect:true,url:'eventTailor/r',method:'POST', idField:'id',
		       				 multiSort:true,fit:true,nowrap:false,toolbar:'#toolbar',pagination:'true'">
				<thead>
					<tr>
						<th data-options="field:'ck',checkbox:true"></th>
						<th data-options="field:'id', hidden:'true'"></th>
						<th data-options="field:'name', width:'80'">事件名称</th>
						<th data-options="field:'type', width:'60',formatter:getType">通知类型</th>
						<th data-options="field:'location', width:'120'">地点</th>
						<th data-options="field:'startTime', width:'100'">开始时间</th>
						<th data-options="field:'endTime', width:'100'">结束时间</th>
						<th data-options="field:'remark', width:'200'">备注</th>
					</tr>
				</thead>
			</table>
			<div id="toolbar">
		        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:'true'" onclick="newEvent()">新建</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:'true'" onclick="editEvent()">编辑</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:'true'" onclick="deleteEvent()">删除</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:'true'" onclick="userEventAdd()">添加参与事件的人员</a>
		        <a href="javascript:void(0)" id="sendSms" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:'true'" onclick="sendRemind()">推送事件提醒</a>
			</div>
			<div id="userEventAdd" class="easyui-window" style="width:400px;height:600px;padding:10px 20px" data-options="closed:'true'">
		        <div class="easyui-layout" data-options="region:'center',border:false,fit:true">
		            <div data-options="region:'north',border:false,title:'查询条件'" style="height:60px;">
		                <form id="search_form" style="margin-left: 20px;vertical-align: middle;">
			                <label>用户类型:</label>
			                <input id="s_userType" class="easyui-combobox" editable="false" name="s_userType" style="width: 180px;"
			                        data-options="valueField:'code',textField:'name',
			                        url:'dict/r/user_type',method:'get'" />
		                    <label>用户姓名:</label>
		                    <input name="s_name" type="text" id="s_name" class="easyui-validatebox" validType="searchParm" style="width: 180px;" />
		                    <input id="submit_search" type="button" value="查询" class="button"/>
		                    <input class="button" id="reset_earch" type="button" value="重置" class="button"/>
		                </form>
		            </div>
		            <div data-options="region:'center',border:false">
		                <table id="userDg"></table>
		            </div>
		        </div>
		    </div>
			<div id="dlg" class="easyui-dialog"
				style="width:600px;height:520px;padding:10px 20px" closed="true"
				maximizable="true" resizable="true" collapsible="true"
				left="200" top="10"
				buttons="#dlg-buttons">
			      <div data-options="region:'center'">
			        <form id="ff" method="post">
			        	<div class="fitem">
			        		<input type="hidden" id="id" name="id">
			        	</div>
			            <div class="fitem">
			                <label>事件名称:</label>
			                <input name="name" class="easyui-validatebox" maxlength="50" data-options="required: true" style="width: 250px;"/>
			            </div>
			            <div class="fitem">
			                <label>事件名称(英文):</label>
			                <input name="nameEn" class="easyui-validatebox" maxlength="50" data-options="required: true" style="width: 250px;"/>
			            </div>
			            <div class="fitem">
			            	<label>通知类型:</label>
			            	<input type="radio" class="radio" name="type" value="1" />不通知
			            	<input type="radio" class="radio" name="type" value="2" checked>邮件
		                    <input type="radio" class="radio" name="type" value="3">短信
		                    <input type="radio" class="radio" name="type" value="4">邮件和短信<br>
		                    <input type="hidden" id="typeVal">
			            </div>
			            <div class="fitem">
		                    <label>地点:</label>
		                    <input name="location" id="location" class="easyui-validatebox" maxlength="50" data-options="required: true" style="width: 250px;"/>
		                </div>
			            <div class="fitem">
		                    <label>地点(英文):</label>
		                    <input name="locationEn" id="locationEn" class="easyui-validatebox" maxlength="50" data-options="required: true" style="width: 250px;"/>
		                </div>
			            <div class="fitem">
		                    <label>开始时间:</label>
		                    <input name="startTime" id="startTime" class="easyui-datetimebox" editable="false" data-options="showSeconds:false"/>
		                </div>
			            <div class="fitem">
		                    <label>结束时间:</label>
		                    <input name="endTime" id="endTime" class="easyui-datetimebox" editable="false" data-options="showSeconds:false"/>
		                </div>
		                
			            <div class="fitem">
			            	<label>备注:</label>
			            	<textarea id="remark" name="remark" class="text" maxlength="1024" style="width:300px;height:120px"></textarea>
			            </div>
			            <div class="fitem">
			            	<label>备注(英文):</label>
			            	<textarea id="remarkEn" name="remarkEn" class="text" maxlength="1024" style="width:300px;height:120px"></textarea>
			            </div>
			        </form>
			      </div>
				<div id="dlg-buttons">
					<button class="easyui-linkbutton" id="saveId" iconCls="icon-ok" onclick="saveForm()" style="width:70px">保存</button>
					<button class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:70px">取消</button>
					<button class="easyui-linkbutton" iconCls="icon-remove" onclick="clearForm()" style="width:70px">重置</button>
				</div>
		    </div>
	    </div>
        <div data-options="region:'east',fix:true,title:'用户列表'" style="width: 250px;">
             <ul id="user_list"></ul>
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
            onSelect : function(rowIndex,rowData){
                setUserByEvent(rowData.id);
            },
        });
    }
   
   // 新建嘉宾
    var url;
    function newEvent() {
        $('#dlg').dialog('open').dialog('setTitle', '新建事件');
        $('#ff').form('clear');
        url = 'eventTailor/add';
    }
    
    // 编辑表单
    function editEvent() {
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
           url='eventTailor/update';
    }
    
    //保存Form表单
    function saveForm() {
        var selectedType = $("input[name='type'][checked]").val();
        $("#typeVal").val(selectedType);
        $("#saveId").attr("disabled","true");
        var startTime = $("#startTime").datetimebox("getText");
        var endTime = $("#endTime").datetimebox("getText");
        if(dateLargeThan(startTime,endTime)){
            $.messager.alert("提示","时间填写不正确，开始时间不能大于结束时间！");
            $("#saveId").removeAttr('disabled');//将按钮可用
            return false;
        }
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
    function deleteEvent() {
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
                    url : 'eventTailor/delete',
                    type : "GET",
                    data: {'ids':sn},
                    success : function(msg) {
                        $.messager.alert('提示', msg);
                        setUserClear();
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
    
    //通知类型处理
    function getType(value){
        if (value == "1") {
            return "不通知";
        } else if (value == "2") {
            return "邮件";
        } else if (value == "3") {
            return "短信";
        } else if (value == "4") {
            return "邮件和短信";
        } else {
            return "";
        }
    }
    
    //给事件指定参与的人员
    function userEventAdd() {
       var row = $('#dg').datagrid('getSelected');
       if (!row) {
           $.messager.alert('提示', "请选中一种事件再对添加人员！");
           return;
       }
       $('#userEventAdd').window('open').dialog('setTitle','添加事件人员');
       var selectedAssignId = "";
       var selectedIds = "";
       $.ajax({
            url: '<%=basePath%>eventTailor/getSelectedUsers/' + row.id,
            method:'POST',
            async:false,
            success:function(data) {
                selectedAssignId = data;
            }
        });
       if (selectedAssignId != null && selectedAssignId != "") {
           selectedAssignId = JSON.stringify(selectedAssignId);
           selectedAssignId = selectedAssignId.substring(1, selectedAssignId.length - 1);
           selectedIds = selectedAssignId.split(",");
       }
       userDg=$('#userDg').datagrid({
           url: '<%=basePath%>eventTailor/getUser/' + row.id,
           singleSelect:false,
           rownumbers:true,
           columns:[[
               {field:'ck',checkbox:true},
               {field:'userId',title:'id',hidden:true},
               {field:'cname',title:'姓名',width:70},
               {field:'ename',title:'英文名',width:100},
               {field:'sex',title:'性别',width:30,formatter:getSexValue},
               {field:'ConfUser.userType',title:'用户类型',width:100,formatter:getUserType},
               {field:'mobile',title:'联系电话',width:110},
               {field:'email',title:'电子邮箱',width:190}
           ]],
           onLoadSuccess:function(data){//数据加载成功后触发
               for (var i=0; i<data.rows.length;i++) {
                    for (var j=0; j<selectedIds.length;j++) {
                        if(data.rows[i].userId == selectedIds[j]){
                            $(this).datagrid('selectRow',i);
                        }
                    }
                }
           },
           toolbar : [{
               text:'指定',
               iconCls:'icon-add',
               handler : function(){
                   user_addFun();
               }
           }]
        });
    }
     
    //保存选择的指派数据
    function user_addFun() {
        var rows = $('#userDg').datagrid('getSelections');
        var row = $('#dg').datagrid('getSelected');
        if (rows == "" || rows == null) {
             $.messager.confirm('确认', '您没有选择任何嘉宾，该操作将删除您当前选择的事件的参与嘉宾，您确定要事件中的人员删除吗？', function(r) {
                if(r) {
                    $.ajax({
                        url : 'eventTailor/deleteEventUser',
                        type : "GET",
                        success : function(msg) {
                            $.messager.alert('提示', msg,'info', function() {
                                $('#userDg').datagrid('clearSelections');
                                $('#userEventAdd').window("close");
                                $('#dg').datagrid('reload'); // reload the user data,loadGrid();
                            });
                        }
                    });
                } else {
                    return;
                }
             });
        } else {
            var num=rows.length;
            var sn = "";
            for(var i=0; i<rows.length; i++){//组成一个字符串，ID主键之间用逗号隔开
                if(i!=rows.length-1){
                    sn+=rows[i].userId+",";
                 } else {
                     sn=sn+rows[i].userId;
                 }
            }
             $.ajax({
                 url : 'eventTailor/saveEventUser',
                 type : "POST",
                 data: {'userIds':sn},
                 success : function(msg) {
                     $.messager.alert('提示', msg,'info', function() {
                         $('#userDg').datagrid('clearSelections');
                         $('#userEventAdd').window("close");
                         $('#dg').datagrid('reload');
                         setUserByEvent(row.id);
                     });
                 }
             });
        }
    }
    
     //设定性别
     function getSexValue(value, row, index){
         if (row.sex == 1) {
             return "男";
         } else if (row.sex == 2) {
             return "女";
         }
      }
     //人员指定列表用户类型匹配
     function getUserType(value, row , index) {
          for (var i=0; i<userType.length; i++) {
              if (row.userType == userType[i].code) {
                  return userType[i].name;
              }
          }
      }
      
      //推送事件提醒
      function sendRemind() {
        var row = $('#dg').datagrid('getSelected');
        if (!row) {
            $.messager.alert('提示', "请先选中一种定制事件再进行该操作。");
            return;
        }
     	if (row.type == "1") {
      		$.messager.alert('提示', "对不起，该事件的通知类型为不通知，不能进行此操作！");
           return;
       }
       $("#sendSms").linkbutton("disable");
       var userCount = "";
       var remindMethod = getType(row.type);
       $.ajax({
            url: '<%=basePath%>eventTailor/queryUserCount/' + row.id,
            method:'POST',
            async:false,
            success:function(data) {
                userCount = data;
            }
        });
       	var countStr = JSON.stringify(userCount);
        userCount = countStr.substring(1, countStr.length - 1);
       	if (userCount == "0") {
       		$.messager.alert('提示', "该事件没有指定参与的嘉宾，请先指定参与嘉宾，再发送提醒！");
            return;
       	}
        $.messager.confirm('提醒', "您确定给定制了该事件的" + userCount + "位嘉宾推送 " + remindMethod + " 提醒吗？", function(r){
            if(r) {
                $.ajax({
                    url : 'eventTailor/sendRemind',
                    type : "POST",
                    data: {'eventId':row.id},
                    success : function(msg) {
                    	$("#sendSms").linkbutton("enable");
                        $.messager.alert('提示', msg);
                    }
                });
            } else {
                return;
            }
        });
      }
      
      //初始化右侧嘉宾列表
      function setUserByEvent(eventId) {
        $("#user_list").datagrid({
            url: '<%=basePath%>eventTailor/getUser',
            singleSelect:true,
            rownumbers : true,
            columns:[[
              {field:'userId',title:'id',hidden:true},
              {field:'name',title:'姓名',width:100},
              {field:'userType',title:'用户类型',width:100,formatter:getUserType},
            ]],
            queryParams : {eventId: eventId},
        });
      }
      
      //删除时右侧列表清空
      function setUserClear(){
        $("#user_list").datagrid('loadData',{total:0,rows:[]});
      }
</script>
  </body>
</html>
