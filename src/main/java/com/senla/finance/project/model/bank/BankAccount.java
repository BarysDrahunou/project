package com.senla.finance.project.model.bank;

import com.senla.finance.project.model.currency.Balance;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BankAccount {

    private String firstName;
    private String lastName;
    @Id
    private String id;
    private String secret;
    private Balance balance;
}
