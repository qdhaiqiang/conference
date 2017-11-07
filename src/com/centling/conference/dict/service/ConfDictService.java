package com.centling.conference.dict.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.centling.conference.base.Constant;
import com.centling.conference.dict.DAO.ConfDictDAO;
import com.centling.conference.dict.category.DAO.ConfDictCategoryDAO;
import com.centling.conference.dict.category.entity.ConfDictCategory;
import com.centling.conference.dict.entity.ConfDict;

/**
 * 业务字典Service
 * @author Dirk
 *
 */
@Service("confDictService")
public class ConfDictService {

	/**
	 * 根据字典类型编号获取字典串
	 * @param categoryCode 字典类型编号
	 * @param flag 是否处理用户类型 flag 1: 不处理    其他：处理
	 * @return 查询到结果返回结果集合，查询不到返回null
	 */
	public List<ConfDict> findDictByCategory(String categoryCode, String flag) {
		// 根据字典类型编号查询字典类型实体
		List<ConfDictCategory> categoryList = confDictCategoryDAO.findByProperty("code", categoryCode);
		if (categoryList!=null && !categoryList.isEmpty()) {
			// 根据字典类型ID查询业务字典实体
			List<ConfDict> list = confDictDAO.findByProperty("categoryId", categoryList.get(0).getId());
			// 用户类型进行处理
			if (!"1".equals(flag)&&"user_type".equals(categoryCode)) {
				for (ConfDict confDict : list) {
					confDict.setName(confDict.getName().substring(3));
				}
			}
			return list;
		}
		return null;
	}
	
	/**
	 * 根据语言、用户类型、类别编码查询字典信息
	 * @param categoryCode 字典类别编号
	 * @param locale 语言
	 * @param type 类型
	 * @return
	 */
	public List<ConfDict> findDictByLocaleAndCategory(String categoryCode,String locale, String type) {
		List<ConfDict> list = findDictByCategory(categoryCode,"1");
		List<ConfDict> returnList = new ArrayList<ConfDict>();
		if (list!=null && !list.isEmpty()) {
			// 判断语言类型
			// 英文
			for (ConfDict confDict : list) {
				ConfDict dict = new ConfDict();
				// 英文
				if (Constant.LOCALE_US.equals(locale)) {
					// 工作人员注册
					if (Constant.REGISTER_STAFF.equals(type)) {
						if (confDict.getName().startsWith("B-1")) {
							dict.setCode(confDict.getCode());
							dict.setName(confDict.getName().substring(3));
							returnList.add(dict);
						}
					// 嘉宾注册
					} else {
						if (confDict.getName().startsWith("B-2")) {
							dict.setCode(confDict.getCode());
							dict.setName(confDict.getName().substring(3));
							returnList.add(dict);
						}
					}
				// 中文
				} else {
					// 工作人员注册
					if (Constant.REGISTER_STAFF.equals(type)) {
						if (confDict.getName().startsWith("A-1")) {
							dict.setCode(confDict.getCode());
							dict.setName(confDict.getName().substring(3));
							returnList.add(dict);
						}
					// 嘉宾注册
					} else {
						if (confDict.getName().startsWith("A-2")) {
							dict.setCode(confDict.getCode());
							dict.setName(confDict.getName().substring(3));
							returnList.add(dict);
						}
					}
				}
			}
			
		}
		return returnList;
	}
	
	 /**
     * 根据字典类型编号获取字典串
     * @param flag 是否处理用户类型 flag guest: 嘉宾   staff：工作人员
     * @return 查询到结果返回结果集合，查询不到返回null
     */
    public List<ConfDict> findGuestOrStaff(String flag) {
        String codes = "";
        // 根据字典类型编号查询字典类型实体
        List<ConfDictCategory> categoryList = confDictCategoryDAO.findByProperty("code", "user_type");
        if (categoryList!=null && !categoryList.isEmpty()) {
            // 用户类型进行处理
            if (flag.equals("staff")) {
                codes = Constant.USER_TYPE_ORG + ","
                        + Constant.USER_TYPE_ORG_EN + "," 
                        + Constant.USER_TYPE_VOLUNTEER + "," 
                        + Constant.USER_TYPE_VOLUNTEER_EN + "," 
                        + Constant.USER_TYPE_SUPPLIER + "," 
                        + Constant.USER_TYPE_SUPPLIER_EN;
            } else if (flag.equals("guest")) {
                codes = Constant.USER_TYPE_VIP + ","
                        + Constant.USER_TYPE_VIP_US + "," 
                        + Constant.USER_TYPE_SPEAKER + "," 
                        + Constant.USER_TYPE_SPEAKER_EN + "," 
                        + Constant.USER_TYPE_ATTEND + "," 
                        + Constant.USER_TYPE_ATTEND_EN + "," 
                        + Constant.USER_TYPE_AUDIENCE + "," 
                        + Constant.USER_TYPE_AUDIENCE_EN + "," 
                        + Constant.USER_TYPE_MEDIA + "," 
                        + Constant.USER_TYPE_MEDIA_EN + "," 
                        + Constant.USER_TYPE_EXHIBITORS + "," 
                        + Constant.USER_TYPE_EXHIBITORS_EN + "," 
                        + Constant.USER_TYPE_SUPPLIER + "," 
                        + Constant.USER_TYPE_SUPPLIER_EN + ","
                        + Constant.USER_TYPE_REGIST_MEDIA + "," 
                        + Constant.USER_TYPE_REGIST_MEDIA_EN + "," 
                        + Constant.USER_TYPE_SPOUSE + "," 
                        + Constant.USER_TYPE_SPOUSE_EN;
            }
            // 根据字典类型ID查询业务字典实体
            List<ConfDict> list = confDictDAO.findUserTypeByCodes(codes);
            for (ConfDict confDict : list) {
                confDict.setName(confDict.getName().substring(3));
            }
            return list;
        }
        return null;
    }
	
	/**
	 * 字典类型DAO
	 */
	@Resource
	private ConfDictCategoryDAO confDictCategoryDAO;
	
	/**
	 * 字典DAO
	 */
	@Resource
	private ConfDictDAO confDictDAO;
}