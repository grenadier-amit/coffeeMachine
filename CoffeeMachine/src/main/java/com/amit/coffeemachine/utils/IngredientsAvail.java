package com.amit.coffeemachine.utils;

import com.amit.coffeemachine.exception.IngredientNotAvailException;
import com.amit.coffeemachine.exception.InvalidIngredientException;
import com.amit.coffeemachine.exception.InvalidOperationException;

import java.util.HashMap;
import java.util.Map;

public class IngredientsAvail {
//singleton class for maintaining ingredient quantity for a given coffee machine	
	private Map<IngredientType, Integer> ingredientsMap;
	private final static IngredientsAvail INGREDIENTS_AVAIL = new IngredientsAvail();

	private IngredientsAvail() {
		this.ingredientsMap = new HashMap<IngredientType, Integer>();
	}

	public synchronized void updateIngredients(final Map<IngredientType, Integer> ingredientsReq,final IngredientUpdateType updateType)
			throws RuntimeException {

		if(updateType.getOperation() == 1) {
			ingredientsReq.forEach((k, v) -> {
				if(!ingredientsMap.containsKey(k)) {
					throw new InvalidIngredientException(k);
				}
				else if (ingredientsMap.get(k) < v) {
					throw new IngredientNotAvailException(k);
				}
				else if(v < 0) {
					throw new InvalidOperationException(k);
				}
			});
		}
		else {
			ingredientsReq.forEach((k, v) -> {
				if(v < 0) {
					System.out.println(k);
					throw new InvalidOperationException(k);
				}
				if(!ingredientsMap.containsKey(k)) {
					ingredientsMap.put(k,0);
				}
			});
		}
		
		ingredientsReq.forEach((k, v) -> {
			ingredientsMap.put(k, ingredientsMap.get(k) - (v*updateType.getOperation()) );
		});

	}

	public static IngredientsAvail getInstance() {
		return INGREDIENTS_AVAIL;
	}
}