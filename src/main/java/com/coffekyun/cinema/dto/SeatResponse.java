package com.coffekyun.cinema.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class SeatResponse {

    private String id;

    private String seatCode;

}
