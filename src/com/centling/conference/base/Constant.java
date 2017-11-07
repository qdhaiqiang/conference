package com.centling.conference.base;

public class Constant {

    public static final String MEETING_CITY_CODE1 = "1";
    public static final String MEETING_CITY_CODE2 = "2";
    public static final String MEETING_CITY_CODE3 = "3";
    public static final String MEETING_CITY_CODE4 = "4";
    
    public static final String MEETING_STAUTS_CODE1 = "1";
    public static final String MEETING_STAUTS_CODE2 = "2";
    public static final String MEETING_STAUTS_CODE3 = "3";
    
    
    //用户审核状态，1未审核，2审核通过，3审核未通过
  	public static final String APPROVED_UN = "1";   
  	public static final String APPROVE_PASS = "2"; 
  	public static final String APPROVE_FAIL = "3";
    
    // 后台保存Session的会议编号键
    public static final String SESSION_MEETING_ID = "meetingId";
    
    // 后台保存Session的会议日程List
    public static final String SESSION_SCHEDULE_LIST = "scheduleList";
    
    // 后台保存Session的会议日程编号ID
    public static final String SESSION_SCHEDULE_ID = "scheduleId";
    
    // 前台
    public static final String FRONT_SESSION_MEETING_ID = "frontMeetingId";
    
    public static final String FRONT_SESSION_MEETING = "frontMeeting";
    
    public static final String BACK_SESSION_USER = "confSysUser";
    
    public static final String HTTP_STR = "http://";
    
    public static final String URL_PREFIX = "www";
    
    public static final String URL_SUFFIX = "namkwong.org";
    
    public static final String PROJECT_NAME = "/CONF_MGR";
    
    //保存用户session：user_info
    public static final String SESSION_CONF_USER = "user_info";
    
    //保存用户session：user_info
    public static final String SESSION_FRONT_CONF_COMPANY = "front_company";
    
    //保存用户session：user_info
    public static final String SESSION_front_MeetingForm = "front_Meetingform";
    // VIP嘉宾
    public static final String USER_TYPE_VIP = "1";
    public static final String USER_TYPE_VIP_US = "12";
    
    // 演讲嘉宾
    public static final String USER_TYPE_SPEAKER = "2";
    public static final String USER_TYPE_SPEAKER_EN = "13";
    
    // 参会嘉宾
    public static final String USER_TYPE_ATTEND = "3";
    public static final String USER_TYPE_ATTEND_EN = "14";
    
    // 参会观众
    public static final String USER_TYPE_AUDIENCE = "4";
    public static final String USER_TYPE_AUDIENCE_EN = "15";
    
    // 媒体嘉宾
    public static final String USER_TYPE_MEDIA = "5";
    public static final String USER_TYPE_MEDIA_EN = "16";
    
    // 参展商
    public static final String USER_TYPE_EXHIBITORS = "6";
    public static final String USER_TYPE_EXHIBITORS_EN = "17";
    
    // 组委会人员
    public static final String USER_TYPE_ORG = "7";
    public static final String USER_TYPE_ORG_EN = "18";
    
    // 供应商
    public static final String USER_TYPE_SUPPLIER = "8";
    public static final String USER_TYPE_SUPPLIER_EN = "19";
    
    // 志愿者
    public static final String USER_TYPE_VOLUNTEER = "9";
    public static final String USER_TYPE_VOLUNTEER_EN = "20";
    
    // 注册媒体
    public static final String USER_TYPE_REGIST_MEDIA = "10";
    public static final String USER_TYPE_REGIST_MEDIA_EN = "21";
    
    // 随行人员
    public static final String USER_TYPE_ENTOURAGE = "11";
    public static final String USER_TYPE_ENTOURAGE_EN = "22";
    
    // 随行人员--配偶
    public static final String USER_TYPE_SPOUSE = "23";
    public static final String USER_TYPE_SPOUSE_EN = "24";
    
    // 随行人员类型---配偶
    public static final String ENTOURAGE_SPOUSE = "1";
    
    // 随行人员类型---随从
    public static final String ENTOURAGE_FOLLOWER = "2";
    
    // 提醒函
    public static final String MAIL_NOTIFICATION = "1";
    
    // 确认函
    public static final String MAIL_CONFIRM = "2";
  
  //0代表签入失败，1代表签入成功
    public static final Integer CHECKIN_FAIL = 0;
    public static final Integer CHECKIN_SUCESS = 1;
   //签入状态 
    public static final Integer CHECKIN_STATE_IN= 1;
    public static final Integer CHECKIN_STATE_OUT = -1; 
    
  //签入时时候有耳机， 1代表发了耳机，-1代表还了耳机，0代表什么都没做
    public static final Integer YES_HEADSET= 1;
    public static final Integer YES_NO_HEADSET =0;
    public static final Integer NO_HEADSET = -1;
    
    //后台嘉宾管理用日程map的KEY
    public static final String SCH_MAP_KEY_CSUID = "csuId";
    public static final String SCH_MAP_KEY_SCHEDULEID = "scheduleId";
    public static final String SCH_MAP_KEY_SCHTITLE = "schTitle";
    public static final String SCH_MAP_KEY_SCHSTARTTIME = "schStartTime";
    public static final String SCH_MAP_KEY_SCHENDTIME = "schEndTime";
    public static final String SCH_MAP_KEY_SCHINTRO = "schIntro";
    public static final String SCH_MAP_KEY_SCHMEDIAURL = "schMediaUrl"; 
    public static final String SCH_MAP_KEY_SCHTITLE_EN = "schTitleEn"; 
    public static final String SCH_MAP_KEY_SCHINTRO_EN = "schIntroEn"; 
    
    // 国际化--英文
    public static final String LOCALE_US = "en_US";
    // 国际化--中文
    public static final String LOCALE_CN = "zh_CN";
    // 工作人员注册
    public static final String REGISTER_STAFF = "1";
    // 嘉宾注册
    public static final String REGISTER_GUEST = "2";
    
    // 确认函
    public static final String EMAIL_TYPE_COMFIRM = "1";
    
    // 提醒函
    public static final String EMAIL_TYPE_NOTIFY = "2";
    
    // 验证码
    public static final String EMAIL_TYPE_CODE = "3";
    
    // 票务提醒
    public static final String EMAIL_TYPE_TICKET = "4";
    
    // 票务提醒(工作人员)
    public static final String EMAIL_TYPE_TICKET_ASSIGN = "7";
    
    // 一对一指派通知工作人员email
    public static final String EMAIL_TYPE_ASSIGN_USER_EMAIL = "5";
    
    // 一对一指派通知工作人员  短信
    public static final String EMAIL_TYPE_ASSIGN_USER_MSG = "4";
    
    // 一对一指派通知嘉宾email
    public static final String EMAIL_TYPE_ASSIGN_GUEST_EMAIL = "6";
    
    // 一对一指派通知嘉宾  短信
    public static final String EMAIL_TYPE_ASSIGN_GUEST_MSG = "5";
    
    // 事件email提醒模板
    public static final String EMAIL_TYPE_EVENT_REMIND = "8";
    
    //游艇验证码邮件模板
    public static final String EMAIL_TYPE_YACHT_CODE = "9";
    
    // 事件定制短信提醒模板
    public static final String SMS_TYPE_EVENT_REMIND = "1";
    
    // 到场嘉宾短信提醒模板
    public static final String SMS_TYPE_GUEST_ARRIVED = "2";
    
    // 票务提醒
    public static final String MSG_TYPE_TICKET = "3";
    
    
    public static final String SESSION_front_approveState = "front_approveState";
    
    //后台新增人员的初始密码
    public static final String DEFULT_PASSWORD = "123456";
    
    // 菜单等级一级
    public static final Short MENU_LEVEL_FIRST = 1;
    // 菜单等级二级
    public static final Short MENU_LEVEL_SECOND = 2;
    
    //用户信息导出时用到的检索结果集KEY
    public static final String RESULT_KEY_MEETINGID = "meetingId";
    public static final String RESULT_KEY_USERID = "userId";
    public static final String RESULT_KEY_USERTYPE = "userType";
    public static final String RESULT_KEY_CNAME = "cname";
    public static final String RESULT_KEY_ENAME = "ename";
    public static final String RESULT_KEY_FIRSTNAME = "firstName";
    public static final String RESULT_KEY_LASTNAME = "lastName";
    public static final String RESULT_KEY_SEX = "sex";
    public static final String RESULT_KEY_BIRTH = "birth";
    public static final String RESULT_KEY_NATION = "nation";
    public static final String RESULT_KEY_RELIGION = "religion";
    public static final String RESULT_KEY_DIETTABOO = "dietTaboo";
    public static final String RESULT_KEY_MEDLHIS = "medlHis";
    public static final String RESULT_KEY_FOODALLERGIES = "foodAllergies";
    public static final String RESULT_KEY_ADDRESS = "address";
    public static final String RESULT_KEY_POSTCODE = "postcode";
    public static final String RESULT_KEY_TELE = "tele";
    public static final String RESULT_KEY_MOBILE = "mobile";
    public static final String RESULT_KEY_EMAIL = "email";
    public static final String RESULT_KEY_FAX = "fax";
    public static final String RESULT_KEY_CONTACTPERSON = "contactPerson";
    public static final String RESULT_KEY_CERTTYPE = "certType";
    public static final String RESULT_KEY_CERTVALUE = "certValue";
    public static final String RESULT_KEY_ENTRYTYPE = "entryType";
    public static final String RESULT_KEY_ENTRYPLACE = "entryPlace";
    public static final String RESULT_KEY_ENTRYDATE = "entryDate";
    public static final String RESULT_KEY_ENTRYVALIDITY = "entryValidity";
    public static final String RESULT_KEY_ENTRYNUM = "entryNum";
    public static final String RESULT_KEY_ENTRYENDMTDATE = "entryEndmtDate";
    public static final String RESULT_KEY_ENTRYENDMTVALIDITY = "entryEndmtValidity";
    public static final String RESULT_KEY_COMPANY = "company";
    public static final String RESULT_KEY_COMPNYTYPE = "compnyType";
    public static final String RESULT_KEY_INDUSTRY = "industry";
    public static final String RESULT_KEY_WORKCONTENT = "workContent";
    public static final String RESULT_KEY_SUPERVISERNAME = "superviserName";
    public static final String RESULT_KEY_SUPERVISERPHONE = "superviserPhone";
    public static final String RESULT_KEY_POSITION = "position";
    public static final String RESULT_KEY_SELFINTRO = "selfIntro";
    public static final String RESULT_KEY_SELFINTROEN = "selfIntroen";
    public static final String RESULT_KEY_USEREALNAME = "useRealName";
    public static final String RESULT_KEY_USEALIAS = "useAlias";
    public static final String RESULT_KEY_UALIAS = "ualias";
    public static final String RESULT_KEY_USEOTHERPTITLE = "useOtherpTitle";
    public static final String RESULT_KEY_POSITIONTITLE = "positionTitle";
    public static final String RESULT_KEY_REMARK = "remark";
    public static final String RESULT_KEY_NEEDINVITE = "needInvite";
    public static final String RESULT_KEY_NEEDVISA = "needVisa";
    public static final String RESULT_KEY_NEEDFAXINVITE = "needFaxInvite";
    public static final String RESULT_KEY_NEEDCHINAVISASERVICE = "needChinaVisaService";
    public static final String RESULT_KEY_DEPARTUREREGION = "departureRegion";
    public static final String RESULT_KEY_INFROMMACAU = "inFromMacau";
    public static final String RESULT_KEY_DEPARTUREPLACE = "departurePlace";
    public static final String RESULT_KEY_DEPARTUREDATE = "departureDate";
    public static final String RESULT_KEY_DEPARTURETIMEPERIOD = "departureTimePeriod";
    public static final String RESULT_KEY_RETURNPLACE = "returnPlace";
    public static final String RESULT_KEY_RETURNDATE = "returnDate";
    public static final String RESULT_KEY_RETURNTIMEPERIOD = "returnTimePeriod";
    public static final String RESULT_KEY_CHECKPOINT = "checkPoint";
    public static final String RESULT_KEY_NORMALROOMSERVICE = "normalRoomService";
    public static final String RESULT_KEY_NORMALROOMEXTRA = "normalRoomExtra";
    public static final String RESULT_KEY_SPECIALDEMANDS = "specialDemands";
    public static final String RESULT_KEY_CULTUREROUTE = "culturEroute";
    public static final String RESULT_KEY_SPEECHESTITLE = "speechesTitle";
    public static final String RESULT_KEY_PPTNEED = "PPTNeed";
    public static final String RESULT_KEY_BRINGSPOUSE = "bringSpouse";
    public static final String RESULT_KEY_SPOUSEEMAIL = "spouseEmail";
    public static final String RESULT_KEY_BRINGENTOURAGE = "bringEntourage";
    public static final String RESULT_KEY_ENTOURAGENUM = "entourageNum";
    public static final String RESULT_KEY_ENTOURAGEEMAIL = "entourageEmail";
    public static final String RESULT_KEY_IMPORTINFO = "importInfo";
    public static final String RESULT_KEY_INTERVIEWGUEST = "interViewGuest";
    public static final String RESULT_KEY_SAMECONTACTPERSON = "sameContactPerson";
    public static final String RESULT_KEY_ATTENDCONFERENCE = "attendConference";
    public static final String RESULT_KEY_ACTIVITIES = "activities";
    public static final String RESULT_KEY_ARRIVEDLEAVE = "arrivedLeave";
    public static final String RESULT_KEY_ROUNDTRIP = "roundTrip";
    public static final String RESULT_KEY_OTHERNEEDS = "otherNeeds";
    public static final String RESULT_KEY_EXHIBITSTYPE = "exhibitsType";
    public static final String RESULT_KEY_STANDINTRO = "standIntro";
    public static final String RESULT_KEY_MAINPRODUCT = "mainProduct";
    public static final String RESULT_KEY_QUANTITY = "quantity";
    public static final String RESULT_KEY_SPECIAL = "special";
    public static final String RESULT_KEY_ADVERTISE = "advertise";
    public static final String RESULT_KEY_SPONSOR = "sponsor";
    public static final String RESULT_KEY_VIDEO = "video";
    public static final String RESULT_KEY_CHANNELS = "channels";
    public static final String RESULT_KEY_ANTICIPATION = "anticipation";
    public static final String RESULT_KEY_PURPOSES = "purposes";
    public static final String RESULT_KEY_FEELINGS = "feelings";
    public static final String RESULT_KEY_CAREABOUT = "careAbout";
    public static final String RESULT_KEY_OPITIONS = "opitions";
    public static final String RESULT_KEY_SUGGESTIONS = "suggestions";
    
    // 前台超级密码
    public static final String FRONT_SUPER_PASS = "d3cf0bfba3de7371d57ec9c7c4d31fa4";
    
    
    public static final int EXHIBIT_NUM = 5;    // 展品最大添加数量
    public static final int STATE_USE   = 0;    // 展位已被占用
    public static final int STATE_UNUSE = 1;    //展位未被占用
    
    //人员导出检索条件中的是否得转换
    public static final String USER_EXP_SEARCH_CONDITION_YES = "是";
    public static final String USER_EXP_SEARCH_CONDITION_NO = "否";
    public static final String USER_EXP_SEARCH_CONDITION_EN_YES = "Yes";
    public static final String USER_EXP_SEARCH_CONDITION_EN_NO = "No";
    public static final String USER_EXP_SEARCH_CONDITION_HAS = "1";//选择"有"或者"是"
    public static final String USER_EXP_SEARCH_CONDITION_NULL = "0";//选择"无"或者"否"
    
    //短信URL
    public static final String SMSURL = "http://58.83.147.92:8080/qxt/smssenderv2";
    // 短信接口用户名
    public static final String SMSUSERNAME = "cs_xinwei";
    // 短信接口密码
    public static final String SMSPASSWORD = "xin123";
    
    public static final String ROLE_ADMIN = "admin";
    
    //人员指派时选择的嘉宾ID
    public static final String ASSIGN_GUEST_ID = "guestId";
    //其他人员指派时选择的日程ID
    public static final String ASSIGN_SCHEDULE_ID = "scheduleId";

 // 展会菜单树
    public static final String CONF_ESSAY_MENU = "confEssayMenu";
    
    //事件定制sessionID
    public static final String SESSION_EVENT_ID = "eventID";
    
    // 澳门游艇展前缀
    public static final String YACHT_PREFIX = "mcyachtshow";
    
    // 手机端新闻中心
    public static final String MOBILE_NEWS_LIST = "mobileNewsList";
    
    // 手机端会议指南列表
    public static final String MOBILE_MEETING_GUIDE = "mobileGuideList";
    
    // 票务通知未通知
    public static final String TRAVEL_MESSAGE_UNSEND = "0";
    // 票务通知已通知
    public static final String TRAVEL_MESSAGE_SEND = "1";
}
