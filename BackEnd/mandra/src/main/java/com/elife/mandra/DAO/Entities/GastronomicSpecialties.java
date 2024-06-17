package com.elife.mandra.DAO.Entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

@Entity
public class GastronomicSpecialties {

        @Id
        @GeneratedValue( strategy = GenerationType.IDENTITY)
        private Long id ;

        @Column(name = "name",length = 40,nullable = false )
        private String name ;

        @Column(name = "description",nullable = false )
        private String description ;
    
        @Column(name = "address",length = 100,nullable = false)
        private String address ;
    
        @Column(name = "city",length = 30,nullable = false)
        private String city ;

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

        @ManyToOne()
        @JoinColumn(name = "AdminId")
        @JsonIgnore
        private Admin Admin ;


        public GastronomicSpecialties(String name, String description, String address, String city, String image ){
            this.name = name;
            this.description = description;
            this.address = address;
            this.city = city;
            this.image = image;

        }

}
