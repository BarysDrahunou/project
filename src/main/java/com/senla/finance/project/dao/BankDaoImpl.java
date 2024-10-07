package com.senla.finance.project.dao;

import com.senla.finance.project.model.bank.BankAccount;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.senla.finance.project.utils.Constants.*;

@Component
@Transactional
public class BankDaoImpl implements BankDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void persist(BankAccount bankAccount) {
        entityManager.persist(bankAccount);
    }

    @Override
    public void updateAccount(BankAccount bankAccount) {
        entityManager.merge(bankAccount);
    }

    @Override
    public Optional<BankAccount> getBankAccountById(String id) {
        List<BankAccount> bankAccounts = (List<BankAccount>) entityManager.createQuery(FIND_USER_ACCOUNT_BY_ID_QUERY)
                .setParameter(PARAMETER_NUMBER, id).getResultList();

        return bankAccounts.stream().findFirst();
    }

    @Override
    public Optional<BankAccount> getBankAccountByIdAndSecret(String userId, String secret) {
        List<BankAccount> bankAccounts = (List<BankAccount>) entityManager.createQuery(FIND_USER_ACCOUNT_BY_ID_AND_SECRET_QUERY)
                .setParameter(PARAMETER_NUMBER, userId)
                .setParameter(PARAMETER_NUMBER_TWO, secret).getResultList();

        return bankAccounts.stream().findFirst();
    }
}
