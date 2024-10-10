package com.senla.finance.project.model.subscriptions;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SubscriptionAuthorities {

    @Id
    @GeneratedValue
    private int id;
    @Column(name = "subscription")
    private String subscriptionName;
    @Column(name = "authority")
    private String authorityName;

}
