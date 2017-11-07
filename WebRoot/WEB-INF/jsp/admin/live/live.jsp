<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../../../include/sys-common.jsp" />
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html >
<html>
  <head>
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
		.app-top-td{
			width:450px;
		}
    </style>
    
     <!-- 首先需要引入plupload的源代码 -->
    <script src="<%=basePath%>js/plupload.full.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/fileupload.js"></script>
  	<script type="text/javascript">
  		var datagrid;
    	// 选择类型的检索条件的值
	    var userType;
	    var userTypeName;
	    var userTypeId;
	    var suserType = "";
	    // 获取data
	    var userDg;
	    var dataList;//指派人员数据源
	    

        var fileuploadurl ="<%=basePath%>fileload/c/upload";
	   //browse bannerPic filelist imgfileshow
	   //上传图片的附加参数,sign需要修改成会议的唯一标识
		var location_params = {
			optiontype:"live",
			sign:"live",
			filecategory:'location'
		};
		var field_pattern_params = {
				optiontype:"live",
				sign:"live",
				filecategory:'fieldPattern',
		};
		var uploader1 = "";
		var uploader2 = "";
	    
		$(document).ready(function(){
	    	loadGrid(); 
	    	//初始化页面的数据，控件和事件等
	    	initLiveElements();
	    });
		
		function loadGrid()  {
	    	$('#dg').datagrid({
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
	    	});
		}
	</script>
  </head>
  
  <body>
	<table id="dg" title="现场管理" style="width:100%;height:100%"
			data-options="striped:true,rownumbers:true,singleSelect:true,url:'schedule/r',method:'get', 
       				 multiSort:true,fit:true,nowrap:false,toolbar:'#toolbar',pagination:'true'">
		<thead>
			<tr>
			    <th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field: 'id', hidden:'true'">日程编号</th>
				<th data-options="field: 'scheduleId', hidden:'true'"></th>
				<th data-options="field: 'meetingId', hidden:'true'">会议编号</th>
				<th data-options="field: 'title', width:'200'">活动名称</th>
				<th data-options="field: 'startTime', width:'100'">开始时间</th>
				<th data-options="field: 'endTime', width:'100'">结束时间</th>
				<th data-options="field: 'location', width:'50'">地点</th>
				<th data-options="field: 'liveSize', width:'50'">规模</th>
				<th data-options="field:'action', width:'10%', formatter:preview">操作</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:'true'" onclick="editLive()">编辑</a>
    </div>
    <div id="dlg" class="easyui-dialog"	style="width:700px;height:400px;padding:20px 40px" closed="true" maximizable="true" resizable="true" buttons="#dlg-buttons">
	      <div data-options="region:'center'">
	        <form id="ff" method="post">
	        	<div class="fitem">
	        		<input id="id" name="id" type="hidden">
	        		<input id="meetingId" name="meetingId" type="hidden">
	        		<input id="titleEn" name="titleEn" type="hidden">
	        		<input id="intro" name="intro" type="hidden">
	        		<input id="intrEn" name="introEn" type="hidden">
	        		<input id="mediaUrl" name="mediaUrl" type="hidden">
	        		<input id="restrictAudience" name="restrictAudience" type="hidden">
	        		<input id="currentAudience" name="currentAudience" type="hidden">
	        		<input id="maxAudience" name="maxAudience" type="hidden">
	        	</div>
	            <div class="fitem">
	                <label>活动名称:</label>
	                <input id="title" name="title" class="easyui-validatebox" data-options="required: true" style="width: 250px;"/>
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
	            	<label>地点:</label>
	            	<input id="location" name="location" class="easyui-validatebox" data-options="required: true" style="width: 250px;"/>
	            </div>
	            <div class="fitem">
	            	<label>规模:</label>
	            	<input id="liveSize" name="liveSize" class="easyui-validatebox" data-options="required: true" style="width: 250px;"/>
	            </div>
	            <div class="fitem">
	            	<label>会场整体平面图标识该活动所在位置:</label>
	            	<a id="browse1" style="cursor: pointer;">[选择文件]</a>
		            <input id="locationUrl" name="locationUrl" type="hidden"><!-- 此处不要使用easyUi -->
		            <div id="filelist1"> </div>
		    		<div id="imgfileshow1"></div>
	            </div>
	            <div class="fitem">
		        	<label>场型图及各区域位置:</label>
		        	<a id="browse2" style="cursor: pointer;">[选择文件]</a>
		            <input id="fieldPatternUrl" name="fieldPatternUrl" type="hidden"><!-- 此处不要使用easyUi -->
		            <div id="filelist2"> </div>
		    		<div id="imgfileshow2"></div>
		        </div>
		        <div class="fitem">
	            	<label>活动简单流程:</label>
	            	<textarea name="liveProcedure" rows="5" cols="45"></textarea>
	            </div>
		        <div class="fitem">
		            <label>现场名签排位(演讲区):</label>
		            <input id="speechSeatId" name="speechSeatId" type="hidden" readonly="true"/>
		            <input id="speechSeatName" name="speechSeatName" readonly="true"/>
		            <button type="button" onclick="openDlg('speechSeat')" style="cursor: pointer;">选择</button>
		        </div>
		        <div class="fitem">
		            <label>现场名签排位(VIP区):</label>
		            <input id="vipSeatId" name="vipSeatId" type="hidden" readonly="true"/>
		            <input id="vipSeatName" name="vipSeatName" readonly="true"/>
		            <button type="button" onclick="openDlg('vipSeat')" style="cursor: pointer;">选择</button>
		        </div>
		        <div class="fitem">
		            <label>现场音频视频文件说明:</label>
		            <textarea name="audioVideo" rows="5" cols="45"></textarea>
		        </div>
		        <div class="fitem">
		            <label>VIP嘉宾名单:</label>
		            <input id="vipListId" name="vipListId" type="hidden" readonly="true"/>
		            <input id="vipListName" name="vipListName" readonly="true"/>
		            <button type="button" onclick="openDlg('vipList')" style="cursor: pointer;">选择</button>
		        </div>
		        <div class="fitem">
		            <label>参会人员名单:</label>
		            <input id="participantListId" name="participantListId" type="hidden" readonly="true"/>
		            <input id="participantListName" name="participantListName" readonly="true"/>
		            <button type="button" onclick="openDlg('participantList')" style="cursor: pointer;">选择</button>
		        </div>
		        <div class="fitem">
		            <label>主持人:</label>
		           	<input id="emceeId" name="emceeId" type="hidden" readonly="true"/>
		            <input id="emceeName" name="emceeName" readonly="true"/>
		            <button type="button" onclick="openDlg('emcee')" style="cursor: pointer;">选择</button>
		        </div>
		        <div class="fitem">
		            <label>分现场总负责人:</label>
		            <input id="personInChargeId" name="personInChargeId" type="hidden" readonly="true"/>
		            <input id="personInChargeName" name="personInChargeName" readonly="true"/>
		            <button type="button" onclick="openDlg('personInCharge')" style="cursor: pointer;">选择</button>
		        </div>
		        <div class="fitem">
		            <label>物料统筹负责人:</label>
		            <input id="personForMaterialId" name="personForMaterialId" type="hidden" readonly="true"/>
		            <input id="personForMaterialName" name="personForMaterialName" readonly="true"/>
		            <button type="button" onclick="openDlg('personForMaterial')" style="cursor: pointer;">选择</button>
		        </div>
		        <div class="fitem">
		            <label>现场工作人员调配负责人:</label>
		            <input id="personForDispatchId" name="personForDispatchId" type="hidden" readonly="true"/>
		            <input id="personForDispatchName" name="personForDispatchName" readonly="true"/>
		            <button type="button" onclick="openDlg('personForDispatch')" style="cursor: pointer;">选择</button>
		        </div>
		        <div class="fitem">
		            <label>会议内容、资料文件整合及会议流程把控负责人:</label>
		            <input id="personForFlowId" name="personForFlowId" type="hidden" readonly="true"/>
		            <input id="personForFlowName" name="personForFlowName" readonly="true"/>
		            <button type="button" onclick="openDlg('personForFlow')" style="cursor: pointer;">选择</button>
		        </div>
	        </form>
	      </div>
	      <div id="dlg-buttons" data-options="region:'south',border:false" style="text-align: right;margin-bottom: 15px;">
	        <button class="easyui-linkbutton" id="save-btn" data-options="iconCls:'icon-ok'" onclick="saveForm()" style="width:90px">保存</button>
	        <button class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</button>
	        <button class="easyui-linkbutton" onclick="clearForm()" iconcls="icon-remove" style="width:90px">重置</button>
	      </div>
	      
	      <div id="selectDlg" class="easyui-window" style="width:300px;height:600px;padding:10px 20px" data-options="closed:'true'">
	        <div class="easyui-layout" data-options="region:'center',border:false,fit:true">
	            <div data-options="region:'north',border:false,title:'查询条件'" style="height:85px;">
	                <form id="search_form" style="margin-left: 20px;vertical-align: middle;">
	                    <label>用户姓名:</label>
	                    <input name="s_cname" type="text" id="s_cname" class="easyui-validatebox" style="width: 180px;" />
	                    <label>用户类型:</label>
	                    <input id="s_usertype" class="easyui-combobox" editable="false" name="s_usertype" maxlength="50"
                        	data-options="valueField:'code',textField:'name',
                        	url:'dict/r/user_type',method:'get'" />
	                    <input id="submit_search" type="button" value="查询" class="button"/>
	                    <input id="reset_submit_search" type="button" value="重置" class="button"/>
	                </form>
	            </div>
	            <div data-options="region:'center',border:false">
	                <table id="userDg"></table>
	            </div>
	        </div>
	      </div>
	      <div>				
	      		<jsp:include page="live_preview.jsp"/> 
	      </div>
    </div>
	
    <script type="text/javascript">
    	function initLiveElements(){
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
	    	$("#selectDlg").window({
               width:700,   
               height:430,   
               resizable:false,
               singleSelect:true
	        });
	        $('#selectDlg').window("close");
	    	
	    	$('#previewDlg').window({
               width:700,   
               height:430,   
               resizable:false,
               singleSelect:true
	        });
	        $('#previewDlg').window("close");
	    	
	    	// 格式化日期控件
	    	datetimebox('time1','startTime');
	    	datetimebox('time2','endTime');
	        
	        $("#submit_search").bind('click',function(){
	            userDg.datagrid({
	                queryParams:{
	                    userType:$("#s_usertype").combobox("getValue"),
	                    cname:$("#s_cname").val()
	                }
	            });
	        });
	        $("#reset_submit_search").bind('click',function(){
	            $("#s_cname").val("");
	            $("#s_usertype").combobox('setValue',"");
	        });
    	}
    	var row;
    	/**
    	   	编辑表单
    	**/
    	function editLive() {
    		$("#save-btn").attr("disabled","true");
    		row = $('#dg').datagrid('getSelected');
            if (!row) {
               $.messager.alert('提示', "请选中一条记录再进行修改！");
               return;
            } else {
            	$("#save-btn").removeAttr('disabled');
    			$('#ff').form('clear');
            	$('#dlg').window('open').dialog('setTitle','现场管理编辑');
            	$('#ff').form('load', row);
            	$("#imgfileshow1").html("<img src='<%=basePath%>"+row.locationUrl+"'>");
            	$("#imgfileshow2").html("<img src='<%=basePath%>"+row.fieldPatternUrl+"'>");
            	$('#time1').datetimebox('setText',row.startTime);
            	$('#time2').datetimebox('setText',row.endTime);
            	ChangeUploader();
            }
            url='schedule/update';
    	}
    	
    	/**
    	   保存Form表单
    	**/
    	function saveForm() {
    		$('#ff').form('submit', {
				url : url,
				method : "POST",
				success : function(result) {
					$.messager.alert('提示', result);
					$("#dlg").window('close'); // close the schedule form
					$('#dg').datagrid('reload'); // reload the schedule data
				}
			});
    	}
    	
    	/**
    	         清空表单
    	**/
        function clearForm() {
            $('#ff').form('clear');
            $("#imgfileshow1").html("");
        	$("#imgfileshow2").html("");
            ChangeUploader();
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
	    function getUserType(value, row , index) {
	       	for (var i=0; i<userType.length; i++) {
	            if (row.userType == userType[i].code) {
	                  return userType[i].name;
	            }
	        }
	    }
      
        function openDlg(usersType) {
	        var selectedAssignId = "";
	        var dlgUrl;
	        if (row.assignId != null && row.assignId != "") {
	            selectedAssignId = row.assignId.split(",");
	        }
	        setDialogTitle(usersType);
	        if (usersType == 'vipSeat' || usersType == 'speechSeat' || usersType == 'vipList' || usersType =='participantList' || usersType == 'emcee') {
				dlgUrl = '<%=basePath%>user/findUsers';
	        }
	        else {
	        	dlgUrl = '<%=basePath%>assignment/getAssignedUserByScheduleId/' + row.id;
	        }
	       	initUserDg(dlgUrl, usersType, selectedAssignId);
        }
        
        function setDialogTitle(usersType) {
        	$("#s_cname").val("");
	        $("#s_usertype").combobox('setValue',"");
        	if (usersType == 'speechSeat')
        	    $('#selectDlg').window('open').dialog('setTitle','选择现场名签排位图(演讲区)');
        	else if (usersType == 'vipSeat')
        		$('#selectDlg').window('open').dialog('setTitle', '选择现场名签排位图(VIP区)');
        	else if (usersType == 'vipList')
     			$('#selectDlg').window('open').dialog('setTitle','选择VIP嘉宾');
     		else if (usersType == 'participantList')
     			$('#selectDlg').window('open').dialog('setTitle', '选择参会人员名单');
     		else if (usersType == 'emcee')
     			$('#selectDlg').window('open').dialog('setTitle', '选择主持人');
     		else if (usersType == 'personInCharge')
     			$('#selectDlg').window('open').dialog('setTitle', '选择分现场总负责人');
     		else if (usersType == 'personForMaterial')
     			$('#selectDlg').window('open').dialog('setTitle', '选择物料统筹负责人');
     		else if (usersType == 'personForDispatch') 
     			$('#selectDlg').window('open').dialog('setTitle', '选择现场工作人员调配负责人');
     		else if (usersType == 'personForFlow')
     			$('#selectDlg').window('open').dialog('setTitle', '选择会议内容、资料文件整合及会议流程把控负责人');
        }
        
        //保存选择的指派数据
	    function add_user_func(usersType) {
	        var rows = $('#userDg').datagrid('getSelections');
	        if (rows==null || rows.length == 0) {
	             $.messager.confirm('确认', '您没有选择任何可指派人员，该操作将删除指派给您当前选择的活动的所有人员，确定要删除吗？', function(r) {
	                if(r) {
	                    row.vipList = '';
	                    $('#vipList').hide();
	                    $('#selectDlg').window("close");
	                } else {
	                    return;
	                }
	             });
	        } else {
		        var num=rows.length;//获取要删除信息的个数
		        var sn = "";
		        for(var i=0; i<rows.length; i++){//组成一个字符串，ID主键之间用逗号隔开
		            if(i!=rows.length-1){
		            	if (usersType == 'vipSeat' || usersType == 'speechSeat' || usersType == 'vipList' || usersType =='participantList' || usersType == 'emcee') 
		                	sn+=rows[i].id+",";
		                else
		                	sn+=rows[i].assignId+",";
		             } else {
		             	if (usersType == 'vipSeat' || usersType == 'speechSeat' || usersType == 'vipList' || usersType =='participantList' || usersType == 'emcee') 
		                 	sn=sn+rows[i].id;
		                else
		                	sn=sn+rows[i].assignId;
		             }
		        }
		        var sname = "";
		        for(var i=0; i<rows.length; i++){//组成一个字符串，ID主键之间用逗号隔开
		            if(i!=rows.length-1){
		                sname+=rows[i].cname+",";
		             } else {
		                 sname=sname+rows[i].cname;
		             }
		        }
		  		userTypeName = '#' + usersType + 'Name';
		  		userTypeId = '#' + usersType + 'Id';
		        $(userTypeName).val(sname);
		        $(userTypeName).show();
		        $(userTypeId).val(sn);
		        $('#userDg').datagrid('clearSelections');
                $('#selectDlg').window("close");
	        }
	    }

		function initUserDg(dlgUrl, usersType, selectedAssignId) {
			userDg=$('#userDg').datagrid({
		            url: dlgUrl,
		            singleSelect : false,
		            pagination : true,//页码
		 		    pageNumber : 1,//初始页码
		 		    pageSize : 10,
		 		    pageList : [10,20,30,40],
		 		    queryParams:{
	                userType:$("#s_usertype").combobox("getValue"),
	                cname:$("#s_cname").val(),
	                },
		            columns:[[
		                {field:'ck',checkbox:true},
		                {field:'id', hidden:true},
		                {field:'assignId',title:'id',hidden:true},
		                {field:'cname',title:'姓名',width:80},		                
		                {field:'sex',title:'性别',width:40,formatter:getSexValue},
		                {field:'userType',title:'用户类型',width:80,formatter:getUserType},
		                {field:'mobile',title:'联系电话',width:100},
		                {field:'email',title:'电子邮箱',width:180}
		            ]],
		            onLoadSuccess:function(data){//当数据加载成功时触发
		                for (var i=0; i<data.rows.length;i++) {
		                    for (var j=0; j<selectedAssignId.length;j++) {
			                    if(data.rows[i].assignId == selectedAssignId[j]){
			                        $(this).datagrid('selectRow',i);
			                    }
		                    }
		                }
		            },
		            toolbar : [{
		                text:'指派',
		                iconCls:'icon-add',
		                handler : function(){
		                    add_user_func(usersType);
		                }
		            }]
		        });
		}
	    
	    //预览
	    function preview(value, row, index) {
	        return '<a onclick="detail()" style="cursor:pointer;"><span style="color:blue;">预览</span></a>';
	    }
	     
    	//预览现场信息
    	function detail() {
         	var row = $('#dg').datagrid('getSelected');
         	if (!row) {
            	$.messager.alert('提示', "请选中一条记录再查看详细信息！");
            	return;
         	}
         	$('.m-info').attr('readonly','true');
         	$("#imgfileshow1Preview").html("<img src='<%=basePath%>" + row.locationUrl + "'>");
            $("#imgfileshow2Preview").html("<img src='<%=basePath%>" + row.fieldPatternUrl + "'>");
         	$('#previewDlg').dialog('open').dialog('setTitle', '现场预览');
         	$('#previewff').form('load', row);
     	}
    	
    	function ChangeUploader(){
    		//将上传的图片控件清空
            uploaderdestroy(uploader1);
            uploaderdestroy(uploader2);
    		uploader1 = initUploader("browse1","filelist1","imgfileshow1","locationUrl",fileuploadurl,false,1000,300,true,location_params);
        	uploader2 = initUploader("browse2","filelist2","imgfileshow2","fieldPatternUrl",fileuploadurl,false,1000,300,true,field_pattern_params);
    	}
    </script>
  </body>
</html>