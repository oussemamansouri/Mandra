package com.elife.mandra.DAO.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass // Use @MappedSuperclass for a base class without its own table
public class User {
        // @Column(name = "code",length = 255,nullable = true , unique = false, insertable=true,updatable = true  )

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
