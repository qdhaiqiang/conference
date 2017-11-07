package com.centling.conference.event.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.centling.conference.user.entity.ConfUser;

/**
 * ConfEventUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "conf_event_user")
public class ConfEventUser implements java.io.Serializable {

    // Fields

    private String id;
    private String eventId;
    private String userId;
    private String updateDate;

    // Constructors

    /** default constructor */
    public ConfEventUser() {
    }

    /** full constructor */
    public ConfEventUser(String eventId, String userId,
            String updateDate) {
        this.eventId = eventId;
        this.userId = userId;
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

    @Column(name = "event_id", length = 40)
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    @Column(name = "user_id", length = 40)
    public String getUserId() {
        return userId;
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

}