package com.amit.coffeemachine.factory.impl;

import java.util.Map;

import com.amit.coffeemachine.exception.InvalidBeverageException;
import com.amit.coffeemachine.factory.BeverageFactory;
import com.amit.coffeemachine.utils.Beverage;
import com.amit.coffeemachine.utils.BeverageType;
import com.amit.coffeemachine.utils.BlackTea;
import com.amit.coffeemachine.utils.ElaichiTea;
import com.amit.coffeemachine.utils.GingerTea;
import com.amit.coffeemachine.utils.GreenTea;
import com.amit.coffeemachine.utils.HotCoffee;
import com.amit.coffeemachine.utils.HotMilk;
import com.amit.coffeemachine.utils.HotTea;
import com.amit.coffeemachine.utils.HotWater;
import com.amit.coffeemachine.utils.IngredientType;

public class BeverageFactoryImpl implements BeverageFactory {

	@Override
	public Beverage createBeverage(BeverageType beverageName, Map<IngredientType, Integer> IngredientsReq) throws RuntimeException {
		
		switch (beverageName) {
		case hot_tea:
			return new HotTea(beverageName,IngredientsReq);
		case hot_coffee:
			return new HotCoffee(beverageName,IngredientsReq);
		case black_tea:
			return new BlackTea(beverageName,IngredientsReq);
		case green_tea:
			return new GreenTea(beverageName,IngredientsReq);
		case ginger_tea:
			return new GingerTea(beverageName,IngredientsReq);
		case elaichi_tea:
			return new ElaichiTea(beverageName,IngredientsReq);
		case hot_milk:
			return new HotMilk(beverageName,IngredientsReq);
		case hot_water:
			return new HotWater(beverageName,IngredientsReq);
		default:
			throw new InvalidBeverageException(beverageName);
		}
	}

}
