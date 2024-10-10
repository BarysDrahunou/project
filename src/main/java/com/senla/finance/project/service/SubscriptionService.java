package com.senla.finance.project.service;

import com.senla.finance.project.dto.SubscriptionRequestDto;
import com.senla.finance.project.model.subscriptions.Subscription;
import com.senla.finance.project.model.subscriptions.SubscriptionAuthorities;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SubscriptionService {

    void updateSubscription(String email, SubscriptionRequestDto subscriptionRequestDto);

    Subscription getDefaultSubscription();

    void deleteAllSubscriptions();

    void addAllSubscriptions(List<Subscription> subscriptions);

    void deleteAllSubscriptionAuthoritiesRelations();

    void addAllSubscriptionsAuthorities(List<SubscriptionAuthorities> subscriptionAuthorities);

    void logNewDefaultSubscription(String email);
}
