package com.learning.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockExchange {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "StockExchange_Id")
	private Long stockExchangeId;
	
	private String exhangeName;
	
	private String brief;
	
	private String address;
	
	private String remarks;
	
}
