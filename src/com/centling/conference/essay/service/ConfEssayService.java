package com.centling.conference.essay.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.essay.DAO.ConfEssayDAO;
import com.centling.conference.essay.entity.ConfEssay;
import com.centling.conference.util.CommonsMethod;

@Service("confEssayService")
public class ConfEssayService {

	public Pagination retrieve(PageBean pageBean, String meetingId,ConfEssay confEssay) {
		Pagination pagination = new Pagination();
		pagination.setRows(confEssayDAO.retrieve(pageBean,meetingId,confEssay));
		pagination.setTotal(confEssayDAO.count(meetingId,confEssay)+"");
		return pagination;
	}
	
	public void save(ConfEssay confEssay) {
		confEssay.setCreateRealTime(CommonsMethod.getNowCorrect2SecondWithWord());
		confEssay.setCreateTime(CommonsMethod.getNowCorrect2Second());
		confEssay.setLastUpdateRealTime(CommonsMethod.getNowCorrect2SecondWithWord());
		confEssay.setLastUpdateTime(CommonsMethod.getNowCorrect2Second());
		confEssayDAO.save(confEssay);
	}
	
	public void update(ConfEssay confEssay) {
		confEssay.setLastUpdateTime(CommonsMethod.getNowCorrect2Second());
		confEssay.setLastUpdateRealTime(CommonsMethod.getNowCorrect2SecondWithWord());
		confEssayDAO.update(confEssay);
	}
	
	public void delete(String id) {
		confEssayDAO.delete(id);
	}
	
	public ConfEssay findById(String id){
		return confEssayDAO.findById(id);
	}
	
	public List<ConfEssay> findAll() {
		return confEssayDAO.findAll();
	}
		
	/**
	 * 根据二级菜单ID及类型加载文章
	 * @param secondEssayTypeId 二级菜单ID
	 * @param infoType 类型 1：文章 2：列表
	 * @return
	 */
	public List<ConfEssay> findListBySecondTypeId(String secondEssayTypeId, Integer infoType) {
		List<ConfEssay> essayList = confEssayDAO.findByEssayType(secondEssayTypeId);
		// 文章
		if (infoType==1 && essayList!=null && !essayList.isEmpty()) {
			// 取第一篇文章
			List<ConfEssay> resultList = new ArrayList<ConfEssay>();
			resultList.add(essayList.get(0));
			return resultList;
		}
		return essayList;
	}
	
	@Resource 
	ConfEssayDAO confEssayDAO;
}
