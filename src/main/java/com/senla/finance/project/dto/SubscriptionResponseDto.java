package com.senla.finance.project.dto;

import com.senla.finance.project.model.subscriptions.SubscriptionKind;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SubscriptionResponseDto {
    private String userName;
    private String email;
    private SubscriptionKind subscriptionKind;
    private String subscriptionExpiration;
}
