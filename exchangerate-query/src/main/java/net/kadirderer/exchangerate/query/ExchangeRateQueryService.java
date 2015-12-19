package net.kadirderer.exchangerate.query;

public interface ExchangeRateQueryService {
	
	public ExchangeRateTable queryExchangeRateTable();
	
	public ExchangeRateQueryResult queryRates(ExchangeRateQuery obj);
	
	public String queryExchangeRateJSON();
	
	public ExchangeRateTable getExchangeRateTable(String jsonString);
	
	public ExchangeRateQueryResult qetExchangeRates(ExchangeRateQuery obj, String jsonString);

}
