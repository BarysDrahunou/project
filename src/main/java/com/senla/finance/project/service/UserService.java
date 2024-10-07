package com.senla.finance.project.service;

import com.senla.finance.project.model.users.User;

import java.util.List;

public interface UserService {

    void addUser(User user);

    User findUserByEmail(String email);

    List<User> getAllUsers();

    boolean checkIfUserExists(String email);
}
