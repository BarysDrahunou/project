package com.senla.finance.project.controller;

import com.senla.finance.project.model.users.User;
import com.senla.finance.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

//    @PostMapping("/{login}/add")
//    public void addUser(@RequestBody UserRequestDto requestDto, @PathVariable("login") String login) {
//        if (!getUsers().isEmpty()) {
//            if (userService.isAdmin(login)){
//                userService.addUser(requestDto.getName(), requestDto.getSurname(), requestDto.getLogin(), Role.USER);
//            }
//            else {
//                throw new RuntimeException("You have no permissions to create a user");
//            }
//        } else {
//            LOGGER.log(Level.INFO, "There are no users yet, adding new admin user...");
//            addDefaultAdminUser();
//        }
//
//    }
}
