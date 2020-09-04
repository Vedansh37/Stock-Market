package com.learning.spring.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.spring.model.StockExchange;

@Repository
public interface StockExchangeRepository extends JpaRepository<StockExchange, Long>{

	public Optional<StockExchange> findStockExchangeByExchangeName(String name);
}
