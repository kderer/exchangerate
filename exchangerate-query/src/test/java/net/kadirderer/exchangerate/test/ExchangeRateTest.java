package net.kadirderer.exchangerate.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.kadirderer.exchangerate.query.ExchangeRateQuery;
import net.kadirderer.exchangerate.query.ExchangeRateQueryResult;
import net.kadirderer.exchangerate.query.ExchangeRateQueryService;
import net.kadirderer.exchangerate.query.ExchangeRateTable;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ExchangeRateTestConfig.class})
public class ExchangeRateTest {
	
	@Autowired
	private ExchangeRateQueryService erqs;	
	
	@Test
	public void queryTest() {
		ExchangeRateTable table = erqs.queryExchangeRateTable();
		
		assertNotNull(table.getRates().get("TRY"));
	}
	
	@Test
	public void queryExchangeRateTest() {
		
		ExchangeRateQuery query = new ExchangeRateQuery();
		query.setFirstCurrency("TRY");
		query.setSecondCurrency("CNY");
		
		ExchangeRateQueryResult result = erqs.queryRates(query);
		
		assertEquals(2.65, result.getRateByFirst(), 0.05);
		
	}
	
	@Test
	public void testQueryRateString() {
		String result = erqs.queryExchangeRateJSON();
		
		assertNotEquals(250, result.length(), 50);
	}
	
	
	@Test
	public void testGetRateMapFromString() {
		String result = erqs.queryExchangeRateJSON();
		ExchangeRateTable table = erqs.getExchangeRateTable(result);
		assertEquals(31, table.getRates().entrySet().size());
	}
	
	
	@Test
	public void qetExchangeRateTest() {
		String json = erqs.queryExchangeRateJSON();
		
		ExchangeRateQuery query = new ExchangeRateQuery();
		query.setFirstCurrency("TRY");
		query.setSecondCurrency("CNY");
		
		ExchangeRateQueryResult result = erqs.qetExchangeRates(query, json);
		
		assertEquals(2.65, result.getRateByFirst(), 0.05);
		
	}

}
