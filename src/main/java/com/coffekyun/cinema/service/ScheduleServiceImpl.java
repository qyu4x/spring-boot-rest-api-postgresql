package com.coffekyun.cinema.service;

import com.coffekyun.cinema.dto.OrderDetailRequest;
import com.coffekyun.cinema.dto.OrderRequest;
import com.coffekyun.cinema.entity.Schedule;
import com.coffekyun.cinema.exception.DataNotFoundException;
import com.coffekyun.cinema.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ScheduleServiceImpl implements ScheduleService{

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public  List<Map<String, Object>> findScheduleById(OrderRequest orderRequest) {
        List<Map<String, Object>> orderDetails = new ArrayList<>();
        orderRequest.getOrderDetailRequests().stream()
                .forEach(orderDetailRequest -> {
                    Optional<Schedule> schedule = scheduleRepository.findById(orderDetailRequest.getScheduleId());
                    if (schedule.isEmpty()) {
                        throw new DataNotFoundException("Opps schedule with id " + orderDetailRequest.getScheduleId() + " not found");
                    }
                    Map<String, Object> orderDetail = new HashMap<>();
                    Schedule tempSchedule = Schedule.builder()
                            .id(schedule.get().getId())
                            .price(schedule.get().getPrice())
                            .build();

                    orderDetail.put("schedule", tempSchedule);
                    orderDetail.put("quantity", orderDetailRequest.getQuantity());

                    orderDetails.add(orderDetail);
                });
        return orderDetails;
    }
}
