package com.elife.mandra.DAO.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "Reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

  

    @Temporal(TemporalType.DATE)
    @Column(name = "check_in")
    private Date checkInDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "check_out")
    private Date checkOutDate;

    @Column(name = "adults")
    private int numOfAdults;

    @Column(name = "children")
    private int numOfChildren;

    @Column(name = "num_of_rooms")
    private int numOfRooms;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;



    public Reservation( Date checkInDate, Date checkOutDate, int numOfAdults, int numOfChildren, int numOfRooms) {
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numOfAdults = numOfAdults;
        this.numOfChildren = numOfChildren;
        this.numOfRooms = numOfRooms;
    }
}
