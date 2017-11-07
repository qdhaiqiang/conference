<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
            String basePath = request.getScheme() + "://"
                    + request.getServerName() + ":" + request.getServerPort()
                    + path + "/";
%>
<jsp:include page="../../../../include/sys-common.jsp" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
<style type="text/css">
.tl {
    width: 80%;
    border: 0px solid #f0f0f0;
    margin: 0 auto;
    margin-bottom: 40px;
    margin-left: 30px;
    text-align:center;
}
.main {
    width: 100%;
    margin-left: 0px;
    margin-top: 80px;
}
.fitem {
    margin-bottom: 5px;
    text-align:center;
}

.fitem .key {
    display: inline-block;
    width: 100px;
    font-size:20px;
    text-align:left;
}

.fitem .value {
    display: inline-block;
    width: 180px;
    font-size:18px;
    text-align:center;
}

</style>
<script type="text/javascript">
    var sexVal = ${sex};
    var nationVal = ${nation};
    var cardTypeVal = ${cardType};
    // 获取国家信息
    var nation = "";
    var cardType = "";
    $(function(){
        $.ajax({
            url: 'dict/r/nation',
            method:'get',
            async:false,
            success:function(data) {
                nation = data;
            }
            
        });
       // 获取身份卡证件类型
       $.ajax({
           url: 'dict/r/card_type',
           method:'get',
           async:false,
           success:function(data) {
               cardType = data;
           }
       });
    });
    
    $(document).ready(function(){
         $("#test").innerText = "ceshi";
         getSex(sexVal);
         getNation(nationVal);
         getCardType(cardTypeVal);
    });
    
    function getSex(sex){
	     if (sex == "1") {
	         $("#sex").html("男");
	     } else if (sex == "2") {
	         $("#sex").html("女");
	     }
    }
    
	  // 国家名称匹配
	  function getNation(value) {
	       var htmlVal = "";
	       for (var i=0; i<nation.length; i++) {
	           if (value == nation[i].code) {
	                htmlVal = nation[i].name;
	                break;
	           }
	       }
	       $("#nation").html(htmlVal);
	   }
        // 身份卡证件类型匹配
        function getCardType(value) {
           var htmlVal = "";
           for (var i=0; i<cardType.length; i++) {
               if (value == cardType[i].code) {
                    htmlVal = cardType[i].name;
                    break;
               }
           }
           $("#cardType").html(htmlVal);
       }
</script>
  </head>
  
  <body>
    <div class="main">
        <div class="fitem">
            <label class="key">中文姓名：</label>
            <label class="value">${cname }</label>
        </div>
        <div class="fitem">
            <label class="key">英文姓名：</label>
            <label class="value">${ename }</label>
        </div>
        <div class="fitem">
            <label class="key">性别：</label>
            <label id="sex" class="value"></label>
        </div>
        <div class="fitem">
            <label class="key">国籍：</label>
            <label id="nation" class="value"></label>
        </div>
        <div class="fitem">
            <label class="key">证件类型：</label>
            <label id="cardType" class="value"></label>
        </div>
    </div>
    <div class="tl">
        <img src="${qrCodeUrl }"/>
    </div>
<script type="text/javascript">

</script>
  </body>
</html>
