package com.altimetrik.playground.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.BadRequestException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import com.altimetrik.playground.model.CountryResponse;

@RunWith(MockitoJUnitRunner.class)
public class CountryServiceTest {
	@Mock
	private RestTemplate template;
	
	private CountryService service;
	
	@Before
	public void setUp() {
		
		this.service = new CountryService(template);
	}
	
	
	@Test
	public void testGetCountryDetails() {
		Object obj[] = new Object[2];
		Map<String, String> pagination = new LinkedHashMap<>();
		// page=1, pages=1, per_page=50, total=1},
		pagination.put("page", "1");
		pagination.put("pages", "1");
		pagination.put("per_page", "50");
		pagination.put("total", "1");
		
		List<Map<String, String>> content = new ArrayList<>();
		Map<String, String> details = new LinkedHashMap<>();
		details.put("id", "IND");
		details.put("name", "India");
		details.put("capitalCity", "New Delhi");
		details.put("latitude", "1122");
		details.put("longitude", "111");
		//CountryResponse(detailsMap.get("name"), detailsMap.get("id"), detailsMap.get("capitalCity"),
		// detailsMap.get("latitude"), detailsMap.get("longitude"));
		content.add(details);
		obj[0]= pagination;
		obj[1]= content;
		
		when(template.getForObject("http://api.worldbank.org/v2/country/IND?format=json", Object[].class)).thenReturn(obj);
		
		CountryResponse response = this.service.getCountryDetails("IND");
		Assert.assertEquals("India", response.getCountryName());
		Assert.assertEquals("IND", response.getCountryCode());
		Assert.assertEquals("New Delhi", response.getCapitalCity());
		Assert.assertEquals("1122", response.getLatitude());
		Assert.assertEquals("111", response.getLongitude());
	}
	
	@Test(expected=BadRequestException.class)
	public void testGetInvalidCountryDetails() {
		//{message=[{id=120, key=Invalid value, value=The provided parameter value is not valid}]}
		Object obj[] = new Object[1];
		
		List<Map<String, String>> content = new ArrayList<>();
		Map<String, String> details = new LinkedHashMap<>();
		details.put("id", "120");
		details.put("key", "Invalid value");
		details.put("value", "The provided parameter value is not valid");
		content.add(details);
		Map<String, List<Map<String, String>>> map = new LinkedHashMap<>();
		map.put("message", content);
		obj[0]= map;
		
		when(template.getForObject("http://api.worldbank.org/v2/country/IND?format=json", Object[].class)).thenReturn(obj);
		
		this.service.getCountryDetails("IND");
	}
	

}
