package com.amit.coffeemachine.exception;

import com.amit.coffeemachine.utils.BeverageType;

public class InvalidBeverageException extends RuntimeException {
	private static final long serialVersionUID = 1L;
//initialized beverage is requested by user
	public InvalidBeverageException(final BeverageType message) {
		super("Invalid Beverage "+ message.getBeverageName());
	}

}
