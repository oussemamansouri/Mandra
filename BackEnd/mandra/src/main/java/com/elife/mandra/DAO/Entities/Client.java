package com.elife.mandra.DAO.Entities;


import com.elife.mandra.DAO.Entities.OptionControl.AccountStateOption;
import com.elife.mandra.DAO.Entities.OptionControl.RoleOption;

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
@Table(name = "Client")
public class Client extends User {


    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id ;


    public Client(String firstname, String lastname, String email, String password,
    String phoneNumber, RoleOption role, String image, AccountStateOption accountState) {
        this.setFirstname(firstname);
        this.setLastname(lastname);
        this.setEmail(email);
        this.setPassword(password);
        this.setPhoneNumber(phoneNumber);
        this.setRole(role);
        this.setImage(image);
        this.setAccountState(accountState);
    }


}
