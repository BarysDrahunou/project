package com.senla.finance.project.dao;

import com.senla.finance.project.exceptions.CompanyNotFoundException;
import com.senla.finance.project.exceptions.UserNotFoundException;
import com.senla.finance.project.model.finnhub.Company;
import com.senla.finance.project.model.users.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.senla.finance.project.utils.Constants.*;
import static java.lang.String.format;

@Component
@Transactional
public class CompaniesDaoImpl implements CompaniesDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UsersDao usersDao;

    @Override
    public void persist(String userEmail, String symbol) {
        User user = usersDao.findUserByEmail(userEmail);
        Company company = findCompanyBySymbol(symbol);
        user.getCompanies().add(company);
        entityManager.persist(user);

    }

    @Override
    public void mergeAll(List<Company> companies) {
        companies.forEach(entityManager::merge);
    }

    @Override
    public Company findCompanyBySymbol(String symbol) {
        List companies = getCompanyBySymbol(symbol);
        if (!companies.isEmpty()) {
            return (Company) companies.getFirst();
        }

        throw new CompanyNotFoundException(format(COMPANY_NOT_FOUND_EXCEPTION, symbol));
    }

    @Override
    public List<String> getAllCompaniesSymbols() {
        return entityManager.createQuery(GET_ALL_COMPANIES_SYMBOLS_QUERY).getResultList();
    }

    private List getCompanyBySymbol(String symbol) {
        return entityManager.createQuery(FIND_COMPANY_BY_SYMBOL_QUERY)
                .setParameter(PARAMETER_NUMBER, symbol).getResultList();
    }
}
