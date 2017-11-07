<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <jsp:include page="../../../../include/sys-common.jsp" />
    <title>Macau Yacht show</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	<script>
	function formSubmit() {
		
		if (!$("#fi").form('validate')) {
			return false;
		}
		var msg = "";
		var form = $("#fi");
		$.ajax({
			  type:"post",
			  url:'confYachtUser/loginIn',
			  data:form.serialize(),
			  success:function(map){ 
				  msg = map.msg;
				  if(msg=='1'){
					  var url = 'confYachtUser/jumpToYacht_edit/' + map.user.id;
			          window.open(url, "full", "fullscreen=yes", "yes");
					  parent.layer.closeAll();
				  }
				  if(msg=='2'){
					  layer.alert("<spring:message code='yatch.login.invaidpwd'/>");
				  }
				  if(msg=='3'){
					  layer.alert("<spring:message code='yatch.login.emailnotreg'/>");
				  }
			}
		});
	}
	</script>
  </head>
  
  <body>
   <form id="f2" action="confYachtUser/jumpToYacht_edit" method="post" name="form2"  >
		<input type="hidden" name="id" value="" id="id">
   </form>	
  <form id="fi"  name="f1" >
  <div style="padding-left:30px;padding-top:20px">
  	<table style="line-height:30px">
    	<tr>
     		<td><spring:message code="yacht.email"/></td>
     		<td> 
     			<input type="text" name="loginEmail" id="loginEmail" class="easyui-textbox" style="width:150px" data-options="required:true,validType:'email'" >
      		</td>
    	</tr>
    	<tr>
    		<td><spring:message code="yacht.password"/></td>
        	<td>
        		<input type ="password" id="loginPassword" name="loginPassword" class="easyui-textbox" style="width:150px" data-options="required:true"/>
        	</td>
     	</tr>
     	<tr>
      		<td>
      			 &nbsp;
      		</td>
      	</tr>
      	<table>
	      	<tr>
	      		<td align="right" style="width:150px;">
	      			<input type="button" style="cursor: pointer;" value="<spring:message code="yacht.win.login" />"  onclick="formSubmit()">
	      		</td>
	      		<td align="left" style="width:150px;">
	      			<input type="reset" style="cursor: pointer;" value="<spring:message code='yacht.reset' />">
	      		</td>
	      	</tr>
      	</table>
  </table>
  </div>
  </form>
  </body>
</html>
