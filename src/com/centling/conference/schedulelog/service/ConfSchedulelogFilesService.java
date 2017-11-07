package com.centling.conference.schedulelog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.schedulelog.DAO.ConfSchedulelogFilesDAO;
import com.centling.conference.schedulelog.entity.ConfSchedulelogFiles;
import com.centling.conference.util.FileUploadUtils;

/**
 * 分会场记-文档中心
 * @author centling
 *
 */
@Service("confSchedulelogFilesService")
public class ConfSchedulelogFilesService {

	@Resource
	private ConfSchedulelogFilesDAO confSchedulelogFilesDAO;

	public List<ConfSchedulelogFiles> findUserFiles(String logid){
		List<ConfSchedulelogFiles> list = confSchedulelogFilesDAO.findByLogId(logid);
		return list;
	}
	
	public ConfSchedulelogFiles save(ConfSchedulelogFiles file){
		return confSchedulelogFilesDAO.merge(file);
	}
	
	public Map<String,Object> deleteFile(String ids,String paths,HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		int results = confSchedulelogFilesDAO.deleteByIds(ids);
		String[] path = paths.split(";");
		for(int i=0;i<path.length;i++){
			String realPath = request.getSession().getServletContext().getRealPath("/");//CommonsMethod.getProjectPath();//path的值，D:/programfiles/apache-tomcat-6.0.35/webapps/CONF_MGR/
			realPath = realPath + path[i];
			boolean isDel = FileUploadUtils.deletefileBypath(realPath);
		}
		map.put("deleteRows", results);
		map.put("result", "sucess");
		return map;
	}
	
}
