package com.coffekyun.cinema.dto;


import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class ScheduleRequest {

    private Date showDate;

    private Date startTime;

    private Date endTime;

    private BigDecimal price;

}
