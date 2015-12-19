package net.kadirderer.exchangerate.query;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ExchangeRateQueryServiceImpl implements ExchangeRateQueryService {

	@Autowired
	private ExchangeRateQueryConfig exchangeRateConfig;
	
	@Override
	public ExchangeRateTable queryExchangeRateTable() {
		
		RestTemplate rt = new RestTemplate();
		return rt.getForObject(exchangeRateConfig.getExchangeRateQueryUrl(), ExchangeRateTable.class);
		
	}

	@Override
	public ExchangeRateQueryResult queryRates(ExchangeRateQuery obj) {
		ExchangeRateTable erTable = queryExchangeRateTable();
		
		Double rate1 = erTable.getRates().get(obj.getFirstCurrency());
		Double rate2 = erTable.getRates().get(obj.getSecondCurrency());
		
		ExchangeRateQueryResult result = new ExchangeRateQueryResult();		
		result.setQuery(obj);
		result.setRateByFirst(rate2 / rate1);
		result.setRateBySecond(rate1 / rate2);
		
		return result;
	}

	@Override
	public String queryExchangeRateJSON() {
		URL url;
		try {
			url = new URL(exchangeRateConfig.getExchangeRateQueryUrl());

			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			BufferedReader br = new BufferedReader(new InputStreamReader(
					con.getInputStream()));

			String input;
			StringBuffer json = new StringBuffer();

			while ((input = br.readLine()) != null) {
				json.append(input);
			}

			return json.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public ExchangeRateTable getExchangeRateTable(String jsonString) {
		ObjectMapper om = new ObjectMapper();
		
		try {
			return om.readValue(jsonString, ExchangeRateTable.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public ExchangeRateQueryResult qetExchangeRates(ExchangeRateQuery obj, String jsonString) {
		ExchangeRateTable erTable = getExchangeRateTable(jsonString);
		
		Double rate1 = erTable.getRates().get(obj.getFirstCurrency());
		Double rate2 = erTable.getRates().get(obj.getSecondCurrency());
		
		ExchangeRateQueryResult result = new ExchangeRateQueryResult();		
		result.setQuery(obj);
		result.setRateByFirst(rate2 / rate1);
		result.setRateBySecond(rate1 / rate2);
		
		return result;
	}

}
