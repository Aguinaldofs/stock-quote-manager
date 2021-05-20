package br.com.icc.stockquotemanager.controller;

import javax.transaction.Transactional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/stockcache")
@RestController
@Slf4j
public class StockCacheController {

	@DeleteMapping
	@Transactional
	@Caching(evict = { @CacheEvict(value = "stocks", allEntries = true),
			@CacheEvict(value = "stock", allEntries = true) })
	public ResponseEntity<?> resetStockCache() {
		log.info("Deletes the cache that searches both for one stock and for all stocks");
		return ResponseEntity.status(200).build();

	}
}
