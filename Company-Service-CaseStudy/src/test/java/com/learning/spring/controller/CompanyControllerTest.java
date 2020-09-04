package com.learning.spring.controller;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
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
	}
	
	
	@Test
	public void A_findAllCompany() throws Exception {
		 mockMvc.perform(MockMvcRequestBuilders.get("/api/companies")
				 .accept(MediaType.APPLICATION_JSON)).andDo(print())
		 		 .andExpect(jsonPath("$", hasSize(0))).andReturn();
	}
	
	
	@Test
	public void B_saveCompany() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api/companies/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n" + 
						"    \"companyName\":\"SocGen\",\r\n" + 
						"    \"turnover\":100000,\r\n" + 
						"    \"ceo\":\"Frederic Oudea\",\r\n" + 
						"    \"sector\":{\r\n" + 
						"        \"sectorName\":\"IT\",\r\n" + 
						"        \"brief\" : \"...IT...\"\r\n" + 
						"    },\r\n" + 
						"    \"boardOfDirectors\":[\"Philippe Aymerich\",\"Francois Bloch\"],\r\n" + 
						"    \"stockExchanges\":[{\r\n" + 
						"        \"address\":\"same address\",\r\n" + 
						"        \"exhangeName\":\"NSE\",\r\n" + 
						"        \"brief\":\"National Stock Exchange\",\r\n" + 
						"        \"remarks\":\"Biggest Indian Exchange\"\r\n" + 
						"    }],\r\n" + 
						"    \"ipoDetails\":[{\r\n" + 
						"        \"companyName\":1,\r\n" + 
						"        \"pricePerShare\":100,\r\n" + 
						"        \"numberOfShare\":100000,\r\n" + 
						"        \"openDate\": \"2020-08-20\",\r\n" + 
						"        \"brief\":\"First IPO\"\r\n" + 
						"\r\n" + 
						"    }]\r\n" + 
						"\r\n" + 
						"}")
				.accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print())
				.andExpect(jsonPath("$.companyId").exists())
				.andExpect(jsonPath("$.companyName").exists())
				.andExpect(jsonPath("$.companyName").value("SocGen"))
				.andExpect(jsonPath("$.ceo").value("Frederic Oudea"))
				.andReturn();	
	}
	
	@Test
	public void C_findAllCompanyAfterPersisit() throws Exception {
		 mockMvc.perform(MockMvcRequestBuilders.get("/api/companies")
				 .accept(MediaType.APPLICATION_JSON)).andDo(print())
		 		 .andExpect(jsonPath("$", hasSize(1))).andReturn();
	}
	
	
	
	
	
	
	
}
