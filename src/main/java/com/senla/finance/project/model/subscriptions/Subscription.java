package com.senla.finance.project.model.subscriptions;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Subscription {

    @Id
    @GeneratedValue
    private int id;
    private String subscriptionName;
    private int subscriptionPrice;
}
