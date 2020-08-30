package com.learning.spring.model;


import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockPrice {

	@Id
	private Long stockId;
	
	private Long companyId;
	
	@ManyToOne
	@JoinColumn(name = "Stock_Exchange")
	private StockExchange stockExchange;
	
	private float price;
	
	private Date date;
	
//	private LocalTime time;
	
	
}
