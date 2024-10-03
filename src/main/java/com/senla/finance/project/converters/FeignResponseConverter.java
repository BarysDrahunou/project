package com.senla.finance.project.converters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.senla.finance.project.model.finnhub.*;
import feign.Response;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.senla.finance.project.utils.Constants.DATA;
import static com.senla.finance.project.utils.Constants.METRIC;

@UtilityClass
public class FeignResponseConverter {

    private final ObjectMapper mapper = new ObjectMapper();

    @SneakyThrows
    public List<Company> convertToCompaniesList(Response response) {
        String result = IOUtils.toString(response.body().asInputStream(), StandardCharsets.UTF_8);

        TypeReference<List<Company>> c = new TypeReference<>() {
        };
        return mapper.readValue(result, c);
    }

    @SneakyThrows
    public DailyStockValue convertToDailyStockValue(Response response, String symbol) {
        String result = IOUtils.toString(response.body().asInputStream(), StandardCharsets.UTF_8);
        TypeReference<DailyStockValue> c = new TypeReference<>() {
        };

        DailyStockValue dailyStockValue = mapper.readValue(result, c);
        dailyStockValue.setSymbol(symbol);

        return dailyStockValue;
    }

    @SneakyThrows
    public YearlyMetrics convertToYearlyMetrics(Response response, String symbol) {
        String result = IOUtils.toString(response.body().asInputStream(), StandardCharsets.UTF_8);

        JSONObject jsonObject = new JSONObject(result);
        String metrics = jsonObject.getJSONObject(METRIC).toString();
        TypeReference<YearlyMetrics> c = new TypeReference<>() {
        };

        YearlyMetrics yearlyMetrics = mapper.readValue(metrics, c);
        yearlyMetrics.setSymbol(symbol);

        return yearlyMetrics;
    }

    @SneakyThrows
    public CompanyReport convertToCompanyFullReport(Response response, String symbol) {
        String result = IOUtils.toString(response.body().asInputStream(), StandardCharsets.UTF_8);

        JSONObject jsonObject = new JSONObject(result);
        TypeReference<List<Map<String, Object>>> c = new TypeReference<>() {
        };

        List<Map<String, Object>> rawYearlyReports = mapper.readValue(jsonObject.getJSONArray(DATA).toString(), c);
        List<YearlyReport> yearlyReports = rawYearlyReports
                .stream()
                .map(report -> mapper.convertValue(report, YearlyReport.class))
                .collect(Collectors.toList());

        CompanyReport companyReport = new CompanyReport();
        companyReport.setSymbol(symbol);
        companyReport.setData(yearlyReports);

        return companyReport;
    }
}
