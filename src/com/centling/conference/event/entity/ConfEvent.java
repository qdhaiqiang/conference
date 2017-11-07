package com.centling.conference.event.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * ConfEvent entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "conf_event")
public class ConfEvent implements java.io.Serializable {

    // Fields

    private String id;
    private String meetingId;
    private String name;
    private String type;
    private String remark;
    private String updateDate;
    private String startTime;
    private String endTime;
    private String location;
    private String nameEn;
    private String locationEn;
    private String remarkEn;
    

    // Constructors

    /** default constructor */
    public ConfEvent() {
    }

    /** full constructor */
    public ConfEvent(String meetingId, String name, String type, String remark,
            String updateDate, String startTime, String endTime, String location,
            String nameEn, String locationEn, String remarkEn) {
        this.meetingId = meetingId;
        this.name = name;
        this.type = type;
        this.remark = remark;
        this.updateDate = updateDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.nameEn = nameEn;
        this.locationEn = locationEn;
        this.remarkEn = remarkEn;
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

    @Column(name = "name", length = 50)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "type", length = 1)
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "remark", length = 1024)
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "update_date", length = 20)
    public String getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    @Column(name="start_time", length = 20)
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Column(name="end_time", length = 20)
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Column(name="location", length = 50)
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Column(name="name_en", length = 50)
    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    @Column(name="location_en", length = 50)
    public String getLocationEn() {
        return locationEn;
    }

    public void setLocationEn(String locationEn) {
        this.locationEn = locationEn;
    }

    @Column(name="remark_en", length = 1024)
    public String getRemarkEn() {
        return remarkEn;
    }

    public void setRemarkEn(String remarkEn) {
        this.remarkEn = remarkEn;
    }

}