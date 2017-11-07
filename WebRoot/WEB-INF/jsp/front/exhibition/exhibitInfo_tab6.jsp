<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<div style="height:500px">
<table class="swtable" style="margin-top:0;">
	<c:forEach items= "${exhibits}" var="exhibit" varStatus="status">
		<tbody id="exhibit${status.index }" style="border-bottom:1px dashed #8CC63F;"> 
			<tr>		
				<td><input type="hidden" name="exhibits[${status.index }].exhibitId" value="${exhibit.exhibitId }"></td>
			</tr>
			<tr>
				<td><font style="color:red;">*</font><spring:message code="exhibition.exhibitName"/></td>
				<td><input type="text" class="form-control" name="exhibits[${status.index }].exhibitName" value="${exhibit.exhibitName }"></td>
				<td><spring:message code="exhibition.standard"/></td>
				<td><input type="text" class="form-control" name="exhibits[${status.index }].exhibitSpecifications" value="${exhibit.exhibitSpecifications }"></td>
			</tr>
			<tr>
				<td><spring:message code="exhibition.amount"/></td>
				<td><input type="text" class="form-control" name="exhibits[${status.index }].exhibitMount" value="${exhibit.exhibitMount }"></td>
				<td><spring:message code="exhibition.unitPrice"/></td>
				<td><input type="text" class="form-control" name="exhibits[${status.index }].exhibitPrice" value="${exhibit.exhibitPrice }"></td>
			</tr>
			<tr>
				<!-- 照片 -->
				<td><spring:message code="exhibition.itemPhoto"/></td>
				<td>
					<div id="fileuploaddiv${status.index }">
						<div id="photofilelist${status.index }"></div>
							<div id="photoimgfileshow${status.index }">
								<img src="<%=basePath%>${exhibit.exhibitImage }" alt="image">
							</div>
							<a id="photobrowse${status.index }" href="javascript:;">[<spring:message code="basicInfo.selectFile" />]</a>
							<input id="photoinput${status.index }" name="exhibits[${status.index }].exhibitImage" value="${exhibit.exhibitImage }" type="hidden">
					</div>
				</td>
			</tr>
			<tr>
				<td><spring:message code="exhibition.itemSummary"/></td>
				<td><textarea name="exhibits[${status.index }].exhibitIntro" class="form-control">${exhibit.exhibitIntro }</textarea></td>
				<td><spring:message code="exhibition.comments"/></td>
				<td><textarea name="exhibits[${status.index }].exhibitOther" class="form-control">${exhibit.exhibitOther }</textarea></td>
			</tr>
		</tbody>
	</c:forEach>
	<tr>	
		<td style="padding-top:10px;"><input type="button" class="swtbtn" id="showMore" value="<spring:message code="exhibition.moreItem"/>"/></td>
		<td style="padding-top:10px;"><input type="button" class="swtbtn" id="showOne" value="<spring:message code="exhibition.foldItems"/>"/></td>
	</tr>
</table>	
</div>
<script type="text/javascript" src="<%=basePath%>js/fileuploaduser.js"></script>
<script src="<%=basePath%>js/plupload.full.min.js"></script>
<script type="text/javascript"> 
	var fileuploadurl ="<%=basePath%>fileload/c/upload";
	//browse bannerPic filelist imgfileshow,上传图片的附加参数,sign需要修改成会议的唯一标识
	var multipart_params = {
		optiontype:"metting",
		sign:'${frontMeetingId}',//后期修改成从session中取mettingid
		filecategory:'${user_info.email}/exhibitions'
	};
	initUploader("photobrowse0","photofilelist0","photoimgfileshow0","photoinput0","fileuploaddiv0",fileuploadurl,false,390,567,true,multipart_params);
	initUploader("photobrowse1","photofilelist1","photoimgfileshow1","photoinput1","fileuploaddiv1",fileuploadurl,false,390,567,true,multipart_params);
	initUploader("photobrowse2","photofilelist2","photoimgfileshow2","photoinput2","fileuploaddiv2",fileuploadurl,false,390,567,true,multipart_params);
	initUploader("photobrowse3","photofilelist3","photoimgfileshow3","photoinput3","fileuploaddiv3",fileuploadurl,false,390,567,true,multipart_params);
	initUploader("photobrowse4","photofilelist4","photoimgfileshow4","photoinput4","fileuploaddiv4",fileuploadurl,false,390,567,true,multipart_params);
	//只显示第一个展品
	showOne();
	
	var showNum = 1;
	$('#showMore').on('click', function() {
		$('#exhibit'+showNum).show();
		showNum++;
	});
	
	$('#showOne').on('click', function() {
		showOne();
	});
	
	function showOne() {
		$('#exhibit1').hide();
		$('#exhibit2').hide();
		$('#exhibit3').hide();
		$('#exhibit4').hide();
		showNum = 1;
	};
	
	//分步验证
	function checkTab5(){
		return true;
	}
</script>