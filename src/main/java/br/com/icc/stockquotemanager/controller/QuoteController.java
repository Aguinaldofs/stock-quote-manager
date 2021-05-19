package br.com.icc.stockquotemanager.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.icc.stockquotemanager.dto.StockDto;
import br.com.icc.stockquotemanager.dto.StockQuoteDto;
import br.com.icc.stockquotemanager.form.StockQuoteForm;
import br.com.icc.stockquotemanager.model.Quote;
import br.com.icc.stockquotemanager.repository.QuoteRepository;
import br.com.icc.stockquotemanager.service.QuoteService;
import br.com.icc.stockquotemanager.service.StockService;

@RestController
@RequestMapping("/quotes")
public class QuoteController {

	private QuoteRepository quoteRepository;
	private StockService stockService;
	private QuoteService quoteService;

	@Autowired
	public QuoteController(QuoteRepository quoteRepository, StockService stockService, QuoteService quoteService) {
		this.quoteRepository = quoteRepository;
		this.stockService = stockService;
	}

	@GetMapping("/{stockId}")
	public ResponseEntity<StockQuoteDto> readAStockQuoteById(@PathVariable String stockId) {
		StockDto stock = stockService.getById(stockId);
		if (stock == null) {
			return ResponseEntity.status(404).body(null);

		} else {

			List<Quote> findById = quoteRepository.findByStockId(stockId);
			StockQuoteDto stockQuoteDto = new StockQuoteDto(stockId, findById);
			return ResponseEntity.status(200).body(stockQuoteDto);

		}

	}

	@GetMapping
	ResponseEntity<List<StockQuoteDto>> readAllStockQuotes() {
		List<StockDto> stocks = stockService.getAll();
		List<StockQuoteDto> stocksQuotesDto = new ArrayList<StockQuoteDto>();
		stocks.forEach(stock -> {
			List<Quote> quotes = quoteRepository.findByStockId(stock.getId());
			stocksQuotesDto.add(new StockQuoteDto(stock.getId(), quotes));
		});

		return ResponseEntity.status(201).body(stocksQuotesDto);
	}

	@PostMapping
	ResponseEntity<StockQuoteDto> createAStockQuote(@RequestBody @Valid StockQuoteForm form) {
		StockDto stockDto = stockService.getById(form.getStockId());
		if (stockDto == null) {
			return ResponseEntity.status(404).body(null);

		} else {
			return ResponseEntity.status(201).body(new StockQuoteDto(form.getStockId(), form.getValueMap()));
		}
	}
}
