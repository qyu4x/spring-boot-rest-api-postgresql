package com.coffekyun.cinema.service;

import com.coffekyun.cinema.dto.OrderDetailRequest;
import com.coffekyun.cinema.dto.OrderRequest;
import com.coffekyun.cinema.dto.OrderSeatRequest;
import com.coffekyun.cinema.entity.Seat;
import com.coffekyun.cinema.exception.DataAlreadyExistsException;
import com.coffekyun.cinema.repository.OrderDetailRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SeatDetailServiceImplTest {

    @Autowired
    private SeatDetailService seatDetailService;

    @Test
    void testCheckSeatAvailability() {

        OrderSeatRequest orderSeatRequest = new OrderSeatRequest();
        orderSeatRequest.setSeatCode("A1");
        orderSeatRequest.setStudioName("Sagiri chan");

        OrderDetailRequest orderDetailRequest = new OrderDetailRequest();
        orderDetailRequest.setOrderSeatRequests(List.of(orderSeatRequest));

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setOrderDetailRequests(List.of(orderDetailRequest));

        Assertions.assertThrows(DataAlreadyExistsException.class, () -> {
            seatDetailService.checkSeatAvailability(orderRequest);
        });

    }

    @Test
    void testCheckSeatAvailabilitySuccess() {

        OrderSeatRequest orderSeatRequest = new OrderSeatRequest();
        orderSeatRequest.setSeatCode("A1");
        orderSeatRequest.setStudioName("Sagiri chan");

        OrderDetailRequest orderDetailRequest = new OrderDetailRequest();
        orderDetailRequest.setOrderSeatRequests(List.of(orderSeatRequest));

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setOrderDetailRequests(List.of(orderDetailRequest));

        Boolean status = seatDetailService.checkSeatAvailability(orderRequest);

        Assertions.assertTrue(status);

    }
}