package com.altimetrik.playground.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.ws.rs.BadRequestException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.altimetrik.playground.model.ApiErrorResponse;

@RestControllerAdvice
public class APIExceptionHandler {

	@ExceptionHandler(value = BadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrorResponse handleBadRequestException(RuntimeException ex, WebRequest req){
		return buildApiErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
	}
	
	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ApiErrorResponse handleException(RuntimeException ex, WebRequest req){
		return buildApiErrorResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
	}
	
	private ApiErrorResponse buildApiErrorResponse(String message, int status) {
		ApiErrorResponse resp = new ApiErrorResponse();
		resp.setMessage(message);
		resp.setStatus(status);
		LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = now.format(formatter);
		resp.setTimeStamp(formatDateTime);
		return resp; 
	}
}
