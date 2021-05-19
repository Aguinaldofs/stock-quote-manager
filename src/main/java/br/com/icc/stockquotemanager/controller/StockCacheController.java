package br.com.icc.stockquotemanager.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.icc.stockquotemanager.service.StockService;

@RestController
public class StockCacheController {

	@Autowired
	public StockCacheController(StockService stockService) {
		stockService.addNotification();
	}

	@DeleteMapping
	@Transactional
	public ResponseEntity<?> resetStockCache() {

		return ResponseEntity.status(200).build();

	}
}
