package com.amit.coffeemachine.utils;

public enum IngredientType {
	hot_water("hot_water"), hot_milk("hot_milk"), tea_leaves_syrup("tea_leaves_syrup"), ginger_syrup("ginger_syrup"),
	sugar_syrup("sugar_syrup"), elaichi_syrup("elaichi_syrup"), green_mixture("green_mixture"),
	coffee_syrup("coffee_syrup");

	private String ingredientName;

	IngredientType(String name) {
		ingredientName = name;
	}

	public String getIngredientName() {
		return ingredientName;
	}

}