package com.coffekyun.cinema.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailResponse {

    private String scheduleId;

    private String title;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date showDate;

    @JsonFormat(pattern="HH:mm:ss")
    private Date startTime;

    @JsonFormat(pattern="HH:mm:ss")
    private Date endTime;

    private BigDecimal price;

    private String currencyCode;

    private String currencySymbol;

    private Integer quantity;


}
