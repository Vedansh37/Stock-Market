package com.learning.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class StockExchange {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "StockExchange_Id")
	private Long stockExchangeId;
	
	@Column(unique = true)
	private String exchangeName;
	
	private String brief;
	
	private String address;
	
	private String remarks;
	
	public StockExchange(String exhangeName, String brief, String address, String remarks) {
		super();
		this.exchangeName = exhangeName;
		this.brief = brief;
		this.address = address;
		this.remarks = remarks;
	}
	
	
}
