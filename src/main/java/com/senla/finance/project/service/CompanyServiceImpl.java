package com.senla.finance.project.service;

import com.senla.finance.project.dao.CompaniesDao;
import com.senla.finance.project.dao.UserCompanyDao;
import com.senla.finance.project.exceptions.CompanyAlreadyAddedException;
import com.senla.finance.project.exceptions.CompanyNotFoundException;
import com.senla.finance.project.model.finnhub.Company;
import com.senla.finance.project.model.finnhub.UserCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.senla.finance.project.utils.Constants.*;
import static com.senla.finance.project.utils.PropertiesValidator.validated;
import static java.lang.String.format;

@Component
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompaniesDao companiesDao;
    @Autowired
    private UserCompanyDao userCompanyDao;

    @Override
    public void addCompany(String symbol) {
        String userEmail = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Optional<Company> retrievedCompany = companiesDao.findCompanyBySymbol(validated(SYMBOL, symbol));

        retrievedCompany.orElseThrow(
                () -> new CompanyNotFoundException(format(COMPANY_NOT_FOUND_EXCEPTION, symbol)));

        userCompanyDao.getUserCompany(userEmail, symbol).ifPresent(s -> {
            throw new CompanyAlreadyAddedException(format(COMPANY_ALREADY_ADDED_EXCEPTION, symbol));
        });

        UserCompany userCompany = UserCompany.builder()
                .userEmail(userEmail)
                .companySymbol(symbol)
                .build();

        userCompanyDao.addCompany(userCompany);
    }
}
