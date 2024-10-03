package com.senla.finance.project.service;

import com.senla.finance.project.model.finnhub.CompanyReport;
import com.senla.finance.project.model.finnhub.YearlyMetrics;

public interface FinnhubService {

    void refreshCompaniesList();

    YearlyMetrics getYearlyMetrics(String symbol);

    CompanyReport getCompanyFullReport(String symbol);
}
