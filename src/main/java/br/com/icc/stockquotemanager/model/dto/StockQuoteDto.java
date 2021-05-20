package br.com.icc.stockquotemanager.model.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.icc.stockquotemanager.model.Quote;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class StockQuoteDto {

	private String id;
	Map<String, String> quotes = new HashMap<String, String>();

	public StockQuoteDto(String stockId, List<Quote> quotes) {
		this.id = stockId;
		mapQuotes(quotes);

	}

	public void mapQuotes(List<Quote> quotes) {
		quotes.forEach(quote -> {

			String date = quote.getDate().toString();
			String value = quote.getValue().toBigInteger().toString();

			this.quotes.put(date, value);
			log.debug("\u001b[42mDate: " + date + " Value: " + value + "\u001b[0m");

		});
	}

}
