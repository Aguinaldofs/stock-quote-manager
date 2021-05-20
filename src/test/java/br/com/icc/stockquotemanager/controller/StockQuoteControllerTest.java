package br.com.icc.stockquotemanager.controller;

import static org.hamcrest.CoreMatchers.containsString;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.icc.stockquotemanager.model.dto.StockDto;
import br.com.icc.stockquotemanager.service.QuoteService;
import br.com.icc.stockquotemanager.service.StockService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class StockQuoteControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	StockService stockService;

	@MockBean
	QuoteService quoteService;

	String id = "petr4";

	@BeforeEach
	public void beforeEach() {

		StockDto expected = new StockDto(id);
		Mockito.when(stockService.getById(id)).thenReturn(expected);

	}

	@Test
	public void shouldListStockQuoteByStockId() throws Exception {

		URI uri = new URI("/quotes/" + id);

		mockMvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().string(containsString("id")))
				.andExpect(MockMvcResultMatchers.content().string(containsString("quotes")));

	}

	@Test
	public void shouldListAllStocksQuotes() throws Exception {

		URI uri = new URI("/quotes/");

		mockMvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	public void shouldAddStockQuote() throws Exception {

		Map<String, String> quotes = new HashMap<String, String>();
		quotes.put("2020-01-12", "20");
		JSONObject map = new JSONObject(quotes);
		JSONObject json = new JSONObject();
		json.put("quotes", map);
		json.put("id", "petr4");
		URI uri = new URI("/quotes");

		mockMvc.perform(
				MockMvcRequestBuilders.post(uri).content(json.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(201));

	}

	@Test
	public void shouldNotReturnAListOfStockQuoteBecauseStockIdIsInvalid() throws Exception {

		String stockId = "agui";
		URI uri = new URI("/quotes/" + stockId);

		mockMvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(404));

	}

	@Test
	public void shouldNotReturnAListOfAllStockQuotesBecauseURLIsInvalid() throws Exception {

		URI uri = new URI("/quote/");

		mockMvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(404));
	}

}
