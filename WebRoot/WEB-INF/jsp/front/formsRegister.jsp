<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="titleInfo.meetingTitle" /></title>
 
<style type="text/css">
.requiredtitle {
	color: #ff0000;
}

.nametitle { /*font-size:15px; */
	font-weight: normal;
}

.descriptionfoot {
	font-size: 12px;
	font-weight: lighter;
}

.linehorizontal {
	width: 90%;
	border-bottom-width: 1px;
	border-bottom-style: dashed;
	border-bottom-color: #808080;
}

.inputtext {
	background-color: #FFFFFF;
	background-image: none;
	border: 1px solid #CCCCCC;
	border-radius: 4px 4px 4px 4px;
	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
	color: #555555;
	display: block;
	font-size: 14px;
	height: 34px;
	line-height: 1.42857;
	padding: 6px 12px;
	transition: border-color 0.15s ease-in-out 0s, box-shadow 0.15s
		ease-in-out 0s;
	width: 90%;
}


#pages a,#userform a{
	color:#0000ff;
	text-decoration:none;
}
#pages a:link,#userform a:link{
	color:#0000ff;
}
#pages a:visited,#userform a:visited{
	color:#800080;
	text-decoration:underline;
}
#pages a:hover,#userform a:hover{
	color:#800080;
	text-decoration:underline;
}
#pages a:active,#userform a:active{
	color:#0000ff;
}

</style>
</head>
<body>

	<fieldset>
		<div class="bs-step inv" id="basic-info">
			<div class="panel panel-default">
 
				<div class="panel-heading panel-bar">
					<h3 class="panel-title"><spring:message code="index.attendingAConference"/></h3>
 
				</div>
				<!-- 分页时候加上  style="height:800px;border:1px solid #000000;overflow-pageINdex:hidden;overflow-y:hidden; word-break:break-all;"-->
				<div class="panel-body bs-step-inner" id="userform"></div>
				<div id="pages" style="font-size:12px;"></div> 
			</div>
		</div>
	</fieldset>

	<button id="getformButton" class="btn btn-primary"><spring:message code="Submit"/></button>
	<button id="schedule" type="button" class="btn btn-primary" onClick="toSchedule()"><spring:message code="index.schedule"/></button>
</body>
<!-- jQuery Version 1.11.0 -->
    
	<!-- 首先需要引入plupload的源代码 -->
    <script src="<%=basePath%>js/plupload.full.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/fileuploaduser.js"></script>

	<script type="text/javascript">
	var isHaveFileUpload = false;
	
	//记录需要上传文件的所有控件
    var fileuploaddivs = new Array();
    function adduploadfile(fileInput,fileUploadDiv,browse,filelist,imgfileshow){
    	var formparam = {};
    	formparam["fileInput"] = fileInput;
    	formparam["fileUploadDiv"] = fileUploadDiv;
    	formparam["browse"] = browse;
    	formparam["filelist"] = filelist;
    	formparam["imgfileshow"] = imgfileshow;
    	var len = fileuploaddivs.length;
    	fileuploaddivs[len] = formparam;
    }
    
	//初始化上传文件的控件及方法
	function createfileUpload(){
    	var fileuploadurl ="<%=basePath%>fileload/c/upload";
	   //browse bannerPic filelist imgfileshow为上传图片及预览所需控件id
	   //附加参数
		var multipart_params = {
			optiontype:"metting",
			sign:'${frontMeetingId}',//后期修改成从session中取mettingid
			filecategory:'${user_info.email}'
		};
		//遍历需要上传文件的所有控件
		var len = fileuploaddivs.length;
		for(var index=0;index<len;index++){
			var filediv = fileuploaddivs[index];
			$("#"+filediv.imgfileshow).hide();
			//$("#"+filediv.fileInput).hide();
			initUploader(filediv.browse,filediv.filelist,filediv.imgfileshow,filediv.fileInput,filediv.fileUploadDiv,fileuploadurl,false,0,0,false,multipart_params);
		}
    } 
  
	 
  		$(function(){
  			
  			$("#btntext").click(function(){
  				$('#myModal').modal('show');
  			});
  			
  			$("#getformButton").hide();
  			$("#schedule").hide();
  			
  			selectRole();
      		$("#getformButton").click(function(){
      			if("${front_approveState}" == "2"){
					var r = confirm("<spring:message code='basicInfo.alreadyVerified'/>");
				    if (r == false ||　r == true) {  //已审核通过，不允许修改
				        return false;
				    }
				}else{
					
					if (${roleCode}==6 || ${roleCode}==17 || ${roleCode}==11 || ${roleCode}==22
    						|| ${roleCode}==23 || ${roleCode}==24 || ${roleCode}==5 
    						|| ${roleCode}==16 || ${roleCode}==10 || ${roleCode}==21) {
						var r = confirm("<spring:message code='schedule.confirmHelp'/>");
					    if (r == false) {  //提示提交之后不允许修改;取消，重新选择日程，确定，继续保存
					        return false;
					    }
    				}
					
					var formdata = getform();
	      			if(formdata.length > 0){
	      				$.ajax({
	            			url: "<%=basePath%>dynForm/c/saveFormFront",
	            			type: "POST",
	            			data: "payload="+ JSON.stringify(formdata),
	            			success: function(data){
	            				if (${roleCode}==6 || ${roleCode}==17 || ${roleCode}==11 || ${roleCode}==22
	            						|| ${roleCode}==23 || ${roleCode}==24 || ${roleCode}==5 
	            						|| ${roleCode}==16 || ${roleCode}==10 || ${roleCode}==21) {
	            					alert("<spring:message code='basicInfo.saveSucceed'/>");
	            					window.location.href='<%=basePath%>r/front/reg_success';
	            				} else {
	            				    alert("<spring:message code='basicInfo.goToScheduleTip'/>");
	            					toSchedule();
	            				}
	            			}
	          			});
	      			}else{
	      				alert("<spring:message code='basicInfo.formInvalid'/>");
	      			}
				}
      		});
	    });
	    
	    function selectRole(){
	    	// {meetingId}/{userType}
	    		$.ajax({
        			url: "<%=basePath%>dynForm/r/getFormFront/${frontMeetingId}/${roleCode}",
        			type: "GET",
        			success: function(data){
        				setform(data);
        			}
      			}); 
	    }
	    
	    function toSchedule(){
	    	//保存cookie $.cookie("payloadcookie","123456789",{ expires: 7, secure: false });//JSON.stringify(payloadData)
	    	//window.open();
	    	window.location.href = '<%=basePath%>r/front/schedule';
	    }
	    
	    function setform(data){
	    	$("#userform").html("");//首先清空表单中的内容
	   	 //后台传的数据，渲染页面
	    	var status = data.status + data.info;
	    	var formcontent = data.data;
	    	if(data.status != "0" && data.status != "1"){
	    		$("#getformButton").hide();
	    		$("#userform").html(""+data.info);
	    		return;
	    	}
	    	if("${front_approveState}" == "2"){
	    		$("#getformButton").hide();
	    		if (${roleCode}==6 || ${roleCode}==17 || ${roleCode}==11 || ${roleCode}==22
						|| ${roleCode}==23 || ${roleCode}==24 || ${roleCode}==5 
						|| ${roleCode}==16 || ${roleCode}==10 || ${roleCode}==21) {
	    			$("#schedule").hide();
				}else{
					$("#schedule").show();
				}
	    		
			}else{
				$("#getformButton").show();
	    		$("#schedule").hide();
			}
				
			var formhtml = "";
	    	for(var i in formcontent){
	    		formhtml += "<div class='col-sm-8'><p><span class='nametitle'>";
	    		if (formcontent[i].type=='website') {
	    			
	    			if (formcontent[i].description!='#') {
	    				formhtml += "<a target='_blank' href='" + formcontent[i].description + "'>"+formcontent[i].name+"</a>";
	    			} else {
	    				formhtml += "<label>"+formcontent[i].name+"</label>";
	    			}
	    			
	    		} else {
		    		formhtml += formcontent[i].name;
		    		if(formcontent[i].required == "true"){
		    			formhtml += "<abbr class='requiredtitle' title='必填required'>*</abbr>";
		    		}
	    		}
	    		formhtml += "</span></p>";
	    		/*
	    		<!-- hidden放置通用的属性：fieldid,name,type,userid,mettingid,roleid,required-->
				<!-- 其他属性：description放置在sapn,options动态组装，value动态获取-->
	    		*/
	    		if(formcontent[i].id){
	    			formhtml += "<input type='hidden' name='id' value='"+formcontent[i].id+"'>";
	    		}else{
	    			formhtml += "<input type='hidden' name='id' value=''>";
	    		}
	    		formhtml += "<input type='hidden' name='fieldId' value='"+formcontent[i].fieldId+"'>"+
	    					"<input type='hidden' name='name' value='"+formcontent[i].name+"'>"+
	    					"<input type='hidden' name='type' value='"+formcontent[i].type+"'>"+
	    					"<input type='hidden' name='meetingId' value='"+formcontent[i].meetingId+"'>"+
	    					"<input type='hidden' name='userType' value='"+formcontent[i].userType+"'>"+
	    					"<input type='hidden' name='userId' value='"+formcontent[i].userId+"'>"+
	    					"<input type='hidden' name='required' value='"+formcontent[i].required+"'>"+
	    					"<input type='hidden' name='orderNum' value='"+formcontent[i].orderNum+"'>"+
	    					"<input type='hidden' name='description' value='"+formcontent[i].description+"'>";
	    		 					
	    		if(formcontent[i].type == "text"){
	    			formhtml += " <input class='inputtext' type='";
	    			formhtml += formcontent[i].type +"' value='"+ formcontent[i].value +"'>";
	    		}else if(formcontent[i].type == "address"){
	    			//在formbuilder中website修改成文件上传的功能
	    			var fileInput = "fileInput"+i;
	    			var fileUploadDiv = "fileUploadDiv"+i;
	    			var browse = "browse"+i;
	    			var filelist = "filelist"+i;
	    			var imgfileshow = "imgfileshow"+i;
	    			formhtml += " <input type='text' id='"+fileInput+"' value='" + formcontent[i].value +"' readonly='readonly'>";
	    			formhtml += "<div id='"+fileUploadDiv+"'>";
	    			formhtml += "<button id='"+browse+"'><spring:message code='basicInfo.selectFile'/></button>";
	    			formhtml += "<div id='"+filelist+"'> </div>";
	    			formhtml += "<div id='"+imgfileshow+"'> </div>";
	    			formhtml += "</div>";
	    			isHaveFileUpload = true;
	    			adduploadfile(fileInput,fileUploadDiv,browse,filelist,imgfileshow);
	    			
	    		}else if(formcontent[i].type == "paragraph"){
	    			formhtml += " <textarea class='easyui-validatebox inputtext' required='"+formcontent[i].required+"' name='"+formcontent[i].type+"'>";
	    			formhtml += formcontent[i].value+"</textarea> ";
	    			
	    		}else if(formcontent[i].type == "email"){
	    			//邮箱要做格式验证
	    			formhtml += " <input type='text' class='easyui-validatebox' validType='email' required='"+formcontent[i].required+"' name='";
	    			formhtml += formcontent[i].type +"' value='"+ formcontent[i].value +"'>";
	    		}else if(formcontent[i].type == "number"){
	    			//邮箱要做格式验证
	    			formhtml += " <input class='easyui-numberbox'  precision='0' required='"+formcontent[i].required+"' type='text' ";
	    			formhtml += " value='"+ formcontent[i].value +"'>";
	    			
	    		}else if(formcontent[i].type == "date"){
	    			//判断日期和时间格式，需要借用input class="easyui-datetimebox"或easyui-datebox
	    			//取值，var begindate=$('#begindate').datebox('getValue'); 
	    			//放置默认值 $("#begindate").datebox('setValue','begindate');
	    			formhtml += "<input class='easyui-datebox' editable='false' required='"+formcontent[i].required+"' id='"+formcontent[i].type+i+"' value='"+ formcontent[i].value +"'>";
	    			//$.parser.parse("#"+formcontent[i].type+i);
	    		}else if(formcontent[i].type == "time"){
	    			//时间可以在data上选择也可以让用户自己填写
	    			formhtml += " <input class='easyui-datetimebox' editable='false' required='"+formcontent[i].required+"' id='"+formcontent[i].type+i+"' value='";
	    			formhtml += + formcontent[i].value +"'>";
	    			
	    		}else if(formcontent[i].type == "checkboxes"){
	    			var formoptions = eval(formcontent[i].options);
	    			for(var j in formoptions){
	    				formhtml += "<input type= 'checkbox' name='"+formcontent[i].type+i;
	    				formhtml += "' value='"+ formoptions[j].label+"'";
	    				if(formoptions[j].checked == true){
	    					formhtml += "checked";
	    				}
	    				formhtml += ">";
	    				formhtml += formoptions[j].label;
	    				formhtml += "<br>";
	    			}
	    		}else if(formcontent[i].type == "radio"){
	    			var formoptions = eval(formcontent[i].options);
	    			for(var j in formoptions){
	    				formhtml += "<input type= 'radio' name='"+formcontent[i].type+i;
	    				formhtml += "' value='"+ formoptions[j].label+"'";
	    				if(formoptions[j].checked == true){
	    					formhtml += "checked";
	    				}
	    				formhtml += ">";
	    				formhtml += formoptions[j].label;
	    				formhtml += "<br>";
	    			}
	    		}else if(formcontent[i].type == "dropdown"){
	    			formhtml += "<select>";
	    			var formoptions = eval(formcontent[i].options);
	    			for(var j in formoptions){
	    				formhtml += "<option value='"+formoptions[j].label+"'";
	    				if(formoptions[j].checked == true){
	    					formhtml += "selected";
	    				}
	    				formhtml += ">"+formoptions[j].label+"</option>";
	    			}
	    			formhtml += "</select>";
	    		}
	    		
	    		if(formcontent[i].type=='website'){
	    			formhtml += "</div>";
	    		}else{
	    			formhtml += "</div><div class='col-sm-4 help'><h4><span style='color:#891300;'></span><spring:message code='basicInfo.help'/></h4><p><span>"
			    		+formcontent[i].description+"</span></p></div>";
	    		}
	    		formhtml += "<div class='clearfix'></div><br>";
	    	}
	    	
	    	// easyui初始化css入口,重新将所有控件的css重新初始化，解决input class="easyui-datetimebox"没有样式问题
	    	$("#userform").append(formhtml);
	    	$.parser.parse();
	    	
	    	//$("#userform").html(status+"<br>"+formcontent[0].field_id+formcontent[0].name+formcontent[0].type);
	    	if(isHaveFileUpload){
	    		createfileUpload();//fileuploaddiv,new array()
	    	}
	    	// pagepart();
	    }
	    
	    
	    
	    function getform(){
	    	var formshow = $("#userform").find(".col-sm-8");
	    	/* $.each(formshow,function(n,value) {
	    		var hiddens = value.find("input[type='hidden']");
	    	}); */
	    	var length = formshow.length;
	    	var firstform = formshow.eq(0);
	    	var formdatas = new Array();
	    	for(var index=0;index<formshow.length;index++){
	    		var formparam = {};
	    		var required = firstform.find("input[name='required']").eq(0).val();
	    		//筛选input[type='hidden']，获取需要传递到后台的数据
	    		var hiddens = firstform.find("input[type='hidden']");
	    		if(hiddens.length <= 0){
	    			firstform = formshow.eq(index+1);
	    			continue;
	    		}
	    		var firsthidden = hiddens.eq(0);
	    		for(var hiddensi=0;hiddensi<hiddens.length;hiddensi++){
	    			var name = firsthidden.attr("name");
	    			var value = firsthidden.attr("value");
	    			if(name){
	    				formparam[name] = value;//formparam.put(name,value);
	    			}
	    			firsthidden = hiddens.eq(hiddensi+1);
	    		}
	    		//然后获取每种情况下的value值和option;type='text'赋option="",radio,checkbox和select需要遍历封装value和optinsf
	    		var inputValue = "";
	    		var inputOptions = "";
	    		if(firstform.find("input[type='text']").length > 0){
	    			inputValue = firstform.find("input[type='text']").first().val();
	    		}else if(firstform.find("input[type='file']").length > 0){
	    			//file操作需要调用其他方法
	    			inputValue = firstform.find("input[type='file']").first().val();
	    		}else if(firstform.find("textarea").length > 0){
	    			// paragraph: textarea
	    			inputValue = firstform.find("textarea").first().val();
	    		}else if(firstform.find("input[id='data"+index+"']").length > 0){
	    			//date
	    			var dataBox = "#date"+index;
	    			inputValue=$(dataBox).datebox('getValue');
	    			
	    		}else if(firstform.find("input[id='time"+index+"']").length > 0){
	    			//date
	    			var dataBox = "#time"+index;
	    			inputValue=$(dataBox).datebox('getValue'); 
	    			
	    		}else if(firstform.find("input[type='radio']").length > 0){
	    			var formRadio = firstform.find("input[type='radio']");
	    			var optiondatas = new Array();
	    			var inputRadio = formRadio.eq(0);
	    			for(var radioindex=0;radioindex<formRadio.length;radioindex++){
	    				var optioncheck = inputRadio.is(":checked");//false,true
	    				var optioni = {};
	    				optioni["label"] = inputRadio.attr("value");
	    				optioni["checked"] = optioncheck;
	    				if(optioncheck){
	    					if(radioindex == 0 || inputValue==""){
	    						inputValue += inputRadio.attr("value");
	    					}else{
	    						inputValue += ";?;"+inputRadio.attr("value");
	    					}
	    				}
	    				optiondatas[radioindex] = optioni;
	    				inputRadio = formRadio.eq(radioindex+1);
	    			}
	    			inputOptions = optiondatas;
	    			
	    		}else if(firstform.find("input[type='checkbox']").length > 0){
	    			var formRadio = firstform.find("input[type='checkbox']");
	    			var optiondatas = new Array();
	    			var inputRadio = formRadio.eq(0);
	    			for(var radioindex=0;radioindex<formRadio.length;radioindex++){
	    				var optioncheck = inputRadio.is(":checked");//false,true
	    				var optioni = {};
	    				optioni["label"] = inputRadio.attr("value");
	    				optioni["checked"] = optioncheck;
	    				if(optioncheck){
	    					if(radioindex == 0 || inputValue==""){
	    						inputValue += inputRadio.attr("value");
	    					}else{
	    						inputValue += ",?,"+inputRadio.attr("value");
	    					}
	    				}
	    				optiondatas[radioindex] = optioni;
	    				inputRadio = formRadio.eq(radioindex+1);
	    			}
	    			inputOptions = optiondatas;
	    		
	    		}else if(firstform.find("select").length > 0){
	    			//inputValue = firstform.find("select").val();
	    			var formRadio = firstform.find("select").children();
	    			var optiondatas = new Array();
	    			var inputRadio = formRadio.eq(0);
	    			for(var radioindex=0;radioindex<formRadio.length;radioindex++){
	    				var optioncheck = inputRadio.is(":selected");//false,true
	    				var optioni = {};
	    				optioni["label"] = inputRadio.attr("value");
	    				optioni["checked"] = optioncheck;
	    				if(optioncheck){
	    					if(radioindex == 0 || inputValue==""){
	    						inputValue += inputRadio.attr("value");
	    					}else{
	    						inputValue += ";?;"+inputRadio.attr("value");
	    					}
	    				}
	    				optiondatas[radioindex] = optioni;
	    				inputRadio = formRadio.eq(radioindex+1);
	    			}
	    			inputOptions = optiondatas;
	    		}else if(formparam.type == "website"){
	    			required = "false";
	    		}
	    		if(inputValue == "" && required == "true"){
	    			return [];
	    		}
	    		formparam["value"] = inputValue;
	    		formparam["options"] = inputOptions;
	    		var len = formdatas.length;
	    		formdatas[len] = formparam;
	    		firstform = formshow.eq(index+1);
	    	}
	    	return formdatas;
	    	/*
	    		<!-- hidden放置通用的属性：fieldid,name,type,userid,mettingid,roleid,required-->
				<!-- 其他属性：description放置在sapn,options动态组装，value动态获取-->
	    	*/
	    }
  	</script>
  	
  	
  	
  	<script language="javascript"> 
var obj = document.getElementById("userform");  //获取内容层 
var pages = document.getElementById("pages");         //获取翻页层 
var pgindex=1;
var allpages = 1;//当前页 
/*window.onload = function()                             //重写窗体加载的事件 
{ 
    var allpages = Math.ceil(parseInt(obj.scrollHeight)/parseInt(obj. offsetHeight));//获取页面数量 
    pages.innerHTML = "<b>共"+allpages+"页</b>";     //输出页面数量 
    for (var i=1;i<=allpages;i++){ 
        pages.innerHTML += "<a href=\"javascript:showPage('"+i+"');\">第"+i+"页</a> "; 
//循环输出第几页 
    } 
    pages.innerHTML += "      <a href=\"javascript:gotopage('-1');\">上一页</a>  <a href=\"javascript:gotopage('1');\">下一页</a>" 
}*/

function pagepart(){
	obj = document.getElementById("userform");  //获取内容层 
	 
	pages = document.getElementById("pages");         //获取翻页层 
	// var allpages = Math.ceil(parseInt(obj.scrollHeight)/parseInt(obj. offsetHeight));//获取页面数量 
	allpages = Math.ceil(parseInt($("#userform").outerHeight(true))/parseInt($("#userform").height()));//获取页面数量 
	    pages.innerHTML = "<b>共"+allpages+"页</b>";     //输出页面数量 
	    for (var i=1;i<=allpages;i++){ 
	        pages.innerHTML += "<a href=\"javascript:showPage("+i+");\">第"+i+"页</a> "; 
	//循环输出第几页 
	    } 
	    pages.innerHTML += "      <a href=\"javascript:gotopage('-1');\">上一页</a>  <a href=\"javascript:gotopage('1');\">下一页</a>" 

}

function gotopage(value){ 
try{ 
 value=="-1"?showPage(pgindex-1):showPage(pgindex+1); 
 }catch(e){ 
  
 } 
} 
function showPage(pageINdex) 
{ 
    if(pageINdex < 1 || pageINdex > allpages){
    	return;
    }
    obj.scrollTop=(pageINdex-1)*parseInt(obj.offsetHeight); //根据高度，输出指定的页 
    pgindex=pageINdex; 
} 
</script> 
</html>