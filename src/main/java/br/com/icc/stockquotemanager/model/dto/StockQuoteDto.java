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
		log.info(
				"Value and date were created separately in the model thinking about future features that need to use them separately");
		quotes.forEach(quote -> {

			String date = quote.getDate().toString();
			String value = quote.getValue().toBigInteger().toString();

			this.quotes.put(date, value);
			log.debug("Date: " + date + " Value: " + value);

		});
	}

}
