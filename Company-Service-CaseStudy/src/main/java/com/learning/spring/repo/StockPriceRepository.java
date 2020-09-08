package com.learning.spring.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.spring.model.StockPrice;

@Repository
public interface StockPriceRepository extends JpaRepository<StockPrice, Long>{

	public List<StockPrice> getAllStockPricesByCompany(String companyName,String exchangeName);
}
