<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<jsp:include page="../../../../include/sys-common.jsp" />
<style>
.combo {
	width: 380px !important;
	
}
.textbox{
	width: 350px !important;
}

#show-dlg,#dlg{
	width:450px;
	height:250px;
	padding:40px 40px;
	margin:auto;
}
</style>
</head>

<body>
	<table id="question-dg" style="width:100%;height:100%"
		data-options="striped:true,rownumbers:true,singleSelect:true,idField:'id',url:'question/r',method:'get',
       				 multiSort:true,fit:true,nowrap:false,toolbar:'#toolbar'">
		<thead>
			<tr>
			    <th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'id', hidden:'true'"></th>
				<th data-options="field:'nickname',width:'100'">提问人</th>
				<th data-options="field:'content',width:'200'">提问内容</th>
				<th data-options="field:'state', width:'80', formatter:state">状态</th>
				<th data-options="field:'time', width:'80', formatter:operation">操作</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<div>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-add',plain:'true'" onclick="addQuestion()">新增</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-edit',plain:'true'" onclick="editQuestion()">编辑</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-remove',plain:'true'"
				onclick="delQuestion()">删除</a>
			<form id="select">
				会场:<input id="schedule" class="easyui-combobox" editable="false"
					name="schedule"
					data-options="valueField:'id',textField:'title',
                    url:'schedule/findByMeetingId',method:'get'" />
				<input id="search" type="button" value="查询" class="search-btn" /> <input id="reset"
					type="button" value="重置" class="search-btn" />
			</form>
		</div>
	</div>

	<div id="dlg" class="easyui-dialog" closed="true"
		buttons="#dlg-buttons">
		<div data-options="region:'center'">
			<form id="que-form" method="post">
				<div class="fitem">
					<input id="id" name="id" type="hidden">
					<div>
						<label>会场:</label>
						<input id="scheduleId" class="easyui-combobox" editable="false"
							name="scheduleId" required = "true"
							data-options="valueField:'id',textField:'title' " />
					</div>

					<div style="margin-top:20px">
						<label>内容:</label>
						<textarea name="content" class="form-control" style="width:350px;height:60px"></textarea>
					</div>
					<div style="margin-top:20px">
						<label>内容英文:</label>
						<textarea name="contentEn" class="form-control" style="width:350px;height:60px"></textarea>
					</div>

				</div>
				<div id="dlg-buttons">
					<button class="easyui-linkbutton" id="saveId" iconCls="icon-ok"
						onclick="saveForm()" style="width:90px">保存</button>
					<button class="easyui-linkbutton" iconCls="icon-cancel"
						onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</button>
					<button class="easyui-linkbutton" iconCls="icon-remove" onclick="clearForm()"
						style="width:90px">重置</button>
				</div>
			</form>
		</div>
	</div>
	
	<div id="show-dlg" class="easyui-dialog" closed="true"
		buttons="#showdlg-buttons">
		<div data-options="region:'center'">
			<form id="show-form" method="post">
				<div class="fitem">
					<input id="id" name="id" type="hidden">

					<div>
						<label style="margin-bottom:10px;font-size:16px">问题:</label>
						<p id="content" style="font-size:16px"></p>
					</div>

				</div>
				<div id="showdlg-buttons">
					<button class="easyui-linkbutton" iconCls="icon-ok"
						onclick="checkState(2)" style="width:90px">通过</button>
					<button class="easyui-linkbutton" iconCls="icon-ok" onclick="checkState(3)"
						style="width:90px">不通过</button>
					<button class="easyui-linkbutton" iconCls="icon-cancel"
						onclick="javascript:$('#show-dlg').dialog('close')" style="width:90px">关闭</button>
				</div>
			</form>
		</div>
	</div>

<script type="text/javascript">
	 
	var datagrid;
	var schedule="";
	var scheduleList;
    
	$(document).ready(function(){
		
		//加载表格数据
		loadGrid();
		findSchedules();
		$("#schedule").combobox({
	    	data: scheduleList 
    	}); 
		
	});
	
	function findSchedules(){
		$.ajax({
			url:"schedule/findByMeetingId",
			method:"get",
			success:function(result){
				scheduleList = result;
				//alert(scheduleList+"00");
			}
		});
	} 
	
	function loadGrid()  {
		datagrid = $("#question-dg").datagrid({
			nowrap:false,
			loadMsg:"加载中，请稍候...",
            fitColumns:true,
            pagination : true,//页码
            pageNumber : 1,//初始页码
            pageSize : 15,
            pageList : [15,30,45,60],
			fitColumns:true,
			onBeforeLoad:function(param){
				param.scheduleId = schedule;
            } 
		});
	}
	
	
	//审核: 1 未审核 2 审核通过 3审核未通过
    function state(value) {
		if(value==1){
			return "未审核";
		}else if(value == 2){
			return "审核通过";
		}else if (value == 3){
			return "审核未通过";
		} else if (value == 4) {
		    return "已上墙";
		}
    }
	
	
	//审核: 1 未审核 2 审核通过 3审核未通过 审核通过的可以上墙，审核未通过或未审核的可以继续审核。
    function operation(value,row) {
		var id = row.id;
		var content = row.content;
		var state = row.state;
		if(state == 2){
			//alert(id);
			return "<a onclick='canToMeetingWall(\""+id+"\")' style='cursor:pointer;'><span style='color:blue;'>顶上墙</span></a>";
		}else if (state == 4){
		    return "<a onclick='cancelFromMeetingWall(\""+id+"\")' style='cursor:pointer;'><span style='color:blue;'>拉下墙</span></a>";
		} else {
			return "<a onclick='checkQuestion(\""+id+"\",\""+content+"\")' style='cursor:pointer;'><span style='color:blue;'>审核</span></a>";
		}
    }
    
    function checkQuestion(id,content){
    	
    	$("#show-dlg").dialog("open").dialog("setTitle","审核问题");

    	$("#show-form #id").val(id);
    	$("#show-form #content").html(content);
    	
    	//alert(id+"审核");
    }
    
    //顶上墙操作
    function canToMeetingWall(id){
        var row = $("#question-dg").datagrid("getSelected");
        if (!row) {
           $.messager.alert("提示", "请选中一条记录再顶上墙！");
           return;
        } else {
            $.ajax({
                url:"question/updateToMeetingWallQ",
                method:"POST",
                data:{questionId:row.id},
                success:function(result){
                    $.messager.alert("提示", result);
                    $("#question-dg").datagrid("reload");
                }
            });

        }
    }
    
    //拉下墙操作
    function cancelFromMeetingWall(id){
        var row = $("#question-dg").datagrid("getSelected");
        if (!row) {
           $.messager.alert("提示", "请选中一条记录再拉！");
           return;
        } else {
            $.ajax({
                url:"question/setNotMeetingWallQ",
                method:"POST",
                data:{questionId:row.id},
                success:function(result){
                    $.messager.alert("提示", result);
                    $("#question-dg").datagrid("reload");
                }
            });

        }
    }
    
  	//审核状态，1未审核，2审核通过，3审核未通过
    function checkState(state){
    	
    	//alert(state);
    	var id = $("#show-form #id").val();
    	$.ajax({
    		url:"question/check",
    		method:"post",
    		data:{questionId:id,state:state},
    		success : function(result){
    			$.messager.alert("提示", result);
				$("#show-dlg").dialog("close"); 
				$("#question-dg").datagrid("reload");
    		}
    	});
    }
    
  	//点击查询。。
    $("#search").click(function(){
    	 schedule = $("#schedule").combobox("getValue");
	   	 datagrid.datagrid({
	            pageNumber : 1
	        });
    });
    
  	//重置
    $("#reset").click(function(){
    	$("#schedule").combobox("clear");
    });
    
    //新增现场提问
    function addQuestion(){
		
		$("#dlg").dialog("open").dialog("setTitle","新增问题");
		$("#que-form").form("clear");
		
		$("#scheduleId").combobox({
	    	data: scheduleList 
    	});
		
		//alert($("#schedule").combobox("getValue"));
		//默认与列表查询条件相同。。。
		var value = $("#schedule").combobox("getValue");
		$("#scheduleId").combobox("select",value);
    }
    
    //编辑现场提问
    function editQuestion(){
    	 
   		var row = $("#question-dg").datagrid("getSelected");
   		//alert(row.scheduleId);
   		$("#scheduleId").combobox({
	    	data:[getAnSchedule(row.scheduleId)] 
    	});
   		
        if (!row) {
           $.messager.alert("提示", "请选中一条记录再进行修改！");
           return;
        } else {
			$("#que-form").form("clear");
        	$("#dlg").window("open").dialog("setTitle","编辑问题");
        	$("#que-form").form("load", row);
        }
    }
    
    //修改不能更改会场
    function getAnSchedule(value) {
        for (var i=0; i<scheduleList.length; i++) {
            if (value == scheduleList[i].id) {
                return scheduleList[i];
            }
        }
    }
    
    function saveForm(){
    	var form = $("#que-form");
    	
    	form.form("submit", {
			url : "question/saveq",
			method : "POST",
			onSubmit: function () {
  			  //表单验证
  			 return form.form("validate")
  			},
			success : function(result) {
				$.messager.alert("提示", result);
				$("#dlg").dialog("close"); 
				$("#question-dg").datagrid("reload");
			}
		});
    }
    
    function clearForm(){
    	$("#que-form").form("clear");
    }
    
    //删除现场提问
    function delQuestion(){
    	var row = $("#question-dg").datagrid("getSelected");
   		
        if (!row) {
           $.messager.alert("提示", "请选中一条记录再进行删除！");
           return;
        } else {
			$.ajax({
				url:"question/delq",
				method:"POST",
				data:{questionId:row.id},
				success:function(result){
					$.messager.alert("提示", result);
					$("#question-dg").datagrid("reload");
				}
			});

        }
    }
    
    
    
</script>

</body>
</html>
