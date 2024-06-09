package com.elife.mandra.DAO.Entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "Owner")
public class Owner extends User {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(name = "proof",nullable = false)
    private String proof;

    @Column(name = "cinImage",nullable = false)
    private String cinImage ;

    @Column(name = "nbOfHotels",nullable = false)
    private int nbOfHotels ;

    @Column(name = "nbOfRestaurant",nullable = false)
    private int nbOfRestaurant ;

    @Column(name = "nbOfGuesthouses",nullable = false)
    private int nbOfGuesthouses ;
    

    // The rolation between the tables
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL )
    private List<Hotel> Hotels ;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL )
    private List<Guesthouse> Guesthouses ;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL )
    private List<Restaurant> Restaurants ;


}

