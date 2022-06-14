package com.sapient.perCapitalIncome.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sapient.perCapitalIncome.pojo.Income;
import com.sapient.perCapitalIncome.util.Constants;

public class GenerateReportService {
    private static final Logger logger = LogManager.getLogger(GenerateReportService.class);

	public void writeReport(List<Income> incomeList,String reportName) {
		try(FileWriter fw=new FileWriter(new File(reportName))){
			fw.append(Constants.OUTPUT_HEAD_COLUMNS).append("\n");
			
			LinkedHashMap<String, Map<String,BigDecimal>> avgGenderIncomeByCountry= 
					calculateAvgIncomePerCountryByGeneder(incomeList);
			
			fw.append(joinCountryGenderAvgIncome(avgGenderIncomeByCountry));
			logger.info("Generated report Successfully");
		}
		catch (IOException e) {
			logger.error("Cant generate report"+e.getMessage());
		}
	}

	private CharSequence joinCountryGenderAvgIncome(
			LinkedHashMap<String, Map<String, BigDecimal>> avgGenderIncomeByCountry) {
		return avgGenderIncomeByCountry.entrySet()
				.stream()
				.map(countryEntry -> 
				countryEntry.getValue().entrySet()
					.stream()
					.map(genderEntry -> 
					joinKeyValues(countryEntry,genderEntry))
					.collect(Collectors.joining("\n")))
				.collect(Collectors.joining("\n"));
	}

	

	private LinkedHashMap<String, Map<String, BigDecimal>> calculateAvgIncomePerCountryByGeneder(List<Income> incomeList) {
		countryOrcity(incomeList);
	    
		return incomeList.stream()
				.collect(Collectors.groupingBy(
						Income::getCountry,
						Collectors.groupingBy(Income::getGender,
								Collectors.mapping(this::getIncomeInUSDollars,
										averageIncome()))))
				.entrySet()
				.stream()
				.sorted(Entry.comparingByKey())
				.collect(Collectors.toMap(
						Entry::getKey,
						Entry::getValue,
						(m1,m2)->m1,
						LinkedHashMap::new));
	}

	private Collector<BigDecimal, BigDecimal[], BigDecimal> averageIncome() {
		Collector<BigDecimal, BigDecimal[], BigDecimal> avgCollector = Collector.of(
			      () -> new BigDecimal[]{BigDecimal.ZERO, BigDecimal.ZERO},
			      (pair, val) -> {
			        pair[0] = pair[0].add(val);
			        pair[1] = pair[1].add(BigDecimal.ONE);
			      },
			      (pair1, pair2) -> new BigDecimal[]{pair1[0].add(pair2[0]), pair1[1].add(pair2[1])},
			      (pair) -> pair[0].divide(pair[1], 2, RoundingMode.HALF_UP)
			);
		return avgCollector;
	}
	
	private void countryOrcity(List<Income> income) {
		income.forEach(action -> {
			if (Objects.isNull(action.getCountry()) || action.getCountry().isBlank()) {
				action.setCountry(action.getCity());
			}
		});

	}
	
	private BigDecimal getIncomeInUSDollars(Income income) {
		return income.getAverageIncome().divide(income.getCurrency().getConversionRate(),MathContext.DECIMAL128);
	}
	private String joinKeyValues(Entry<String, Map<String, BigDecimal>> countryEntry, Entry<String, BigDecimal> genderEntry) {
		return countryEntry.getKey()+","+genderEntry.getKey()+","+genderEntry.getValue();
	}
	

	
}
