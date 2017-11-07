package com.centling.conference.assignment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * ConfAssignmentOther entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "conf_assignment_other")
public class ConfAssignmentOther implements java.io.Serializable {

    // Fields

    private String id;
    private String meetingId;
    private String scheduleId;
    private String userId;
    private String userName;
    private String updateDate;

    // Constructors

    /** default constructor */
    public ConfAssignmentOther() {
    }

    /** full constructor */
    public ConfAssignmentOther(String meetingId, String scheduleId,
            String userId, String userName, String updateDate) {
        this.meetingId = meetingId;
        this.scheduleId = scheduleId;
        this.userId = userId;
        this.userName = userName;
        this.updateDate = updateDate;
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

    @Column(name = "schedule_id", length = 40)
    public String getScheduleId() {
        return this.scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    @Column(name = "user_id")
    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "update_date", length = 20)
    public String getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}