package com.learning.spring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learning.spring.dto.StockExchangeDto;
import com.learning.spring.model.StockExchange;
import com.learning.spring.service.StockExchangeService;

@RestController
public class StockExchangeController {

	StockExchangeService stockExchangeService;

	public StockExchangeController(StockExchangeService stockExchangeService) {
		super();
		this.stockExchangeService = stockExchangeService;
	}
	
	@GetMapping("/stockexchanges/id/{id}")
	public ResponseEntity<Optional<StockExchangeDto>> findStockExhchangeById(@PathVariable("id") Long id){
		return ResponseEntity.status(HttpStatus.FOUND).body(stockExchangeService.findStockExchangeById(id));
	}
	
	@GetMapping("/stockexchanges/name/{name}")
	public ResponseEntity<Optional<StockExchangeDto>> findStockExchangeByName(@PathVariable("name")String name){
		
		Optional<StockExchangeDto> stockExchange = stockExchangeService.findStockExchangeByExchangeName(name);
		if(stockExchange==null) {
			return null;
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(stockExchange);
	}
	@GetMapping("/stockexchanges")
	public ResponseEntity<List<StockExchangeDto>> findAllStockExchanges(){
		
		return ResponseEntity.status(HttpStatus.FOUND).body(stockExchangeService.findAllStockExchanges());
	}
	
	@PostMapping("/stockexchanges/add")
	public ResponseEntity<StockExchangeDto> addStockExchange(@RequestBody StockExchange stockExchange){
		
		return  ResponseEntity.status(HttpStatus.CREATED).body(stockExchangeService.addStockExchange(stockExchange));
	}
}
