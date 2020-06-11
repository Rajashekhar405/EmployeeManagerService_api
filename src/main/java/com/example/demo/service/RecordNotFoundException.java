package com.example.demo.service;

public class RecordNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;

	public RecordNotFoundException() {

	}

	public RecordNotFoundException(String message) {
		this.message = message;
	}

}
