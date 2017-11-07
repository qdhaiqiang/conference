package com.centling.conference.meeting.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * ConfVMeetingIndex entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "conf_v_meeting_index")
public class ConfVMeetingIndex implements java.io.Serializable {

    // Fields

    private ConfVMeetingIndexId id;

    // Constructors

    /** default constructor */
    public ConfVMeetingIndex() {
    }

    /** full constructor */
    public ConfVMeetingIndex(ConfVMeetingIndexId id) {
        this.id = id;
    }

    // Property accessors
    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "id", column = @Column(name = "id", nullable = false, length = 40)),
            @AttributeOverride(name = "name", column = @Column(name = "NAME", length = 200)),
            @AttributeOverride(name = "startTime", column = @Column(name = "start_time", length = 20)),
            @AttributeOverride(name = "endTime", column = @Column(name = "end_time", length = 20)),
            @AttributeOverride(name = "city", column = @Column(name = "city", length = 3)),
            @AttributeOverride(name = "num", column = @Column(name = "num")) })
    public ConfVMeetingIndexId getId() {
        return this.id;
    }

    public void setId(ConfVMeetingIndexId id) {
        this.id = id;
    }

}