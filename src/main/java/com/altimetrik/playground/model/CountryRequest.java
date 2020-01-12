package com.altimetrik.playground.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CountryRequest {

	@NotNull(message="Country code cannot be left blank")
	@Size(min = 3, max = 3, message= "Country code must be of 3 charecters")
	private String countrycode;

	public String getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}
}
