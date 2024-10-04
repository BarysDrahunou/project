package com.senla.finance.project.utils;

import com.senla.finance.project.exceptions.PropertyNotValidException;
import com.senla.finance.project.model.roles.Role;
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
