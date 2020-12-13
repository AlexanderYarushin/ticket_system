package com.example.ticket_system.model;

import javax.persistence.*;


public class SupportDays {

    private String day;
    private String day_name;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDay_name() {
        return day_name;
    }

    public void setDay_name(String day_name) {
        this.day_name = day_name;
    }

    public SupportDays(String day, String day_name) {
        this.day = day;
        this.day_name = day_name;
    }
}
