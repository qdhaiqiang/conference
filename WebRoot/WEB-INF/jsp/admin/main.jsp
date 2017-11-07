<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../../../include/sys-common.jsp" />
<!DOCTYPE html >
<html>
 <head>
  <link rel="shortcut icon" href="images/favicon.ico">
  <style type="text/css">
a {
    color: Black;
    text-decoration: none;
}

a:hover {
    color: black;
    text-decoration: none;
}
</style>
 </head>
 <body class="easyui-layout" style="overflow-y: hidden" scroll="no">
  <!-- 顶部-->
  <jsp:include page="top.jsp" />
  <!-- 左侧-->
  <%--<div region="west" split="true" href="" title="导航菜单" style="width: 150px; padding: 1px;">
  
  
  --%></div>
  <!-- 中间-->
  <div id="mainPanle" region="center" style="overflow: hidden;">
   <div id="maintabs" class="easyui-tabs" fit="true" border="false">
    <div class="easyui-tab" title="首页" href="r/admin/main-right" style="padding:2px; overflow: hidden;">
                 
    </div>
   </div>
  </div>
  <!-- 底部 -->
  <div region="south" border="false" style="height: 25px; overflow: hidden;">
    <div align="center" style="color: #CC99FF; padding-top: 2px">
    &copy; 版权所有
     <span class="tip"><%--<a href="http://www.jeecg.org" title="JEECG V3版演示系统">JEECG</a> (推荐谷歌浏览器，获得更快响应速度)  技术支持:<a href="#" title="JEECG V3版演示系统">JEECG</a></span>--%>
   </div>
  </div>
  <div id="mm" class="easyui-menu" style="width:150px;">
        <div id="mm-tabclose">关闭</div>
        <div id="mm-tabcloseall">全部关闭</div>
        <div id="mm-tabcloseother">除此之外全部关闭</div>
        <div class="menu-sep"></div>
        <div id="mm-tabcloseright">当前页右侧全部关闭</div>
        <div id="mm-tabcloseleft">当前页左侧全部关闭</div>
        
</div>

 </body>
</html>