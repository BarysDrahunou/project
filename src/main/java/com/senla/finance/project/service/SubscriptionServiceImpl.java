package com.senla.finance.project.service;

import com.senla.finance.project.dao.UsersDao;
import com.senla.finance.project.model.subscriptions.SubscriptionKind;
import com.senla.finance.project.model.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private BankService bankService;

    @Override
    public void updateSubscription(User user, SubscriptionKind subscriptionKind, int days, String bankAccountId, String bankAccountSecret) {
        int sum = subscriptionKind.getPrice() * days;
        bankService.withdraw(bankAccountId, bankAccountSecret, sum);

        if (user.getExpirationDate().isBefore(LocalDateTime.now())) {
            user.setExpirationDate(LocalDateTime.now());
        }

        if (user.getSubscriptionKind().equals(subscriptionKind)) {
            user.setExpirationDate(user.getExpirationDate().plusDays(days));
        } else {
            user.setSubscriptionKind(subscriptionKind);
            user.setExpirationDate(LocalDateTime.now().plusDays(days));
        }

        usersDao.merge(user);
    }
}
