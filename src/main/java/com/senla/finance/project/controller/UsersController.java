package com.senla.finance.project.controller;

import com.senla.finance.project.dto.UserRequestDto;
import com.senla.finance.project.model.users.User;
import com.senla.finance.project.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.senla.finance.project.utils.Constants.*;
import static com.senla.finance.project.utils.PropertiesValidator.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/get")
    public User getUser(@Valid @RequestBody UserRequestDto requestDto) {
        return userService.findUserByEmail(validated(EMAIL_PROPERTY, requestDto.getEmail()));
    }

    @PostMapping("/add")
    public void addUser(@Valid @RequestBody UserRequestDto requestDto) {
        userService.addUser(requestDto);
    }

    @DeleteMapping("/delete")
    public void deleteUser(@Valid @RequestBody UserRequestDto requestDto) {
        userService.deleteUser(requestDto);
    }

    @PutMapping("/update")
    public void updateUser(@Valid @RequestBody UserRequestDto requestDto) {
        userService.updateUser(requestDto);
    }
}

