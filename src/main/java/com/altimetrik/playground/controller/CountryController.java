package com.altimetrik.playground.controller;

import javax.ws.rs.BadRequestException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.altimetrik.playground.model.CountryResponse;
import com.altimetrik.playground.service.CountryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value="Country API", description="API to return code information", basePath="/api/v1" )
@RestController
public class CountryController {

	private CountryService service;

	public CountryController(CountryService service) {
		this.service = service;
	}
	
/*	@ApiOperation(value = "Get All country details", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully retrieved country list"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@GetMapping("country")
	public List<CountryResponse> getAllCountryDetails(){
		return this.service.getAllCountryDetails();
	}*/
	

	@ApiOperation(value = "Get Country Details for a given country code", response = CountryResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully retrieved country details"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@GetMapping("country/{countrycode}")
	public CountryResponse getCountryDetails(
			@ApiParam(value = "3 letter country code", required = true, name = "countrycode") @PathVariable("countrycode") String countrycode) {
		if (countrycode == null) {
			throw new BadRequestException("Country code cannot be left blank");
		} else if (countrycode.length() != 3) {
			throw new BadRequestException("Country code should of length 3 characters.");
		}

		return this.service.getCountryDetails(countrycode);
	}

}
