package com.example.demo.Exception;

public class RecordNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;
	public RecordNotFoundException(String msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "RecordNotFoundException [msg=" + msg + "]";
	}
	

}
