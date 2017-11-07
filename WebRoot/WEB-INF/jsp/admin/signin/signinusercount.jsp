<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="../../../../include/sys-common.jsp" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="<%=basePath%>js/plugins/highcharts/highcharts.js"></script>
<script type="text/javascript">
		var datagrid;
		$(document).ready(function(){  
			count();
			loadGrid();
			
			//获取用户类型数据
	         $.ajax({
	             url: 'dict/r/user_type',
	             method:'get',
	             async:false,
	             success:function(data) {
	                 userType = data;
	             }
	         });
			
	         $("#submit_search").bind('click',function(){
		    		datagrid.datagrid({
	                    pageNumber : 1
	                });
		    		$("#username").val("");
		    	}); 
	         $("#user_resetSearch").bind('click',function(){
	             $("#username").val("");
	         });
	         $("#refresh").bind('click',function(){
	        	 window.location.reload();
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
	        		param.username=$("#username").val();
	        		
	        	}
	    	});
	    	$('#dg').datagrid('getPager').pagination({
				showPageList:true,
				beforePageText:"",
				afterPageText:"",
				displayMsg:""
			});
		}
		
		//查询报到此会议的人员总数
		function count(){
			$.ajax({
				url : "<%=basePath%>signinuser/count",
				type : "get",
				success : function(data) {
					$("#count").text(data.countSignedUser);
					showPieChart(data.countSignedUser,data.countMeetingUser);
				}
			});
		}
		
		
		function showCname(value,row,index){
			return new Object(row["confUser"]).cname;
		} 
		function showEname(value,row,index){
			return new Object(row["confUser"]).ename;
		} 
		
		function showmealTicket(value, row, index){
            if (row.mailTicket == 1) {
                return "是";
            } else if (row.mailTicket == 0) {
                return "否";
            }
        }
		function showGift(value, row, index){
            if (row.gift == 1) {
                return "是";
            } else if (row.gift == 0) {
                return "否";
            }
        }
		function showIfdocument(value, row, index){
            if (row.ifdocument == 1) {
                return "是";
            } else if (row.ifdocument == 0) {
                return "否";
            }
        }
		function showIfreceipt(value, row, index){
            if (row.ifreceipt == 1) {
                return "是";
            } else if (row.ifreceipt == 0) {
                return "否";
            }
        }
		
		function showUserType(value, row, index){
			for (var i=0; i<userType.length; i++) {
	            if (row.userType == userType[i].code) {
	                return userType[i].name;
	            }
	        }
		}
		
		function showPieChart(signinCount,sumCount){

			 var percentsigned = Math.round(signinCount * 100 * 100 / (100 * (sumCount)));
			 var percentnosigned = 100 - parseInt(percentsigned);

			 $('#container').highcharts({
			        chart: {
			            plotBackgroundColor: null,
			            plotBorderWidth: 1,//null,
			            plotShadow: false
			        },
			        title: {
			            text: '报到/未报到比例图'
			        },
			        credits:{enabled:false},
			        tooltip: {
			            pointFormat: '{point.name}: <b>{point.percentage:.1f}%</b>'
			        },
			        plotOptions: {
			            pie: {
			                allowPointSelect: true,
			                cursor: 'pointer',
			                dataLabels: {
			                    enabled: true,
			                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
			                    style: {
			                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
			                    }
			                }
			            }
			        },
			        series: [{
			            type: 'pie',
			            
			            data: [
			                {
			                    name: '已报到',
			                    y: percentsigned,
			                    sliced: true,
			                    selected: true
			                },
			                ['未报到', percentnosigned],
			            ]
			        }],
			        colors: [
                             'green', 
                             'red'  
                          ]
			    });
		}
	</script>
  </head>
  
  <body>
  	<table style="width:100%;height:100%;margin-bottom: 10px;">
  		<tr>
  			<td style="width:50%">
				<table id="dg" title="签到人员显示"
						data-options="striped:true,rownumbers:true,singleSelect:true,url:'signinuser/selectSignUser',method:'post', 
       				 		multiSort:true,fit:true,nowrap:false,toolbar:'#toolbar',pagination:'true'">
					<thead>
						<tr>
						    <th data-options="field:'ck',checkbox:true"></th>
							<th data-options="field: 'cname',formatter:showCname">中文姓名</th>
							<th data-options="field: 'ename',formatter:showEname">英文姓名</th>
							<th data-options="field: 'userType',formatter:showUserType">用户类型</th>
							<th data-options="field: 'mailTicket',formatter:showmealTicket">餐券</th>
							<th data-options="field: 'gift', formatter:showGift">礼品</th>
							<th data-options="field: 'ifdocument', formatter:showIfdocument">证件</th>
							<th data-options="field: 'ifreceipt', formatter:showIfreceipt">报销</th>
							<th data-options="field: 'restaurant', width:'50'">酒店</th>
							<th data-options="field: 'roomNum', width:'50'">房间号</th>
							<th data-options="field: 'postscript', width:'100'">特殊备注</th>
						</tr>
					</thead>
				</table>
				<div id="toolbar">
        			<a>报到总人数:</a>
        			<a id="count" name="count"></a>
        			<form id="search_form" style="margin-left: 0px;vertical-align: middle;">
						<label>姓名:</label>
		        		<input name="username" type="text" id="username" class="easyui-validatebox" />
                		<input id="submit_search" type="button" value="查询" class="search-btn"/>
                		<input id="user_resetSearch" type="button" value="重置" class="search-btn"/>
                		<input id="refresh" type="button" value="刷新" class="search-btn"/>
					</form>
    			</div>
			</td>
    		<td style="width:50%">
    			<div>
    				<div id="container" style="min-width: 50%; margin: 0 auto"></div>
    			</div>
    		</td>
    	</tr>
    </table>
  </body>
</html>
