package com.learning.spring.service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.reflect.TypeToken;
import com.learning.spring.dto.StockExchangeDto;
import com.learning.spring.model.StockExchange;
import com.learning.spring.repo.StockExchangeRepository;

@Service
public class StockExchangeServiceImpl implements StockExchangeService{

	StockExchangeRepository stockExchangeRepository;
	
	@Autowired
	ModelMapper mapper;
	
	public StockExchangeServiceImpl(StockExchangeRepository stockExchangeRepository) {
		this.stockExchangeRepository = stockExchangeRepository;
	}

	@Override
	@Transactional
	public Optional<StockExchangeDto> findStockExchangeById(Long id) {
		
		 Optional<StockExchange> stockExchange = stockExchangeRepository.findById(id);
		 Type optionalType = new TypeToken<Optional<StockExchangeDto>>() {}.getType();
		 return mapper.map(stockExchange, optionalType);
	}

	@Override
	@Transactional
	public List<StockExchangeDto> findAllStockExchanges() {
		 List<StockExchange> stockExchanges = stockExchangeRepository.findAll();
		 Type listType = new TypeToken<List< StockExchangeDto>>() {}.getType();
		return mapper.map(stockExchanges,listType);
	}

	@Override
	@Transactional
	public StockExchangeDto addStockExchange(StockExchange stockExchange) {
		 
		 return mapper.map(stockExchangeRepository.save(stockExchange), StockExchangeDto.class);
	}

	@Override
	public Optional<StockExchangeDto> findStockExchangeByExchangeName(String name) {
		Optional<StockExchange> stockExchange = stockExchangeRepository.findStockExchangeByExchangeName(name);
		
		if(!stockExchange.isPresent())return null;
		Type optionalType = new TypeToken<Optional<StockExchangeDto>>() {}.getType();
		return mapper.map(stockExchange, optionalType);
	};
	
	
}
