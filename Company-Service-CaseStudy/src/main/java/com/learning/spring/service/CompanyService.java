package com.learning.spring.service;

import java.util.List;
import java.util.Optional;

import com.learning.spring.dto.CompanyDto;
import com.learning.spring.dto.IpoDetailsDto;
import com.learning.spring.model.Company;

public interface CompanyService {

	
	public Optional<CompanyDto> findCompanyById(Long id);
	
	public CompanyDto addCompany(Company company);
	
	public float getCompanyStockPrice(Company company);
	
	public List<CompanyDto> findAllCompany();
	
//	Function to be done later
//	public List<CompanyDto> getMatchingCompanies(String companyName);
	
	public Optional<CompanyDto> getCompanyDetails(String companyName);
	
	public List<Optional<IpoDetailsDto>> getCompanyIpoDetails(Long id);

	List<CompanyDto> findCompanyForStockExchangeById(Long id);
}
