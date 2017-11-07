package com.centling.conference.assignment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


/**
 * ConfAssignment entity. @author Marvin
 */
@Entity
@Table(name="conf_assignment")
public class ConfAssignment  implements java.io.Serializable {


    // Fields

     private String id;
     private String meetingId;
     private String userId;
     private String guestId;
     private String updateDate;

    // Constructors

    /** default constructor */
    public ConfAssignment() {
    }


    /** full constructor */
    public ConfAssignment(String meetingId, String userId, String guestId, String updateDate) {
        this.meetingId = meetingId;
        this.userId = userId;
        this.guestId = guestId;
        this.updateDate = updateDate;
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

    @Column(name="user_id", length=40)

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name="guest_id", length=40)

    public String getGuestId() {
        return this.guestId;
    }

    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }

    @Column(name="update_date", length=20)

    public String getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    @Column(name="meeting_id", length=40)

    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }

}