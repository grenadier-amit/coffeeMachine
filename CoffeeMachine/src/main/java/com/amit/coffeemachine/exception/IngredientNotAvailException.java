package com.amit.coffeemachine.exception;

import com.amit.coffeemachine.utils.IngredientType;

public class IngredientNotAvailException extends RuntimeException {

	private static final long serialVersionUID = 1L;
//Ingredient's quantity did not fulfill the beverage's requirement
	public IngredientNotAvailException(final IngredientType message) {
		super(message.getIngredientName());
	}

}