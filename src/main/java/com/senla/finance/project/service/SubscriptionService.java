package com.senla.finance.project.service;

import com.senla.finance.project.model.subscriptions.SubscriptionKind;
import com.senla.finance.project.model.users.User;
import org.springframework.stereotype.Component;

@Component
public interface SubscriptionService {

    void updateSubscription(User user, SubscriptionKind subscriptionKind, int days, String bankAccountId, String bankAccountSecret);
}
