package br.com.icc.stockquotemanager.form;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import br.com.icc.stockquotemanager.model.Quote;
import lombok.Getter;

@Getter
public class StockQuoteForm {

	@NotNull
	private String id;
	@NotNull
	Map<String, String> quotes;

	public List<Quote> toListQuote() {
		List<Quote> quoteList = new ArrayList<Quote>();
		for (Map.Entry<String, String> quote : this.quotes.entrySet()) {
			Quote newQuote = new Quote(LocalDate.parse(quote.getKey()), new BigDecimal(quote.getValue()), this.id);
			quoteList.add(newQuote);
			System.out.println("date: " + newQuote.getDate());
		}
		quoteList.forEach(item -> {
			System.out.println(item.getDate());
			System.out.println(item.getValue());
			System.out.println(item.getStockId());
		});
		return quoteList;
	}

}
