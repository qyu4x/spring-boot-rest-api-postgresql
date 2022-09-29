package com.coffekyun.cinema.repository;

import com.coffekyun.cinema.entity.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    void testSaveFilm() throws ParseException {

        String pattern = "EEEE dd MMMM yyyy";

        Locale indonesia = new Locale("id", "ID"); // ke indonesia
        DateFormat dateFormat = new SimpleDateFormat(pattern, indonesia);

        Date dates = dateFormat.parse("Senin 05 September 2022");

        Movie kaguyasamaloveiswar = Movie.builder()
                .id(UUID.randomUUID().toString())
                .genre("Romance, Comedy, Seinen, Psychological")
                .duration(120)
                .title("Kaguya-sama wa Kokurasetai: Ultra Romantic")
                .description("The elite members of Shuchiin Academy's student council continue their competitive day-to-day antics. Council president Miyuki Shirogane clashes daily against vice-president Kaguya Shinomiya, each fighting tooth and nail to trick the other into confessing their romantic love. Kaguya struggles within the strict confines of her wealthy, uptight family, rebelling against her cold default demeanor as she warms to Shirogane and the rest of her friends.")
                .language("Japanese")
                .country("Japan")
                .showStatus(true)
                .startDate(dates)
                .endDate(dates)
                .build();

        Movie movie = movieRepository.save(kaguyasamaloveiswar);
        Assertions.assertNotNull(movie);

    }

    @Test
    void tesFindById() {
        Optional<Movie> movie =  movieRepository.findById("d052c81d-3d54-464f-a2e5-106ba1442508");
        Assertions.assertNotNull(movie);
    }

    @Test
    void testUpdate() {
        Optional<Movie> movie =  movieRepository.findById("d052c81d-3d54-464f-a2e5-106ba1442508");
        movieRepository.updateTitleById("test update", new Date(), movie.get().getId());
    }

    @Test
    void testDelete() {
//        movieRepository.deleteById("6c9b60c2-fa77-4962-81f3-a882f5d707d7");
    }

    @Test
    void testFindAllIfLive() {

        List<Movie> movieList =  movieRepository.findAllIfLive(true);
        Assertions.assertNotNull(movieList);

    }

    @Test
    void testFindScheduleFilmById() {

        Movie movie = movieRepository.findScheduleByIdMovie("0c631cd7-a8f4-4bfe-9c70-d2218245167e");
        Assertions.assertNotNull(movie);

        Assertions.assertSame(movie.getSchedules(), movie.getSchedules());

       Assertions.assertEquals(2, movie.getSchedules().size());
    }
}