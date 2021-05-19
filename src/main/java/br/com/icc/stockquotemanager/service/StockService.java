package br.com.icc.stockquotemanager.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.icc.stockquotemanager.dto.StockDto;

@Service
public class StockService {

	private String url = "http://localhost:8080";
	private RestTemplate restTemplate;

	public StockService() {
		restTemplate = new RestTemplate();
	}

	public List<StockDto> getAll() {
		StockDto[] stocks = restTemplate.getForObject(url + "/stock", StockDto[].class);
		return Arrays.asList(stocks);
	}

	public StockDto getById(String id) {
		StockDto stock = restTemplate.getForObject(url + "/stock/" + id, StockDto.class);
		return stock;
	}

}
