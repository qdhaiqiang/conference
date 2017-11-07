package com.centling.conference.dyn.field.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


/**
 * ConfDynField entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="conf_dyn_field"
    
)

public class ConfDynField  implements java.io.Serializable {


    // Fields    

     private String id;
     private String type;
     private String name;
     private String meetingId;
     private String userType;
     private String required;
     private String description;
     private String options;
     private Integer orderNum;
     private String cid;


    // Constructors

    /** default constructor */
    public ConfDynField() {
    }

    
    /** full constructor */
    public ConfDynField(String type, String name, String meetingId, String userType, String required, String description, String options, Integer orderNum,String cid) {
        this.type = type;
        this.name = name;
        this.meetingId = meetingId;
        this.userType = userType;
        this.required = required;
        this.description = description;
        this.options = options;
        this.orderNum = orderNum;
        this.cid = cid;
    }
    
    /** full constructor */
    public ConfDynField(String type, String name, String meetingId, String userType, String required, String description, String options, Integer orderNum) {
        this.type = type;
        this.name = name;
        this.meetingId = meetingId;
        this.userType = userType;
        this.required = required;
        this.description = description;
        this.options = options;
        this.orderNum = orderNum;
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
    
    @Column(name="type", length=20)

    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    @Column(name="name", length=20)

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="meeting_id", length=40)

    public String getMeetingId() {
        return this.meetingId;
    }
    
    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }
    
    @Column(name="user_type", length=2)

    public String getUserType() {
        return this.userType;
    }
    
    public void setUserType(String userType) {
        this.userType = userType;
    }
    
    @Column(name="required", length=1)

    public String getRequired() {
        return this.required;
    }
    
    public void setRequired(String required) {
        this.required = required;
    }
    
    @Column(name="description", length=500)

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Column(name="options", length=65535)

    public String getOptions() {
        return this.options;
    }
    
    public void setOptions(String options) {
        this.options = options;
    }
    
    @Column(name="order_num")

    public Integer getOrderNum() {
        return this.orderNum;
    }
    
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    @Column(name="cid")
	public String getCid() {
		return cid;
	}


	public void setCid(String cid) {
		this.cid = cid;
	}
   








}