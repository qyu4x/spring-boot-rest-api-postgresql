package com.coffekyun.cinema.service;

import com.coffekyun.cinema.dto.OrderRequest;
import com.coffekyun.cinema.entity.Order;
import com.coffekyun.cinema.entity.Schedule;
import com.coffekyun.cinema.entity.User;
import com.coffekyun.cinema.exception.DataNotFoundException;
import com.coffekyun.cinema.repository.OrderRepository;
import com.coffekyun.cinema.repository.ScheduleRepository;
import com.coffekyun.cinema.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public void createUserOrder(OrderRequest orderRequest) {

    }

    @Override
    public Order createOrder(String idUser) {
        try {
            User user = User.builder()
                    .id(idUser)
                    .build();
            Order order = Order.builder()
                    .id(UUID.randomUUID().toString())
                    .user(user)
                    .build();
            return orderRepository.save(order);
        }catch (Exception exception) {
            throw new RuntimeException("Oops ailed to make an order");
        }
    }

    @Override
    public Order findById(String id) {
        Optional<Order> orderResponse = orderRepository.findById(id);
        if (orderResponse.isEmpty()) {
            throw new DataNotFoundException("user with id " + id + " not found");
        } else {
            Order order = orderResponse.get();
            return Order.builder()
                    .id(order.getId())
                    .totalPrice(order.getTotalPrice())
                    .build();

        }
    }
}
