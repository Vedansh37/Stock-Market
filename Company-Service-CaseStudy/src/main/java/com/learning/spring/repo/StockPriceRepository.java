package com.learning.spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.spring.model.StockPrice;

@Repository
public interface StockPriceRepository extends JpaRepository<StockPrice, Long>{

}
