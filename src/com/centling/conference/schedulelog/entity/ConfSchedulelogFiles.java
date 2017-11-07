package com.centling.conference.schedulelog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * ConfSchedulelogFiles entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "conf_schedulelog_files" )
public class ConfSchedulelogFiles implements java.io.Serializable {

	// Fields

	private String id;
	private String logId;
	private String fileName;
	private String filePath;
	private String memo;

	// Constructors

	/** default constructor */
	public ConfSchedulelogFiles() {
	}

	/** full constructor */
	public ConfSchedulelogFiles(String logId, String fileName, String filePath,
			String memo) {
		this.logId = logId;
		this.fileName = fileName;
		this.filePath = filePath;
		this.memo = memo;
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

	@Column(name = "log_id", length = 40)
	public String getLogId() {
		return this.logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	@Column(name = "file_name", length = 500)
	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "file_path", length = 1000)
	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Column(name = "memo", length = 50)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}