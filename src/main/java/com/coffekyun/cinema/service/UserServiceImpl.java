package com.coffekyun.cinema.service;

import com.coffekyun.cinema.config.EncoderConfiguration;
import com.coffekyun.cinema.dto.UserRequest;
import com.coffekyun.cinema.dto.UserResponse;
import com.coffekyun.cinema.dto.UserUpdateRequest;
import com.coffekyun.cinema.entity.User;
import com.coffekyun.cinema.exception.DataAlreadyExistsException;
import com.coffekyun.cinema.exception.DataNotFoundException;
import com.coffekyun.cinema.exception.NotMatchException;
import com.coffekyun.cinema.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EncoderConfiguration encoderConfiguration;
    @Override
    public UserResponse registerNewUserAccount(UserRequest userRequest) {
        if (userRepository.findByEmail(userRequest.getEmail()) != null) {
            throw new DataAlreadyExistsException(
                    "There is an account with that email adress:" + userRequest.getEmail()
            );
        } else {
            User user = userRequest.toUser();
            user.setId(UUID.randomUUID().toString());
            user.setPassword(
                    encoderConfiguration.passwordEncoder().encode(user.getPassword())
            );
            User registeredUser = userRepository.save(user);
            return UserResponse.builder()
                    .id(registeredUser.getId())
                    .name(registeredUser.getName())
                    .email(registeredUser.getEmail())
                    .phone(registeredUser.getPhone())
                    .build();
        }
    }

    @Override
    public UserResponse updateUserAccount(UserUpdateRequest userRequest, String id) {
        User toUser = userRequest.toUser();
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            User updateUser = user.get();
            boolean match =  encoderConfiguration.passwordEncoder().matches(userRequest.getOldPassword(), updateUser.getPassword());
            if (match) {
                updateUser.setName(toUser.getName());
                updateUser.setEmail(toUser.getEmail());
                updateUser.setUpdatedAt(new Date());
                updateUser.setPassword(
                        encoderConfiguration.passwordEncoder().encode(userRequest.getNewPassword())
                );

                User updatedUser = userRepository.save(updateUser);
                return UserResponse.builder()
                        .id(updatedUser.getId())
                        .name(updatedUser.getName())
                        .email(updateUser.getEmail())
                        .phone(updateUser.getPhone())
                        .build();
            } else {
                throw new NotMatchException("Opps, passwords don't match");
            }
        } else {
            throw new DataNotFoundException("user with id " + id + " not found");
        }
    }

    @Override
    public void deleteUserAccount(String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new DataNotFoundException("user with id " + id + " not found");
        }
    }

    @Override
    public User findById(String id) {
        Optional<User> userResposne = userRepository.findById(id);
        if (userResposne.isEmpty()) {
            throw new DataNotFoundException("user with id " + id + " not found");
        } else {
            User user = userResposne.get();
            return User.builder()
                    .name(user.getName())
                    .email(user.getEmail())
                    .phone(user.getPhone()).build();

        }
    }
}
