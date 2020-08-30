package com.learning.spring.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.spring.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{

	public Optional<Company> findCompanyByCompanyName(String companyName);
	
	public List<Company> findCompanyForStockExchangeById(Long id);
}
