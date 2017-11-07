<%@  page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<jsp:include page="top.jsp"/>
<html>
<head>
	<base href="<%=basePath%>">
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/style.css" media="screen">
	<link href="<%=basePath%>/css/cn.css" rel="stylesheet" type="text/css" />
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>public/ui/themes/default/easyui.admin.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>public/ui/jquery-ui.css">
		
	<script type="text/javascript" src="<%=basePath%>public/ui/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>public/layer/layer.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>public/ui/jquery.easyui.min.js"></script>
	<script src="<%=basePath%>js/jquery.databox.ex.js"></script>
	<script type="text/javascript" src="<%=basePath%>public/ui/plugins/jquery.validate.ex.js"></script>
	<script type="text/javascript" src="<%=basePath%>public/ui/jquery.easyui.plus.js"></script>
	<script type="text/javascript" src="<%=basePath%>public/ui/locale/easyui-lang-zh_CN.js"></script>
	   
	<link rel="icon" href="<%=basePath%>favicon.ico" type="image/x-icon" />
	<link rel="shortcut icon" href="<%=basePath%>favicon.ico" type="image/x-icon" />
	<link rel="icon" href="<%=basePath%>favicon.ico" type="image/x-icon" />
	<link rel="shortcut icon" href="<%=basePath%>favicon.ico" type="image/x-icon" />
		
	
	<script type="text/javascript" src="<%=basePath%>js/fileuploaduser.js"></script>
	<script src="<%=basePath%>js/plupload.full.min.js"></script>
	
	<script type="text/javascript" src="<%=basePath%>/js/layer.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>/js/lab.min.js"></script>
	
	
	<link type="text/css" rel="stylesheet" href="<%=basePath%>public/layer/skin/layer.css" id="skinlayercss">
	<script>
		var fileuploadurl ="<%=basePath%>fileload/c/upload";
		//browse bannerPic filelist imgfileshow,上传图片的附加参数,sign需要修改成会议的唯一标识
		var multipart_params = {
			optiontype:"type",
			sign:"sign",
			filecategory:"certPic1"
		};
		
		var fileuploadur2 ="<%=basePath%>fileload/c/upload";
		//browse bannerPic filelist imgfileshow,上传图片的附加参数,sign需要修改成会议的唯一标识
		var multipart_params2 = {
			optiontype:"type",
			sign:"sign",
			filecategory:"certPic2"
		};
		
		var uploader1 = "";
		var uploader2 = "";
		
	$(document).ready(function(){
		uploader1 = initUploader("photobrowse","photofilelist","photoimgfileshow","photoinput","fileuploaddiv",fileuploadurl,false,390,567,true,multipart_params);	
		uploader2 = initUploader("photobrowse2","photofilelist2","photoimgfileshow2","photoinput2","fileuploaddiv2",fileuploadur2,false,390,567,true,multipart_params2);		
	});	
	
	
		function typeChange() {
			var oCheck;
			var radio = document.getElementsByName("userType");
			for ( var i = 0; i < radio.length; i++) {
				if (radio[i].checked == true) {
					oCheck = radio[i].value;
					break;
				}
			}
			var table2 = document.getElementById("tab2");
			var table22 = document.getElementById("tab22");
			var table3 = document.getElementById("tab3");
			if (oCheck == '2') {	
				table2.style.display = "";
				table22.style.display = "";
				table3.style.display = "none";
			} else if(oCheck == '3'){
				table2.style.display = "none";
				table22.style.display = "none";
				table3.style.display = "";
			} else{
				table2.style.display = "none";
				table22.style.display = "none";
				table3.style.display = "none";
			}
		}
		
		function saveUserinfo(){
			$("#email").validatebox({required:true}); 
            $("#cname").validatebox({required:true});
            return true;
		}
		
		function onLogin() {
			$.layer({
		        type: 2,
		        title: '<spring:message code="yacht.win.login" />',
		        maxmin: true,
		        shadeClose: true, //开启点击遮罩关闭层
		        area : ['400px' , '250px'],
		        offset : ['100px', ''],
		        iframe: {src: 'r/front/yacht/yacht_log'}
		    });
		}
		
		function formSubmit() {
			if (!$("#fi").form('validate')) {
				return false;
			}
			var msg = "";
			var form = $("#fi");
			$.ajax({
				  type:"post",
				  url:'confYachtUser/addUser',
				  data:form.serialize(),
				  success:function(map){ 
					  msg = map.msg;
					 
					  if(msg=='3'){
						  //layer.alert("<spring:message code='yacht.register.success'/>",1);
						  $("#f2 #id").val(map.user.id);
						  $("#f2").submit(); 
					  }
					  if(msg=='1'){
						  layer.alert("<spring:message code='yacht.email.registered'/>",8);
					  }
					  if(msg=='4'){
						  layer.alert("<spring:message code='yacht.register.failer'/>",8);
					  }
				}
			});
		}
		

    </script>
</head>

<body style="background-color: white;">
	<div align="center">
		<table width="1024" border="0" align="center" cellpadding="0" cellspacing="0" >
		<tbody>
			<tr>
				<td>
					<form id="f2" action="confYachtUser/jumpToYacht_suc" method="post" name="form2"  >
						<input type="hidden" name="id" value="" id="id">
					</form>
					<form id="fi" action=""  name="form1"  >
						<div class="section">
							<div class="new"><spring:message code="yacht.register"/></div>
							<div class="login">
								<a onclick="onLogin()" style="cursor: pointer;"><spring:message code="yacht.login"/></a>
							</div>
							<div style="float: right;">
								<spring:message code="yacht.suggest.browser"/>
							</div>
						</div>
						<div class="section">
							<div class="title pt-5"><spring:message code="yacht.account.info"/></div>
							<div class="account">
								<table id="tab0">
									<tr>
										<td>
											<span class="required">*</span> 
											<label><spring:message code="yacht.customer.type"/></label>
										</td>
										<td>
											<input type="radio" class="ml-10" name="userType" value="1" onchange="typeChange()" checked /><spring:message code="yacht.buyer"/>
											<input type="radio" class="ml-10" name="userType" value="2" onchange="typeChange()" /><spring:message code="yacht.guest"/> 
											<input type="radio" class="ml-10" name="userType" value="3" onchange="typeChange()"  /><spring:message code="yacht.visitor"/>
										</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>
											<span class="required">*</span> 
											<label><spring:message code="yacht.email"/></label>
										</td>
										<td>
											<input id="email" name="email" class="easyui-textbox" style="width:250px" data-options="required:true,validType:'email'"/>
										</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>
											<span class="required">*</span>
											<label><spring:message code="yacht.password"/></label>
										</td>
										<td>
											<input type="password" id="password" name="password" class="easyui-textbox" style="width:250px" data-options="required: true" validType="length[6,15]" style="width:250px"/>
										</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>
											<span class="required">*</span>
											<label><spring:message code="yacht.confirm.password"/></label>
										</td>
										<td>
											<input type="password" id="comfirmPassword" name="comfirmPassword" class="easyui-textbox" data-options="required: true" style="width:250px" validType="equals['#password']" />
										</td>
									</tr>
								</table>
							</div>
						</div>
						<div class="section">
							<div class="title"><spring:message code="yacht.user.info"/></div>
							<div class="person">
								<table id="tab1" style="diplay:">
									<tr>
										<td>
											<span class="required">*</span>
											<label><spring:message code="yacht.userName"/></label>
										</td>
										<td>
											<input id="cname" type="text" class="easyui-textbox" name="cname" style="width:250px" data-options="required:true"  validType="minlength"/>
										</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>
											<span class="required">*</span>
											<label><spring:message code="yacht.gender"/></label>
										</td>
										<td>
											<input type="radio" class="ml-10" name="sex" value="1" checked/><spring:message code="yacht.male"/>
											<input type="radio" class="ml-10" name="sex" value="2" /><spring:message code="yacht.female"/>
										</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>
											<span class="required">*</span>
											<label><spring:message code="yacht.userCard"/></label>
										</td>
										<td>
											<input id="certValue" type="text" class="easyui-textbox" name="certValue" style="width:250px" data-options="required:true"/>
										</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>
											<span class="required">*</span>
											<label><spring:message code="yacht.birth"/></label>
										</td>
										<td>
											<input id="birth" name="birth" type="text" class="easyui-datebox" style="width: 250px;" required="required"></input>  
										</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>
											<span class="required">*</span>
											<label><spring:message code="yacht.nation"/></label>
										</td>
										<td>
											<input id="nation" class="easyui-combobox" name="nation" editable="false" style="width:250px"
												data-options="valueField:'code',textField:'name', url:'<%=basePath %>dict/r/nation',method:'get',required:'true'" />
										</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>
											<span class="required">*</span>
											<label><spring:message code="yacht.company"/></label>
										</td>
										<td>
											<input id="company" type="text" name="company" class="easyui-textbox" style="width:250px" data-options="required:true" />
										</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>
											<span class="required">*</span>
											<label><spring:message code="yacht.position"/></label></td>
										<td>
											<input id="position" type="text" name="position" class="easyui-textbox" style="width:250px" data-options="required:true" />
										</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>
											<span class="required">*</span>
											<label><spring:message code="yacht.mobile"/></label></td>
										<td>
											<input id="mobile" type="text" name="mobile" class="easyui-textbox" style="width:250px" data-options="required:true,validType:'mobile'" />
										</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>
											<span class="required">*</span>
											<label><spring:message code="yacht.address"/></label>
										</td>
										<td>
											<input id="address" type="text" name="address" class="easyui-textbox" style="width:250px" data-options="required:true" />
										</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>
											<span class="required">*</span>
											<label><spring:message code="yacht.card1"/></label>
										</td>
											<td>
												<div id="fileuploaddiv">
													<div id="photofilelist"></div>
													<div id="photoimgfileshow">
														<img src=" " alt="image">
													</div>
													<a id="photobrowse" href="javascript:;"><spring:message code="yacht.choose.card1"/></a> 
													<input id="photoinput" name="certPic1" value="" type="hidden">
												</div>
											</td>
										</tr>
									<tr>
										<td>
											<span class="required">*</span>
											<label><spring:message code="yacht.card2"/></label>
										</td>
										<td>
											 <div id="fileuploaddiv2">
													<div id="photofilelist2"></div>
													<div id="photoimgfileshow2">
														<img src=" " alt="image">
													</div>
													<a id="photobrowse2" href="javascript:;" style="cursor: pointer;"><spring:message code="yacht.choose.card2"/></a> 
													<input id="photoinput2" name="certPic2" value="" type="hidden">
												</div>
										</td>
									</tr>
								</table>
								<table id="tab22" style="display:none">
									<tr>
										<td>
											<label><spring:message code="yacht.arrive.num"/></label>
										</td>
										<td>
											<input id="arriveNum" type="text" name="arriveNum" class="easyui-textbox" style="width:250px" />
										</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>
											<label><spring:message code="yacht.arrive.time"/></label>
										</td>
										<td>
											<input id="arriveTime" type="text" name="arriveTime" class="easyui-datebox" style="width:250px"/>
										</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>
											<label><spring:message code="yacht.pick.location"/></label>
										</td>
										<td>
											<input id="pickLocation" type="text" name="pickLocation" class="easyui-textbox" style="width:250px"/>
										</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
									</tr>
								</table>
								<table id="tab11">
									<tr>
										<td>
											<label><spring:message code="yacht.buy.info"/></label>
										</td>
									</tr>
									<tr>
										<td>
											<input type="radio" class="ml-10" name="buyInfo" value="1" /><spring:message code="yacht.yes"/>
											<input type="radio" class="ml-10" name="buyInfo" value="2" /><spring:message code="yacht.no"/>
										</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
									</tr>
								</table>
								<table id="tab2" style="display:none">
									<tr>
										<td><label><spring:message code="yacht.push.info"/></label></td>
									</tr>
									<tr>
										<td>
											<input type="radio" class="ml-10" name="pushInfo" value="1" /><spring:message code="yacht.yes"/>
											<input type="radio" class="ml-10" name="pushInfo" value="2" /><spring:message code="yacht.no"/>
										</td>
									<tr>
										<td>&nbsp;</td>
									</tr>
								</table>

								<table id="tab3" style="display:none">
									<tr>
										<td>
											<label><spring:message code="yacht.entrance"/></label>
										</td>
									</tr>
									<tr>
										<td>
											<input type="radio" class="ml-10" name="entrance" value="1" /><spring:message code="yacht.yes"/>
											<input type="radio" class="ml-10" name="entrance" value="2" /><spring:message code="yacht.no"/>
										</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td></td>
									</tr>
								</table>
							</div>
						<div class="section footer">
							<input type="submit" name="btnSubmit" value="<spring:message code='yacht.submit'/>" data-value="<spring:message code='yacht.submit'/>" onclick="formSubmit()" />
						</div>
					</div>
				</form>
				</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>
<jsp:include page="bottom.jsp"/>