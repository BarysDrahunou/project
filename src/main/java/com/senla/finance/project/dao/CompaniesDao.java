package com.senla.finance.project.dao;

import com.senla.finance.project.model.finnhub.Company;
import com.senla.finance.project.model.users.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface CompaniesDao {
    void persist(User user);

    void mergeAll(List<Company> companies);

    Optional<Company> findCompanyBySymbol(String symbol);

    List<String> getAllCompaniesSymbols();
}
