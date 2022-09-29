package com.coffekyun.cinema.service;

import com.coffekyun.cinema.dto.SeatRequest;
import com.coffekyun.cinema.dto.SeatResponse;
import com.coffekyun.cinema.entity.Seat;
import com.coffekyun.cinema.exception.DataAlreadyExistsException;
import com.coffekyun.cinema.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatServiceImpl implements SeatService{
    @Autowired
    private SeatRepository seatRepository;

    @Override
    public SeatResponse add(SeatRequest seatRequest) {
        Seat seat =  seatRequest.toSeat();
        if(seatRepository.existsById(seat.getId())) {
            throw new DataAlreadyExistsException( "There is already a seat with a code seat :" + seat.getSeatCode());
        } else {
            Seat seatResponse = seatRepository.save(seat);
            return SeatResponse.builder()
                    .id(seatResponse.getId())
                    .seatCode(seatResponse.getSeatCode())
                    .build();
        }
    }
}
