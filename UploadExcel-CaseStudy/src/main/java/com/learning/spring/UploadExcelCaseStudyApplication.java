package com.learning.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UploadExcelCaseStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(UploadExcelCaseStudyApplication.class, args);
	}

}
