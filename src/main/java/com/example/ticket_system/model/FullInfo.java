package com.example.ticket_system.model;

import javax.persistence.*;


public class FullInfo {
    private String name;

    public FullInfo(){}

    public FullInfo(String name, String surname, String telephone, String from_city, String from_region, String to_city, String to_region, String start_time, String end_time, String start_date, String end_date, String travel_time, Integer distance, Integer price) {
        this.name = name;
        this.surname = surname;
        this.telephone = telephone;
        this.from_city = from_city;
        this.from_region = from_region;
        this.to_city = to_city;
        this.to_region = to_region;
        this.start_time = start_time;
        this.end_time = end_time;
        this.start_date = start_date;
        this.end_date = end_date;
        this.travel_time = travel_time;
        this.distance = distance;
        this.price = price;
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

    private String surname;
    private String telephone;
    private String from_city;
    private String from_region;
    private String to_city;



    private String to_region;
    private String start_time;

    public String getFrom_city() {
        return from_city;
    }

    public void setFrom_city(String from_city) {
        this.from_city = from_city;
    }

    public String getFrom_region() {
        return from_region;
    }

    public void setFrom_region(String from_region) {
        this.from_region = from_region;
    }

    public String getTo_city() {
        return to_city;
    }

    public void setTo_city(String to_city) {
        this.to_city = to_city;
    }

    public String getTo_region() {
        return to_region;
    }

    public void setTo_region(String to_region) {
        this.to_region = to_region;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getTravel_time() {
        return travel_time;
    }

    public void setTravel_time(String travel_time) {
        this.travel_time = travel_time;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    private String end_time;
    private String start_date;
    private String end_date;
    private String travel_time;
    private Integer distance;
    private Integer price;




}