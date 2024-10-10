package com.senla.finance.project.utils;

import com.senla.finance.project.exceptions.PropertyNotValidException;
import com.senla.finance.project.model.roles.Role;
import com.senla.finance.project.model.subscriptions.Subscription;
import lombok.experimental.UtilityClass;

import static com.senla.finance.project.utils.Constants.*;
import static java.lang.String.format;

@UtilityClass
public class PropertiesValidator {

    public static String validated(String name, String value) {
        if (value != null && !value.isBlank()) {
            return value;
        } else {
            throw new PropertyNotValidException(format(PROPERTY_NOT_VALID_EXCEPTION, name));
        }
    }

    public static int numberValidated(String name, String value) {
        validated(name, value);
        int parsedValue;

        try {
            parsedValue = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new PropertyNotValidException(format(PROPERTY_SHOULD_BE_A_NUMBER_EXCEPTION, name));
        }
        if (parsedValue > 0) {
            return parsedValue;
        } else {
            throw new PropertyNotValidException(format(NUMBER_NOT_VALID_EXCEPTION, name));
        }
    }

    public static Subscription subscriptionValidated(String name, String value) {
//        validated(name, value);
//        SubscriptionKind subscriptionKind;
//        try {
//            subscriptionKind = SubscriptionKind.valueOf(value);
//            if (SubscriptionKind.valueOf(value).equals(SubscriptionKind.DISABLED)) {
//                throw new PropertyNotValidException(format(SUBSCRIPTION_VALUE_NOT_VALID_EXCEPTION, value));
//            }
//            return subscriptionKind;
//        } catch (IllegalArgumentException e) {
//            throw new PropertyNotValidException(format(SUBSCRIPTION_VALUE_NOT_VALID_EXCEPTION, value));
//        }
        return null;
    }

    public static Role roleValidated(String value) {
        try {
            return Role.valueOf(validated(ROLE, value));
        } catch (IllegalArgumentException e) {
            throw new PropertyNotValidException(format(ROLE_NOT_VALID_EXCEPTION, value));
        }
    }

    public static String updatePropertyIfNotBlank(String oldProperty, String newProperty) {
        if (newProperty == null || newProperty.isBlank()) {
            return oldProperty;
        }
        return newProperty;
    }
}
