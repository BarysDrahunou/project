package com.senla.finance.project.model.currency;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Embeddable
public class Balance {
    private long balance;

    public void topUp(long deposit){
        this.balance+=deposit;
    }
}
