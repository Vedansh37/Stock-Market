package com.learning.spring.service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learning.spring.dto.CompanyDto;
import com.learning.spring.dto.IpoDetailsDto;
import com.learning.spring.model.Company;
import com.learning.spring.model.IpoDetail;
import com.learning.spring.repo.CompanyRepository;

@Service
public class CompanyServiceImpl implements CompanyService {

	CompanyRepository companyRepository;

	public CompanyServiceImpl(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}

	@Override
	@Transactional
	public Optional<CompanyDto> findCompanyById(Long id) {
		Optional<Company> company = companyRepository.findById(id);
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Type optionalType = new TypeToken<Optional<CompanyDto>>() {
		}.getType();

		return mapper.map(company, optionalType);
	}

	@Override
	@Transactional
	public CompanyDto addCompany(Company company) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
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
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Type listType = new TypeToken<List<CompanyDto>>() {
		}.getType();

		return mapper.map(companies, listType);
	}

	@Override
	@Transactional
	public Optional<CompanyDto> getCompanyDetails(String companyName) {
		Optional<Company> company = companyRepository.findCompanyByCompanyName(companyName);
		Type optionalType = new TypeToken<Optional<CompanyDto>>() {}.getType();
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return mapper.map(company, optionalType);
	}

	@Override
	@Transactional
	public List<Optional<IpoDetailsDto>> getCompanyIpoDetails(Long id) {
		
		Optional<Company> company = companyRepository.findById(id);
		List<IpoDetail> ipoDetails = company.get().getIpoDetails();
		Type optionalListType = new TypeToken<List<Optional<IpoDetailsDto>>>() {}.getType();
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return mapper.map(ipoDetails, optionalListType);
		
		
	}

}
