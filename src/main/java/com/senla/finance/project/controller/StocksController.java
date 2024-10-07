package com.senla.finance.project.controller;

import com.senla.finance.project.dto.CompanyRequestDto;
import com.senla.finance.project.model.finnhub.CompanyReport;
import com.senla.finance.project.model.finnhub.YearlyMetrics;
import com.senla.finance.project.service.CompanyService;
import com.senla.finance.project.service.FinnhubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stocks")
public class StocksController {

    @Autowired
    private FinnhubService finnhubService;

    @Autowired
    private CompanyService companyService;

    @GetMapping(value = "/yearly")
    public YearlyMetrics getYearlyMetrics(@RequestBody CompanyRequestDto companyRequestDto) {
        return finnhubService.getYearlyMetrics(companyRequestDto.getSymbol());
    }

    @GetMapping(value = "/report")
    public CompanyReport getCompanyFullReport(@RequestBody CompanyRequestDto companyRequestDto) {
        return finnhubService.getCompanyFullReport(companyRequestDto.getSymbol());
    }

    @PostMapping(value = "/add")
    public void addCompany(@RequestBody CompanyRequestDto companyRequestDto) {
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        companyService.addCompany(username, companyRequestDto.getSymbol());
    }

}
