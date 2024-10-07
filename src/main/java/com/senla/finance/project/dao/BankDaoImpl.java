package com.senla.finance.project.dao;

import com.senla.finance.project.exceptions.BankAccountNotFoundException;
import com.senla.finance.project.exceptions.UserAlreadyExistsException;
import com.senla.finance.project.model.bank.BankAccount;
import com.senla.finance.project.model.currency.Balance;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.senla.finance.project.utils.Constants.*;
import static java.lang.String.format;

@Component
@Transactional
public class BankDaoImpl implements BankDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void persist(BankAccount bankAccount) {
        String userId = bankAccount.getId();

        BankAccount retrievedBankAccount = getBankAccountById(userId);

        if (retrievedBankAccount == null) {
            entityManager.persist(bankAccount);
        } else {
            throw new UserAlreadyExistsException(format(BANK_ACCOUNT_ALREADY_EXISTS_EXCEPTION, userId));
        }
    }

    @Override
    public void topUpAccount(String userId, int sum) {
        BankAccount retrievedBankAccount = getBankAccountById(userId);

        if (retrievedBankAccount != null) {
            Balance balance = retrievedBankAccount.getBalance();
            balance.topUp(sum);
            retrievedBankAccount.setBalance(balance);
            entityManager.merge(retrievedBankAccount);
        } else {
            throw new BankAccountNotFoundException(format(BANK_ACCOUNT_DOES_NOT_EXIST_EXCEPTION, userId));
        }
    }

    @Override
    public void updateAccount(BankAccount bankAccount) {
        entityManager.merge(bankAccount);
    }

    @Override
    public BankAccount getBankAccountById(String userId) {
        List<BankAccount> bankAccounts = (List<BankAccount>) entityManager.createQuery(FIND_USER_ACCOUNT_BY_ID_QUERY)
                .setParameter(PARAMETER_NUMBER, userId).getResultList();
        if (!bankAccounts.isEmpty()) {
            return bankAccounts.getFirst();
        } else {
            return null;
        }
    }

    @Override
    public BankAccount getBankAccountByIdAndSecret(String userId, String secret) {
        List<BankAccount> bankAccounts = (List<BankAccount>) entityManager.createQuery(FIND_USER_ACCOUNT_BY_ID_AND_SECRET_QUERY)
                .setParameter(PARAMETER_NUMBER, userId)
                .setParameter(PARAMETER_NUMBER_TWO, secret).getResultList();
        if (!bankAccounts.isEmpty()) {
            return bankAccounts.getFirst();
        } else {
            return null;
        }
    }
}
