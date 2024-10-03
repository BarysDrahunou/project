package com.senla.finance.project.model.finnhub;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyReport {

    private String symbol;
    private List<YearlyReport> data;
}
