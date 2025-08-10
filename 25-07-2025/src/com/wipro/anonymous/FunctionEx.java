package com.wipro.anonymous;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class FunctionEx {

	public static void main(String[] args) {
	
		
		List<String> names = Arrays.asList("Jayanta", "Amit", "Vasu");

        Function<List<String>, List<Integer>> getLengths = list -> {
            List<Integer> result = new ArrayList<>();
            for (String name : list) {
                result.add(name.length());
            }
            return result;
        };

        List<Integer> lengths = getLengths.apply(names);
        System.out.println("String lengths: " + lengths);
	}

}
