package com.elife.mandra.DAO.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private String code ;

    @Column(name = "lastname",length = 20,nullable = false)
    private String name ;

    @Column(name = "email",length = 30,nullable = false)
    private String price ;

    @Column(name = "phoneNumber",length = 20,nullable = false)
    private int phoneNumber ;

    @Column(name = "subject",length = 30,nullable = false )
    private String subject ;

    @Column(name = "description",nullable = false)
    private String description ;


}
