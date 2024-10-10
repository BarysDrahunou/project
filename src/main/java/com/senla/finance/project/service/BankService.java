package com.senla.finance.project.service;

import com.senla.finance.project.dto.AccountTopUpDto;
import com.senla.finance.project.dto.BankAccountDto;
import org.springframework.stereotype.Component;

@Component
public interface BankService {

    void createAccount(BankAccountDto bankAccountDto);

    void topUpAccount(AccountTopUpDto accountTopUpDto);

    void withdraw(String id, String secret, int sum);
}
