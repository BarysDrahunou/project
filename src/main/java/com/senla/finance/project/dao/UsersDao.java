package com.senla.finance.project.dao;

import com.senla.finance.project.model.roles.Roles;
import com.senla.finance.project.model.users.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UsersDao {
    void merge(User user);
    List<User> findAll();
    Roles getUserRole(String login);
}
