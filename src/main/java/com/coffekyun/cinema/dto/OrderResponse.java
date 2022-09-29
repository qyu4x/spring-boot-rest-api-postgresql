package com.coffekyun.cinema.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {

    private String orderId;

    private BigDecimal totalPrice;

    private String currencyCode;

    private String currencySymbol;

    List<OrderDetailResponse> orderDetails;


}
