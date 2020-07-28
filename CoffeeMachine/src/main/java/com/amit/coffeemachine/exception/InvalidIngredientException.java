package com.amit.coffeemachine.exception;

import com.amit.coffeemachine.utils.IngredientType;

public class InvalidIngredientException extends RuntimeException {

	private static final long serialVersionUID = 1L;
//un-initialized ingredient is requested for a beverage
	public InvalidIngredientException(final IngredientType message) {
		super(message.getIngredientName());
	}

}