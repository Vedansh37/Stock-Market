package com.learning.spring.service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learning.spring.dto.CompanyDto;
import com.learning.spring.dto.IpoDetailsDto;
import com.learning.spring.dto.StockPriceDto;
import com.learning.spring.intercomm.StockExchangeClient;
import com.learning.spring.model.Company;
import com.learning.spring.model.IpoDetail;
import com.learning.spring.model.Sector;
import com.learning.spring.model.StockExchange;
import com.learning.spring.model.StockPrice;
import com.learning.spring.repo.CompanyRepository;
import com.learning.spring.repo.SectorRepository;
import com.learning.spring.repo.StockExchangeRepository;
import com.learning.spring.repo.StockPriceRepository;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

	CompanyRepository companyRepository;
	StockPriceRepository stockPriceRepository;
	StockExchangeRepository stockExchangeRepository;
	
	@Autowired
	SectorRepository sectorRepository;
	
	@Autowired
	StockExchangeClient stockExchangeClient;
	
	@Autowired
	Logger logger;
	
	@Autowired
	ModelMapper mapper;
	
	public CompanyServiceImpl(CompanyRepository companyRepository, StockPriceRepository stockPriceRepository,
			StockExchangeRepository stockExchangeRepository) {
		super();
		this.companyRepository = companyRepository;
		this.stockPriceRepository = stockPriceRepository;
		this.stockExchangeRepository = stockExchangeRepository;
	}


	@Override
	public CompanyDto findCompanyById(Long id) {
		Optional<Company> optionalCompany = companyRepository.findById(id);
		
		if(!optionalCompany.isPresent()) {
			return null;
		}

		return mapper.map(optionalCompany.get(),CompanyDto.class);
	}


	@Override
	public CompanyDto addCompany(Company company) {
		
		Set<StockExchange> stockExchanges = company.getStockExchanges();
		
		for(StockExchange stockExchange: stockExchanges) {
//			StockExchanges if already exists won't be persisted 
			
			Optional<StockExchange> optionalExchange = stockExchangeRepository.
					findByExchangeName(stockExchange.getExchangeName());
			if(optionalExchange.isPresent()){
				company.getStockExchanges().remove(stockExchange);
				company.getStockExchanges().add(optionalExchange.get());
			}
			else {
//				Communicating with Feign Client Stock Exchange for data integrity
				stockExchangeClient.addStockExchange(stockExchange);
			}
		}
		
		Optional<Sector> sector = sectorRepository.findBySectorName(company.getSector().getSectorName());
		if(sector.isPresent()) {
			company.setSector(sector.get());
		}
		companyRepository.save(company);
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
	public List<CompanyDto> findCompanyForStockExchangeById(String exchangeName){
		
		Optional<StockExchange> exchange = stockExchangeRepository.findByExchangeName(exchangeName);
		List<Company> companies = companyRepository.
							findCompanyForStockExchangeById(exchange.get().getStockExchangeId());
		Type listType = new TypeToken<List<CompanyDto>>() {}.getType();
		List<CompanyDto> companiesDto=mapper.map(companies, listType);
//		Logging to check the output
		logger.info(companiesDto.toString());
		return companiesDto;
	}

	@Override
	public StockPriceDto addStockPrice(StockPrice stockPrice) {
		Company company = stockPrice.getCompany();
		StockExchange stockExchange = stockPrice.getStockExchange();
		Optional<Company> optionalCompany= companyRepository.findCompanyByCompanyName(company.getCompanyName());
//		Setting the company to the one already in the repository
		stockPrice.setCompany(optionalCompany.get());
		Optional<StockExchange> optionalExchange = stockExchangeRepository.findByExchangeName(stockExchange.getExchangeName());
//		Setting the Stock Exchange to the one already in the repository
		stockPrice.setStockExchange(optionalExchange.get());
		
		stockPriceRepository.save(stockPrice);
		
		return mapper.map(stockPrice, StockPriceDto.class);
	}

	@Override
	public List<StockPriceDto> getAllStockPricesByCompany(String companyName, String exchangeName) {
		List<StockPrice> allStockPrices = stockPriceRepository
										.getAllStockPricesByCompany(companyName,exchangeName);
		Type listType = new TypeToken<List<StockPriceDto>>() {}.getType();
		
		return mapper.map(allStockPrices, listType);
	}

}
