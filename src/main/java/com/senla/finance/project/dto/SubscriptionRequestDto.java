package com.senla.finance.project.dto;

import lombok.Getter;

@Getter
public class SubscriptionRequestDto {
    private String subscriptionKind;
    private String days;
    private String id;
    private String secret;
}
