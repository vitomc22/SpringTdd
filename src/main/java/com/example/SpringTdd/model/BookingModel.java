package com.example.SpringTdd.model;


import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Entity
public class BookingModel {

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    @Column
    private String reserveName;
    @Column
    private LocalDate checkIn;
    @Column
    private LocalDate checkOut;
    @Column
    private String numberGuests;

    public BookingModel() {
    }

    public BookingModel(String reserveName, LocalDate checkIn, LocalDate checkOut, String numberGuests) {
        this.reserveName = reserveName;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.numberGuests = numberGuests;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReserveName() {
        return reserveName;
    }

    public void setReserveName(String reserveName) {
        this.reserveName = reserveName;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public String getNumberGuests() {
        return numberGuests;
    }

    public void setNumberGuests(String numberGuests) {
        this.numberGuests = numberGuests;
    }
}
