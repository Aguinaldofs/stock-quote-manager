package br.com.icc.stockquotemanager.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.icc.stockquotemanager.model.Quote;
import lombok.Getter;

@Getter
public class StockQuoteDto {

	private String id;
	Map<String, String> quotes = new HashMap<String, String>();

	public StockQuoteDto(String stockId, List<Quote> quotes) {
		this.id = stockId;
		mapQuotes(quotes);

	}

	public StockQuoteDto(String id, Map<String, String> quotes) {
		this.id = id;
		this.quotes = quotes;
	}

	public void mapQuotes(List<Quote> quotes) {
		quotes.forEach(quote -> {

			String date = quote.getDate().toString();
			String value = quote.getValue().toBigInteger().toString();

			this.quotes.put(date, value);
		});
	}

}
