package com.senla.finance.project.utils;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;

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
    public static final String USERS_COMPANIES_TABLE_NAME = "user_company";

    public static final String YEARLY_WEEK_HIGH = "52WeekHigh";
    public static final String YEARLY_WEEK_HIGH_DATE = "52WeekHighDate";
    public static final String YEARLY_WEEK_LOW = "52WeekLow";
    public static final String YEARLY_WEEK_LOW_DATE = "52WeekLowDate";
    public static final String YEARLY_WEEK_PRICE_RETURN_DAILY = "52WeekPriceReturnDaily";

    public static final long INITIAL_BALANCE = 0L;
    public static final int DEFAULT_SUBSCRIPTION_PRICE = 0;
    public static final String DEFAULT_SUBSCRIPTION_EXPIRATION = "never";
    public static final int PARAMETER_NUMBER = 1;
    public static final int PARAMETER_NUMBER_TWO = 2;

    public static final String FIND_ALL_USERS_QUERY = "SELECT u FROM User u";
    public static final String FIND_USER_BY_EMAIL_QUERY = format("SELECT u FROM User u WHERE u.email LIKE ?%s", PARAMETER_NUMBER);
    public static final String GET_ALL_COMPANIES_SYMBOLS_QUERY = "SELECT c.symbol FROM Company c";
    public static final String FIND_COMPANY_BY_SYMBOL_QUERY = format("SELECT c FROM Company c WHERE c.symbol LIKE ?%s", PARAMETER_NUMBER);
    public static final String FIND_USER_ACCOUNT_BY_ID_QUERY = format("SELECT a FROM BankAccount a WHERE a.id LIKE ?%s", PARAMETER_NUMBER);
    public static final String FIND_USER_ACCOUNT_BY_ID_AND_SECRET_QUERY = format("SELECT a FROM BankAccount a WHERE a.id LIKE ?%s AND a.secret LIKE ?%s", PARAMETER_NUMBER, PARAMETER_NUMBER_TWO);
    public static final String FIND_USER_COMPANY_BY_SYMBOL = format("SELECT u FROM UserCompany u WHERE u.userEmail LIKE ?%s AND u.companySymbol LIKE ?%s", PARAMETER_NUMBER, PARAMETER_NUMBER_TWO);
    public static final String DELETE_ALL_SUBSCRIPTIONS_QUERY = "DELETE FROM Subscription s";
    public static final String FIND_DEFAULT_SUBSCRIPTION_QUERY = format("SELECT s FROM Subscription s WHERE s.subscriptionPrice = %s", DEFAULT_SUBSCRIPTION_PRICE);
    public static final String DELETE_ALL_AUTHORITIES_QUERY = "DELETE FROM Authority a";
    public static final String DELETE_ALL_SUBSCRIPTIONS_AUTHORITIES_QUERY = "DELETE FROM SubscriptionAuthorities s";

    public static final int DEFAULT_EXPIRATION_DATE_YEAR = 3000;
    public static final int DEFAULT_EXPIRATION_DATE_MONTH = 1;
    public static final int DEFAULT_EXPIRATION_DATE_DAY = 1;
    public static final LocalDate DEFAULT_SUBSCRIPTION_EXPIRATION_DATE = LocalDate
            .of(DEFAULT_EXPIRATION_DATE_YEAR, DEFAULT_EXPIRATION_DATE_MONTH,
                    DEFAULT_EXPIRATION_DATE_DAY);

    public static final String FIRST_NAME_PROPERTY = "firstName";
    public static final String LAST_NAME_PROPERTY = "lastName";
    public static final String EMAIL_PROPERTY = "email";
    public static final String PASSWORD_PROPERTY = "password";
    public static final String VALID_EMAIL_MESSAGE = "Email should be valid";
    public static final String VALID_BANK_ACCOUNT_ID_PROPERTY = "id";
    public static final String VALID_BANK_ACCOUNT_BALANCE_PROPERTY = "sum";
    public static final String VALID_BANK_ACCOUNT_SECRET_PROPERTY = "secret";
    public static final String SUBSCRIPTIONS_PROPERTY = "subscriptions.";
    public static final String SUBSCRIPTIONS_UPDATE_PROPERTY = "subscriptions.update";

    public static final String DEFAULT_SUBSCRIPTION_LOG = "User %s has just been registered and received default %s subscription with expiration date %s";

    public static final String ADMIN_ROLE = "ADMIN";
    public static final String USER_ROLE = "USER";
    public static final String ROLE = "role";
    public static final String NAME = "name";
    public static final String PRICE = "price";
    public static final String AUTHORITIES = "authorities";

    public static final String USERS_PATTERN = "/users/**";
    public static final String STOCKS_PATTERN = "/stocks/**";
    public static final String BANK_PATTERN = "/bank/**";

    public static final String USER_ALREADY_EXISTS_EXCEPTION = "User with an email '%s' already exists";
    public static final String USER_NOT_FOUND_EXCEPTION = "User with an email '%s' does not exist";
    public static final String COMPANY_NOT_FOUND_EXCEPTION = "Company with a symbol '%s' does not exist";
    public static final String PROPERTY_NOT_VALID_EXCEPTION = "Given property '%s' is absent or empty, but required.";
    public static final String NUMBER_NOT_VALID_EXCEPTION = "Given property '%s' must be bigger than 0.";
    public static final String PROPERTY_SHOULD_BE_A_NUMBER_EXCEPTION = "Given property '%s' must be a number.";
    public static final String ROLE_NOT_VALID_EXCEPTION = "Given role '%s' is incorrect, only 'ADMIN' and 'USER' roles are allowed.";
    public static final String SUBSCRIPTION_VALUE_NOT_VALID_EXCEPTION = "Given subscription kind '%s' is not allowed";
    public static final String BANK_ACCOUNT_ALREADY_EXISTS_EXCEPTION = "User with an id '%s' already exists";
    public static final String BANK_ACCOUNT_DOES_NOT_EXIST_EXCEPTION = "User with an id '%s' does not have any active bank accounts";
    public static final String BANK_ACCOUNT_CREDENTIALS_INVALID_EXCEPTION = "Provided credentials are incorrect. Please try again.";
    public static final String NOT_ENOUGH_MONEY_EXCEPTION = "You don't have enough money to perform this operation. Please try again.";
    public static final String COMPANY_ALREADY_ADDED_EXCEPTION = "Company with chosen symbol '%s' is already selected. Please choose another one.";
    public static final String DEFAULT_SUBSCRIPTION_NOT_FOUND_EXCEPTION = "Default subscription does not exist";

    public static final String MAPPER_NAME_FORMAT = "%s %s";
    public static final String MAPPER_DATE_FORMAT = "yyyy-MM-dd";
}
