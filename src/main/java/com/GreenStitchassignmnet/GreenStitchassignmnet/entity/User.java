package com.GreenStitchassignmnet.GreenStitchassignmnet.entity;

import jakarta.persistence.*;

import java.util.Collection;


@Entity
@Table(name = "\"User\"")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;


    private String roles;



    // Constructors
    public User() {
    }

    public User(String username, String password , String roles ) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    // Getter and Setter methods
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getroles() {

        return roles;
    }

    public void setroles(String roles) {
        this.roles = roles;
    }



}
