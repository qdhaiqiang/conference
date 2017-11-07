<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<jsp:include page="../../../../include/sys-common.jsp" />
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <!-- 首先需要引入plupload的源代码 -->
  <script src="<%=basePath%>js/plupload.full.min.js"></script>
  <script type="text/javascript" src="<%=basePath%>js/fileuploaduser.js"></script>
    <script type="text/javascript">
    var scheduleId = "";
	var userName = "";
	var userEmail = "";
	var schduleList = new Array();
	var datagrid;
	var userfilesDg;
	var logId;
	var uploader;
	
	//初始化上传文件的控件及方法
  	var fileuploadurl ="<%=basePath%>fileload/c/upload";
	   //browse bannerPic filelist imgfileshow为上传图片及预览所需控件id
	   //附加参数,文件指定目录
		var multipart_params = {
			optiontype:"metting",
			sign:'${meetingId}',//后期修改成从session中取mettingid
			filecategory:'schduleLogSpeechFiles'
		};
	   
	   
	$(document).ready(function(){
		loadSchduleList();
		loadGrid();
		setSchduelSearch();
		
		$("#photoimgfileshowDiv").hide();
		//addFileDiv photofilelistDiv photoimgfileshowDiv photobrowseDiv photoinputDiv
		uploader = initUploader("photobrowseDiv","photofilelistDiv","photoimgfileshowDiv","photoinputDiv","addFileDiv",fileuploadurl,false,0,0,false,multipart_params);
	});
	
	
	   
    </script>
    
  </head>
  
  <body>
    <table id="dg" title="分会场记文档管理" style="width:100%;height:100%">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'schdule_id',width:'200',formatter:setSchduelName">日程</th><!-- ,formatter:setSchduelName -->
				<th data-options="field:'cname', width:'100'">用户姓名</th>
				<th data-options="field:'email', width:'150'">用户邮箱</th>
				<th data-options="field:'speech_topic', width:'100'">演讲主题</th>
				<th data-options="field:'userOptions', width:'50',formatter:setUserOptions">文档管理</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">   
		<div float:right>
			<form id="select">
				<label>选择日程:</label>
					<input id="findSchduelId" class="easyui-combobox" validType="searchParm" editable="false"/>	<!-- editable="false" -->					
				<label>姓名:</label>
					<input type="text" id="findUserName" class="easyui-validatebox" validType="searchParm"/>						
				<label>邮箱:</label>
					<input type="text" id="findUserEmail" class="easyui-validatebox" validType="searchParm"/> 
				<input class="search-btn" onclick="setSearchForm()" type="button" value="查询" />
				<input class="search-btn" onclick="resetSearchForm()" type="button" value="重置" />
			</form>
		</div>
	</div>
	
	<!-- 初始化窗口打开时最大化 -->
	<div id="userfilesDlg" class="easyui-window" style="width:100%;height:100%" data-options="maximizable:true,maximized:true,closed:'true'">
		<table id="userfilesDg" style="width:100%;height:100%">
			<thead>
				<tr>
					<th data-options="field:'ck',checkbox:true"></th>
					<th data-options="field:'id',hidden:true"></th>
					<th data-options="field:'logId',hidden:true"></th>
					<th data-options="field:'fileName', width:'200'">文件名</th>
					<th data-options="field:'filePath', width:'500'">文件路径</th>
					<th data-options="field:'userDownloadFile', width:'50',formatter:userDownloadFile">下载</th>
				</tr>
			</thead>
		</table>
		
		<div id="addfileDlg" class="easyui-dialog" style="width:400px;height:200px;padding:20px 20px" closed="true" maximizable="true" resizable="true" left="150px" top="100px" buttons="#dlg-buttons">
			<div id="addFileDiv" >
				<a id="photobrowseDiv">[选择文件]</a>
				<div id="photofilelistDiv"></div>
				<div id="photoimgfileshowDiv"></div>
				<input id="photoinputDiv" name="file" value="" type="hidden">
			</div>
		</div>
		<div id="dlg-buttons">
			<button class="easyui-linkbutton" iconCls="icon-ok" onclick="uploadFileById()" style="width:90px">保存</button>
			<button class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#addfileDlg').dialog('close')" style="width:90px">关闭</button>
			<button class="easyui-linkbutton" iconCls="icon-remove" onclick="clearuploadFile()" style="width:90px">重置</button>
		</div>
	</div>
	
	
  </body>
  
  <script type="text/javascript">
  function loadGrid(){
		datagrid = $('#dg').datagrid({
			url:'schedulelog/files/findUers',
			method:'post',
			idField:'id',
			striped:true,
			rownumbers:true,
			singleSelect:false,
			nowrap:false,
			loadMsg:'加载中，请稍候...',
			fit:true,
			fitColumns:true,
			multiSort:true,
			pagination : true,//页码
			pageNumber : 1,//初始页码
			pageSize : 15,
			pageList : [15,30,45,60],
			toolbar:'#toolbar',
			onBeforeLoad:function(param){
				param.scheduleId = scheduleId;
				param.userName = userName;
				param.userEmail = userEmail;
			},
		});
	}
  
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
  
 function setSchduelSearch(){
	 $("#findSchduelId").combobox({
         data: schduleList,//json格式的数据
         valueField:'id',
         textField:'title'
     });
 }
  
//每条加载的物料中根据日程id显示日程名称
  function setSchduelName(value, row , index) {
     for (var i=0; i<schduleList.length; i++) {
         if (value == schduleList[i].id) {
             return schduleList[i].title;
         }
     }
 }
 
//点击搜索时，放入搜索条件
	function setSearchForm(){
		if (!$("#select").form('validate')) {
			return false;
		}else {
			scheduleId = $("#findSchduelId").combobox('getValue');
			userName = $("#findUserName").val().replace(" ","");
			userEmail = $("#findUserEmail").val().replace(" ","");
			datagrid.datagrid({
				pageNumber : 1
			});
		}
	}
	//重置查询条件
	function resetSearchForm(){
		$("#select").form('clear');
		schduelId = "";
		userName = "";
		userEmail = "";
	}
	
	//打开用户文件
	function setUserOptions(value, row, index) {
		return '<a onclick="showUserFiles()" style="cursor:pointer;"><span style="color:blue;">文档管理</span></a>';
	}
	
	function showUserFiles(){
		var row = $('#dg').datagrid('getSelected');
	    if (!row) {
	    	$.messager.alert('提示', "请选中一个用户再管理文档！");
	    	return;
	    }
	    var filesList;
	    logId = row.id;
	    $('#userfilesDlg').window('open').dialog('setTitle','用户：'+row.cname+'的文档管理');
	    
	    /*$.ajax({
            url: 'schedulelog/files/findUerFiles/' + logId,
            method:'POST',
            async:false,
            success:function(data) {
            	filesList = data;
            }
        });*/
	    // data : filesList,
	    userfilesDg=$('#userfilesDg').datagrid({
            url:'<%=basePath%>schedulelog/files/findUerFiles/' + logId,
            singleSelect : false,
			pagination: false,
			rownumbers: true,
            toolbar : [{
                text:'新增文件',
                iconCls:'icon-add',
                handler : function(){
                	savefile();
                }
            },
            {
                text:'删除文件',
                iconCls:'icon-remove',
                handler : function(){
                	deletefiles();
                }
            }]
        });
	}
	
	//审核链接拼写
    function userDownloadFile(value, row, index) {
		var path = row.filePath;
		var endIndex = path.indexOf(";");
		path = path.substring(0,endIndex);
		var aherf = '';
		aherf += '<a id="downherf'+
				index+'" target="_blank" href="<%=basePath%>'+
				path+'"> <span style="color:blue;">下载</span></a>';
        return aherf;
    }
	
	function deletefiles(){
		var rows = userfilesDg.datagrid('getSelections');
		var num=rows.length;//获取要删除信息的个数
	    if (!rows || num < 1) {
	    	$.messager.alert('提示', "请选中一个文档再删除！");
	    	return;
	    }
	    
	    var sn = "";
	    var paths = "";
	    for(var i=0; i<rows.length; i++){//组成一个字符串，ID主键之间用逗号隔开
            if(i!=rows.length-1){
                sn=sn+rows[i].id+",";
             }else{
                sn=sn+rows[i].id;
             }
	    	paths += rows[i].filePath;
        }
	    
	    $.messager.confirm('确认', '确定要删除这'+ num+'条记录吗？', function(r){
	    	if(r){
	    		$.ajax({
	                url: '<%=basePath%>schedulelog/files/dropFile',
	                method:'POST',
	                data: {'ids':sn,'paths':paths},
	                async:false,
	                success:function(data) {
	                	$.messager.alert('提示', "成功删除"+data.deleteRows+"条记录。");
	                	userfilesDg.datagrid('reload');
	                }
	            });
	    	}
	    });
	    
	}
	
	function savefile(){
		$('#addfileDlg').window('open').dialog('setTitle','上传文件');
	}
	
	function uploadFileById(){
		//logId
		var filename = $("#photofilelistDiv div").last().html();
		var endIndex = filename.indexOf("<span>");
		filename = filename.substring(0,endIndex);
		var param = {};
		param["logId"] = logId;
		param["fileName"] = filename;
		param["filePath"] = $("#photoinputDiv").val();
		$.ajax({
            url: '<%=basePath%>schedulelog/files/save',
            method:'POST',
            data:param,
            async:false,
            success:function(data) {
            	userfilesDg.datagrid('reload');
            	$.messager.alert('提示',"文件"+filename+"上传成功。");
            	$('#addfileDlg').window('close');
            	clearuploadFile();
            	
            }
        });
	}
	
	function clearuploadFile(){
		removefiles(uploader);
		//$("#photofilelistDiv").empty();
    	//$("#photoinputDiv").val("");
		//document.getElementById("photofilelistDiv").innerHTML = "";
		//document.getElementById("photoinputDiv").value = "";
	}
  </script>
  
</html>
