package br.com.icc.stockquotemanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.icc.stockquotemanager.model.Quote;
import br.com.icc.stockquotemanager.repository.QuoteRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QuoteService {

	@Autowired
	QuoteRepository quoteRepository;

	public void saveQuote(Quote quote) {
		quoteRepository.save(quote);

	}

	public List<Quote> findByStockId(String stockId) {
		return quoteRepository.findByStockId(stockId);

	}

}
