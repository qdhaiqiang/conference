<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<jsp:include page="../../../include/common.jsp"></jsp:include>


<style>
body {
	padding-top: 20px;
}
</style>

<link href="<%=basePath%>js/plugins/bwizard/css/bwizard.min.css"
	rel="stylesheet" />

<style>
.label-inverse,.badge-inverse {
	background-color: #333;
}

.label {
	background-color: #999;
}
</style>

</head>

<body>


	<div id="win" class="easyui-window"	data-options="iconCls:'icon-save',modal:true,">
		<div class="easyui-panel" style="width:486px;">
			<div align="center">
				<form id="ff" method="post" enctype="multipart/form-data">
					<table cellpadding="5" style="width:320px">
               			<tr>
		                    <td>上传:</td>
		                    <td><input style="width:260px" name="myFile" class="easyui-filebox" data-options=" buttonText: '浏览'"></input></td>
                		</tr>
					</table>
				</form>
				<div style="text-align:center;padding:5px">
					<a href="import_template/user-template.xlsx" class="easyui-linkbutton">模板下载</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="upload()">上传</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">取消</a>
				</div>
			</div>
		</div>
	</div>


	<script src="<%=basePath%>js/plugins/bwizard/js/bwizard.min.js"
		type="text/javascript"></script>
	<script type="text/javascript">
	   
	   function upload() {
			$('#ff').form('submit', {
				url:"user/imp",
				method:"POST",
				success:function(data){
						$.messager.alert('提示',data);
						$('#win').window("close");
						loadGrid();
					}
				});
		}
	   function clearForm() {
			$('#ff').form('reset');
		}
	   function downloadTemplate(){

	   }
	</script>
</body>
</html>
