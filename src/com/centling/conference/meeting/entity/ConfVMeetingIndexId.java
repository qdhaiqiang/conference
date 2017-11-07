package com.centling.conference.meeting.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ConfVMeetingIndexId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class ConfVMeetingIndexId implements java.io.Serializable {

    // Fields

    private String id;
    private String name;
    private String startTime;
    private String endTime;
    private String city;
    private long num;

    // Constructors

    /** default constructor */
    public ConfVMeetingIndexId() {
    }

    /** minimal constructor */
    public ConfVMeetingIndexId(String id) {
        this.id = id;
    }

    /** full constructor */
    public ConfVMeetingIndexId(String id, String name, String startTime,
            String endTime, String city, long num) {
        this.id = id;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.city = city;
        this.num = num;
    }

    // Property accessors

    @Column(name = "id", nullable = false, length = 40)
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "NAME", length = 200)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "start_time", length = 20)
    public String getStartTime() {
        return this.startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Column(name = "end_time", length = 20)
    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Column(name = "city", length = 3)
    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "num")
    public long getNum() {
        return this.num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    

    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConfVMeetingIndexId other = (ConfVMeetingIndexId) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (num != other.num)
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (int) (num ^ (num >>> 32));
		result = prime * result
				+ ((startTime == null) ? 0 : startTime.hashCode());
		return result;
	}

}