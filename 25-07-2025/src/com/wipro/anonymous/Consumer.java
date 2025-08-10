package com.wipro.anonymous;

import java.util.function.Consumer;

public class Consumer {

	public static void main(String[] args) {
		Consumer<Integer> nextEvenNum = (num) -> {
			int evenNum = num%2 ==0 ?  num + 2  :  num + 1;
			System.out.println(evenNum);
		};
		
		nextEvenNum.accept(5);
	}

}
