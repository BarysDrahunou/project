package com.senla.finance.project.model.users;

import com.senla.finance.project.model.currency.Balance;
import com.senla.finance.project.model.roles.Role;
import jakarta.persistence.*;
import lombok.*;

import static com.senla.finance.project.utils.Constants.USERS_TABLE_NAME;


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = USERS_TABLE_NAME)
public class User {

    private String firstName;
    private String lastName;
    @Id
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private Balance balance;
}
