package com.senla.finance.project.config;

import com.senla.finance.project.model.currency.Balance;
import com.senla.finance.project.model.roles.Role;
import com.senla.finance.project.model.subscriptions.SubscriptionKind;
import com.senla.finance.project.model.users.User;
import com.senla.finance.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SetupDataLoader {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @EventListener(ApplicationReadyEvent.class)
    public void addAdminIfNotExists() {
        if (!userService.checkIfUserExists("admin@senla.eu")) {
            User user = User.builder()
                    .firstName("Aleh")
                    .lastName("Lapin")
                    .email("admin@senla.eu")
                    .password(passwordEncoder.encode("admin"))
                    .role(Role.ADMIN)
                    .subscriptionKind(SubscriptionKind.DISABLED)
                    .expirationDate(LocalDateTime.of(3000, 1, 1, 0, 0))
                    .balance(new Balance())
                    .build();

            userService.addUser(user);
        }
    }
}
