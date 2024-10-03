package com.senla.finance.project.model.finnhub;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static com.senla.finance.project.utils.Constants.*;

@Setter
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class YearlyMetrics {

    @Id
    private String symbol;
    @JsonProperty(value = YEARLY_WEEK_HIGH)
    private Double high;
    @JsonProperty(value = YEARLY_WEEK_HIGH_DATE)
    private String highDate;
    @JsonProperty(value = YEARLY_WEEK_LOW)
    private Double low;
    @JsonProperty(value = YEARLY_WEEK_LOW_DATE)
    private String lowDate;
    @JsonProperty(value = YEARLY_WEEK_PRICE_RETURN_DAILY)
    private Double priceReturnDaily;
}
