package com.learning.spring.service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learning.spring.dto.CompanyDto;
import com.learning.spring.dto.IpoDetailsDto;
import com.learning.spring.intercomm.StockExchangeClient;
import com.learning.spring.model.Company;
import com.learning.spring.model.IpoDetail;
import com.learning.spring.model.StockExchange;
import com.learning.spring.repo.CompanyRepository;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

	CompanyRepository companyRepository;

	
	@Autowired
	StockExchangeClient stockExchangeClient;
	
	@Autowired
	Logger logger;
	
	@Autowired
	ModelMapper mapper;
	
	public CompanyServiceImpl(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}

	@Override
	public CompanyDto findCompanyById(Long id) {
//		Optional<Company> optionalCompany = companyRepository.findById(id);
//		logger.info(optionalCompany.get().toString());
		Company company = companyRepository.findCompanyById(id);
		return mapper.map(company,CompanyDto.class);
	}

	@Override
	public CompanyDto addCompany(Company company) {
		
		List<StockExchange> stockExchanges = company.getStockExchanges();
		
//		 List<StockExchange> stockExchangesListed = new ArrayList<>();
		 
//		 for(StockExchange stockExchange : stockExchanges) {
//			 String exhangeName = stockExchange.getExchangeName();
//			 if(stockExchangeClient.findStockExchangeByExchangeName(exhangeName)!=null) { 
//				 stockExchanges.remove(stockExchange);
//				 logger.info(stockExchange.toString());
//				 logger.info(stockExchanges.toString());
//				 stockExchangesListed.add(stockExchange); 
//			 } 
//		 
		 
		 companyRepository.save(company);
		
//		Communicating with Feign Client Stock Exchange for data integrity
		for(StockExchange stockExchange: stockExchanges) {
			stockExchangeClient.addStockExchange(stockExchange);
		}
		
//		  for(StockExchange stockExchange: stockExchangesListed) {
//			  company.getStockExchanges() .add(stockExchange); 
//		 }
		 
		return mapper.map(company, CompanyDto.class);
	}

	@Override
	public float getCompanyStockPrice(Company company) {
		List<IpoDetail> ipoDetails = company.getIpoDetails();

		return ipoDetails.get(0).getPricePerShare();
	}

	@Override
	public List<CompanyDto> findAllCompany() {
		List<Company> companies = companyRepository.findAll();
		Type listType = new TypeToken<List<CompanyDto>>() {
		}.getType();
		
		return mapper.map(companies, listType);
	}

	@Override
	public Optional<CompanyDto> getCompanyDetails(String companyName) {
		Optional<Company> company = companyRepository.findCompanyByCompanyName(companyName);
		Type optionalType = new TypeToken<Optional<CompanyDto>>() {}.getType();
		return mapper.map(company, optionalType);
	}

	@Override
	public List<Optional<IpoDetailsDto>> getCompanyIpoDetails(Long id) {
		
		Optional<Company> company = companyRepository.findById(id);
		List<IpoDetail> ipoDetails = company.get().getIpoDetails();
		Type optionalListType = new TypeToken<List<Optional<IpoDetailsDto>>>() {}.getType();
		return mapper.map(ipoDetails, optionalListType);	
	}
	
	@Override
	public List<CompanyDto> findCompanyForStockExchangeById(Long id){
		
		List<Company> companies = companyRepository.findCompanyForStockExchangeById(id);
		Type listType = new TypeToken<List<CompanyDto>>() {}.getType();
		List<CompanyDto> companiesDto=mapper.map(companies, listType);
//		Logging to check the output
		logger.info(companiesDto.toString());
		return companiesDto;
	}

}
