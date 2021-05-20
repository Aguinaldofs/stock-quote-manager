package br.com.icc.stockquotemanager.service;

import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.icc.stockquotemanager.model.dto.StockDto;

@Service
public class StockService {

	private String url = "http://localhost:8080";
	private RestTemplate restTemplate;

	public StockService() {
		restTemplate = new RestTemplate();
	}

	@Cacheable(value = "stockList")
	public List<StockDto> getAll() {
		StockDto[] stocks = restTemplate.getForObject(url + "/stock", StockDto[].class);
		return Arrays.asList(stocks);
	}

	@Cacheable(value = "stock")
	public StockDto getById(String id) {
		StockDto stock = restTemplate.getForObject(url + "/stock/" + id, StockDto.class);
		return stock;
	}

	public void addNotification() {

		JSONObject notificationJsonObject = new JSONObject();
		String notificationJsonLiteral;
		HttpHeaders header = new HttpHeaders();

		header.setContentType(MediaType.APPLICATION_JSON);

		notificationJsonObject.put("host", "localhost");
		notificationJsonObject.put("port", "8081");

		notificationJsonLiteral = notificationJsonObject.toString();

		HttpEntity<String> request = new HttpEntity<String>(notificationJsonLiteral, header);

		restTemplate.postForObject(url + "/notification", request, String.class);

	}

}
