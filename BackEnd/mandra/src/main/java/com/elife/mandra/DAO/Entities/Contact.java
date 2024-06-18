package com.elife.mandra.DAO.Entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
public class Contact { 
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(name = "firstname",length = 20,nullable = false )
    private String firstname ;

    @Column(name = "lastname",length = 20,nullable = false)
    private String lastname ;

    @Column(name = "email",length = 30,nullable = false)
    private String email ;

    @Column(name = "phoneNumber",length = 20,nullable = false)
    private String phoneNumber ;

    @Column(name = "subject",length = 30,nullable = false )
    private String subject ;

    @Column(name = "description",nullable = false)
    private String description ;

    @Column(name = "createDate",nullable = false,updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate ;

    @PrePersist
    protected void onCreate() {
        createDate = new Date();
    }


    public Contact(String firstname, String lastname, String email, String phoneNumber, String subject, String description){
        this.firstname = firstname;
        this.lastname = lastname;
        this.email =  email;
        this.phoneNumber = phoneNumber;
        this.subject = subject;
        this.description = description;
    }

 


}
