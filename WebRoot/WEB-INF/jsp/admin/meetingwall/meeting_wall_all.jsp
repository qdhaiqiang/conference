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
    <script type="text/javascript" src="js/leftmenu.js"></script>
<style type="text/css">
#menu_btn {display:none} 
.image_btn {
    height:60%;
    width:20%;
    margin-top:150px;
    margin-left:100px;
}
#condition {
    margin-top:20px;
    margin-left:40px;
}
</style>
  </head>
  
  <body>
    <form id="select">
        <div id="condition">
            <label>会场:</label>
            <input id="schedule" class="easyui-combobox" editable="false" name="schedule"
                data-options="valueField:'id',textField:'title', required:'true',
                url:'schedule/findByMeetingId',method:'get'" />
            <input id="search" type="button" value="选择" class="search-btn" /> <input id="reset"
                type="button" value="重置" class="search-btn" />
        </div>
        <div id="menu_btn">
            <img src='images/meeting_wall/question.jpg' alt="现场提问" class="image_btn" onclick="goQuestionPage()"/>
            <img src='images/meeting_wall/vote.jpg' alt="现场投票" class="image_btn" onclick="goVoteResultPage()"/>
            <img src='images/meeting_wall/guest_arrive.jpg' alt="嘉宾到场" class="image_btn" onclick="goGuestPage()"/>
        </div>
        <input type="hidden" name="selectedSch" id="selectedSch">
    </form>
    
    <script type="text/javascript">
        var scheduleId="";
        //点击选择
        $("#search").click(function(){
            scheduleId = $("#schedule").combobox("getValue");
            if (scheduleId == "") {
                $.messager.alert('提示', "请先选择一个日程");
                return;
            }
            $("selectedSch").val(scheduleId);
            $("#menu_btn").show();
        });
        
        //展示现场提问
        function goQuestionPage() {
            scheduleId = $("#schedule").combobox("getValue");
            var url = 'question/showMeetingWallQ/' + scheduleId;
            window.open(url+'?openwin=true', "现场提问问题","width=1350,height=650,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no,top=0,left=0");
        }
        
        //展示投票结果
        function goVoteResultPage() {
            scheduleId = $("#schedule").combobox("getValue");
            var voteCount;
	        $.ajax({
	            url: 'confVote/findVotesCountBySchduelId/' + scheduleId,
	            method:'get',
	            async:false,
	            success:function(result) {
	               voteCount = result;
	            }
	        });
	        if (voteCount == "0") {
	           $.messager.alert('提示', "您还没有创建该日程的投票，请先去现场投票菜单里创建一个可显示的投票结果。");
	           return;
	        }
            var url = 'confVote/showVotesResult/' + scheduleId;
            window.open(url+'?openwin=true', "投票结果","height=650,width=1350,toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no,status=no,top=0,left=0");
        }
        
        //展示演讲嘉宾结果
        function goGuestPage() {
            scheduleId = $("#schedule").combobox("getValue");
            var count;
            $.ajax({
                url: 'user/findSpeakerCountBySchduelId/' + scheduleId,
                method:'get',
                async:false,
                success:function(result) {
                   count = result;
                }
            });
            if (count == "0") {
               $.messager.alert('提示', "该会场没有演讲嘉宾，无法投放。");
               return;
            }
            var url = 'user/showSpeaker/' + scheduleId;
            window.open(url+'?openwin=true', "演讲嘉宾","height=650,width=1350,toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no,status=no,top=0,left=0");
        }
        
        //清除表单
        $("#reset").click(function(){
             $("#schedule").combobox("clear");
             $("#menu_btn").hide();
        });
    </script>    
  </body>
</html>
