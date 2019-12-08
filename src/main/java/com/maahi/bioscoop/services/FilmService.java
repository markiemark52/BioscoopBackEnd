package com.maahi.bioscoop.services;

import com.maahi.bioscoop.entities.Film;
import com.maahi.bioscoop.repositories.FilmRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService {
    private final FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public Film addFilm(Film newFilm) {
        return filmRepository.save(newFilm);
    }

    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }
}
