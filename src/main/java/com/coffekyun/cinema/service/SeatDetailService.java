package com.coffekyun.cinema.service;

import com.coffekyun.cinema.dto.OrderRequest;
import com.coffekyun.cinema.dto.SeatDetailRequest;
import com.coffekyun.cinema.dto.SeatDetailResponse;
import com.coffekyun.cinema.dto.SeatResponse;
import com.coffekyun.cinema.entity.OrderDetail;
import com.coffekyun.cinema.entity.SeatDetail;

import java.util.List;

public interface SeatDetailService {

    SeatDetailResponse addSeatAndStudio(SeatDetailRequest seatDetailRequest);

    List<SeatDetailResponse> getSeatIfAvailable(Boolean status);

    Boolean checkSeatAvailability(OrderRequest orderRequest);

    Boolean checkNumberOfSeat(List<OrderDetail> orderDetails, OrderRequest orderRequest);

}
