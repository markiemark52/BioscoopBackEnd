package com.maahi.bioscoop.controllers;

import com.maahi.bioscoop.HibernateUtil;
import com.maahi.bioscoop.datamodels.Showtime;
import com.maahi.bioscoop.entities.Auditorium;
import com.maahi.bioscoop.entities.AuditoriumFilm;
import com.maahi.bioscoop.entities.Film;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/auditoriums")
@CrossOrigin(origins = "http://localhost:4200")
public class AuditoriumController {
    public AuditoriumController() { }

//    @PostMapping("/add")
//    public ResponseEntity<Auditorium> addAuditorium(@RequestBody Auditorium auditorium) {
//        Transaction transaction = null;
//        try(Session session = HibernateUtil.getSessionFactory().openSession()){
//            transaction = session.beginTransaction();
//            session.save(auditorium);
//            transaction.commit();
//            return ResponseEntity.ok(auditorium);
//        } catch(Exception e){
//            if(transaction != null){
//                transaction.rollback();
//            }
//            e.printStackTrace();
//            return ResponseEntity.badRequest().build();
//        }
//    }

    @GetMapping("/all")
    public ResponseEntity<List<Auditorium>> getAllAuditoriums() {
        List<Auditorium> auditoriums;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            auditoriums = session.createQuery("from Auditorium ", Auditorium.class).list();
            return ResponseEntity.ok(auditoriums);
        } catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/manage")
    public ResponseEntity<Boolean> manage(@RequestParam String filmTitle,
                                          @RequestParam String auditoriumName,
                                          @RequestParam String datetime) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            Film film = session.createQuery("from Film f where f.title = :filmTitle", Film.class)
                    .setParameter("filmTitle", filmTitle).getSingleResult();
            Auditorium auditorium = session.createQuery("from Auditorium a where a.name = :auditoriumName", Auditorium.class)
                    .setParameter("auditoriumName", auditoriumName).getSingleResult();

            AuditoriumFilm auditoriumFilm = new AuditoriumFilm();
            auditoriumFilm.setFilm(film);
            auditoriumFilm.setAuditorium(auditorium);
            auditoriumFilm.setTime(datetime);

            session.save(auditoriumFilm);

            transaction.commit();
            return ResponseEntity.ok(true);
        } catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/showtimes")
    public ResponseEntity<List<Showtime>> getShowtimesByFilmId(@RequestParam int filmId) {
        List<AuditoriumFilm> auditoriumFilms;
        List<Showtime> showtimes = new ArrayList<>();
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            auditoriumFilms = session.createQuery("from AuditoriumFilm where film.id = :filmId", AuditoriumFilm.class)
                    .setParameter("filmId", filmId)
                    .list();

            for(AuditoriumFilm aF : auditoriumFilms) {
                Showtime showtime = new Showtime();
                showtime.auditorium = aF.getAuditorium().getName();
                showtime.datetime = aF.getTime();

                showtimes.add(showtime);
            }

            return ResponseEntity.ok(showtimes);
        } catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
}