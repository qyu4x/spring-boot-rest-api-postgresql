package com.coffekyun.cinema.repository;

import com.coffekyun.cinema.entity.Order;
import com.coffekyun.cinema.service.OrderDetailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;


@SpringBootTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailService orderDetailService;

    @Test
    void testUpdateTotalPriceById() {

        BigDecimal totalPrice = orderDetailService.getTotalPriceById(Order.builder()
                .id("ff643d7e-1f8f-4393-b0a5-0f5177d8ec92").build());

        orderRepository.updateTotalPriceById(totalPrice, "ff643d7e-1f8f-4393-b0a5-0f5177d8ec92");


    }

    @Test
    void testGetOrderDetail() {
        List<Order> list = orderRepository.getOrderDetail("38250b71-3607-45b0-9ea5-860121cda5b5");
        Assertions.assertNotNull(list);
    }
}