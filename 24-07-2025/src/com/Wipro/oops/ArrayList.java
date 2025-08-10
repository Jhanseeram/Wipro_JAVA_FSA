package com.Wipro.oops;

import java.util.List;

public class ArrayList {
	
	public static void main(String[] args) {
		
		List<Integer> lst = new java.util.ArrayList<>();
		lst.add(10);
		lst.add(20);
		lst.add(30);
		lst.add(40);
		lst.add(50);
		
		System.out.println(lst);
		
		lst.remove(2);
		System.out.println(lst);
		
		lst.add(60);
		System.out.println(lst);
	}
}
