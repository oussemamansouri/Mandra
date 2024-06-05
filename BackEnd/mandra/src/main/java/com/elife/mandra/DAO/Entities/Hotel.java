package com.elife.mandra.DAO.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "Hotel")

public class Hotel {

        // @Column(name = "code",length = 255,nullable = true , unique = false, insertable=true,updatable = true  )

        @Id
        @GeneratedValue( strategy = GenerationType.IDENTITY)
        private Long id ;
    
        @Column(name = "name",length = 30,nullable = false )
        private String name ;

        @Column(name = "description",nullable = false )
        private String description ;
    
        @Column(name = "email",length = 30,nullable = false)
        private String email ;
    
        @Column(name = "address",length = 30,nullable = false)
        private String address ;
    
        @Column(name = "city",nullable = false)
        private String city ;
    
        @Column(name = "phoneNumber",length = 20,nullable = false)
        private int phoneNumber ;
    
        @Column(name = "website",length = 10,nullable = false)
        private String website ;

        @Column(name = "numberOfRooms",length = 10,nullable = false)
        private String numberOfRooms ;

        @Column(name = "hasParking",length = 10,nullable = false)
        private String aparking ;

        @Column(name = "hasWifi",length = 10,nullable = false)
        private String awifi ;

        @Column(name = "hasGym",length = 10,nullable = false)
        private String aSalleDeSport ;

        @Column(name = "hasPool",length = 10,nullable = false)
        private String aPiscine ;

        @Column(name = "hasRestaurant",length = 10,nullable = false)
        private String aRestaurant ;

        @Column(name = "allowsPets",length = 10,nullable = false)
        private String accpteAnimaux ;

        @Column(name = "facebook",length = 10,nullable = false)
        private String facebook ;

        @Column(name = "instagram",length = 10,nullable = false)
        private String instagram ;

        @Column(name = "nbOfRaters",length = 10,nullable = false)
        private String nbOfRaters ;

        @Column(name = "rateScore",length = 10,nullable = false)
        private String rateScore ;
        
        @Column( name = "nbOfStars")
        private long nbOfStars;

        @Column(name = "image",nullable = true)
        private String image ;

}
