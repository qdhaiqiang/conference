package com.centling.conference.schedule.entity;


/**
 * 会议议程
 * @author lenovo
 *
 */
public class MeetingScheduleModel  implements java.io.Serializable {

	// Fields

		private String id;
		private String meetingId;
		private String title;
		private String startTime;
		private String endTime;
		private Integer currentAudience;
		private Integer maxAudience;
		private String intro;
		private String mediaUrl;
		private String restrictAudience;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getMeetingId() {
			return meetingId;
		}
		public void setMeetingId(String meetingId) {
			this.meetingId = meetingId;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getStartTime() {
			return startTime;
		}
		public void setStartTime(String startTime) {
			this.startTime = startTime;
		}
		public String getEndTime() {
			return endTime;
		}
		public void setEndTime(String endTime) {
			this.endTime = endTime;
		}
		public Integer getCurrentAudience() {
			return currentAudience;
		}
		public void setCurrentAudience(Integer currentAudience) {
			this.currentAudience = currentAudience;
		}
		public Integer getMaxAudience() {
			return maxAudience;
		}
		public void setMaxAudience(Integer maxAudience) {
			this.maxAudience = maxAudience;
		}
		public String getIntro() {
			return intro;
		}
		public void setIntro(String intro) {
			this.intro = intro;
		}
		public String getMediaUrl() {
			return mediaUrl;
		}
		public void setMediaUrl(String mediaUrl) {
			this.mediaUrl = mediaUrl;
		}
		public String getRestrictAudience() {
			return restrictAudience;
		}
		public void setRestrictAudience(String restrictAudience) {
			this.restrictAudience = restrictAudience;
		}

}