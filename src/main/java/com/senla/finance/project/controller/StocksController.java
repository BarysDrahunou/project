package com.senla.finance.project.controller;

import com.senla.finance.project.dto.CompanyRequestDto;
import com.senla.finance.project.model.finnhub.CompanyReport;
import com.senla.finance.project.model.finnhub.YearlyMetrics;
import com.senla.finance.project.service.FinnhubService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stocks")
public class StocksController {
    @Autowired
    private FinnhubService finnhubService;

    private static final Logger LOGGER = LogManager.getLogger(StocksController.class);

    @GetMapping(value = "/yearly")
    public YearlyMetrics getYearlyMetrics(@RequestBody CompanyRequestDto companyRequestDto){
        return finnhubService.getYearlyMetrics(companyRequestDto.getSymbol());
    }

    @GetMapping(value = "/report")
    public CompanyReport getCompanyFullReport(@RequestBody CompanyRequestDto companyRequestDto){
        return finnhubService.getCompanyFullReport(companyRequestDto.getSymbol());
    }

}
