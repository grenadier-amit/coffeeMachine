package com.amit.coffeemachine.exception;

import com.amit.coffeemachine.utils.IngredientType;

public class InvalidOperationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
//trying to add negative quantity for a beverage
	public InvalidOperationException(final IngredientType message) {
		super(message.getIngredientName());
	}

}
