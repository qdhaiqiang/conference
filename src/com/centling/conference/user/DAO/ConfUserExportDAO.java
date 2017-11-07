package com.centling.conference.user.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.centling.conference.base.BaseHibernateDAO;
import com.centling.conference.base.Constant;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.user.entity.ConfUserExptSrchCondModel;

@Repository("confUserExportDAO")
public class ConfUserExportDAO extends BaseHibernateDAO{
    private static final Logger log = LoggerFactory
            .getLogger(ConfUserDAO.class);

    /**
     * 嘉宾管理机能根据会议ID查找参会人员信息
     * @param pageBean page信息
     * @param meetingId 会议ID
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Map> findUserBySearchCond(PageBean pageBean, String meetingId, ConfUserExptSrchCondModel sCond) {
        log.debug("finding all conf_meeting_user instances");
        try {
            Query query = null;
            String queryString = "select * from conf_v_info where meetingId = ?";
            query = selectUserSql(query,queryString,meetingId, sCond);
            return query.setFirstResult((pageBean.getPage()-1)*pageBean.getRows())
                    .setMaxResults(pageBean.getRows())
                    .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
                    .list();
        } catch (Exception re) {
            log.error("find all failed", re);
        }
        return null;
    }

    /**
     * 查找会议的注册用户的数量
     *
     */
    public String count(String meetingId, ConfUserExptSrchCondModel sCond) {
        log.debug("finding all ConfMeeting instance");
        try {
            Query query = null;
            String queryString = "select count(*) from conf_v_info where meetingId = ?";
            query = selectUserSql(query,queryString,meetingId, sCond);
            return query.uniqueResult().toString();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<Object[]> findUserByIds(String meetingId, String ids) {
        log.debug("finding all conf_v_info instances");
        try {
            Query query = null;
            List<Object[]> list = null;
            String queryString = "select * from conf_v_info where meetingId = ? and userId in (:userId)";
            query = getSession().createSQLQuery(queryString).setString(0, meetingId)
                    .setParameterList("userId", ids.split(","));
            list = query.list();
            return list;
        } catch (Exception re) {
            log.error("find all failed", re);
        }
        return null;
    }
    
    @SuppressWarnings("unchecked")
    public List<Object[]> findAllUser(String meetingId, ConfUserExptSrchCondModel sCond) {
        log.debug("finding all conf_v_info instances");
        try {
        	Query query = null;
        	List<Object[]> list = null;
            String queryString = "select * from conf_v_info where meetingId = ?";
            query = selectUserSql(query,queryString,meetingId, sCond);
            list = query.list();
            return list;
        } catch (Exception re) {
            log.error("find all failed", re);
        }
        return null;
    }
    
    public Query selectUserSql(Query query,String queryString,String meetingId, ConfUserExptSrchCondModel sCond){
    	List<String> param = new ArrayList<String>();
        if (sCond.getName() != null && !sCond.getName().equals("")) {
            queryString += " and (cname like ? or ename like ? )";
            param.add("%"+sCond.getName()+"%");
            param.add("%"+sCond.getName()+"%");
        }
        if (sCond.getEmail() != null && !sCond.getEmail().equals("")) {
            queryString += " and email like ?";
            param.add("%"+sCond.getEmail()+"%");
        }
        if (sCond.getSex() != null && !sCond.getSex().equals("")) {
            queryString += " and sex = ? ";
            param.add(sCond.getSex());
        }
        if (sCond.getReligion() != null && !sCond.getReligion().equals("")) {
            queryString += getQuerySql(sCond.getReligion(), "religion");
        }
        if (sCond.getDietTaboo() != null && !sCond.getDietTaboo().equals("")) {
            queryString += getQuerySql(sCond.getDietTaboo(), "dietTaboo");
        }
        if (sCond.getMedlHis() != null && !sCond.getMedlHis().equals("")) {
            queryString += getQuerySql(sCond.getMedlHis(), "medlHis");
        }
        if (sCond.getFoodAllergies() != null && !sCond.getFoodAllergies().equals("")) {
            queryString += getQuerySql(sCond.getFoodAllergies(), "foodAllergies");
        }
        //入澳证件信息-有效签注有效期(<=)
        if (sCond.getEntryEndmtValidity() != null && !sCond.getEntryEndmtValidity().equals("")) {
            queryString += " and entryEndmtValidity <= ? ";
            param.add(sCond.getEntryEndmtValidity());
        }
        //是否愿意公布真实姓名和职务
        if (sCond.getUseRealname() != null && !sCond.getUseRealname().equals("")) {
            queryString += " and useRealname = ? ";
            param.add(sCond.getUseRealname());
        }
        //是否愿意使用化名
        if (sCond.getUseAlias() != null && !sCond.getUseAlias().equals("")) {
            queryString += " and useAlias = ? ";
            param.add(sCond.getUseAlias());
        }
        //是否愿意使用替代职务
        if (sCond.getUseOtherPtitle() != null && !sCond.getUseOtherPtitle().equals("")) {
            queryString += " and useOtherPtitle = ? ";
            param.add(sCond.getUseOtherPtitle());
        }
        //是否需要主办方提供官方邀请函
        if (sCond.getNeedInvite() != null && !sCond.getNeedInvite().equals("")) {
            if (sCond.getNeedInvite().equals(Constant.USER_EXP_SEARCH_CONDITION_YES)){
                queryString += " and (needInvite like ?";
                param.add("%"+sCond.getNeedInvite()+"%");
                //英文匹配
                queryString += " or needInvite like ? )";
                param.add("%"+Constant.USER_EXP_SEARCH_CONDITION_EN_YES+"%");
            } else if (sCond.getNeedInvite().equals(Constant.USER_EXP_SEARCH_CONDITION_NO)){
                queryString += " and (needInvite like ? ";
                param.add("%"+sCond.getNeedInvite()+"%");
                //空白匹配
                queryString += " or needInvite like ? or needInvite is null or needInvite = \"\")";
                param.add("%"+Constant.USER_EXP_SEARCH_CONDITION_EN_NO+"%");
            }
        }
        //是否需要会前协助办理入境签证
        if (sCond.getNeedVisa() != null && !sCond.getNeedVisa().equals("")) {
            if (sCond.getNeedVisa().equals(Constant.USER_EXP_SEARCH_CONDITION_YES)){
                queryString += " and (needVisa like ?";
                param.add("%"+sCond.getNeedVisa()+"%");
                //英文匹配
                queryString += " or needVisa like ? )";
                param.add("%"+Constant.USER_EXP_SEARCH_CONDITION_EN_YES+"%");
            } else if (sCond.getNeedVisa().equals(Constant.USER_EXP_SEARCH_CONDITION_NO)){
                queryString += " and (needVisa like ? ";
                param.add("%"+sCond.getNeedVisa()+"%");
                //空白匹配
                queryString += " or needVisa like ? or needVisa is null or needVisa = \"\")";
                param.add("%"+Constant.USER_EXP_SEARCH_CONDITION_EN_NO+"%");
            }
        }
        //是否需要组委会传真官方邀请函
        if (sCond.getNeedFaxInvite() != null && !sCond.getNeedFaxInvite().equals("")) {
            if (sCond.getNeedFaxInvite().equals(Constant.USER_EXP_SEARCH_CONDITION_YES)){
                queryString += " and (needFaxInvite like ?";
                param.add("%"+sCond.getNeedFaxInvite()+"%");
                //英文匹配
                queryString += " or needFaxInvite like ? )";
                param.add("%"+Constant.USER_EXP_SEARCH_CONDITION_EN_YES+"%");
            } else if (sCond.getNeedFaxInvite().equals(Constant.USER_EXP_SEARCH_CONDITION_NO)){
                queryString += " and (needFaxInvite like ? ";
                param.add("%"+sCond.getNeedFaxInvite()+"%");
                //空白匹配
                queryString += " or needFaxInvite like ? or needFaxInvite is null or needFaxInvite = \"\")";
                param.add("%"+Constant.USER_EXP_SEARCH_CONDITION_EN_NO+"%");
            }
        }
        //是否需要中国大陆地区签证办理咨询服务
        if (sCond.getNeedChinaVisaService() != null && !sCond.getNeedChinaVisaService().equals("")) {
            if (sCond.getNeedChinaVisaService().equals(Constant.USER_EXP_SEARCH_CONDITION_YES)){
                queryString += " and (needChinaVisaService like ?";
                param.add("%"+sCond.getNeedChinaVisaService()+"%");
                //英文匹配
                queryString += " or needChinaVisaService like ? )";
                param.add("%"+Constant.USER_EXP_SEARCH_CONDITION_EN_YES+"%");
            } else if (sCond.getNeedChinaVisaService().equals(Constant.USER_EXP_SEARCH_CONDITION_NO)){
                queryString += " and (needChinaVisaService like ? ";
                param.add("%"+sCond.getNeedChinaVisaService()+"%");
                //空白匹配
                queryString += " or needChinaVisaService like ? or needChinaVisaService is null or needChinaVisaService = \"\")";
                param.add("%"+Constant.USER_EXP_SEARCH_CONDITION_EN_NO+"%");
            }
        }
        //赴澳/返程交通方式
        if (sCond.getInFromMacau() != null && !sCond.getInFromMacau().equals("")) {
            String method = sCond.getInFromMacau();
            if(method.equals("1")){
                queryString += " and (inFromMacau like ?";
                param.add("%飞机%");
                queryString += " or inFromMacau like ?)";
                param.add("%airplanes%");
            } else if (method.equals("2")){
                queryString += " and (inFromMacau like ?";
                param.add("%高铁%");
                queryString += " or inFromMacau like ?)";
                param.add("%high-speed railway%");
            } else {
                queryString += " or inFromMacau like ?)";
                param.add("%"+ method +"%");
            }
        }
        //是否需要送关
        if (sCond.getCheckpoint() != null && !sCond.getCheckpoint().equals("")) {
            if (sCond.getCheckpoint().equals(Constant.USER_EXP_SEARCH_CONDITION_YES)){
                queryString += " and (checkpoint like ?";
                param.add("%"+sCond.getCheckpoint()+"%");
                //英文匹配
                queryString += " or checkpoint like ? )";
                param.add("%"+Constant.USER_EXP_SEARCH_CONDITION_EN_YES+"%");
            } else if (sCond.getCheckpoint().equals(Constant.USER_EXP_SEARCH_CONDITION_NO)){
                queryString += " and (checkpoint like ? ";
                param.add("%"+sCond.getCheckpoint()+"%");
                //空白匹配
                queryString += " or checkpoint like ? or checkpoint is null or checkpoint = \"\")";
                param.add("%"+Constant.USER_EXP_SEARCH_CONDITION_EN_NO+"%");
            }
        }
        //住宿特殊需求(有无)
        if (sCond.getSpecialDemands() != null && !sCond.getSpecialDemands().equals("")) {
            queryString += getQuerySql(sCond.getSpecialDemands(), "specialDemands");
        }
        //文化考察路线
        if (sCond.getCultureRoute() != null && !sCond.getCultureRoute().equals("")) {
            String route = sCond.getCultureRoute();
            if(route.equals("路线一")){
                queryString += " and (cultureRoute like ?";
                param.add("%"+route+"%");
                queryString += " or cultureRoute like ?)";
                param.add("%Route one%");
            } else if (route.equals("路线二")){
                queryString += " and (cultureRoute like ?";
                param.add("%"+route+"%");
                queryString += " or cultureRoute like ?)";
                param.add("%Route two%");
            } else if (route.equals("不参加")){
                queryString += " and (cultureRoute like ?";
                param.add("%"+route+"%");
                queryString += " or cultureRoute like ? or cultureRoute is null or cultureRoute = \"\")";
                param.add("%No need%");
            }
        }
        //是否携带配偶
        if (sCond.getBringSpouse() != null && !sCond.getBringSpouse().equals("")) {
            if (sCond.getBringSpouse().equals(Constant.USER_EXP_SEARCH_CONDITION_YES)){
                queryString += " and (bringSpouse like ?";
                param.add("%"+sCond.getBringSpouse()+"%");
                //英文匹配
                queryString += " or bringSpouse like ? )";
                param.add("%"+Constant.USER_EXP_SEARCH_CONDITION_EN_YES+"%");
            } else if (sCond.getBringSpouse().equals(Constant.USER_EXP_SEARCH_CONDITION_NO)){
                queryString += " and (bringSpouse like ? ";
                param.add("%"+sCond.getBringSpouse()+"%");
                //英文匹配
                queryString += " or bringSpouse like ? or bringSpouse is null or bringSpouse = \"\")";
                param.add("%"+Constant.USER_EXP_SEARCH_CONDITION_EN_NO+"%");
            }
        }
        //是否携带随行人员
        if (sCond.getBringEntourage() != null && !sCond.getBringEntourage().equals("")) {
            if (sCond.getBringEntourage().equals(Constant.USER_EXP_SEARCH_CONDITION_YES)){
                queryString += " and (bringEntourage like ?";
                param.add("%"+sCond.getBringEntourage()+"%");
                //英文匹配
                queryString += " or bringEntourage like ? )";
                param.add("%"+Constant.USER_EXP_SEARCH_CONDITION_EN_YES+"%");
            } else if (sCond.getBringEntourage().equals(Constant.USER_EXP_SEARCH_CONDITION_NO)){
                queryString += " and (bringEntourage like ? ";
                param.add("%"+sCond.getBringEntourage()+"%");
                //空白匹配
                queryString += " or bringEntourage like ? or bringEntourage is null or bringEntourage =\"\")";
                param.add("%"+Constant.USER_EXP_SEARCH_CONDITION_EN_NO+"%");
            }
        }

        //多选条件
        if (!selectedVal(sCond.getUserType()).equals("")) {
            queryString += " and userType in (:userType) ";
        }
        if (!selectedVal(sCond.getNation()).equals("")) {
            queryString += " and nation in (:nation) ";
        }
        if (!selectedVal(sCond.getCertType()).equals("")) {
            queryString += " and certType in (:certType) ";
        }
        if (!selectedVal(sCond.getEntryType()).equals("")) {
            queryString += " and entryType in (:entryType) ";
        }

        query = getSession().createSQLQuery(queryString).setString(0, meetingId);
        for(int i=0;i<param.size();i++){
            query.setString(i+1, param.get(i));
        }

        if (!selectedVal(sCond.getUserType()).equals("")) {
            query.setParameterList("userType", selectedVal(sCond.getUserType()).split(","));
        }
        if (!selectedVal(sCond.getNation()).equals("")) {
            query.setParameterList("nation", selectedVal(sCond.getNation()).split(","));
        }
        if (!selectedVal(sCond.getCertType()).equals("")) {
            query.setParameterList("certType", selectedVal(sCond.getCertType()).split(","));
        }
        if (!selectedVal(sCond.getEntryType()).equals("")) {
            query.setParameterList("entryType", selectedVal(sCond.getEntryType()).split(","));
        }
        
        return query;
    }
    
    //判断选择型检索条件是否有被选中
    public String selectedVal(String val){
        if (val == null || val.equals("") || val.length() == 0) {
            return "";
        } else {
            if (val.startsWith(",")){
                return val.substring(1, val.length());
            } else if (val.equals(",")) {
                return "";
            } else {
                return val;
            }
        }
    }
    
    //根据检索条件选择的有或者无拼写检索sql
    private String getQuerySql(String value, String param) {
        String sql = ""; 
        if (value.equals(Constant.USER_EXP_SEARCH_CONDITION_HAS)){
            sql += " and (" + param + " is not null and " + param + " != \"\")";
        } else if (value.equals(Constant.USER_EXP_SEARCH_CONDITION_NULL)){
            sql += " and (" + param + " is null or " + param + "  = \"\")";
        }
        return sql;
    }
    
}
