package com.learning.spring.intercomm;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.learning.spring.model.StockExchange;

@FeignClient("stockexchange-service")
public interface StockExchangeClient {

	@GetMapping("/stockexchanges")
	public List<Object> getAllStockExchanges();
}
