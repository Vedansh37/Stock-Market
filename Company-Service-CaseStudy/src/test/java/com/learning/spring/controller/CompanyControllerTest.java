package com.learning.spring.controller;

import static org.hamcrest.CoreMatchers.is;
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
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultHandler;
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
						"        \"exchangeName\":\"NSE\",\r\n" + 
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
	
	@Test
	public void D_addSecondCompany() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.post("/api/companies/add")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\r\n" + 
								"    \"companyName\":\"Jio\",\r\n" + 
								"    \"turnover\":50000,\r\n" + 
								"    \"ceo\":\"Mukesh Ambani\",\r\n" + 
								"    \"sector\":{\r\n" + 
								"        \"sectorName\":\"IT\",\r\n" + 
								"        \"brief\" : \"...IT...\"\r\n" + 
								"    },\r\n" + 
								"    \"boardOfDirectors\":[\"Muker Ambani\",\"Nita Ambani\"],\r\n" + 
								"    \"stockExchanges\":[{\r\n" + 
								"        \"address\":\"same address\",\r\n" + 
								"        \"exchangeName\":\"NSE\",\r\n" + 
								"        \"brief\":\"National Stock Exchange\",\r\n" + 
								"        \"remarks\":\"Biggest Indian Exchange\"\r\n" + 
								"    }]\r\n" + 
								"\r\n" + 
								"}")
					.accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print())
					.andExpect(jsonPath("$.companyName").exists())
					.andExpect(jsonPath("$.sector.sectorId").value(1))
					.andExpect(jsonPath("$.stockExchanges[0].stockExchangeId",is(1)))
					.andReturn();	
	}
	
	@Test
	public void E_getCompaniesFromExchange() throws Exception{
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/companies/stockexchange/NSE")
				.accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print())
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].companyName", is("SocGen")))
				.andExpect(jsonPath("$[1].companyName", is("Jio")))
				.andReturn();
				
	}
	
	@Test
	public void F_postStockPrice() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.post("/api/stockprices/add")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\r\n" + 
							"    \"price\":93.2,\r\n" + 
							"    \"company\":{\r\n" + 
							"        \"companyName\":\"SocGen\"\r\n" + 
							"        },\r\n" + 
							"    \"date\":\"2020-05-22\",\r\n" + 
							"    \"stockExchange\":{\r\n" + 
							"        \"exchangeName\":\"NSE\"\r\n" + 
							"    }\r\n" + 
							"}").accept(MediaType.APPLICATION_JSON))
					.andDo(MockMvcResultHandlers.print())
					.andExpect(jsonPath("$.stockId").exists())
					.andExpect(jsonPath("$.company.companyName", is("SocGen")))
					.andReturn();
	}
	
	
	
	
	
}
