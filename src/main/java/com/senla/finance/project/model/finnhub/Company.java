package com.senla.finance.project.model.finnhub;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Company {

    @Id
    private String symbol;
    private String currency;
    private String description;
    private String type;
}
