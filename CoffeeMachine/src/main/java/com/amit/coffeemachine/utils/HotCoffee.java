package com.amit.coffeemachine.utils;

import java.util.Map;


public class HotCoffee extends Beverage {

	public HotCoffee(BeverageType beverageName, Map<IngredientType, Integer> ingredients) {
		super(beverageName, ingredients);
	}
}