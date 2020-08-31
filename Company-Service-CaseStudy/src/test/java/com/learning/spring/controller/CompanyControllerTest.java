package com.learning.spring.controller;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.learning.spring.CompanyServiceCaseStudyApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=CompanyServiceCaseStudyApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CompanyControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	public void findAllCompany() throws Exception {
		 mockMvc.perform(MockMvcRequestBuilders.get("/api/companies")
				 .accept(MediaType.APPLICATION_JSON)).andDo(print())
		 		 .andExpect(jsonPath("$", hasSize(0))).andReturn();
	}
	
	
	
}
