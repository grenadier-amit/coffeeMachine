package com.amit.coffeemachine.utils.server;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.amit.coffeemachine.exception.IngredientNotAvailException;
import com.amit.coffeemachine.exception.InvalidIngredientException;
import com.amit.coffeemachine.exception.InvalidOperationException;
import com.amit.coffeemachine.exception.MachineNotStartedException;
import com.amit.coffeemachine.factory.impl.BeverageFactoryImpl;
import com.amit.coffeemachine.utils.BeverageType;
import com.amit.coffeemachine.utils.IngredientType;
import com.amit.coffeemachine.utils.IngredientUpdateType;
import com.amit.coffeemachine.utils.IngredientsAvail;

public class BeverageMachineImpl extends BeverageMachine {

	ExecutorService executorService;
	private final BeverageFactoryImpl beverageFactoryImpl;

	public BeverageMachineImpl(Integer outlets, Map<IngredientType, Integer> ingredientsIni) {
		super(outlets, ingredientsIni);
		this.beverageFactoryImpl = new BeverageFactoryImpl();
	}

	@Override
	public void startService() {

		executorService = Executors.newFixedThreadPool(super.getOutlets());
	}

	@Override
	public void stopService() {
		executorService.shutdown();
	}

	@Override
	public void prepareBeverage(BeverageType beverageName, Map<IngredientType, Integer> IngredientsReq) {

		if (executorService == null) {
			throw new MachineNotStartedException("Machine not started yet");
		}

		executorService.execute(new Runnable() {
			@Override
			public void run() {
				try {
					try {
						TimeUnit.MILLISECONDS.sleep(5000);
					} catch (InterruptedException e) {
						System.out.println("Exception in sleep " + e);
					}
					beverageFactoryImpl.createBeverage(beverageName, IngredientsReq).prepare();
					System.out.println(beverageName.getBeverageName() + " is prepared");
				} catch (IngredientNotAvailException ex) {
					System.out.println(beverageName.getBeverageName() + " cannot be prepared because " + ex.getMessage()
							+ " is not sufficient");
					
				//Remove below comment block to automatically add 100 quantity for a given ingredient, if it is not able to fulfill requirement of any beverage	
					/*
					 * Map<IngredientType, Integer> tempMap = new HashMap<IngredientType,
					 * Integer>(); tempMap.put(IngredientType.valueOf(ex.getMessage()), 100);
					 * addIngredients(tempMap);
					 */
					
					
				} catch (InvalidIngredientException ex) {
					System.out.println(beverageName.getBeverageName() + " cannot be prepared because " + ex.getMessage()
							+ " is not available");
				} catch(InvalidOperationException ex) {
					System.out.println(beverageName.getBeverageName() + " cannot be prepared because " + ex.getMessage()+" has negative quantity");
				}
			}
		});
	}

	@Override
	protected void addIngredients(Map<IngredientType, Integer> IngredientsAdd) {
		final IngredientsAvail ingredientAvail = IngredientsAvail.getInstance();
		ingredientAvail.updateIngredients(IngredientsAdd, IngredientUpdateType.add);
	}

}
