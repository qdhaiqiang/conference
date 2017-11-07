<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<jsp:include page="../../../../include/sys-common.jsp" />
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="<%=basePath%>js/plugins/highcharts/highcharts.js"></script> 
    <script src="<%=basePath%>js/plugins/highcharts/exporting.js"></script>   
    <script type="text/javascript">
    var schduleList = new Array();
   	$(document).ready(function(){
   		loadSchduleList();
   		setSchduelSearch();
   	});
	</script>
	   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
<style type="text/css">
label,p {
	padding: 0;
	margin: 0;
	list-style: none;
	font-size: 15px;
	line-height: 30px;
}       
table{
	text-align: left;
	font-size: 15px;
	border-collapse: collapse;
    border: solid #000 1px;
}
tr{
	border:1px #c5c5c5 dotted;
}
.search{
	padding: 20px 14px 15px;
}
.qd {
	width: 1110px;
	padding: 15px 20px 30px;
	margin: 0 auto;
	border: 1px solid #ccc;
	height: 430px;
	font-size: 20px;
	font-family: "Microsoft YaHei", "宋体", "Georgia", "Times New Roman",
		"Times", "serif";
}

.query {
	width: 300px;
	height: 400px;
	float: left;
	border-right: 1px dotted #c5c5c5;
	text-align: left;
}

.user {
	width: 800px;
	float: right;
	height: 400px;
	text-align: center;
}

.tdLabel{
	width: 200px;
	word-break:break-all;
	border-right: 1px dotted #c5c5c5;
	padding-right:5px;
}
</style>
  </head>  
  <body>
    <div>
		<form id="select" class="search">
			<label>选择会场:</label> 
			<input id="findBySchduelId" name="scheduleId" class="easyui-combobox" required="true" editable="false" validType="searchParm" />			
			<a onclick="findVotesBySchduelId()" class="easyui-linkbutton" iconCls="icon-ok">查询</a>
		</form>
	</div>
	<div id="tables">
		<!-- 需要调页面美化时候，需要此处代码 
		<div  class="qd">
			<div class="query">
				<p>投票问题：aaaaaaaaa</p>
				<p>问题描述：bbbbbbbb</p>
				<p>统计结果：</p>
				<table>
				<tr>
					<th>选项</th>
					<th>投票人数</th>
				</tr>
				<tr>
					<td>aaaaa</td>
					<td>16</td>
				</tr>
				<tr>
					<td>bbb</td>
					<td>68</td>
				</tr>
				<tr>
					<td>ccc</td>
					<td>37</td>
				</tr>
			</table>
			</div>
			<div class="user">
				
			</div>
		</div>
		-->
	</div>
    
    <script type="text/javascript">
   	function loadSchduleList(){
    	$.ajax({
            url: 'schedule/findByMeetingId',
            method:'get',
            async:false,
            success:function(data) {
            	schduleList = data;
            }
        });
   	}
   	
  	//根据加载来的schduleList，将其放在日程查询的条件中
    function setSchduelSearch(){
    	$("#findBySchduelId").combobox({
            data: schduleList,//json格式的数据
            valueField:'id',
            textField:'title'
        });
  	}
  	
  	function findVotesBySchduelId(){
  		if (!$("#select").form('validate')) {
			return false;
		}else{
			var schduelId = $("#findBySchduelId").combobox('getValue');
			$.ajax({
	            url: 'confVote/findVotesBySchduelId/'+schduelId,
	            method : "GET",
				success : function(result) {
					showVotesResult(result);
				}
	        });
		}
  	}
  	
  	function showVotesResult(result){
  		$("#tables").html("");
  		var resultdatas =  JSON.parse(result);
  		if(resultdatas.length > 0){
  			for(var i in resultdatas){
  				var htmlValue = '';
  				if(resultdatas[i].type != "text"){
  					htmlValue += '<div class="qd"><div class="query"><p>投票问题：'+
						resultdatas[i].name+'</p><p>问题说明：'+
						resultdatas[i].description+'</p><p>统计结果：</p>';
  					var options = eval(resultdatas[i].options);
  					htmlValue += '<table><tr><th>选项</th><th>投票人数</th></tr>';
  					var labels = new Array();
  					var nums = new Array();
  					for(var j in options){
  						labels[j] = options[j].label;
  						nums[j] = Number(options[j].num);
  						htmlValue += '<tr><td class="tdLabel">';
  						htmlValue += options[j].label;
  						htmlValue += '</td>';
  						htmlValue += '<td>';
  						htmlValue += options[j].num;
  						htmlValue += '</td>';
  					}
  	  				htmlValue += '</table></div><div class="user" id="chartShow'+i+'"></div></div>';
  	  				//htmlValue += '</table></div></div>';
  	  				$("#tables").append(htmlValue);
  	  				showHighcharts("#chartShow"+i,labels,nums,resultdatas[i].name);//div categories data
  				}			
  			}
  		}
  	}
  	
  	function showHighcharts(div,categories,data,title){
  		    $(div).highcharts({
  		        chart: {
  		            type: 'column',
  		            margin: [ 50, 50, 100, 80]
  		        },
  		        credits: {  
                  enabled: false  
                },
  		        title: {
  		            text: title+'-投票统计'
  		        },
  		        xAxis: {
  		            categories: categories,
  		            labels: {
  		                rotation: -30,
  		                align: 'right',
  		                style: {
  		                    fontSize: '13px',
  		                    fontFamily: 'Verdana, sans-serif'
  		                }
  		            }
  		        },
  		        yAxis: {
  		            min: 0,
  		            title: {
  		                text: '投票人数'
  		            }
  		        },
  		        legend: {
  		            enabled: false
  		        },
  		        tooltip: {
  		            pointFormat: '投票人数: <b>{point.y} 人</b>',
  		        },
  		        series: [{
  		            name: 'Population',
  		            data: data,
  		            dataLabels: {
  		                enabled: true,
  		                rotation: -90,
  		                color: '#FFFFFF',
  		                align: 'right',
  		                x: 4,
  		                y: 10,
  		                style: {
  		                    fontSize: '13px',
  		                    fontFamily: 'Verdana, sans-serif',
  		                    textShadow: '0 0 3px black'
  		                }
  		            }
  		        }]
  		    });
  	}
	</script>
  </body>
</html>
