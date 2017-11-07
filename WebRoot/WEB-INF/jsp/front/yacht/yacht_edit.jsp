<%@  page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<jsp:include page="top.jsp"/>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title></title>
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
		
		$(document).ready(function(){
			var msg = "${map.msg}";
			if(msg == '1'){
				layer.alert("<spring:message code='yacht.edit.success' />",1);
			}
			if(msg == '2'){
				layer.alert("<spring:message code='yacht.edit.failer' />",8);
			}
			var fileuploadurl ="<%=basePath%>fileload/c/upload";
			//browse bannerPic filelist imgfileshow,上传图片的附加参数,sign需要修改成会议的唯一标识
			var multipart_params = {
				optiontype:"type",
				sign:"sign",
				filecategory:"certPic1",
			};
			initUploader("photobrowse","photofilelist","photoimgfileshow","photoinput","fileuploaddiv",fileuploadurl,false,390,567,true,multipart_params);	
			
			var fileuploadur2 ="<%=basePath%>fileload/c/upload";
			//browse bannerPic filelist imgfileshow,上传图片的附加参数,sign需要修改成会议的唯一标识
			var multipart_params2 = {
				optiontype:"type",
				sign:"sign",
				filecategory:"certPic2",
			};
			initUploader("photobrowse2","photofilelist2","photoimgfileshow2","photoinput2","fileuploaddiv2",fileuploadur2,false,390,567,true,multipart_params2);		
		});	
		
		function formSubmit() {
			if (!$("#fi").form('validate')) {
				return false;
			}else{
				document.form1.submit();
			}
			
		}
    </script>
</head>

<body>
	<div align="center">
		<table width="1024" border="0" align="center" cellpadding="0" cellspacing="0" >
		<tbody>
			<tr>
				<td>
					<form id="fi" action="<%=basePath%>confYachtUser/editUser" name="form1" method="post" >
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
												<c:if test="${map.user.userType=='1'}">
													<spring:message code="yacht.buyer"/>
												</c:if>
												<c:if test="${map.user.userType=='2'}">
													<spring:message code="yacht.guest"/>
												</c:if>
												<c:if test="${map.user.userType=='3'}">
													<spring:message code="yacht.visitor"/>
												</c:if>
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
												<input id="email" name="email" class="easyui-textbox" value="${map.user.email}" style="width:250px"  readonly/>
											</td>
											<td></td>
										</tr>
										<tr>
											<td>&nbsp;</td>
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
												<input id="cname" type="text" value="${map.user.cname}" class="easyui-textbox" name="cname" style="width:250px"
												data-options="required:true" />
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
												<c:if test="${map.user.sex=='1'}">
													<input type="radio" class="ml-10" name="sex" value="1" checked/><spring:message code="yacht.male"/> 
												</c:if>
												<c:if test="${map.user.sex!='1'}">
													<input type="radio" class="ml-10" name="sex" value="1" /><spring:message code="yacht.male"/>
												</c:if>
												<c:if test="${map.user.sex=='2'}">
													<input type="radio" class="ml-10" name="sex" value="2" checked/><spring:message code="yacht.female"/>
												</c:if>
												<c:if test="${map.user.sex!='2'}">
													<input type="radio" class="ml-10" name="sex" value="2" /><spring:message code="yacht.female"/>
												</c:if>
											</td>
										</tr>
										<tr>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td>
												<span class="required">*</span> <label><spring:message code="yacht.userCard"/></label>
											</td>
											<td>
												<input id="certValue" type="text" value="${map.user.certValue}" class="easyui-textbox" name="certValue" style="width:250px" data-options="required:true" />
											</td>
										</tr>
										<tr>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td>
												<span class="required">*</span> <label><spring:message code="yacht.birth"/></label>
											</td>
											<td>
												<input id="birth" name="birth" value="${map.user.birth}" type="text" class="easyui-datebox" required="required"></input>  
											</td>
										</tr>

										<tr>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td><span class="required">*</span> <label><spring:message code="yacht.nation"/></label>
											</td>
											<td>
												<input id="nation" name="nation" value="${map.user.nation}" class="easyui-combobox" editable="false" style="width:250px" data-options="valueField:'code',textField:'name', url:'dict/r/nation',method:'get',required:'true'" />
											</td>
										</tr>
										<tr>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td>
												<span class="required">*</span> <label><spring:message code="yacht.company"/></label>
											</td>
											<td>
												<input id="company" type="text" name="company" value="${map.user.company}" class="easyui-textbox" style="width:250px" data-options="required:true" /></td>
										</tr>
										<tr>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td>
												<span class="required">*</span> <label><spring:message code="yacht.position"/></label></td>
											<td>
												<input id="position" type="text" name="position" value="${map.user.position}" class="easyui-textbox" style="width:250px" data-options="required:true" /></td>
										</tr>
										<tr>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td>
												<span class="required">*</span> <label><spring:message code="yacht.mobile"/></label></td>
											<td>
												<input id="mobile" type="text" name="mobile" value="${map.user.mobile}" class="easyui-textbox" style="width:250px" data-options="required:true,validType:'mobile'" /></td>
										</tr>
										<tr>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td>
												<span class="required">*</span><label><spring:message code="yacht.address"/></label>
											</td>
											<td>
												<input id="address" type="text" name="address" value="${map.user.address}" class="easyui-textbox" style="width:250px" data-options="required:true" /></td>
										</tr>
										<tr>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td>
												<span class="required">*</span> <label><spring:message code="yacht.card1"/></label>
											</td>
											<td>
												<div id="fileuploaddiv">
													<div id="photofilelist"></div>
													<div id="photoimgfileshow">
														<img src="${map.user.certPic1}" alt="image">
													</div>
													<a id="photobrowse" href="javascript:;" style="cursor: pointer;"><spring:message code="yacht.choose.card1"/></a> <input
														id="photoinput" name="certPic1" value="${map.user.certPic1}" type="hidden">
												</div>
											</td>	
										</tr>
										<tr>
											<td><span class="required">*</span> <label><spring:message code="yacht.card2"/></label></td>
										</tr>
										<tr>
											<td>&nbsp;</td>
											<td>
												<div id="fileuploaddiv2">
													<div id="photofilelist2"></div>
													<div id="photoimgfileshow2">
														<img src="${map.user.certPic2}" alt="image">
													</div>
													<a id="photobrowse2" href="javascript:;" style="cursor: pointer;"><spring:message code="yacht.choose.card2"/></a> <input
														id="photoinput2" name="certPic2" value="${map.user.certPic2}" type="hidden">
												</div>
											</td>
										</tr>
										<tr>
											<td>&nbsp;</td>
										</tr>
									</table>
									<table>

									</table>
									<table id="tab11">
										<tr>
											<td><label><spring:message code="yacht.buy.info"/></label></td>
										</tr>
										<tr>
											<td>
												<c:if test="${map.user.buyInfo=='1'}">
													<input type="radio" class="ml-10" name="buyInfo" value="1" checked/><spring:message code="yacht.yes"/>
												</c:if>
												<c:if test="${map.user.buyInfo!='1'}">
													<input type="radio" class="ml-10" name="buyInfo" value="1" /><spring:message code="yacht.yes"/>
												</c:if>
												<c:if test="${map.user.buyInfo=='2'}">
													<input type="radio" class="ml-10" name="buyInfo" value="2" checked/><spring:message code="yacht.no"/>
												</c:if>
												<c:if test="${map.user.buyInfo!='2'}">
													<input type="radio" class="ml-10" name="buyInfo" value="2" /><spring:message code="yacht.no"/>
												</c:if>
											</td>
										</tr>
										<tr>
											<td>&nbsp;</td>
										</tr>
									</table>
									<c:if test="${map.user.userType=='2'}">
										<table id="tab2" >
											<tr>
												<td><label><spring:message code="yacht.push.info"/></label></td>
											</tr>
											<tr>
												<td>
													<c:if test="${map.user.pushInfo=='1'}">
														<input type="radio" class="ml-10" name="pushInfo" value="1" checked/><spring:message code="yacht.yes"/>
													</c:if>
													<c:if test="${map.user.pushInfo!='1'}">
														<input type="radio" class="ml-10" name="pushInfo" value="1" /><spring:message code="yacht.yes"/>
													</c:if>
													<c:if test="${map.user.pushInfo=='2'}">
														<input type="radio" class="ml-10" name="pushInfo" value="2" checked/><spring:message code="yacht.no"/>
													</c:if>
													<c:if test="${map.user.pushInfo!='2'}">
														<input type="radio" class="ml-10" name="pushInfo" value="2" /><spring:message code="yacht.no"/>
													</c:if>
												</td>
											<tr>
												<td>&nbsp;</td>
											</tr>
										</table>
										<table id="tab22" >
											<tr>
												<td>
													<span class="required">*</span> <label><spring:message code="yacht.arrive.num"/></label>
												</td>
												<td>
													<input id="arriveNum" type="text" name="arriveNum" value="${map.user.arriveNum}" class="easyui-textbox" style="width:250px" />
												</td>
											</tr>
											<tr>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td>
													<span class="required">*</span> <label><spring:message code="yacht.arrive.time"/></label>
												</td>
												<td>
													<input id="arriveTime" type="text" name="arriveTime" value="${map.user.arriveTime}" class="easyui-datebox" style="width:250px" />
												</td>
											</tr>
											<tr>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td>
													<span class="required">*</span> <label><spring:message code="yacht.pick.location"/></label>
												</td>
												<td>
													<input id="pickLocation" type="text" name="pickLocation" value="${map.user.pickLocation}" class="easyui-textbox" style="width:250px" />
												</td>
											</tr>
											<tr>
												<td>&nbsp;</td>
											</tr>
										</table>			
									</c:if>
										
									<c:if test="${map.user.userType=='3'}">
										<table id="tab3">
											<tr>
												<td>
													<label><spring:message code="yacht.entrance"/></label>
												</td>
											</tr>
											<tr>
												<td>
													<c:if test="${map.user.entrance=='1'}">
														<input type="radio" class="ml-10" name="entrance" value="1" checked /><spring:message code="yacht.yes"/>
													</c:if>
													<c:if test="${map.user.entrance!='1'}">
														<input type="radio" class="ml-10" name="entrance" value="1" /><spring:message code="yacht.yes"/>
													</c:if>
													<c:if test="${map.user.entrance=='2'}">
														<input type="radio" class="ml-10" name="entrance" value="2" checked/><spring:message code="yacht.no"/>
													</c:if>
													<c:if test="${map.user.entrance!='2'}">
														<input type="radio" class="ml-10" name="entrance" value="2" /><spring:message code="yacht.no"/>
													</c:if>
												</td>
											</tr>
											<tr>
												<td>&nbsp;</td>
												<td></td>
											</tr>
										</table>
									</c:if>
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