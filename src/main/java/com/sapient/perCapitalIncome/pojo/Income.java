package com.sapient.perCapitalIncome.pojo;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Income {
	private String city;
	private String country;
	private String gender;
	private Currency currency;
	private BigDecimal averageIncome; 

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public BigDecimal getAverageIncome() {
		return averageIncome;
	}

	public void setAverageIncome(BigDecimal averageIncome) {
		this.averageIncome = averageIncome;
	}

	@Override
	public String toString() {
		return "Income [city=" + city + ", country=" + country + ", gender=" + gender + ", currency=" + currency
				+ ", averageIncome=" + averageIncome + "]";
	}

	public Income(final String country, final String city, final String gender, final Currency currency,
			final BigDecimal averageIncome) {
		this.city = city;
		this.country = country;
		this.gender = gender;
		this.currency = currency;
		this.averageIncome = averageIncome;
	}
}
