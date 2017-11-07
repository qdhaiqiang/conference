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
    var scheduleId = "${ scheduleId}";
    $(document).ready(function(){
         $.ajax({
            url: 'confVote/findVotesBySchduelId/' + scheduleId,
            method:'get',
            async:false,
            success:function(result) {
               showVotesResult(result);
            }
        });
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
body{
	margin:0 auto;
    background:#fff;
}
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
    width: 90%;
/*     float: right; */
    height: 400px;
    text-align: center;
    margin-left:50px;
}

.tdLabel{
    width: 200px;
    word-break:break-all;
    border-right: 1px dotted #c5c5c5;
    padding-right:5px;
}
#tables {
    width:1024px;
    margin:0 auto;
	background:#F4F2E5;
}
.tl {
    TEXT-ALIGN: center;
}
.tl h2{
	font-weight:bold;
	font-size: 24px; 
	color:#fff;
	padding:10px;
	text-align:left;
	margin:10px 0 20px 0px;
	background: #F29409;
}
#QRimg img{
   float:right;
   margin-right:20px;
}
</style>
  </head>  
  <body>
  <div style="height:768px;background:#F4F2E5;width:1024px;margin:0 auto;">
      <div class="tl">
         <img src='<%=basePath%>images/reg_banner.jpg' style="width:1024px;" />
         <h2>现场投票结果</h2>
      </div>
    <div id="tables"></div>
    <%-- <div id="QRimg" style="width:100%;height:150px;margin-top:0px;padding-bottom:20px;background: #F4F2E5;">
        <img id="img" width="150px" alt="" src="<%=basePath%>images/meeting_wall/phone_QR.jpg">
      </div> --%>
    </div>
    <script type="text/javascript">
    function showVotesResult(result){
        $("#tables").html("");
        var resultdatas = JSON.parse(result);
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
                    //htmlValue += '</table></div><div class="user" id="chartShow'+i+'"></div></div>';
                    htmlValue = '<div class="user" id="chartShow'+i+'"></div>';
                    //htmlValue += '</table></div></div>';
                    $("#tables").append(htmlValue);
                    showHighcharts("#chartShow"+i,labels,nums,resultdatas[i].name);
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
                    text: title
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
