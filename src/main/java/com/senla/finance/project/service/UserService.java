package com.senla.finance.project.service;

import com.senla.finance.project.dto.SubscriptionResponseDto;
import com.senla.finance.project.dto.UserRequestDto;
import com.senla.finance.project.model.users.User;

import java.util.List;

public interface UserService {

    void addUser(UserRequestDto requestDto);

    void updateUser(UserRequestDto requestDto);

    User findUserByEmail(String email);

    SubscriptionResponseDto getSubscriptionResponse(String email);

    List<User> getAllUsers();

    void deleteUser(String email);
}
