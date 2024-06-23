package com.elife.mandra.DAO.Entities;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
public class Owner extends User implements UserDetails{

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getRole().getAuthorities();
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}

