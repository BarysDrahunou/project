package com.senla.finance.project.model.users;

import com.senla.finance.project.model.currency.Balance;
import com.senla.finance.project.model.roles.Role;
import com.senla.finance.project.model.subscriptions.SubscriptionKind;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import static com.senla.finance.project.utils.Constants.USERS_TABLE_NAME;


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = USERS_TABLE_NAME)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private SubscriptionKind subscriptionKind;
    private LocalDateTime expirationDate;
    private Balance balance;
}
