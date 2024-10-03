package com.senla.finance.project.service;

import com.senla.finance.project.dao.UsersDao;
import com.senla.finance.project.dto.UserRequestDto;
import com.senla.finance.project.model.roles.Roles;
import com.senla.finance.project.model.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {
    @Autowired
    UsersDao usersDao;

    public boolean isAdmin(String login){
        return usersDao.getUserRole(login).equals(Roles.ADMIN);
    }

    public void addUser(String name, String surname, String login, Roles role){
        User user = new User(name, surname, login, role);
        usersDao.merge(user);
    }

    public List<User> getAllUsers(){
        return usersDao.findAll();
    }
}
