package com.learning.spring.intercomm;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("stockexchange-service")
public interface StockExchangeClient {

	@GetMapping("/stockexchanges")
	public List<Object> getAllStockExchanges();
	
	@PostMapping("/stockexchanges/add")
	public ResponseEntity<Object> addStockExchange(@RequestBody Object stockExchange);
	
	@GetMapping("/stockexchanges/id/{id}")
	public ResponseEntity<Object> findStockExchangeById(@PathVariable("id") Long id);
	
	@GetMapping("/stockexchanges/name/{name}")
	public ResponseEntity<Object> findStockExchangeByExchangeName(@PathVariable("name")String name);
}
