package com.senla.finance.project.controller;

import com.senla.finance.project.dto.UserRequestDto;
import com.senla.finance.project.model.roles.Roles;
import com.senla.finance.project.model.users.User;
import com.senla.finance.project.service.FinnhubService;
import com.senla.finance.project.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UsersController {

    private static final Logger LOGGER = LogManager.getLogger(UsersController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private FinnhubService finnhubService;

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/init")
    public void addDefaultAdminUser() {
        String defaultAdminName = "Oleg";
        String defaultAdminSurname = "Lapin";
        String defaultAdminLogin = "admin";
        userService.addUser(defaultAdminName, defaultAdminSurname, defaultAdminLogin, Roles.ADMIN);

    }

    @PostMapping("/{login}/add")
    public void addUser(@RequestBody UserRequestDto requestDto, @PathVariable("login") String login) {
        if (!getUsers().isEmpty()) {
            if (userService.isAdmin(login)){
                userService.addUser(requestDto.getName(), requestDto.getSurname(), requestDto.getLogin(), Roles.USER);
            }
            else {
                throw new RuntimeException("You have no permissions to create a user");
            }
        } else {
            LOGGER.log(Level.INFO, "There are no users yet, adding new admin user...");
            addDefaultAdminUser();
        }

    }
}
