package com.coffekyun.cinema.service;

import com.coffekyun.cinema.dto.MovieUpdateRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MovieServiceImplTest {

    @Autowired
    private MovieService movieService;

    @Test
    void testUpdate() {
        MovieUpdateRequest movieUpdateRequest = new MovieUpdateRequest();
        movieUpdateRequest.setTitle("kaguya chan");
        movieService.update(movieUpdateRequest, "d052c81d-3d54-464f-a2e5-106ba1442508");
    }
}