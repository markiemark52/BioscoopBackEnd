package com.maahi.bioscoop.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
    @Id @GeneratedValue
    private long id;
    private String email;

    public long getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
}
