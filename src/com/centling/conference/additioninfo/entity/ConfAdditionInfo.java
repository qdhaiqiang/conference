package com.centling.conference.additioninfo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


/**
 * ConfAdditionInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="conf_addition_info"
    
)

public class ConfAdditionInfo  implements java.io.Serializable {


    // Fields    

     private String id;
     private String meetingId;
     private String airTicketId;
     private String shipTicketId;
     private String trainTicketId;
     private String hotelId;
     private String arrivalType;
     private String arrivalTime;
     private String number;


    // Constructors

    /** default constructor */
    public ConfAdditionInfo() {
    }

    
    /** full constructor */
    public ConfAdditionInfo(String meetingId, String airTicketId, String shipTicketId, String trainTicketId, String hotelId, String arrivalType, String arrivalTime, String number) {
        this.meetingId = meetingId;
        this.airTicketId = airTicketId;
        this.shipTicketId = shipTicketId;
        this.trainTicketId = trainTicketId;
        this.hotelId = hotelId;
        this.arrivalType = arrivalType;
        this.arrivalTime = arrivalTime;
        this.number = number;
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
    
    @Column(name="meeting_id", length=40)

    public String getMeetingId() {
        return this.meetingId;
    }
    
    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }
    
    @Column(name="air_ticket_id", length=1)

    public String getAirTicketId() {
        return this.airTicketId;
    }
    
    public void setAirTicketId(String airTicketId) {
        this.airTicketId = airTicketId;
    }
    
    @Column(name="ship_ticket_id", length=1)

    public String getShipTicketId() {
        return this.shipTicketId;
    }
    
    public void setShipTicketId(String shipTicketId) {
        this.shipTicketId = shipTicketId;
    }
    
    @Column(name="train_ticket_id", length=1)

    public String getTrainTicketId() {
        return this.trainTicketId;
    }
    
    public void setTrainTicketId(String trainTicketId) {
        this.trainTicketId = trainTicketId;
    }
    
    @Column(name="hotel_id", length=1)

    public String getHotelId() {
        return this.hotelId;
    }
    
    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }
    
    @Column(name="arrival_type", length=1)

    public String getArrivalType() {
        return this.arrivalType;
    }
    
    public void setArrivalType(String arrivalType) {
        this.arrivalType = arrivalType;
    }
    
    @Column(name="arrival_time", length=18)

    public String getArrivalTime() {
        return this.arrivalTime;
    }
    
    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    
    @Column(name="number", length=50)

    public String getNumber() {
        return this.number;
    }
    
    public void setNumber(String number) {
        this.number = number;
    }
   








}