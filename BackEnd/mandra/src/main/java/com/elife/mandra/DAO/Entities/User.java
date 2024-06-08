package com.elife.mandra.DAO.Entities;

import java.util.Date;

import com.elife.mandra.DAO.Entities.OptionControl.RoleOption;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "First name is mandatory")
    @Size(max = 20, message = "First name must be less than 20 characters")
    private String firstname ;

    @Column(name = "lastname",length = 20,nullable = false)
    @NotBlank(message = "Last name is mandatory")
    @Size(max = 20, message = "Last name must be less than 20 characters")
    private String lastname ;

    @Column(name = "email",length = 30,nullable = false)
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Size(max = 30, message = "Email must be less than 30 characters")
    private String email ;

    @Column(name = "password",nullable = false)
    @NotBlank(message = "Password is mandatory")
    private String password ;

    @Column(name = "phoneNumber",length = 20,nullable = false)
    @NotNull(message = "Phone number is mandatory")
    private int phoneNumber ;

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
