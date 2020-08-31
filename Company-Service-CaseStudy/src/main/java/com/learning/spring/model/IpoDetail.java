package com.learning.spring.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="IPO_Detail")
public class IpoDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ipoId;
	
//	@Column(name = "Company_Name")
//	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Long companyName;
//	
	
//	@Column(name="StockExchange")
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="stockExchange")
	private StockExchange stockExchange;
	
	@Column(name = "Price_Per_Share")
	private float pricePerShare;
	
	@Column(name="Number_Of_Shares")
	private int numberOfShare;
	
	@Column(name = "Open_Date")
	private Date openDate;
	
	private String brief;
}
