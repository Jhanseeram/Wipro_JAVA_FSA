package com.wipro.optional;

import java.util.Optional;

public class OptionalEx16 {

	public static void main(String[] args) {
		
		Optional<Integer> num = Optional.ofNullable(12);
		
		num.ifPresent(val -> System.out.println("Value for nullable optional - " + val));

        Optional<Integer> num2 = Optional.empty();
        
        int result = num2.orElse(50);
        
        System.out.println("Value (from empty optional) is: " + result);


	}

}
