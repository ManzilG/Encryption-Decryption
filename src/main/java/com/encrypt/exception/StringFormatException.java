package com.encrypt.exception;

//Custom Exception Class for throwing exceptions for input strings format mismatch and empty strings and null
public class StringFormatException extends Exception {
	public StringFormatException(String s) {
		super(s);
	}

}
