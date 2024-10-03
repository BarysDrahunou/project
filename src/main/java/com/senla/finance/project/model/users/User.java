package com.senla.finance.project.model.users;

import com.senla.finance.project.model.currency.Balance;
import com.senla.finance.project.model.roles.Roles;
import com.senla.finance.project.model.subscriptions.SubscriptionKind;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@Entity
@Table(name = "Users")
@NoArgsConstructor
public class User {

    private String name;
    private String surname;
    @Id
    private String login;
    @Enumerated(EnumType.STRING)
    @Setter
    private SubscriptionKind subscriptionKind;
    @Enumerated(EnumType.STRING)
    @Setter
    private Roles role;
    private LocalDateTime expirationDate;
    private Balance balance;


    public User(String name, String surname, String login, Roles role) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.subscriptionKind = SubscriptionKind.DISABLED;
        this.role = role;
        this.expirationDate = LocalDateTime.of(3000,1,1,0,0);
        this.balance = new Balance();
    }
}
