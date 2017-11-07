package com.centling.conference.assignment.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ConfVAssignmentId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class ConfVAssignment implements java.io.Serializable {

    // Fields

    private String id;
    private String meetingId;
    private String guestType;
    private String guestId;
    private String updateDate;
    private String ename;
    private String cname;
    private String company;
    private String email;
    private String sex;
    private String mobile;
    private String userType;
    private String assginId;
    private String assginName;
    private String assignMobile;

    // Constructors

    /** default constructor */
    public ConfVAssignment() {
    }

    /** minimal constructor */
    public ConfVAssignment(String id) {
        this.id = id;
    }

    /** full constructor */
    public ConfVAssignment(String id, String meetingId, String assginId,
            String guestId, String updateDate, String ename, String cname,
            String company, String email, String sex, String nation,
            String mobile, String userType, String assginName,
            String assignMobile) {
        this.id = id;
        this.meetingId = meetingId;
        this.assginId = assginId;
        this.guestId = guestId;
        this.updateDate = updateDate;
        this.ename = ename;
        this.cname = cname;
        this.company = company;
        this.email = email;
        this.sex = sex;
        this.assginId = nation;
        this.mobile = mobile;
        this.userType = userType;
        this.assginName = assginName;
        this.assignMobile = assignMobile;
    }

    // Property accessors

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMeetingId() {
        return this.meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }

    public String getGuestId() {
        return this.guestId;
    }

    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }

    public String getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getEname() {
        return this.ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getCname() {
        return this.cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserType() {
        return this.userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getAssginName() {
        return this.assginName;
    }

    public void setAssginName(String assginName) {
        this.assginName = assginName;
    }

    public String getAssignMobile() {
        return this.assignMobile;
    }

    public void setAssignMobile(String assignMobile) {
        this.assignMobile = assignMobile;
    }

    public String getGuestType() {
        return guestType;
    }

    public void setGuestType(String guestType) {
        this.guestType = guestType;
    }

    public String getAssginId() {
        return assginId;
    }

    public void setAssginId(String assginId) {
        this.assginId = assginId;
    }

    public String getMobile() {
        return mobile;
    }

}