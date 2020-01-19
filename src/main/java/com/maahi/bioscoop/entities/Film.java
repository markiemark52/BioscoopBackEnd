package com.maahi.bioscoop.entities;

import javax.persistence.*;

@Entity
@Table(name = "film")
public class Film {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Film() { }

    public Film(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
