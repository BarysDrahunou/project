package com.senla.finance.project.model.finnhub;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class YearlyMetrics {
    @Id
    private String symbol;
    @JsonProperty(value = "52WeekHigh")
    private Double high;
    @JsonProperty(value = "52WeekHighDate")
    private String highDate;
    @JsonProperty(value = "52WeekLow")
    private Double low;
    @JsonProperty(value = "52WeekLowDate")
    private String lowDate;
    @JsonProperty(value = "52WeekPriceReturnDaily")
    private Double priceReturnDaily;
}
