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
    @NotBlank(message = "Le prénom est obligatoire")
    @Size(max = 20, message = "Le prénom doit comporter moins de 20 caractères")
    private String firstname ;

    @Column(name = "lastname",length = 20,nullable = false)
    @NotBlank(message = "Le nom de famille est obligatoire")
    @Size(max = 20, message = "Le nom de famille doit comporter moins de 20 caractères.")
    private String lastname ;

    @Column(name = "email",length = 30,nullable = false)
    @NotBlank(message = "email est obligatoire")
    @Email(message = "L'email doit être valide")
    @Size(max = 30, message = "L'email doit comporter moins de 30 caractères.")
    private String email ;

    @Column(name = "password",nullable = false)
    @NotBlank(message = "Mot de passe obligatoire")
    private String password ;

    @Column(name = "phoneNumber",length = 20,nullable = false)
    @NotNull(message = "Le numéro de téléphone est obligatoire")
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
