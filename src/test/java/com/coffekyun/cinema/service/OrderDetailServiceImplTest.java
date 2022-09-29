package com.coffekyun.cinema.service;

import com.coffekyun.cinema.dto.*;
import com.coffekyun.cinema.entity.Order;
import com.coffekyun.cinema.entity.Schedule;
import com.coffekyun.cinema.entity.SeatDetail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SpringBootTest
class OrderDetailServiceImplTest {

    @Autowired
    private OrderDetailService orderDetailService;

    @Test
    void testInsertIntoTableOrderDetail() {


        Map<String, Object> orderDetails = new HashMap<>();
        orderDetails.put("quantity", 4);
        orderDetails.put("schedule", Schedule.builder()
                .id("fe2377e7-85fb-4663-b909-a7f6abef42bf")
                .price(new BigDecimal("40000.00"))
                .build());

        Order order = Order.builder()
                .id("ff643d7e-1f8f-4393-b0a5-0f5177d8ec92")
                .build();

        orderDetailService.insertIntoTableOrderDetail(orderDetails, order);
    }

    @Test
    void testCreateOrderDetail() {

        List<OrderSeatRequest> seatDetailRequests = new ArrayList<>();
        OrderSeatRequest seatDetailRequest = new OrderSeatRequest();
        seatDetailRequest.setSeatCode("A12");
        seatDetailRequest.setStudioName("Sagiri chan");
        seatDetailRequests.add(seatDetailRequest);

        List<OrderDetailRequest> orderDetailRequests = new ArrayList<>();
        OrderDetailRequest orderDetailRequest = new OrderDetailRequest();
        orderDetailRequest.setQuantity(1);
        orderDetailRequest.setScheduleId("c974ab19-8661-4f8b-a071-7a834f1e83d7");
        orderDetailRequest.setOrderSeatRequests(seatDetailRequests);
        orderDetailRequests.add(orderDetailRequest);

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setUserId("d8215237-deb4-4f81-984b-d22e2a17d7a9");
        orderRequest.setOrderDetailRequests(orderDetailRequests);

        orderDetailService.createOrderDetail(orderRequest);
    }

    @Test
    void testCreateOrderDetailFailed() {

        List<OrderSeatRequest> seatDetailRequests = new ArrayList<>();
        OrderSeatRequest seatDetailRequest = new OrderSeatRequest();
        seatDetailRequest.setSeatCode("A1");
        seatDetailRequest.setStudioName("Sagiri chan");
        seatDetailRequests.add(seatDetailRequest);

        List<OrderDetailRequest> orderDetailRequests = new ArrayList<>();
        OrderDetailRequest orderDetailRequest = new OrderDetailRequest();
        orderDetailRequest.setQuantity(2);
        orderDetailRequest.setScheduleId("e1544de1-3cdb-49a4-bf2f-65ac5122265a");
        orderDetailRequest.setOrderSeatRequests(seatDetailRequests);
        orderDetailRequests.add(orderDetailRequest);

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setUserId("86aa7ac8-25ef-4b26-b7bc-9884791d0729");
        orderRequest.setOrderDetailRequests(orderDetailRequests);

        Assertions.assertThrows(RuntimeException.class, () -> {
            orderDetailService.createOrderDetail(orderRequest);
        });
    }

    @Test
    void testGetTotalPriceById() {
        Order order = new Order();
        order.setId("53d93ecf-9458-44ee-a37d-d11f138c90b4");
        BigDecimal totalPrice = orderDetailService.getTotalPriceById(order);

        Assertions.assertNotNull(totalPrice);

    }

    @Test
    void testGetResponse() {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setUserId("7b021d69-7618-4c44-b8a9-57e10ea51a09");

        Order order = new Order();
        order.setId("f63ac204-1972-4818-93f9-901ef3a37e4e");

        OrderResponse response = orderDetailService.getResponse(order, new BigDecimal("100000"));

       Assertions.assertNotNull(response);

    }
}