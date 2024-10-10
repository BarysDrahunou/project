package com.senla.finance.project.dao;

import com.senla.finance.project.model.users.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface UsersDao {

    void persist(User user);

    void merge(User user);

    Optional<User> findUserByEmail(String email);

    List<User> findAll();

    boolean checkIfUserExists(String email);

    void deleteUser(User user);
}
