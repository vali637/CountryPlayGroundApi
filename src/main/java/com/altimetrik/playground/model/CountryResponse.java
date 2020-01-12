package com.altimetrik.playground.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="Country Details")
public class CountryResponse {
	@ApiModelProperty(value="Country Name")
	@JsonProperty(value="name")
	private String countryName;
	@JsonProperty(value="id")
	@ApiModelProperty(value="Country code")
	private String countryCode;
	@ApiModelProperty(value="Country capital city name")
	@JsonProperty(value="capitalCity")
	private String capitalCity;
	@ApiModelProperty(value="Country latitude position")
	@JsonProperty(value="latitude")
	private String latitude;
	@ApiModelProperty(value="Country longitude position")
	@JsonProperty(value="longitude")
	private String longitude;
	
	
	
	public CountryResponse(String countryName, String countryCode, String capitalCity, String latitude,
			String longitude) {
		super();
		this.countryName = countryName;
		this.countryCode = countryCode;
		this.capitalCity = capitalCity;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getCapitalCity() {
		return capitalCity;
	}
	public void setCapitalCity(String capitalCity) {
		this.capitalCity = capitalCity;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
}
