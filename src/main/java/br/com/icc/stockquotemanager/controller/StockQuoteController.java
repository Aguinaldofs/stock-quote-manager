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

import br.com.icc.stockquotemanager.model.Quote;
import br.com.icc.stockquotemanager.model.dto.StockDto;
import br.com.icc.stockquotemanager.model.dto.StockQuoteDto;
import br.com.icc.stockquotemanager.model.form.StockQuoteForm;
import br.com.icc.stockquotemanager.repository.QuoteRepository;
import br.com.icc.stockquotemanager.service.QuoteService;
import br.com.icc.stockquotemanager.service.StockService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/quotes")
@Slf4j
@AllArgsConstructor(onConstructor = @__({ @Autowired }))
public class StockQuoteController {

	private QuoteRepository quoteRepository;
	private StockService stockService;
	private QuoteService quoteService;

	@GetMapping("/{stockId}")
	public ResponseEntity<StockQuoteDto> readAStockQuoteById(@PathVariable String stockId) {
		log.info("\u001b[42mSearch the stock quote for stockId\u001b[0m");

		StockDto stock = stockService.getById(stockId);
		if (stock == null) {
			log.warn(
					"\u001b[43mThe inserted stockId was not found in the database, consider inserting one that exists\u001b[0m");
			return ResponseEntity.status(404).body(null);

		} else {

			List<Quote> findById = quoteService.findByStockId(stockId);
			StockQuoteDto stockQuoteDto = new StockQuoteDto(stockId, findById);
			log.debug("\u001b[45mFinished searching for stockQuote by id!\u001b[0m");
			return ResponseEntity.status(200).body(stockQuoteDto);

		}

	}

	@GetMapping
	ResponseEntity<List<StockQuoteDto>> readAllStockQuotes() {
		log.info("\u001b[42mSearch for all stockQuotes\u001b[0m");
		List<StockDto> stocks = stockService.getAll();
		List<StockQuoteDto> stocksQuotesDto = new ArrayList<StockQuoteDto>();
		stocks.forEach(stock -> {
			List<Quote> quotes = quoteService.findByStockId(stock.getId());
			stocksQuotesDto.add(new StockQuoteDto(stock.getId(), quotes));
		});
		log.debug("\u001b[45mFinished searching for all stocks and quotes!\u001b[0m");
		return ResponseEntity.status(200).body(stocksQuotesDto);
	}

	@PostMapping
	ResponseEntity<StockQuoteDto> createAStockQuote(@RequestBody @Valid StockQuoteForm form) {
		log.info("\u001b[42mCreate a  stockQuote\u001b[0m");
		StockDto stockDto = stockService.getById(form.getId());
		if (stockDto == null) {
			log.warn("\u001b[43mThere is no stock with this id in the database\u001b[0m");
			return ResponseEntity.status(404).body(null);

		}
		List<Quote> quotes = form.toListQuote();
		quoteRepository.saveAll(quotes);
		List<Quote> findById = quoteRepository.findByStockId(form.getId());
		StockQuoteDto stockQuoteDto = new StockQuoteDto(form.getId(), findById);
		log.debug("\u001b[45mFinished creating a Stocksquote!\u001b[0m");
		return ResponseEntity.status(201).body(stockQuoteDto);
	}
}
