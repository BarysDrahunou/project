package com.senla.finance.project.dao;

import com.senla.finance.project.model.bank.BankAccount;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface BankDao {
    void persist(BankAccount bankAccount);

    void updateAccount(BankAccount bankAccount);

    Optional<BankAccount> getBankAccountById(String id);

    Optional<BankAccount> getBankAccountByIdAndSecret(String id, String secret);
}
