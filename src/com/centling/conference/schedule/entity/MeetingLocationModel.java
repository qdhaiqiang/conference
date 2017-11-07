package com.centling.conference.schedule.entity;

import java.util.List;

/**
 * 会议地点
 * @author lenovo
 *
 */
public class MeetingLocationModel  implements java.io.Serializable {

	// Fields

		private String id;
		private String location;
		private List<MeetingScheduleModel> schedules;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
		}
		public List<MeetingScheduleModel> getSchedules() {
			return schedules;
		}
		public void setSchedules(List<MeetingScheduleModel> schedules) {
			this.schedules = schedules;
		}
		

}