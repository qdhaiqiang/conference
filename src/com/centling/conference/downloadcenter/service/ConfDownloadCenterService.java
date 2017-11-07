package com.centling.conference.downloadcenter.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.downloadcenter.DAO.ConfDownloadCenterDAO;
/**
 * 资源下载中心Service
 * @author lizzh
 *
 */
@Service("confDownloadCenterService")
public class ConfDownloadCenterService {
	@Resource
	private ConfDownloadCenterDAO confDownloadCenterDAO;
	
	public Pagination getResList(PageBean pageBean, String meetingId) {
		Pagination pagination = new Pagination();
		List<Map<String, Object>> list = confDownloadCenterDAO.retrieve(pageBean, meetingId);
		pagination.setRows(list);
		pagination.setTotal(confDownloadCenterDAO.count(meetingId)+"");
		return pagination;
	}
}
