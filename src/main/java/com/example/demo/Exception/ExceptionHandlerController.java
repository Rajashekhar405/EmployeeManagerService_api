package com.example.demo.Exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {
	
	
	@ExceptionHandler
	public String customExceptionHandler(CustomException exception) {
		return exception.getMessage();
	}

}
