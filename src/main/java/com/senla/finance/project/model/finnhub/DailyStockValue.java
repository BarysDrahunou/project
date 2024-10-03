package com.senla.finance.project.model.finnhub;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class DailyStockValue {

    @Id
    private String symbol;
    private String description;
    private double c;
    private double d;
    private double dp;
    private double l;
    private double h;
    private double o;
    private double pc;
    private double t;
}
