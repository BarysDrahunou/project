package com.senla.finance.project.service;

import com.senla.finance.project.converters.FeignResponseConverter;
import com.senla.finance.project.dao.CompaniesDao;
import com.senla.finance.project.model.finnhub.Company;
import com.senla.finance.project.model.finnhub.CompanyReport;
import com.senla.finance.project.model.finnhub.DailyStockValue;
import com.senla.finance.project.model.finnhub.YearlyMetrics;
import feign.Response;
import lombok.SneakyThrows;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class FinnhubService {

    @Autowired
    private FinnhubApiFeignClient feignClient;
    @Autowired
    private CompaniesDao companiesDao;

    private static final Logger LOGGER = LogManager.getLogger(FinnhubService.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron = "0 0 8 * * *")
    public void refreshCompaniesList() {
        Response response = feignClient
                .getCompaniesList("US","btqbebn48v6t9hdd6cog");
        List<Company> companies = FeignResponseConverter.convertToCompaniesList(response);
        companiesDao.mergeAll(companies);
    }

    @Scheduled(fixedRate = 300000)
    public void refreshDailyStockValues(){
//        List<String> companiesSymbols = companiesDao.getAllCompaniesSymbols();
//        List<DailyStockValue> chunkList = new ArrayList<>();
//        final int rateLimit = 25;
//
//        for (String symbol: companiesSymbols){
//            Response response = feignClient
//                    .getDailyStockValues(symbol, "btqbebn48v6t9hdd6cog");
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
//                        .getDailyStockValues(symbol, "btqbebn48v6t9hdd6cog");
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

    public YearlyMetrics getYearlyMetrics(String symbol){
        Response response = feignClient
                .getYearlyMetricsResponseByCompany(symbol,"all","btqbebn48v6t9hdd6cog");
        return FeignResponseConverter.convertToYearlyMetrics(response, symbol);
    }

    public CompanyReport getCompanyFullReport(String symbol){
        Response response = feignClient
                .getCompanyFullReport(symbol,"btqbebn48v6t9hdd6cog");
        return FeignResponseConverter.convertToCompanyFullReport(response, symbol);
    }

}
