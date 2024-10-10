package com.senla.finance.project.dao;

import com.senla.finance.project.model.subscriptions.SubscriptionLog;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class SubscriptionLogDaoImpl implements SubscriptionLogDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void logSubscription(SubscriptionLog subscriptionLog) {
        entityManager.persist(subscriptionLog);
    }
}
