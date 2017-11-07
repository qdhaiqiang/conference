package com.centling.conference.material.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * ConfMaterialSchedule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "conf_material_schedule")
public class ConfMaterialSchedule implements java.io.Serializable {

	// Fields

	private String id;
	private String meetingId;
	private String schduelId;
	private String materialId;
	private String materialType;
	private String materialName;
	private Integer materialNum;
	private String materialLeader;
	private String leaderMobile;
	private String memo;

	// Constructors

	/** default constructor */
	public ConfMaterialSchedule() {
	}

	/** full constructor */
	public ConfMaterialSchedule(String meetingId, String schduelId,
			String materialId, String materialType, String materialName,
			Integer materialNum, String materialLeader, String leaderMobile,
			String memo) {
		this.meetingId = meetingId;
		this.schduelId = schduelId;
		this.materialId = materialId;
		this.materialType = materialType;
		this.materialName = materialName;
		this.materialNum = materialNum;
		this.materialLeader = materialLeader;
		this.leaderMobile = leaderMobile;
		this.memo = memo;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false, length = 36)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "meeting_id", length = 36)
	public String getMeetingId() {
		return this.meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}

	@Column(name = "schduel_id", length = 36)
	public String getSchduelId() {
		return this.schduelId;
	}

	public void setSchduelId(String schduelId) {
		this.schduelId = schduelId;
	}

	@Column(name = "material_id", length = 36)
	public String getMaterialId() {
		return this.materialId;
	}

	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}

	@Column(name = "material_type", length = 10)
	public String getMaterialType() {
		return this.materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}

	@Column(name = "material_name", length = 50)
	public String getMaterialName() {
		return this.materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	@Column(name = "material_num")
	public Integer getMaterialNum() {
		return this.materialNum;
	}

	public void setMaterialNum(Integer materialNum) {
		this.materialNum = materialNum;
	}

	@Column(name = "material_leader", length = 20)
	public String getMaterialLeader() {
		return this.materialLeader;
	}

	public void setMaterialLeader(String materialLeader) {
		this.materialLeader = materialLeader;
	}

	@Column(name = "leader_mobile", length = 20)
	public String getLeaderMobile() {
		return this.leaderMobile;
	}

	public void setLeaderMobile(String leaderMobile) {
		this.leaderMobile = leaderMobile;
	}

	@Column(name = "memo", length = 50)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}