package com.elife.mandra.DAO.Entities;

import jakarta.persistence.Column;
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
@Table(name = "Hotel")

public class Hotel {

        // @Column(name = "code",length = 255,nullable = true , unique = false, insertable=true,updatable = true  )

        @Id
        @GeneratedValue( strategy = GenerationType.IDENTITY)
        private Long id ;
    
        @Column(name = "name",length = 20,nullable = false )
        private String name ;

        @Column(name = "description",length = 20,nullable = false )
        private String description ;
    
        @Column(name = "email",length = 20,nullable = false)
        private String email ;
    
        @Column(name = "adress",length = 30,nullable = false)
        private String adress ;
    
        @Column(name = "ville",nullable = false)
        private String ville ;
    
        @Column(name = "tel",length = 20,nullable = false)
        private int tel ;
    
        @Column(name = "website",length = 10,nullable = false)
        private String website ;
    
        @Column(name = "image",nullable = true)
        private String image ;

}
