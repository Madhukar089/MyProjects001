package com.customExceptions;

public class InsertException extends Exception {

	private static final long serialVersionUID = 1L;

	private static String message = "An error occurred while updating the data, Please do reload the page!";

	public InsertException() {
		super(message);
	}

}
