package com.wipro.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamEx11 {

	public static void main(String[] args) {
		List<String> list = Arrays.asList("Janhvee", "Jayanta", "Jnanedra");
		
		List<String> consecutiveList = list.stream()
				.filter(name -> name.toLowerCase().contains("ja"))
				.collect(Collectors.toList());
		
		System.out.println(consecutiveList);

	}

}
