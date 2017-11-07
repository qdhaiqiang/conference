package com.centling.conference.article.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.centling.conference.article.DAO.ConfArticleDAO;
import com.centling.conference.article.entity.ConfArticle;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;

@Service("confArticleService")
public class ConfArticleService {

	public Pagination retrieve(PageBean pageBean ) {
		Pagination pagination = new Pagination();
		pagination.setRows(confArticleDAO.retrieve(pageBean));
		pagination.setTotal(confArticleDAO.count()+"");
		return pagination;
	}
	
	public void save(ConfArticle confArticle) {
		confArticleDAO.save(confArticle);
	}
	
	public void update(ConfArticle confArticle) {
		confArticleDAO.update(confArticle);
	}

	public void delete(String id) {
		confArticleDAO.delete(id);
	}
	
	public ConfArticle findById(String id){
		return confArticleDAO.findById(id);
	}
	
	@Resource
	ConfArticleDAO confArticleDAO;
}
