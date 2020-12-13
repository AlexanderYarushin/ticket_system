package com.example.ticket_system.model;

import javax.persistence.*;


@Entity
@Table(name = "trips")
public class Trip {
    public Integer getId() {
        return id;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Integer getTopoint() {
        return topoint;
    }

    public void setTopoint(Integer topoint) {
        this.topoint = topoint;
    }

    public Integer getFrompoint() {
        return frompoint;
    }

    public void setFrompoint(Integer frompoint) {
        this.frompoint = frompoint;
    }

    private Integer topoint;
    private Integer price;
    private Integer distance;
    private String start_time;
    private String end_time;



    private String startdate;
    private String end_date;
    private String travel_time;
    private Integer frompoint;

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }


    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
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

    public String getEnd_dateOrig() {

            return end_date;

    }

    public String getEnd_date() {
        if(startdate.equals(end_date)){
            return "";
        }else{
            return end_date;
        }
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getTravel_time() {
        int val = Integer.valueOf(travel_time);
        int hours = val / 60; //since both are ints, you get an int
        int minutes = val % 60;

        return Integer.toString(hours) + " ч., "+Integer.toString(minutes)+" мин.";
    }

    public void setTravel_time(String travel_time) {
        this.travel_time = travel_time;
    }

    public Trip(Integer from_point, Integer to_point, Integer price, Integer distance, String start_time, String end_time, String startdate, String end_date, String travel_time) {
        this.frompoint = from_point;
        this.topoint = to_point;
        this.price = price;
        this.distance = distance;
        this.start_time = start_time;
        this.end_time = end_time;
        this.startdate = startdate;
        this.end_date = end_date;
        this.travel_time = travel_time;
    }


    public Trip() {
    }


}