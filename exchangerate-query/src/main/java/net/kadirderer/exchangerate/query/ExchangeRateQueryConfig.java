package net.kadirderer.exchangerate.query;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExchangeRateQueryConfig {
	
	@Value("${exchangerate.query.url}")
	private String exchangeRateQueryUrl;
	
	public String getExchangeRateQueryUrl() {
		return exchangeRateQueryUrl;
	}

}
