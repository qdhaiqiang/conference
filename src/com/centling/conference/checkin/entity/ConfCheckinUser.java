package com.centling.conference.checkin.entity;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


/**
 * ConfCheckinUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="conf_checkin_user"
    ,catalog="conference"
)

public class ConfCheckinUser  implements java.io.Serializable {


    // Fields    

     private String id;
     private String userId;
     private String meetingId;
     private Integer checkState;
     private String checkTime;
     private String checkTimeCopy;
     private String operator;
     private String scheduleId;
     private Integer state;
     private Integer isHeadset;


    // Constructors

    /** default constructor */
    public ConfCheckinUser() {
    }

    
    /** full constructor */
    public ConfCheckinUser(String userId, String meetingId, Integer checkState, String checkTime, String checkTimeCopy, String operator, String scheduleId, Integer state, Integer isHeadset) {
        this.userId = userId;
        this.meetingId = meetingId;
        this.checkState = checkState;
        this.checkTime = checkTime;
        this.checkTimeCopy = checkTimeCopy;
        this.operator = operator;
        this.scheduleId = scheduleId;
        this.state = state;
        this.isHeadset = isHeadset;
    }

   
    // Property accessors
    @GenericGenerator(name="generator", strategy="uuid.hex")@Id @GeneratedValue(generator="generator")
    
    @Column(name="id", unique=true, nullable=false, length=40)

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    @Column(name="user_id", length=100)

    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    @Column(name="meeting_id", length=50)

    public String getMeetingId() {
        return this.meetingId;
    }
    
    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }
    
    @Column(name="check_state")

    public Integer getCheckState() {
        return this.checkState;
    }
    
    public void setCheckState(Integer checkState) {
        this.checkState = checkState;
    }
    
    @Column(name="check_time", length=50)

    public String getCheckTime() {
        return this.checkTime;
    }
    
    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }
    
    @Column(name="check_time_copy", length=50)

    public String getCheckTimeCopy() {
        return this.checkTimeCopy;
    }
    
    public void setCheckTimeCopy(String checkTimeCopy) {
        this.checkTimeCopy = checkTimeCopy;
    }
    
    @Column(name="operator", length=50)

    public String getOperator() {
        return this.operator;
    }
    
    public void setOperator(String operator) {
        this.operator = operator;
    }
    
    @Column(name="schedule_id", length=50)

    public String getScheduleId() {
        return this.scheduleId;
    }
    
    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }
    
    @Column(name="state")

    public Integer getState() {
        return this.state;
    }
    
    public void setState(Integer state) {
        this.state = state;
    }

    @Column(name="isheadset")
    
	public Integer getIsHeadset() {
		return isHeadset;
	}


	public void setIsHeadset(Integer isHeadset) {
		this.isHeadset = isHeadset;
	}
    
   

  
   








}