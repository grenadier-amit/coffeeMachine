package com.amit.coffeemachine.utils;

public enum BeverageType {
	hot_tea("hot_tea"), hot_coffee("hot_coffee"), black_tea("black_tea"), green_tea("green_tea"),
	ginger_tea("ginger_tea"), elaichi_tea("elaichi_tea"), hot_milk("hot_milk"), hot_water("hot_water");

	private String beverageName;

	BeverageType(String name) {
		beverageName = name;
	}

	public String getBeverageName() {
		return beverageName;
	}
}