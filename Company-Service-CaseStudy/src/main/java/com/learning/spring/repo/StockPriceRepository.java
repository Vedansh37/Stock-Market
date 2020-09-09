package com.learning.spring.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.learning.spring.model.StockPrice;

@Repository
public interface StockPriceRepository extends JpaRepository<StockPrice, Long>{

	public List<StockPrice> getAllStockPricesByCompany(String companyName,String exchangeName);
	
	
	@Query(value = "SELECT * FROM  STOCK_PRICE WHERE DATE BETWEEN :fromDate and :toDate "
			+ "AND COMPANY_ID =(SELECT COMPANY_ID FROM COMPANY WHERE COMPANY_NAME = :companyName)"
			+ "AND STOCK_EXCHANGE = (SELECT STOCK_EXCHANGE_ID FROM STOCK_EXCHANGE WHERE "
			+ "EXCHANGE_NAME = :exchangeName)",nativeQuery = true)
	public List<StockPrice> getStockPriceFromToDate(@Param("companyName")String companyName,
													@Param("exchangeName")String exchangeName,
													@Param("fromDate")String fromDate,
													@Param("toDate")String toDate);
}
