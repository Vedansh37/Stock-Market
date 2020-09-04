package com.learning.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StockExchangeDto {

//	private Long stockExchangeId;
	
	private String exchangeName;
	
	private String brief;
	
//	private String address;
//	
//	private String remarks;
}
