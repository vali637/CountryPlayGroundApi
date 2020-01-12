package com.altimetrik.playground.controller;

import static org.mockito.Mockito.when;

import javax.ws.rs.BadRequestException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.altimetrik.playground.model.CountryResponse;
import com.altimetrik.playground.service.CountryService;

@RunWith(MockitoJUnitRunner.class)
public class CountryControllerTest {

	@Mock
	private CountryService service;
	
	private CountryController controller;
	
	@Before
	public void setup() {
		this.controller = new CountryController(service);
	}
	
	@Test(expected =BadRequestException.class)
	public void testCountryCodeNull() {
		this.controller.getCountryDetails(null);
	}
	
	@Test(expected = BadRequestException.class)
	public void testInvalidCountryCode() {
		this.controller.getCountryDetails("A");
	}
	
	@Test(expected = BadRequestException.class)
	public void testInvalidCountryCode2() {
		when(service.getCountryDetails("ATN")).thenThrow(new BadRequestException("Bad request"));
		this.controller.getCountryDetails("ATN");
	}
	
	@Test
	public void testValidCountryCode() {
		CountryResponse mockresponse = new CountryResponse("India", "IND", "New Delhi", "1111", "2222");
		when(service.getCountryDetails("IND")).thenReturn(mockresponse);
		CountryResponse response = this.controller.getCountryDetails("IND");
		Assert.assertNotNull(response);
		Assert.assertEquals(mockresponse.getCountryName(), response.getCountryName());
	}
	
}
