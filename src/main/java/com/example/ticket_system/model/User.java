package com.example.ticket_system.model;

import javax.persistence.*;


@Entity
@Table(name = "users")
public class User {
    public User() {

    }

    public Integer getId() {
        return id;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fullname;
    private String email;

    public User(String fullname, String email, String password, String sessionid, Boolean entered) {
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.sessionid = sessionid;
        this.entered = entered;
    }

    private String password;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public Boolean getEntered() {
        return entered;
    }

    public void setEntered(Boolean entered) {
        this.entered = entered;
    }

    private String sessionid;
    private Boolean entered;




}