package com.coffekyun.cinema.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

    private String id;

    private String name;

    private String email;

    private String phone;

}
