package br.com.icc.stockquotemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.com.icc.stockquotemanager.service.StockService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@AllArgsConstructor(onConstructor = @__({ @Autowired }))

public class InitController {
	private StockService stockService;

	@EventListener(ApplicationReadyEvent.class)
	private void OnProjectInit() {
		log.info(
				"\u001b[42mRegister the application when starting up to receive notifications of possible changes in the external api's database\u001b[0m");
		stockService.addNotification();
	}

}
