package com.learning.spring;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StockExchangeCaseStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockExchangeCaseStudyApplication.class, args);
	}

	@Bean
	public  ModelMapper Mapper(){
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return mapper;
	}
}
