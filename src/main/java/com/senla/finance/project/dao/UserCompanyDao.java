package com.senla.finance.project.dao;

import com.senla.finance.project.model.finnhub.UserCompany;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface UserCompanyDao {

    Optional<UserCompany> getUserCompany(String email, String symbol);

    void addCompany(UserCompany userCompany);
}
