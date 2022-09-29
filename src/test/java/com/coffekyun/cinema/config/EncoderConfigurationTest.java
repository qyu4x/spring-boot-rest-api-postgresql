package com.coffekyun.cinema.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EncoderConfigurationTest {

    @Autowired
    private EncoderConfiguration encoderConfiguration;

    @Test
    void testEncode() {
        String passwordNotEncode1 = encoderConfiguration.passwordEncoder().encode("rahasia");
        String passwordNotEncode2 = encoderConfiguration.passwordEncoder().encode("rahasia");

        encoderConfiguration.passwordEncoder().matches("rahasia", passwordNotEncode1);
        encoderConfiguration.passwordEncoder().matches("rahasia", passwordNotEncode2);
    }
}