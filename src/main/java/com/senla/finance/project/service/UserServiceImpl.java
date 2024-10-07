package com.senla.finance.project.service;

import com.senla.finance.project.dao.UsersDao;
import com.senla.finance.project.dto.SubscriptionResponseDto;
import com.senla.finance.project.dto.UserRequestDto;
import com.senla.finance.project.mapper.SubscriptionMapper;
import com.senla.finance.project.model.currency.Balance;
import com.senla.finance.project.model.roles.Role;
import com.senla.finance.project.model.subscriptions.SubscriptionKind;
import com.senla.finance.project.model.users.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.senla.finance.project.utils.Constants.*;
import static com.senla.finance.project.utils.PropertiesValidator.*;
import static com.senla.finance.project.utils.PropertiesValidator.updatePropertyIfNotBlank;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UsersDao usersDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void addUser(UserRequestDto requestDto) {
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

        usersDao.persist(user);
    }

    @Override
    public void updateUser(UserRequestDto requestDto) {
        User existingUser = usersDao.findUserByEmail(validated(EMAIL_PROPERTY, requestDto.getEmail()));

        existingUser.setFirstName(updatePropertyIfNotBlank(existingUser.getFirstName(), requestDto.getFirstName()));
        existingUser.setLastName(updatePropertyIfNotBlank(existingUser.getLastName(), requestDto.getLastName()));
        existingUser.setEmail(updatePropertyIfNotBlank(existingUser.getEmail(), requestDto.getEmail()));
        existingUser.setPassword(passwordEncoder.encode(updatePropertyIfNotBlank(existingUser.getPassword(), requestDto.getPassword())));
        existingUser.setRole(Role.valueOf(updatePropertyIfNotBlank(existingUser.getRole().name(), requestDto.getRole())));

        usersDao.merge(existingUser);
    }

    @Override
    public User findUserByEmail(String email) {
        return usersDao.findUserByEmail(email);
    }

    @Override
    public SubscriptionResponseDto getSubscriptionResponse(String email) {
        User user = usersDao.findUserByEmail(email);

        return new SubscriptionMapper().mapFromUser(user);
    }

    public List<User> getAllUsers() {
        return usersDao.findAll();
    }

    @Override
    public void deleteUser(String email) {
        usersDao.deleteUser(email);
    }
}
