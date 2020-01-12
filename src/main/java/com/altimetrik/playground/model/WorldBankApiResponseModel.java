package com.altimetrik.playground.model;

import java.util.List;

public class WorldBankApiResponseModel {
	private Object[] responseList;

		
	public Object[] getResponseList() {
		return responseList;
	}


	public void setResponseList(Object[] responseList) {
		this.responseList = responseList;
	}


	public Pagination getPaginationDetails() {
		return (Pagination)this.responseList[0];
	}
	
	
	public List<CountryResponse> getCountryDetails(){
		return (List<CountryResponse>) this.responseList[1];
	}	

}
