package com.learning.spring.dto;

import java.util.List;

import com.learning.spring.model.Sector;
//import com.learning.spring.model.IpoDetail;
//import com.learning.spring.model.Sector;
import com.learning.spring.model.StockExchange;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {
	
	private Long companyId;

	private String companyName;

	private String ceo;

	private Sector sector;
	
	private List<String> boardOfDirectors;
	
	private List<StockExchange> stockExchanges;
	
//	private List<IpoDetail> ipoDetails;
	
	
	
}
