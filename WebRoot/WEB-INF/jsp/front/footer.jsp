<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<div id="footer">
	<div>
	<p><font style="color:#C0A17B"><spring:message code="footer.supportUnits"/></font>
		<a><spring:message code="footer.nationalHealth"/>&nbsp;|&nbsp;</a>
		<a><spring:message code="footer.ChinaFood"/>&nbsp;|&nbsp;</a>
		<a><spring:message code="footer.federalOfLiteraryAndArt"/></a>
	</p>
	<p>	
		<a><spring:message code="footer.liasionOffice"/>&nbsp;|&nbsp;</a>
		<a><spring:message code="footer.adminOfTraditionalMedicine"/>&nbsp;|&nbsp;</a>
		<a><spring:message code="footer.foreignAffairs"/></a>
	</p>
	<p>		
		<a><spring:message code="footer.secretariatForEconomy"/>&nbsp;|&nbsp;</a>
		<a><spring:message code="footer.FDIC"/></a>
	</p>
		</div>
	<p><font style="color:#C0A17B"><spring:message code="footer.organizers"/></font>
		<a><img src="<%=basePath%>images/taihu-logo.png" height="14" /><spring:message code="footer.worldCultureForum"/>&nbsp;|&nbsp;</a>
		<a><img src="<%=basePath%>images/logo_b.gif" height="9" /> <spring:message code="footer.MacauEconomicServices"/>&nbsp;|&nbsp;</a>
		<a><img src="<%=basePath%>images/logo_nk.png" height="10" /> <spring:message code="footer.NamKwong"/></a>
	</p>
	<p><font style="color:#C0A17B"><spring:message code="footer.Sponsors"/></font>
		<a><spring:message code="footer.directorsOfForum"/>&nbsp;|&nbsp; </a>
		<a><spring:message code="footer.universityOfTraditionalMedicine"/>&nbsp;|&nbsp;</a>
		<a><spring:message code="footer.MacauUniversity"/>&nbsp;|&nbsp;</a>
		<a><spring:message code="footer.NamKwongExhibitionService"/></a>
	</p>
	<p><font style="color:#C0A17B"><spring:message code="footer.coOrganizers"/></font>
		<a><spring:message code="footer.academyOfChineseMedical"/>&nbsp;|&nbsp;</a>
		<a><spring:message code="footer.instituteOfBasicClinicalMedicine"/>&nbsp;|&nbsp; </a>
		<a><spring:message code="footer.HongKongBaptistUniversity"/></a>
	</p>
</div>

<!-- 在线用户分析 -->
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-54750593-1', 'auto');
  ga('send', 'pageview');
</script>
