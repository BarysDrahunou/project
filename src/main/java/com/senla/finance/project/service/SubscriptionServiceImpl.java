package com.senla.finance.project.service;

import com.senla.finance.project.dao.UsersDao;
import com.senla.finance.project.dto.SubscriptionRequestDto;
import com.senla.finance.project.model.subscriptions.SubscriptionKind;
import com.senla.finance.project.model.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static com.senla.finance.project.utils.Constants.*;
import static com.senla.finance.project.utils.Constants.VALID_BANK_ACCOUNT_SECRET_PROPERTY;
import static com.senla.finance.project.utils.PropertiesValidator.*;
import static com.senla.finance.project.utils.PropertiesValidator.validated;

@Component
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private BankService bankService;

    @Override
    public void updateSubscription(String email, SubscriptionRequestDto subscriptionRequestDto) {
        SubscriptionKind subscriptionKind = subscriptionValidated(SUBSCRIPTION, subscriptionRequestDto.getSubscriptionKind());
        int days = numberValidated(DAYS, subscriptionRequestDto.getDays());

        User user = usersDao.findUserByEmail(email);
        String bankAccountId = validated(VALID_BANK_ACCOUNT_ID_PROPERTY, subscriptionRequestDto.getId());
        String bankAccountSecret = validated(VALID_BANK_ACCOUNT_SECRET_PROPERTY, subscriptionRequestDto.getSecret());


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
