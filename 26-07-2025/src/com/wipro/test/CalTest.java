package com.wipro.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.wipro.junit.Calculator;

public class CalTest {

	@Test
	public void test() {
		Calculator calc = new Calculator();
		int sum = Calculator.add(2,4);
		assertTrue(sum==6);
	}
	
	@Test
	public void addneg() {
		Calculator calc = new Calculator();
		int sum = Calculator.add(2,4);
		assertFalse(sum==5);
	}

}
