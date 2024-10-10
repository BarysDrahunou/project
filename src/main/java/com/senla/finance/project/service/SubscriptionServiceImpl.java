package com.senla.finance.project.service;

import com.senla.finance.project.dao.SubscriptionDao;
import com.senla.finance.project.dao.SubscriptionLogDao;
import com.senla.finance.project.dao.UsersDao;
import com.senla.finance.project.dto.SubscriptionRequestDto;
import com.senla.finance.project.exceptions.SubscriptionNotFoundException;
import com.senla.finance.project.model.subscriptions.Subscription;
import com.senla.finance.project.model.subscriptions.SubscriptionAuthorities;
import com.senla.finance.project.model.subscriptions.SubscriptionLog;
import com.senla.finance.project.model.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.senla.finance.project.utils.Constants.*;
import static com.senla.finance.project.utils.Constants.VALID_BANK_ACCOUNT_SECRET_PROPERTY;
import static com.senla.finance.project.utils.PropertiesValidator.*;
import static com.senla.finance.project.utils.PropertiesValidator.validated;
import static java.lang.String.format;

@Component
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private SubscriptionDao subscriptionDao;

    @Autowired
    private SubscriptionLogDao subscriptionLogDao;

    @Autowired
    private BankService bankService;

    @Override
    public void updateSubscription(String email, SubscriptionRequestDto subscriptionRequestDto) {
//        Subscription subscription = subscriptionValidated(SUBSCRIPTION, subscriptionRequestDto.getSubscriptionKind());
//        int days = numberValidated(DAYS, subscriptionRequestDto.getDays());
//
//        User user = usersDao.findUserByEmail(email).get();
//        String bankAccountId = validated(VALID_BANK_ACCOUNT_ID_PROPERTY, subscriptionRequestDto.getId());
//        String bankAccountSecret = validated(VALID_BANK_ACCOUNT_SECRET_PROPERTY, subscriptionRequestDto.getSecret());
//
//
//        int sum = subscriptionKind.getPrice() * days;
//        bankService.withdraw(bankAccountId, bankAccountSecret, sum);
//
//        if (user.getExpirationDate().isBefore(LocalDateTime.now())) {
//            user.setExpirationDate(LocalDateTime.now());
//        }
//
//        if (user.getSubscriptionKind().equals(subscriptionKind)) {
//            user.setExpirationDate(user.getExpirationDate().plusDays(days));
//        } else {
//            user.setSubscriptionKind(subscriptionKind);
//            user.setExpirationDate(LocalDateTime.now().plusDays(days));
//        }
//
//        usersDao.merge(user);
    }

    @Override
    public Subscription getDefaultSubscription() {
        return subscriptionDao.getDefaultSubscription()
                .orElseThrow(() -> new SubscriptionNotFoundException(DEFAULT_SUBSCRIPTION_NOT_FOUND_EXCEPTION));
    }

    @Override
    public void deleteAllSubscriptions() {
        subscriptionDao.deleteAllSubscriptions();
    }

    @Override
    public void addAllSubscriptions(List<Subscription> subscriptions) {
        subscriptionDao.addAllSubscriptions(subscriptions);
    }

    @Override
    public void deleteAllSubscriptionAuthoritiesRelations() {
        subscriptionDao.deleteAllSubscriptionAuthoritiesRelations();
    }

    @Override
    public void addAllSubscriptionsAuthorities(List<SubscriptionAuthorities> subscriptionAuthorities) {
        subscriptionDao.addAllSubscriptionAuthorities(subscriptionAuthorities);
    }

    @Override
    public void logNewDefaultSubscription(String email) {
        SubscriptionLog defaultSubscriptionLog = SubscriptionLog.builder()
                .userId(email)
                .subscriptionName(getDefaultSubscription().getSubscriptionName())
                .expirationDate(DEFAULT_SUBSCRIPTION_EXPIRATION_DATE)
                .dayOfSubscription(LocalDate.now())
                .logMessage(format(DEFAULT_SUBSCRIPTION_LOG, email, getDefaultSubscription().getSubscriptionName(), DEFAULT_SUBSCRIPTION_EXPIRATION_DATE))
                .build();

        subscriptionLogDao.logSubscription(defaultSubscriptionLog);
    }
}
