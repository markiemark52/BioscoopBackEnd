package com.maahi.bioscoop;

import com.maahi.bioscoop.entities.Film;
import com.maahi.bioscoop.services.FilmService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BioscoopApplicationTests {
	@Autowired
	private FilmService filmService;

	@Test
	void contextLoads() {
	}

	@Test
	public void whenApplicationStarts_thenHibernateCreatesInitialRecords() {
		List<Film> films = filmService.getAllFilms();

		Assertions.assertEquals(films.size(), 3);
	}
}
