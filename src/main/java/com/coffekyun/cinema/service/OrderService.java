package com.coffekyun.cinema.service;

import com.coffekyun.cinema.dto.OrderRequest;
import com.coffekyun.cinema.entity.Order;
import com.coffekyun.cinema.entity.User;

public interface OrderService {

    void createUserOrder(OrderRequest orderRequest);
    Order createOrder(String idUser);

    Order findById(String id);

}
