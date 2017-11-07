package com.centling.conference.messagepush.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.centling.conference.messagepush.DAO.ConfMessagePushDAO;
import com.centling.conference.messagepush.entity.ConfMessagePush;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;

@Service("confMessagePushService")
public class ConfMessagePushService {
	@Resource
	private ConfMessagePushDAO confMessagePushDAO;
	public List<ConfMessagePush> retrieve(PageBean pageBean, ConfMessagePush confMessagePush, String meetingId) {
		List<ConfMessagePush> list = confMessagePushDAO.retrieve(pageBean, confMessagePush, meetingId);
		return list;
	}
	public void save(ConfMessagePush confMessagePush) {
		confMessagePushDAO.save(confMessagePush);
		
	}
	public ConfMessagePush findById(String messageId) {
		return confMessagePushDAO.findById(messageId);
	}
	public void update(ConfMessagePush confMessagePush){
		confMessagePushDAO.merge(confMessagePush);
	}

}
