<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../../../include/sys-common.jsp" />
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
	$(function() {
		$('#menuid').tree({
			checkbox : true,
			url : '<%=basePath%>roleMenu/getMenu/${roleId}',
			onLoadSuccess : function(node) {
				expandAll();
			}
		});
		$("#menuListPanel").panel(
			{
				title :"菜单列表",
				tools:[{iconCls:'icon-save',handler:function(){mysubmit();}}]
			}
		);
	});
	function mysubmit() {
		var roleId = $("#rid").val();
		var s = GetNode();
		doSubmit("roleController.do?updateAuthority&rolefunctions=" + s + "&roleId=" + roleId);
	}
	function GetNode() {
		var node = $('#menuid').tree('getChecked');
		var cnodes = '';
		var pnodes = '';
		var prevNode = ''; //保存上一步所选父节点
		for ( var i = 0; i < node.length; i++) {
			if ($('#menuid').tree('isLeaf', node[i].target)) {
				cnodes += node[i].id + ',';
				var pnode = $('#menuid').tree('getParent', node[i].target); //获取当前节点的父节点
				if (prevNode != pnode.id) //保证当前父节点与上一次父节点不同
				{
					pnodes += pnode.id + ',';
					prevNode = pnode.id; //保存当前节点
				}
			}
		}
		cnodes = cnodes.substring(0, cnodes.length - 1);
		pnodes = pnodes.substring(0, pnodes.length - 1);
		return cnodes + "," + pnodes;
	};
	
	function expandAll() {
		var node = $('#menuid').tree('getSelected');
		if (node) {
			$('#menuid').tree('expandAll', node.target);
		} else {
			$('#menuid').tree('expandAll');
		}
	}
</script>
<div class="easyui-panel" style="padding:1px;" fit="true" border="false" id="menuListPanel">
 <input type="hidden" name="roleId" value="${roleId}" id="rid">
 <ul id="menuid"></ul>
</div>