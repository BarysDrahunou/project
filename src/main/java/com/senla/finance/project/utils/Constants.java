package com.senla.finance.project.utils;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

import static java.lang.String.format;

@UtilityClass
public class Constants {

    public static final String FEIGN_CLIENT_NAME = "feign-client";

    public static final String SYMBOL = "symbol";
    public static final String METRIC = "metric";
    public static final String EXCHANGE = "exchange";
    public static final String TOKEN = "token";
    public static final String DATA = "data";
    public static final String STOCK_METRIC = "stock/metric";
    public static final String STOCK_SYMBOL = "stock/symbol";
    public static final String QUOTE = "quote";
    public static final String STOCK_FINANCIALS_REPORTED = "stock/financials-reported";

    public static final String REFRESH_COMPANIES_CRON_EXPRESSION = "0 0 8 * * *";
    public static final String US = "US";
    public static final String ALL = "all";

    public static final String USERS_TABLE_NAME = "Users";

    public static final String YEARLY_WEEK_HIGH = "52WeekHigh";
    public static final String YEARLY_WEEK_HIGH_DATE = "52WeekHighDate";
    public static final String YEARLY_WEEK_LOW = "52WeekLow";
    public static final String YEARLY_WEEK_LOW_DATE = "52WeekLowDate";
    public static final String YEARLY_WEEK_PRICE_RETURN_DAILY = "52WeekPriceReturnDaily";

    public static final long INITIAL_BALANCE = 0L;
    public static final int PARAMETER_NUMBER = 1;

    public static final String FIND_ALL_USERS_QUERY = "SELECT u FROM User u";
    public static final String FIND_USER_BY_EMAIL_QUERY = format("SELECT u FROM User u WHERE u.email LIKE ?%s", PARAMETER_NUMBER);
    public static final String GET_ALL_COMPANIES_SYMBOLS_QUERY = "SELECT c.symbol FROM Company c";

    public static final int DEFAULT_EXPIRATION_DATE_YEAR = 3000;
    public static final int DEFAULT_EXPIRATION_DATE_MONTH = 1;
    public static final int DEFAULT_EXPIRATION_DATE_DAY = 1;
    public static final int DEFAULT_EXPIRATION_DATE_HOUR = 0;
    public static final int DEFAULT_EXPIRATION_DATE_MINUTE = 0;
    public static final LocalDateTime DISABLED_SUBSCRIPTION_EXPIRATION_DATE = LocalDateTime
            .of(DEFAULT_EXPIRATION_DATE_YEAR, DEFAULT_EXPIRATION_DATE_MONTH,
            DEFAULT_EXPIRATION_DATE_DAY, DEFAULT_EXPIRATION_DATE_HOUR, DEFAULT_EXPIRATION_DATE_MINUTE);

    public static final String FIRST_NAME_PROPERTY = "firstName";
    public static final String LAST_NAME_PROPERTY = "lastName";
    public static final String EMAIL_PROPERTY = "email";
    public static final String PASSWORD_PROPERTY = "password";
    public static final String VALID_EMAIL_MESSAGE = "Email should be valid";

    public static final String ADMIN_ROLE= "ADMIN";
    public static final String ROLE= "role";

    public static final String USERS_PATTERN= "/users/**";

    public static final String USER_ALREADY_EXISTS_EXCEPTION = "User with an email %s already exists";
    public static final String USER_NOT_FOUND_EXCEPTION = "User with an email %s does not exist";
    public static final String PROPERTY_NOT_VALID_EXCEPTION = "Given property '%s' is absent or empty, but required.";
    public static final String ROLE_NOT_VALID_EXCEPTION = "Given role '%s' is incorrect, only 'ADMIN' and 'USER' roles are allowed.";
}
