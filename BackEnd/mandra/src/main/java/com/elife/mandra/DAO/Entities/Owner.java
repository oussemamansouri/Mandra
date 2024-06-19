package com.elife.mandra.DAO.Entities;

import java.util.List;

import com.elife.mandra.DAO.Entities.OptionControl.AccountStateOption;
import com.elife.mandra.DAO.Entities.OptionControl.RoleOption;
// import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @Column(name = "proof",nullable = true)
    private String proof;

    @Column(name = "cinImage",nullable = true)
    private String cinImage ;

    @Column(name = "nbOfHotels",nullable = false)
    private int nbOfHotels ;

    @Column(name = "nbOfRestaurant",nullable = false)
    private int nbOfRestaurant ;

    @Column(name = "nbOfGuesthouses",nullable = false)
    private int nbOfGuesthouses ;

    @Column(name = "accountState",nullable = false)
    private AccountStateOption accountState ;
    

    // The rolation between the tables
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true )
    // @JsonIgnore
    private List<Hotel> Hotels ;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true )
    // @JsonIgnore
    private List<Guesthouse> Guesthouses ;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true )
    // @JsonIgnore
    private List<Restaurant> Restaurants ;

    public Owner(String firstname, String lastname, String email, String password,
    String phoneNumber,String proof, String cinImage, int nbOfHotels, int nbOfRestaurant, 
    int nbOfGuesthouses, RoleOption role, String image, AccountStateOption accountState) {
        this.setFirstname(firstname);
        this.setLastname(lastname);
        this.setEmail(email);
        this.setPassword(password);
        this.setPhoneNumber(phoneNumber);
        this.proof = proof;
        this.cinImage = cinImage;
        this.nbOfGuesthouses = nbOfGuesthouses;
        this.nbOfHotels = nbOfHotels;
        this.nbOfRestaurant = nbOfRestaurant;
        this.setRole(role);
        this.setImage(image);
        this.accountState = accountState;
    }


}

