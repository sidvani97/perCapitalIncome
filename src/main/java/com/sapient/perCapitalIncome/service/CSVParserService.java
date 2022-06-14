package com.sapient.perCapitalIncome.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sapient.perCapitalIncome.pojo.Income;

public class CSVParserService implements InputParserInterface {
    private static final Logger logger = LogManager.getLogger(CSVParserService.class);

	@Override
	public List<Income> parseIncome(File fileName) {
		try(BufferedReader reader =Files.newBufferedReader(Paths.get(fileName.getPath()))){
			Spliterator<String> spliterator=reader.lines().spliterator();
			Spliterator<Income> incomeSpliterator=new IncomeSpliterator(spliterator);
			Stream<Income> stream=StreamSupport.stream(incomeSpliterator, false);
			
			return  stream.collect(Collectors.toList());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getStackTrace());
		}
		return new ArrayList<>();
	}

}
