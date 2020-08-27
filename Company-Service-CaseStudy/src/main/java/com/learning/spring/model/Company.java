package com.learning.spring.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long companyId;
	
	
	private String companyName;
	
	private int turnover;
	
	private String ceo;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="Sector")
	private Sector sector;
//	private String sector;
	
//	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private String[] boardOfDirectors = new String[2];
	
	
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "companyId")
	private List<StockExchange> stockExchanges;
	
	
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "companyName")
//	@JoinColumn(name="companyName")
	private List<IpoDetail> ipoDetails;
	
	
}
