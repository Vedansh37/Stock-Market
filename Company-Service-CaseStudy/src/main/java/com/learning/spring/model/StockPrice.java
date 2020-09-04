package com.learning.spring.model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedNativeQuery(name="StockPrice.getAllStockPrices",
					query = "SELECT * FROM STOCK_PRICE WHERE COMPANY_ID = ? AND STOCK_EXCHANGE = ?",
					resultClass = StockPrice.class)
@Table(name = "STOCK_PRICE")
public class StockPrice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long stockId;
	
	@ManyToOne
	@JoinColumn(name="companyId")
	private Company company;
	
	@ManyToOne
	@JoinColumn(name = "Stock_Exchange")
	private StockExchange stockExchange;
	
	private float price;
	
	private Date date;
	
//	private LocalTime time;
	
	
}
