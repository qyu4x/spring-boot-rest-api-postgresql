package com.coffekyun.cinema.service;

import com.coffekyun.cinema.dto.OrderDetailResponse;
import com.coffekyun.cinema.dto.OrderRequest;
import com.coffekyun.cinema.dto.OrderResponse;
import com.coffekyun.cinema.entity.Order;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface OrderDetailService {

    OrderResponse createOrderDetail(OrderRequest orderRequest);
    void insertIntoTableOrderDetail(Map<String, Object> orderDetail, Order order);

    BigDecimal getTotalPriceById(Order order);

    List<OrderDetailResponse> getOrderDetail(String id);

    OrderResponse getResponse(Order order, BigDecimal totalPrice);

}
