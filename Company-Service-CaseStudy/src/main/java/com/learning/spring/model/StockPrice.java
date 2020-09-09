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
@NamedNativeQuery(name="StockPrice.getAllStockPricesByCompany",
					query = "SELECT * FROM STOCK_PRICE WHERE COMPANY_ID in (SELECT COMPANY_ID FROM COMPANY"
							+ " WHERE COMPANY_NAME = ?) AND STOCK_EXCHANGE IN (SELECT STOCK_EXCHANGE_ID FROM "
							+ "STOCK_EXCHANGE WHERE EXCHANGE_NAME = ?)",
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
