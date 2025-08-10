package com.wipro.stream;

import java.util.ArrayList;
import java.util.List;

public class StreamEx14 {
	public static void main(String[] args) {
		FoodItemclass f1 = new FoodItemclass("Noodles", "Veg", "Chinese", 60 );
		FoodItemclass f2 = new FoodItemclass("mango sticky rice", "Veg", "Thai", 160 );
		FoodItemclass f3 = new FoodItemclass("Pizza", "Veg", "Continental", 260 );
		FoodItemclass f4 = new FoodItemclass("Manchurian", "Veg", "Chinese", 80 );
		FoodItemclass f5 = new FoodItemclass("Curry", "Veg", "Thai", 160 );
		FoodItemclass f6 = new FoodItemclass("Fried Rice", "Veg", "Chinese",100 );
		
		List<FoodItemclass> foodList= new ArrayList<>();
		foodList.add(f1);
		foodList.add(f2);
		foodList.add(f3);
		foodList.add(f4);
		foodList.add(f5);
		foodList.add(f6);
		
		
		FoodItemclass pList= foodList.stream()
		.filter(food -> food.getCuisine().equalsIgnoreCase("Chinese"))
        .reduce((food1, food2) -> food1.getPrice() > food2.getPrice() ? food1 : food2)
        .orElse(null);
		 System.out.println(pList);
	}
}
