package com.centling.conference.sysrole.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


/**
 * ConfSysRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="conf_sys_role")
public class ConfSysRole  implements java.io.Serializable {


    // Fields    

     private String id;
     private String name;
     private String code;


    // Constructors

    /** default constructor */
    public ConfSysRole() {
    }

    
    /** full constructor */
    public ConfSysRole(String name, String code) {
        this.name = name;
        this.code = code;
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
    
    @Column(name="name", length=100)

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="code", length=100)

    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
   








}