package com.coffekyun.cinema.repository;

import com.coffekyun.cinema.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ScheduleRepository extends JpaRepository<Schedule, String> {

}
