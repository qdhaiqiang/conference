<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	response.setDateHeader("Expires", 0);
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<style type="text/css">
	.fitem {
	    margin-bottom: 5px;
	}
	</style>
	<jsp:include page="../../../../include/sys-common.jsp" />
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <!-- 首先需要引入plupload的源代码 -->
    <script src="<%=basePath%>js/plupload.full.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/fileupload.js"></script>
    <script type="text/javascript">
		var datagrid;
		var selectExhibitionName = "";
		$(document).ready(function(){
		    closeEditor();
		    $('#dg').datagrid('hideColumn','exhibitionId');
		    $("#user_submitSearch").bind('click',function(){
            if (!$("#select").form('validate')) {
                return false;
            }else {
                selectExhibitionName = $("#selectExhibitionName").val().replace(" ","");
                datagrid.datagrid({
                    pageNumber : 1
                });
            }
        });
        $("#user_resetSearch").bind('click',function(){
            $("#selectExhibitionName").val("");
        });
		});  
		function loadGrid()  {
			datagrid = $('#dg').datagrid({
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
	                param.exhibitionName = selectExhibitionName;
            },
			});
			
		}

	</script>
  </head>
  
  <body>
  	
  	<div style="width:100%; height:100%;" id="tablegrid">
		<table id="dg" title="展位类型管理" style="width:100%;height:100%"
        	data-options="striped:true,rownumbers:true,singleSelect:false,url:'confExhibitionController/r',method:'post', 
       				 multiSort:true,fit:true,nowrap:false,toolbar:'#toolbar',pagination:'true'">
	        <thead>
	            <tr>
	                <th data-options="field:'ck',checkbox:true"></th>
	                <th data-options="field:'exhibitionId'"></th>
	                <th data-options="field:'exhibitionName', width:'150'">展位类型名称</th>
	                <th data-options="field:'exhibitionImage', width:'100'">展位示意图</th>
	                <th data-options="field:'exhibitionPrice', width:'120'">展位人民币价格</th>
	                <th data-options="field:'exhibitionServiceCharge', width:'120'">展位葡币价格</th>
	            </tr>
	        </thead>
	    </table>
	</div>
	
	<div id="toolbar">
	<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true" onclick="addTemplateInfo()" >添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="editTemplateInfo()">修改</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true" onclick="delTemplateInfo()">删除</a>
		<div float:right>
            <form id="select">
                <label>展位类别名称:</label><input name="selectExhibitionName" type="text" id="selectExhibitionName"  class="easyui-validatebox"  validType="searchParm"/>            
                <input id="user_submitSearch" type="button" value="查询" class="search-btn" />
                <input id="user_resetSearch" type="button" value="重置" class="search-btn" />
            </form>
        </div>
	</div>
	
	<div id="ueditor-win" class="easyui-dialog"
        style="width:50%;height:85%;padding:10px 20px" closed="true"
        maximizable="true" resizable="true"
        left="150" top="0"
        buttons="#dlg-buttons">
		<div align="center">
			<form id="ff" method="post">
				<table cellpadding="5">
					<tr>
						<td></td>
						<td><input class="easyui-validatebox" type="hidden" name="exhibitionId" value="" readonly="true"></input>
						</td>
					</tr>
					<tr>
						<td>展位类别名称:</td>
						<td><input class="easyui-validatebox" data-options="required:'true'" type="text" id = "exhibitionName" name="exhibitionName" value="" style="width: 320px;"></input>
						</td>
					</tr>
					<tr>
						<td>展位人民币价格:</td>
						<td><input class="easyui-numberbox" data-options="required:'true'" id="exhibitionPrice" name="exhibitionPrice" style="width: 320px;"  value="" ></input>
						</td>
					</tr>
					<tr>
						<td>展位葡币价格:</td>
						<td><input class="easyui-numberbox" data-options="required:'true'" id="exhibitionServiceCharge" name="exhibitionServiceCharge" style="width: 320px;" type="number" ></input>
						</td>
					</tr>
					<tr>
						<td>展位示意图:</td>
						<td>
                            <div id="exhibitionImage1"></div>
                            <div id="imgfileshow_photo"></div>
                            <button id="exhibition_pic1">选择文件</button>
                            <button type="button" onclick="deletePhoto()" id="delete_cert_pic1" class="search-btn">删除</button>
                            <input id="exhibitionImage" name="exhibitionImage" type="hidden">
                        </td>
					</tr>
				</table>
			</form>
		
		    <div id="dlg-buttons">
					<button class="easyui-linkbutton" id="add" iconCls="icon-ok" onclick="add()" style="width:90px">添加</button>
					<button class="easyui-linkbutton" id="edit" iconCls="icon-ok" onclick="edit()" style="width:90px">修改</button>
					<button class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#ueditor-win').dialog('close')" style="width:90px">关闭</button>
			</div>
		</div>
 	</div>
	<script type="text/javascript">
		var templateType;
		var userType;
	    $(function(){
	    	// 获取展位类型
	    	$.ajax({
	    		url: 'dict/r/exhibition_type',
	    		method:'get',
	    		async:false,
	    		success:function(data) {
	    			templateType = data;
	    		}
	    	});
	    	
	    });
	    var uploader1 = "";
        var uploader2 = "";
        var uploader3 = "";

        function uploadimage(exhibitionName){
            //上传下载图片
               var fileuploadurl ="<%=basePath%>fileload/c/upload";
                //上传图片的附加参数,sign需要修改成用户邮箱
                var userphotoparam = {
                        optiontype:"meeting",
                        sign:"${meetingId}",
                        filecategory:'exhibition'
                };
               //browse bannerPic filelist imgfileshow
               uploader1 = initUploader("exhibition_pic1","exhibitionImage1","imgfileshow_photo","exhibitionImage",fileuploadurl,false,300,300,true,userphotoparam);
        }
        function deletePhoto () {
            $('#imgfileshow_photo').html("");
            $('#exhibitionImage').val("");
        }
		function addTemplateInfo() {
			clearForm();
			$('#ff').form('reset');
			$('#exhibitionName').val('');
			$('#exhibitionPrice').val('');
		    $('#add').css('display','inline');
		    $('#edit').css('display','none');
 		    
		    openEditor();
		    uploaderdestroy(uploader1);
		    // 初始化展位类型
			
            uploadimage("newUser");
		}
		
		function add() {
			$('#ff').form('submit', {
				url:'<%=basePath%>confExhibitionController/add',
				method:'POST',
				success:function(data){
						$.messager.alert('提示',data);
						closeEditor();
					}
				});
		}
		
		function getTemplateType(value,row,index) {
			for (var i=0; i<templateType.length; i++) {
        		if (value == templateType[i].code) {
        			return templateType[i].name;
        		}
        	}
		}
		
		
		function delTemplateInfo(){
			var ids = "";
			var rows = $('#dg').datagrid('getSelections');
			var num=rows.length;//获取要删除信息的个数
			if(num > 0){
				for(var i=0; i<rows.length; i++){//组成一个字符串，ID主键之间用逗号隔开
					if(i!=rows.length-1){
						ids=ids+rows[i].exhibitionId+",";
				     }else{
				    	 ids=ids+rows[i].exhibitionId;
				     } 
					} 
					$.messager.confirm("提示", "是否删除选中的"+num+"条数据?", function (r) {
					if(r){
						 $.ajax({
					          type:"GET",  
					          url:'<%=basePath%>confExhibitionController/delete',  
					          data: {id:ids},  
					          success:function(msg){
					        	  if(msg.isDelete == "true"){
					        		  $.messager.alert('提示',"成功删除"+msg.num+"条数据。");
					        		  loadGrid();
					        	  }else if(msg.isDelete == "false"){
					        		  $.messager.alert('提示',"删除失败。此展位类别已有"+msg.num+"个展位，不可删除。\n请删除相应展位后再删除该展位类型");
					        	  }
					          }  
					       });
					}
					});
			}else{
				$.messager.alert('错误','请选中要删除的模板！');
			}
			
		}
		
		function editTemplateInfo(){
			$('#add').css('display','none');
			$('#edit').css('display','inline');
			var row = $('#dg').datagrid('getSelections');
			var num  = row.length;
			if(num > 1 || num == 0){
				$.messager.alert('提示',"请选中一条记录进行修改");
				return;
			}
			openEditor();
			uploaderdestroy(uploader1);
			$("#imgfileshow_photo").html("");
			if (row[0].exhibitionImage != null && row[0].exhibitionImage != "") {
                $("#imgfileshow_photo").html("<img src='"+row[0].exhibitionImage+"'>");
            }
            
			$("#ff").form("load", row[0]);
			for (var i=0; i<templateType.length; i++) {
        		if (row[0].name == templateType[i].code) {
        			$("#lblTemplateName").html(templateType[i].name);
        		}
        	}
			
			uploadimage(row[0].exhibitionName);
		}
		
		function edit() {
			if($("#content").val()==""){
				//return;
			}
			$('#ff').form('submit', {
				url:'<%=basePath%>confExhibitionController/update',
				method:'POST',
				success:function(data){
						closeEditor();
						$.messager.alert('提示',data);
				}
			});
		}
		
		function clearForm() {
			$('#imgfileshow_photo').html("");
            $('#exhibitionImage').val("");
		}
		
		function closeEditor() {
			$('#ueditor-win').dialog('close');
			loadGrid();
		}
		
		function openEditor() {
			$("#ueditor-win").show();
            $('#ueditor-win').dialog('open').dialog('setTitle', '展位类型信息');
		}


		
	</script>
    
  </body>
</html>
 
