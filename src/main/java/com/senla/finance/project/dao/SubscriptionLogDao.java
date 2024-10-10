package com.senla.finance.project.dao;

import com.senla.finance.project.model.subscriptions.SubscriptionLog;
import org.springframework.stereotype.Component;

@Component
public interface SubscriptionLogDao {

    void logSubscription(SubscriptionLog subscriptionLog);
}
