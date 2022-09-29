package com.coffekyun.cinema.repository;

import com.coffekyun.cinema.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testSaveDataIntoTableUsers() {

        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .name("hikaru")
                .email("hikaru@gmail.com")
                .password("rahasia")
                .build();

        User response = userRepository.save(user);
        Assertions.assertNotNull(response);

    }
}