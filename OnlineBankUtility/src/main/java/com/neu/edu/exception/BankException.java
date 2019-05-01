package com.neu.edu.exception;

public class BankException extends Exception {

	public BankException(String message) {
		super("BankException-" + message);
	}

	public BankException(String message, Throwable cause) {
		super("BankException-" + message, cause);
	}

}
