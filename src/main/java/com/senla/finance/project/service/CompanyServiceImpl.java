package com.senla.finance.project.service;

import com.senla.finance.project.dao.CompaniesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class CompanyServiceImpl implements CompanyService{
    @Autowired
    private CompaniesDao companiesDao;

    @Override
    public void addCompany(String symbol) {
        String userEmail = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        companiesDao.persist(userEmail, symbol);
    }
}
