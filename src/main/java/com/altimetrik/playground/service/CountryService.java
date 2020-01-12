package com.altimetrik.playground.service;

import java.util.List;
import java.util.Map;

import javax.ws.rs.BadRequestException;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.altimetrik.playground.model.CountryResponse;

@Service
public class CountryService {
	
	private RestTemplate resttemplate;
	

	public CountryService(RestTemplate resttemplate) {
		super();
		this.resttemplate = resttemplate;
	}

	public CountryResponse getCountryDetails(String code) {
		System.out.println(" Country Code == " + code);
		Object[] response = resttemplate.getForObject("http://api.worldbank.org/v2/country/" + code + "?format=json",
				Object[].class);
		// System.out.println("response ::: " + response);
		if(response.length !=2) {
			Map<String, List<Map<String, String>>> error = (Map<String, List<Map<String, String>>>)response[0];
			List<Map<String, String>> errorList = error.get("message");
			throw new BadRequestException(errorList.get(0).get("value"));
		}
		List<Map<String, String>> list = (List<Map<String, String>>) response[1];
		Map<String, String> detailsMap = list.get(0);
		return new CountryResponse(detailsMap.get("name"), detailsMap.get("id"), detailsMap.get("capitalCity"),
				detailsMap.get("latitude"), detailsMap.get("longitude"));
	}

	/*public List<CountryResponse> getAllCountryDetails() {
		System.out.println("Get All Country Details ...");
		WorldBankApiResponseModel response = resttemplate.getForObject("http://api.worldbank.org/v2/country?format=json", WorldBankApiResponseModel.class);
		System.out.println("Response :: "+ response);
		return response.getCountryDetails();
	}*/
}
