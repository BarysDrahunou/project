package com.senla.finance.project.model.subscriptions;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class SubscriptionLog {

    @Id
    @GeneratedValue
    private int id;
    private String userId;
    private String subscriptionName;
    private LocalDate expirationDate;
    private String logMessage;
    private LocalDate dayOfSubscription;

}
