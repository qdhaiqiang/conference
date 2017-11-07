<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
 
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=basePath%>css/exhibition.css" rel="stylesheet">
	<jsp:include page="../../../../include/common.jsp"/>
	<script src="<%=basePath%>js/jquery.min.js"></script>
	<script src="<%=basePath%>js/jquery.smartWizard-2.0.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		// Smart Wizard 	
		$('#wizard').smartWizard();
	
		function onFinishCallback(){
			$('#wizard').smartWizard('showMessage','Finish Clicked');
		}
	  
	});
	</script>
  </head>
  
  <body style="background:#FBFAF8;">
  	<div class="cont_exhi" style="height:auto;">
	  	<img src="<%=basePath %>images/reg_banner.jpg" />
	    <div class="exhibitionPages">
	    <div class="actBar">
	    	<h3>展位申请流程</h3>
	    	<form>
				<div id="wizard" class="swMain">
					<ul>
						<li><a href="#step-1"><span class="stepDesc">行业选择 ></span></a></li>
						<li><a href="#step-2"><span class="stepDesc">单位 ></span></a></li>
						<li><a href="#step-3"><span class="stepDesc">展台细节 ></span></a></li>
						<li><a href="#step-4"><span class="stepDesc">展位楣板及标摊设施 ></span></a></li>
						<li><a href="#step-5"><span class="stepDesc">额外家具及杂项租赁 ></span></a></li>		
						<li><a href="#step-6"><span class="stepDesc">展品信息 ></span></a></li>
						<li><a href="#step-7"><span class="stepDesc"> 物流></span></a></li>
						<li><a href="#step-8"><span class="stepDesc">确认申请</span></a></li>
					</ul>
					<div id="step-1" style="text-align:center;">
						<table class="swtable">
							<tr>
								<td>所属行业</td>
								<td><input type="text" id="" value="" name="industry" class="formcontrol"></td>
							</tr>
							<tr>
								<td>所在单位所属行业类别</td>
								<td><input type="text" id="" value="" name="industry" class="formcontrol"></td>
							</tr>
						</table>		
					</div>
					<div id="step-2">
						<table class="swtable">
							<tr>
								<td>所在单位名称</td>
								<td><input type="text" id="" value="" name="industry" class="formcontrol"></td>
							</tr>
							<tr>
								<td>单位性质</td>
								<td><input type="text" id="" value="" name="industry" class="formcontrol"></td>
							</tr>
							<tr>
								<td>个人职务</td>
								<td><input type="text" id="" value="" name="industry" class="formcontrol"></td>
							</tr>
						</table>	
					</div>                      
					<div id="step-3">
						<table class="swtable">
							<tr style="border-bottom:2px solid #8CC63F;">
								<th>展位类型</th>
								<th>价格</th>
								<th>剩余数量</th>
								<th>租用数量</th>
								<th>展台服务费</th>
							</tr>
							<tr style="line-height:30px;">
								<td>标准摊位 3m*3m</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td><img src="<%=basePath %>images/tw_sample.png" width="80" height="80" /></td>
								<td>***元人民币</td>
								<td>50个</td>
								<td>1,2,3,4……</td>
								<td>人民币：3980元</td>
							</tr>
						</table>
					</div>
					<div id="step-4">
						<label>	展位楣板名称：以不超过24 个中文字及40 个英文字母为限</label><br/>
						<textarea name="lintelName">${selectedBooth.lintelName }</textarea><br/>
						<img src="<%=basePath %>images/exhibition/u212.png"/>
					</div>
					<div id="step-5">
						<table class="swtable">
							<tr style="border-bottom:2px solid #8CC63F;">
								<th>代号</th>
								<th>品名</th>
								<th>价格（人民币）</th>
								<th>剩余数量</th>
								<th>租用数量</th>
								<th>展台服务费</th>
							</tr>
							<tr style="border-bottom:1px dotted #8CC63F;line-height:40px;">
								<td width="10%">F01</td>
								<td width="34%">有锁地柜<br/>
									(1000mm阔x500mm深x750mm高)
								</td>
								<td width="10%">280</td>
								<td width="15%">50个</td>
								<td width="15%">1,2,3,4……</td>
								<td>人民币：3980元</td>
							</tr>
							<tr style="line-height:40px;">
								<td>F02</td>
								<td>有锁地柜<br/>
									(1000mm阔x500mm深x750mm高)
								</td>
								<td>280</td>
								<td>50个</td>
								<td>1,2,3,4……</td>
								<td>人民币：3980元</td>
							</tr>
						</table>
						<br/><br/>
						<p>備註:</p>
						<p>	1) 所有租賃定單必須由指定供應商提供和安裝</p>
						<p>	2) 現場發生電箱移位或申請臨時用電，一律加收50％的費用。</p>
						<p>	3) 於2014 年10 月20 日後交回的定單，將加收30%的附加費;於2014 年11 月12 日後到的定單，則加收50%的加急費。</p>
						<p>	4) 現場定單的付款由主場搭建商直接向參展商收取。</p>
					</div>
					<div id="step-6">
						<table class="swtable">
							<tr>
								<td><font style="color:red;">*</font>&nbsp;展品名称:</td>
								<td><input type="text" id="" value="" name="industry" class="formcontrol"></td>
							</tr>
							<tr>
								<td>&nbsp;&nbsp;&nbsp;规格:<br/><font style="color:#ccc">&nbsp;（长*宽*高cm）</font></td>
								<td><input type="text" id="" value="" name="industry" class="formcontrol"></td>
							</tr>
							<tr>
								<td>&nbsp;&nbsp;&nbsp;数量：</td>
								<td><input type="text" id="" value="" name="industry" class="formcontrol"></td>
							</tr>
							<tr>
								<td>&nbsp;&nbsp;&nbsp;单价：</td>
								<td><input type="text" id="" value="" name="industry" class="formcontrol"></td>
							</tr>
							<tr>
								<td><font style="color:red;">*</font>&nbsp;100字简介：<br/><font style="color:#ccc">&nbsp;（欢迎以电子档提供）</font></td>
								<td><textarea name="introduction"></textarea></td>
							</tr>
							<tr>
								<td>&nbsp;&nbsp;&nbsp;备注：</td>
								<td><textarea name="note"></textarea></td>
							</tr>
						</table>
					</div>
					<div id="step-7">
						<table class="swtable">
							<tr>
								<td>物流信息名称</td>
								<td><input type="text" id="" value="" name="industry" class="formcontrol"></td>
							</tr>
							<tr>
								<td>发货单位</td>
								<td><input type="text" id="" value="" name="industry" class="formcontrol"></td>
							</tr>
							<tr>
								<td>接受单位</td>
								<td><input type="text" id="" value="" name="industry" class="formcontrol"></td>
							</tr>
						</table>
					</div>
					<div id="step-8">
						
					</div>
				</div>
			</form>
			</div>
		</div>
	</div>
	<jsp:include page="../footer.jsp"></jsp:include>
  </body>
</html>
