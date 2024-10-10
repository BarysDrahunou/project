package com.senla.finance.project.model.finnhub;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import static com.senla.finance.project.utils.Constants.USERS_COMPANIES_TABLE_NAME;

@Entity
@Table(name = USERS_COMPANIES_TABLE_NAME)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCompany {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String userEmail;
    private String companySymbol;
}
