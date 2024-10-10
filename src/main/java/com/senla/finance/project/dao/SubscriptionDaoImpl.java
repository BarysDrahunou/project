package com.senla.finance.project.dao;

import com.senla.finance.project.model.subscriptions.Subscription;
import com.senla.finance.project.model.subscriptions.SubscriptionAuthorities;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.senla.finance.project.utils.Constants.*;

@Component
@Transactional
public class SubscriptionDaoImpl implements SubscriptionDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void deleteAllSubscriptions() {
        entityManager.createQuery(DELETE_ALL_SUBSCRIPTIONS_QUERY).executeUpdate();
    }

    @Override
    public void addAllSubscriptions(List<Subscription> subscriptions) {
        subscriptions.forEach(entityManager::persist);
    }

    @Override
    public void deleteAllSubscriptionAuthoritiesRelations() {
        entityManager.createQuery(DELETE_ALL_SUBSCRIPTIONS_AUTHORITIES_QUERY).executeUpdate();
    }

    @Override
    public void addAllSubscriptionAuthorities(List<SubscriptionAuthorities> subscriptionAuthorities) {
        subscriptionAuthorities.forEach(entityManager::persist);
    }

    @Override
    public Optional<Subscription> getDefaultSubscription() {
        List<Subscription> subscriptions = (List<Subscription>) entityManager.createQuery(FIND_DEFAULT_SUBSCRIPTION_QUERY)
                .getResultList();

        return subscriptions.stream().findFirst();
    }
}
