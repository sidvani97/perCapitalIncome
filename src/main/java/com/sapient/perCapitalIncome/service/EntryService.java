package com.sapient.perCapitalIncome.service;

import java.io.File;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sapient.perCapitalIncome.pojo.Income;
import com.sapient.perCapitalIncome.util.Constants;

@Service
public class EntryService {
    private static final Logger logger = LogManager.getLogger(EntryService.class);
	private static List<Income> incomeList;

	public void execute() {
		EntryService es = new EntryService();
		File file = es.openFile(Constants.INPUT_FILE_PATH);
		
		if(file.exists()) {
			CSVParserService csvParserService=new CSVParserService();
			incomeList=csvParserService.parseIncome(file);
			printIncomes();
			
		}
		GenerateReportService generateReportService=new GenerateReportService();
		generateReportService.writeReport(incomeList, Constants.OUTPUT_FILE_PATH);
	
	}

	private void printIncomes() {
		// TODO Auto-generated method stub
		for(Income income : incomeList) {
			logger.info(income);
			}
	}

	private File openFile(String fileName) {
		if (Objects.isNull(fileName) || fileName.isBlank()) {
			logger.error("Invalid file name");
		}
		return new File(fileName);
	}

}
