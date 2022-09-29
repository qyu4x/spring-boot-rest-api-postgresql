package com.coffekyun.cinema.service;

import com.coffekyun.cinema.entity.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Test
    void testCreateOrder() {
        Order order =  orderService.createOrder("d8215237-deb4-4f81-984b-d22e2a17d7a9");
        Assertions.assertNotNull(order);
        Assertions.assertEquals("d8215237-deb4-4f81-984b-d22e2a17d7a9", order.getUser().getId());
    }

    @Test
    void testFindById() {
        Order order = orderService.findById("502af91c-8783-4001-85f7-0dd02fda7527");
        Assertions.assertNotNull(order);
    }
}