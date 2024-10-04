package com.senla.finance.project.dao;

import com.senla.finance.project.model.finnhub.Company;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CompaniesDao {
    void persist(String userEmail, String symbol);

    void mergeAll(List<Company> entities);

    Company findCompanyBySymbol(String symbol);

    List<String> getAllCompaniesSymbols();
}
