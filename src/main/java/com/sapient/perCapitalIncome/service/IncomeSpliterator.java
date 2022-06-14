package com.sapient.perCapitalIncome.service;

import java.math.BigDecimal;
import java.util.Spliterator;
import java.util.function.Consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sapient.perCapitalIncome.pojo.Currency;
import com.sapient.perCapitalIncome.pojo.Income;
import com.sapient.perCapitalIncome.util.Constants;

public class IncomeSpliterator implements Spliterator<Income> {
    private static final Logger logger = LogManager.getLogger(IncomeSpliterator.class);

	private Spliterator<String> spliterator;
	private String[] incomeToken;

	public IncomeSpliterator(final Spliterator<String> spliterator) {
		this.spliterator = spliterator;
		spliterator.tryAdvance(s ->logger.info("Skip Header " + s));
	}

	@Override
	public boolean tryAdvance(Consumer<? super Income> action) {
		// TODO Auto-generated method stub
		if (spliterator.tryAdvance(s -> this.incomeToken = s.split(","))) {
//			if (incomeToken.length != 5) {
//				action.accept(null);
//				return true;
//			}
			String country = incomeToken[Constants.COUNTRY_INDEX];
			String city = incomeToken[Constants.CITY_INDEX];
			String gender = incomeToken[Constants.GENDER_INDEX];
			String currency = incomeToken[Constants.CURRENCY_INDEX];
			String averageIncome = incomeToken[Constants.INCOME_INDEX];
			action.accept(
					new Income(country, 
							city, 
							gender, 
							Currency.valueOf(currency), 
							new BigDecimal(averageIncome)));
			return true;
		}
		return false;
	}

	@Override
	public Spliterator<Income> trySplit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long estimateSize() {
		// TODO Auto-generated method stub
		return spliterator.estimateSize();
	}

	@Override
	public int characteristics() {
		// TODO Auto-generated method stub
		return spliterator.characteristics();
	}

}
