package com.centling.conference.serviceprovider.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


/**
 * ConfServiceProvider entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="conf_service_provider")
public class ConfServiceProvider  implements java.io.Serializable {


    // Fields    

     private String id;
     private String meetingId;
     private String providerName;
     private String service;
     private String manager;
     private String managerContact;
     private String remarks;


    // Constructors

    /** default constructor */
    public ConfServiceProvider() {
    }

    
    /** full constructor */
    public ConfServiceProvider(String meetingId, String providerName, String service, String manager, String managerContact, String remarks) {
        this.meetingId = meetingId;
        this.providerName = providerName;
        this.service = service;
        this.manager = manager;
        this.managerContact = managerContact;
        this.remarks = remarks;
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
    
    @Column(name="provider_name", length=50)

    public String getProviderName() {
        return this.providerName;
    }
    
    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }
    
    @Column(name="service", length=200)

    public String getService() {
        return this.service;
    }
    
    public void setService(String service) {
        this.service = service;
    }
    
    @Column(name="manager", length=20)

    public String getManager() {
        return this.manager;
    }
    
    public void setManager(String manager) {
        this.manager = manager;
    }
    
    @Column(name="manager_contact", length=20)

    public String getManagerContact() {
        return this.managerContact;
    }
    
    public void setManagerContact(String managerContact) {
        this.managerContact = managerContact;
    }
    
    @Column(name="remarks", length=200)

    public String getRemarks() {
        return this.remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
   








}