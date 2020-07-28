package com.amit.coffeemachine.utils;

import java.util.Map;

public abstract class Beverage {
	private final BeverageType beverageName;
	private final Map<IngredientType, Integer> ingredients;

	public Beverage(BeverageType beverageName, Map<IngredientType, Integer> ingredients) {
		this.beverageName = beverageName;
		this.ingredients = ingredients;
	}

	public BeverageType getBeverageName() {
		return beverageName;
	}

	public Map<IngredientType, Integer> getIngredients() {
		return ingredients;
	}
	
	public void prepare() throws RuntimeException {	//prepare a beverage and remove ingredients required from coffee machine
		final IngredientsAvail ingredientAvail = IngredientsAvail.getInstance();
		ingredientAvail.updateIngredients(getIngredients(),IngredientUpdateType.remove);
	}

}