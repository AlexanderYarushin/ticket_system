package com.example.ticket_system.model;

import javax.persistence.*;

@Entity
@Table(name = "purchasetrips")
public class PurchaseTrip {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer tripsid;
    private String name;
    private String surname;
    private String telephone;

    public PurchaseTrip(Integer tripsid, String name, String surname, String telephone) {
        this.tripsid = tripsid;
        this.name = name;
        this.surname = surname;
        this.telephone = telephone;
    }

    public PurchaseTrip() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTripsid() {
        return tripsid;
    }

    public void setTripsid(Integer tripsid) {
        this.tripsid = tripsid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

}
