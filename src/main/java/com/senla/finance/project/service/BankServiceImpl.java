package com.senla.finance.project.service;

import com.senla.finance.project.dao.BankDao;
import com.senla.finance.project.dto.AccountTopUpDto;
import com.senla.finance.project.dto.BankAccountDto;
import com.senla.finance.project.exceptions.BankAccountNotFoundException;
import com.senla.finance.project.exceptions.NotEnoughMoneyException;
import com.senla.finance.project.exceptions.UserAlreadyExistsException;
import com.senla.finance.project.model.bank.BankAccount;
import com.senla.finance.project.model.currency.Balance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.senla.finance.project.utils.Constants.*;
import static com.senla.finance.project.utils.PropertiesValidator.numberValidated;
import static com.senla.finance.project.utils.PropertiesValidator.validated;
import static java.lang.String.format;

@Component
public class BankServiceImpl implements BankService {

    @Autowired
    private BankDao bankDao;

    @Override
    public void createAccount(BankAccountDto bankAccountDto) {
        String firstName = validated(FIRST_NAME_PROPERTY, bankAccountDto.getFirstName());
        String lastName = validated(LAST_NAME_PROPERTY, bankAccountDto.getLastName());
        String id = validated(VALID_BANK_ACCOUNT_ID_PROPERTY, bankAccountDto.getId());
        String secret = validated(VALID_BANK_ACCOUNT_SECRET_PROPERTY, bankAccountDto.getSecret());

        Optional<BankAccount> existingAccount = bankDao.getBankAccountById(id);

        existingAccount.ifPresent(s -> {
            throw new UserAlreadyExistsException(format(BANK_ACCOUNT_ALREADY_EXISTS_EXCEPTION, id));
        });

        BankAccount bankAccount = BankAccount.builder()
                .firstName(firstName)
                .lastName(lastName)
                .id(id)
                .secret(secret)
                .balance(new Balance())
                .build();

        bankDao.persist(bankAccount);
    }

    @Override
    public void topUpAccount(AccountTopUpDto accountTopUpDto) {
        String id = accountTopUpDto.getId();
        int sum = numberValidated(VALID_BANK_ACCOUNT_BALANCE_PROPERTY, accountTopUpDto.getSum());

        Optional<BankAccount> existingAccount = bankDao.getBankAccountById(id);

        BankAccount bankAccount = existingAccount.orElseThrow(
                () -> new BankAccountNotFoundException(format(BANK_ACCOUNT_DOES_NOT_EXIST_EXCEPTION, id)));

        Balance balance = bankAccount.getBalance();
        balance.topUp(sum);
        bankAccount.setBalance(balance);

        bankDao.updateAccount(bankAccount);
    }

    @Override
    public void withdraw(String id, String secret, int sum) {
        Optional<BankAccount> existingAccount = bankDao.getBankAccountByIdAndSecret(id, secret);

        BankAccount bankAccount = existingAccount.orElseThrow(
                () -> new BankAccountNotFoundException(BANK_ACCOUNT_CREDENTIALS_INVALID_EXCEPTION));

        Balance balance = bankAccount.getBalance();
        if (balance.getBalance() < sum) {
            throw new NotEnoughMoneyException(NOT_ENOUGH_MONEY_EXCEPTION);
        }

        bankAccount.getBalance().withdraw(sum);

        bankDao.updateAccount(bankAccount);
    }
}
