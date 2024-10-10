package com.senla.finance.project.dto;

import lombok.Getter;

@Getter
public class SubscriptionRequestDto {
    private String subscription;
    private String days;
    private String id;
    private String secret;
}
