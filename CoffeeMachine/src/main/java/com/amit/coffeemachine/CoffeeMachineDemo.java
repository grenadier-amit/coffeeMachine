package com.amit.coffeemachine;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.amit.coffeemachine.utils.BeverageType;
import com.amit.coffeemachine.utils.IngredientType;
import com.amit.coffeemachine.utils.server.BeverageMachineImpl;

public class CoffeeMachineDemo {

	public static void main(String[] args) throws Exception {
		CoffeeMachineDemo main = new CoffeeMachineDemo();
		Object obj = null;
		JSONParser parser = new JSONParser();

        File file = main.getFileFromResources("inputTest.json"); //takes input from inputTest.json present in src/main/resources
        
        /*Corner Test Cases
         * Process only those beverage for which full quantity of all required ingredients is available
         * Don't deduct ingredient quantity for partial fulfillment of a beverage
         * Negative quantity of ingredient for initial machine shouldn't be allowed
         * Negative quantity of ingredient for a beverage shouldn't be allowed
         * Functionality for automatically adding ingredients if it fails to fulfill any beverage's requirement: BeverageMachineImpl.java (line 62:66)
         * 
         */
        
        try {
			obj = parser.parse(new FileReader(file));
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
        JSONObject jsonObject = (JSONObject) obj;
        
        int outlets = (Integer.parseInt(String.valueOf(((JSONObject) ((JSONObject) jsonObject.get("machine")).get("outlets")).get("count_n"))));


        JSONObject resources = (JSONObject) ((JSONObject) jsonObject.get("machine")).get("total_items_quantity");
        Set<String> keys = resources.keySet();
        Iterator<String> it = keys.iterator();
        Map<IngredientType, Integer> ingredientsInit = new HashMap<IngredientType, Integer>();
        while(it.hasNext()){
            String key = it.next();
            ingredientsInit.put(IngredientType.valueOf(key), Integer.valueOf(String.valueOf(resources.get(key))));
        }
        
        BeverageMachineImpl beverageMachineImpl = new BeverageMachineImpl(outlets, ingredientsInit);
        beverageMachineImpl.startService();
        
        
        JSONObject beverages = (JSONObject) ((JSONObject) jsonObject.get("machine")).get("beverages");
        keys = beverages.keySet();
        it = keys.iterator();
        while(it.hasNext()){
            String key = it.next();
            JSONObject beverage = (JSONObject) beverages.get(key);
            Map<IngredientType,Integer> ingredientsReq = new HashMap<IngredientType, Integer>();
            Set<String> ingredients = beverage.keySet();
            Iterator<String> iter = ingredients.iterator();
            while(iter.hasNext()){
                String keyIng = iter.next();
                ingredientsReq.put(IngredientType.valueOf(keyIng), Integer.valueOf(String.valueOf(beverage.get(keyIng))));
            }
            beverageMachineImpl.prepareBeverage(BeverageType.valueOf(key), ingredientsReq);
        }
        
        beverageMachineImpl.stopService();
		
	}
	private File getFileFromResources(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }

    }
}
