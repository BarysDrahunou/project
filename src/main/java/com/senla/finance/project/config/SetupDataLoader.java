package com.senla.finance.project.config;

import com.senla.finance.project.model.currency.Balance;
import com.senla.finance.project.model.roles.Role;
import com.senla.finance.project.model.subscriptions.SubscriptionKind;
import com.senla.finance.project.model.users.User;
import com.senla.finance.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static com.senla.finance.project.utils.Constants.*;

@Component
public class SetupDataLoader {

    @Value("${admin.user.firstname}")
    private String defaultAdminUserFirstName;
    @Value("${admin.user.lastname}")
    private String defaultAdminUserLastName;
    @Value("${admin.user.email}")
    private String defaultAdminUserEmail;
    @Value("${admin.user.password}")
    private String defaultAdminUserPassword;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @EventListener(ApplicationReadyEvent.class)
    public void addAdminIfNotExists() {
        if (!userService.checkIfUserExists(defaultAdminUserEmail)) {
            User user = User.builder()
                    .firstName(defaultAdminUserFirstName)
                    .lastName(defaultAdminUserLastName)
                    .email(defaultAdminUserEmail)
                    .password(passwordEncoder.encode(defaultAdminUserPassword))
                    .role(Role.ADMIN)
                    .subscriptionKind(SubscriptionKind.DISABLED)
                    .expirationDate(DISABLED_SUBSCRIPTION_EXPIRATION_DATE)
                    .balance(new Balance())
                    .build();

            userService.addUser(user);
        }
    }
}
