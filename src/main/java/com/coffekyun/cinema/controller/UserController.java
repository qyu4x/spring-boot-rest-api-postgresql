package com.coffekyun.cinema.controller;

import com.coffekyun.cinema.dto.GlobalResponseHandler;
import com.coffekyun.cinema.dto.UserRequest;
import com.coffekyun.cinema.dto.UserResponse;
import com.coffekyun.cinema.dto.UserUpdateRequest;
import com.coffekyun.cinema.exception.DataAlreadyExistsException;
import com.coffekyun.cinema.exception.DataNotFoundException;
import com.coffekyun.cinema.exception.GlobalExceptionHandler;
import com.coffekyun.cinema.exception.NotMatchException;
import com.coffekyun.cinema.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/user/")
public class UserController {

    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register")
    @ResponseBody
    public ResponseEntity<?> postRequestRegister(@Valid @RequestBody UserRequest userRequest) {
        log.info("#calling controller postRequestRegister");
        try {
            UserResponse userResponse = userService.registerNewUserAccount(userRequest);
            return GlobalResponseHandler
                    .generateResponse("successfully register user with id "  + userResponse.getId() , HttpStatus.OK, userResponse);
        }catch (DataAlreadyExistsException exception) {
            return GlobalExceptionHandler.dataAlreadyExistsHandler(exception.getMessage());
        }
    }


    @PutMapping(value = "/update/{id}")
    @ResponseBody
    public ResponseEntity<?> postRequestUpdate(@Valid @RequestBody UserUpdateRequest userUpdateRequest , @PathVariable String id) {
        log.info("#calling controller postRequestUpdate");
        try {
            UserResponse userResponse = userService.updateUserAccount(userUpdateRequest, id);
            return GlobalResponseHandler
                    .generateResponse("successfully update user with id "  + userResponse.getId() , HttpStatus.OK, userResponse);
        }catch (DataNotFoundException | NotMatchException exception) {
            return GlobalExceptionHandler.dataNotFoundHandler(exception.getMessage());
        }
    }


    @DeleteMapping(value = "/delete/{id}")
    @ResponseBody
    public ResponseEntity<?> requestDeleteUserAccount(@PathVariable("id") String id) {
        log.info("#calling controller requestDeleteUserAccount");
        try {
            userService.deleteUserAccount(id);
            return GlobalResponseHandler
                    .generateResponse("successfully delete data with id "  + id ,HttpStatus.OK, null);
        }catch (DataNotFoundException exception) {
            return GlobalExceptionHandler.dataNotFoundHandler(exception.getMessage());
        }
    }


}
