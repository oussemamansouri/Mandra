package com.elife.mandra.DAO.Entities;

import java.util.Date;

import com.elife.mandra.DAO.Entities.OptionControl.RoleOption;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
    private String firstname ;

    @Column(name = "lastname",length = 20,nullable = false)
    private String lastname ;

    @Column(name = "email",length = 30,nullable = false)
    private String email ;

    @JsonIgnore // Ignore password during serialization
    @Column(name = "password",nullable = false)
    private String password ;

    @Column(name = "phoneNumber",length = 20,nullable = false)
    private String phoneNumber ;

    @Column(name = "role",length = 10,nullable = false)
    private RoleOption role ;

    @Column(name = "image",nullable = true)
    private String image ;


    @Column(name = "createDate",nullable = false,updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate ;

    @Column(name = "updateDate",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate ;

    @PrePersist
    protected void onCreate() {
        createDate = new Date();
        updateDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updateDate = new Date();
    }

}
