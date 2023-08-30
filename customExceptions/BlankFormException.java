package com.customExceptions;

public class BlankFormException extends Exception {

	private static final long serialVersionUID = 1L;

	private static String message = "The form Cannot be Blank. Do add atleast one component";

	public BlankFormException() {
		super(message);
	}

}
