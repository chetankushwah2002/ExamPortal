package com.adda.app.helper;

public class UserNotFoundException extends Exception{

	public UserNotFoundException() {
		super("user with this username not found in database !!");
		// TODO Auto-generated constructor stub
	}

	public UserNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	

	
}
