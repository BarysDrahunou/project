package com.senla.finance.project.model.currency;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.ToString;

import static com.senla.finance.project.utils.Constants.INITIAL_BALANCE;

@Getter
@ToString
@Embeddable
public class Balance {
    private long balance;

    public Balance() {
        this.balance = INITIAL_BALANCE;
    }

    public void topUp(long deposit) {
        this.balance += deposit;
    }
}
