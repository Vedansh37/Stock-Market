	package com.learning.spring.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedNativeQuery(name = "Company.findCompanyForStockExchangeById",
				  query = "SELECT * FROM COMPANY WHERE COMPANY_ID IN "
				  		+ "(SELECT COMPANY_ID FROM COMPANY_STOCKEXCHANGES WHERE STOCK_EXCHANGE_ID= ?)",
				  resultClass = Company.class)
public class Company{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long companyId;
	
	
	private String companyName;
	
	private int turnover;
	
	private String ceo;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
	@JoinColumn(name="Sector")
	private Sector sector;
	
//	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private String[] boardOfDirectors = new String[2];
	
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
	@JoinTable(name = "Company_Stockexchanges",
				joinColumns = {@JoinColumn(name="Company_Id")},
				inverseJoinColumns = {@JoinColumn(name="StockExchange_Id")})
	private Set<StockExchange> stockExchanges;
	
	
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//	@JoinColumn(name="companyName")
	private List<IpoDetail> ipoDetails;


	@Override
	public String toString() {
		return "Company [companyId=" + companyId + ", companyName=" + companyName + ", turnover=" + turnover + ", ceo="
				+ ceo + "]";
	}

	
	
}
