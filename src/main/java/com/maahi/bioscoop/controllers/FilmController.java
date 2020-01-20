package com.maahi.bioscoop.controllers;

import com.maahi.bioscoop.HibernateUtil;
import com.maahi.bioscoop.entities.Film;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/films")
@CrossOrigin(origins = "http://localhost:4200")
public class FilmController {
    public FilmController() { }

    @PostMapping("/add")
    public ResponseEntity<Film> addFilm(@RequestBody Film film) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.save(film);
            transaction.commit();
            return ResponseEntity.ok(film);
        } catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Film>> getAllFilms() {
        List<Film> films;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            films = session.createQuery("from Film", Film.class).list();
            return ResponseEntity.ok(films);
        } catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Film>> getFilmsByUser(@RequestParam String email) {
        System.out.println("Test");
        List<Film> films;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            films = session.createQuery("from Film where user in(select id from User u where email = :email)", Film.class)
                    .setParameter("email", email)
                    .list();
            return ResponseEntity.ok(films);
        } catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/id")
    public ResponseEntity<List<Film>> getFilmById(@RequestParam int id) {
        List<Film> film = new ArrayList<>();

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            film = session.createQuery("from Film f where f.id = :id", Film.class)
                    .setParameter("id", id)
                    .list();

            return ResponseEntity.ok(film);
        } catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
}
