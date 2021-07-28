package com.samuraiDigital.adminsystem.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler
	@ResponseBody
	public ResponseEntity<?> handleException(HttpClientErrorException e) {
		if (e.getStatusCode() == HttpStatus.OK) {
			return new ResponseEntity<>(e.getStatusText(), e.getStatusCode());
		} else {
			return new ResponseEntity<>(e.getStatusText(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
