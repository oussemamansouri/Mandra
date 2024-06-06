package com.elife.mandra.DAO.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
public class SpecialtyWomen {

    
        @Id
        @GeneratedValue( strategy = GenerationType.IDENTITY)
        private Long id ;

        @Column(name = "Specialty",length = 40,nullable = false )
        private String Specialty ;

        @Column(name = "description",nullable = false )
        private String description ;
    
        @Column(name = "address",length = 100,nullable = false)
        private String address ;
    
        @Column(name = "city",length = 30,nullable = false)
        private String city ;

        @Column(name = "phoneNumber",length = 30,nullable = false)
        private String phoneNumber ;

        @Column(name = "image",nullable = true)
        private String image ;

}
