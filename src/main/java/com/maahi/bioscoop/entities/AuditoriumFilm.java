package com.maahi.bioscoop.entities;

import javax.persistence.*;

@Entity
@Table(name = "Auditoriums_Films")
public class AuditoriumFilm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auditorium_film_id")
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "auditorium_id")
    private Auditorium auditorium;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "film_id")
    private Film film;

    @Column(name = "time")
    private String time;

    public AuditoriumFilm() { }

    public Auditorium getAuditorium() {
        return this.auditorium;
    }
    public void setAuditorium(Auditorium auditorium) {
        this.auditorium = auditorium;
    }

    public Film getFilm() {
        return this.film;
    }
    public void setFilm(Film film) {
        this.film = film;
    }

    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
}