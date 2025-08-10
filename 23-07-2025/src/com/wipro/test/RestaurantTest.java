package com.wipro.test;

import com.wipro.oop.Restaurants;

public class RestaurantTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Restaurants res = new Restaurants(101, "Hotel Athithi", "Pune", "Panner Tikka");
		System.out.println(res);
		
		res.setFamousDish("Panner Handi");
		System.out.println(res.getFamousDish());
	}

}
