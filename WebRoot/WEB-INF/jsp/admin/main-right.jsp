<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
	<style type="text/css">
	#win {display:none}
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
	   }
	   .fitem label{
	       display:inline-block;
	       width:80px;
	   }
	   .fitem input{
	       width:160px;
	   }
	</style>
</head>
<body>

<!-- 首先需要引入plupload的源代码 -->
    <script src="<%=basePath%>js/plupload.full.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/fileupload.js"></script>
<script type="text/javascript">

/**
初始化
**/
$(document).ready(function(){
	$('#win').window({
		title:'新建会议',
		width:800,
		height:600,   
		resizable:false  
	});
	$('#win').dialog('close');
	// 判断用户是否为管理员
	$.ajax({
  		url: 'confSysUser/checkAdmin',
  		type: 'post',
  		success: function(data) {
  			if (!data) {
  				$("#toolbar").hide();
  			}
  		}
  	});	
	loadGrid();
}); 

/**
加载未结束会议列表
**/
function loadGrid()  {
	$('#dg').datagrid({
		nowrap:false,
		loadMsg:'加载中，请稍候...',
		pagination : true,//页码
		pageNumber : 1,//初始页码
		pageSize : 15,
		pageList : [15,30,45,60],
		fitColumns:true,
		detailFormatter:function(index,row){
			return '<div style="padding:5px"><table id="ddv-' + index + '"></table></div>';
		},
		pagination:true,
	});
}
    /**
       	创建会议
    **/
    function newMeeting(){
    	$('#win').show();
        $('#win').window('open');       
        $('#ff').form('clear');
        createfileUpload();
       	$.parser.parse();//easyui-所有控件初始化的入口
    }
    
    function createfileUpload(){
    	removefileUpload();
    	var filediv = '<div id="fileUploadDiv">';
    	filediv += '<button id="browse">选择文件</button>';
    	filediv += '<div id="filelist"> </div>';
    	filediv += '<div id="imgfileshow"> </div>';
    	filediv += '</div>';
    	$("#bannerPic").after(filediv);
    	
    	var fileuploadurl ="<%=basePath%>fileload/c/upload";
	   //browse bannerPic filelist imgfileshow为上传图片及预览所需控件id
	   //上传图片的附加参数,sign需要修改成会议的唯一标识
		var multipart_params = {
			optiontype:"metting",
			sign:'meetingidOrcode',
			filecategory:'banner'
		};
	   initUploader("browse","filelist","imgfileshow","bannerPic",fileuploadurl,false,1000,300,true,multipart_params);
    }
    
    function removefileUpload(){
    	$("#fileUploadDiv").remove();
    }
        
    /**
       	保存会议
    **/
    function saveMeeting(){
    	if(validataSave()){
    		$('#ff').form('submit',{
                url: 'meeting/add',
                method:"POST",
                success: function(result){
                    $.messager.alert('提示',result);
                    if (result.errorMsg){
                        $.messager.show({
                            title: 'Error',
                            msg: result.errorMsg
                        });
                    } else {
                        $('#win').dialog('close');        // close the dialog
                        loadGrid();    // reload the meeting data
                    }
                }
            });
    	}else{
    		$.messager.alert('提示','起止时间设置错误');
    	}
    }
    
    function validataSave(){
    	var start = $("#mettingstarttime").datebox('getValue');
    	var end = $("#mettingendtime").datebox('getValue');
    	
    	if(end.localeCompare(start) == 1){
    		return true;
    	}
    	return false;
    }
    
    /**
       	 初始化会议
    **/
    function clearForm() {
        $('#ff').form('clear');//此处用form'clear'方法，重置之后图片选不上；将上传图片的元素移出form，就没有这个问题。待解决\
        createfileUpload();
    }
    
    
	   /**
	                   点击会议名称进入会议主界面
	   **/
	   function showConfDetail(value,row,index) {
		   return '<a onclick="goBackMain(\'' + row.id+ '\')" style="cursor:pointer;">' + value +'</a>';
	   }
	   
	   /**
	                  跳转
	   **/
	   function goBackMain(meetingId) {
	   	   $.ajax({
	   		   url: '<%=basePath%>confSysUser/goToBackMain',
	   		   type: 'post',
	   		   data: 'meetingId='+meetingId,
	   		   success: function(data) {
			       window.location.href='<%=basePath%>r/admin/index';
	   		   }
	   	   });
	   }
	   
	 //实例化编辑器
	 var uech = UE.getEditor('containerCH');
	 var ueen = UE.getEditor('containerEN');
</script>

	<!-- 会议列表 -->
	<table id="dg" title="未结束会议列表" style="width:100%;height:100%"
	       data-options="striped:true,rownumbers:true,singleSelect:true,url:'meeting/r',method:'get', 
	       				 multiSort:true,fit:true,nowrap:false,toolbar:'#toolbar',pagination:'true'">
		<thead>
	        <tr>
	            <th data-options="field:'ck',checkbox:true"></th>
	            <th data-options="field:'id',width:'50',hidden:'true'">会议编号</th>
	            <th data-options="field:'name','width':'500',formatter:showConfDetail">会议名称</th>
	            <th data-options="field:'startTime',width:'180'">开始时间</th>
	            <th data-options="field:'endTime',width:'180'">结束时间</th>
	            <th data-options="field:'city',width:'80'">举办城市</th>
	            <th data-options="field:'num',width:'80'">报名人数</th>
	        </tr>
	    </thead>
	</table>
	    
	<!-- 工具栏  -->
	<div id="toolbar">
	    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:'true'" onclick="newMeeting()">新建会议</a>
	</div>
	    
	<div id="win" class="easyui-window" data-options="iconCls:'icon-save',modal:true">
		<div class="easyui-panel">
			<div align="left">
				<form id="ff" method="post" style="margin:10px 0 10px 10px;">
					<div class="fitem" style="margin-top:10px;margin-bottom:5px;">
	                	<label>会议名称:</label>
	                	<input name="name"  class="easyui-validatebox" style="width: 600px;"  required="required">
	            	</div>
					<div class="fitem" style="margin-top:10px;margin-bottom:5px;">
	                	<label>会议名称-英文:</label>
	                	<input name="nameEn" class="easyui-validatebox" style="width: 570px;" required="required">
	            	</div>
		            <div class="fitem" style="margin-top:10px;margin-bottom:5px;">
		                <label>开始时间:</label>
		                <input id="mettingstarttime" name="startTime" id = "startTime" class="easyui-datetimebox" required="required" editable="false">
		            </div>
		            <div class="fitem" style="margin-top:10px;margin-bottom:5px;">
		                <label>结束时间:</label>
		                <input id="mettingendtime" name="endTime" id = "endTime" class="easyui-datetimebox" required="required" editable="false">
		            </div>
		            <div class="fitem" style="margin-top:10px;margin-bottom:5px;">
		                <label>城市:</label>
		                <input type="text" id="city" name="city" class="easyui-validatebox">
		            </div>
		            <div class="fitem" style="margin-top:10px;margin-bottom:5px;">
		                <label>图片:</label>
		                <input name="bannerPic" id="bannerPic" type="hidden"> <!--最后要加上 type="hidden" -->
		                <div id="fileUploadDiv"></div>
		            </div>
		            <div class="fitem" style="margin-top:10px;margin-bottom:5px;">
		                <label>会议状态:</label>
		                <select id="cc" class="easyui-combobox" editable="false" name="meetingStatus">   
		                <option value="1">准备中</option>   
		                <option value="2">开展中</option>   
		                <option value="3">已结束</option>   
		                </select> 
		            </div>
		            <div class="fitem" style="margin-top:10px;margin-bottom:5px;">
		                <label>会议简介:</label>
		                <script id="containerCH" name="meetingIntro" type="text/plain" style="height:50%;max-height:50%;overflow-y:auto; overflow-x:hidden;">
    							</script>
		            </div>
		            <div class="fitem" style="margin-top:10px;margin-bottom:5px;">
		                <label>会议简介-英文:</label>
		                 <script id="containerEN" name="meetingIntroEn" type="text/plain" style="height:50%;max-height:50%;overflow-y:auto; overflow-x:hidden;">
    							</script>
    							
		            </div>
				</form>
	    				
				<div style="text-align:center;padding:5px;">
					<a href="javascript:void(0)" id="add" class="easyui-linkbutton" onclick="saveMeeting()" data-options="iconCls:'icon-ok'">保存</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" onclick="clearForm()">重置</a>
				</div>
			</div>
		</div>
	</div>
    
</body>
</html>