package com.amit.coffeemachine.utils.server;

import java.util.Map;

import com.amit.coffeemachine.utils.BeverageType;
import com.amit.coffeemachine.utils.IngredientType;
import com.amit.coffeemachine.utils.IngredientUpdateType;
import com.amit.coffeemachine.utils.IngredientsAvail;

public abstract class BeverageMachine {
	protected final Integer outlets;	//n outlets
	protected final Map<IngredientType, Integer> ingredientsIni;	
	protected final IngredientsAvail ingredientsAvail;		//for maintaining ingredients quantity for all operations
	
	protected Integer getOutlets() {
		return outlets;
	}

	public BeverageMachine(Integer outlets, Map<IngredientType, Integer> ingredientsIni) {
		this.outlets = outlets;
		this.ingredientsIni = ingredientsIni;	//Initial ingredients supplied for coffee machine
		this.ingredientsAvail = IngredientsAvail.getInstance();
		try {
			ingredientsAvail.updateIngredients(ingredientsIni,IngredientUpdateType.add);
		} catch (RuntimeException e) {
			System.out.println("Exception in adding ingredients "+e);
		}
	}
	
	public abstract void startService();		//start executor service for n parallel outlets
	public abstract void stopService();			//stop executor service for n parallel outlets
	public abstract void prepareBeverage(BeverageType beverageName, Map<IngredientType, Integer> IngredientsReq);		//prepate beverage and remove used ingredients
	protected abstract void addIngredients(Map<IngredientType, Integer> IngredientsReq);		//add ingredients
}
