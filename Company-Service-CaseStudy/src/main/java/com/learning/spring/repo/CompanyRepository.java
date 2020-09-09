package com.learning.spring.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.learning.spring.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{

	public Optional<Company> findCompanyByCompanyName(String companyName);
	
	public List<Company> findCompanyForStockExchangeById(Long id);
	
	@Query(value = "SELECT * FROM COMPANY WHERE SECTOR = (SELECT SECTOR_ID FROM SECTOR WHERE "
			+ "SECTOR_NAME=:sectorName)",nativeQuery = true)
	public List<Company> getCompanyFromSector(@Param("sectorName")String sectorName);
	 
}
