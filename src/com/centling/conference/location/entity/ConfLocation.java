package com.centling.conference.location.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


/**
 * ConfLocation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="conf_location"
    
)

public class ConfLocation  implements java.io.Serializable {


    // Fields    

     private String id;
     private String location;
     private String scheduleId;


    // Constructors

    /** default constructor */
    public ConfLocation() {
    }

    
    /** full constructor */
    public ConfLocation(String location, String scheduleId) {
        this.location = location;
        this.scheduleId = scheduleId;
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
    
    @Column(name="location", length=100)

    public String getLocation() {
        return this.location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    @Column(name="schedule_id", length=40)

    public String getScheduleId() {
        return this.scheduleId;
    }
    
    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }
   








}