package com.sapient.perCapitalIncome.service;

import java.io.File;
import java.util.List;

import com.sapient.perCapitalIncome.pojo.Income;

public interface InputParserInterface {
	List<Income> parseIncome(File fileName);
}
