package com.centling.conference.funcSetting.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.centling.conference.base.page.Pagination;
import com.centling.conference.funcSetting.DAO.FuncSettingDAO;
import com.centling.conference.funcSetting.entity.ConfFuncSetting;

/**
 * 手机端 功能设置
 *
 */
@Service("funcSettingService")
public class FuncSettingService {

	public List<ConfFuncSetting> findFuncByOrder(String meetingId){
		List<ConfFuncSetting> list= funcSettingDAO.findFuncByOrder(meetingId);
		return list;
	}
	public Pagination retrive(String meetingId){
		Pagination pagination = new Pagination();
		List<ConfFuncSetting> list= funcSettingDAO.findAllByOrder(meetingId);
		pagination.setRows(list);
		if(list==null){
			pagination.setTotal("0");
		}else{
			pagination.setTotal(list.size()+"");
		}
		return pagination;
	}
	
	/**
	 * 更新状态
	 * @param funcSettings
	 * @return
	 */
	public void mergeFuncSetting(String allStatus){
		if(allStatus==null){
			return;
		}
		//将前台收到的字符串解析
		String[] statusList = allStatus.split("@");
		if(statusList==null||statusList.length==0){
			return;
		}
		for(String s:statusList){
			String[] slist = s.split(":");
			if(slist.length==2){
				//取出ID/状态
				String id = slist[0];
				String status = slist[1];
				if(status==null){
					status="0";
				}
				//数据库中读取
				ConfFuncSetting oldFuncs = funcSettingDAO.findById(id);
				//更新状态
				oldFuncs.setStatus(Integer.valueOf(status));
				funcSettingDAO.merge(oldFuncs);
			}
		}
	}
	
	@Resource
	private FuncSettingDAO funcSettingDAO;
}
