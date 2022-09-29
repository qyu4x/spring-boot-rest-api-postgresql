package com.coffekyun.cinema.service;

import com.coffekyun.cinema.dto.OrderRequest;

import java.util.List;
import java.util.Map;

public interface ScheduleService {

    List<Map<String, Object>> findScheduleById(OrderRequest orderRequest);

}
