package com.senla.finance.project.dao;

import com.senla.finance.project.model.finnhub.Company;
import com.senla.finance.project.model.users.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.senla.finance.project.utils.Constants.*;

@Component
@Transactional
public class CompaniesDaoImpl implements CompaniesDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UsersDao usersDao;

    @Override
    public void persist(User user) {
        entityManager.persist(user);
    }

    @Override
    public void mergeAll(List<Company> companies) {
        companies.forEach(entityManager::merge);
    }

    @Override
    public Optional<Company> findCompanyBySymbol(String symbol) {
        List<Company> companies = entityManager.createQuery(FIND_COMPANY_BY_SYMBOL_QUERY)
                .setParameter(PARAMETER_NUMBER, symbol).getResultList();
        return companies.stream().findFirst();
    }

    @Override
    public List<String> getAllCompaniesSymbols() {
        return entityManager.createQuery(GET_ALL_COMPANIES_SYMBOLS_QUERY).getResultList();
    }
}
