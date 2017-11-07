<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
            String basePath = request.getScheme() + "://"
                    + request.getServerName() + ":" + request.getServerPort()
                    + path + "/";
%>
<jsp:include page="../../../include/sys-common.jsp" />
<!DOCTYPE html >
<html>
<style type="text/css">
#dlg {display:none} 
#app-dlg {display:none} 
#imp {display:none} 
#dlg-buttons {display:none}
#app-dlg-buttons {display:none}
#toolbar {display:none} 
.m-info{
    margin: 0;
    padding: 4px;
    vertical-align: top;
    border: 1px solid #95B8E7;
    -moz-border-radius: 5px 5px 5px 5px;
    -webkit-border-radius: 5px 5px 5px 5px;
    border-radius: 5px 5px 5px 5px;
}
.m-approve {
    border-left:0px;border-top:0px;border-right:0px;border-bottom:0px; border-bottom-color:Black;
}
#fm {
    margin: 0;
    padding: 10px 30px;
}

#ff {
    margin: 0;
    padding: 10px 30px;
}

.ftitle {
    font-size: 14px;
    font-weight: bold;
    padding: 5px 0;
    margin-bottom: 10px;
    border-bottom: 1px solid #ccc;
}

.fitem {
    margin-bottom: 5px;
    border-bottom:1px dotted #8CC63F;
}

.formshow{
	border-bottom:1px dotted #8CC63F;
	padding-bottom:5px;
}

.fitem label {
    display: inline-block;
    width: 90px;
}

.fitem input {
    width: 180px;
}
.fitem input[type="checkbox"] {
    width: 20px;
}
.app-top-td {
    valign:top;border:1px solid #8CC63F;width:710px;padding-left:2px;
}
.prompt {
    color:red;
}
</style>
<script type="text/javascript">
    var sname = "";
    var semail = "";
    var suserType = "";
    var sapproveState = "";
    var datagrid;
    $(document).ready(function(){
        loadGrid();
        $("#user_submitSearch").bind('click',function(){
            if (!$("#select").form('validate')) {
                return false;
            }else {
                sname = $("#sname").val().replace(" ","");
                semail = $("#semail").val().replace(" ","");
                suserType = $("#suserType").combobox("getValues");
                sapproveState = $("#sapproveState").combobox("getValue");
                datagrid.datagrid({
                    pageNumber : 1
                });
            }
        });
        $("#user_resetSearch").bind('click',function(){
            $("#sname").val("");
            $("#semail").val("");
            $("#suserType").combobox('setValue',"");
            $("#sapproveState").combobox('setValue',"");
        });
    });

    function loadGrid()  {
        datagrid = $('#dg').datagrid({
            nowrap:false,
            loadMsg:'加载中，请稍候...',
            fitColumns:true,
            pagination : true,//页码
            pageNumber : 1,//初始页码
            pageSize : 15,
            pageList : [15,30,45,60],
            detailFormatter:function(index,row){
                return '<div style="padding:5px"><table id="ddv-' + index + '"></table></div>';
            },
            pagination:true,
            onClickRow : function(rowIndex,rowData) {
                $(this).datagrid('unselectRow',rowIndex);
            },
            onDblClickRow :function(rowIndex,rowData){
                $(this).datagrid('selectRow',rowIndex);
            },
            onBeforeLoad:function(param){
                param.sname = sname;
                param.semail = semail;
                param.sapproveState = sapproveState;
                var userTypes = "";
                for(var i=0;i<suserType.length;i++){
                    userTypes += suserType[i] + ",";
                }
                param.suserType = userTypes;
            },
            onLoadSuccess:function(data){
            	$('#imp').show(); 
            	$('#dlg-buttons').show(); 
            	$('#toolbar').show();
            },
        });
        $('#dg').datagrid("clearSelections");
    }

    // 提醒函发送
    function remindMailSend() {
        $.messager.confirm('确认', '您确认要发送提醒函？', function(r) {
            if (r) {
                $.ajax({
                    url : 'meetingUser/remindMailSend',
                    type : "GET",
                    success : function(msg) {
                        /*$.messager.show({
                            title:'提示',
                            msg:msg,
                            showType:'show'
                        });*/
                    }
                });
            }
        });
    }

</script>
<body>
    <table id="dg" title="注册人员管理" style="width:100%;height:100%"
        data-options="striped:true,rownumbers:true,singleSelect:false,url:'meetingUser/r',method:'post', idField:'userId',
                     multiSort:true,fit:true,nowrap:false,toolbar:'#toolbar',pagination:'true'">
        <thead>
            <tr>
                <th data-options="field:'ck',checkbox:true"></th>
                <th data-options="field:'userId', hidden:'true'"></th>
                <th data-options="field:'cname', width:'90'">姓名(中)</th>
                <th data-options="field:'ename', width:'100'">姓名(英)</th>
                <th data-options="field:'userType', width:'80', formatter:getUserType">用户类型</th>
                <th data-options="field:'sex', width:'50', formatter:getSexValue">性别</th>
                <th data-options="field:'position', width:'100'">职位</th>
                <th data-options="field:'mobile', width:'120'">联系电话</th>
                <th data-options="field:'email', width:'200'">邮箱</th>
                <th data-options="field:'company', width:'150'">公司名称</th>
                <th data-options="field:'updateDate', width:'160'">最后修改日期</th>
                <th data-options="field:'approveState', width:'100', formatter:getApproveState">审核状态</th>
                <th data-options="field:'approve', width:'80', formatter:userApprove">操作</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">新建人员</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">修改人员信息</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除人员</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" plain="true" onclick="approveCheckedUser()">审核通过</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="importUser()">批量导入工作人员</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="remindMailSend()">发送提醒函</a>
        <div float:right>
            <form id="select">
                <label>姓名:</label><input name="sname" type="text" id="sname"  class="easyui-validatebox"  validType="searchParm"/>
                <label>邮箱:</label><input name="semail" type="text" id="semail"  class="easyui-validatebox"  validType="searchParm"/>
                <label>用户类型:</label>
                <input name="suserType" id="suserType" class="easyui-combobox" validType="searchParm"/></td>
                <label>审核状态:</label><input id="sapproveState" class="easyui-combobox" editable="false" name="sapproveState" maxlength="50"
                        data-options="valueField:'code',textField:'name',
                        url:'dict/r/approveState',method:'get'" />
                <input class="easyui-linkbutton search-btn" id="user_submitSearch" type="button" value="查询" />
                <input class="easyui-linkbutton search-btn" id="user_resetSearch" type="button" value="重置" />
            </form>
        </div>
    </div>
    <div id="dlg" class="easyui-dialog"
        style="width:85%;height:95%;padding:10px 20px" closed="true"
        maximizable="true" resizable="true"
        left="150" top="0"
        buttons="#dlg-buttons">
        <form id="fm" method="post" novalidate>
            <table>
                <tr>
                    <td>
                        <div>
                            <label><b>人员信息修改提示</b></label><br/>
                            <label><span class="prompt"><font style="font-size:10px">&nbsp;&nbsp;&nbsp;&nbsp;*上传的图片只能是bmp,jpg,jpeg,gif,png几种格式.</font></span></label><br/>
                            <label><span class="prompt"><font style="font-size:10px">&nbsp;&nbsp;&nbsp;&nbsp;*请不要修改动态表单信息!</font></span></label><br/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td valign="top" style="width:300px">
                        <div class="fitem">
                            <input name="userId" type="hidden">
                        </div>
                        <div class="fitem">
                            <label>用户类型:</label>
                            <input id="userType" class="easyui-combobox" editable="false" name="userType" maxlength="50"
                                data-options="valueField:'code',textField:'name',multiple:'true',
                                    url:'dict/r/user_type',method:'get',required:'true'" />
                        </div>
                        <div class="fitem">
                            <label>中文姓名:</label>
                            <input name="cname" type="text" class="easyui-textbox" required="true" maxlength="50">
                        </div>
                        <div class="fitem">
                            <label>英文姓名:</label>
                            <input name="ename" class="easyui-textbox" required="true" maxlength="50">
                        </div>
                        <div class="fitem">
                            <label>名:</label>
                            <input name="firstName" class="easyui-textbox" required="true" maxlength="20">
                        </div>
                        <div class="fitem">
                            <label>姓:</label>
                            <input name="lastName" class="easyui-textbox" required="true" maxlength="20">
                        </div>
                        <div class="fitem">
                            <label>邮箱:</label>
                            <input id="userEmail" name="email" class="form-control" validType="email" required="true" maxlength="50">
                        </div>
                        <div class="fitem">
                            <label>性别:</label>
                            <select class="easyui-combobox" name="sex" style="width:80px" required="true" editable="false">
                                <option value="1" selected="selected">男</option>
                                <option value="2">女</option>
                            </select>
                        </div>
                        <div class="fitem">
                            <label>生年月日:</label>
                            <input name="birth" class="easyui-datebox" data-options="formatter:dateFormatter,parser:dateParser" editable="false" >
                        </div>
                        <div class="fitem">
                            <label>国籍:</label>
                            <input id="nation" class="easyui-combobox" name="nation" editable="false"
                                data-options="valueField:'code',textField:'name',
                                    url:'dict/r/nation',method:'get',required:'true'" />
                        </div>
                        <div class="fitem">
                            <label>身份卡证件类型:</label>
                            <input id="cardType" class="easyui-combobox" editable="false" name="cardType"
                                data-options="valueField:'code',textField:'name',
                                    url:'dict/r/card_type',method:'get'" />
                        </div>
                        <div class="fitem">
                            <label>个人照片:</label>
                            <div id="filelist_photo"></div>
                            <div id="imgfileshow_photo"></div>
                            <button id="browse_photo">选择文件</button>
                            <button type="button" onclick="deletePhoto()" id="delete_photo">删除</button>
                            <input id="photo" name="photo" type="hidden">
                        </div>
                        <div class="fitem">
                            <label>地址:</label>
                            <input name="address" class="easyui-textbox" maxlength="50">
                        </div>
                        <div class="fitem">
                            <label>邮编:</label>
                            <input name="postcode" class="easyui-textbox" maxlength="50">
                        </div>
                        <div class="fitem">
                            <label>电话:</label>
                            <input name="tele" class="easyui-textbox" maxlength="50">
                        </div>
                        <div class="fitem">
                            <label>移动电话:</label>
                            <input name="mobile" class="easyui-textbox" maxlength="50">
                        </div>
                        <div class="fitem">
                            <label>传真:</label>
                            <input name="fax" class="easyui-textbox" maxlength="50">
                        </div>
                        <div class="fitem">
                            <label><b>指定联系人</b></label>
                            <input id="contactPerson" name="contactPerson" type="hidden">
                        </div>
                        <div class="fitem">
                            <label>联系人姓名:</label>
                            <input id="s-full-name" name="s-full-name" class="form-control" maxlength="50">
                        </div>
                        <div class="fitem">
                            <label>联系人性别:</label>
                            <select class="easyui-combobox" id ="s-sex" name="s-sex" style="width:80px" editable="false" >
                                <option value="0" selected="selected"></option>
                                <option value="1">男</option>
                                <option value="2">女</option>
                            </select>
                        </div>
                        <div class="fitem">
                            <label>联系人电话:</label>
                            <input id="s-contact-no" name="s-contact-no" class="form-control" maxlength="50">
                        </div>
                        <div class="fitem">
                            <label>联系人手机:</label>
                            <input type="text" class="form-control" id="s-mobile" name="s-mobile" maxlength="50">
                        </div>
                        <div class="fitem">
                            <label>联系人邮箱:</label>
                            <input type="text" class="form-control" id="s-email" name="s-email" validType="email" maxlength="50">
                        </div>
                        <div class="fitem">
                            <label>联系人传真:</label>
                            <input type="text" class="form-control" id="s-fax-no" name="s-fax-no" maxlength="50">
                        </div>
                        <div class="fitem">
                            <label>联系人职位:</label>
                            <input id="s-position" name="s-position" class="form-control" maxlength="50">
                        </div>
                        <div class="fitem">
                            <label>证件类型:</label>
                            <input id="certType" class="easyui-combobox" editable="false" name="certType"
                                data-options="valueField:'code',textField:'name',
                                    url:'dict/r/cert_type',method:'get'" />
                        </div>
                        <div class="fitem">
                            <label>证件号:</label>
                            <input name="certValue" class="easyui-textbox" maxlength="50">
                        </div>
                        <div class="fitem">
                            <label>身份证正面:</label>
                            <div id="filelist_cert_pic1"></div>
                            <div id="imgfileshow_cert_pic1"></div>
                            <button id="browse_cert_pic1">选择文件</button>
                            <button type="button" onclick="deleteCertPic1()" id="delete_cert_pic1">删除</button>
                            <input id="cert_pic1" name="certPic1" type="hidden">
                        </div>
                        <div class="fitem">
                            <label>身份证反面:</label>
                            <div id="filelist_cert_pic2"></div>
                            <div id="imgfileshow_cert_pic2"></div>
                            <button id="browse_cert_pic2">选择文件</button>
                            <button type="button" onclick="deleteCertPic2()" id="delete_cert_pic2">删除</button>
                            <input id="cert_pic2" name="certPic2" type="hidden">
                        </div>
                    <!--
                        <div class="fitem">
                            <label>证件过期日期:</label>
                            <input name="certExpiryDate" class="easyui-datebox" data-options="formatter:dateFormatter,parser:dateParser" editable="false" >
                        </div>
                    -->
                        <div class="fitem">
                            <label><b>入澳证件信息</b></label>
                        </div>
                        <div class="fitem">
                            <label>入澳证件类型:</label>
                            <input id="entryType" class="easyui-combobox" editable="false" name="entryType"
                                data-options="valueField:'code',textField:'name',
                                    url:'dict/r/entry_type',method:'get'" />
                        </div>
                        <div class="fitem">
                            <label>签发地点:</label>
                            <input name="entryPlace" class="easyui-textbox" maxlength="50">
                        </div>
                        <div class="fitem">
                            <label>签发日期:</label>
                            <input name="entryDate" class="easyui-datebox" data-options="formatter:dateFormatter,parser:dateParser" editable="false" >
                        </div>
                        <div class="fitem">
                            <label>有效期至:</label>
                            <input name="entryValidity" class="easyui-datebox" data-options="formatter:dateFormatter,parser:dateParser" editable="false" >
                        </div>
                        <div class="fitem">
                            <label>证件号码:</label>
                            <input name="entryNum" class="easyui-textbox" maxlength="50">
                        </div>
                        <div class="fitem">
                            <label>有效签注签发日期:</label>
                            <input name="entryEndmtDate" class="easyui-datebox" data-options="formatter:dateFormatter,parser:dateParser" editable="false" >
                        </div>
                        <div class="fitem">
                            <label>有效签注有效期至:</label>
                            <input name="entryEndmtValidity" class="easyui-datebox"  data-options="formatter:dateFormatter,parser:dateParser" editable="false" >
                        </div>
                        <div class="fitem">
                            <label>个人信息页:</label>
                            <div id="filelist_entry_pic1"></div>
                            <div id="imgfileshow_entry_pic1"></div>
                            <button id="browse_entry_pic1">选择文件</button>
                            <button type="button" onclick="deleteEntryPic1()" id="delete_entry_pic1">删除</button>
                            <input id="entry_pic1" name="entryPic1" type="hidden">
                        </div>
                        <div class="fitem">
                            <label>签注签发页:</label>
                            <div id="filelist_entry_pic2"></div>
                            <div id="imgfileshow_entry_pic2"></div>
                            <button id="browse_entry_pic2">选择文件</button>
                            <button type="button" onclick="deleteEntryPic2()" id="delete_entry_pic2">删除</button>
                            <input id="entry_pic2" name="entryPic2" type="hidden">
                        </div>
                        <div class="fitem">
                            <label>所在行业:</label>
                            <input id="industry" class="easyui-combobox" editable="false" name="industry"
                                data-options="valueField:'code',textField:'name',
                                url:'dict/r/industry',method:'get'" />
                        </div>
                        <div class="fitem">
                            <label>公司性质:</label>
                            <input id="companyType" class="easyui-combobox" name="companyType"
                                data-options="valueField:'code',textField:'name',
                                url:'dict/r/company_type',method:'get'" />
                        </div>
                        <div class="fitem">
                            <label>公司名称:</label>
                             <input name="company" class="easyui-textbox" maxlength="50">
                        </div>
                        <div class="fitem">
                            <label>职位:</label>
                            <input name="position" class="easyui-textbox" maxlength="50">
                        </div>
                    <!--
                        <div class="col-sm-8">
                            <label>是否需要签证:</label>
                            <input type="radio" name="visaNeed" id="visa-yes" value="1"
                            >是&nbsp;&nbsp;&nbsp;&nbsp;
                            <input type="radio" name="visaNeed" id="visa-no" value="0">否
                            <div id="visacity-div">
                                <label>签证城市:</label>
                                <input type="text" class="form-control" id="visaCity"
                                    name="visaCity">
                            </div>
                        </div>
                     -->
                    <!--
                        <div class="fitem">
                            <label>医疗需求:</label>
                            <input name="medlHis" class="easyui-textbox" maxlength="50">
                        </div>
                     -->
                        <div class="fitem">
                            <label>尊教信仰:</label>
                            <input name="religion" class="easyui-textbox" maxlength="50">
                        </div>
                        <div class="fitem">
                            <label>饮食禁忌:</label>
                            <input name="dietTaboo" class="easyui-textbox" maxlength="100">
                        </div>
                        <div class="fitem">
                            <label>慢性病史:</label>
                            <input name="medlHis" class="easyui-textbox" maxlength="50">
                        </div>
                        <div class="fitem">
                            <label>过敏食物:</label>
                            <input name="foodAllergies" class="easyui-textbox" maxlength="50">
                        </div>
                        <div class="fitem">
                            <label>自我介绍:</label>
                            <textarea name="selfIntro" class="easyui-validatebox" maxlength="1024" style="width:180px;"></textarea>
                        </div>
                        <div class="fitem">
                            <label>自我介绍-英文:</label>
                            <textarea name="selfIntroEn" class="easyui-validatebox" style="width:180px;"></textarea>
                        </div>
                        <div class="fitem">
                            <label>使用的化名:</label>
                            <input name="ualias" class="easyui-textbox" maxlength="50">
                        </div>
                        <div class="fitem">
                            <label>使用的职务名称:</label>
                            <input name="positionTitle" class="easyui-textbox" maxlength="50">
                        </div>
                        <div class="fitem">
                            <label>备注信息:</label>
                            <input name="remark" class="easyui-textbox" maxlength="50">
                        </div>
                        <div class="fitem">
                            <label>单位会期工作内容:</label>
                            <input name="workContent" class="easyui-textbox" maxlength="50">
                        </div>
                        <div class="fitem">
                            <label>主管负责人姓名:</label>
                            <input name="superviserName" class="easyui-textbox" maxlength="50">
                        </div>
                        <div class="fitem">
                            <label>主管负责人联系方式:</label>
                            <input name="superviserPhone" class="easyui-textbox" maxlength="50">
                        </div>
                        <div class="fitem" id="mailCheckedDiv">
                            <label>邮箱认证状态:</label>
                            <input id="mailChecked" readonly="true" style="border:0px solid;">
                            <input name="mailChecked" type="hidden">
                            <input name="remindFlag" type="hidden">
                        </div>
                        <div class="fitem" id="approveStateDiv">
                            <label>用户审核状态:</label>
                            <input id="approveState" readonly="true" style="border:0px solid;">
                            <input name="approveState" type="hidden">
                        </div>
                    </td>
                    <td valign="top">
                        <div class="fitem">
                            <label><b>日程信息</b></label>
                            <div id="schlistDiv"></div>
                        </div>
                        <div id="dynForm">
                            <label><b>动态表单信息</b></label>
                            <div id="userform"></div>
                        </div>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div id="dlg-buttons">
        <button class="easyui-linkbutton" id="saveId" iconCls="icon-ok" onclick="saveUser()" style="width:90px">保存</button>
        <button class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</button>
        <button class="easyui-linkbutton" iconCls="icon-remove" onclick="clearForm()" style="width:90px">重置</button>
    </div>
    <div id="imp" class="easyui-window" closed="true" data-options="iconCls:'icon-save',modal:true," style="width:506px;">
        <div class="easyui-panel" style="width:486px;">
            <div align="center">
                <form id="fi" method="post" enctype="multipart/form-data">
                    <table cellpadding="5" >
                        <tr>
                            <td>上传:</td>
                            <td>
                            <input id="fb" name="myFile" type="file" />
                            <!-- <input style="width:260px" id="fb" name="myFile" class="easyui-filebox" data-options=" buttonText: '浏览'" /> -->
                            </td>
                        </tr>
                    </table>
                </form>
                <div style="text-align:center;padding:5px">
                    <a href="import_template/user-template.xlsx" class="easyui-linkbutton">模板下载</a>
                    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="upload()">上传</a>
                    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearImpForm()">取消</a>
                </div>
            </div>
        </div>
    </div>
    <!-- 首先需要引入plupload的源代码 -->
    <script src="<%=basePath%>js/plupload.full.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/fileupload.js"></script>
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

        // 获取用户类型
        var userType;
        $(function(){
            $.ajax({
                url: 'dict/r/user_type',
                method:'get',
                async:false,
                success:function(data) {
                    userType = data;
                }
            });
            //加载检索条件（用户类型）
             $("#suserType").combobox({
                data : userType,
                valueField:'code',
                textField:'name',
                multiple:true,
                editable:false
//                 ,panelHeight:'auto'
             });
        });

        //用户类型匹配
        function getUserType(value, row , index) {
           for (var i=0; i<userType.length; i++) {
               if (value == userType[i].code) {
                   return userType[i].name;
               }
           }
       }

        // 获取用户审核状态
        var approveState;
        $(function(){
            $.ajax({
                url: 'dict/r/approveState',
                method:'get',
                async:false,
                success:function(data) {
                    approveState = data;
                }
            });
        });

        //一览表用户审核状态匹配
        function getApproveState(value, row, index) {
           for (var i=0; i<approveState.length; i++) {
               if (value == approveState[i].code) {
                   return approveState[i].name;
               }
           }
       }

        function getSexValue(value, row, index){
            if (row.sex == 1) {
                return "男";
            } else if (row.sex == 2) {
                return "女";
            }
        }

        // 获取证件类型
        var certType;
        $(function(){
            $.ajax({
                url: 'dict/r/cert_type',
                method:'get',
                async:false,
                success:function(data) {
                    certType = data;
                }
            });
        });

        // 证件类型匹配
        function getCertType(value) {
           for (var i=0; i<certType.length; i++) {
               if (value == certType[i].code) {
                   return certType[i].name;
               }
           }
       }

        // 获取入港通行证证件类型
        var entryType;
        $(function(){
            $.ajax({
                url: 'dict/r/entry_type',
                method:'get',
                async:false,
                success:function(data) {
                    entryType = data;
                }
            });
        });

        // 入港通行证证件类型匹配
        function getEntryType(value) {
           for (var i=0; i<entryType.length; i++) {
               if (value == entryType[i].code) {
                   return entryType[i].name;
               }
           }
       }

        // 获取身份卡证件类型
        var cardType;
        $(function(){
            $.ajax({
                url: 'dict/r/card_type',
                method:'get',
                async:false,
                success:function(data) {
                    cardType = data;
                }
            });
        });

        // 身份卡证件类型匹配
        function getCardType(value) {
           for (var i=0; i<cardType.length; i++) {
               if (value == cardType[i].code) {
                   return cardType[i].name;
               }
           }
       }

        // 获取行业类型
        var industry;
        $(function(){
            $.ajax({
                url: 'dict/r/industry',
                method:'get',
                async:false,
                success:function(data) {
                    industry = data;
                }
            });
        });

        // 证件类型匹配
        function getIndustryType(value) {
           for (var i=0; i<industry.length; i++) {
               if (value == industry[i].code) {
                   return industry[i].name;
               }
           }
       }


        // 获取行业类型
        var companyType;
        $(function(){
            $.ajax({
                url: 'dict/r/company_type',
                method:'get',
                async:false,
                success:function(data) {
                    companyType = data;
                }
            });
        });

        // 证件类型匹配
        function getCompanyType(value) {
           for (var i=0; i<companyType.length; i++) {
               if (value == companyType[i].code) {
                   return companyType[i].name;
               }
           }
       }

        // 获取国家信息
        var nation;
        $(function(){
            $.ajax({
                url: 'dict/r/nation',
                method:'get',
                async:false,
                success:function(data) {
                    nation = data;
                }
            });
        });

        // 国家名称匹配
        function getNation(value) {
           for (var i=0; i<nation.length; i++) {
               if (value == nation[i].code) {
                   return nation[i].name;
               }
           }
       }

        //删除图片(个人照片)
        function deletePhoto () {
            $('#imgfileshow_photo').html("");
            $('#photo').val("");
        }
        //删除图片(个人身份信息照)
        function deleteCertPic1 () {
            $('#imgfileshow_cert_pic1').html("");
            $('#cert_pic1').val("");
        }
        //删除图片(个人身份反面照)
        function deleteCertPic2 () {
            $('#imgfileshow_cert_pic2').html("");
            $('#cert_pic2').val("");
        }
        //删除图片(入奥证件个人信息照)
        function deleteEntryPic1 () {
            $('#imgfileshow_entry_pic1').html("");
            $('#photo').val("");
        }
        //删除图片(入奥证件签注签发照)
        function deleteEntryPic2 () {
            $('#imgfileshow_entry_pic2').html("");
            $('#entry_pic2').val("");
        }
        //新建嘉宾
        var url;
        function newUser() {
            $("#dlg").show();
            $('#dlg').dialog('open').dialog('setTitle', '新建人员');
            loadNewSchData();
            $('#dynForm').hide();
            $('#fm').form('clear');
            url = 'meetingUser/add';

            //将上传的图片控件清空
            uploaderdestroy(uploader1);
            uploaderdestroy(uploader2);
            uploaderdestroy(uploader3);
            $('#userEmail').attr('readonly', false);
            $("#imgfileshow_photo").html("");
            $("#imgfileshow_entry_pic1").html("");
            $("#imgfileshow_entry_pic2").html("");
            $("#imgfileshow_cert_pic1").html("");
            $("#imgfileshow_cert_pic2").html("");
            $("#mailCheckedDiv").hide();
            $("#approveStateDiv").hide();
            uploadimage("newuser");//user email,new user's email is null,so .
        }

        //编辑嘉宾信息
        function editUser() {
        	$("#dlg").show();
            var row = $('#dg').datagrid('getSelected');
            if (!row) {
               $.messager.alert('提示', "请选中一条记录再进行修改！");
               return;
            }
            var approveState3 = getApproveState2(row.approveState);
            $('#dlg').dialog('open').dialog('setTitle', '修改人员信息');
            $('#religion').val(row.religion);
            $('#city').val(row.city);
            $('#meetingStatus').val(row.meetingStatus);
            $('#approveState').val(approveState3);
            $('#userEmail').attr('readonly', true);
            //邮箱验证处理
            if (row.mailChecked == 1) {
                $('#mailChecked').attr("value",'已认证');
            } else if (row.mailChecked == 2) {
                $('#mailChecked').attr("value",'未认证');
            }
           //处理visaNeed
            var visaNeed = row.visaNeed;
            if (visaNeed != "") {
                if (visaNeed == '1') {
                    $("#visa-yes").attr("checked", true);
                } else {
                    $("#visa-no").attr("checked", true);
                }
            }
            //visacity选中yes显示div
            $("[name='visaNeed']").click(function() {
                if (this.value == 1) {
                    $("#visacity-div").slideDown();
                } else {
                    $("#visacity-div").slideUp();
                    $("#visaCity").val("");
                }
            });
            //处理指定联系人
            var sContact = row.contactPerson;
            if (sContact != "") {
                var jsonObj = eval('(' + sContact + ')');
                if (jsonObj != "{}") {
                    $("#s-full-name").val(jsonObj.fullName);
                    //$("#s-sex").val(jsonObj.sex);
                    $('#s-sex').combobox('setValue', jsonObj.sex);
                    $("#s-position").val(jsonObj.position);
                    $("#s-contact-no").val(jsonObj.contactNo);
                    $("#s-email").val(jsonObj.email);
                    $("#s-fax-no").val(jsonObj.faxNo);
                    $("#s-mobile").val(jsonObj.mobile);
                }
            }
            //在此处放上所有图片的信息
            uploaderdestroy(uploader1);
            uploaderdestroy(uploader2);
            uploaderdestroy(uploader3);
            $("#imgfileshow_photo").html("");
            $("#imgfileshow_entry_pic1").html("");
            $("#imgfileshow_entry_pic2").html("");
            $("#imgfileshow_cert_pic1").html("");
            $("#imgfileshow_cert_pic2").html("");
            if (row.photo != null && row.photo != "") {
                $("#imgfileshow_photo").html("<img src='"+row.photo+"'>");
            }
            if (row.entryPic1 != null && row.entryPic1 != "") {
                $("#imgfileshow_entry_pic1").html("<img src='"+row.entryPic1+"'>");
            }
            if (row.entryPic2 != null && row.entryPic2 != "") {
                $("#imgfileshow_entry_pic2").html("<img src='"+row.entryPic2+"'>");
            }
            if (row.certPic1 != null && row.certPic1 != "") {
                $("#imgfileshow_cert_pic1").html("<img src='"+row.certPic1+"'>");
            }
            if (row.certPic2 != null && row.certPic2 != "") {
                $("#imgfileshow_cert_pic2").html("<img src='"+row.certPic2+"'>");
            }
            uploadimage(row.email);
            loadEditSchData(row);
            getDynData(row);
            $('#fm').form('load', row);
            $('#userType').combobox('clear');
            $("#userType").combobox("select",row.userType);
            url = 'meetingUser/update';
        }

        //保存嘉宾信息
        function saveUser() {
            $("#saveId").attr("disabled","true");
            //check邮箱
            var emailVal = $("#userEmail").val();
            if (emailVal == null || emailVal == "") {
                $("#saveId").removeAttr('disabled');
                $.messager.alert('提示', "邮箱账号是必填项，请输入有效邮箱！");
                return;
            }
            var filter  = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
            if (!filter.test(emailVal)){
                $("#saveId").removeAttr('disabled');
                $.messager.alert('提示', "邮箱格式不正确，请输入有效邮箱账号！");
                 return true;
            }
            //处理指定联系人
            var contactPerson = "";

            if ($("#s-full-name").val() == "" &&
                    ($("#s-mobile").val() == ""  &&
                             $("#s-position").val() == ""  &&
                             $("#s-contact-no").val() ==""  &&
                             $("#s-email").val() == ""  &&
                             $("#s-fax-no").val() == "")){
                contactPerson = "{}";
            } else if ($("#s-full-name").val() == "" &&
                    ($("#s-mobile").val() != "" ||
                             $("#s-position").val() != "" ||
                             $("#s-contact-no").val() !="" ||
                             $("#s-email").val() != "" ||
                             $("#s-fax-no").val() != "")) {
                        $.messager.alert('提示', "填写指定联系人信息时至少要填写姓名。");
                        $("#saveId").removeAttr('disabled');
                        return false;
            } else {
              contactPerson = "{fullName:'" + $("#s-full-name").val()
                    +"',position:'" + $("#s-position").val()
                    +"',sex:'" + $('#s-sex').combobox("getValue")
                    + "',contactNo:'" + $("#s-contact-no").val()
                    + "',email:'" + $("#s-email").val() + "',faxNo:'"
                    + $("#s-fax-no").val() + "',mobile:'"
                    + $("#s-mobile").val() + "'}";
            }
            $("#contactPerson").val(contactPerson);
            $('#fm').form('submit', {
                url : url,
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

                success : function(result) {
                    if (result == "该邮箱已存在") {
                        $.messager.alert('提示', "该邮箱已存在，请重新填写邮箱信息！");
                        $("#saveId").removeAttr('disabled');
                        return;
                    } else if (result == "不允许修改用户类型") {
                        $.messager.alert('提示', "用户类型修改不正确，只能将演讲嘉宾或者参会嘉宾改为VIP嘉宾！");
                        $("#saveId").removeAttr('disabled');
                        return;
                    } else {
                        $("#saveId").removeAttr('disabled');
                        $.messager.alert('提示', result);
                        $('#dlg').dialog('close');        // close the dialog
                        $('#dg').datagrid('reload'); // reload the meeting data
                        $('#dg').datagrid("clearSelections");
                    }
                }
            });
        }

        //删除注册嘉宾信息
        function destroyUser() {
            var rows = $('#dg').datagrid('getSelections');
            if (!rows) {
                $.messager.alert('提示', "请选中一条记录再进行删除");
                return;
            }
            var num=rows.length;//获取要删除信息的个数
            var sn = "";
            for(var i=0; i<rows.length; i++){//组成一个字符串，ID主键之间用逗号隔开
                if(i!=rows.length-1){
                    sn=sn+rows[i].userId+",";
                 }else{
                     sn=sn+rows[i].userId;
                 }
            }
                $.messager.confirm('确认', '确定要删除这'+ num+'条记录吗？', function(r) {
                    if (r) {
                        $.ajax({
                            url : 'meetingUser/delete',
                            type : "GET",
                            data: {'userIds':sn},
                            success : function(msg) {
                                $.messager.alert('提示', msg);
                                $('#dg').datagrid('reload'); // reload the user data,loadGrid();
                                $('#dg').datagrid('clearSelections');
                            }
                        });
                    }
                });
        }

        //清除表单
        function clearForm() {
            $('#fm').form('clear');
        }

        //嘉宾信息批量审核
        function approveCheckedUser() {
            var rows = $('#dg').datagrid('getSelections');
            if (!rows) {
                $.messager.alert('提示', "请选中一条记录再进行下一步操作!");
                return;
            }
            var num=rows.length;//获取要删除信息的个数
            var sn = "";
            for(var i=0; i<rows.length; i++){//组成一个字符串，ID主键之间用逗号隔开
                if(i!=rows.length-1){
                    sn=sn+rows[i].userId+",";
                 }else{
                     sn=sn+rows[i].userId;
                 }
            }

            $.messager.confirm("提示", "是否将选中的"+num+"条数据的审核状态全部改为审核通过?", function (r) {
                if(r){
                     $.ajax({
                          type:"GET",
                          url:'meetingUser/approveCheckedUser',
                          data: {'snNumbers':sn},
                          success:function(msg){
                            $.messager.alert('提示', msg);
                            $('#app-dlg').dialog('close');// close the dialog
                            $('#dg').datagrid('reload'); // reload the meeting data
                            $('#dg').datagrid('clearSelections');
                          }
                       });
                     sendEmail(sn);
                }
            });
        }

        //（编辑用）加载用户日程信息
        function loadEditSchData(row) {
            var formhtml = "";
            $("#schlistDiv").html(formhtml);
            for(var i in schdule){
                formhtml += "<br/><input type= 'checkbox' name='schlistCheckbox' value='" + schdule[i].id + "'";
                for (var j in row.schList) {
                    if(row.schList[j].scheduleId == schdule[i].id){
                        formhtml += " checked ";
                    }
                }
                formhtml += " style='width:20px;'>" + schdule[i].title + "<br/>";
                formhtml += "开始时间:" + schdule[i].startTime + "<br/>";
                formhtml += "----------------------------";
            }
            $("#schlistDiv").append(formhtml);
        }

        //（新建用）加载用户日程信息
        function loadNewSchData() {
            var formhtml = "";
            $("#schlistDiv").html(formhtml);
            for(var i in schdule){
                formhtml += "<br/><input type= 'checkbox' name='schlistCheckbox' value='" + schdule[i].id + "'";
                formhtml += ">" + schdule[i].title + "<br/>";
                formhtml += "开始时间:" + schdule[i].startTime + "<br/>";
                formhtml += "结束时间:" + schdule[i].endTime + "<br/>";
                formhtml += "----------------------------";
            }
            $("#schlistDiv").append(formhtml);
        }

        //审核链接拼写
        function userApprove(value, row, index) {
            return '<a onclick="approve()" style="cursor:pointer;"><span style="color:blue;">审核</span></a>';
        }

        var uploader1 = "";
        var uploader2 = "";
        var uploader3 = "";

        function uploadimage(useremail){
            //上传下载图片
               var fileuploadurl ="<%=basePath%>fileload/c/upload";
                //上传图片的附加参数,sign需要修改成用户邮箱
                var frontparam = {
                    optiontype:"user",
                    sign:useremail,
                    filecategory:'front'
                };
                var backparam = {
                    optiontype:"user",
                    sign:useremail,
                    filecategory:'back'
                };
                var userphotoparam = {
                        optiontype:"user",
                        sign:useremail,
                        filecategory:'photo'
                };
               //browse bannerPic filelist imgfileshow
               uploader1 = initUploader("browse_photo","filelist_photo","imgfileshow_photo","photo",fileuploadurl,false,300,300,true,userphotoparam);
               uploader2 = initUploader("browse_entry_pic1","filelist_entry_pic1","imgfileshow_entry_pic1","entry_pic1",fileuploadurl,false,300,300,true,userphotoparam);
               uploader3 = initUploader("browse_entry_pic2","filelist_entry_pic2","imgfileshow_entry_pic2","entry_pic2",fileuploadurl,false,300,300,true,userphotoparam);
               uploader2 = initUploader("browse_cert_pic1","filelist_cert_pic1","imgfileshow_cert_pic1","cert_pic1",fileuploadurl,false,300,300,true,userphotoparam);
               uploader3 = initUploader("browse_cert_pic2","filelist_cert_pic2","imgfileshow_cert_pic2","cert_pic2",fileuploadurl,false,300,300,true,userphotoparam);
        }

        //用户信息审核
        function approve() {
            var row = $('#dg').datagrid('getSelected');
            if (!row) {
               $.messager.alert('提示', "请选中一条记录再审核！");
               return;
            }
            
            $("#specific_close").css({ "width": "60px" });
            $("#app-dlg").css({ "width": "710px" });
            $("#app-dlg").show();
            $('#app-dlg-buttons').show();
            //处理指定联系人
            var sContact = row.contactPerson;
            if (sContact != "") {
                var jsonObj = eval('(' + sContact + ')');
                if (jsonObj != "{}") {
                    $("#app-s-full-name").val(jsonObj.fullName);

                    if(jsonObj.sex == 1){
                        $("#app-s-sex").val("男");
                    }else if(jsonObj.sex == 2){
                        $("#app-s-sex").val("女");
                    }
                    $("#app-s-position").val(jsonObj.position);
                    $("#app-s-contact-no").val(jsonObj.contactNo);
                    $("#app-s-email").val(jsonObj.email);
                    $("#app-s-fax-no").val(jsonObj.faxNo);
                    $("#app-s-mobile").val(jsonObj.mobile);
                }
            }
            //处理图片
            $("#app-photo").html("<img src='"+row.photo+"'>");
            $("#bannerPic").html("<img src='"+row.bannerPic+"'>");
            $("#app-entry-pic1").html("<img src='"+row.entryPic1+"'>");
            $("#app-entry-pic2").html("<img src='"+row.entryPic2+"'>");
            $("#app-cert-pic1").html("<img src='"+row.certPic1+"'>");
            $("#app-cert-pic2").html("<img src='"+row.certPic2+"'>");
            //处理选择项，日程，动态表单信息
            loadSelectData(row);
            loadSchData(row);
            getDynData(row);
            $('#ff').form('load', row);
        }

        //加载嘉宾个人选择型信息(审核用)
        function loadSelectData(row){
            var nation = getNation(row.nation);
            var certType = getCertType(row.certType);
            var userType2 = getUserType2(row.userType);
            var industryType = getIndustryType(row.industry);
            var companyType = getCompanyType(row.companyType);
            var approveState2 = getApproveState2(row.approveState);
            var entryType = getEntryType(row.entryType);
            var cardType = getCardType(row.cardType);
            //国籍、证件类型、行业类型处理
            $('#app-nation').attr("value", nation);
            $('#app-certType').attr("value", certType);
            $('#app-industry').attr("value", industryType);
            //入港通行证处理
            $('#app-entryType').attr("value", entryType);
            $('#app-cardType').attr("value", cardType);
            $('#app-dlg').dialog('open').dialog('setTitle', '用户信息审核');
            $('.m-info').addClass("m-approve");
            $('.m-info').attr('readonly','true');
            //性别处理
            if (row.sex == 1) {
                $('#sex').attr("value",'男');
            } else if (row.sex == 2) {
                $('#sex').attr("value",'女');
            }
            $('#app-companyType').attr("value", companyType);

            //是否需要签证处理
            if (row.visaNeed == 1) {
                $('#app-visaNeed').attr("value",'是');
            } else if (row.city == 2) {
                $('#app-visaNeed').attr("value",'否');
            }
            //邮箱验证处理
            if (row.mailChecked == 1) {
                $('#mailChecked').attr("value",'已认证');
            } else if (row.mailChecked == 2) {
                $('#mailChecked').attr("value",'未认证');
            }
            //宗教处理
            $('#religion').val(row.religion);
            //会议状态处理
            if (row.meetingStatus == 1) {
                $('#app-meetingStatus').attr("value",'筹备中');
            } else if (row.meetingStatus == 2) {
                $('#app-meetingStatus').attr("value",'开展中');
            } else if (row.meetingStatus == 3) {
                $('#app-meetingStatus').attr("value",'已结束');
            }

            $('#app-userType').attr("value", userType2);
            if (approveState2 == null || approveState2 == "") {
                $('#app-approveState').attr("value", "");
            } else {
                $('#app-approveState').attr("value", approveState2);
            }
        }

        //审核加载用户日程信息
        function loadSchData(row) {
            $("#app-schListDiv").html("");
            for(var i in row.schList){
                $("#app-schListDiv").append("<br/>标题:  " + row.schList[i].schTitle);
                $("#app-schListDiv").append("<br/>开始时间:" + row.schList[i].schStartTime);
                $("#app-schListDiv").append("<br/>结束时间:" + row.schList[i].schEndTime);
                $("#app-schListDiv").append("<br/>" + "----------------------------");
            }
        }

        //用户信息审核通过
        function approveOK() {
            if ($("#app-schListDiv").html() == "") {
                $.messager.confirm("提示", "该用户没有选择的日程信息，确定设置为审核通过吗?", function (r) {
                    if(r){
                        $('#ff').form('submit', {
                            url : 'meetingUser/updateApproveOK',
                            method : "POST",
                            success : function(result) {
                                $.messager.alert('提示', result);
                                sendEmail($('#userId').val());
                            }
                        });
                    }
                });
            } else {
                $('#ff').form('submit', {
                    url : 'meetingUser/updateApproveOK',
                    method : "POST",
                    success : function(result) {
                        $.messager.alert('提示', result);
                        sendEmail($('#userId').val());
                    }
                });
            }
        }

        //用户信息审核不通过
        function approveNotOK() {
            $('#ff').form('submit', {
                url : 'meetingUser/updateApproveNotOK',
                method : "POST",
                success : function(result) {
                    $.messager.alert('提示', result);
                    $('#app-dlg').dialog('close');        // close the dialog
                    $('#dg').datagrid('reload'); // reload the meeting data
                }
            });
        }

        //发送邮件
        function sendEmail(userId){
            $.messager.confirm('确认', '立即发送确认函？', function(r) {
                if (r) {
                    $.ajax({
                        url: "<%=basePath%>user/mailSend",
                        type: "POST",
                        data: {'confUserId':userId,'type':'2'},
                        success: function(msg){
                            $('#dg').datagrid('clearSelections');
                            $('#app-dlg').dialog('close');        // close the dialog
                            $('#dg').datagrid('reload'); // reload the meeting data
                        }
                    });
                } else {
                    $('#app-dlg').dialog('close');        // close the dialog
                    $('#dg').datagrid('reload'); // reload the meeting data
                }
            });
        }

        //用户类型匹配
        function getUserType2(value) {
           for (var i=0; i<userType.length; i++) {
               if (value == userType[i].code) {
                   return userType[i].name;
               }
           }
        }

        //审核画面表用户审核状态匹配
        function getApproveState2(value) {
               for (var i=0; i<approveState.length; i++) {
                   if (value == approveState[i].code) {
                       return approveState[i].name;
                   }
               }
        }

        function importUser() {
            $('#imp').dialog('open').dialog('setTitle', '导入工作人员');
            $('#fi').form('clear');
            /*$('#fb').filebox({
                buttonText: '浏览',
                buttonAlign: 'right'
            });*/
        }

        //导入用户
        function upload() {
        	//ie下无法正常导入
            $('#fi').form('submit', {
                url:"user/imp",
                method:"POST",
                success:function(data){
                	$.messager.alert('提示',data);
                    //$('#win').window("close");
                    $('#imp').dialog('close');
                    loadGrid();
                }
        	});
        }
       function clearImpForm() {
            $('#fi').form('reset');
            $('#imp').dialog('close');
        }

        function getDynData(row){
                $.ajax({
                    url: "<%=basePath%>dynForm/r/getFormAdmin/" + row.meetingId + "/" + row.userId,
                    type: "GET",
                    success: function(data){
                        setform(data);
                    }
                });
        }

        //初始化动态表单data
        function setform(data){
            var isHaveFileUpload = false;
            $("#userform").html("");//首先清空表单中的内容
            $("#app-userform").html("");//首先清空表单中的内容
         //后台传的数据，渲染页面
            var status = data.status + data.info;
            var formcontent = data.data;
            if (data.status != "0") {
                $("#userform").append(formcontent);
                $("#ap_userform").append(formcontent);
                return;
            }
            for(var i in formcontent){
                var formhtml = "<div class='formshow'><span class='nametitle'>";
                formhtml += formcontent[i].name;
                if(formcontent[i].required == "true"){
                    formhtml += "<abbr class='requiredtitle' title='必填required'>*</abbr>";
                }
                formhtml += "</span><br>";
                /*
                <!-- hidden放置通用的属性：fieldid,name,type,userid,mettingid,roleid,required-->
                <!-- 其他属性：description放置在sapn,options动态组装，value动态获取-->
                */
                if(formcontent[i].id){
                    formhtml += "<input type='hidden' name='id' value='"+formcontent[i].id+"'>";
                }else{
                    formhtml += "<input type='hidden' name='id' value=''>";
                }
                formhtml += "<input type='hidden' name='fieldId' value='"+formcontent[i].fieldId+"'>"+
                            "<input type='hidden' name='name' value='"+formcontent[i].name+"'>"+
                            "<input type='hidden' name='type' value='"+formcontent[i].type+"'>"+
                            "<input type='hidden' name='meetingId' value='"+formcontent[i].meetingId+"'>"+
                            "<input type='hidden' name='uType' value='"+formcontent[i].userType+"'>"+
                            "<input type='hidden' name='id' value='"+formcontent[i].userId+"'>"+
                            "<input type='hidden' name='required' value='"+formcontent[i].required+"'>"+
                            "<input type='hidden' name='description' value='"+formcontent[i].description+"'>";

                if(formcontent[i].type == "text"){
                    formhtml += " <input class='easyui-textbox' required='"+formcontent[i].required+"' type='";
                    formhtml += formcontent[i].type +"' value='"+ ((formcontent[i].value) ==null ? '' : formcontent[i].value) +"'>";
                }else if(formcontent[i].type == "address"){
                    if(formcontent[i].value == ""){
                        formhtml += "<label><b>嘉宾没有上传附件</b></label>";
                    } else {
                        formhtml += "<a target='_blank' href='" + formcontent[i].value + "'>"+formcontent[i].name+"</a>";
                    }
                }else if(formcontent[i].type == "website"){
                     continue;
                }else if(formcontent[i].type == "paragraph"){
                    formhtml += " <textarea class='easyui-validatebox' required='"+formcontent[i].required+"' name='"+formcontent[i].type+"'>";
                    formhtml += (formcontent[i].value==null ? '': formcontent[i].value) +"</textarea> ";

                }else if(formcontent[i].type == "email"){
                    //邮箱要做格式验证
                    formhtml += " <input type='text' class='easyui-validatebox' validType='email' required='"+formcontent[i].required+"' name='";
                    formhtml += formcontent[i].type +"' value='"+ (formcontent[i].value==null ? "": formcontent[i].value) +"'>";
                }else if(formcontent[i].type == "number"){
                    //邮箱要做格式验证
                    formhtml += " <input class='easyui-numberbox'  precision='0' required='"+formcontent[i].required+"' type='text' ";
                    formhtml += " value='"+ (formcontent[i].value==null ? "": formcontent[i].value) +"'>";

                }else if(formcontent[i].type == "date"){
                    //判断日期和时间格式，需要借用input class="easyui-datetimebox"或easyui-datebox
                    //取值，var begindate=$('#begindate').datebox('getValue');
                    //放置默认值 $("#begindate").datebox('setValue','begindate');
                    formhtml += "<input class='easyui-datebox' editable='false' required='"+formcontent[i].required+"' id='"+formcontent[i].type+i+"' value='"+ (formcontent[i].value==null ? "": formcontent[i].value) +"'>";
                    //$.parser.parse("#"+formcontent[i].type+i);
                }else if(formcontent[i].type == "time"){
                    //时间可以在data上选择也可以让用户自己填写
                    formhtml += " <input class='easyui-datetimebox' editable='false' required='"+formcontent[i].required+"' id='"+formcontent[i].type+i+"' value='";
                    formhtml += (formcontent[i].value==null ? "": formcontent[i].value) +"'>";

                }else if(formcontent[i].type == "checkboxes"){
                    var formoptions = eval(formcontent[i].options);
                    for(var j in formoptions){
                        formhtml += "<input type= 'checkbox' name='"+formcontent[i].type+i;
                        formhtml += "' value='"+ formoptions[j].label+"'";
                        if(formoptions[j].checked == true){
                            formhtml += "checked";
                        }
                        formhtml += ">";
                        formhtml += formoptions[j].label;
                    }
                }else if(formcontent[i].type == "radio"){
                    var formoptions = eval(formcontent[i].options);
                    for(var j in formoptions){
                        formhtml += "<input type= 'radio' name='"+formcontent[i].type+i;
                        formhtml += "' value='"+ formoptions[j].label+"'";
                        if(formoptions[j].checked == true){
                            formhtml += "checked";
                        }
                        formhtml += ">";
                        formhtml += formoptions[j].label;
                    }
                }else if(formcontent[i].type == "dropdown"){
                    formhtml += "<select>";
                    var formoptions = eval(formcontent[i].options);
                    for(var j in formoptions){
                        formhtml += "<option value='"+formoptions[j].label+"'";
                        if(formoptions[j].checked == true){
                            formhtml += "selected";
                        }
                        formhtml += ">"+formoptions[j].label+"</option>";
                    }
                    formhtml += "</select>";
                }

                formhtml += "<br><span class='descriptionfoot'>"+(formcontent[i].description == null ? "" : formcontent[i].description) + "</span></div><br>";
                $("#userform").append(formhtml);
                $("#app-userform").append(formhtml);
            }
            // easyui初始化css入口,重新将所有控件的css重新初始化，解决input class="easyui-datetimebox"没有样式问题
            //$("#userform").html(status+"<br>"+formcontent[0].field_id+formcontent[0].name+formcontent[0].type);
            if(isHaveFileUpload){
                createfileUpload("fileInput");
            }
        }

        //初始化上传文件的控件及方法
        function createfileUpload(fileInputDiv){
            removefileUpload();
            var filediv = '<div id="fileUploadDiv">';
            filediv += '<button id="browse">选择文件</button>';
            filediv += '<button id="start_upload">开始上传</button>';
            filediv += '<div id="filelist"> </div>';
            filediv += '<div id="imgfileshow"> </div>';
            filediv += '</div>';
            $("#"+fileInputDiv).after(filediv);

            var fileuploadurl ="<%=basePath%>fileload/c/upload";
           //browse bannerPic filelist imgfileshow为上传图片及预览所需控件id
           //附加参数
            var multipart_params = {
                optiontype:"metting",
                sign:'meetingidOrcode',
                filecategory:'useremail'
            };
           initUploader("browse","filelist","imgfileshow",fileInputDiv,fileuploadurl,true,0,0,false,multipart_params);
        }

        function removefileUpload(){
            $("#fileUploadDiv").remove();
        }
    </script>
    <div id="app-dlg" class="easyui-dialog"
        style="width:710px;height:530px;padding:10px 20px" closed="true"
        maximizable="true" resizable="true"
        left="150" top="0"
        buttons="#app-dlg-buttons">
        <form id="ff" method="post" novalidate>
            <table>
                <tr>
                    <th class="app-top-td" style="color: #EA8511;font-size:14px;border:none;padding-bottom:10px;">个人基本信息</th>
                    <th class="app-top-td" style="color: #EA8511;font-size:14px;border:none;padding-bottom:10px;">日程及动态表单信息</th>
                </tr>
                <tr>
                    <td class="app-top-td" valign="top">
                        <div class="fitem" style="border-bottom:none;">
                            <input name="userId" id="userId" type="hidden">
                        </div>
                        <div class="fitem">
                            <label>用户类型:</label>
                            <input id="app-userType" class="m-info">
                        </div>
                        <div class="fitem">
                            <label>中文姓名:</label>
                            <input name="cname" class="m-info">
                        </div>
                        <div class="fitem">
                            <label>英文姓名:</label>
                            <input name="ename" class="m-info">
                        </div>
                        <div class="fitem">
                            <label>名:</label>
                            <input name="firstName" class="m-info">
                        </div>
                        <div class="fitem">
                            <label>姓:</label>
                            <input name="lastName" class="m-info">
                        </div>
                        <div class="fitem">
                            <label>邮箱:</label>
                            <input name="email" class="m-info">
                        </div>
                        <div class="fitem">
                            <label>性别:</label>
                            <input id="sex" class="m-info">
                        </div>
                        <div class="fitem">
                            <label>生年月日:</label>
                            <input name="birth" class="m-info">
                        </div>
                        <div class="fitem">
                            <label>国籍:</label>
                            <input id="app-nation" class="m-info">
                        </div>
                        <div class="fitem">
                            <label>个人照片:</label>
                            <div id="app-photo" name="photo" class="m-info"></div>
                        </div>
                        <div class="fitem">
                            <label>证件类型:</label>
                            <input id="app-certType" class="m-info">
                        </div>
                        <div class="fitem">
                            <label>证件号:</label>
                            <input name="certValue" class="m-info">
                        </div>
                        <div class="fitem">
                            <label>身份证正面:</label>
                            <div id="app-cert-pic1" class="m-info"></div>
                        </div>
                        <div class="fitem">
                            <label>身份证反面:</label>
                            <div id="app-cert-pic2" class="m-info"></div>
                        </div>
                        <div class="fitem">
                            <b style="color:#EA8511">入澳证件信息</b>
                        </div>
                        <div class="fitem">
                            <label>入澳证件类型:</label>
                            <input id="app-entryType" class="m-info">
                        </div>
                        <div class="fitem">
                            <label>签发地点:</label>
                            <input name="entryPlace" class="m-info">
                        </div>
                        <div class="fitem">
                            <label>签发日期:</label>
                            <input name="entryDate" class="m-info">
                        </div>
                        <div class="fitem">
                            <label>有效期至:</label>
                            <input name="entryValidity" class="m-info">
                        </div>
                        <div class="fitem">
                            <label>证件号码:</label>
                            <input name="entryNum" class="m-info">
                        </div>
                        <div class="fitem">
                            <label>有效签注签发日期:</label>
                            <input name="entryEndmtDate" class="m-info">
                        </div>
                        <div class="fitem">
                            <label>有效签注有效期至:</label>
                            <input name="entryEndmtValidity" class="m-info">
                        </div>
                        <div class="fitem">
                            <label>入澳证件个人照:</label>
                            <div id="app-entry-pic1" class="m-info"></div>
                        </div>
                        <div class="fitem">
                            <label>入澳证件签注签发照:</label>
                            <div id="app-entry-pic2" class="m-info"></div>
                        </div>
                    <!--
                        <div class="fitem">
                            <label>证件过期日期:</label>
                            <input name="certExpiryDate" class="m-info">
                        </div>
                    -->
                        <div class="fitem">
                            <label>地址:</label>
                            <input name="address" class="m-info">
                        </div>
                        <div class="fitem">
                            <label>邮编:</label>
                            <input name="postcode" class="m-info">
                        </div>
                        <div class="fitem">
                            <label>固定电话:</label>
                            <input name="tele" class="m-info">
                        </div>
                        <div class="fitem">
                            <label>移动电话:</label>
                            <input name="mobile" class="m-info">
                        </div>
                        <div class="fitem">
                            <label>传真:</label>
                            <input name="fax" class="m-info">
                        </div>
                        <div class="fitem">
                            <b style="color:#EA8511">指定联系人</b>
                        </div>
                        <div class="fitem">
                            <label>联系人姓名:</label>
                            <input id="app-s-full-name" name="s-full-name" class="m-info">
                        </div>
                        <div class="fitem">
                            <label>联系人性别:</label>
                            <input id="app-s-sex" name="s-sex" class="m-info">
                        </div>
                        <div class="fitem">
                            <label>联系人电话:</label>
                            <input id="app-s-contact-no" name="s-contact-no" class="m-info">
                        </div>
                        <div class="fitem">
                            <label>联系人手机:</label>
                            <input type="text" class="m-info" id="app-s-mobile" name="s-mobile">
                        </div>
                        <div class="fitem">
                            <label>联系人邮箱:</label>
                            <input type="text" class="m-info" id="app-s-email" name="s-email">
                        </div>
                        <div class="fitem">
                            <label>联系人传真:</label>
                            <input type="text" class="m-info" id="app-s-fax-no" name="s-fax-no">
                        </div>
                        <div class="fitem">
                            <label>联系人职位:</label>
                            <input id="app-s-position" name="s-position" class="m-info">
                        </div>
                        <div class="fitem">
                            <label>所在行业:</label>
                            <input id="app-industry" class="m-info">
                        </div>
                        <div class="fitem">
                            <label>公司性质:</label>
                            <input id="app-companyType" class="m-info">
                        </div>
                        <div class="fitem">
                            <label>公司名称:</label>
                             <input name="company" class="m-info">
                        </div>
                        <div class="fitem">
                            <label>职位:</label>
                            <input name="position" class="m-info">
                        </div>
                        <div class="fitem">
                            <label>尊教信仰:</label>
                            <input name="religion" class="m-info">
                        </div>
                        <div class="fitem">
                            <label>饮食禁忌:</label>
                            <input name="dietTaboo" class="m-info">
                        </div>
                        <div class="fitem">
                            <label>慢性病史:</label>
                            <input name="medlHis" class="m-info">
                        </div>
                        <div class="fitem">
                            <label>过敏食物:</label>
                            <input name="foodAllergies" class="m-info">
                        </div>
                        <div class="fitem">
                            <label>自我介绍:</label>
                            <input name="selfIntro" class="m-info">
                        </div>
                        <div class="fitem">
                            <label>自我介绍-英文:</label>
                            <input name="selfIntroEn" class="m-info">
                        </div>
                        <div class="fitem">
                            <label>使用的化名:</label>
                            <input name="ualias" class="m-info"">
                        </div>
                        <div class="fitem">
                            <label>使用的职务名称:</label>
                            <input name="positionTitle" class="m-info">
                        </div>
                        <div class="fitem">
                            <label>备注信息:</label>
                            <input name="remark" class="m-info">
                        </div>
                    <!--
                        <div class="fitem">
                            <label>是否需要签证:</label>
                             <input id="app-visaNeed" class="m-info">
                        </div>
                        <div class="fitem">
                            <label>签证城市:</label>
                            <input name="visaCity" class="m-info">
                        </div>
                        <div class="fitem">
                            <label>医疗需求:</label>
                            <input name="medlHis" class="m-info">
                        </div>
                        <div class="fitem">
                            <label>特殊礼节:</label>
                            <input name="etiquette" class="m-info">
                        </div>
                        <div class="fitem">
                            <label>官方语言:</label>
                            <input name="officialLang" class="m-info">
                        </div>
                    -->
                        <div class="fitem">
                            <label>单位会期工作内容:</label>
                            <input name="workContent" class="m-info">
                        </div>
                        <div class="fitem">
                            <label>主管负责人姓名:</label>
                            <input name="superviserName" class="m-info"">
                        </div>
                        <div class="fitem">
                            <label>主管负责人联系方式:</label>
                            <input name="superviserPhone" class="m-info">
                        </div>
                        <div class="fitem">
                            <label>邮箱认证状态:</label>
                            <input id="mailChecked" class="m-info">
                        </div>
                        <div class="fitem">
                            <label>用户审核状态:</label>
                            <input id="app-approveState" class="m-info" >
                        </div>
                        <div class="fitem">
                            <label>身份卡证件类型:</label>
                            <input id="app-cardType" class="m-info">
                        </div>
                    </td>
                    <td class="app-top-td" valign="top">
                        <div class="fitem">
                            <b style="color:#EA8511">用户选择的日程信息:</b>
                            <div id="app-schListDiv"></div>
                        </div>
                        <div>
                            <b style="color:#EA8511;padding:6px 0;display:block;">动态表单信息:</b>
                            <div id="app-userform"></div>
                        </div>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div id="app-dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="approveOK()" style="width:90px">通过审核</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="approveNotOK()" style="width:108px">审核不通过</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-close" onclick="javascript:$('#app-dlg').dialog('close')" id="specific_close" style="width:60px">关闭</a>
    </div>
</body>
</html>
