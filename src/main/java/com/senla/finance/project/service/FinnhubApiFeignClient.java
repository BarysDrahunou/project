package com.senla.finance.project.service;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.senla.finance.project.utils.Constants.*;

@FeignClient(name = FEIGN_CLIENT_NAME, url = "${finnhub.api.url}")
public interface FinnhubApiFeignClient {

    @GetMapping(value = STOCK_METRIC)
    public Response getYearlyMetricsResponseByCompany(@RequestParam(SYMBOL) String symbol,
                                                      @RequestParam(METRIC) String metric,
                                                      @RequestParam(TOKEN) String token);

    @GetMapping(value = STOCK_SYMBOL)
    public Response getCompaniesList(@RequestParam(EXCHANGE) String exchange,
                                     @RequestParam(TOKEN) String token);

    @GetMapping(value = QUOTE)
    public Response getDailyStockValues(@RequestParam(SYMBOL) String symbol,
                                        @RequestParam(TOKEN) String token);

    @GetMapping(value = STOCK_FINANCIALS_REPORTED)
    public Response getCompanyFullReport(@RequestParam(SYMBOL) String symbol,
                                         @RequestParam(TOKEN) String token);

}

