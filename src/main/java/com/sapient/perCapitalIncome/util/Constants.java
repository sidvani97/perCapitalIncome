package com.sapient.perCapitalIncome.util;

public class Constants {
	public static final String INPUT_FILE_PATH = "C:\\Users\\sidvani\\Documents\\workspace-spring-tool-suite-4-4.14.1.RELEASE\\perCapitalIncome\\src\\main\\resources\\Sample_Input.csv";
	public static final String OUTPUT_FILE_PATH = "C:\\Users\\sidvani\\Documents\\workspace-spring-tool-suite-4-4.14.1.RELEASE\\perCapitalIncome\\src\\main\\resources\\ouput.csv";

	public static final String OUTPUT_HEAD_COLUMNS = "City\\Country,Gender,Average Income(USD)";

	// paser constants
	public static final int INCOME_INDEX = 4;
	public static final int CURRENCY_INDEX = 3;
	public static final int GENDER_INDEX = 2;
	public static final int COUNTRY_INDEX = 1;
	public static final int CITY_INDEX = 0;
	public static final String CSV_DELIMETER = ",";
}
