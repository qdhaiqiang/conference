

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="../../../../include/sys-common.jsp" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

  <head>
    <base href="<%=basePath%>">
<style type="text/css">
#btn_creatQR {display:none}

label,ul,ol,dl,li,p {
	padding: 0;
	margin: 0;
	list-style: none;
	font-size: 15px;
	line-height: 30px;
}

.tl {
	width: 1140px;
	border: 1px solid #f0f0f0;
	margin: 0 auto;
	margin-bottom: 10px;
}

.cont .tl img {
	height: 250px;
}

.qd {
	width: 1110px;
	padding: 14px 14px 50px;
	margin: 0 auto;
	border: 1px solid #ccc;
	height: 400px;
	font-size: 20px;
	font-family: "Microsoft YaHei", "宋体", "Georgia", "Times New Roman",
		"Times", "serif";
}

.query {
	width: 450px;
	height: 400px;
	float: left;
	border-right: 1px dotted #c5c5c5;
	text-align: center;
}
.fitem {
	text-align: left;
}

.fitem label {
    display: inline-block;
    width: 110px;
}

.ljm_submit {
	width: 40px;
	height: 30px;
	background: #EA8511;
	padding: 4px 15px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px border:1px solid #EA8511;
	color: #F8F8F8;
}
#schlistDiv {
	height:410px;
}
</style>
  </head>
	<script type="text/javascript">
		//加载本会议的所有日程
        var schdule;
        $(function(){
            $.ajax({
                url: 'schedule/findByMeetingId',
                method:'get',
                async:false,
                success:function(data) {
                    schdule = data;
                }
            });
        });
        
		$(document).ready(function(){
			$(document).keydown(function(event){
// 			 	if(event.keyCode == 13){
// 				  submitUser();
// 			 	}
			});
		
		 	$("#user_submit_query").bind('click',function(){
				submitUser();
		 	});
		 	
		 	$("#create_QR").bind('click', function(){
		 	    createQRImage();
		 	});
		 	loadNewSchData();
		});
	</script>
 	<body>
 	
 	<div class="tl">
		<img src='images/reg_banner.jpg' />
	</div>
	<div  class="qd">
    	<div class="query">
    	   <form id="fm" method="post">
			<table>
				<tbody>
					<tr>
						<td valign="top" style="width:350px" colspan="2">
	                        <div class="fitem">
	                            <input name="userId" type="hidden">
	                        </div>
	                        <div class="fitem">
                            	<label>身份卡证件类型:</label>
                            	<input id="cardType" class="easyui-combobox" editable="false" name="cardType"
                                data-options="valueField:'code',textField:'name',
                                    url:'dict/r/card_type',method:'get'" />
                        	</div>
	                        <div class="fitem">
	                            <label>用户类型:</label>
	                            <input id="userType" class="easyui-combobox easyui-validatebox" editable="false" name="userType" maxlength="50"
	                                data-options="valueField:'code',textField:'name',
	                                    url:'dict/r/user_type',method:'get',required:'true'" />
	                        </div>
	                        <div class="fitem">
	                            <label>中文姓名:</label>
	                            <input name="cname" type="text" class="easyui-validatebox" required="true" maxlength="50">
	                        </div>
	                        <div class="fitem">
	                            <label>英文姓名:</label>
	                            <input name="ename" class="easyui-validatebox" required="true" maxlength="50" style="width:132px;">
	                        </div>
	                        <div class="fitem">
	                            <label>邮箱:</label>
	                            <input id="userEmail" name="email" class="easyui-validatebox" validType="email" required="true" maxlength="50">
	                        </div>
	                        <div class="fitem">
	                            <label>性别:</label>
	                            <select class="easyui-validatebox" name="sex" style="width:80px" required="true" editable="false">
	                                <option value="1" selected="selected">男</option>
	                                <option value="2">女</option>
	                            </select>
	                        </div>
	                        <div class="fitem">
	                            <label>生年月日:</label>
	                            <input name="birth" class="easyui-datebox easyui-validatebox" data-options="formatter:dateFormatter,parser:dateParser" editable="false" >
	                        </div>
	                        <div class="fitem">
	                            <label>国籍:</label>
	                            <input id="nation" class="easyui-combobox easyui-validatebox" name="nation" editable="false"
	                                data-options="valueField:'code',textField:'name',
	                                    url:'dict/r/nation',method:'get',required:'true'" />
	                        </div>
	                    </td>
                    </tr>
                    <tr></tr>
                    <tr>
                        <td align="center">
                            <label id="user_submit_query" class="ljm_submit">提交</label>
                        </td>
                        <td align="center">
	                        <div id="btn_creatQR">
	                            <label id="create_QR" class="ljm_submit">生成二维码</label>
	                        </div>
                        </td>
                    </tr>
					
				</tbody>
			 </table>
			 </form>
		    </div>
		    <div class="fitem">
		        <label style="font-size:20px;margin-left:180px;">日程信息</label>
		        <div id="schlistDiv"></div>
		    </div>
		    <input type="hidden" name="userId" id="userId">
		</div>
    <script type="text/javascript">
        //加载用户日程信息
        function loadNewSchData() {
            var formhtml = "";
            $("#schlistDiv").html(formhtml);
            for(var i in schdule){
            	// 只显示大会日程
            	if (schdule[i].scheduleType=="1") {
                	formhtml += "<br/><input type= 'checkbox' name='schlistCheckbox' value='" + schdule[i].id + "'";
                	formhtml += " style='width:20px;'>" + schdule[i].title + "<br/>";
                }
//                 formhtml += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;开始时间:" + schdule[i].startTime + "<br/>";
//                 formhtml += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;结束时间:" + schdule[i].endTime + "<br/>";
//                 formhtml += "----------------------------";
            }
            $("#schlistDiv").append(formhtml);
        }
        
        function submitUser() {
            $("#user_submit_query").attr("disabled","true");
            $('#fm').form('submit', {
                url : 'checkinuser/add',
                method : "POST",
                onSubmit: function(param){
                    var paramList = new Array();
                    var schtextboxList = $("#schlistDiv").find("input[name='schlistCheckbox']");
                    var schFirst = schtextboxList.eq(0);
                    for(var schindex = 0;schindex<schtextboxList.length;schindex++){

                        if(schFirst.is(":checked")){
                            var schmap = {};
                            schmap["id"] = schFirst.attr("value");
                            paramList[paramList.length] = schmap;
                        }
                        schFirst = schtextboxList.eq(schindex+1);
                    }
                    param.schData = JSON.stringify(paramList);
                },
                success : function(msg) {
                     if (msg == "该邮箱已存在") {
                        $.messager.alert('提示', "该邮箱已存在，请重新填写邮箱信息！");
                        $("#user_submit_query").removeAttr('disabled');
                        return;
                    } else {
                        var result = String(msg);
                        var userId = result.substring(4, result.length);
                        $.messager.alert('提示', (String(msg)).substring(0, 4));
                        $("#user_submit_query").removeAttr('disabled');
                        $("#btn_creatQR").show();
                        $("#userId").val(userId);
                    }
                }
            });
        }
        
        //生成二维码
        function createQRImage() {
            var userId = $('#userId').val();
            if (userId == null || userId == "") {
                $.messager.alert('提示', "邮箱为空或者数据保存错误，请先输入正确的邮箱地址!");
            }
            var url = 'checkinuser/createQR/' + userId;
            window.open(url+'?openwin=true', "临时制证人员二维码生成","height=500,width=430,toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no,status=no,top=150px,left=360px");
        }
    </script>
  	</body>
</html>