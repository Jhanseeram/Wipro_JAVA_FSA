package com.wipro.anonymous;

import java.util.function.Predicate;

public class Palindrome {

	public static void main(String[] args) {
			 Predicate<String> palindrome = str -> {
		            return str.equalsIgnoreCase(new StringBuilder(str).reverse().toString());
		        };

	        String p = "refer";

	        System.out.println(p + " is palindrome - " + palindrome.test(p));

	}

}
