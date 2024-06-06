package com.elife.mandra.DAO.Entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@MappedSuperclass // Use @MappedSuperclass for a base class without its own table
public class Property {

      @Column(name = "name",length = 40,nullable = false )
        private String name ;

        @Column(name = "description",nullable = false )
        private String description ;
    
        @Column(name = "email",length = 40,nullable = false)
        private String email ;
    
        @Column(name = "address",length = 100,nullable = false)
        private String address ;
    
        @Column(name = "city",length = 30,nullable = false)
        private String city ;
    
        @Column(name = "phoneNumber",length = 20,nullable = false)
        private int phoneNumber ;
    
        @Column(name = "website",nullable = true)
        private String website ;

        @Column(name = "hasParking",nullable = false)
        private boolean aparking ;

        @Column(name = "hasWifi",nullable = false)
        private boolean awifi ;

        @Column(name = "allowsPets",nullable = false)
        private boolean accpteAnimaux ;

        @Column(name = "facebook",nullable = true)
        private String facebook ;

        @Column(name = "instagram",nullable = true)
        private String instagram ;

        @Column(name = "nbOfRaters",nullable = true)
        private long nbOfRaters ;

        @Column(name = "rateScore",nullable = true)
        private long rateScore ;

        @Column(name = "image",nullable = true)
        private String image ;

}