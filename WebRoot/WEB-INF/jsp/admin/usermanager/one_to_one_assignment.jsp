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
    width: 90px;
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
    var industry;
    // 获取data
    var suserType = "";
    var suserName = "";
    var sremindFlag = "";
    var userDg;
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
                resizable:false
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
                    email:$("#s_mail").val(),
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
                suserName = $("#sname").val().replace(" ","");
                sremindFlag = $("#sremindFlag").val().replace(" ","");
                datagrid.datagrid({
                    pageNumber : 1
                });
            }
        });
        $("#user_resetSearch").bind('click',function(){
            $("#suserType").combobox('setValue',"");
            $("#sremindFlag").val("");
            $("#sname").val("");
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
        <div data-options="region:'north',border:false,title:'查询条件'" style="height:55px">
            <form id="search_form">
                <label style="margin-left:20px;">嘉宾类型:</label>
                <input id="suserType" class="easyui-combobox" editable="false" name="suserType" class="easyui-validatebox" />
                <label style="margin-left:20px;">姓名:</label>
                <input id="sname" class="easyui-text" name="sname" class="easyui-validatebox" />
                <label style="margin-left:20px;">是否到场提醒嘉宾:</label>
                <select id="sremindFlag" class="search-select" name="sremindFlag"/>
                    <option >　</option>
                    <option value="1">是</option>
                    <option value="0">否</option>
                 </select>
                <input id="user_submitSearch" type="button" value="查询" class="search-btn"/>
                <input id="user_resetSearch" type="button" value="重置" class="search-btn"/>
            </form>
        </div>
        <!--用户列表 -->
        <div data-options="region:'center',border:false">
            <table id="dg" title="一对一指派" style="width:100%;height:100%">
                <thead>
                    <tr>
                        <th data-options="field:'ck',checkbox:true"></th>
                        <th data-options="field:'guestId', hidden:'true'"></th>
                        <th data-options="field:'userId', hidden:'true'"></th>
                        <th data-options="field:'cname', width:'90'">嘉宾姓名(中)</th>
                        <th data-options="field:'ename', width:'100'">嘉宾姓名(英)</th>
                        <th data-options="field:'guestType', width:'100', formatter:getGuestType">嘉宾类型</th>
                        <th data-options="field:'sex', width:'50', formatter:getSexValue">性别</th>
                        <th data-options="field:'mobile', width:'120'">联系电话</th>
                        <th data-options="field:'email', width:'180'">邮箱</th>
                        <th data-options="field:'remindFlag', width:'60',formatter:getRemindFlag">嘉宾到场提醒</th>
                        <th data-options="field:'assignName', width:'100'">服务专员姓名</th>
                        <th data-options="field:'assignMobile', width:'100'">服务专员电话</th>
                        <th data-options="field:'assignEmail', width:'180'">服务专员邮箱</th>
                        <th data-options="field:'approve', width:'80', formatter:assignUser">操作</th>
                    </tr>
                </thead>
            </table>
            <div id="toolbar">
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="setRemind()">设定为到场提醒嘉宾</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeRemind()">取消到场提醒嘉宾设定</a>
            </div>
        </div>
	    <div id="importDlg" class="easyui-window" style="width:350px;height:600px;padding:10px 20px" data-options="closed:'true'">
	        <div class="easyui-layout" data-options="region:'center',border:false,fit:true">
	            <div data-options="region:'north',border:false,title:'查询条件'" style="height:85px;">
	                <form id="search_form" style="margin-left: 20px;vertical-align: middle;">
	                    <label>用户姓名:</label>
	                    <input name="s_cname" type="text" id="s_cname" class="easyui-textbox" style="width: 180px;" />
	                    <label style="margin-left:2px;">人员类型:</label>
               		    <input id="s_userType" class="easyui-combobox" editable="false" name="s_userType" class="easyui-validatebox" />	
	                    <input id="submit_search" type="button" value="查询" class="button"/>
	                    <input id="staff_resetSearch" type="button" value="重置" class="search-btn"/>
	                    <br/>
	                    <label style="display:inline-block;width:80px;">嘉宾通知方式</label>
	                    <input type="checkbox" name="guestInformWays" value="1"/>邮件
	                    <input type="checkbox" name="guestInformWays" value="2"/>短信
	                     &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;
	                    <label style="display:inline-block;width:100px;">指派人员通知方式</label>
	                    <input type="checkbox" name="staffInformWays" value="1"/>邮件
	                    <input type="checkbox" name="staffInformWays" value="2"/>短信
	                </form>
	            </div>
	            <div data-options="region:'center',border:false">
	                <table id="userDg"></table>
	                <div id="dlg-buttons">
        				<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" id="btnAssign" iconCls="icon-add" onclick="user_addFun()">指派</a>
        				<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" iconCls="icon-cancel" onclick="deleteAssignment()">取消</a>
    				</div>
	            </div>
	        </div>
	    </div>
    </div>
     <script type="text/javascript">
     function loadGrid() {
        datagrid = $('#dg').datagrid({
            url: 'assignment/rOne',
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
                param.cname = suserName;
                param.remindFlag = sremindFlag;
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
      
     //设定是否有嘉宾提醒
     function getRemindFlag(value, row, index){
         if (row.remindFlag == 1) {
             return "是";
         } else 
             return "否";
      }

     //指派人员
     function assignUser(value, row, index) {
        return '<a onclick="editAssignUser()" style="cursor:pointer;"><span style="color:blue;">指派</span></a>';
     }

     //嘉宾类型匹配
     function getGuestType(value, row , index) {
          for (var i=0; i<userType.length; i++) {
              if (row.guestType == userType[i].code) {
                  return userType[i].name;
              }
          }
      }
            
     //指派列表用户类型匹配
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
           $.messager.alert('提示', "请选中一位嘉宾再对其指派人员信息！");
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
        $("input[name='guestInformWays'][value='1']").attr("checked",true);
        $("input[name='staffInformWays'][value='1']").attr("checked",true);
        $('#importDlg').window('open').dialog('setTitle','工作人员指派');
        var selectedAssignId = row.assignId;
        userDg=$('#userDg').datagrid({
            url : '<%=basePath%>assignment/getAssignMember/' + row.guestId,
            singleSelect:true,
            rownumbers : true,
            columns:[[
<%--                {field:'ck',checkbox:true},--%>
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
                for (var dex=0; dex<data.rows.length;dex++) {
	                if(data.rows[dex].assignId == selectedAssignId){
					    $(this).datagrid('selectRow',dex);
					    break;
					}
                }
            },
            toolbar : '#dlg-buttons'
        });
    }
     
    //保存选择的指派数据
    function user_addFun() {
       var row = $('#userDg').datagrid('getSelected');
       if (!row) {
          $.messager.alert('提示', "请选中一位工作人员再指派！");
          return;
       }
       $("#btnAssign").linkbutton("disable");
      	var guestInformWays = "";
      	var staffInformWays = "";
      	$('input[name="guestInformWays"]:checked').each(function(){
              guestInformWays += $(this).val() + ",";
        });
      	$('input[name="staffInformWays"]:checked').each(function(){
      	    staffInformWays += $(this).val() + ",";
        });
        guestInformWays = guestInformWays.substring(0, guestInformWays.length - 1);
        staffInformWays = staffInformWays.substring(0, staffInformWays.length - 1);
        
        //$("#btnAssign").attr({"disabled":"true"});
        $.ajax({
             url : 'assignment/saveAssignUser',
             type : "POST",
             data: {'assignUserId':row.assignId,'userType':row.userType,'userName':row.cname,'email':row.email,
            	 	'mobile':row.mobile,'guestInformWays':guestInformWays,'staffInformWays':staffInformWays},
             success : function(msg) {
                 $.messager.alert('提示', msg,'info', function() {
                     $('#userDg').datagrid('clearSelections');
                     $('#importDlg').window("close");
                     $('#dg').datagrid('reload');
                 });
                 $("#btnAssign").linkbutton("enable");
             }
         });
     }
    
    //取消指派
    function deleteAssignment() {
       $.messager.confirm('确认', '您确定要取消该嘉宾的人员指派吗？', function(r) {
          if(r) {
              $.ajax({
                  url : 'assignment/deleteAssignUser',
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
    }
    
    //将嘉宾设置为到场提醒嘉宾
    function setRemind() {
        var row = $('#dg').datagrid('getSelected');
        if (!row) {
            $.messager.alert('提示', "请选中一位嘉宾再设置其嘉宾通知功能！");
            return;
        }
        $.messager.confirm('确认', '您确定将' + row.cname + '设定为需要通知接待的嘉宾吗？', function(r){
            if (r) {
                $.ajax({
                    url : 'assignment/setRemindGuest',
                    type : 'POST',
                    data : {'guestId':row.guestId},
                    success: function(result) {
                        $.messager.alert('提示', "操作成功，已将该嘉宾设定为到场提醒嘉宾。");
                        $('#dg').datagrid('reload');
                    }
                });
            }
        });
    }
    //取消将嘉宾设置为提醒嘉宾
    function removeRemind() {
        var row = $('#dg').datagrid('getSelected');
        if (!row) {
            $.messager.alert('提示', "请选中一位嘉宾再取消其嘉宾通知功能！");
            return;
        }
        $.messager.confirm('确认', '您确定取消' + row.cname + '为到场提醒的嘉宾的设定吗？', function(r){
            if (r) {
                $.ajax({
                    url : 'assignment/removeRemind',
                    type : 'POST',
                    data : {'guestId':row.guestId},
                    success: function(result) {
                        $.messager.alert('提示', "操作成功，已取消该嘉宾为到场提醒嘉宾的设定。");
                        $('#dg').datagrid('reload');
                    }
                });
            }
        });
    }
  </script>
  </body>
</html>
