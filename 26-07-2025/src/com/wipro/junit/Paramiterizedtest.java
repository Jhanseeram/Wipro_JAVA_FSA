package com.wipro.junit;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class Paramiterizedtest {

	 @ParameterizedTest
	    @ValueSource(strings = {"vedanti", "hello", "java"})
	    void testString(String str) {
	        assertTrue(Lowercase(str));
	    }

	  Boolean Lowercase(String str) {
		// TODO Auto-generated method stub
		return str.equals(str.toLowerCase());
	 }
	}
