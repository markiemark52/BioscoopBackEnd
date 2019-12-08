package com.maahi.bioscoop.controllers;

import com.maahi.bioscoop.entities.Film;
import com.maahi.bioscoop.services.FilmService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/films")
@CrossOrigin(origins = "http://localhost:4200")
public class FilmController {
    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @PostMapping("/add")
    public Film addFilm(@RequestBody Film newFilm) {
        System.out.println("addfilm");

        return filmService.addFilm(newFilm);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Film>> getAllFilms() {
        List<Film> allFilms = filmService.getAllFilms();

        if (allFilms == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(allFilms);
        }
    }
}
