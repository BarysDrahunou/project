package com.senla.finance.project.service;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "feignClient", url = "https://finnhub.io/api/v1")
public interface FinnhubApiFeignClient {

    @GetMapping(value = "stock/metric")
    public Response getYearlyMetricsResponseByCompany(@RequestParam("symbol") String symbol,
                                                                        @RequestParam("metric") String metric,
                                                                        @RequestParam("token") String token);
    @GetMapping(value = "stock/symbol")
    public Response getCompaniesList(@RequestParam("exchange") String exchange,
                                     @RequestParam("token") String token);

    @GetMapping(value = "quote")
    public Response getDailyStockValues(@RequestParam("symbol") String symbol,
                                     @RequestParam("token") String token);

    @GetMapping(value = "stock/financials-reported")
    public Response getCompanyFullReport(@RequestParam("symbol") String symbol,
                                        @RequestParam("token") String token);

}

