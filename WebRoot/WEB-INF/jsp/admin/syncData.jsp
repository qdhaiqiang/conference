<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <jsp:include page="../../../include/sys-common.jsp"></jsp:include>
<style type="text/css">
.m-info{
    margin: 0;
    padding: 4px;
    vertical-align: top;
    border: 1px solid #95B8E7;
    -moz-border-radius: 5px 5px 5px 5px;
    -webkit-border-radius: 5px 5px 5px 5px;
    border-radius: 5px 5px 5px 5px;
    
    border-left:0px;
    border-top:0px;
    border-right:0px;
    border-bottom:0px;
    border-bottom-color:Black;
}

h4 {
	margin: 0 0 7px -12px;
	padding: 7px 0 7px 5px;
	font: bold 12px Times, serif;
	color: #891300;
	border-top: 0;
	background:#f5f9fd;
	border-bottom: 1px dotted #dddad9;
}
ul {
  margin:0px;   
  border-style: solid;
  border-width: 0px;
}
#search_button {
    margin-left:500px;
}
.h_prompt {
    margin-left:0px;
    font-weight:bold;
}


</style>
  <script type="text/javascript">
    var datagrid;
    // 选择类型的检索条件的值
    var userType;

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
	    loadGrid();
     });
  </script>

  </head>

  <body>
    <!-- 展示界面Div -->
    <div id="cc" class ="easyui-layout" data-options="region:'center',border:false,fit:true">查询
        <div data-options="region:'center',border:false" id="table_div">
	        <table id="dg" title="人员列表" style="width:100%;height:100%">
		        <thead>
		            <tr>
		                <th data-options="field:'ck',checkbox:true"></th>
		                <th data-options="field:'cname', width:'90'">姓名(中)</th>
		                <th data-options="field:'ename', width:'100'">姓名(英)</th>
		                <th data-options="field:'userType', width:'80', formatter:getUserType">用户类型</th>
		                <th data-options="field:'sex', width:'50', formatter:getSexValue">性别</th>
		                <th data-options="field:'position', width:'100'">职位</th>
		                <th data-options="field:'mobile', width:'120'">联系电话</th>
		                <th data-options="field:'email', width:'150'">邮箱</th>
		                <th data-options="field:'company', width:'150'">公司名称</th>
		            </tr>
		        </thead>
	        </table>
	        <div id="toolbar">
	            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="synch()">手动同步</a>
            </div>
        </div>
	 </div>
     <script type="text/javascript">
    function loadGrid() {
        datagrid = $('#dg').datagrid({
            url: 'userExport/findByCond',
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
            singleSelect : false,
            idField:'userId',
            multiSort:true,
            fit:true,
            nowrap:false,
            toolbar:'#toolbar',
            pagination:true,
            detailFormatter:function(index,row){
                return '<div style="padding:5px"><table id="ddv-' + index + '"></table></div>';
            },
            pagination:true
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
     //用户类型匹配
     function getUserType(value) {
        for (var i=0; i<userType.length; i++) {
            if (value == userType[i].code) {
                return userType[i].name;
            }
        }
     }
     
     /**
                        手动同步数据
     **/
     function synch() {
     	$.ajax({
             url: '<%=basePath%>user/dataSync',
             method:'POST',
             success:function(result) {
             	$.messager.alert("提示",result);
             	loadGrid();
             }
         });
     }
  </script>
  </body>
</html>
