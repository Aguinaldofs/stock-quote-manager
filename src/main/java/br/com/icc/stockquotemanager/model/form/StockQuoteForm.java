package br.com.icc.stockquotemanager.model.form;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import br.com.icc.stockquotemanager.model.Quote;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
@NotNull
public class StockQuoteForm {

	private String id;
	Map<String, String> quotes;

	public List<Quote> toListQuote() {
		log.info("\u001b[42mConverts a date and value map to a list of quotes\u001b[0m");
		List<Quote> quoteList = new ArrayList<Quote>();
		for (Map.Entry<String, String> quote : this.quotes.entrySet()) {
			Quote newQuote = new Quote(LocalDate.parse(quote.getKey()), new BigDecimal(quote.getValue()), this.id);
			quoteList.add(newQuote);
		}

		log.debug("\u001b[45mConversion of the map to a list of quotes finished\u001b[0m");
		return quoteList;
	}

}
