package com.amit.coffeemachine.utils;

public enum IngredientUpdateType {
	add(-1), remove(1);
//Add and remove operation for a given ingredient
	private int operation;

	IngredientUpdateType(int operation) {
		this.operation = operation;
	}

	public int getOperation() {
		return operation;
	}
	
}
