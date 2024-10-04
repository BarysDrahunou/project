package com.senla.finance.project.service;

import com.senla.finance.project.dao.CompaniesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompanyServiceImpl implements CompanyService{
    @Autowired
    private CompaniesDao companiesDao;

    @Override
    public void addCompany(String userEmail, String symbol) {
        companiesDao.persist(userEmail, symbol);
    }
}
