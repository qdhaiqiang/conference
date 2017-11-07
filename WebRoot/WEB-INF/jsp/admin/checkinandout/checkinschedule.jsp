<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
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
<script type="text/javascript">
	$(document).ready(function(){
		//var schedileList = $.session("scheduleList");
		//alert("12333");
		//alert($.session("scheduleList"));
		//$("#gointo").bind('click',function(){
			//alert("11111");
		//	alert($("#scheduleId").val());
			//location.href = "<%=basePath%>r/admin/checkinandout/checkin";
		
	 	//});	
	});
	
	function gointocheckin(scheduleId){
		//alert(scheduleId);
		//location.href = "<%=basePath%>r/admin/checkinandout/checkin";
		$.ajax({
			url : "<%=basePath%>checkinuser/checkinSelectSchedule/"+scheduleId,
			type : "get",
			success : function(data) {
				if(data == "success"){
					location.href = "<%=basePath%>r/admin/checkinandout/checkin";
				}

			}
		});
	}
	
	function gointoview(scheduleId){
		//alert(scheduleId);
		//location.href = "<%=basePath%>r/admin/checkinandout/checkin";
		$.ajax({
			url : "<%=basePath%>checkinuser/checkinSelectSchedule/"+scheduleId,
			type : "get",
			success : function(data) {
				if(data == "success"){
					location.href = "<%=basePath%>curcheckinuser/r/audiencelist";
				}

			}
		});
	}
	
</script>
  </head>
  
  <body>
    <div class="checkstyle">
     	<div class="tl">
			<img src='<%=basePath%>images/reg_banner.jpg' width="1024" />
		</div>
    	<h3>
    	请从下面的列表中选择你要进行签到的会场：
    	</h3>
    		
    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
    		<c:forEach var="schedule" items="${scheduleList}" >
    			
    		 	<tr class="svList2">
                	<td><label>${schedule.title}</label></td>
                	<td>
                  		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="gointocheckin('${schedule.id}')">进入签入</a>
                  		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="gointoview('${schedule.id}')">会议墙实时显示详细信息</a>
                	</td>
              	</tr>
    		</c:forEach>
    	</table>
    </div>
    <jsp:include page="../../front/footer.jsp"></jsp:include>
  </body>
</html>
