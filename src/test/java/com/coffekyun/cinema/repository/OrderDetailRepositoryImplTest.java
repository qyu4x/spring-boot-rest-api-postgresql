package com.coffekyun.cinema.repository;

import com.coffekyun.cinema.entity.Order;
import com.coffekyun.cinema.entity.OrderDetail;
import com.coffekyun.cinema.entity.Schedule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;


@SpringBootTest
class OrderDetailRepositoryImplTest {
    
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    void testAddOrderDetail() {
        Schedule schedule = Schedule.builder()
                .id("8acbdd5f-1dcc-4b27-9dfa-a601900df3ee")
                .price(new BigDecimal("40000.00"))
                .build();

        OrderDetail orderDetail = OrderDetail.builder()
                .order(Order.builder().id("3a71e0e0-8cad-4f18-b66e-0efe8c9dbf15").build())
                .schedule(Schedule.builder().id("2e57c752-b9b5-4121-aff5-1e929ea86446").build())
                .quantity(4)
                .price(schedule.getPrice())
                .build();
        orderDetailRepository.addOrderDetail(orderDetail);
    }

    @Test
    void testFindOrderDetailById() {
        Order order = new Order();
        order.setId("be41a583-9a59-4628-984a-e3350feb723b");

        List<OrderDetail> orderDetails = orderDetailRepository.findOrderDetailByOrderId(order.getId());
        Assertions.assertNotNull(orderDetails);
    }
}