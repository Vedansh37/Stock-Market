package com.learning.spring.service;

import java.util.List;
import java.util.Optional;

import com.learning.spring.dto.StockExchangeDto;
import com.learning.spring.model.StockExchange;

public interface StockExchangeService {

	
	public Optional<StockExchangeDto> findStockExchangeById(Long id);
	
	public List<StockExchangeDto> findAllStockExchanges();
	
	public StockExchangeDto addStockExchange(StockExchange stockExchange);
	
	public Optional<StockExchangeDto> findStockExchangeByExchangeName(String name);
}
