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
@Table(name = "Admin")
public class Admin {
        // @Column(name = "code",length = 255,nullable = true , unique = false, insertable=true,updatable = true  )

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(name = "firstname",length = 20,nullable = false )
    private String code ;

    @Column(name = "lastname",length = 20,nullable = false)
    private String name ;

    @Column(name = "email",length = 30,nullable = false)
    private String price ;

    @Column(name = "password",nullable = false)
    private String quantity ;

    @Column(name = "tel",length = 20,nullable = false)
    private int tel ;

    @Column(name = "role",length = 10,nullable = false)
    private String role ;

    @Column(name = "image",nullable = true)
    private String image ;

}

