package com.coffekyun.cinema.controller;

import com.coffekyun.cinema.dto.*;
import com.coffekyun.cinema.exception.DataAlreadyExistsException;
import com.coffekyun.cinema.exception.DataNotFoundException;
import com.coffekyun.cinema.exception.GlobalExceptionHandler;
import com.coffekyun.cinema.service.SeatDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/seat-details/")
public class SeatDetailController {
    private SeatDetailService seatDetailService;
    public SeatDetailController(SeatDetailService seatDetailService) {
        this.seatDetailService = seatDetailService;
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public ResponseEntity<?> postRequestAddNewSeatDetails(@Valid @RequestBody SeatDetailRequest seatDetailRequest) {
        log.info("#calling controller postRequestAddNewSeatDetails");
        try {
            SeatDetailResponse seatDetailResponse =  seatDetailService.addSeatAndStudio(seatDetailRequest);
            return GlobalResponseHandler
                    .generateResponse("successfully add seat and studio" , HttpStatus.OK, seatDetailResponse);
        }catch (DataAlreadyExistsException | DataNotFoundException exception) {
            return GlobalExceptionHandler.dataAlreadyExistsHandler(exception.getMessage());
        }
    }

    @GetMapping(value = "/available")
    @ResponseBody
    public ResponseEntity<?> requestShowMovieSchedule(@RequestParam Boolean status) {
        log.info("#calling controller requestShowMovieSchedule");
        List<SeatDetailResponse> seatDetailResponses = seatDetailService.getSeatIfAvailable(status);
        return GlobalResponseHandler
                .generateResponse("successfully to get all seats", HttpStatus.OK, seatDetailResponses);
    }

}
