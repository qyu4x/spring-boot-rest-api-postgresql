package com.coffekyun.cinema.repository;

import com.coffekyun.cinema.entity.SeatDetail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class SeatDetailRepositoryTest {
    @Autowired
    private SeatDetailRepository seatDetailRepository;

    @Test
    void testGetSeatIfAvailable() {

        List<SeatDetail> seatDetailList =  seatDetailRepository.findByStatus(true);
        Assertions.assertNotNull(seatDetailList);

    }

    @Test
    void testFindBySeatCodeAndStudioName() {
        List<SeatDetail> seatDetailList = seatDetailRepository.findBySeatCodeAndStudioName("test", "test");
        Assertions.assertSame(true, seatDetailList.isEmpty());
    }

    @Test
    void testFindBySeatCodeAndStudioNameSuccess() {
        List<SeatDetail> seatDetailList = seatDetailRepository.findBySeatCodeAndStudioName("A1", "Sagiri chan");
        Assertions.assertSame(false, seatDetailList.isEmpty());
    }
}