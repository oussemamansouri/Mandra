package com.elife.mandra.DAO.Entities;

import java.util.List;

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
public class Admin extends User {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id ;

    @OneToMany(mappedBy = "AdminId", cascade = CascadeType.ALL )
    private List<GastronomicSpecialties> GastronomicSpecialties ;

    @OneToMany(mappedBy = "AdminId", cascade = CascadeType.ALL )
    private List<SpecialtyWomen> SpecialtyWomens ;


}

