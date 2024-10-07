package com.senla.finance.project.service;

import com.senla.finance.project.model.bank.BankAccount;
import org.springframework.stereotype.Component;

@Component
public interface BankService {

    void createAccount(BankAccount bankAccount);

    void topUpAccount(String id, int sum);

    void withdraw(String id, String secret, int sum);
}
