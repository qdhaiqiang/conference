package com.centling.conference.exhibitbooth.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.exhibitbooth.DAO.ConfBoothDAO;
import com.centling.conference.exhibitbooth.entity.ConfBooth;
import com.centling.conference.exhibition.entity.ConfExhibition;

@Service("confBoothService")
public class ConfBoothService {
	@Resource
	private ConfBoothDAO confBoothDAO;

	public List<ConfBooth> findByParentId(String parentId) {
		return confBoothDAO.findByParentId(parentId);
	}
	
	public ConfBooth findById(String id) {
		return confBoothDAO.findById(id);
	}
	
	public void save(ConfBooth confBooth) {
		confBoothDAO.save(confBooth);
	}
	
	/**
	 * 参展商登陆后，待显示的可用展位:必须是本次会议的，展位未被选择或者被该登录展商申请
	 * @param meetingId 会议id
	 * @param userId 登陆的展商id
	 * @return
	 */
	public List<ConfBooth> findAvailableBooths(String meetingId,String userId){
		return confBoothDAO.findAvailableBooths(meetingId,userId);
	}
	
	/**
	 * 获取每类展位的可用展位数目
	 * @param exhibition
	 * @return
	 */
	public int[] getAvailableBoothsNum(List<ConfExhibition> exhibition){
		int[] boothsNum = null;
		if(exhibition != null && exhibition.size() > 0){
			boothsNum = new int[exhibition.size()];
			for(int i=0; i<exhibition.size();i++){
				boothsNum[i] = getAvailableBoothsNum(exhibition.get(i).getExhibitionId());
			}
		}
		return boothsNum;
	}
	
	public int getAvailableBoothsNum(String parentId) {
		int num = 0;
		List<ConfBooth> booths = findByParentId(parentId);
		if (booths != null) {
			for (int i = 0; i < booths.size(); i++) {
				if (Integer.parseInt(booths.get(i).getBoothState()) == 1) {
					num++;
				}
			}
		}
		return num;
	}
	public Pagination retrive(PageBean pageBean, ConfBooth confBooth, String meetingId) {
		Pagination pagination = new Pagination();
		List<ConfBooth> list = confBoothDAO.retrieve(pageBean, confBooth, meetingId);
		pagination.setRows(list);
		pagination.setTotal(confBoothDAO.count(meetingId, confBooth));
		
		return pagination;
	}
	public void update(ConfBooth confBooth){
		confBoothDAO.merge(confBooth);
	}
	public void delete(String[] ids, String meetingId){
		for(String id:ids){
			confBoothDAO.deleteById(id);
		}
	}
	
	public List<ConfBooth> findByMeetingId(String meetingId) {
		return confBoothDAO.findByMeetingId(meetingId);
	}
	
	
}