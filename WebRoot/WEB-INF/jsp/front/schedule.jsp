<%@ page  language="java" import="java.util.*,javax.servlet.*,javax.servlet.http.*,org.springframework.web.servlet.support.RequestContextUtils" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	HttpSession s = request.getSession(); 
	Locale locale = RequestContextUtils.getLocale(request);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<jsp:include page="../../../include/common.jsp"></jsp:include>

<%--<title><spring:message code="titleInfo.meetingTitle" /></title>--%>
<!-- <meta charset="utf-8">
    <meta name="description" content="TimelineJS example">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-touch-fullscreen" content="yes">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

      <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"> -->
<!-- Style-->
<style>
        body,.slider-container-mask,.timenav-interval-background,div#storyjs-timeline {
            background-color: #F4F2E5;
        }

</style>
<!-- HTML5 shim, for IE6-8 support of HTML elements-->
<!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/tsrunk/html5.js"></script><![endif]-->
<!--  <script type="text/javascript" src="js/jquery.min.js"></script>
      <script type="text/javascript" src="js/bootstrap.min.js"></script> -->
<!-- BEGIN TimelineJS -->
<script type="text/javascript" src="js/storyjs-embed.js"></script>
<script type="text/javascript">
		var checkboxes;
          
		$(function(){
		$.ajax({  
		      type: "GET",  
		      url: "meeting/meetingjson",
		      dataType: "json",  
		      cache: true,   
		      success: function(data) {
		      		  var convertdata ={'timeline':data};
		              createStoryJS({
		                  type:       'timeline',
		                  width:      '100%',
		                  height:     '70%',
		                  lang:       '<%=locale.toString()%>',
		                  source:     convertdata,
		                  embed_id:   'my-timeline'
		              });
		          } 
  		});
		var box=[];
		var times=[];
		var confilctTime='';
		var reg = new RegExp('"',"g");
		$("#submit").click(function(){
			if("${front_approveState}" == "2"){
				$("#btn").attr("disabled","true");
				var r = confirm("<spring:message code='basicInfo.alreadyVerified'/>");
			    if (r == false ||　r == true) {  //已审核通过，不允许修改
			        return false;
			    }
			}else{
				var r = confirm("<spring:message code='schedule.confirmHelp'/>");
			    if (r == false) {  ////提示提交之后不允许修改;取消，重新选择日程，确定，继续保存
			        return false;
			    }
				box=[];
			      times=[];
			      confilctTime='';
			      $('#certType').append(getCertType(${user_info.certType}));//转译证件类型信息
			      $('#industry').append(getIndustryType(${user_info.industry}));//转译行业信息
			      $('#nation').append(getNation(${user_info.nation}));//转译国家信息
			      $('#companyType').append(getCompanyType(${user_info.companyType}));//转译单位性质
			      $('#entryType').append(getEntryType(${user_info.entryType}));//转译单位性质
			      //用户基本信息，紧急联系人的信息展示
			      var sContactstr = "${user_info.contactPerson}";
			      if(sContactstr == "" || sContactstr == "{}"){
			    	  $("#contactPersonInfo").html("<spring:message code='warning.noContactPersonInfo'/>");
			      }else{
			    	  var sContact = ${user_info.contactPerson};//isEmptyObject(sContact)
			    	  $("#cpname").html(":"+sContact.fullName);
		      		  if (sContact.sex == 1) {
		      			$("#cpsex").html(":<spring:message code='basicInfo.Male'/>");
		      		  } else if (sContact.sex == 2) {
		      			$("#cpsex").html(": <spring:message code='basicInfo.Female'/>");
		      		  }
			    	  $("#cptelephone").html(":"+sContact.contactNo);
			    	  $("#cpmobilePhone").html(":"+sContact.mobile);
			    	  $("#cpemail").html(":"+sContact.email);
			    	  $("#cpfaxNo").html(":"+sContact.faxNo);
			    	  $("#cpposition").html(":"+sContact.position);
			      }
			      $("#schedulebody").html("");
                checkboxes = document.getElementsByName("join");
                for(var i=0;i<checkboxes.length;i++){
                    if(checkboxes[i].checked){
                  	  var key = checkboxes[i].value.split('|')[0];
                  	  var title = JSON.stringify(checkboxes[i].value.split('|')[1]);
                  	  var time = JSON.stringify(checkboxes[i].value.split('|')[2]);
                  	  var content = JSON.stringify(title).replace(reg, "");
                        content = content.substring(1,content.length-1);
                  	  if(!ischeckoverlap(content,time)){
                  		  box.push(key);
                            $("#schedulebody").append("<li>"+content+time+"</li>");
                            $("#btn").removeAttr('disabled');
                  	  }else{
                  		  $("#schedulebody").html(confilctTime);     
                  		  $("#btn").attr("disabled","true");
                  		  break;
                  	  }
                    }
                }
			}
			      
        });
		
		var ischeckoverlap = function(content,time){
			var inputStart = parseDate(time.split('-')[0]);
			var inputEnd = parseDate(time.split('-')[1]);
			if(times.length==0){
				var tmp = {"content":content,"time":time};
				times.push(tmp);
			}else{
				for(var i=0;i<times.length;i++){
					var existStart = parseDate(times[i]["time"].split('-')[0]);
					var existEnd = parseDate(times[i]["time"].split('-')[1]);
					if(!(inputStart>=existEnd||inputEnd<=existStart)){
						confilctTime+= "<li><span style='color:red'>"+content+" <spring:message code='schedule.at'/> &nbsp;&nbsp;&nbsp;"+time+"<br /> <spring:message code='schedule.and'/>  "+times[i]["content"]+"<spring:message code='schedule.conflict'/></span></li>";
						return true;
					}
				}
				var tmp = {"content":content,"time":time};
				times.push(tmp);
				return false;
			}
		};
		
		function parseDate(str){
			str = str.replace(reg, "");
			return new Date(str);
		}
		
		
		$("#btn").click(function(){
			if(box.length<=0){ //未选中任何日程
				var r = confirm("<spring:message code='schedule.noneSelect'/>");
			    if (r == false || r == true) {  //取消，重新选择日程，确定，继续保存
			    	$("#dismiss").click();
			        return false;
			    }
			}
			
			$.ajax({
				type : "POST",
				url : "<%=basePath%>schedule/subscribe/${user_info.id}",
				data : JSON.stringify(box),
				contentType: "application/json; charset=utf-8",
				dataType: "json",
				success : function(result) {
					if(result['success']){
						window.location.href = '<%=basePath%>r/front/reg_success';						
						/*if(confirm("<spring:message code='schedule.savesuccess'/>")){
						}else{
							//history.go(-1);
						}*/
					}
					else
						alert("<spring:message code='schedule.savefail'/>");
				}
			});
		});
		
		$("#reset").click(function(){
			$(":checkbox").each(function(){
				$(this).attr("checked",false);
			});
		});
	});	
  
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
    
    // 获取单位性质
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
    
    // 单位性质匹配
    function getCompanyType(value) {
       for (var i=0; i<companyType.length; i++) {
           if (value == companyType[i].code) {
               return companyType[i].name;
           }
       }
   }
    
    // 获取入澳通行证
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
    
    // 入澳通行证匹配
    function getEntryType(value) {
       for (var i=0; i<entryType.length; i++) {
           if (value == entryType[i].code) {
               return entryType[i].name;
           }
       }
   }
    //判断变量是否是对象
    function isEmptyObject(o){
        for(var n in o){

            return false;
        }
        return true;
    }

</script>
</head>
<body>
	<!-- BEGIN Timeline Embed -->
    <div class="container">
		<img src="images/reg_banner.jpg" style="margin-top:-9px;"/>
		<div id="my-timeline"></div>
		<!-- Button trigger modal -->
		<div class="row" >
			<div class='col-lg-offset-9'>
				<button class="btn btn-primary btn-lg" id="submit" data-toggle="modal" data-target="#myModal" align="right">
				  <spring:message code="Submit"/>
				</button>
				<button class="btn btn-primary btn-lg" id="reset" align="right">
				  <spring:message code="Reset"/>
				</button>
			</div>
		</div>
		<!-- Modal -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
		    	<div class="modal-content">
		      		<div class="modal-header">
		        		<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only"><spring:message code="schedule.close"/></span></button>
		        		<h4 class="modal-title" id="myModalLabel"><spring:message code="schedule.confirm"/></h4>
		      		</div>
		      		<div class="modal-body" style="max-height: 400px; overflow-y:auto;">
		      			<h3><spring:message code="confirm.scheduleinfo"/></h3>
		      			<p id="schedulebody"></p>
		      			<h3><spring:message code="confirm.userinfo"/></h3>
		      			<p><span><spring:message code="basicInfo.ChineseName"/></span>: ${user_info.cname} </p>
		      			<p><span><spring:message code="basicInfo.englishName"/></span>: ${user_info.ename}</p>
		      			<p><span><spring:message code="basicInfo.firstName"/></span>: ${user_info.firstName} </p>
		      			<p><span><spring:message code="basicInfo.lastName"/></span>: ${user_info.lastName} </p>
		      			<p><span><spring:message code="basicInfo.email"/></span>: ${user_info.email}</p>
		      			<p>
		      				<span><spring:message code="basicInfo.Gender"/></span>:
		      				<c:if test='${user_info.sex == "1"}'><spring:message code="basicInfo.Male"/></c:if>
		      				<c:if test='${user_info.sex == "2"}'><spring:message code="basicInfo.Female"/></c:if>
		      			</p>
		      			<p><span><spring:message code="basicInfo.birth"/></span>: ${user_info.birth}</p>
		      			<p>
		      				<div id="nation"><span><spring:message code="basicInfo.nation" /></span>: </div>
		      			</p>
		      			<p><span><spring:message code="basicInfo.photo"/></span>: <img src="${user_info.photo}" alt="image"></p>
		      			<p><span><spring:message code="basicInfo.telephone"/></span>: ${user_info.tele}</p>
		      			<p><span><spring:message code="basicInfo.mobilePhone"/></span>: ${user_info.mobile}</p>
		      			<p><span><spring:message code="basicInfo.faxNo"/></span>: ${user_info.fax}</p>
		      			<p><span><spring:message code="basicInfo.postCode"/></span>: ${user_info.postcode}</p>
		      			<p><span><spring:message code="basicInfo.address"/></span>: ${user_info.address}</p>
		      			<p><div id="certType"><span><spring:message code="basicInfo.certificationType" /></span>: </div></p>
<%--		      		<p><span><spring:message code="basicInfo.expiryDate"/></span>: ${user_info.certExpiryDate}</p>--%>
		      			<p><span><spring:message code="basicInfo.certificationNo"/></span>: ${user_info.certValue}</p>
		      		
		      			<p><div id="industry"><span><spring:message code="basicInfo.industry"/></span>: </div></p>
		      			<p><div id="companyType"><span><spring:message code="basicInfo.companyType"/></span>: </div></p>
		      			<p><span><spring:message code="basicInfo.company"/></span>: ${user_info.company}</p>
		      			<p><span><spring:message code="basicInfo.Position"/></span>: ${user_info.position}</p>
<%--		      		<p><span><spring:message code="basicInfo.cityToGetVisa"/></span>: ${user_info.visaCity}</p>--%>
<%--		      		<p><span><spring:message code="basicInfo.religion"/></span>: ${user_info.religion}</p>--%>
		      			<p><span><spring:message code="basicInfo.dietTaboo"/></span>: ${user_info.dietTaboo}</p>
		      			<p><span><spring:message code="basicInfo.foodallergies"/></span>: ${user_info.foodAllergies}</p>
<%--		      		<p><span><spring:message code="basicInfo.specialEtiquette"/></span>: ${user_info.etiquette}</p>--%>
		      			<p><span><spring:message code="basicInfo.introduction"/></span>: ${user_info.selfIntro}</p>
		      			<p><span><spring:message code="basicInfo.introductionEn"/></span>: ${user_info.selfIntroEn}</p>
		      			<p><span><spring:message code="basicInfo.alias"/></span>: ${user_info.ualias}</p>
		      			<p><span><spring:message code="basicInfo.positionTitle"/></span>: ${user_info.positionTitle}</p>
		      			<p><span><spring:message code="basicInfo.remarks"/></span>: ${user_info.remark}</p>		      		
		      			<p><div id="entryType"><span><spring:message code="basicInfo.HK/MacauEntryType"/></span>: </div></p>
		      			<p><span><spring:message code="basicInfo.HK/MacauEntryPlace"/></span>: ${user_info.entryPlace}</p>
		      			<p><span><spring:message code="basicInfo.HK/MacauEntryDate"/></span>: ${user_info.entryDate}</p>
		      			<p><span><spring:message code="basicInfo.HK/MacauEntryValidity"/></span>: ${user_info.entryValidity}</p>
		      			<p><span><spring:message code="basicInfo.HK/MacauEntryNum"/></span>: ${user_info.entryNum}</p>
		      			<p><span><spring:message code="basicInfo.HK/MacauEntryEndmtDate"/></span>: ${user_info.entryEndmtDate}</p>
		      			<p><span><spring:message code="basicInfo.HK/MacauEntryEndmtValidity"/></span>: ${user_info.entryEndmtValidity}</p>
		      			<p><span><spring:message code="basicInfo.HK/MacauEntryPic1"/></span>: <img src="${user_info.entryPic1}" alt="image"></p>
		      			<p><span><spring:message code="basicInfo.HK/MacauEntryPic2"/></span>: <img src="${user_info.entryPic2}" alt="image"></p>
		      			<h4><spring:message code="basicInfo.contactPersonInfo"/></h4>
		      			<div id="contactPersonInfo">
			      			<p><spring:message code="basicInfo.name"/><span id="cpname">:</span></p>
			      			<p><spring:message code="basicInfo.Gender"/><span id="cpsex">:</span></p>
			      			<p><spring:message code="basicInfo.telephone"/><span id="cptelephone">:</span></p>
			      			<p><spring:message code="basicInfo.mobilePhone"/><span id="cpmobilePhone">:</span></p>
			      			<p><spring:message code="basicInfo.email"/><span id="cpemail">:</span></p>
			      			<p><spring:message code="basicInfo.faxNo"/><span id="cpfaxNo">:</span></p>
			      			<p><spring:message code="basicInfo.position"/><span id="cpposition">:</span></p>
		      			</div>
		      			<p id="mettingInfo"></p>
		      			<h3><spring:message code="confirm.meeting"/></h3>
		      			<c:forEach items="${front_Meetingform}" var="RegistMeetingInfo" varStatus="id">
		      				<p>
		      					<c:if test='${RegistMeetingInfo.type != "website"}'>
		      						<span><c:out value="${RegistMeetingInfo.name}" /></span>: <c:out value="${RegistMeetingInfo.value}" />
		      					</c:if>
		      				</p>
		      			</c:forEach>
		      		</div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal" id="dismiss"><spring:message code="schedule.close"/></button>
		        <button  id="btn"  type="button" class="btn btn-primary"><spring:message code="schedule.save"/></button>
		      </div>
		    </div>
		  </div>
		</div>
	</div>
</body>
</html>