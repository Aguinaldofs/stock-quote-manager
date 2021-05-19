package br.com.icc.stockquotemanager.form;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;

public class StockQuoteForm {

	@NotNull
	private String stockId;
	Map<String, String> quotes = new HashMap<String, String>();

	public String getStockId() {
		return stockId;
	}

	public Map<String, String> getValueMap() {

		return quotes;
	}

}
