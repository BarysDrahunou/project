package com.senla.finance.project.dto;

import com.senla.finance.project.model.subscriptions.Subscription;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SubscriptionResponseDto {
    private String userName;
    private String email;
    private Subscription subscription;
    private String subscriptionExpiration;
}
