package com.learning.spring.service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learning.spring.dto.CompanyDto;
import com.learning.spring.dto.IpoDetailsDto;
import com.learning.spring.model.Company;
import com.learning.spring.model.IpoDetail;
import com.learning.spring.model.StockExchange;
import com.learning.spring.repo.CompanyRepository;
import com.learning.spring.repo.StockExchangeRepository;

@Service
public class CompanyServiceImpl implements CompanyService {

	CompanyRepository companyRepository;

	StockExchangeRepository stockExchangeRepository;
	
	@Autowired
	Logger logger;
	
	@Autowired
	ModelMapper mapper;
	
	public CompanyServiceImpl(CompanyRepository companyRepository,StockExchangeRepository stockExchangeRepository) {
		this.companyRepository = companyRepository;
		this.stockExchangeRepository= stockExchangeRepository;
	}

	@Override
	@Transactional
	public CompanyDto findCompanyById(Long id) {
		Optional<Company> optionalCompany = companyRepository.findById(id);
		Company companyFromOptional = mapper.map(optionalCompany.get(), Company.class);
		logger.info(companyFromOptional.toString());
		Company company = companyRepository.findCompanyById(id);
		return mapper.map(company,CompanyDto.class);
	}

	@Override
	@Transactional
	public CompanyDto addCompany(Company company) {
//		
//		List<StockExchange> stockExchanges = company.getStockExchanges();
//		
//		for(StockExchange stockExchange : stockExchanges) {
//			
//			if(stockExchangeRepository.findByExhangeName(stockExchange.getExhangeName())!=null)
//			stockExchangeRepository.save(stockExchange);
//		}
		
		companyRepository.save(company);
		return mapper.map(company, CompanyDto.class);
	}

	@Override
	@Transactional
	public float getCompanyStockPrice(Company company) {
		List<IpoDetail> ipoDetails = company.getIpoDetails();

		return ipoDetails.get(0).getPricePerShare();
	}

	@Override
	@Transactional
	public List<CompanyDto> findAllCompany() {
		List<Company> companies = companyRepository.findAll();
		Type listType = new TypeToken<List<CompanyDto>>() {
		}.getType();

		return mapper.map(companies, listType);
	}

	@Override
	@Transactional
	public Optional<CompanyDto> getCompanyDetails(String companyName) {
		Optional<Company> company = companyRepository.findCompanyByCompanyName(companyName);
		Type optionalType = new TypeToken<Optional<CompanyDto>>() {}.getType();
		return mapper.map(company, optionalType);
	}

	@Override
	@Transactional
	public List<Optional<IpoDetailsDto>> getCompanyIpoDetails(Long id) {
		
		Optional<Company> company = companyRepository.findById(id);
		List<IpoDetail> ipoDetails = company.get().getIpoDetails();
		Type optionalListType = new TypeToken<List<Optional<IpoDetailsDto>>>() {}.getType();
		return mapper.map(ipoDetails, optionalListType);	
	}
	
	@Override
	@Transactional
	public List<CompanyDto> findCompanyForStockExchangeById(Long id){
		
		List<Company> companies = companyRepository.findCompanyForStockExchangeById(id);
		Type listType = new TypeToken<List<CompanyDto>>() {}.getType();
		List<CompanyDto> companiesDto=mapper.map(companies, listType);
//		Logging to check the output
		logger.info(companiesDto.toString());
		return companiesDto;
	}

}
