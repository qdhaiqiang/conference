<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="../../../include/sys-common.jsp" />
<!DOCTYPE html >
<html>
  <head>
  <style type="text/css">
        #adTitleList div {
    margin:5px 0 10px 0;
        }
        #adTitleList div span {
    font-size:16px;
    font-weight: bold;
    text-decoration: none;
        }
        #uplist {
    height:20px;
        }
        
    </style>
  </head>
  
  <body>
    <div style="padding-left:350px;"><input id="userType" class="easyui-combobox" editable="false" style="width:300px;"></div>
    <div id="adTitleList" class="easyui-dialog" style="width:400px;height:500px;top:100px" closed="true" buttons="#dlg-buttons">
	</div>
	 <div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveLiz()" style="width:90px">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#adTitleList').dialog('close')" style="width:90px">取消</a>
	</div>
    <div id="formbuilder"></div>
    <script type="text/javascript">
		var userType  = ${userType};
		var basePath = '<%=basePath%>';
		var senddata = "";
        $(function(){
        	editDynForm('${meetingId}' ,'${userType}');
        	reBindFunction();
        });
        
    	
    	/**
    	         清空表单
    	**/
        function clearForm() {
            $('#ff').form('clear');
        }
        
    	/**
    	         显示表单
    	**/
        function showFormBuilder(selector, data, userType) {
        	var fb = new Formbuilder({
				selector : selector,
				bootstrapData : data.fields,
				urlPath: 'dynForm/c/saveDynForm',
            	method: 'POST',
            	data: userType
			});
        	fb.on('save', function(data,payload){
        		senddata = data;
        		 $('#adTitleList').dialog('open').dialog('setTitle','给表单排序');
        		 $('#adTitleList').html("");
        		  $.each(payload,function(n,value) {  
        	          $('#adTitleList').append('<div><span style="width:10px">'+(n+1)+'</span>、'+
        	        		  '[<span>'+value.cid+'</span>]'+'--'+
        	        		  '<span>'+value.label+'</span>'+
        	        		  '<img src="'+basePath+'images/up_list.gif" class="pointer" id="up_list" title="向上">'+
        	        		  '<img src="'+basePath+'images/down_list.gif" class="pointer" id="down_list" title="向下">'+
        	        		  '</div>');
        	          reBindFunction();
        	          FilmResort($("#adTitleList"));
        	       });
        		  
        		});
		}
        
        
        /**
                                  查看编辑表单
        **/
        function editDynForm(meetingId, userType) {
        	var url = 'dynForm/r/getForm/'+meetingId+"/"+userType;
        	$('#ff').form('clear');
            $('#dlg').window('open').dialog('setTitle','新建表单');  
        	// 从后台获取表单数据
        	$.ajax({
        		url: url,
        		type: "GET",
        		success: function(data){
        			var payloaddata = JSON.parse(data.data.payload);
        			showFormBuilder("#formbuilder",payloaddata,userType);
       			}
        	});
        	// 初始化动态用户类型下拉列表框
            $("#userType").combobox({
            	url:'dict/r/user_type',
            	method:'get',
            	required:'true',
            	valueField:'code',
            	textField:'name'
            });
        	// 绑定用户类型
        	$("#userType").combobox('select',userType);
        	// 将用户类型设置为不可编辑
        	$('#userType').combobox('disable');
        }
        
        function FilmResort(src){
        	var count = 1;
        	var len = src.find(">div").length-1;
        	src.find(">div").each(function(i){
        		$(this).find("span:eq(0)").text(i+1);
        		$(this).find("#ad_rank").val(count);
        		$(this).find("img").show();
        		if(i == 0)
        			$(this).find("img#up_list").hide();
        		if(i == len)
        			$(this).find("img#down_list").hide();
        	});
        }
        
        function reBindFunction()
        {
           	$("img#up_list").unbind("click").click(function(){
        		var obj = $(this).parent();
        		obj.prev().before(obj);
        		FilmResort($("#adTitleList"));
        	});
        	$("img#down_list").unbind("click").click(function(){
        		var obj = $(this).parent();
        		obj.next().after(obj);
        		FilmResort($("#adTitleList"));
        	});
        }
        
        function saveLiz()
        {
        	
       		var values = "";
       		var len = $("#adTitleList").find(">div").length-1;
       		$("#adTitleList").find(">div").each(function(i){
       			values = values + $(this).find("span:eq(0)").text()+'-'+$(this).find("span:eq(1)").text();
       			if(i != len){
       				values = values+"|";
       			}
           	});
       		$.ajax({
       	        url: 'dynForm/c/saveDynForm',
       	        type: Formbuilder.options.HTTP_METHOD,
       	        data: senddata+'&'+'sort='+values,
       	        success: function(data) {
       	          // 表单页面关闭
       	          $.messager.alert('提示', data.info,'info',function(){
       	        	  // 刷新父页面
       	        	  window.opener.location.reload();
       	        	  window.close();
       	          });
       	        }
       	      });
        }
    </script>
  </body>
</html>