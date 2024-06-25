package com.elife.mandra.DAO.Entities;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.elife.mandra.DAO.Entities.OptionControl.RoleOption;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
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
@Table(name = "Admin")
public class Admin extends User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "Admin", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<GastronomicSpecialties> GastronomicSpecialties;

    @OneToMany(mappedBy = "Admin", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<SpecialtyWomen> SpecialtyWomens;

    public Admin(String firstname, String lastname, String email, String password,
            String phoneNumber, RoleOption role, String image) {
        this.setFirstname(firstname);
        this.setLastname(lastname);
        this.setEmail(email);
        this.setPassword(password);
        this.setPhoneNumber(phoneNumber);
        this.setRole(role);
        this.setImage(image);
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
