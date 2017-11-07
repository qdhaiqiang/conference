package com.centling.conference.managermobile.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * ConfManagerMobile entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "conf_manager_mobile")
public class ConfManagerMobile implements java.io.Serializable {

    // Fields

    private String id;
    private String meetingId;
    private String managerName;
    private String managerMobile;
    private String managerPosition;
    private String remark;

    // Constructors

    /** default constructor */
    public ConfManagerMobile() {
    }

    /** full constructor */
    public ConfManagerMobile(String meetingId, String managerName,
            String managerMobile, String managerPosition, String remark) {
        this.meetingId = meetingId;
        this.managerName = managerName;
        this.managerMobile = managerMobile;
        this.managerPosition = managerPosition;
        this.remark = remark;
    }

    // Property accessors
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "id", unique = true, nullable = false, length = 40)
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "meeting_id", length = 40)
    public String getMeetingId() {
        return this.meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }

    @Column(name = "manager_name", length = 50)
    public String getManagerName() {
        return this.managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    @Column(name = "manager_mobile", length = 50)
    public String getManagerMobile() {
        return this.managerMobile;
    }

    public void setManagerMobile(String managerMobile) {
        this.managerMobile = managerMobile;
    }

    @Column(name = "manager_position", length = 50)
    public String getManagerPosition() {
        return this.managerPosition;
    }

    public void setManagerPosition(String managerPosition) {
        this.managerPosition = managerPosition;
    }

    @Column(name = "remark", length = 1024)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    

}