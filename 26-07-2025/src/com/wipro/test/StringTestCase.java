package com.wipro.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.wipro.junit.StringCheck;

public class StringTestCase {

    StringCheck str;
    

    @Before
    public void startup() {
        str = new StringCheck(); 
    }

    @Test
    public void CheckTrue() {
        String str1 = StringCheck.Uppercase("vedanti");
        assertTrue(str1.equals(str1.toUpperCase()));
    }

    @Test
    public void CheckFalse() {
        String str1 = StringCheck.Uppercase("vedanti");
        assertFalse(str1.equals(str1.toLowerCase()));
    }
}
