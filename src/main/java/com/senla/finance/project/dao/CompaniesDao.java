package com.senla.finance.project.dao;

import com.senla.finance.project.model.finnhub.Company;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CompaniesDao {


    void mergeAll(List<Company> entities);

    List<String> getAllCompaniesSymbols();
}
