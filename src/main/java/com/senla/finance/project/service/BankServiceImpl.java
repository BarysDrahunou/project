package com.senla.finance.project.service;

import com.senla.finance.project.dao.BankDao;
import com.senla.finance.project.dto.BankAccountDto;
import com.senla.finance.project.exceptions.NotEnoughMoneyException;
import com.senla.finance.project.model.bank.BankAccount;
import com.senla.finance.project.model.currency.Balance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.senla.finance.project.utils.Constants.*;
import static com.senla.finance.project.utils.PropertiesValidator.validated;

@Component
public class BankServiceImpl implements BankService {

    @Autowired
    private BankDao bankDao;

    @Override
    public void createAccount(BankAccountDto bankAccountDto) {
        BankAccount bankAccount = BankAccount.builder()
                .firstName(validated(FIRST_NAME_PROPERTY, bankAccountDto.getFirstName()))
                .lastName(validated(LAST_NAME_PROPERTY, bankAccountDto.getLastName()))
                .id(validated(VALID_BANK_ACCOUNT_ID_PROPERTY, bankAccountDto.getId()))
                .secret(validated(VALID_BANK_ACCOUNT_SECRET_PROPERTY, bankAccountDto.getSecret()))
                .balance(new Balance())
                .build();

        bankDao.persist(bankAccount);
    }

    @Override
    public void topUpAccount(String id, int sum) {
        bankDao.topUpAccount(id, sum);
    }

    @Override
    public void withdraw(String id, String secret, int sum) {
        BankAccount bankAccount = bankDao.getBankAccountByIdAndSecret(id, secret);

        Balance balance = bankAccount.getBalance();
        if (balance.getBalance() < sum) {
            throw new NotEnoughMoneyException(NOT_ENOUGH_MONEY_EXCEPTION);
        }

        bankAccount.getBalance().withdraw(sum);

        bankDao.updateAccount(bankAccount);
    }
}
