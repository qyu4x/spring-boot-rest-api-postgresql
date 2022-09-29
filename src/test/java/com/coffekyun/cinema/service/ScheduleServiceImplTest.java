package com.coffekyun.cinema.service;

import com.coffekyun.cinema.dto.OrderDetailRequest;
import com.coffekyun.cinema.dto.OrderRequest;
import com.coffekyun.cinema.entity.Schedule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SpringBootTest
class ScheduleServiceImplTest
{
    @Autowired
    private ScheduleService scheduleService;

    @Test
    void testFindSchedule() {
        List<OrderDetailRequest> orderDetailRequests = new ArrayList<>();
        OrderDetailRequest orderDetailRequest1 = new OrderDetailRequest();
        orderDetailRequest1.setQuantity(3);
        orderDetailRequest1.setScheduleId("2e57c752-b9b5-4121-aff5-1e929ea86446");
        orderDetailRequests.add(orderDetailRequest1);

        OrderDetailRequest orderDetailRequest2 = new OrderDetailRequest();
        orderDetailRequest2.setQuantity(1);
        orderDetailRequest2.setScheduleId("2e57c752-b9b5-4121-aff5-1e929ea86446");
        orderDetailRequests.add(orderDetailRequest2);

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setOrderDetailRequests(orderDetailRequests);

        List<Map<String, Object>> orderDetails =  scheduleService.findScheduleById(orderRequest);
        Assertions.assertNotNull(orderDetails);

        Schedule schedule = (Schedule) orderDetails.get(1).get("schedule");
        Assertions.assertNotNull(schedule);

    }
}