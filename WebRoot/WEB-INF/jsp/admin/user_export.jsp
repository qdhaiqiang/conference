<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <jsp:include page="../../../include/sys-common.jsp"></jsp:include>
<style type="text/css">
.search-select {
	width: 180px;
}
.m-info{
    margin: 0;
    padding: 4px;
    vertical-align: top;
    border: 1px solid #95B8E7;
    -moz-border-radius: 5px 5px 5px 5px;
    -webkit-border-radius: 5px 5px 5px 5px;
    border-radius: 5px 5px 5px 5px;
    
    border-left:0px;
    border-top:0px;
    border-right:0px;
    border-bottom:0px;
    border-bottom-color:Black;
}

h4 {
	margin: 0 0 7px -12px;
	padding: 7px 0 7px 5px;
	font: bold 12px Times, serif;
	color: #891300;
	border-top: 0;
	background:#E3EFFF;
	border-bottom: 1px dotted #dddad9;
}

#search_form table td{
	padding-bottom:4px;
	padding-top:4px;
}

#ff {
	margin: 0;
	padding: 10px 10px;
}


.search_li {
    margin:10px;
}
ul {
  margin:0px;   
  border-style: solid;
  border-width: 0px;
}
#search_button {
    margin-left:500px;
}
.h_prompt {
    margin-left:0px;
    font-weight:bold;
}
.easyui-validatebox{
	width:182px;
}
.fitem div{
display: inline-block;
width: 200px;
color: black;
word-break: break-all;
}
.fitem input {
width: 220px;
}
.fitem2 div{
display: inline-block;
width: 220px;
color: black;
word-break: break-all;
}
.fitem2 input {
width: 220px;
}
.fitem2 textarea{
height:50px;
width:220px;
}
</style>
  <script type="text/javascript">
    var datagrid;
    var dataList;
    // 选择类型的检索条件的值
    var userType;
    var nation;
    var certType;
    var entryType;
    var companyType;
    var industry;
    // 获取data
    var suserType = "";
    var sname = "";
    var semail = "";
    var ssex = "";
    var snation = "";
    var sreligion = "";
    var sdietTaboo = "";
    var smedlHis = "";
    var sfoodAllergies = "";
    var scertType = "";
    var sentryType = "";
    var sentryEndmtValidity = "";
    var suseRealname = "";
    var suseAlias = "";
    var suseOtherPtitle = "";
    var sneedInvite = "";
    var sneedVisa = "";
    var sneedFaxInvite = "";
    var sneedChinaVisaService = "";
    var sinFromMacau = "";
    var scheckpoint = "";
    var sspecialDemands = "";
    var scultureRoute = "";
    var sbringSpouse = "";
    var sbringEntourage = "";

     $(function(){
    	//获取用户类型数据
         $.ajax({
             url: 'dict/r/user_type',
             method:'get',
             async:false,
             success:function(data) {
                 userType = data;
             }
         });
    	//获取国籍数据
         $.ajax({
             url: 'dict/r/nation',
             method:'get',
             async:false,
             success:function(data) {
            	 nation = data;
             }
         });
    	//获取身份证件类型数据
         $.ajax({
             url: 'dict/r/cert_type',
             method:'get',
             async:false,
             success:function(data) {
            	 certType = data;
             }
         });
    	//获取入澳证件类型数据
         $.ajax({
             url: 'dict/r/entry_type',
             method:'get',
             async:false,
             success:function(data) {
            	 entryType = data;
             }
         });
        // 获取单位性质
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
       // 获取行业类型
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

        //加载检索条件（用户类型）
        $("#s_userType").combobox({
                data : userType,
                valueField:'code',
                textField:'name',
                multiple:true,
                editable:false
//                 ,panelHeight:'auto'
       	});
        //加载检索条件（国籍）
        $("#s_nation").combobox({
                data : nation,
                valueField:'code',
                textField:'name',
                multiple:true,
                editable:false
       	});
        //加载检索条件（身份证件类型）
        $("#s_certType").combobox({
                data : certType,
                valueField:'code',
                textField:'name',
                multiple:true,
                editable:false
       	});
        //加载检索条件（入澳证件类型）
        $("#s_entryType").combobox({
                data : entryType,
                valueField:'code',
                textField:'name',
                multiple:true,
                editable:false
       	});
     });

    $(document).ready(function(){
        $('#table_div').hide();
        $("#submit_search").bind('click',function(){
            if (!$("#search_form").form('validate')) {
                return false;
            }else {
                $('#cc').layout('collapse','north');
                $('#table_div').show();
                getSearchParams();
                loadGrid();
            }
        });
        $("#reset_earch").bind('click',function(){
            $("#s_name").val("");
            $("#s_userType").combobox('setValue',"");
            $("#s_email").val("");
            $("#s_sex").val("");
            $("#s_nation").combobox('setValue',"");
            $("#s_religion").val("");
            $("#s_dietTaboo").val("");
            $("#s_medlHis").val("");
            $("#s_foodAllergies").val("");
            $("#s_certType").combobox('setValue',"");
            $("#s_entryType").combobox('setValue',"");
//             $("#s_entryEndmtValidity").databox('setValue', formatterDate(new Date()));
            $("#s_useRealname").val("");
            $("#s_useAlias").val("");
            $("#s_useOtherPtitle").val("");
            $("#s_needInvite").val("");
            $("#s_needVisa").val("");
            $("#s_needFaxInvite").val("");
            $("#s_needChinaVisaService").val("");
            $("#s_inFromMacau").val("");
            $("#s_checkpoint").val("");
            $("#s_specialDemands").val("");
            $("#s_cultureRoute").val("");
            $("#s_bringSpouse").val("");
            $("#s_bringEntourage").val("");
        });
        
        $("#exportExcelBtn").bind('click',exportExcelFun);
        $("#exportQR").bind('click',exportQRFun);
    });

  </script>

  </head>

  <body>
    <!-- 展示界面Div -->
    <div id="cc" class ="easyui-layout" data-options="region:'center',border:false,fit:true">查询
        <div data-options="region:'north',border:false,title:'注册信息查询导出'" style="height:100%;">
            <form id="search_form">
                <h4 class="h_prompt">个人基本信息条件</h4>
                <table class="tabunion">
                	<tr>
                		<td width="12%;">人员类型:</td>
                		<td width="25%;padding-right:20px;"><input name="userType" type="text" id="s_userType" class="easyui-validatebox" validType="searchParm"/></td>              		
                		<td width="12%;">饮食禁忌:</td>
                		<td width="25%;padding-right:20px;">
	                		<select id="s_dietTaboo" name="dietTaboo" class="search-select">
	                            <option ></option>
	                            <option value="1">有</option>
	                            <option value="0">无</option>
	                        </select>
                		</td>
                		<td width="14%;">邮箱:</td>
                		<td width="25%;"><input name="email" type="text" id="s_email" class=easyui-validatebox validType="searchParm"/></td>
                	</tr>
                	<tr>
                		<td>国籍:</td>
                		<td><input name="nation" type="text" id="s_nation" class="easyui-validatebox" validType="searchParm"/></td>
                		<td>宗教信仰:</td>
                		<td>
	                		<select id="s_religion" name="religion" class="search-select">
	                            <option ></option>
	                            <option value="1">有</option>
	                            <option value="0">无</option>
	                        </select>
                		</td>
                		<td>性别:</td>
                		<td><select id="s_sex" name="sex" class="search-select">
								<option ></option>
								<option value="1">男</option>
								<option value="2">女</option>
		               		</select>
		                </td>
                	</tr>
                	<tr>
                		<td>姓名:</td>
                		<td><input name="name" type="text" id="s_name" class="easyui-validatebox" validType="searchParm"/></td>
                		<td>慢性病或病史:</td>
                		<td>
                			<select id="s_medlHis" name="medlHis" class="search-select">
	                            <option ></option>
	                            <option value="1">有</option>
	                            <option value="0">无</option>
                        	</select>
                        </td>
                		<td>过敏食物:</td>
                		<td>
	                		<select id="s_foodAllergies" name="foodAllergies" class="search-select">
	                            <option ></option>
	                            <option value="1">有</option>
	                            <option value="0">无</option>
	                        </select>
                		</td>
                	</tr>
                </table>
                <h4 class="h_prompt">出入境及陪同条件</h4>
		         <table class="tabunion">
                	<tr>
                		<td width="12%;">赴澳/返程交通方式:</td>
                		<td width="25%;padding-right:20px;">
                		<select id="s_inFromMacau" name="inFromMacau" class="search-select">
							<option ></option>
							<option value="1">飞机</option>
							<option value="2">高铁</option>
		                </select>
                		</td>
                		<td width="12%;">是否需要送关:</td>
                		<td width="25%;padding-right:20px;">
							<select id="s_checkpoint" name="checkpoint" class="search-select">
								<option ></option>
								<option value="1">是</option>
								<option value="0">否</option>
			                </select>
						</td>
                		<td width="14%;">住宿特殊需求(有无):</td>
                		<td width="25%;">
						<select id="s_specialDemands" name="specialDemands" class="search-select">
							<option ></option>
							<option value="1">有</option>
							<option value="0">无</option>
		                </select>
						</td>
                	</tr>
                	<tr>
                		<td>文化考察路线:</td>
		                <td>
		                <select id="s_cultureRoute" name="cultureRoute" class="search-select">
                            <option ></option>
                            <option value="1">路线一</option>
                            <option value="2">路线二</option>
                            <option value="3">不参加</option>
                        </select>
                        </td>
		                <td>是否携带配偶:</td>
		                <td>
		                <select id="s_bringSpouse" name="bringSpouse" class="search-select">
							<option ></option>
							<option value="1">是</option>
							<option value="0">否</option>
		                </select>
		                </td>
		                <td>是否随行人员陪同出席:</td>
		                <td>
		                <select id="s_bringEntourage" name="bringEntourage" class="search-select">
							<option ></option>
							<option value="1">是</option>
							<option value="0">否</option>
		                </select>
		                </td>
                	</tr>
                </table>
                <h4 class="h_prompt">证件职务信息条件</h4>
                <table class="tabunion">
                	<tr>
                		<td width="12%;">有效证件类型:</td>
                		<td width="25%;"><input name="certType" type="text" id="s_certType" class="easyui-validatebox" validType="searchParm"/></td>
                		<td width="12%;">入澳证件类型:</td>
                		<td width="25%;"><input name="entryType" type="text" id="s_entryType" class="easyui-validatebox" validType="searchParm"/></td>
                		<td width="14%;">入澳证件信息-有效签注有效期:</td>
                		<td width="25%;"><input name="entryEndmtValidity" type="text" id="s_entryEndmtValidity" class="easyui-datebox" validType="searchParm"/></td>
               		</tr>
                	<tr>
                	
                		<td>是否愿意公布真实姓名和职务:</td>
                		<td><select id="s_useRealname" name="useRealname" class="search-select">
							<option ></option>
							<option value="1">是</option>
							<option value="0">否</option>
		                </select>
		                </td>             		
                		<td>是否愿意使用化名:</td>
                		<td><select id="s_useAlias" name="useAlias" class="search-select">
							<option ></option>
							<option value="1">是</option>
							<option value="0">否</option>
		                </select></td>
                		<td>是否愿意使用替代职务:</td>
                		<td><select id="s_useOtherPtitle" name="useOtherPtitle" class="search-select">
							<option ></option>
							<option value="1">是</option>
							<option value="0">否</option>
		                </select></td>
                	</tr>
                	<tr>
                		<td>是否需要主办方提供官方邀请函:</td>
                		<td><select id="s_needInvite" name="needInvite" class="search-select">
							<option ></option>
							<option value="1">是</option>
							<option value="0">否</option>
		                </select></td>
                		<td>是否需要会前协助办理入境签证:</td>
                		<td><select id="s_needVisa" name="needVisa" class="search-select">
							<option ></option>
							<option value="1">是</option>
							<option value="0">否</option>
		                </select></td>
                		<td>是否需要组委会传真官方邀请函:</td>
                		<td>
                		<select id="s_needFaxInvite" name="needFaxInvite" class="search-select">
							<option ></option>
							<option value="1">是</option>
							<option value="0">否</option>
		               	 </select>
		                </td>
                	</tr>
                	<tr>
                		<td>是否需要中国大陆地区签证办理咨询服务:</td>
                		<td><select id="s_needChinaVisaService" name="needChinaVisaService" class="search-select">
							<option ></option>
							<option value="1">是</option>
							<option value="0">否</option>
		                </select></td>
                	</tr>
                </table>	
		         
                <div id="search_button" style="margin-top:10px;">
	                <input class="search-btn" id="submit_search" type="button" value="查询" class="button"/>
	                <input class="search-btn" id="reset_earch" type="button" value="重置" class="button"/>
	            </div>
            </form>
            <form id="downform" method="post">
            <!-- onclick="exportExcelFun()" -->
        		<span id="exportExcelBtn" class="easyui-linkbutton">导出excel表格</span>
        		<span id="exportQR" class="easyui-linkbutton">导出所有人员的二维码</span>
        	</form>
        </div>
        <!--用户列表 -->
        <div data-options="region:'center',border:false" id="table_div">
	        <table id="dg" title="人员列表" style="width:100%;height:100%">
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
		                <th data-options="field:'email', width:'150'">邮箱</th>
		                <th data-options="field:'company', width:'150'">公司名称</th>
		                <th data-options="field:'approve', width:'80', formatter:userDetail">操作</th>
		            </tr>
		        </thead>
	        </table>
	        <div id="toolbar">
	            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="exportSelectedUser()">导出</a>
            </div>
        </div>

	    <div id="dlg" class="easyui-dialog"
	        style="width:85%;height:100%;padding:10px 20px" closed="true"
	        maximizable="true" resizable="true" collapsible="true"
	        left="100px" top="0"
	        buttons="#dlg-buttons">
	          <div data-options="region:'center'">
	            <form id="ff" method="post">
		           <table cellspacing="30px">
		                <tr>
		                    <th class="app-top-td" style="color:#EA8511;font-size:14px;">个人基本信息</th>
		                    <th class="app-top-td" style="color:#EA8511;font-size:14px;">会议日程相关信息</th>
		                </tr>
		                <tr>
	                    <td class="app-top-td" valign="top" style="border:1px solid #8CC63F;padding:4px;">
	                        <div class="fitem" style="border:none;">
	                            <input name="userId" id="userId" type="hidden">
	                        </div>
	                        <div class="fitem">
	                            <label>用户类型:</label>
	                            <div id="det_userType" class="m-info"></div>
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
	                            <label>邮箱:</label>
	                            <input name="email" class="m-info">
	                        </div>
	                        <div class="fitem">
	                            <label>性别:</label>
	                            <div id="sex" class="m-info"></div>
	                        </div>
	                        <div class="fitem">
	                            <label>出生日期:</label>
	                            <input name="birth" class="m-info">
	                        </div>
	                        <div class="fitem">
	                            <label>国籍:</label>
	                            <div id="det_nation" class="m-info"></div>
	                        </div>
	                        <div class="fitem">
	                            <label>宗教信仰:</label>
	                            <input name="religion" class="m-info">
	                        </div>
	                        <div class="fitem">
	                            <label>饮食禁忌:</label>
	                            <div class="m-info"></div>
	                            <input name="dietTaboo" class="m-info" type="hidden">
	                        </div>
	                        <div class="fitem">
	                            <label>慢性病史:</label>
	                            <div class="m-info"></div>
	                            <input name="medlHis" class="m-info" type="hidden">
	                        </div>
	                        <div class="fitem">
	                            <label>过敏食物:</label>
	                            <div class="m-info"></div>
	                            <input name="foodAllergies" class="m-info" type="hidden">
	                        </div>
	                        <div class="fitem">
	                            <label>通讯地址:</label>
	                            <div class="m-info"></div>
	                            <input name="address" class="m-info" type="hidden">
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
	                            <label>指定联系人姓名:</label>
	                            <div id="det_s_full_name" name="s-full-name" class="m-info"></div>
	                        </div>
	                        <div class="fitem">
	                            <label>指定联系人性别:</label>
	                            <div id="det_s_sex" name="s-sex" class="m-info"></div>
	                        </div>
	                        <div class="fitem">
	                            <label>指定联系人电话:</label>
	                            <div id="det_s_contact_no" name="s-contact-no" class="m-info"></div>
	                        </div>
	                        <div class="fitem">
	                            <label>指定联系人手机:</label>
	                            <div type="text" class="m-info" id="det_s_mobile" name="s-mobile"></div>
	                        </div>
	                        <div class="fitem">
	                            <label>指定联系人邮箱:</label>
	                            <div type="text" class="m-info" id="det_s_email" name="s-email"></div>
	                        </div>
	                        <div class="fitem">
	                            <label>指定联系人传真:</label>
	                            <div type="text" class="m-info" id="app_s_fax_no" name="s-fax-no"></div>
	                        </div>
	                        <div class="fitem">
	                            <label>指定联系人职务:</label>
	                            <div id="det_s_position" name="s-position" class="m-info"></div>
	                        </div>
	                        <div class="fitem">
	                            <label>嘉宾配偶信息:</label>
	                            <textarea id="spouseInfo" name="spouseInfo" class="m-info" style="height:50px;width:220px;"></textarea>
	                        </div>
	                        <div class="fitem">
	                            <label>嘉宾随行人员信息:</label>
	                            <textarea id="entourageInfo" class="m-info" style="height:180px;width:220px;"></textarea>
	                        </div>
	                        <div class="fitem">
	                            <label>有效证件类型:</label>
	                            <div id="det_certType" class="m-info"></div>
	                        </div>
	                        <div class="fitem">
	                            <label>有效证件号码:</label>
	                            <input name="certValue" class="m-info">
	                        </div>
	                        <div class="fitem">
	                            <label>入澳证件类型:</label>
	                            <div id="det_entryType" class="m-info"></div>
	                        </div>
	                        <div class="fitem">
	                            <label>签发地点:</label>
	                            <div class="m-info"></div>
	                            <input name="entryPlace" class="m-info" type="hidden">
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
	                            <label>所在单位名称:</label>
	                             <input name="company" class="m-info">
	                        </div>
	                        <div class="fitem">
	                            <label>单位性质:</label>
	                            <div id="det_companyType" class="m-info"></div>
	                        </div>
	                        <div class="fitem">
	                            <label>所在单位所属行业:</label>
	                            <div id="det_industry" class="m-info"></div>
	                        </div>
	                        <div class="fitem">
	                            <label>个人职务:</label>
	                            <input name="position" class="m-info">
	                        </div>
	                        <div class="fitem">
	                            <label>自我介绍-中文:</label>
	                            <textarea name="selfIntro" class="m-info" style="height:100px;width:220px;"></textarea>
	                        </div>
	                        <div class="fitem">
	                            <label>自我介绍-英文:</label>
	                            <textarea name="selfIntroEn" class="m-info" style="height:100px;width:220px;"></textarea>
	                        </div>
	                    </td>
	                    <td class="app-top-td" valign="top" style="border:1px solid #8CC63F;padding:4px;">
	                        <div class="fitem2">
	                            <label>是否愿意公布真实姓名和职务:</label>
	                            <input id="det_useRealname" class="m-info">
	                        </div>
	                        <div class="fitem2">
	                            <label>是否使用化名:</label>
	                            <input id="det_useAlias" class="m-info">
	                        </div>
	                        <div class="fitem2">
	                            <label>化名:</label>
	                            <input name="ualias" class="m-info">
	                        </div>
	                        <div class="fitem2">
	                            <label>是否使用其他职务名称:</label>
	                            <input id="det_useOtherPtitle" class="m-info">
	                        </div>
	                        <div class="fitem2">
	                            <label>职务名称:</label>
	                            <input name="positionTitle" class="m-info">
	                        </div>
	                        <div class="fitem2">
	                            <label>备注信息:</label>
	                            <div class="m-info"></div>
	                            <input name="remark" class="m-info" type="hidden">
	                        </div>
	                        <div class="fitem2">
	                            <label>是否需要主办方提供官方邀请函:</label>
	                            <input name="needInvite" class="m-info">
	                        </div>
	                        <div class="fitem2">
	                            <label>是否需要会前协助办理入境签证:</label>
	                            <input name="needVisa" class="m-info">
	                        </div>
	                        <div class="fitem2">
	                            <label>是否需要组委会传真官方邀请函:</label>
	                            <input name="needFaxInvite" class="m-info">
	                        </div>
	                        <div class="fitem2">
	                            <label>是否需要中国大陆地区签证办理咨询服务:</label>
	                            <div id="needChinaVisaService" class="m-info"></div>
	                        </div>
		                    <div class="fitem2">
		                        <label>出发地区:</label>
		                        <div class="m-info"></div>
		                        <input name="departureRegion" class="m-info" type="hidden">
		                    </div>
		                    <div class="fitem2">
		                        <label>赴澳/返程交通方式:</label>
		                        <div class="m-info"></div>
		                        <input name="inFromMacau" class="m-info" type="hidden">
		                    </div>
		                    <div class="fitem2">
		                        <label>启程出发地:</label>
		                        <div class="m-info"></div>
		                        <input name="departurePlace" class="m-info" type="hidden">
		                    </div>
		                    <div class="fitem2">
		                        <label>启程出发日期:</label>
		                        <input name="departureDate" class="m-info">
		                    </div>
		                    <div class="fitem2">
		                        <label>启程出发时间段:</label>
		                        <input name="departureTimePeriod" class="m-info">
		                    </div>
		                    <div class="fitem2">
		                        <label>返程地:</label>
		                        <div class="m-info"></div>
		                        <input name="returnPlace" class="m-info" type="hidden">
		                    </div>
		                    <div class="fitem2">
		                        <label>返程日期:</label>
		                        <input name="returnDate" class="m-info">
		                    </div>
		                    <div class="fitem2">
		                        <label>返程出发时间段:</label>
		                        <input name="returnTimePeriod" class="m-info">
		                    </div>
		                    <div class="fitem2">
		                        <label>是否需要送关:</label>
		                        <input name="checkpoint" class="m-info">
		                    </div>
		                    <div class="fitem2">
		                        <label>酒店服务（常规）:</label>
		                        <input name="normalRoomService" class="m-info">
		                    </div>
		                    <div class="fitem2">
		                        <label>酒店服务（延时一日）:</label>
		                        <input name="normalRoomExtra" class="m-info">
		                    </div>
		                    <div class="fitem2">
		                        <label>住宿特殊需求:</label>
		                        <div class="m-info"></div>
		                        <input name="specialDemands" class="m-info" type="hidden">
		                    </div>
		                    <div class="fitem2">
		                        <label>文化考察路线:</label>
		                        <div class="m-info"></div>
		                        <input name="cultureRoute" class="m-info" type="hidden">
		                    </div>
		                    <div class="fitem2">
		                        <label>演讲题目:</label>
		                        <div class="m-info"></div>
		                        <input name="speechesTitle" class="m-info" type="hidden">
		                    </div>
		                    <div class="fitem2">
		                        <label>是否需要PPT演示:</label>
		                        <input name="PPTNeed" class="m-info">
		                    </div>
		                    <div class="fitem2">
		                        <label>是否携带配偶:</label>
		                        <input name="bringSpouse" class="m-info">
		                    </div>
		                    <div class="fitem2">
		                        <label>配偶电子邮箱:</label>
		                        <input name="spouseEmail" class="m-info">
		                    </div>
		                    <div class="fitem2">
		                        <label>是否随行人员陪同出席:</label>
		                        <input name="bringEntourage" class="m-info">
		                    </div>
		                    <div class="fitem2">
		                        <label>随行人员数量:</label>
		                        <input name="entourageNum" class="m-info">
		                    </div>
		                    <div class="fitem2">
		                        <label>随行电子邮箱:</label>
		                        <div class="m-info"></div>
		                        <input name="entourageEmail" class="m-info" type="hidden">
		                    </div>
		                    <div class="fitem2">
		                        <label>希望组委会了解的重要信息:</label>
		                        <div class="m-info"></div>
		                        <input name="importInfo" class="m-info" type="hidden">
		                    </div>
		                    <div class="fitem2">
		                        <label>对某位或某几位嘉宾采访申请:</label>
		                        <div class="m-info"></div>
		                        <input name="interviewGuest" class="m-info" type="hidden">
		                    </div>
		                    <div class="fitem2">
		                        <label>是否和特邀嘉宾同一联系人:</label>
		                        <input name="sameContactPerson" class="m-info">
		                    </div>
		                    <div class="fitem2">
		                        <label>是否陪同嘉宾参加会期活动:</label>
		                        <input name="attendConference" class="m-info">
		                    </div>
		                    <div class="fitem2">
		                        <label>计划参加活动:</label>
		                        <div class="m-info"></div>
		                        <input name="activities" class="m-info"  type="hidden">
		                    </div>
		                    <div class="fitem2">
		                        <label>是否同特邀嘉宾同一线路往返澳门:</label>
		                        <input name="arrivedLeave" class="m-info">
		                    </div>
		                    <div class="fitem2">
		                        <label>是否需要组委会协助预定往返票务:</label>
		                        <input name="roundTrip" class="m-info">
		                    </div>
		                    <div class="fitem2">
		                        <label>其他需求:</label>
		                        <div class="m-info"></div>
		                        <input name="otherNeeds" class="m-info"  type="hidden">
		                    </div>
		                    <div class="fitem2">
		                        <label>展品类型:</label>
		                        <input name="exhibitsType" class="m-info">
		                    </div>
		                    <div class="fitem2">
		                        <label>展位简介:</label>
		                        <div class="m-info"></div>
		                        <input name="standIntro" class="m-info"  type="hidden">
		                    </div>
		                    <div class="fitem2">
		                        <label>主导产品介绍:</label>
		                        <div class="m-info"></div>
		                        <input name="mainProduct" class="m-info"  type="hidden">
		                    </div>
		                    <div class="fitem2">
		                        <label>预申请展位数量:</label>
		                        <input name="quantity" class="m-info">
		                    </div>
		                    <div class="fitem2">
		                        <label>展位布置特殊需求:</label>
		                        <div class="m-info"></div>
		                        <input name="special" class="m-info" type="hidden">
		                    </div>
		                    <div class="fitem2">
		                        <label>广告申请类别:</label>
		                        <input name="advertise" class="m-info">
		                    </div>
		                    <div class="fitem2">
		                        <label>友情赞助类别:</label>
		                        <input name="sponsor" class="m-info">
		                    </div>
		                    <div class="fitem2">
		                        <label>独家视频播放媒体:</label>
		                        <div class="m-info"></div>
		                        <input name="video" class="m-info" type="hidden">
		                    </div>
		                    <div class="fitem2">
		                        <label>从哪种渠道得知会议消息:</label>
		                        <div class="m-info"></div>
		                        <input name="channels" class="m-info" type="hidden">
		                    </div>
		                    <div class="fitem2">
		                        <label>希望参会后收获:</label>
		                        <div class="m-info"></div>
		                        <input name="anticipation" class="m-info" type="hidden">
		                    </div>
		                    <div class="fitem2">
		                        <label>本次参会的目的:</label>
		                        <div class="m-info"></div>
		                        <input name="purposes" class="m-info" type="hidden">
		                    </div>
		                    <div class="fitem2">
		                        <label>希望本次会议现场气氛的感觉:</label>
		                        <div class="m-info"></div>
		                        <input name="feelings" class="m-info" type="hidden">
		                    </div>
		                    <div class="fitem2">
		                        <label>最关注中国到底中医药产品展览会的哪方面:</label>
		                        <div class="m-info"></div>
		                        <input name="careAbout" class="m-info" type="hidden">
		                    </div>
		                    <div class="fitem2">
		                        <label>对本次参会网络注册方式的意见:</label>
		                        <div class="m-info"></div>
		                        <input name="opitions" class="m-info" type="hidden">
		                    </div>
		                    <div class="fitem2" style="border-bottom:none;">
		                        <label>对本届会议有何建议:</label>
		                        <div class="m-info"></div>
		                        <input name="suggestions" class="m-info" type="hidden">
		                    </div>
		                    </td>
	                    </tr>
                    </table>
	            </form>
	          </div>
	        <div id="dlg-buttons">
	            <button class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">关闭</button>
	        </div>
	    </div>
     </div>
     <script type="text/javascript">
     function exportExcelFun(){
        getSearchParams();
        
        var userTypes = "";
        for(var i=0;i<suserType.length;i++){
            userTypes += suserType[i] + ",";
        }
        var nations = "";
        for(var i=0;i<snation.length;i++){
            nations += snation[i] + ",";
        }
        var certTypes = "";
        for(var i=0;i<scertType.length;i++){
            certTypes += scertType[i] + ",";
        }
        var entryTypes = "";
        for(var i=0;i<sentryType.length;i++){
            entryTypes += sentryType[i] + ",";
        }
        
        
        $('#downform').form('submit', {
            url : "userExport/exportUserFile",
            method : "POST",
            onSubmit: function(param){
            	
            	param.name = sname;
                param.email = semail;

                param.userType = userTypes;
                param.sex = ssex;

                param.nation = nations;
                param.religion = sreligion;
                param.dietTaboo = sdietTaboo;
                param.medlHis = smedlHis;
                param.foodAllergies = sfoodAllergies;
                
                param.certType = certTypes;
                
                param.entryType = entryTypes;
                param.entryEndmtValidity = sentryEndmtValidity;
                param.useRealname = suseRealname;
                param.useAlias = suseAlias;
                param.useOtherPtitle = suseOtherPtitle;
                param.needInvite = sneedInvite;
                param.needVisa = sneedVisa;
                param.needFaxInvite = sneedFaxInvite;
                param.needChinaVisaService = sneedChinaVisaService;
                param.inFromMacau = sinFromMacau;
                param.checkpoint = scheckpoint;
                param.specialDemands = sspecialDemands;
                param.cultureRoute = scultureRoute;
                param.bringSpouse = sbringSpouse;
                param.bringEntourage = sbringEntourage;
            },
            success : function(result) {
                $.messager.alert('提示', result);
            }
        });
    }
    
    function getSearchParams(){
        sname = $("#s_name").val().replace(" ","");
        suserType = $("#s_userType").combobox("getValues");
        semail = $("#s_email").val().replace(" ","");
        ssex = $("#s_sex").val();
        snation = $("#s_nation").combobox("getValues");
        sreligion = $("#s_religion").val();
        sdietTaboo = $("#s_dietTaboo").val();
        smedlHis = $("#s_medlHis").val();
        sfoodAllergies = $("#s_foodAllergies").val();
        scertType = $("#s_certType").combobox("getValues");
        sentryType = $("#s_entryType").combobox("getValues");
        sentryEndmtValidity = $("#s_entryEndmtValidity").datebox("getValue");
        suseRealname = $("#s_useRealname").val();
        suseAlias = $("#s_useAlias").val();
        suseOtherPtitle = $("#s_useOtherPtitle").val();
        sneedInvite = $("#s_needInvite").find("option:selected").text();
        sneedVisa = $("#s_needVisa").find("option:selected").text();
        sneedFaxInvite = $("#s_needFaxInvite").find("option:selected").text();
        sneedChinaVisaService = $("#s_needChinaVisaService").find("option:selected").text();
        sinFromMacau = $("#s_inFromMacau").val();
        scheckpoint = $("#s_checkpoint").find("option:selected").text();
        sspecialDemands = $("#s_specialDemands").val();
        scultureRoute = $("#s_cultureRoute").find("option:selected").text();
        sbringSpouse = $("#s_bringSpouse").find("option:selected").text();
        sbringEntourage = $("#s_bringEntourage").find("option:selected").text();
    }

    function loadGrid() {
        datagrid = $('#dg').datagrid({
            url: 'userExport/findByCond',
            method:'POST',
            nowrap:false,
            loadMsg:'加载中，请稍候...',
            fitColumns:true,
            pagination : true,//页码
            pageNumber : 1,//初始页码
            pageSize : 15,
            pageList : [15,30,45,60],
            striped : true,
            rownumbers : true,
            singleSelect : false,
            idField:'userId',
            multiSort:true,
            fit:true,
            nowrap:false,
            toolbar:'#toolbar',
            pagination:true,
            detailFormatter:function(index,row){
                return '<div style="padding:5px"><table id="ddv-' + index + '"></table></div>';
            },
            pagination:true,
            onBeforeLoad:function(param){
                param.name = sname;
                param.email = semail;
                var userTypes = "";
                for(var i=0;i<suserType.length;i++){
                    userTypes += suserType[i] + ",";
                }
                param.userType = userTypes;
                param.sex = ssex;
                var nations = "";
                for(var i=0;i<snation.length;i++){
                    nations += snation[i] + ",";
                }
                param.nation = nations;
                param.religion = sreligion;
                param.dietTaboo = sdietTaboo;
                param.medlHis = smedlHis;
                param.foodAllergies = sfoodAllergies;
                var certTypes = "";
                for(var i=0;i<scertType.length;i++){
                    certTypes += scertType[i] + ",";
                }
                param.certType = certTypes;
                var entryTypes = "";
                for(var i=0;i<sentryType.length;i++){
                    entryTypes += sentryType[i] + ",";
                }
                param.entryType = entryTypes;
                param.entryEndmtValidity = sentryEndmtValidity;
                param.useRealname = suseRealname;
                param.useAlias = suseAlias;
                param.useOtherPtitle = suseOtherPtitle;
                param.needInvite = sneedInvite;
                param.needVisa = sneedVisa;
                param.needFaxInvite = sneedFaxInvite;
                param.needChinaVisaService = sneedChinaVisaService;
                param.inFromMacau = sinFromMacau;
                param.checkpoint = scheckpoint;
                param.specialDemands = sspecialDemands;
                param.cultureRoute = scultureRoute;
                param.bringSpouse = sbringSpouse;
                param.bringEntourage = sbringEntourage;
            },
        });
    }

     //设定性别
     function getSexValue(value, row, index){
         if (row.sex == 1) {
             return "男";
         } else if (row.sex == 2) {
             return "女";
         }
      }

     //查看详细链接拼写
     function userDetail(value, row, index) {
        return '<a onclick="detail()" style="cursor:pointer;"><span style="color:blue;">查看详情</span></a>';
     }

     //嘉宾详细信息
     function detail() {
         var row = $('#dg').datagrid('getSelected');
         if (!row) {
            $.messager.alert('提示', "请选中一条记录再查看详细信息！");
            return;
         }
         $('.m-info').attr('readonly','true');
         $('#dlg').dialog('open').dialog('setTitle', '人员详细信息');
         loadSelectData(row);
         $('#ff').form('load', row);
     }
	     //得到当前日期
    function formatterDate(date) {
        var day = date.getDate();
        var month = date.getMonth();
        return date.getFullYear() + '-' + month + '-' + day;
    };
      //加载个人选择型信息
      function loadSelectData(row){
         var userType2 = getUserType(row.userType);
         var nation = getNation(row.nation);
         var certType = getCertType(row.certType);
         var industryType = getIndustryType(row.industry);
         var companyType = getCompanyType(row.compnyType);
         var entryType = getEntryType(row.entryType);
         //国籍、证件类型、行业类型处理
         $('#det_userType').html(userType2);
         $('#det_nation').html(nation);
         $('#det_certType').html(certType);
         $('#det_industry').html(industryType);
         //入港通行证处理
         $('#det_entryType').html(entryType);
         $('#det_companyType').html(companyType);
         //处理指定联系人
         var sContact = row.contactPerson;
         if (sContact != "") {
             var jsonObj = eval('(' + sContact + ')');
             if (jsonObj != "{}") {
                 $("#det_s_full_name").html(jsonObj.fullName);

                 if(jsonObj.sex == 1){
                     $("#det_s_sex").html("男");
                 }else if(jsonObj.sex == 2){
                     $("#det_s_sex").html("女");
                 }
                 $("#det_s_position").html(jsonObj.position);
                 $("#det_s_contact_no").html(jsonObj.contactNo);
                 $("#det_s_email").html(jsonObj.email);
                 $("#app_s_fax_no").html(jsonObj.faxNo);
                 $("#det_s_mobile").html(jsonObj.mobile);
             }
         }
         //性别处理
         if (row.sex == 1) {
             $('#sex').html('男');
         } else if (row.sex == 2) {
             $('#sex').html('女');
         }
         //是否公布真实姓名和职务
         if (row.useRealname == 0) {
            $('#det_useRealname').val( '否');
         } else if (row.useRealname == 1) {
            $('#det_useRealname').val( '是');
         }
         //是否使用化名
         if (row.useAlias == 0) {
            $('#det_useAlias').val( '否');
         } else if (row.useAlias == 1) {
            $('#det_useAlias').val( '是');
         }
         //是否使用其他职务名称
         if (row.useOtherPtitle == 0) {
            $('#det_useOtherPtitle').val( '否');
         } else if (row.useOtherPtitle == 1) {
            $('#det_useOtherPtitle').val( '是');
         }
         
         //处理嘉宾的随行人员信息
         var entourageInfo = row.entourageInfo;
         var entourage = new Array();
         var entourages = "";
         if (entourageInfo != null && entourageInfo != '') {
            entourage = entourageInfo.split(",");
         }
         for (var i = 0;i<entourage.length;i++) {
            entourages += "随行人员" + (i+1) + ": " + entourage[i] + "\r\n";
         }
         $("#entourageInfo").val(entourages);
		//饮食禁忌:
		$("input[name='dietTaboo']").prev().html(row.dietTaboo);
		//慢性病史
		$("input[name='medlHis']").prev().html(row.medlHis);
		//过敏食物:
		$("input[name='foodAllergies']").prev().html(row.foodAllergies);
        //通讯地址:
        $("input[name='address']").prev().html(row.address);
  		//签发地点:
        $("input[name='entryPlace']").prev().html(row.entryPlace);
        //备注信息
        $("input[name='remark']").prev().html(row.remark);
        //出发地区:
 		$("input[name='departureRegion']").prev().html(row.departureRegion);
        //赴澳/返程交通方式 
        $("input[name='inFromMacau']").prev().html(row.inFromMacau);
        //启程出发地
        $("input[name='departurePlace']").prev().html(row.departurePlace);
    	//返程地:
    	$("input[name='returnPlace']").prev().html(row.returnPlace);
    	//住宿特殊需求:
    	$("input[name='specialDemands']").prev().html(row.specialDemands);
        //文化考察路线:
    	$("input[name='cultureRoute']").prev().html(row.cultureRoute);
        //演讲题目:
    	$("input[name='speechesTitle']").prev().html(row.speechesTitle);
    	//随行电子邮箱
    	$("input[name='entourageEmail']").prev().html(row.entourageEmail);
        //希望组委会了解的重要信息
    	$("input[name='importInfo']").prev().html(row.importInfo);
        //对某位或某几位嘉宾采访申请
    	$("input[name='interviewGuest']").prev().html(row.interviewGuest);
    	//计划参加活动
    	$("input[name='activities']").prev().html(row.activities);
       //其他需求
    	$("input[name='otherNeeds']").prev().html(row.otherNeeds);
         //展位简介
    	$("input[name='standIntro']").prev().html(row.standIntro);
        //主导产品介绍
    	$("input[name='mainProduct']").prev().html(row.mainProduct);
    	//展位布置特殊需求
        $("input[name='special']").prev().html(row.special);
        //独家视频播放媒体
        $("input[name='video']").prev().html(row.video);
    	//从哪种渠道得知会议消息:
		$("input[name='channels']").prev().html(row.channels);
        //希望参会后收获
		$("input[name='anticipation']").prev().html(row.anticipation);
        //本次参会的目的
		$("input[name='purposes']").prev().html(row.purposes);
        //希望本次会议现场气氛的感觉
		$("input[name='feelings']").prev().html(row.feelings);
        //最关注中国到底中医药产品展览会的哪方面
		$("input[name='careAbout']").prev().html(row.careAbout);
        //对本次参会网络注册方式的意见
		$("input[name='opitions']").prev().html(row.opitions);
        //对本届会议有何建议
		$("input[name='suggestions']").prev().html(row.suggestions);
      }
     
     //用户类型匹配
     function getUserType(value) {
        for (var i=0; i<userType.length; i++) {
            if (value == userType[i].code) {
                return userType[i].name;
            }
        }
     }
     
     // 国家名称匹配
    function getNation(value) {
       for (var i=0; i<nation.length; i++) {
           if (value == nation[i].code) {
               return nation[i].name;
           }
       }
   }
    // 证件类型匹配
    function getCertType(value) {
       for (var i=0; i<certType.length; i++) {
           if (value == certType[i].code) {
               return certType[i].name;
           }
       }
   }
    // 入港通行证证件类型匹配
    function getEntryType(value) {
        for (var i=0; i<entryType.length; i++) {
            if (value == entryType[i].code) {
                return entryType[i].name;
            }
        }
    }
    // 证件类型匹配
    function getIndustryType(value) {
       for (var i=0; i<industry.length; i++) {
           if (value == industry[i].code) {
               return industry[i].name;
           }
       }
   }
    // 证件类型匹配
    function getCompanyType(value) {
       for (var i=0; i<companyType.length; i++) {
           if (value == companyType[i].code) {
               return companyType[i].name;
           }
       }
   }
   
   //导出选择的人员
   function exportSelectedUser(row) {
        var rows = $('#dg').datagrid('getSelections');
        if (!rows || rows.length == 0) {
            $.messager.alert('提示', "请至少选中一条记录再操作");
            return;
        }
        var ids = "";
        for(var i=0; i<rows.length; i++){//组成一个字符串，ID主键之间用逗号隔开
            if(i!=rows.length-1){
                ids=ids+rows[i].userId+",";
             }else{
                 ids=ids+rows[i].userId;
             }
        }
        $('#downform').form('submit', {
	        url : "userExport/exportSelectedUser",
	        method : "POST",
	        onSubmit: function(param){
                param.userIds = ids;
                
            },
            success : function(result) {
                $.messager.alert('提示', result);
            }
        });
   }
   
   function exportQRFun() {
       $('#downform').form('submit', {
            url : 'userExport/exportQRInfo',
            method : "POST",
            success : function(result) {
                $.messager.alert('提示', result);
            }
       });
   }
  </script>
  </body>
</html>
