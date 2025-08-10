package com.wipro.oop;

public class Restaurants 
{
	int restaurantId;
	String restaurantName;
	String address;
	String famousDish;
	
	public Restaurants() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Restaurants(int restaurantId, String restaurantName, String address, String famousDish) {
		super();
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.address = address;
		this.famousDish = famousDish;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFamousDish() {
		return famousDish;
	}

	public void setFamousDish(String famousDish) {
		this.famousDish = famousDish;
	}

	@Override
	public String toString() {
		return "Restaurants [restaurantId=" + restaurantId + ", restaurantName=" + restaurantName + ", address="
				+ address + ", famousDish=" + famousDish + "]";
	}
}
