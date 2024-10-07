package com.senla.finance.project.dao;

import com.senla.finance.project.model.bank.BankAccount;
import org.springframework.stereotype.Component;

@Component
public interface BankDao {
    void persist(BankAccount bankAccount);

    void topUpAccount(String id, int sum);

    void updateAccount(BankAccount bankAccount);

    BankAccount getBankAccountById(String id);

    BankAccount getBankAccountByIdAndSecret(String id, String secret);
}
