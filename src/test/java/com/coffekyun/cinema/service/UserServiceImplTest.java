package com.coffekyun.cinema.service;

import com.coffekyun.cinema.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    void testFindById() {

        User user = userService.findById("d8215237-deb4-4f81-984b-d22e2a17d7a9");
        Assertions.assertNotNull(user);

    }
}