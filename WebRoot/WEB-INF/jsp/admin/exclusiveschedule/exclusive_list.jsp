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
    // 获取data
    var suserType = "";
    var suserName = "";
    var userDg;
     $(function(){
        //获取用户类型数据
         $.ajax({
             url: 'dict/r/user_type',
             method:'get',
             async:false,
             success:function(data) {
            	 for(var i=0;i<data.length;i++){
            		 index = i;
            		 //if:data[i].name.indexOf("A-1") || data[i].name.indexOf("B-1")
            		 if(data[i].code == "7" || data[i].code == "8" || data[i].code == "9" ||  data[i].code == "18" || data[i].code == "19" || data[i].code == "20"){
            			 data.splice(i,1);//参数：第一个是删除的起始索引(从0算起),第二个是删除元素的个数
            			 i = i-1;
            		 }
            	 }
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
                width:700,   
                height:430,   
                resizable:false
        });
        $('#importDlg').window("close");
     });

    $(document).ready(function(){
        loadGrid();
        $("#submit_search").bind('click',function(){
            userDg.datagrid({
                queryParams:{
                    email:$("#s_mail").val(),
                    cname:$("#s_cname").val()
                }
            });
        });
        $("#user_submitSearch").bind('click',function(){
            if (!$("#select").form('validate')) {
                return false;
            }else {
                suserType = $("#suserType").combobox("getValues");
                suserName = $("#sname").val().replace(" ","");
                datagrid.datagrid({
                    pageNumber : 1
                });
            }
        });
        $("#user_resetSearch").bind('click',function(){
            $("#suserType").combobox('setValue',"");
            $("#sname").val("");
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
                <input id="user_submitSearch" type="button" value="查询" class="search-btn"/>
                <input id="user_resetSearch" type="button" value="重置" class="search-btn"/>
            </form>
        </div>
        <!--用户列表 -->
        <div data-options="region:'center',border:false">
            <table id="dg" title="专属日程" style="width:100%;height:100%">
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
                        <th data-options="field:'assignName', width:'100'">指派人员姓名</th>
                        <th data-options="field:'assignMobile', width:'100'">指派人员电话</th>
                        <th data-options="field:'assignEmail', width:'180'">指派人员邮箱</th>
                        <th data-options="field:'approve', width:'80', formatter:detail">操作</th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
     <script type="text/javascript">
     function loadGrid() {
        datagrid = $('#dg').datagrid({
            url: 'exclusiveschedule/r',
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

     //查看详细
     function detail(value, row, index) {
        return '<a onclick="guestDetail()" style="cursor:pointer;"><span style="color:blue;">查看详细</span></a>';
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
    function guestDetail() {
       var row = $('#dg').datagrid('getSelected');
       if (!row) {
           $.messager.alert('提示', "请选中一位嘉宾再查看详细信息！");
           return;
       }
       var url = "exclusiveschedule/getExculScheInfo/" + row.guestId;
       window.open(url+'?openwin=true', "专属日程嘉宾详细信息","fullScreen=yes,toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no,status=no");
    }
  </script>
  </body>
</html>
