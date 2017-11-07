<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<table class="swtable">
	<h3><spring:message code="exhibition.tab4.info"/></h3>
	<c:forEach items= "${booths}" var="booth" varStatus="status">
		<tr class="boothLintelNames">
			<td>
				${booth.boothName }
			</td>
			<td>
				<textarea id="booths[${status.index }].boothLintelName" name="booths[${status.index }].boothLintelName">${booth.boothLintelName }</textarea>
			</td>
		</tr>
	</c:forEach>
</table>	
<img src="<%=basePath %>images/exhibition/u212.png"/>
<script type="text/javascript">
//分步验证
function checkTab4(){
	return true;
}
</script>