package com.Wipro.oops;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

public class RemoveDuplicates {
	
	public static void main(String[] args) {
        
        List<Integer> numberList = new ArrayList<>();
        numberList.add(10);
        numberList.add(20);
        numberList.add(10);
        numberList.add(30);
        numberList.add(20);
        numberList.add(40);

        System.out.println("Original List: " + numberList);

        Set<Integer> uniqueSet = new LinkedHashSet<>(numberList);

        List<Integer> uniqueList = new ArrayList<>(uniqueSet);

        System.out.println("Duplicate List" + uniqueList);
    }
}
