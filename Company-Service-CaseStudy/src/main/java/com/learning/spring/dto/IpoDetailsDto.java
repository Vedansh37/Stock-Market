package com.learning.spring.dto;

import java.util.Date;

import com.learning.spring.model.StockExchange;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IpoDetailsDto {

	private Long ipoId;
	private String companyName;
	private StockExchange stockExchange;
	private float pricePerShare;
	private int numberOfShare;
	private Date openDate;

	private String brief;
}
