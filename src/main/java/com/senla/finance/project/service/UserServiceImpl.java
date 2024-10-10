package com.senla.finance.project.service;

import com.senla.finance.project.dao.UsersDao;
import com.senla.finance.project.dto.SubscriptionResponseDto;
import com.senla.finance.project.dto.UserRequestDto;
import com.senla.finance.project.exceptions.UserAlreadyExistsException;
import com.senla.finance.project.exceptions.UserNotFoundException;
import com.senla.finance.project.mapper.SubscriptionMapper;
import com.senla.finance.project.model.currency.Balance;
import com.senla.finance.project.model.roles.Role;
import com.senla.finance.project.model.users.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.senla.finance.project.utils.Constants.*;
import static com.senla.finance.project.utils.PropertiesValidator.*;
import static com.senla.finance.project.utils.PropertiesValidator.updatePropertyIfNotBlank;
import static java.lang.String.format;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UsersDao usersDao;

    @Autowired
    SubscriptionService subscriptionService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void addUser(UserRequestDto requestDto) {
        String email = validated(EMAIL_PROPERTY, requestDto.getEmail());

        Optional<User> existingUser = usersDao.findUserByEmail(email);

        existingUser.ifPresent(s -> {
            throw new UserAlreadyExistsException(format(USER_ALREADY_EXISTS_EXCEPTION, email));
        });

        User user = User.builder()
                .firstName(validated(FIRST_NAME_PROPERTY, requestDto.getFirstName()))
                .lastName(validated(LAST_NAME_PROPERTY, requestDto.getLastName()))
                .email(email)
                .password(passwordEncoder.encode(validated(PASSWORD_PROPERTY, requestDto.getPassword())))
                .role(roleValidated(requestDto.getRole()))
                .balance(new Balance())
                .build();

        usersDao.persist(user);
        subscriptionService.logNewDefaultSubscription(email);
    }

    @Override
    public void addUser(User user) {
        String email = user.getEmail();

        Optional<User> existingUser = usersDao.findUserByEmail(email);

        existingUser.ifPresent(s -> {
            throw new UserAlreadyExistsException(format(USER_ALREADY_EXISTS_EXCEPTION, email));
        });

        usersDao.persist(user);
        subscriptionService.logNewDefaultSubscription(email);
    }

    @Override
    public void updateUser(UserRequestDto requestDto) {
        String email = validated(EMAIL_PROPERTY, requestDto.getEmail());

        Optional<User> existingUser = usersDao.findUserByEmail(email);

        User user = existingUser.orElseThrow(()-> new UserNotFoundException(format(USER_NOT_FOUND_EXCEPTION, email)));

        user.setFirstName(updatePropertyIfNotBlank(user.getFirstName(), requestDto.getFirstName()));
        user.setLastName(updatePropertyIfNotBlank(user.getLastName(), requestDto.getLastName()));
        user.setPassword(passwordEncoder.encode(updatePropertyIfNotBlank(user.getPassword(), requestDto.getPassword())));
        user.setRole(Role.valueOf(updatePropertyIfNotBlank(user.getRole().name(), requestDto.getRole())));

        usersDao.merge(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return usersDao.findUserByEmail(email)
                .orElseThrow(()-> new UserNotFoundException(format(USER_NOT_FOUND_EXCEPTION, email)));
    }

    @Override
    public boolean userExists(String email) {
        return usersDao.findUserByEmail(email).isPresent();
    }

    @Override
    public SubscriptionResponseDto getSubscriptionResponse(String email) {
        User user = usersDao.findUserByEmail(email)
                .orElseThrow(()-> new UserNotFoundException(format(USER_NOT_FOUND_EXCEPTION, email)));

        return new SubscriptionMapper().mapFromUser(user);
    }

    public List<User> getAllUsers() {
        return usersDao.findAll();
    }

    @Override
    public void deleteUser(UserRequestDto requestDto) {
        String email = validated(EMAIL_PROPERTY, requestDto.getEmail());

        User user = usersDao.findUserByEmail(email)
                .orElseThrow(()-> new UserNotFoundException(format(USER_NOT_FOUND_EXCEPTION, email)));

        usersDao.deleteUser(user);
    }
}
