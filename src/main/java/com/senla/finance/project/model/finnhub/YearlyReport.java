package com.senla.finance.project.model.finnhub;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Setter
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class YearlyReport {

    private String cik;
    private int year;
    private int quarter;
    private String form;
    private String startDate;
    private String endDate;
    private String filedDate;
    private String acceptedDate;
    private Map<String, Object> report;
}
