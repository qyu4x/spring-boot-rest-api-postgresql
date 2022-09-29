package com.coffekyun.cinema.controller;

import com.coffekyun.cinema.dto.GlobalResponseHandler;
import com.coffekyun.cinema.dto.SeatRequest;
import com.coffekyun.cinema.dto.SeatResponse;
import com.coffekyun.cinema.exception.DataAlreadyExistsException;
import com.coffekyun.cinema.exception.GlobalExceptionHandler;
import com.coffekyun.cinema.service.SeatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/seat/")
public class SeatController {
    private SeatService seatService;
    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public ResponseEntity<?> postRequestAddNewSeat(@Valid @RequestBody SeatRequest seatRequest) {
        log.info("#calling controller postRequestAddNewSeat");
        try {
            SeatResponse seatResponse =  seatService.add(seatRequest);
            return GlobalResponseHandler
                    .generateResponse("successfully add seat with id "  + seatResponse.getId() , HttpStatus.OK, seatResponse);
        }catch (DataAlreadyExistsException exception) {
            return GlobalExceptionHandler.dataAlreadyExistsHandler(exception.getMessage());
        }
    }
}
