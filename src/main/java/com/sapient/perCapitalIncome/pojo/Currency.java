package com.sapient.perCapitalIncome.pojo;

import java.math.BigDecimal;

public enum Currency {
	USD(new  BigDecimal(1)),
	INR(new  BigDecimal(66f)),
	GBP(new  BigDecimal(1.74f)), 
	SGP(new  BigDecimal(1.34f)), 
	HKD(new  BigDecimal(785f));

	private BigDecimal conversionRate;

	private Currency(BigDecimal conversion) {
		this.conversionRate = conversion;
	}

	public BigDecimal getConversionRate() {
		return (BigDecimal) conversionRate;
	}
}
