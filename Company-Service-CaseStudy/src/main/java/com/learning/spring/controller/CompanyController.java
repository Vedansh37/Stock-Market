package com.learning.spring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.spring.dto.CompanyDto;
import com.learning.spring.dto.IpoDetailsDto;
import com.learning.spring.model.Company;
import com.learning.spring.service.CompanyService;


@RestController
@RequestMapping("/api")
public class CompanyController {

	CompanyService companyService;

	public CompanyController(CompanyService companyService) {
		super();
		this.companyService = companyService;
	}
	
	@GetMapping("companies/id/{id}")
	public ResponseEntity<CompanyDto> findCompanyById(@PathVariable("id") Long id){
		CompanyDto companyDto = companyService.findCompanyById(id);
//		System.out.println(companyDto.get());
//		return companyDto;
		return ResponseEntity.status(HttpStatus.FOUND).body(companyDto);
//		return new ResponseEntity<Optional<CompanyDto>>(companyService.findCompanyById(id),HttpStatus.FOUND);
	}
	
	@PostMapping("/companies/add")
	public ResponseEntity<CompanyDto> addCompany(@RequestBody Company company){
		
		return new ResponseEntity<CompanyDto>(companyService.addCompany(company),HttpStatus.CREATED);
	}
	
	@GetMapping("/companies")
	public ResponseEntity<List<CompanyDto>> findAllCompany(){
		return new ResponseEntity<List<CompanyDto>>(companyService.findAllCompany(),HttpStatus.OK);
	}

	@GetMapping("/companies/name/{name}")
	public ResponseEntity<Optional<CompanyDto>> getCompanyDetails(@PathVariable("name") String cname){
		
		return ResponseEntity.status(HttpStatus.FOUND).body(companyService.getCompanyDetails(cname));
	}
	
	@GetMapping("/companies/ipoDetails/{id}")
	public ResponseEntity<List<Optional<IpoDetailsDto>>> getCompanyIpoDetails(@PathVariable("id") Long companyId){
		return ResponseEntity.status(HttpStatus.FOUND).body(companyService.getCompanyIpoDetails(companyId));
	}
	
	@GetMapping("/companies/stockexchange/{id}")
	public ResponseEntity<List<CompanyDto>> findCompanyForStockExchangeById(@PathVariable("id") Long id){
		return ResponseEntity.status(HttpStatus.FOUND).body(companyService.findCompanyForStockExchangeById(id));
	}
}
