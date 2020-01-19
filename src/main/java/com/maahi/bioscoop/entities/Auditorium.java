package com.maahi.bioscoop.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Auditorium")
public class Auditorium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

//    @ManyToMany(cascade = {CascadeType.ALL})
//    @JoinTable(
//            name = "Auditorium_Film",
//            joinColumns = {@JoinColumn(name = "auditorium_id")},
//            inverseJoinColumns = {@JoinColumn(name = "film_id")}
//    )
//    Set<Film> films = new HashSet<>();

    @OneToMany(mappedBy = "auditorium")
    private Set<AuditoriumFilm> auditoriumFilms = new HashSet<>();

    public Auditorium() {
    }

    public Auditorium(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    private Set<AuditoriumFilm> getAuditoriumFilms() {
        return this.auditoriumFilms;
    }
    public void setAuditoriumFilms(Set<AuditoriumFilm> auditoriumFilms) {
        this.auditoriumFilms = auditoriumFilms;
    }
    public void addAuditoriumFilm(AuditoriumFilm auditoriumFilm) {
        this.auditoriumFilms.add(auditoriumFilm);
    }
}