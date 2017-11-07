<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../../../include/sys-common.jsp" />
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html >
<html>
  <head>
	<style type="text/css">
        #ff{
            margin:0;
            padding:10px 30px;
        }
        .ftitle{
            font-size:14px;
            font-weight:bold;
            padding:5px 0;
            margin-bottom:10px;
            border-bottom:1px solid #ccc;
        }
        .fitem{
            margin-bottom:5px;
            padding-bottom:5px;
        }
        .fitem label{
            display:inline-block;
            width:100px;
        }
        .fitem input{
            width:160px;
        }
    </style>

    <!-- 首先需要引入plupload的源代码 -->
    <script src="<%=basePath%>js/plupload.full.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/fileupload.js"></script>
  </head>
  
  <body>
    <div class="panel-header">
    	<div class="panel-title">会议设置</div><div class="panel-tool"></div>
    </div>
    <form id="ff" method="post">
        <div class="fitem">
            <label>会议名称:</label>
            <input name="name" class="easyui-textbox" style="width: 600px;" data-options="required:true">
        </div>
        <div class="fitem">
            <label>会议名称-英文:</label>
            <input name="nameEn" class="easyui-textbox" style="width: 600px;" data-options="required:true">
        </div>
         <div class="fitem">
            <label>开始时间:</label>
            <input id="time1" name="time1" class="easyui-datetimebox" editable="false" data-options="required: true"/>
	        <input type="hidden" id="startTime" name="startTime">
        </div>
        <div class="fitem">
            <label>结束时间:</label>
            <input id="time2" name="time2" class="easyui-datetimebox" editable="false" data-options="required: true">
	        <input type="hidden" id="endTime" name="endTime">
        </div>
        <div class="fitem">
            <label>城市:</label>
            <input id="city" name="city" class="easyui-textbox" style="width: 160px;" data-options="required: true" />
        </div>
        <div class="fitem">
        	<label>会议URL:</label>
        	<input id="url" name="url" class="easyui-textbox" style="width:90px;text-align: right;" data-options="required:true" />.namkwong.org
        </div>
        <div class="fitem">
            <label id="bannerLabel">图片:</label>
            <button id="browse">选择文件</button>
            <input id="bannerPic" name="bannerPic" type="hidden"><!-- 此处不要使用easyUi -->
            <div id="filelist"> </div>
    		<div id="imgfileshow"> </div>
        </div>
        <div class="fitem">
            <label>会议状态:</label>
            <select id="meetingStatus" class="easyui-combobox" editable="false" name="meetingStatus" style="width: 160px;">   
	            <option value="1">准备中</option>
	            <option value="2">开展中</option>
	            <option value="3">已结束</option>
            </select> 
        </div>
        <div class="fitem">
            <label>会议简介:</label>
            <script id="containerCH" name="meetingIntro" type="text/plain" style="margin-top:10px;height:300px;max-height:300px;overflow-y:auto; overflow-x:hidden;">
    		</script>
        </div>
        <div class="fitem">
            <label>会议简介-英文:</label>
            <script id="containerEN" name="meetingIntroEn" type="text/plain" style="height:300px;max-height:300px;overflow-y:auto; overflow-x:hidden;">
    		</script>
    	</div>
    </form>
    
    <div id="dlg-buttons">
        <button id="save-btn" style="margin-left: 200px;" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="saveMeeting()" style="width:90px">保存</button>
    </div>

	<script type="text/javascript">
		$(function() {
			// 格式化日期控件
	    	datetimebox('time1','startTime');
	    	datetimebox('time2','endTime');
	    	
	    	loadmeeting();
			//$('#ff').form('load', "meeting/findById");
			setTimeout(function() {$("#time1").datetimebox('setText',$("#startTime").val());},500);
			setTimeout(function() {$("#time2").datetimebox('setText',$("#endTime").val());},500);
		});
		
		function loadmeeting(){
			$.ajax({
    			url: "<%=basePath%>meeting/findById",
    			type: "GET",
    			success: function(data){
    				$('#ff').form('load', data);
    				uech.setContent(data.meetingIntro);
    				ueen.setContent(data.meetingIntroEn);
    				$("#imgfileshow").html("<img src='<%=basePath%>"+data.bannerPic+"'>");
    			}
  			});
		}
		function saveMeeting() {
			$("#save-btn").attr("disabled","true");
			if(validataSave()){
				$('#ff').form('submit', {
					url : 'meeting/update',
					method : "POST",
					success : function(result) {
						$("#save-btn").removeAttr('disabled');
						$.messager.alert('提示', result);
						$('#ff').form('reload'); // reload the meeting data
					}
				});
			}else{
				alert("起止时间设置错误");
			}
			
		}
		
		function validataSave(){
	    	var start = $("#startTime").val();
	    	var end = $("#endTime").val();
	    	if(end.localeCompare(start) == 1){
	    		return true;
	    	}
	    	return false;
	    }
		
	   
	   var fileuploadurl ="<%=basePath%>fileload/c/upload";
	   //browse bannerPic filelist imgfileshow
	   //上传图片的附加参数,sign需要修改成会议的唯一标识
		var multipart_params = {
			optiontype:"metting",
			sign:'${sessionScope.meetingId}',
			filecategory:'banner'
		};
	   initUploader("browse","filelist","imgfileshow","bannerPic",fileuploadurl,false,1000,300,true,multipart_params);
	   
	//实例化编辑器
	var uech = UE.getEditor('containerCH');
	var ueen = UE.getEditor('containerEN');
		 
	</script>
</body>
</html>
