package com.centling.conference.meetinguser.DAO;

import static org.hibernate.criterion.Example.create;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.centling.conference.base.BaseHibernateDAO;
import com.centling.conference.base.Constant;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.meetinguser.entity.ConfGuest;
import com.centling.conference.meetinguser.entity.ConfMeetingUser;
import com.centling.conference.travel.entity.SearchParams;

/**
 * A data access object (DAO) providing persistence and search support for
 * ConfMeetingUser entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see com.centling.conference.meetinguser.entity.ConfMeetingUser
 * @author MyEclipse Persistence Tools
 */
/**
 * @author lenovo
 *
 */
/**
 * @author lenovo
 *
 */
@Repository("confMeetingUserDAO")
public class ConfMeetingUserDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ConfMeetingUserDAO.class);
	// property constants
	public static final String MEETING_ID = "meetingId";
	public static final String USER_ID = "userId";
	public static final String USER_TYPE = "userType";
	public static final String APPROVE_STATE = "approveState";

	public void save(ConfMeetingUser transientInstance) {
		log.debug("saving ConfMeetingUser instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConfMeetingUser persistentInstance) {
		log.debug("deleting ConfMeetingUser instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConfMeetingUser findById(java.lang.String id) {
		log.debug("getting ConfMeetingUser instance with id: " + id);
		try {
			ConfMeetingUser instance = (ConfMeetingUser) getSession()
					.get("com.centling.conference.meetinguser.entity.ConfMeetingUser",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ConfMeetingUser> findByExample(ConfMeetingUser instance) {
		log.debug("finding ConfMeetingUser instance by example");
		try {
			List<ConfMeetingUser> results = (List<ConfMeetingUser>) getSession()
					.createCriteria(
							"com.centling.conference.meetinguser.entity.ConfMeetingUser")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding ConfMeetingUser instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ConfMeetingUser as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ConfMeetingUser> findByMeetingId(Object meetingId) {
		return findByProperty(MEETING_ID, meetingId);
	}

	public List<ConfMeetingUser> findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List<ConfMeetingUser> findByUserType(Object userType) {
		return findByProperty(USER_TYPE, userType);
	}

	public List<ConfMeetingUser> findByApproveState(Object approveState) {
		return findByProperty(APPROVE_STATE, approveState);
	}

	public List findAll() {
		log.debug("finding all ConfMeetingUser instances");
		try {
			String queryString = "from ConfMeetingUser";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConfMeetingUser merge(ConfMeetingUser detachedInstance) {
		log.debug("merging ConfMeetingUser instance");
		try {
			ConfMeetingUser result = (ConfMeetingUser) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConfMeetingUser instance) {
		log.debug("attaching dirty ConfMeetingUser instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConfMeetingUser instance) {
		log.debug("attaching clean ConfMeetingUser instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

    /**
     * 嘉宾管理机能根据会议ID查找参会人员信息
     * @param pageBean page信息
     * @param meetingId 会议ID
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<ConfGuest> retrieve(PageBean pageBean, String meetingId, ConfGuest condGuest) {
        log.debug("finding all conf_meeting_user instances");
        try {
            List<Object[]> list = null;
            Query query = null;
            String queryString = "select u.id as userId, u.cname, u.last_name, u.ename, u.sex, u.birth, u.nation, u.cert_type, u.cert_value," +    //9
            		" u.cert_expiry_date, u.cert_pic1, u.cert_Pic2, u.email, u.tele, u.mobile, u.fax, u.address, u.postcode," +          //9
            		" u.photo, u.medl_his, u.religion, u.self_intro, u.industry, u.company, u.position, u.visa_need, u.visa_city," +     //9
            		" u.contact_person, u.vegetarian, u.PASSWORD, u.mail_checked, u.diet_taboo, u.etiquette, u.official_lang, " +       //4
            		" cmu.id, cmu.meeting_id, cmu.user_type, cmu.approve_state, " +        //4
            		" m.name, m.start_time, m.end_time, m.city, m.location_id, m.banner_pic, m.url, m.meeting_intro, m.meeting_status, " +   //9
            		" m.name_en, m.meeting_intro_en, " +   //2
            		" u.entry_type, u.entry_place, u.entry_date, u.entry_validity, u.entry_num, u.entry_endmt_date, u.entry_endmt_validity, " +  //7
            		" u.entry_pic1, u.entry_pic2, u.company_type, u.self_intro_en, u.food_allergies, " +  //5
            		" u.use_realname, u.use_alias, u.ualias, u.use_other_ptitle, u.position_title, u.remark, " +   //6
            		" u.work_content, u.superviser_name, u.superviser_phone, u.first_name, u.update_date, cmu.remind_flag, u.card_type" + //7
            		" from conf_user u " +
            		" LEFT JOIN conf_meeting_user cmu ON  u.id = cmu.user_id " +
            		" LEFT JOIN conf_meeting m ON  m.id = cmu.meeting_id " +
            		" where meeting_id = ?";

            List<String> param = new ArrayList<String>();
            if (condGuest.getSname() != null && !condGuest.getSname().equals("")) {
            	queryString += " and (u.cname like ? or u.ename like ? )";
            	param.add("%"+condGuest.getSname()+"%");
            	param.add("%"+condGuest.getSname()+"%");
            }
            if (condGuest.getSemail() != null && !condGuest.getSemail().equals("")) {
            	queryString += " and (u.email like ?) ";
            	param.add("%"+condGuest.getSemail()+"%");
            }

            if (condGuest.getSapproveState() != null && !condGuest.getSapproveState().equals("")) {
            	queryString += " and cmu.approve_state = ? ";
            	param.add(condGuest.getSapproveState());
            }
            
            //多选条件
            if (!selectedVal(condGuest.getSuserType()).equals("")) {
                queryString += " and cmu.user_type in (:userType) ";
            }

            query = getSession().createSQLQuery(queryString).setString(0, meetingId);
            for(int i=0;i<param.size();i++){
            	query.setString(i+1, param.get(i));
            }
            
            if (!selectedVal(condGuest.getSuserType()).equals("")) {
                query.setParameterList("userType", selectedVal(condGuest.getSuserType()).split(","));
            }
            
            list = query.setFirstResult((pageBean.getPage()-1)*pageBean.getRows())
                    .setMaxResults(pageBean.getRows()).list();
            List<ConfGuest> resultList = new ArrayList<ConfGuest>();
            for (Object object[] : list) {
                ConfGuest guest = new ConfGuest();
                //个人信息
                guest.setUserId(object[0] != null ? object[0].toString() : "" );
                guest.setCname(object[1] != null ? object[1].toString() : "" );
                guest.setLastName(object[2] != null ? object[2].toString() : "" );
                guest.setEname(object[3] != null ? object[3].toString() : "" );
                guest.setSex(object[4] != null ? object[4].toString() : "" );
                guest.setBirth(object[5] != null ? object[5].toString() : "" );
                guest.setNation(object[6] != null ? object[6].toString() : "" );
                guest.setCertType(object[7] != null ? object[7].toString() : "" );
                guest.setCertValue(object[8] != null ? object[8].toString() : "" );
                guest.setCertExpiryDate(object[9] != null ? object[9].toString() : "" );
                guest.setCertPic1(object[10] != null ? object[10].toString() : "" );
                guest.setCertPic2(object[11] != null ? object[11].toString() : "" );
                guest.setEmail(object[12] != null ? object[12].toString() : "" );
                guest.setTele(object[13] != null ? object[13].toString() : "" );
                guest.setMobile(object[14] != null ? object[14].toString() : "" );
                guest.setFax(object[15] != null ? object[15].toString() : "" );
                guest.setAddress(object[16] != null ? object[16].toString() : "" );
                guest.setPostcode(object[17] != null ? object[17].toString() : "" );
                guest.setPhoto(object[18] != null ? object[18].toString() : "" );
                guest.setMedlHis(object[19] != null ? object[19].toString() : "" );
                guest.setReligion(object[20] != null ? object[20].toString() : "" );
                guest.setSelfIntro(object[21] != null ? object[21].toString() : "" );
                guest.setIndustry(object[22] != null ? object[22].toString() : "" );
                guest.setCompany(object[23] != null ? object[23].toString() : "" );
                guest.setPosition(object[24] != null ? object[24].toString() : "" );
                guest.setVisaNeed(object[25] != null ? object[25].toString() : "" );
                guest.setVisaCity(object[26] != null ? object[26].toString() : "" );
                guest.setContactPerson(object[27] != null ? object[27].toString() : "" );
                guest.setVegetarian(object[28] != null ? object[28].toString() : "" );
                guest.setPassword(object[29] != null ? object[29].toString() : "" );
                guest.setMailChecked(object[30] != null ? object[30].toString() : "" );
                guest.setDietTaboo(object[31] != null ? object[31].toString() : "" );
                guest.setEtiquette(object[32] != null ? object[32].toString() : "" );
                guest.setOfficialLang(object[33] != null ? object[33].toString() : "" );

                guest.setEntryType(object[49] != null ? object[49].toString() : "" );
                guest.setEntryPlace(object[50] != null ? object[50].toString() : "" );
                guest.setEntryDate(object[51] != null ? object[51].toString() : "" );
                guest.setEntryValidity(object[52] != null ? object[52].toString() : "" );
                guest.setEntryNum(object[53] != null ? object[53].toString() : "" );
                guest.setEntryEndmtDate(object[54] != null ? object[54].toString() : "" );
                guest.setEntryEndmtValidity(object[55] != null ? object[55].toString() : "" );
                guest.setEntryPic1(object[56] != null ? object[56].toString() : "" );
                guest.setEntryPic2(object[57] != null ? object[57].toString() : "" );
                guest.setCompanyType(object[58] != null ? object[58].toString() : "");
                guest.setSelfIntroEn(object[59] != null ? object[59].toString() : "");
                guest.setFoodAllergies(object[60] != null ? object[60].toString() : "");

                guest.setUseRealname(object[61] != null ? object[61].toString() : "");
                guest.setUseAlias(object[62] != null ? object[62].toString() : "");
                guest.setUalias(object[63] != null ? object[63].toString() : "");
                guest.setUseOtherPtitle(object[64] != null ? object[64].toString() : "");
                guest.setPositionTitle(object[65] != null ? object[65].toString() : "");
                guest.setRemark(object[66] != null ? object[66].toString() : "");

                guest.setWorkContent(object[67] != null ? object[67].toString() : "");
                guest.setSuperviserName(object[68] != null ? object[68].toString() : "");
                guest.setSuperviserPhone(object[69] != null ? object[69].toString() : "");
                guest.setFirstName(object[70] != null ? object[70].toString() : "");
                guest.setUpdateDate(object[71] != null ? object[71].toString() : "");
                guest.setRemindFlag(object[72] != null ? object[72].toString() : "");
                guest.setCardType(object[73] != null ? object[73].toString() : "");

                //用户和会议关联信息
                guest.setMeetingUserId(object[34] != null ? object[34].toString() : "" );
                guest.setMeetingId(object[35] != null ? object[35].toString() : "" );
                guest.setUserType(object[36] != null ? object[36].toString() : "" );
                guest.setApproveState(object[37] != null ? object[37].toString() : "" );
                //会议信息
                guest.setMeetingName(object[38] != null ? object[38].toString() : "" );
                guest.setStartTime(object[39] != null ? object[39].toString() : "" );
                guest.setEndTime(object[40] != null ? object[40].toString() : "" );
                guest.setCity(object[41] != null ? object[41].toString() : "" );
                guest.setLocationId(object[42] != null ? object[42].toString() : "" );
                guest.setBannerPic(object[43] != null ? object[43].toString() : "" );
                guest.setUrl(object[44] != null ? object[44].toString() : "" );
                guest.setMeetingIntro(object[45] != null ? object[45].toString() : "" );
                guest.setMeetingStatus(object[46] != null ? object[46].toString() : "" );
                guest.setMeetingNameEn(object[47] != null ? object[47].toString() : "" );
                guest.setMeetingIntroEn(object[48] != null ? object[48].toString() : "" );

                //日程信息
                queryString = "select csu.id, csu.user_id, csu.schedule_id, " +
                		" cs.title, cs.start_time, cs.end_time, cs.intro, cs.media_url, cs.title_en, cs.intro_en " +
                		" from conf_schedule_user csu left join conf_schedule cs on csu.schedule_id = cs.id " +
                		" where user_id = ?";
                List<Object[]> schList = getSession().createSQLQuery(queryString)
                           .setString(0, guest.getUserId()).list();
                List<Map<String, String>> guestSchList = new ArrayList<Map<String, String>>();
                for (Object schObject[] : schList) {
                    Map map = new HashMap();
                    map.put(Constant.SCH_MAP_KEY_CSUID, schObject[0] != null ? schObject[0].toString() : "");
                    map.put(Constant.SCH_MAP_KEY_SCHEDULEID, schObject[2] != null ? schObject[2].toString() : "");
                    map.put(Constant.SCH_MAP_KEY_SCHTITLE, schObject[3] != null ? schObject[3].toString() : "");
                    map.put(Constant.SCH_MAP_KEY_SCHSTARTTIME, schObject[4] != null ? schObject[4].toString() : "");
                    map.put(Constant.SCH_MAP_KEY_SCHENDTIME, schObject[5] != null ? schObject[5].toString() : "");
                    map.put(Constant.SCH_MAP_KEY_SCHINTRO, schObject[6] != null ? schObject[6].toString() : "");
                    map.put(Constant.SCH_MAP_KEY_SCHMEDIAURL, schObject[7] != null ? schObject[7].toString() : "");
                    map.put(Constant.SCH_MAP_KEY_SCHTITLE_EN, schObject[8] != null ? schObject[8].toString() : "");
                    map.put(Constant.SCH_MAP_KEY_SCHINTRO_EN, schObject[9] != null ? schObject[9].toString() : "");
                    guestSchList.add(map);
                }
                guest.setSchList(guestSchList);

                resultList.add(guest);
            }
            return resultList;
        } catch (Exception re) {
            log.error("find all failed", re);
        }
        return null;
    }

    /**
     * 查找会议的注册用户的数量
     *
     */
    public String count(String meetingId, ConfGuest condGuest) {
        log.debug("finding all ConfMeeting instance");
        try {
        	Query query = null;
            String queryString = "SELECT COUNT(*) FROM conf_meeting_user cmu INNER JOIN conf_user u on cmu.user_id = u.id WHERE cmu.meeting_id = ? ";
            List<String> param = new ArrayList<String>();
            if (condGuest.getSname() != null && !condGuest.getSname().equals("")) {
            	queryString += " and (u.cname like ? or u.ename like ? )";
            	param.add("%"+condGuest.getSname()+"%");
            	param.add("%"+condGuest.getSname()+"%");
            }
            if (condGuest.getSemail() != null && !condGuest.getSemail().equals("")) {
            	queryString += " and (u.email like ?) ";
            	param.add("%"+condGuest.getSemail()+"%");
            }

            //多选条件
            if (!selectedVal(condGuest.getSuserType()).equals("")) {
                queryString += " and cmu.user_type in (:userType) ";
            }
            if (condGuest.getSapproveState() != null && !condGuest.getSapproveState().equals("")) {
            	queryString += " and cmu.approve_state = ? ";
            	param.add(condGuest.getSapproveState());
            }

            query = getSession().createSQLQuery(queryString).setString(0, meetingId);
            if (!selectedVal(condGuest.getSuserType()).equals("")) {
                query.setParameterList("userType", selectedVal(condGuest.getSuserType()).split(","));
            }
            for(int i=0;i<param.size();i++){
            	query.setString(i+1, param.get(i));
            }

             return query.uniqueResult().toString();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    /**
     * 更新用户注册信息
     *
     */
    public void update(ConfMeetingUser instance) {
        log.debug("finding all ConfMeetingUser instance");
        try {
            getSession().merge(instance);
        } catch (RuntimeException re) {
            log.error("update failed", re);
            throw re;
        }
    }

    /**
     * 通过UserId删除用户对会议的注册信息
     *
     */
    public void deleteByUserId(String userId, String meetingId) {
        log.debug("finding all ConfMeetingUser instance");
        try {
            String queryString = "delete from conf_meeting_user where user_id = ? and meeting_id=?";
            getSession().createSQLQuery(queryString).setString(0, userId).setString(1, meetingId).executeUpdate();
        } catch (RuntimeException re) {
            log.error("update failed", re);
            throw re;
        }
    }

    /**
     * 通过UserId删除用户对会议的注册信息
     *
     */
    public String findMeetingUserKey(String userId, String meetingId) {
        log.debug("finding all ConfMeetingUser instance");
        try {
            String queryString = "select id from conf_meeting_user where user_id = ? and meeting_id = ?";
            Query query = getSession().createSQLQuery(queryString);
            query.setString(0, userId);
            query.setString(1, meetingId);
            return query.list().get(0).toString();
        } catch (RuntimeException re) {
            log.error("update failed", re);
            throw re;
        }
    }

  //通过useid 和meetingid 查询user 签到 
    public List findUsersBymeetingIdAndUserId(String userId, String meetingId) {
        log.debug("finding all ConfMeetingUser instance");
        try {
            String queryString = "from ConfMeetingUser as c where c.userId = ? and c.meetingId = ? and c.approveState = ?";
            Query query = getSession().createQuery(queryString);
            query.setString(0, userId);
            query.setString(1, meetingId);
            query.setInteger(2, 2);
            return query.list();
        } catch (RuntimeException re) {
            log.error("update failed", re);
            throw re;
        }
    }   
    
 
    
    public void updateApprove(ConfGuest instance) {
        log.debug("update dirty ConfMeetingUser instance");
        try {
            String stringQuery = "update conf_meeting_user set approve_state = ? where meeting_id = ? and user_id = ?";
            Query query = getSession().createSQLQuery(stringQuery);
            query.setString(0, instance.getApproveState());
            query.setString(1, instance.getMeetingId());
            query.setString(2, instance.getUserId());
            query.executeUpdate();
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public List<Object[]> getEmailList(String meetingId) {
		log.debug("query uncompleted user mail");
		try {
			String queryString = "SELECT email,cname FROM conf_v_complete WHERE meeting_id=? AND iscomplete='0'";
			return getSession().createSQLQuery(queryString).setString(0, meetingId).list();
		} catch (RuntimeException re) {
			log.error("query uncompleted user mail failed", re);
			throw re;
		}
	}
  
    /**
     * 通过UserId删除用户对会议的注册信息
     *
     */
    public void deleteRoomInfo(String meetingId) {
        log.debug("finding all ConfMeetingUser roomInfo instance");
        try {
            String queryString = "update conf_meeting_user t set t.room_type=null, t.organizer_pay=null where t.meeting_id=?";
            getSession().createSQLQuery(queryString).setString(0, meetingId).executeUpdate();
        } catch (RuntimeException re) {
            log.error("update failed", re);
            throw re;
        }
    }
    
    /**
     * 给参会人员自动分配房型、主办方是否承担费用信息
     * @param meetingId
     */
    public void updateRoomInfo(String meetingId){
    	  log.debug("update room_type, organizer_pay of meeting_user ");
          try {
              String queryString = "update conf_meeting_user t  " +
              		"set t.room_type = (select r.room_type from conf_user_room r " +
              		"where r.meeting_id = :meetingId and r.user_type=t.user_type limit 1), " +
              		"t.organizer_pay = (select r.organizer_pay from conf_user_room r " +
              		"where r.meeting_id = :meetingId and r.user_type=t.user_type limit 1) " +
              		"where t.meeting_id = :meetingId AND EXISTS (SELECT 1 FROM conf_user_room b WHERE t.user_type=b.user_type)";
              getSession().createSQLQuery(queryString).setString("meetingId", meetingId).executeUpdate();
          } catch (RuntimeException re) {
              log.error("update failed", re);
              throw re;
          }
    }
    
    /**
     * 查询列表信息，显示房型，是否承担费用等信息
     * @param meetingId
     */
    @SuppressWarnings("unchecked")
	public List<Map<String,Object>> findUserRoomInfo(String meetingId){
    	  log.debug("search room_type, organizer_pay of meeting_user ");
          try {
              String queryString = "select mu.id,u.cname,u.ename,mu.user_type userType,u.sex,u.email,u.mobile,mu.room_type roomType,mu.organizer_pay organizerPay " +
              		"from conf_meeting_user mu left join conf_user u on mu.user_id = u.id " +
              		"where mu.meeting_id=:meetingId and mu.room_type is not null";
             return getSession().createSQLQuery(queryString).setString("meetingId", meetingId).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
          } catch (RuntimeException re) {
              log.error("attach failed", re);
              throw re;
          }
    }
    
    
    /**
     * 给参会人员自动分配房型、主办方是否承担费用信息 (分页显示)
     * @param meetingId
     */
    @SuppressWarnings("unchecked")
	public List<Map<String, Object>> findRoomInfoPages(PageBean pageBean, String meetingId, String sname, String semail, String suserType) {
		log.debug("search room_type, organizer_pay of meeting_user ");
		try {
			String queryString = "select mu.id,u.cname,u.ename,mu.user_type userType,u.sex,u.email,u.mobile," +
					"mu.room_type roomType,mu.organizer_pay organizerPay,mu.check_in_date checkInDate,mu.check_out_date checkOutDate "
					+ "from conf_meeting_user mu left join conf_user u on mu.user_id = u.id "
					+ "where mu.meeting_id=? and mu.room_type is not null";
			
			List<String> param = new ArrayList<String>();
			param.add(meetingId);
			
			if (sname != null && !sname.equals("")) {
            	queryString += " and (u.cname like ? or u.ename like ? )";
            	param.add("%"+sname+"%");
            	param.add("%"+sname+"%");
            }
            if (semail != null && !semail.equals("")) {
            	queryString += " and (u.email like ?) ";
            	param.add("%"+semail+"%");
            }

            if ( suserType != null && ! suserType.equals("")) {
            	queryString += " and mu.user_type = ? ";
            	param.add( suserType);
            }
            
			Query query = getSession().createSQLQuery(queryString);
			for(int i=0;i<param.size();i++){
            	query.setString(i, param.get(i));
            }
			
			query.setFirstResult((pageBean.getPage() - 1) * pageBean.getRows()).setMaxResults(pageBean.getRows());
			return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();

		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
    
    /**
     * 计算总数 (分页显示)
     * @param meetingId
     */
    public String roomCount(String meetingId, String sname, String semail, String suserType) {
		log.debug("get count of meeting_user with room info");
		try {
            String queryString = "select count(1) from conf_meeting_user mu left join conf_user u on mu.user_id = u.id "
					+ "where mu.meeting_id=? and mu.room_type is not null";
            List<String> param = new ArrayList<String>();
			param.add(meetingId);
			
			if (sname != null && !sname.equals("")) {
            	queryString += " and (u.cname like ? or u.ename like ? )";
            	param.add("%"+sname+"%");
            	param.add("%"+sname+"%");
            }
            if (semail != null && !semail.equals("")) {
            	queryString += " and (u.email like ?) ";
            	param.add("%"+semail+"%");
            }

            if ( suserType != null && ! suserType.equals("")) {
            	queryString += " and mu.user_type = ? ";
            	param.add( suserType);
            }
            
			Query query = getSession().createSQLQuery(queryString);
			for(int i=0;i<param.size();i++){
            	query.setString(i, param.get(i));
            }

	        return query.uniqueResult().toString();
	        
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    
    
	/**
	 * 更新meeting_user表中的房间信息
	 * @param user
	 */
	public void updateUserRoom(ConfMeetingUser user){
    	  log.debug("update room_type, organizer_pay of meeting_user ");
          try {
        	  String queryString = "update conf_meeting_user set " +
        	  		"room_type = ?, organizer_pay = ?, check_in_date = ?,check_out_date = ? " +
        	  		"where id = ?";
        	  Query query = getSession().createSQLQuery(queryString);
        	  
        	  query.setParameter(0, user.getRoomType());
        	  query.setParameter(1, user.getOrganizerPay());
        	  query.setParameter(2, user.getCheckInDate());
        	  query.setParameter(3, user.getCheckOutDate());
        	  query.setParameter(4, user.getId());
        	  
        	  query.executeUpdate();
             
          } catch (RuntimeException re) {
              log.error("update failed", re);
              throw re;
          }
    }

	/**
	 * 根据邮箱查找用户信息
	 * @param email
	 * @param meetingId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> findUserInfoByEmail(String meetingId,String email){
		 log.debug("search basic info of meeting_user ");
         try {
             String queryString = "	select mu.id,mu.user_type userType,mu.room_type as roomType,u.cname,u.ename,u.email,u.mobile from conf_meeting_user mu " +
             		"left join conf_user u on mu.user_id = u.id	" +
             		"where mu.meeting_id = ? and u.email= ? ";
            return getSession().createSQLQuery(queryString).setString(0, meetingId).setString(1, email).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
         } catch (RuntimeException re) {
             log.error("attach failed", re);
             throw re;
         }
	}
	
	
	/**
	 * 删除参会人员的房间信息
	 * @param id
	 * @return
	 */
	public void delUserRoom(String id){
		 log.debug("clear room info of meeting_user ");
         try {
        	 String queryString = "update conf_meeting_user set " +
         	  		"room_type = null, organizer_pay = null, check_in_date = null,check_out_date = null " +
         	  		"where id = ?";
        	 
         	  Query query = getSession().createSQLQuery(queryString);
         	  query.setParameter(0, id);
         	  
         	  query.executeUpdate();
              
         } catch (RuntimeException re) {
             log.error("update failed", re);
             throw re;
         }
	}
	
	
	/**
	 * 房间类型用户类型统计
	 * @param meetingId
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> roomStat(String startDate, String endDate, String meetingId){
		log.debug("search  Statistics info of meeting_user's room ");
        try {
            String queryString = "select count(1) count,t.room_type roomType,t.organizer_pay organizerPay from conf_meeting_user t " +
            		"where t.room_type is not null and t.meeting_id =:meetingId ";
            if(startDate!=null && !startDate.equals("")){
            	queryString += "and to_days(t.check_in_date) >= to_days(:start) ";
            }
            if(endDate!=null && !endDate.equals("")){
            	queryString += "and to_days(t.check_out_date) < to_days(:end) ";
            }
            queryString += "group by t.room_type,t.organizer_pay";
            
            
            Query query = getSession().createSQLQuery(queryString);
            query.setString("meetingId", meetingId);
            
            if(startDate!=null && !startDate.equals("")){
            	query.setString("start", startDate);
            }
            if(endDate!=null && !endDate.equals("")){
            	query.setString("end", endDate);
            }
            
            return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
            
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
	}

	/**
	 * 自动分配入住时间和离开时间，内容从航班信息表中取得
	 * 
	 * @param meetingId
	 */
	public void updateCheckDate(String meetingId) {
		log.debug("update check_in_date, check_out_date of meeting_user ");
		try {
			String queryString = "update conf_meeting_user cmu set " +
					"cmu.check_in_date = (select substring_index(cut.end_time_come,' ',1) from conf_user_travel cut where cut.user_id = cmu.user_id and cut.meeting_id = cmu.meeting_id )," +
					"cmu.check_out_date =  (select substring_index(cut.start_time_go,' ',1) from conf_user_travel cut where cut.user_id = cmu.user_id and cut.meeting_id = cmu.meeting_id ) " +
					"where (cmu.check_in_date is null or cmu.check_out_date is null or cmu.check_in_date='' or cmu.check_out_date='') " +
					"and cmu.meeting_id = :meetingId and cmu.room_type is not null";
			getSession().createSQLQuery(queryString).setString("meetingId", meetingId).executeUpdate();
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}
	
	//修改嘉宾提醒事件的值
    public void updateRemindFlag(String meetingId, String userId, String flag){
        log.debug("update remindFlag of meeting_user ");
        try {
            String queryString = "update conf_meeting_user t  " +
                  "set t.remind_flag = :remindFlag " +
                  "where t.meeting_id = :meetingId and t.user_id = :userId ";
            getSession().createSQLQuery(queryString).setString("remindFlag", flag)
                .setString("meetingId", meetingId).setString("userId", userId).executeUpdate();
        } catch (RuntimeException re) {
            log.error("update failed", re);
            throw re;
        }
    }

	public List<Map<String, Object>> retrieve(PageBean pageBean, String meetingId,SearchParams searchParams) {
		log.debug("finding room statistics Pagition");
		try {
			Query queryObject = null;
			StringBuffer queryString = new StringBuffer(" select b.cname,a.user_type as userType,b.cert_value as certValue,b.mobile,a.check_in_date as checkInDate,a.check_out_date as checkOutDate,datediff(check_out_date,check_in_date) as diffDate,a.room_type as roomType,a.organizer_pay as organizerPay ");
			queryObject = selectUserSql(queryObject, queryString, meetingId, searchParams);
			queryObject.setFirstResult((pageBean.getPage()-1)*pageBean.getRows())
   		 		.setMaxResults(pageBean.getRows())
   		 		.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Long count(String meetingId,SearchParams searchParams) {
		log.debug("finding room statistics Pagition");
		try {
			Query queryObject = null;
			StringBuffer queryString = new StringBuffer(" select count(1) ");
			queryObject = selectUserSql(queryObject, queryString, meetingId, searchParams);
			return ((BigInteger)queryObject.uniqueResult()).longValue();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public Long countByMeetingId(String meetingId) {
		log.debug("finding all ConfMeetingUser count");
        try {
            String queryString = "select count(1) from ConfMeetingUser where meetingId = ?";
            return (Long)(getSession().createQuery(queryString).setString(0, meetingId).uniqueResult());
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
	}

	public List<Object[]> findRoomStatic(String meetingId,SearchParams searchParams) {
		log.debug("finding room statistics");
		try {
			Query queryObject = null;
			StringBuffer queryString = new StringBuffer(" select b.cname, case b.sex when '1' then '男' when '2' then '女' end as sex,b.birth,b.position,a.user_type as userType,b.cert_value as certValue,b.mobile,a.room_type as roomType,a.check_in_date as checkInDate,a.check_out_date as checkOutDate,datediff(check_out_date,check_in_date) as diffDate,case a.organizer_pay when '1' then '主办方承担' else '参会人员承担' end as organizerPay ");
			queryObject = selectUserSql(queryObject, queryString, meetingId, searchParams);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    //获取嘉宾的嘉宾提醒状态
    public String findRemindFlag(String meetingId, String userId) {
        log.debug("finding remindFlag statistics");
        try {
            String queryString = "select remind_flag from conf_meeting_user where meeting_id = :meetingId and user_id = :userId";
            Query queryObject = getSession().createSQLQuery(queryString);
            queryObject.setString("meetingId", meetingId).setString("userId", userId);
            return (String)queryObject.uniqueResult();
        } catch (RuntimeException re) {
            log.error("find remindFlag failed", re);
            throw re;
        }
    }
    
    public Query selectUserSql(Query query,StringBuffer queryString,String meetingId,SearchParams searchParams){   	
    	queryString.append(" from conf_meeting_user a left join conf_user b on a.user_id=b.id ");
    	List<String> param = new ArrayList<String>();
		queryString.append(" where (a.check_in_date is not null or a.check_in_date<>'') and a.meeting_id=? ");
		param.add(meetingId);
		if (StringUtils.isNotEmpty(searchParams.getCname())) {
			queryString.append(" AND b.cname LIKE ? ");
			param.add("%"+searchParams.getCname()+"%");
		}
		if(StringUtils.isNotEmpty(searchParams.getUserType())){
			queryString.append(" AND a.user_type = ? ");
			param.add(searchParams.getUserType());
		}
		if(StringUtils.isNotEmpty(searchParams.getRoomType())){
			queryString.append(" AND a.room_type=? ");
			param.add(searchParams.getRoomType());
		}
		if(StringUtils.isNotEmpty(searchParams.getCheckInDate())){
			queryString.append(" AND TO_DAYS(a.check_in_date)>=TO_DAYS(?) ");
			param.add(searchParams.getCheckInDate());
		}
		if(StringUtils.isNotEmpty(searchParams.getCheckOutDate())){
			queryString.append(" AND TO_DAYS(a.check_out_date)<=TO_DAYS(?) ");
			param.add(searchParams.getCheckOutDate());
		}
		if(StringUtils.isNotEmpty(searchParams.getOrganizerPay())){
			queryString.append(" AND a.organizer_pay=? ");
			param.add(searchParams.getOrganizerPay());
		}
		query = getSession().createSQLQuery(queryString.toString());
		for(int i=0;i<param.size();i++){
            query.setString(i, param.get(i));
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
}