package com.elife.mandra.DAO.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
public class Guesthouse extends Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hasPool",nullable = false)
    private boolean hasPool ;
    
    @Column(name = "hasRestaurant",nullable = false)
    private boolean hasRestaurant ;

    @ManyToOne()
    @JoinColumn(name = "OwnerId")
    private Owner owner ;

}
