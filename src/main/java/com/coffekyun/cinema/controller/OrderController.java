package com.coffekyun.cinema.controller;


import com.coffekyun.cinema.dto.*;

import com.coffekyun.cinema.exception.GlobalExceptionHandler;
import com.coffekyun.cinema.service.OrderDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/order/")
public class OrderController {
    private OrderDetailService orderDetailService;
    public OrderController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @PostMapping(value = "/ticket")
    @ResponseBody
    public ResponseEntity<?> postRequestOrder(@Valid @RequestBody OrderRequest orderRequest) {
        log.info("#calling controller postRequestRegister");
        try {
            OrderResponse orderResponse = orderDetailService.createOrderDetail(orderRequest);
            return GlobalResponseHandler
                    .generateResponse("order successfully " , HttpStatus.OK, orderResponse);
        }catch (RuntimeException exception) {
            return GlobalExceptionHandler.dataAlreadyExistsHandler(exception.getMessage());
        }
    }
}
