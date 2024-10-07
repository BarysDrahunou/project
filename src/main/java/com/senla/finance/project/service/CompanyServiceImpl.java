package com.senla.finance.project.service;

import com.senla.finance.project.dao.CompaniesDao;
import com.senla.finance.project.dao.UsersDao;
import com.senla.finance.project.exceptions.CompanyNotFoundException;
import com.senla.finance.project.model.finnhub.Company;
import com.senla.finance.project.model.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.senla.finance.project.utils.Constants.COMPANY_NOT_FOUND_EXCEPTION;
import static java.lang.String.format;

@Component
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompaniesDao companiesDao;
    @Autowired
    private UsersDao usersDao;

    @Override
    public void addCompany(String symbol) {
        String userEmail = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = usersDao.findUserByEmail(userEmail).get();
        Optional<Company> retrievedCompany = companiesDao.findCompanyBySymbol(symbol);

        Company company = retrievedCompany.orElseThrow(
                () -> new CompanyNotFoundException(format(COMPANY_NOT_FOUND_EXCEPTION, symbol)));

        user.getCompanies().add(company);

        companiesDao.persist(user);
    }
}
