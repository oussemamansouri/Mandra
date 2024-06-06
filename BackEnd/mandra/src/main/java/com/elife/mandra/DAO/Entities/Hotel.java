package com.elife.mandra.DAO.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

public class Hotel extends Property {

        @Id
        @GeneratedValue( strategy = GenerationType.IDENTITY)
        private Long id ;

        @Column(name = "numberOfRooms",nullable = false)
        private int numberOfRooms ;

        @Column(name = "hasGym",nullable = false)
        private boolean aSalleDeSport ;

        @Column(name = "hasPool",nullable = false)
        private boolean aPiscine ;

        @Column(name = "hasRestaurant",nullable = false)
        private boolean aRestaurant ;

        @Column( name = "nbOfStars" )
        private long nbOfStars;

        @ManyToOne()
        @JoinColumn(name = "OwnerId")
        private Owner owner ;
    

}
