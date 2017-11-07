package com.centling.conference.schedule.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import sun.misc.BASE64Encoder;

import com.centling.conference.assignment.DAO.ConfAssignmentOtherDAO;
import com.centling.conference.assignment.entity.ConfAssignmentOther;
import com.centling.conference.base.Constant;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.material.DAO.ConfMaterialScheduleDAO;
import com.centling.conference.material.entity.ConfMaterialSchedule;
import com.centling.conference.schedule.DAO.ConfScheduleDAO;
import com.centling.conference.schedule.entity.ConfSchedule;
import com.centling.conference.schedulelog.DAO.ConfSchedulelogUserAssignDAO;
import com.centling.conference.sysuser.entity.ConfSysUser;
import com.centling.conference.user.DAO.ConfUserDAO;
import com.centling.conference.user.entity.ConfUser;

@Service("confScheduleService")
public class ConfScheduleService {
	@Resource
	private ConfScheduleDAO confScheduleDAO;
	
	@Resource
	private ConfAssignmentOtherDAO confAssignmentOtherDAO;
	
	@Resource
	private ConfMaterialScheduleDAO confMaterialScheduleDAO;
	
	@Resource
	private ConfUserDAO confUserDAO;
	
	@Resource
	private ConfSchedulelogUserAssignDAO confSchedulelogUserAssignDAO;
	
	public Pagination retrieve(PageBean pageBean, String meetingId) {
		Pagination pagination = new Pagination();
		List<ConfSchedule> list = confScheduleDAO.retrieve(pageBean, meetingId);
		pagination.setRows(list);
		pagination.setTotal(confScheduleDAO.count(meetingId)+"");
		return pagination;
	}

	public void save(ConfSchedule confSchedule) {
		confScheduleDAO.save(confSchedule);
	}
	
	public void update(ConfSchedule confSchedule) {
		confScheduleDAO.update(confSchedule);
	}

	public void delete(String scheduleId) {
		ConfSchedule confSchedule = confScheduleDAO.findById(scheduleId);
		if (confSchedule != null) {
			confScheduleDAO.delete(confSchedule);
		}
	}
	
    //根据ID查询会议
    public List<ConfSchedule> findByMeetingId(String id) {
        return confScheduleDAO.findByProperty(Constant.SESSION_MEETING_ID, id);
    }
    
    //根据ID查询活动信息
    public ConfSchedule findLiveById(String id) {
    	return confScheduleDAO.findById(id);
    }
    
    /**
     * 根据用户ID，会议ID查询日程列表
     * @param userId 用户ID
     * @param meetingId 会议ID
     * @return
     */
	public List<ConfSchedule> findScheduleByUserId(String userId, String meetingId) {
		List<ConfSchedule> scheduleList = new ArrayList<ConfSchedule>();
		// 根据用户ID判断用户是否有日程控制
		List<ConfAssignmentOther> assignmentOtherList = confAssignmentOtherDAO.findListByUserId(userId,meetingId);
		// 用户有日程权限控制
		if (assignmentOtherList!=null && !assignmentOtherList.isEmpty()) {
			StringBuffer buffer = new StringBuffer();
			for (ConfAssignmentOther confAssignmentOther : assignmentOtherList) {
				buffer.append(confAssignmentOther.getScheduleId()).append(",");
			}
			String scheduleIds = buffer.toString();
			if (StringUtils.isNotEmpty(scheduleIds)) {
				scheduleIds = scheduleIds.substring(0,scheduleIds.length()-1);
				// 查询日程列表
				scheduleList = confScheduleDAO.findBySchedules(scheduleIds);
			}
		// 用户无日程权限控制,查询所有的日程信息
		} else {
			scheduleList = confScheduleDAO.findByMeetingId(meetingId);
		}
		return scheduleList;
	}
	
	/**
	 * 根据日程ID获取分会场记数据
	 * @param scheduleId 日程编号（分会场）
	 * @return
	 */
	public Map<String, Object> getBranchData(String scheduleId,String webPath) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 查询日程信息
		ConfSchedule confSchedule = confScheduleDAO.findById(scheduleId);
		// 标题
		if (StringUtils.isNotEmpty(confSchedule.getTitle())) {
			resultMap.put("title", confSchedule.getTitle());
		} else {
			resultMap.put("title",StringUtils.EMPTY);
		}
		// 时间
		if (StringUtils.isNotEmpty(confSchedule.getStartTime())) {
			resultMap.put("startDate", formatDate(confSchedule.getStartTime()));
			resultMap.put("startTime", formatTime(confSchedule.getStartTime(),confSchedule.getEndTime()));
		} else {
			resultMap.put("startDate",StringUtils.EMPTY);
			resultMap.put("startTime",StringUtils.EMPTY);
		}
		// 地点
		if (StringUtils.isNotEmpty(confSchedule.getLocation())) {
			resultMap.put("location", confSchedule.getLocation());
		} else {
			resultMap.put("location",StringUtils.EMPTY);
		}
		// 规模
		if (StringUtils.isNotEmpty(confSchedule.getLiveSize())) {
			resultMap.put("liveSize", confSchedule.getLiveSize());
		} else {
			resultMap.put("liveSize", StringUtils.EMPTY);
		}
		// 主持人
		if (StringUtils.isNotEmpty(confSchedule.getEmceeId())) {
			resultMap.put("moderatorList", confUserDAO.getUserByIdList(confSchedule.getEmceeId()));
		} else {
			resultMap.put("moderatorList",new ArrayList<ConfSchedule>());
		}
		// VIP区名签排位
		if (StringUtils.isNotEmpty(confSchedule.getVipSeatId())) {
			resultMap.put("vipSeatList", confUserDAO.getUserByIdList(confSchedule.getVipSeatId()));
		} else {
			resultMap.put("vipSeatList", new ArrayList<ConfSchedule>());
		}
		// 演讲区名签排位
		if (StringUtils.isNotEmpty(confSchedule.getSpeechSeatId())) {
			resultMap.put("speechSeatList", confUserDAO.getUserByIdList(confSchedule.getSpeechSeatId()));
		} else {
			resultMap.put("speechSeatList", new ArrayList<ConfSchedule>());
		}
		// 发言嘉宾
		List<ConfUser> speakerList = confSchedulelogUserAssignDAO.findSpeakerByScheduleId(confSchedule.getId());
		if (speakerList==null || speakerList.isEmpty()) {
			resultMap.put("speakerList", new ArrayList<ConfUser>());
		} else {
			resultMap.put("speakerList", speakerList);
		}
		// VIP嘉宾
		if (StringUtils.isNotEmpty(confSchedule.getVipListId())) {
			resultMap.put("vipList", confUserDAO.getUserByIdList(confSchedule.getVipListId()));
		} else {
			resultMap.put("vipList", new ArrayList<ConfSchedule>());
		}
		// 参会嘉宾
		if (StringUtils.isNotEmpty(confSchedule.getParticipantListId())) {
			resultMap.put("participantList", confUserDAO.getUserByIdList(confSchedule.getParticipantListId()));
		} else {
			resultMap.put("participantList", new ArrayList<ConfSchedule>());
		}
		// 流程
		if (StringUtils.isNotEmpty(confSchedule.getLiveProcedure())) {
			resultMap.put("procedure", confSchedule.getLiveProcedure());
		} else {
			resultMap.put("procedure",StringUtils.EMPTY);
		}
		// 整体平面图
		if (StringUtils.isNotEmpty(confSchedule.getLocationUrl())) {
			resultMap.put("flatImage", getImageStr(webPath+confSchedule.getLocationUrl().replace(";", "")));
		} else {
			resultMap.put("flatImage", StringUtils.EMPTY);
		}
		// 场型图
		if (StringUtils.isNotEmpty(confSchedule.getFieldPatternUrl())) {
			resultMap.put("fieldImage", getImageStr(webPath+confSchedule.getFieldPatternUrl().replace(";", "")));
		} else {
			resultMap.put("fieldImage", StringUtils.EMPTY);
		}
		// 音频视频文件描述
		if (StringUtils.isNotEmpty(confSchedule.getAudioVideo())) {
			resultMap.put("audioVideo", confSchedule.getAudioVideo());
		} else {
			resultMap.put("audioVideo",StringUtils.EMPTY);
		}
		// 物料
		StringBuffer buffer = new StringBuffer();
		List<ConfMaterialSchedule> materialList = confMaterialScheduleDAO.findBySchduelId(scheduleId);
		for (ConfMaterialSchedule confMaterialSchedule : materialList) {
			buffer.append(confMaterialSchedule.getMaterialName()).append(":数量")
				.append(confMaterialSchedule.getMaterialNum()).append(";");
		}
		if (StringUtils.isNotEmpty(buffer.toString())) {
			resultMap.put("material", buffer.toString().substring(0,buffer.toString().length()-1));
		} else {
			resultMap.put("material",StringUtils.EMPTY);
		}
		// 总负责人
		if (StringUtils.isNotEmpty(confSchedule.getPersonInChargeId())) {
			resultMap.put("totalChargeList", confUserDAO.getUserByIdList(confSchedule.getPersonInChargeId()));
//		    resultMap.put("totalChargeList", confSysUserDAO.findSysUserByIdList(confSchedule.getPersonInChargeId()));
		} else {
			resultMap.put("totalChargeList",new ArrayList<ConfSysUser>());
		}
		// 物料负责人
		if (StringUtils.isNotEmpty(confSchedule.getPersonForMaterialId())) {
			resultMap.put("materialChargeList", confUserDAO.getUserByIdList(confSchedule.getPersonForMaterialId()));
//			resultMap.put("materialChargeList", confSysUserDAO.findSysUserByIdList(confSchedule.getPersonForMaterialId()));
		} else {
			resultMap.put("materialChargeList", new ArrayList<ConfSysUser>());
		}
		// 现场调配负责人
		if (StringUtils.isNotEmpty(confSchedule.getPersonForDispatchId())) {
			resultMap.put("dispatchChargeList", confUserDAO.getUserByIdList(confSchedule.getPersonForDispatchId()));
//			resultMap.put("dispatchChargeList", confSysUserDAO.findSysUserByIdList(confSchedule.getPersonForDispatchId()));
		} else {
			resultMap.put("dispatchChargeList", new ArrayList<ConfSysUser>());
		}
		// 流程把控负责人
		if (StringUtils.isNotEmpty(confSchedule.getPersonForFlowId())) {
			resultMap.put("flowChargeList", confUserDAO.getUserByIdList(confSchedule.getPersonForFlowId()));
//			resultMap.put("flowChargeList", confSysUserDAO.findSysUserByIdList(confSchedule.getPersonForFlowId()));
		} else {
			resultMap.put("flowChargeList", new ArrayList<ConfSysUser>());
		}
		return resultMap;
	}
	
	/**
	 * 格式化日期
	 * @param startTime 开始时间
	 * @return
	 */
	private String formatDate(String startTime) {
		return startTime.substring(0,4)+"年"+startTime.substring(5,7)+"月"+startTime.substring(8,10)+"日";
	}

	/**
	 * 格式化时间
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 */
	private String formatTime(String startTime, String endTime) {
		return startTime.substring(11)+"-"+endTime.substring(11);
	}
	
	/**
	 * 获取图片
	 * @return
	 */
	private String getImageStr(String imgFile) {
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }
}