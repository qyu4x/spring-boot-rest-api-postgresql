package com.coffekyun.cinema.service;

import com.coffekyun.cinema.dto.SeatRequest;
import com.coffekyun.cinema.dto.SeatResponse;

public interface SeatService {

    SeatResponse add(SeatRequest seatRequest);

}
