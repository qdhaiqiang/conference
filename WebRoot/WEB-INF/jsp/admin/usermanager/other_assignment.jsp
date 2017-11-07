<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <jsp:include page="../../../../include/sys-common.jsp"></jsp:include>
<style type="text/css">
.search-select {
    width: 70px;
}
#ff {
    margin: 0;
    padding: 10px 30px;
}

.fitem {
    margin-bottom: 5px;
}
.fitem label {
    display: inline-block;
    width: 180px;
}

.fitem input {
    width: 180px;
}
.search_li {
    margin:10px;
}
ul {
  margin:0px;
  border-style: solid;
  border-width: 0px;
}
.h_prompt {
    margin-left:20px;
    font-weight:bold;
}
</style>
  <script type="text/javascript">
    var datagrid;
    // 选择类型的检索条件的值
    var userType;
    var nation;
    var companyType;
    // 获取data
    var suserType = "";
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
        $("#suserType").combobox({
             data : userType,
             valueField:'code',
             textField:'name',
             multiple:true,
             editable:false
        });
        $("#importDlg").window({
                width:850,   
                height:500,   
                resizable:false,
                singleSelect:true
        });
        $('#importDlg').window("close");
     });

    $(document).ready(function(){
        loadGrid();
        $("#submit_search").bind('click',function(){
        	var userTypes = "";
        	var staffTypes = $("#s_userType").combobox('getValues');
	        for(var i=0;i<staffTypes.length;i++){
	            userTypes += staffTypes[i] + ",";
	        }
            userDg.datagrid({
                queryParams:{
                    cname:$("#s_cname").val(),
                    userType:userTypes
                }
            });
        });
        $("#user_submitSearch").bind('click',function(){
            if (!$("#select").form('validate')) {
                return false;
            }else {
                suserType = $("#suserType").combobox("getValues");
                datagrid.datagrid({
                    pageNumber : 1
                });
            }
        });
        $("#user_resetSearch").bind('click',function(){
            $("#suserType").combobox('setValue',"");
        });
        $("#staff_resetSearch").bind('click',function(){
            $("#s_userType").combobox('setValue',"");
            $("#s_cname").val("");
        });
    });
  </script>
  </head>
  <body>
    <!-- 展示界面Div -->
    <div id="cc" class ="easyui-layout" data-options="region:'center',border:false,fit:true">查询
        <!--日程控制人员指派表 -->
        <div data-options="region:'center',border:false">
            <table id="dg" title="会场指派" style="width:100%;height:100%">
                <thead>
                    <tr>
                        <th data-options="field:'ck',checkbox:true"></th>
                        <th data-options="field:'scheduleId', hidden:'true'"></th>
                        <th data-options="field:'assignId', hidden:'true'"></th>
                        <th data-options="field:'title', width:'27%'">日程名称</th>
                        <th data-options="field:'assignName', width:'60%'">指派人员</th>
                        <th data-options="field:'action', width:'10%', formatter:assignUser">操作</th>
                    </tr>
                </thead>
            </table>
        </div>
	    <div id="importDlg" class="easyui-window" style="width:300px;height:600px;padding:10px 20px" data-options="closed:'true'">
	        <div class="easyui-layout" data-options="region:'center',border:false,fit:true">
	            <div data-options="region:'north',border:false,title:'查询条件'" style="height:55px;">
	                <form id="search_form" style="margin-left: 20px;vertical-align: middle;">
	                    <label>用户姓名:</label>
	                    <input name="s_cname" type="text" id="s_cname" class="easyui-textbox" style="width: 180px;" />
	                    <label style="margin-left:2px;">人员类型:</label>
               		    <input id="s_userType" class="easyui-combobox" editable="false" name="s_userType" class="easyui-validatebox" />	
	                    <input id="submit_search" type="button" value="查询" class="search-btn"/>
	                    <input id="staff_resetSearch" type="button" value="重置" class="search-btn"/>
	                </form>
	            </div>
	            <div data-options="region:'center',border:false">
	                <table id="userDg"></table>
	            </div>
	        </div>
	    </div>
    </div>
     <script type="text/javascript">
     function loadGrid() {
        datagrid = $('#dg').datagrid({
            url: 'assignment/rOther',
            method:'POST',
            nowrap:false,
            loadMsg:'加载中，请稍候...',
            fitColumns:true,
            pagination : true,//页码
            pageNumber : 1,//初始页码
            pageSize : 15,
            pageList : [15,30,45,60],
            striped : true,
            rownumbers : true,
            singleSelect : true,
            idField:'userId',
            multiSort:true,
            fit:true,
            nowrap:false,
            toolbar:'#toolbar',
            pagination:true,
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
            },
        });
    }

     //设定性别
     function getSexValue(value, row, index){
         if (row.sex == 1) {
             return "男";
         } else if (row.sex == 2) {
             return "女";
         }
      }

     //指派人员
     function assignUser(value, row, index) {
        return '<a onclick="editAssignUser()" style="cursor:pointer;"><span style="color:blue;">指派</span></a>';
     }

     //用户类型匹配
     function getUserType(value, row , index) {
          for (var i=0; i<userType.length; i++) {
              if (row.userType == userType[i].code) {
                  return userType[i].name;
              }
          }
      }
    
    //给指派工作人员
    function editAssignUser() {
        var row = $('#dg').datagrid('getSelected');
        if (!row) {
            $.messager.alert('提示', "请选中一个日程再对其指派人员信息！");
            return;
        }
        var staffType ;
 	   //获取工作人员类型数据
	       $.ajax({
	           url: 'dict/findStaff',
	           method:'get',
	           async:false,
	           success:function(data) {
	          	 staffType = data;
	           }
	       });
	      $("#s_userType").combobox({
	           data : staffType,
	           valueField:'code',
	           textField:'name',
	           multiple:true,
	           editable:false
	      });
        var selectedAssignId = "";
        if (row.assignId != null && row.assignId != "") {
            selectedAssignId = row.assignId.split(",");
        }
        $('#importDlg').window('open').dialog('setTitle','工作人员指派');
        userDg=$('#userDg').datagrid({
            url: '<%=basePath%>assignment/getOtherAssignMember/' + row.scheduleId,
            singleSelect : false,
            rownumbers : true,
            columns:[[
                {field:'ck',checkbox:true},
                {field:'assignId',title:'id',hidden:true},
                {field:'cname',title:'姓名',width:80},
                {field:'roleName',title:'组别',width:120},
                {field:'sex',title:'性别',width:40,formatter:getSexValue},
                {field:'userType',title:'用户类型',width:80,formatter:getUserType},
                {field:'company',title:'单位',width:100},
                {field:'mobile',title:'联系电话',width:100},
                {field:'email',title:'电子邮箱',width:180}
            ]],
            onLoadSuccess:function(data){//当数据加载成功时触发
                for (var i=0; i<data.rows.length;i++) {
                    for (var j=0; j<selectedAssignId.length;j++) {
	                    if(data.rows[i].assignId == selectedAssignId[j]){
	                        $(this).datagrid('selectRow',i);
	                    }
                    }
                }
            },
            toolbar : [{
                text:'指派',
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
        if (rows==null || rows.length == 0) {
             $.messager.confirm('确认', '您没有选择任何可指派人员，该操作将删除指派给您当前选择的日程的所有服务人员，确定要删除吗？', function(r) {
                if(r) {
                    $.ajax({
                        url : 'assignment/deleteOtherAssignUser',
                        type : "GET",
                        success : function(msg) {
                            $.messager.alert('提示', msg,'info', function() {
                                $('#userDg').datagrid('clearSelections');
                                $('#importDlg').window("close");
                                $('#dg').datagrid('reload'); // reload the user data,loadGrid();
                            });
                        }
                    });
                } else {
                    return;
                }
             });
        } else {
	        var num=rows.length;//获取要删除信息的个数
	        var sn = "";
	        for(var i=0; i<rows.length; i++){//组成一个字符串，ID主键之间用逗号隔开
	            if(i!=rows.length-1){
	                sn+=rows[i].assignId+",";
	             } else {
	                 sn=sn+rows[i].assignId;
	             }
	        }
	        var sname = "";
	        for(var i=0; i<rows.length; i++){//组成一个字符串，ID主键之间用逗号隔开
	            if(i!=rows.length-1){
	                sname+=rows[i].cname+",";
	             } else {
	                 sname=sname+rows[i].cname;
	             }
	        }
	        
	        $.ajax({
	            url : 'assignment/saveOtherAssignUser',
	            type : "POST",
	            data: {'assignUserIds':sn,'assignUserNames':sname},
	            success : function(msg) {
	                $.messager.alert('提示', msg,'info', function() {
	                    $('#userDg').datagrid('clearSelections');
	                    $('#importDlg').window("close");
	                    $('#dg').datagrid('reload'); // reload the user data,loadGrid();
	                });
	            }
	        });
        }
    }
  </script>
  </body>
</html>
