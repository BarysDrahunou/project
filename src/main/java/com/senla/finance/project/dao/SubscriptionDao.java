package com.senla.finance.project.dao;

import com.senla.finance.project.model.subscriptions.Subscription;
import com.senla.finance.project.model.subscriptions.SubscriptionAuthorities;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface SubscriptionDao {

    void deleteAllSubscriptions();

    void addAllSubscriptions(List<Subscription> subscriptions);

    void deleteAllSubscriptionAuthoritiesRelations();

    void addAllSubscriptionAuthorities(List<SubscriptionAuthorities> subscriptions);

    Optional<Subscription> getDefaultSubscription();
}
