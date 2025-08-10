package com.Wipro.oops;

import java.util.HashSet;
import java.util.Set;

public class SetExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Set<Integer> set1 = new HashSet<>();
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set1.add(4);
        set1.add(5);

        Set<Integer> set2 = new HashSet<>();
        set2.add(4);
        set2.add(5);
        set2.add(6);
        set2.add(7);

        System.out.println("Set 1: " + set1);
        System.out.println("Set 2: " + set2);

        set1.removeAll(set2);

        System.out.println("Difference: " + set1);
    }

}
