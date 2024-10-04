package com.senla.finance.project.controller;

import com.senla.finance.project.dto.UserRequestDto;
import com.senla.finance.project.model.currency.Balance;
import com.senla.finance.project.model.roles.Role;
import com.senla.finance.project.model.subscriptions.SubscriptionKind;
import com.senla.finance.project.model.users.User;
import com.senla.finance.project.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.senla.finance.project.utils.Constants.*;
import static com.senla.finance.project.utils.PropertiesValidator.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        User user = User.builder()
                .firstName(validated(FIRST_NAME_PROPERTY, requestDto.getFirstName()))
                .lastName(validated(LAST_NAME_PROPERTY, requestDto.getLastName()))
                .email(validated(EMAIL_PROPERTY, requestDto.getEmail()))
                .password(passwordEncoder.encode(validated(PASSWORD_PROPERTY, requestDto.getPassword())))
                .role(roleValidated(requestDto.getRole()))
                .subscriptionKind(SubscriptionKind.DISABLED)
                .expirationDate(DISABLED_SUBSCRIPTION_EXPIRATION_DATE)
                .balance(new Balance())
                .build();
        userService.addUser(user);
    }

    @DeleteMapping("/delete")
    public void deleteUser(@Valid @RequestBody UserRequestDto requestDto) {
        userService.deleteUser(validated(EMAIL_PROPERTY, requestDto.getEmail()));
    }

    @PutMapping("/update")
    public void updateUser(@Valid @RequestBody UserRequestDto requestDto) {
        User existingUser = userService.findUserByEmail(validated(EMAIL_PROPERTY, requestDto.getEmail()));

        existingUser.setFirstName(updatePropertyIfNotBlank(existingUser.getFirstName(), requestDto.getFirstName()));
        existingUser.setLastName(updatePropertyIfNotBlank(existingUser.getLastName(), requestDto.getLastName()));
        existingUser.setEmail(updatePropertyIfNotBlank(existingUser.getEmail(), requestDto.getEmail()));
        existingUser.setPassword(passwordEncoder.encode(updatePropertyIfNotBlank(existingUser.getPassword(), requestDto.getPassword())));
        existingUser.setRole(Role.valueOf(updatePropertyIfNotBlank(existingUser.getRole().name(), requestDto.getRole())));

        userService.updateUser(existingUser);
    }
}

