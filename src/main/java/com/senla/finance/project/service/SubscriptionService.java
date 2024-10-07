package com.senla.finance.project.service;

import com.senla.finance.project.dto.SubscriptionRequestDto;
import org.springframework.stereotype.Component;

@Component
public interface SubscriptionService {

    void updateSubscription(String email, SubscriptionRequestDto subscriptionRequestDto);
}
