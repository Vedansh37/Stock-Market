package com.learning.spring;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class CompanyServiceCaseStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanyServiceCaseStudyApplication.class, args);
	}
	
	@Bean
	public Logger logger() {
		Logger logger = LoggerFactory.getLogger(getClass());
		return logger;
	}
	
	@Bean
	public ModelMapper mapper() {
		 ModelMapper mapper = new ModelMapper();
		 mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		 
		 return mapper;
	}

}
