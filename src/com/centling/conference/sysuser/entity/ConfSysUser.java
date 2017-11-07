package com.centling.conference.sysuser.entity;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * ConfSysUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "conf_sys_user")
public class ConfSysUser  implements java.io.Serializable {


    // Fields    

     private String id;
     private String loginName;
     private String loginPassword;
     private String mail;
     private String name;
     private String company;
     private String status;
     private String createTime;
     private String createRealTime;
     private String activeTime;
     private String activeRealTime;


    // Constructors

    /** default constructor */
    public ConfSysUser() {
    }

    
    /** full constructor */
    public ConfSysUser(String loginName, String loginPassword, String mail, String status, String createTime, String createRealTime, String activeTime, String activeRealTime) {
        this.loginName = loginName;
        this.loginPassword = loginPassword;
        this.mail = mail;
        this.status = status;
        this.createTime = createTime;
        this.createRealTime = createRealTime;
        this.activeTime = activeTime;
        this.activeRealTime = activeRealTime;
    }

   
    // Property accessors
    @GenericGenerator(name="generator", strategy="uuid.hex")@Id @GeneratedValue(generator="generator")
    
    @Column(name="id", unique=true, nullable=false, length=100)

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    @Column(name="login_name", length=100)

    public String getLoginName() {
        return this.loginName;
    }
    
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    
    @Column(name="login_password", length=100, updatable=false)

    public String getLoginPassword() {
        return this.loginPassword;
    }
    
    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }
    
    @Column(name="mail", length=100)

    public String getMail() {
        return this.mail;
    }
    
    public void setMail(String mail) {
        this.mail = mail;
    }
    
    @Column(name="status", length=50)

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Column(name="create_time", length=50)

    public String getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="create_real_time", length=50)

    public String getCreateRealTime() {
        return this.createRealTime;
    }
    
    public void setCreateRealTime(String createRealTime) {
        this.createRealTime = createRealTime;
    }
    
    @Column(name="active_time", length=50)

    public String getActiveTime() {
        return this.activeTime;
    }
    
    public void setActiveTime(String activeTime) {
        this.activeTime = activeTime;
    }
    
    @Column(name="active_real_time", length=50)

    public String getActiveRealTime() {
        return this.activeRealTime;
    }
    
    public void setActiveRealTime(String activeRealTime) {
        this.activeRealTime = activeRealTime;
    }
    
    @Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="company")
	public String getCompany() {
		return company;
	}


	public void setCompany(String company) {
		this.company = company;
	}
}