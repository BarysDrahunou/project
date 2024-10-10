package com.senla.finance.project.mapper;

import com.senla.finance.project.dto.SubscriptionResponseDto;
import com.senla.finance.project.model.subscriptions.Subscription;
import com.senla.finance.project.model.users.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.senla.finance.project.utils.Constants.*;
import static java.lang.String.format;

public class SubscriptionMapper {

    public SubscriptionResponseDto mapFromUser(User user) {
        return SubscriptionResponseDto.builder()
                .userName(format(MAPPER_NAME_FORMAT, user.getFirstName(), user.getLastName()))
                .email(user.getEmail())
                .build();
    }

    private String formatExpirationDate(Subscription subscription, LocalDateTime expirationDate) {
        if (subscription.getSubscriptionPrice() == DEFAULT_SUBSCRIPTION_PRICE) {
            return DEFAULT_SUBSCRIPTION_EXPIRATION;
        }
        return expirationDate.format(DateTimeFormatter.ofPattern(MAPPER_DATE_FORMAT));
    }
}
