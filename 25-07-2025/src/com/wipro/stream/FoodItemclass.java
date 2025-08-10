package com.wipro.stream;

public class FoodItemclass {
	String FoodName; 
	String Foodtype;
    String Cuisine; 
	double price;
	
	
	public String getFoodName() {
		return FoodName;
	}


	public void setFoodName(String foodName) {
		FoodName = foodName;
	}


	public String getFoodtype() {
		return Foodtype;
	}


	public void setFoodtype(String foodtype) {
		Foodtype = foodtype;
	}


	public String getCuisine() {
		return Cuisine;
	}


	public void setCuisine(String cuisine) {
		Cuisine = cuisine;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public FoodItemclass(String foodName, String foodtype, String cuisine, double price) {
		super();
		FoodName = foodName;
		Foodtype = foodtype;
		Cuisine = cuisine;
		this.price = price;
	}


	@Override
	public String toString() {
		return "FoodItemclass [FoodName=" + FoodName + ", Foodtype=" + Foodtype + ", Cuisine=" + Cuisine + ", price="
				+ price + "]";
	}
	
	
	
	

}
