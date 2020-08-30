package com.learning.spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.spring.model.StockExchange;

@Repository
public interface StockExchangeRepository extends JpaRepository<StockExchange, Long>{

	StockExchange findByExhangeName(String exhangeName);
}
