package com.maahi.bioscoop.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Film {
    @Id @GeneratedValue
    private long id;
    private String name;

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}
