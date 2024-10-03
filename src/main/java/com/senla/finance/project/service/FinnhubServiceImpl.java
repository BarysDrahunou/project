package com.senla.finance.project.service;

import com.senla.finance.project.converters.FeignResponseConverter;
import com.senla.finance.project.dao.CompaniesDao;
import com.senla.finance.project.model.finnhub.Company;
import com.senla.finance.project.model.finnhub.CompanyReport;
import com.senla.finance.project.model.finnhub.YearlyMetrics;
import feign.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.senla.finance.project.utils.Constants.*;

@Component
public class FinnhubServiceImpl implements FinnhubService {

    @Value("${finnhub.api.token}")
    private String token;

    @Autowired
    private FinnhubApiFeignClient feignClient;
    @Autowired
    private CompaniesDao companiesDao;

    @Scheduled(cron = REFRESH_COMPANIES_CRON_EXPRESSION)
    public void refreshCompaniesList() {
        Response response = feignClient
                .getCompaniesList(US, token);
        List<Company> companies = FeignResponseConverter.convertToCompaniesList(response);
        companiesDao.mergeAll(companies);
    }

    @Scheduled(fixedRate = 300000)
    public void refreshDailyStockValues() {
//        List<String> companiesSymbols = companiesDao.getAllCompaniesSymbols();
//        List<DailyStockValue> chunkList = new ArrayList<>();
//        final int rateLimit = 25;
//
//        for (String symbol: companiesSymbols){
//            Response response = feignClient
//                    .getDailyStockValues(symbol, token);
//            DailyStockValue dailyStockValue;
//            try{
//            dailyStockValue = FeignResponseConverter.convertToDailyStockValue(response, symbol);}
//            catch (RuntimeException e){
//                LOGGER.log(Level.WARN, e.getMessage());
//                try {
//                    System.out.println("Sleeping...");
//                    Thread.sleep(20000);
//                } catch (InterruptedException ex) {
//                    throw new RuntimeException(ex);
//                }
//                response = feignClient
//                        .getDailyStockValues(symbol, token);
//                dailyStockValue = FeignResponseConverter.convertToDailyStockValue(response, symbol);
//            }
//            chunkList.add(dailyStockValue);
//            System.out.println(chunkList.size());
//            if (chunkList.size() == rateLimit){
//                companiesDao.mergeAll(chunkList);
//                chunkList = new ArrayList<>();
//                try {
//                    System.out.println("Sleeping...");
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
////                }
//            }
//        }
    }

    public YearlyMetrics getYearlyMetrics(String symbol) {
        Response response = feignClient
                .getYearlyMetricsResponseByCompany(symbol, ALL, token);
        return FeignResponseConverter.convertToYearlyMetrics(response, symbol);
    }

    public CompanyReport getCompanyFullReport(String symbol) {
        Response response = feignClient
                .getCompanyFullReport(symbol, token);
        return FeignResponseConverter.convertToCompanyFullReport(response, symbol);
    }

}
