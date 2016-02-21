package net.kadirderer.exchangerate.query;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExchangeRateQueryConfig {
	
	@Value("${exchangerate.query.url}")
	private String exchangeRateQueryUrl;
	
	@Value("${exchangerate.currency.selected}")
	private String selectedCurrency;
	
	public String getExchangeRateQueryUrl() {
		return exchangeRateQueryUrl;
	}
	
	public String getSelectedCurrency() {
		return selectedCurrency;
	}

}
