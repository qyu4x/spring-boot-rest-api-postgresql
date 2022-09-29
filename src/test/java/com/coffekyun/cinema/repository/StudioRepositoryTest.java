package com.coffekyun.cinema.repository;

import com.coffekyun.cinema.entity.Studio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudioRepositoryTest {

    @Autowired
    private StudioRepository studioRepository;

    @Test
    void testFindStudioByIdMovie() {
        List<Studio> studioList =  studioRepository.findByStudioId("78d71d66-01b8-4b93-b7ea-21ee44cb056d");
        Assertions.assertNotNull(studioList);

    }
}