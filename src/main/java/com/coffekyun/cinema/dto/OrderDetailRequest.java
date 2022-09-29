package com.coffekyun.cinema.dto;

import com.coffekyun.cinema.entity.Seat;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderDetailRequest {

    @NotEmpty(message = "The studioId is required.")
    private String studioId;

    @NotEmpty(message = "The scheduleId is required.")
    private String scheduleId;

    @NotEmpty(message = "The quantity is required.")
    private Integer quantity;

    private List<OrderSeatRequest> orderSeatRequests;

}
