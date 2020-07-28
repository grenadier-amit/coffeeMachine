package com.amit.coffeemachine.factory;

import java.util.Map;

import com.amit.coffeemachine.utils.Beverage;
import com.amit.coffeemachine.utils.BeverageType;
import com.amit.coffeemachine.utils.IngredientType;

public interface BeverageFactory {
	Beverage createBeverage(BeverageType beverageName, Map<IngredientType, Integer> IngredientsReq);
	//create a beverage with given ingredient list
}