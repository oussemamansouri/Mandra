package com.elife.mandra.DAO.Entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "Restaurant")
public class Restaurant extends Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "withTerrace", nullable = false)
    private boolean withTerrace;

    @Column(name = "acceptsReservation", nullable = false)
    private boolean acceptsReservation;

    @ManyToOne()
    @JoinColumn(name = "OwnerId")
    @JsonIgnore
    private Owner owner;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RestaurantImage> restaurantImage;

    public Restaurant(String name, String description, String email, String address, String city,
            String phoneNumber, String website, boolean hasParking, boolean hasWifi, boolean allowsPets,
            String facebook, String instagram, boolean withTerrace, boolean acceptsReservation) {
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
        this.withTerrace = withTerrace;
        this.acceptsReservation = acceptsReservation;
    }
}
