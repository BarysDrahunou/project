package com.senla.finance.project.utils;

import lombok.experimental.UtilityClass;

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

    public static final String FIND_ALL_USERS_QUERY = "SELECT u FROM User u";
    public static final String CHECK_IF_USER_EXISTS_QUERY = "SELECT u FROM User u WHERE u.email LIKE ?1";
    public static final String GET_ALL_COMPANIES_SYMBOLS_QUERY = "SELECT c.symbol FROM Company c";
}
