package com.senla.finance.project.dao;

import com.senla.finance.project.model.finnhub.UserCompany;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.senla.finance.project.utils.Constants.*;

@Component
@Transactional
public class UserCompanyDaoImpl implements UserCompanyDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<UserCompany> getUserCompany(String email, String symbol) {
        List userCompanies = entityManager.createQuery(FIND_USER_COMPANY_BY_SYMBOL)
                .setParameter(PARAMETER_NUMBER, email)
                .setParameter(PARAMETER_NUMBER_TWO, symbol).getResultList();
        return userCompanies.stream().findFirst();
    }

    @Override
    public void addCompany(UserCompany userCompany) {
        entityManager.persist(userCompany);
    }
}
