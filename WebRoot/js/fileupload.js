//buttonid点击选择图片的按钮id,filelistid显示文件列表的id,imgfileshow图片预览控件id,
//pathinputid图片上传成功，input放入返回值,fileuploadurl：上传文件的后台url
//multiSelect：false只能选单个文件true选择多个文件,width图片宽度,height图片高度,isImage：true传图片，false传office文件
function initUploader(buttonid,filelistid,imgfileshow,pathinputid,fileuploadurl,multiSelect,width,height,isImage,params){

	var filterType;
	if(isImage){
		filterType = [{title : "Image files", extensions : "bmp,jpg,jpeg,gif,png"}];
	}else{
		filterType = [{title : "office files", extensions : "doc,xls,ppt,docx,xlsx,pptx"}];
	}
		//实例化一个plupload上传对象
	   var uploader = new plupload.Uploader({
		   runtimes : 'html5,html4',
	        browse_button : document.getElementById(buttonid), //触发文件选择对话框的按钮，为那个元素id
	        //container: document.getElementById(containerdiv), //'' ... or DOM Element itself
	        url : fileuploadurl, //服务器端的上传页面地址
	        multipart:true,//为true时将以multipart/form-data的形式来上传文件，为false时则以二进制的格式来上传文件
	        resize: {
	        	width: width,
	        	height: height,
	        	crop: false,//只做压缩，不做裁剪
	        	quality: 60,
	        	preserve_headers: false
	        },
	        filters : {
				mime_types: filterType,
				prevent_duplicates : true, //不允许选取重复文件
			},
			multi_selection:multiSelect,//false是只可选择单个文件，true是可选择多个文件
			//附加参数
			multipart_params: params,
	        init:{
	        //在实例对象上调用init()方法进行初始化
				PostInit: function() {
					document.getElementById(filelistid).innerHTML = '';
					//document.getElementById(imgfileshow).innerHTML = '';
					/*document.getElementById(uploadbutton).onclick = function() {uploader.start();return false;};*/
				},

				 //绑定各种事件，并在事件监听函数中做你想做的事
				FilesAdded: function(up, files) {
					plupload.each(files, function(file) {
						if(!multiSelect){
							document.getElementById(filelistid).innerHTML = "";
						}
						document.getElementById(filelistid).innerHTML += 
							'<div id="' + file.id + '">' + file.name + '<span>(' + plupload.formatSize(file.size) + ')</span><b></b></div>';
						uploader.start();
					});
				},

				UploadProgress: function(up, file) {
					document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";
				},
				
				FileUploaded:function(uploader,file,responseObject){
					var responseStr = responseObject.response;
					var responseSize = responseStr.length;
					if(responseStr.substr(0,5) == '<pre>'){
						responseStr = responseStr.substr(5,responseSize-11);//第二个参数：在返回的子字符串中应包括的字符个数
					}
					if(!multiSelect){
						document.getElementById(imgfileshow).innerHTML = "";
						document.getElementById(pathinputid).value = "";
						if(file.type.substr(0,5) == 'image'){
							document.getElementById(imgfileshow).innerHTML += "&nbsp;<img src='"+responseStr+"' id='"+file.id+"'>&nbsp;";
							//previewImage(file,width,height,function(imgsrc){});
						}
						document.getElementById(pathinputid).value += responseStr + ";";
					}else{
						document.getElementById(pathinputid).value += responseStr + ";";
					}
					
				},
				
				FilesRemoved:function(uploader,files){
					plupload.each(files, function(file) {
						document.getElementById(filelistid).removeChild(document.getElementById(file.id));//删除id为file.id的元素
						//document.getElementById(imgfileshow).removeChild(document.getElementById(file.id));
						document.getElementById(pathinputid).value = "";
					});
				},

				Error: function(up, err) {
					var error = "\nError #" + err.code + ": " + err.message;
					//document.getElementById('console').innerHTML += "\nError #" + err.code + ": " + err.message;
				},
				Destroy:function(uploader){
					//document.getElementById(pathinputid).value = "";
					//document.getElementById(imgfileshow).innerHTML = "";
					//document.getElementById(filelistid).innerHTML = "";
					uploader = "";
				}
			}
	     });
	    
	    uploader.init();
	    return uploader;
	}

	function removefiles(uploader){
		var filesinQueue = uploader.files;
		plupload.each(filesinQueue, function(file) {
			uploader.removeFile(file);
		});
		//uploader.init();
	}
	
	function uploaderdestroy(uploader){
		if(uploader){
			uploader.destroy();
		}
	}
	

	//plupload中为我们提供了mOxie对象
	//有关mOxie的介绍和说明请看：https://github.com/moxiecode/moxie/wiki/API
	//如果你不想了解那么多的话，那就照抄本示例的代码来得到预览的图片吧
	function previewImage(file,width,height,callback){//file为plupload事件监听函数参数中的file对象,callback为预览图片准备完成的回调函数
		if(!file) return;
		var preloader = new mOxie.Image();
		preloader.onload = function() {
			preloader.downsize(width,height);//先压缩一下要预览的图片
			var imgsrc =  preloader.getAsDataURL(); //得到图片src,实质为一个base64编码的数据
			callback && callback(imgsrc); //callback传入的参数为预览图片的url
		};
		preloader.load( file.getSource() );
	}