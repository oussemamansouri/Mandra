package com.elife.mandra.DAO.Entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "Hotel")
public class Hotel extends Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numberOfRooms", nullable = false)
    private int numberOfRooms;

    @Column(name = "hasGym", nullable = false)
    private boolean hasGym;

    @Column(name = "hasPool", nullable = false)
    private boolean hasPool;

    @Column(name = "hasRestaurant", nullable = false)
    private boolean hasRestaurant;

    @Column(name = "nbOfStars")
    private long nbOfStars;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OwnerId")
    @JsonIgnore
    private Owner owner;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    // @JsonIgnore
    private List<HotelImage> hotelImage;


    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations;
    public Hotel(String name, String description, String email, String address, String city,
                 String phoneNumber, String website, boolean hasParking, boolean hasWifi, boolean allowsPets,
                 String facebook, String instagram, int numberOfRooms, boolean hasGym, boolean hasPool,
                 boolean hasRestaurant, long nbOfStars) {
        this.setName(name);
        this.setDescription(description);
        this.setEmail(email);
        this.setAddress(address);
        this.setCity(city);
        this.setPhoneNumber(phoneNumber);
        this.setWebsite(website);
        this.setHasParking(hasParking);
        this.setHasWifi(hasWifi);
        this.setAllowsPets(allowsPets);
        this.setFacebook(facebook);
        this.setInstagram(instagram);
        this.numberOfRooms = numberOfRooms;
        this.hasGym = hasGym;
        this.hasPool = hasPool;
        this.hasRestaurant = hasRestaurant;
        this.nbOfStars = nbOfStars;
    }
}
