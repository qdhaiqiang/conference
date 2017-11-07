package com.centling.conference.dyn.form.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;


/**
 * ConfDynForm entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="conf_dyn_form"
    
)

public class ConfDynForm  implements java.io.Serializable {


    // Fields    
	 @JsonIgnore
     private String id;
     private String meetingId;
     private String userType;
     private String payload;


    // Constructors

    /** default constructor */
    public ConfDynForm() {
    }

    
    /** full constructor */
    public ConfDynForm(String meetingId, String userType, String payload) {
        this.meetingId = meetingId;
        this.userType = userType;
        this.payload = payload;
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
    
    @Column(name="payload", length=500)

    public String getPayload() {
        return this.payload;
    }
    
    public void setPayload(String payload) {
        this.payload = payload;
    }
   








}