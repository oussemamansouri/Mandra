package com.elife.mandra.DAO.Entities;

import java.util.Date;

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
    private String code ;

    @Column(name = "lastname",length = 20,nullable = false)
    private String name ;

    @Column(name = "email",length = 30,nullable = false)
    private String price ;

    @Column(name = "password",nullable = false)
    private String quantity ;

    @Column(name = "phoneNumber",length = 20,nullable = false)
    private int phoneNumber ;

    @Column(name = "role",length = 10,nullable = false)
    private String role ;

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
