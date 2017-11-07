<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div id="previewDlg" class="easyui-dialog" style="width:60%;height:100%;padding:10px 20px" closed="true" 
		maximizable="true" resizable="true" collapsible="true" left="100px" top="0" buttons="#previewDlg-buttons">
      <div data-options="region:'center'">
      	<form id="previewff" method="post">
        <table cellspacing="30px">
             <tr>
                 <th class="app-top-td" style="color:#EA8511;font-size:14px;">现场预览</th>
             </tr>
             <tr>
                <td class="app-top-td" valign="top" style="border:1px solid #8CC63F;padding:4px;">
                    <div class="fitem" style="border:none;">
                        <input name="userId" id="userId" type="hidden">
                    </div>
                    <div class="fitem">
                        <label>活动名称:</label>
                        <input id="title" name="title" class="m-info">
                    </div>
                    <div class="fitem">
                        <label>开始时间:</label>
                        <input id="startTime" name="startTime" class="m-info">
                    </div>
                    <div class="fitem">
                        <label>结束时间:</label>
                        <input id="endTime" name="endTime" class="m-info">
                    </div>
                    <div class="fitem">
                        <label>地点:</label>
                        <input id="location" name="location" class="m-info">
                    </div>
                    <div class="fitem">
                        <label>规模:</label>
                        <textarea id="liveSize" name="liveSize" class="m-info"></textarea>
                    </div>
                    <div class="fitem">
                        <label>会场整体平面图标识该活动所在位置:</label>
                        <input id="locationUrl" name="locationUrl" class="m-info" type="hidden">
                        <div id="imgfileshow1Preview"></div>
                    </div>
                    <div class="fitem">
                        <label>场型图及各区域位置:</label>
                        <input id="fieldPatternUrl" name="fieldPatternUrl" class="m-info" type="hidden">
                        <div id="imgfileshow2Preview"></div>
                    </div>
                    <div class="fitem">
                        <label>活动简单流程:</label>
	            		<textarea id="liveProcedure" name="liveProcedure" class="m-info"></textarea>
                    </div>
                    <div class="fitem">
                        <label>现场名签排位(演讲区):</label>
		            	<input id="speechSeatName" name="speechSeatName" class="m-info">
                    </div>
                    <div class="fitem">
                        <label>现场名签排位(VIP区):</label>
		            	<input id="vipSeatName" name="vipSeatName" class="m-info">
                    </div>
                    <div class="fitem">
                        <label>现场音频视频文件说明:</label>
		            	<input id="audioVideo" name="audioVideo" class="m-info">
                    </div>
                    <div class="fitem">
                        <label>VIP嘉宾名单:</label>
		            	<input id="vipListName" name="vipListName" class="m-info">
                    </div>
                    <div class="fitem">
                        <label>参会人员名单:</label>
		            	<input id="participantListName" name="participantListName" class="m-info">
                    </div>
                    <div class="fitem">
                        <label>主持人:</label>
		            	<input id="emceeName" name="emceeName" class="m-info">
                    </div>
                    <div class="fitem">
                        <label>分现场总负责人:</label>
		            	<input id="personInChargeName" name="personInChargeName" class="m-info">
                    </div>
                    <div class="fitem">
                        <label>物料统筹负责人:</label>
		            	<input id="personForMaterialName" name="personForMaterialName" class="m-info">
                    </div>
                    <div class="fitem">
                        <label>会议内容、资料文件整合及会议流程把控负责人:</label>
		            	<input id="personForFlowName" name="personForFlowName" class="m-info">
                    </div>
                </td>
                </tr>
               </table>
        </form>
      </div>
    <div id="previewDlg-buttons">
        <button class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#previewDlg').dialog('close')" style="width:90px">关闭</button>
    </div>
</div>