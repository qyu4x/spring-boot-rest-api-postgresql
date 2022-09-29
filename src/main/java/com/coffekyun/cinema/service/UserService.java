package com.coffekyun.cinema.service;

import com.coffekyun.cinema.dto.UserRequest;
import com.coffekyun.cinema.dto.UserResponse;
import com.coffekyun.cinema.dto.UserUpdateRequest;
import com.coffekyun.cinema.entity.User;

public interface UserService {

    UserResponse registerNewUserAccount(UserRequest userRequest);

    UserResponse updateUserAccount(UserUpdateRequest userRequest, String id);

    void deleteUserAccount(String id);

    User findById(String id);

}
