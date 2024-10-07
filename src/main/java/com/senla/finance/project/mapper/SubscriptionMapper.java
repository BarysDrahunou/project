package com.senla.finance.project.mapper;

import com.senla.finance.project.dto.SubscriptionResponseDto;
import com.senla.finance.project.model.subscriptions.SubscriptionKind;
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
                .subscriptionKind(user.getSubscriptionKind())
                .subscriptionExpiration(formatExpirationDate(user.getSubscriptionKind(), user.getExpirationDate()))
                .build();
    }

    private String formatExpirationDate(SubscriptionKind subscriptionKind, LocalDateTime expirationDate) {
        if (subscriptionKind.equals(SubscriptionKind.DISABLED)) {
            return NEVER;
        }
        return expirationDate.format(DateTimeFormatter.ofPattern(MAPPER_DATE_FORMAT));
    }
}
