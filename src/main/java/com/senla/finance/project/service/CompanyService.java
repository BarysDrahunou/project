package com.senla.finance.project.service;

import org.springframework.stereotype.Component;

@Component
public interface CompanyService {

    void addCompany(String userEmail, String symbol);
}
