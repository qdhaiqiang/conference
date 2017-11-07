package com.centling.conference.schedule.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * ConfScheduleUser entity provides the base persistence definition of
 * the ConfScheduleUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="conf_schedule_user"
    
)
public class ConfScheduleUser implements java.io.Serializable {

	// Fields

		private String id;
		private String userId;
		private String scheduleId;
		private String meetingId;

		// Constructors

		/** default constructor */
		public ConfScheduleUser() {
		}

		/** full constructor */
		public ConfScheduleUser(String userId, String scheduleId,
				String meetingId) {
			this.userId = userId;
			this.scheduleId = scheduleId;
			this.meetingId = meetingId;
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

		@Column(name = "user_id", length = 40)
		public String getUserId() {
			return this.userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		@Column(name = "schedule_id", length = 50)
		public String getScheduleId() {
			return this.scheduleId;
		}

		public void setScheduleId(String scheduleId) {
			this.scheduleId = scheduleId;
		}

		@Column(name = "meeting_id", length = 50)
		public String getMeetingId() {
			return this.meetingId;
		}

		public void setMeetingId(String meetingId) {
			this.meetingId = meetingId;
		}


}