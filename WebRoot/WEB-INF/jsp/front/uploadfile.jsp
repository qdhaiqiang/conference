<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

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

<%--	<title>Plupload使用指南</title>--%>
	<title><spring:message code="titleInfo.meetingTitle" /></title>
	<script src="<%=basePath%>public/ui/jquery.min.js"></script>
    <!-- 首先需要引入plupload的源代码 -->
    <script src="<%=basePath%>js/plupload.full.min.js"></script>
  </head>
  
  <body>
    This is my JSP page. <br>
    
    <!-- 这里我们只使用最基本的html结构：一个选择文件的按钮，一个开始上传文件的按钮(甚至该按钮也可以不要) -->
    <p>
        <button id="browse">选择文件</button>
        <button id="start_upload">开始上传</button>
        
        <button id="removefiles"  onclick="removefiles()">移除文件</button>
        
        <form id="downform" action='<%=basePath%>fileload/c/download' method="post">
        	<button id="download" type="submit">下载excel文件</button>
        </form>
        
    </p>
    <input id="showPathinput">
    <div id="showPathdiv"></div>
    <div id="filelist"> </div>
    <div id="imgfileshow"> </div>
    <script>
    
    $(document).ready(function(){
    	//$("#download").bind('click',downloadfiles);
    });

    function downloadfiles(){
    	window.location.href = '<%=basePath%>fileload/c/download';
    	/*$.ajax({
            url: '',
            method:'post',
            async:false,
            success:function(data) {
            	alert("下载完成");
            }
        });*/
    }
    
    //实例化一个plupload上传对象
    var uploader = new plupload.Uploader({
        browse_button : 'browse', //触发文件选择对话框的按钮，为那个元素id
        url : '<%=basePath%>fileload/c/upload', //服务器端的上传页面地址
        multipart:true,//为true时将以multipart/form-data的形式来上传文件，为false时则以二进制的格式来上传文件
        resize: {
        	width: 300,
        	height: 300,
        	crop: false,//是否裁剪
        	quality: 60,
        	preserve_headers: false
        },
        filters : {
			max_file_size : '10mb',
			mime_types: [
				{title : "Image files", extensions : "jpg,gif,png"},
				{title : "Zip files", extensions : "zip"},
				{title : "office files", extensions : "doc,docx,ppt,excel"}
			],
			prevent_duplicates : true //不允许选取重复文件
		},
		//附加参数
		multipart_params: {
			optiontype:"user",
			sign:'alisa.yang@centling.com',
			filecategory:'front'
		},
        
        init:{
        //在实例对象上调用init()方法进行初始化
			PostInit: function() {
				document.getElementById('filelist').innerHTML = '';

				document.getElementById('start_upload').onclick = function() {
					uploader.start();
					return false;
				};
			},

			 //绑定各种事件，并在事件监听函数中做你想做的事
			FilesAdded: function(up, files) {
				plupload.each(files, function(file) {
					document.getElementById('filelist').innerHTML += '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ') <b></b></div>';
				});
			},

			UploadProgress: function(up, file) {
				document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";
			},
			
			FileUploaded:function(uploader,file,responseObject){
				var filepath = responseObject.response;
				document.getElementById('showPathinput').value = filepath;
				document.getElementById('showPathdiv').innerHTML += filepath+"<br>";
				
				if(file.type.substr(0,5) == 'image'){
					previewImage(file,function(imgsrc){
						document.getElementById('imgfileshow').innerHTML += "&nbsp;<img src='"+imgsrc+"' id='"+file.id+"'>&nbsp;";
					});
				}
				
			},
			FilesRemoved:function(uploader,files){
				plupload.each(files, function(file) {
					document.getElementById('filelist').removeChild(document.getElementById(file.id));//删除id为file.id的元素
					document.getElementById('imgfileshow').removeChild(document.getElementById(file.id));
				});
			},

			Error: function(up, err) {
				document.getElementById('console').innerHTML += "\nError #" + err.code + ": " + err.message;
			},
			Destroy:function(uploader){
				document.getElementById(showPathinput).value = "";
				document.getElementById(showPathdiv).innerHTML = "";
				document.getElementById(filelist).innerHTML = "";
			}
		}
     });

	uploader.init();
	
	//plupload中为我们提供了mOxie对象
	//有关mOxie的介绍和说明请看：https://github.com/moxiecode/moxie/wiki/API
	//如果你不想了解那么多的话，那就照抄本示例的代码来得到预览的图片吧
	function previewImage(file,callback){//file为plupload事件监听函数参数中的file对象,callback为预览图片准备完成的回调函数
		if(!file) return;
		var preloader = new mOxie.Image();
		preloader.onload = function() {
			preloader.downsize( 300, 300 );//先压缩一下要预览的图片
			var imgsrc =  preloader.getAsDataURL(); //得到图片src,实质为一个base64编码的数据
			callback && callback(imgsrc); //callback传入的参数为预览图片的url
		};
		preloader.load( file.getSource() );
	}
	
	function removefiles(){
		var filesinQueue = uploader.files;
		for(var i=0;i<filesinQueue.length;i++){
			uploader.removeFile(filesinQueue[i]);
		}
		/* plupload.each(filesinQueue, function(file) {
			uploader.removeFile(file);
		}); */
		//uploader.init();
	}
    </script>
  </body>
</html>
