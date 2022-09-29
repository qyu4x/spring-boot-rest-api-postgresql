package com.coffekyun.cinema.controller;

import com.coffekyun.cinema.dto.*;
import com.coffekyun.cinema.exception.DataNotFoundException;
import com.coffekyun.cinema.exception.GlobalExceptionHandler;
import com.coffekyun.cinema.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/movie/")
public class MovieController {
    private MovieService movieService;
    private MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public ResponseEntity<?> postRequestAddNewMovie(@Valid @RequestBody MovieRequest movieRequest) {
        log.info("#calling controller postRequestAddNewMovie");
        final MovieResponse movieResponse =  movieService.add(movieRequest);
        return GlobalResponseHandler.generateResponse(
                "successfully added movie with id "  + movieResponse.getId(),HttpStatus.OK, movieResponse
        );
    }

    @PutMapping(value = "/update/{id}")
    @ResponseBody
    public ResponseEntity<?> postRequestChangeMovie(@Valid @RequestBody MovieUpdateRequest movieUpdateRequest, @PathVariable("id") String id) {
        log.info("#calling controller postRequestChangeMovie");
        try {
            final MovieUpdateResponse movieUpdateResponse =  movieService.update(movieUpdateRequest, id);
            return GlobalResponseHandler
                    .generateResponse("successfully update movie with id "  + id ,HttpStatus.OK, movieUpdateResponse);
        }catch (DataNotFoundException exception) {
            return GlobalExceptionHandler.dataNotFoundHandler(exception.getMessage());
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseBody
    public ResponseEntity<?> requestDeleteMovie(@PathVariable("id") String id) {
        log.info("#calling controller requestDeleteMovie");
        try {
            movieService.delete(id);
            return GlobalResponseHandler
                    .generateResponse("successfully delete data with id "  + id ,HttpStatus.OK, null);
        }catch (DataNotFoundException exception) {
            return GlobalExceptionHandler.dataNotFoundHandler(exception.getMessage());
        }
    }

    @GetMapping(value = "/show")
    @ResponseBody
    public ResponseEntity<?> requestShowMovieSchedule(@RequestParam Boolean status) {
        log.info("#calling controller requestShowMovieSchedule");
        List<MovieIsShowingResponse> onShowResponse = movieService.isShowing(status);
        return GlobalResponseHandler
                .generateResponse("success taking all the films that are currently showing", HttpStatus.OK, onShowResponse);
    }

    @GetMapping(value = "/schedule/{id}")
    @ResponseBody
    public ResponseEntity<?> requestShowMovieScheduleBasedOnMovie(@PathVariable String id) {
        log.info("#calling controller requestShowMovieScheduleBasedOnMovie");
        try {
            MovieScheduleResponse movieScheduleResponse = movieService.showSchedule(id);
            return GlobalResponseHandler
                    .generateResponse("successfully get movie schedule with id "  + id ,HttpStatus.OK, movieScheduleResponse);
        }catch (DataNotFoundException exception) {
            return GlobalExceptionHandler.dataNotFoundHandler(exception.getMessage());
        }
    }

}
