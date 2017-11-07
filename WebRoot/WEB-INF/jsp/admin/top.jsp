<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<style>
#psw-div {display:none} 
.fitem {
	padding: 5px;
}

.fitem label {
	display: inline-block;
	width: 100px;
	vertical-align: top;
}

.fitem input {
	width: 200px;
	padding: 5px;
	border-radius: 5px;
}

</style>

<div region="north" border="false" title=""
	style="BACKGROUND:url(<%=basePath %>images/adtitle_bg.jpg);height: 60px; padding: 6px; overflow: hidden;">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td align="left" style="vertical-align:text-bottom"><img src="easyui/plugin/login/images/toplogo-main.png" style="height:48px;" /> <!--
      <img src="plug-in/login/images/toplogo.png" width="550" height="52" alt="">-->
			</td>
			<td align="right" nowrap>
				<table>
					<tr>
						<td valign="top" height="50">
							<span style="color: #0E2D5F">当前用户:</span>
							<span style="color: #E99314">${confSysUser.loginName}</span>
							<span style="color: #0E2D5F">&nbsp;姓名:</span>
							<span style="color: #E99314">${confSysUser.name}</span>
						</td>
					</tr>
					<tr>
						<td>
							<div style="position: absolute; right: 0px; margin-right:10px;bottom: 0px;">
								<a href="javascript:void(0);" class="easyui-menubutton"
									menu="#layout_north_kzmbMenu" iconCls="icon-help">控制面板</a><a
									href="/j_spring_security_logout" class="easyui-menubutton"
									menu="#layout_north_zxMenu" iconCls="icon-back">注销</a>
							</div>
							<div id="layout_north_kzmbMenu"
								style="width: 100px; display: none;">
								<!-- <div onclick="openwindow('用户信息','userController.do?userinfo')">
									个人信息</div> -->
								<div class="menu-sep"></div>
								<div onclick="changePsw()">修改密码</div>
							</div>
							<div id="layout_north_zxMenu"
								style="width: 100px; display: none;">
								<div class="menu-sep"></div>
								<div onclick="exit('<%=basePath %>confSysUser/logout','确定退出该系统吗 ?');">
									退出系统</div>
							</div></td>
					</tr>
				</table></td>
			<td align="right">&nbsp;&nbsp;&nbsp;</td>
		</tr>
	</table>
</div>

<div id="psw-div" class="easyui-dialog" title="修改密码"
	style="width:500px;height:360px;margin:auto"
	closed="true" maximizable="true" resizable="true"
	buttons="#dlg-buttons">
	<div style="width:350px;height:120px;margin:50px auto">
		<form id="psw-form">
			<div class="fitem">
				<label>旧密码:</label>
				<input id="oldPwd" name="oldPwd" class="easyui-validatebox" type="password" data-options="required: true" validType="length[5,30]"/>
			</div>
			<div class="fitem">
				<label>新密码:</label> 
				<input id="newPwd" name="newPwd" class="easyui-validatebox" type="password" data-options="required: true" validType="length[5,30]"/>
			</div>
			<div class="fitem">
				<label>再次输入新密码:</label> 
				<input id="newPwdRepeat" name="newPwdRepeat" type="password" class="easyui-validatebox" data-options="required: true" validType="equals['#newPwd']"/>
			</div>

		</form>
	</div>
	<div id="dlg-buttons">
		<button class="easyui-linkbutton" id="saveId" iconCls="icon-ok"
			onclick="savePwd()" style="width:90px">保存</button>
		<button class="easyui-linkbutton" iconCls="icon-cancel"
			onclick="javascript:$('#psw-div').dialog('close')" style="width:90px">关闭</button>
	</div>

</div>


<script src="<%=basePath%>js/common.js"></script>

<script>
	function exit(url, content) {
		$.messager.confirm("提示", content, function(r) {
			if (r) {
				window.location = url;
			}
		});
	}

	function changePsw() {
		$('#psw-div').show();
		$('#psw-div').dialog('open');
	}
	
	function savePwd(){
		if ($("#psw-form").form('validate')) {
			$.ajax({
				type : "POST",
				url : "confSysUser/changepwd",
				data : $("#psw-form").serialize(),
				success : function(result) {
					 if(result=="1"){
						 $.messager.alert("提示","原密码不正确！");
					 }else{
						 $.messager.alert("提示","修改成功，请重新登录！","info",function(){
						 	window.location = "<%=basePath %>confSysUser/logout";
						 });
					 }
				}
			});
		}
	}
</script>