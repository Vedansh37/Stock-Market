package com.learning.spring.dto;

import java.util.Date;

import com.learning.spring.model.Company;
import com.learning.spring.model.StockExchange;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockPriceDto {
	
	private Long stockId;
	private Company company;
	private StockExchange stockExchange;
	private float price;
	private Date date;
}
