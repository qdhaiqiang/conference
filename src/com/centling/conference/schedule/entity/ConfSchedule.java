package com.centling.conference.schedule.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * ConfSchedule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "conf_schedule")
public class ConfSchedule implements java.io.Serializable {

	// Fields

	private String id;
	private String meetingId;
	private String title;
	private String titleEn;
	private String startTime;
	private String endTime;
	private String intro;
	private String introEn;
	private String mediaUrl;
	private String restrictAudience;
	private Integer currentAudience;
	private Integer maxAudience;
	private String location;
	private String liveSize;
	private String locationUrl;
	private String fieldPatternUrl;
	private String liveProcedure;
	private String audioVideo;
	private String speechSeatId;
	private String speechSeatName;
	private String vipSeatId;
	private String vipSeatName;
	private String vipListId;
	private String vipListName;
	private String participantListId;
	private String participantListName;
	private String emceeId;
	private String emceeName;
	private String personInChargeId;
	private String personInChargeName;
	private String personForMaterialId;
	private String personForMaterialName;
	private String personForDispatchId;
	private String personForDispatchName;
	private String personForFlowId;
	private String personForFlowName;
	private String scheduleType;

	// Constructors

	/** default constructor */
	public ConfSchedule() {
	}

	/** full constructor */
	public ConfSchedule(String meetingId, String title, String titleEn,
			String startTime, String endTime, String intro, String introEn,
			String mediaUrl, String restrictAudience, Integer currentAudience,
			Integer maxAudience, String location, String liveSize,
			String locationUrl, String fieldPatternUrl, String liveProcedure,
			String audioVideo, String speechSeatId, String speechSeatName,
			String vipSeatId, String vipSeatName, String vipListId,
			String vipListName, String participantListId,
			String participantListName, String emceeId, String emceeName,
			String personInChargeId, String personInChargeName,
			String personForMaterialId, String personForMaterialName,
			String personForDispatchId, String personForDispatchName,
			String personForFlowId, String personForFlowName) {
		this.meetingId = meetingId;
		this.title = title;
		this.titleEn = titleEn;
		this.startTime = startTime;
		this.endTime = endTime;
		this.intro = intro;
		this.introEn = introEn;
		this.mediaUrl = mediaUrl;
		this.restrictAudience = restrictAudience;
		this.currentAudience = currentAudience;
		this.maxAudience = maxAudience;
		this.location = location;
		this.liveSize = liveSize;
		this.locationUrl = locationUrl;
		this.fieldPatternUrl = fieldPatternUrl;
		this.liveProcedure = liveProcedure;
		this.audioVideo = audioVideo;
		this.speechSeatId = speechSeatId;
		this.speechSeatName = speechSeatName;
		this.vipSeatId = vipSeatId;
		this.vipSeatName = vipSeatName;
		this.vipListId = vipListId;
		this.vipListName = vipListName;
		this.participantListId = participantListId;
		this.participantListName = participantListName;
		this.emceeId = emceeId;
		this.emceeName = emceeName;
		this.personInChargeId = personInChargeId;
		this.personInChargeName = personInChargeName;
		this.personForMaterialId = personForMaterialId;
		this.personForMaterialName = personForMaterialName;
		this.personForDispatchId = personForDispatchId;
		this.personForDispatchName = personForDispatchName;
		this.personForFlowId = personForFlowId;
		this.personForFlowName = personForFlowName;
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

	@Column(name = "title", length = 200)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "title_en", length = 200)
	public String getTitleEn() {
		return this.titleEn;
	}

	public void setTitleEn(String titleEn) {
		this.titleEn = titleEn;
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

	@Column(name = "intro", length = 500)
	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	@Column(name = "intro_en", length = 500)
	public String getIntroEn() {
		return this.introEn;
	}

	public void setIntroEn(String introEn) {
		this.introEn = introEn;
	}

	@Column(name = "media_url", length = 256)
	public String getMediaUrl() {
		return this.mediaUrl;
	}

	public void setMediaUrl(String mediaUrl) {
		this.mediaUrl = mediaUrl;
	}

	@Column(name = "restrict_audience", length = 100)
	public String getRestrictAudience() {
		return this.restrictAudience;
	}

	public void setRestrictAudience(String restrictAudience) {
		this.restrictAudience = restrictAudience;
	}

	@Column(name = "current_audience")
	public Integer getCurrentAudience() {
		return this.currentAudience;
	}

	public void setCurrentAudience(Integer currentAudience) {
		this.currentAudience = currentAudience;
	}

	@Column(name = "max_audience")
	public Integer getMaxAudience() {
		return this.maxAudience;
	}

	public void setMaxAudience(Integer maxAudience) {
		this.maxAudience = maxAudience;
	}

	@Column(name = "location", length = 500)
	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "live_size", length = 500)
	public String getLiveSize() {
		return this.liveSize;
	}

	public void setLiveSize(String liveSize) {
		this.liveSize = liveSize;
	}

	@Column(name = "location_url", length = 200)
	public String getLocationUrl() {
		return this.locationUrl;
	}

	public void setLocationUrl(String locationUrl) {
		this.locationUrl = locationUrl;
	}

	@Column(name = "field_pattern_url", length = 200)
	public String getFieldPatternUrl() {
		return this.fieldPatternUrl;
	}

	public void setFieldPatternUrl(String fieldPatternUrl) {
		this.fieldPatternUrl = fieldPatternUrl;
	}

	@Column(name = "live_procedure", length = 65535)
	public String getLiveProcedure() {
		return this.liveProcedure;
	}

	public void setLiveProcedure(String liveProcedure) {
		this.liveProcedure = liveProcedure;
	}

	@Column(name = "audio_video", length = 200)
	public String getAudioVideo() {
		return this.audioVideo;
	}

	public void setAudioVideo(String audioVideo) {
		this.audioVideo = audioVideo;
	}

	@Column(name = "speech_seat_id", length = 65535)
	public String getSpeechSeatId() {
		return this.speechSeatId;
	}

	public void setSpeechSeatId(String speechSeatId) {
		this.speechSeatId = speechSeatId;
	}

	@Column(name = "speech_seat_name", length = 65535)
	public String getSpeechSeatName() {
		return this.speechSeatName;
	}

	public void setSpeechSeatName(String speechSeatName) {
		this.speechSeatName = speechSeatName;
	}

	@Column(name = "vip_seat_id", length = 65535)
	public String getVipSeatId() {
		return this.vipSeatId;
	}

	public void setVipSeatId(String vipSeatId) {
		this.vipSeatId = vipSeatId;
	}

	@Column(name = "vip_seat_name", length = 65535)
	public String getVipSeatName() {
		return this.vipSeatName;
	}

	public void setVipSeatName(String vipSeatName) {
		this.vipSeatName = vipSeatName;
	}

	@Column(name = "vip_list_id", length = 65535)
	public String getVipListId() {
		return this.vipListId;
	}

	public void setVipListId(String vipListId) {
		this.vipListId = vipListId;
	}

	@Column(name = "vip_list_name", length = 65535)
	public String getVipListName() {
		return this.vipListName;
	}

	public void setVipListName(String vipListName) {
		this.vipListName = vipListName;
	}

	@Column(name = "participant_list_id", length = 65535)
	public String getParticipantListId() {
		return this.participantListId;
	}

	public void setParticipantListId(String participantListId) {
		this.participantListId = participantListId;
	}

	@Column(name = "participant_list_name", length = 65535)
	public String getParticipantListName() {
		return this.participantListName;
	}

	public void setParticipantListName(String participantListName) {
		this.participantListName = participantListName;
	}

	@Column(name = "emcee_id", length = 1000)
	public String getEmceeId() {
		return this.emceeId;
	}

	public void setEmceeId(String emceeId) {
		this.emceeId = emceeId;
	}

	@Column(name = "emcee_name", length = 1000)
	public String getEmceeName() {
		return this.emceeName;
	}

	public void setEmceeName(String emceeName) {
		this.emceeName = emceeName;
	}

	@Column(name = "person_in_charge_id", length = 1000)
	public String getPersonInChargeId() {
		return this.personInChargeId;
	}

	public void setPersonInChargeId(String personInChargeId) {
		this.personInChargeId = personInChargeId;
	}

	@Column(name = "person_in_charge_name", length = 1000)
	public String getPersonInChargeName() {
		return this.personInChargeName;
	}

	public void setPersonInChargeName(String personInChargeName) {
		this.personInChargeName = personInChargeName;
	}

	@Column(name = "person_for_material_id", length = 1000)
	public String getPersonForMaterialId() {
		return this.personForMaterialId;
	}

	public void setPersonForMaterialId(String personForMaterialId) {
		this.personForMaterialId = personForMaterialId;
	}

	@Column(name = "person_for_material_name", length = 1000)
	public String getPersonForMaterialName() {
		return this.personForMaterialName;
	}

	public void setPersonForMaterialName(String personForMaterialName) {
		this.personForMaterialName = personForMaterialName;
	}

	@Column(name = "person_for_dispatch_id", length = 1000)
	public String getPersonForDispatchId() {
		return this.personForDispatchId;
	}

	public void setPersonForDispatchId(String personForDispatchId) {
		this.personForDispatchId = personForDispatchId;
	}

	@Column(name = "person_for_dispatch_name", length = 1000)
	public String getPersonForDispatchName() {
		return this.personForDispatchName;
	}

	public void setPersonForDispatchName(String personForDispatchName) {
		this.personForDispatchName = personForDispatchName;
	}

	@Column(name = "person_for_flow_id", length = 1000)
	public String getPersonForFlowId() {
		return this.personForFlowId;
	}

	public void setPersonForFlowId(String personForFlowId) {
		this.personForFlowId = personForFlowId;
	}

	@Column(name = "person_for_flow_name", length = 1000)
	public String getPersonForFlowName() {
		return this.personForFlowName;
	}

	public void setPersonForFlowName(String personForFlowName) {
		this.personForFlowName = personForFlowName;
	}
	
	@Column(name = "schedule_type", length = 1)
	public String getScheduleType() {
		return scheduleType;
	}

	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}
}