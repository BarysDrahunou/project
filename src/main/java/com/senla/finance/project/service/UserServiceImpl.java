package com.senla.finance.project.service;

import com.senla.finance.project.dao.UsersDao;
import com.senla.finance.project.model.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    UsersDao usersDao;

    public void addUser(User user) {
        usersDao.merge(user);
    }

    public List<User> getAllUsers() {
        return usersDao.findAll();
    }

    @Override
    public boolean checkIfUserExists(String email) {
        return usersDao.checkIfUserExists(email);
    }
}
