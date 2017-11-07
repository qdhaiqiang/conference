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
	<jsp:include page="../../../include/sys-common.jsp" />
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <script type="text/javascript">
		var datagrid;
		var selectExhibitionType = "";
		var selectExhibitionName = "";
		$(document).ready(function(){
		    closeEditor();
		    $('#dg').datagrid('hideColumn','exhibitionId');
		    $("#user_submitSearch").bind('click',function(){
            if (!$("#select").form('validate')) {
                return false;
            }else {
                selectExhibitionName = $("#selectExhibitionName").val().replace(" ","");
                selectExhibitionType = $("#selectExhibitionType").combobox("getValue");
                datagrid.datagrid({
                    pageNumber : 1
                });
            }
        });
        $("#user_resetSearch").bind('click',function(){
            $("#selectExhibitionName").val("");
            $("#selectExhibitionType").combobox('setValue',"");
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
	                param.exhibitionType = selectExhibitionType;
            },
			});
			
		}

	</script>
  </head>
  
  <body>
  	
  	<div style="width:100%; height:100%;" id="tablegrid">
		<table id="dg" title="展位列表" style="width:100%;height:100%"
        	data-options="striped:true,rownumbers:true,singleSelect:true,url:'confExhibitionController/r',method:'get', 
       				 multiSort:true,fit:true,nowrap:false,toolbar:'#toolbar',pagination:'true'">
	        <thead>
	            <tr>
	                <th data-options="field:'ck',checkbox:true"></th>
	                <th data-options="field:'exhibitionId'"></th>
	                <th data-options="field:'exhibitionName', width:'90'">展位名称</th>
	                <th data-options="field:'exhibitionType', width:'80',formatter:getTemplateType ">展位类型</th>
	                <th data-options="field:'exhibitionImage', width:'100'">展位图片</th>
	                <th data-options="field:'exhibitionPrice', width:'120'">展位价格</th>
	                <th data-options="field:'exhibitionServiceCharge', width:'150'">展位服务费</th>
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
                <label>展位名称:</label><input name="selectExhibitionName" type="text" id="selectExhibitionName"  class="easyui-validatebox"  validType="searchParm"/>
                <label>展位类别:</label><input id="selectExhibitionType" class="easyui-combobox" editable="false" name="selectExhibitionType" maxlength="50"
                        data-options="valueField:'code',textField:'name',
                        url:'dict/r/exhibition_type',method:'get'" />
                <input id="user_submitSearch" type="button" value="查询" class="search-btn" />
                <input id="user_resetSearch" type="button" value="重置" class="search-btn" />
            </form>
        </div>
	</div>
	
	<div id="ueditor-win">
		<div align="center">
			<form id="ff" method="post">
				<table cellpadding="5">
					<tr>
						<td></td>
						<td><input class="easyui-validatebox textbox" type="hidden" name="exhibitionId" value="" readonly="true"></input>
						</td>
					</tr>
					<tr>
						<td>展位名称:</td>
						<td><input class="easyui-validatebox textbox" type="text" id = "exhibitionName" name="exhibitionName" value="" data-options="required: true" style="width: 540px;"></input>
						</td>
					</tr>
					<tr>
						<td>展位类型:</td>
						<td><input class="easyui-combobox" id="exhibitionType" name="exhibitionType" style="width:540px;" data-options="required: true" value=""></input>
						</td>
					</tr>

					<tr>
						<td>展位价格:</td>
						<td><input class="easyui-validatebox textbox" id="exhibitionPrice" name="exhibitionPrice" style="width: 540px;" data-options="required: true" value="" ></input>
						</td>
					</tr>
					<tr>
						<td>展位服务费:</td>
						<td><input class="easyui-validatebox textbox" id="exhibitionServiceCharge" name="exhibitionServiceCharge" style="width: 540px;" data-options="required: true" value="" ></input>
						</td>
					</tr>
					<tr>
						<td>展位图片:</td>
						<td><input class="easyui-validatebox textbox" id="exhibitionImage" name="exhibitionImage" style="width: 540px;" data-options="required: true" value="" ></input>
						
						<div class="fitem">
                            <label>展位图片:</label>
                            <div id="filelist_cert_pic1"></div>
                            <div id="imgfileshow_cert_pic1"></div>
                            <button id="exhibition_pic1">选择文件</button>
                            <button type="button" onclick="deleteCertPic1()" id="delete_cert_pic1">删除</button>
                            <input id="pic1" name="pic1" type="hidden">
                        </div>
                        </td>
					</tr>
				</table>
			</form>
		
		    <div style="text-align:center;padding:5px">
					<a href="javascript:void(0)" id="add" class="easyui-linkbutton" iconCls="icon-add" onclick="add()" style="padding:5px;">添加</a>
					<a href="javascript:void(0)" id="edit" class="easyui-linkbutton" iconCls="icon-ok" onclick="edit()" style="padding:5px;">修改</a>
					<a id="closehref" href="javascript:void(0)" class="easyui-linkbutton"  onclick="closeEditor()">关闭</a>
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
               var fileuploadurl ="<%=basePath%>fileload/c/upload/exhibition";
                //上传图片的附加参数,sign需要修改成用户邮箱
                var userphotoparam = {
                        optiontype:"exhibition",
                        sign:exhibitionName,
                        filecategory:'photo'
                };
               //browse bannerPic filelist imgfileshow
               uploader1 = initUploader("browse_photo","filelist_photo","imgfileshow_photo","photo",fileuploadurl,false,300,300,true,userphotoparam);
        }
		function addTemplateInfo() {
			clearForm();
			$('#ff').form('reset');
			$('#exhibitionName').val('');
			$('#exhibitionPrice').val('');
		    $('#add').css('display','inline');
		    $('#edit').css('display','none');
		    openEditor();
		    // 初始化展位类型
			$("#exhibitionType").combobox({
            	url:'dict/r/exhibition_type',
            	method:'get',
            	valueField:'code',
            	textField:'name',
            	multiple:false,
                editable:false,  
                panelHeight:'auto'
            });
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
						ids=ids+rows[i].id+",";
				     }else{
				    	 ids=ids+rows[i].id;
				     } 
					} 
					$.messager.confirm("提示", "是否删除选中的"+num+"条数据?", function (r) {
					if(r){
						 $.ajax({  
					          type:"GET",  
					          url:'<%=basePath%>confExhibitionController/delete',  
					          data: {id:ids},  
					          success:function(msg){  
					             alert(msg);  
					             loadGrid();
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
			$("#ff").form("load", row[0]);
			for (var i=0; i<templateType.length; i++) {
        		if (row[0].name == templateType[i].code) {
        			$("#lblTemplateName").html(templateType[i].name);
        		}
        	}
			// 初始化展位类型
			$("#exhibitionType").combobox({
            	url:'dict/r/exhibition_type',
            	method:'get',
            	valueField:'code',
            	textField:'name',
            	multiple:false,  
                editable:false,  
                panelHeight:'auto'
            });
			// 绑定展位类型
			$("#exhibitionType").combobox('setValues',row[0].exhibitionType);
		}
		
		function edit() {
			if($("#content").val()==""){
				alert("请输入短信内容！");
				return;
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
		}
		
		function closeEditor() {
			$('#ueditor-win').hide();
			$('#closehref').hide();
			$('#tablegrid').show();
			loadGrid();
		}
		
		function openEditor() {
			$('#ueditor-win').show();
			$('#closehref').show();
			$('#tablegrid').hide();
		}


		
	</script>
    
  </body>
</html>
 
