package com.wipro.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class NullPointerTestCase {

    String sample;

    @Before
    public void startup() {
        sample = null;
    }

    @Test
    public void CheckTrue() {
        String str1 = sample.toUpperCase();
        assertTrue(str1.equals(str1.toUpperCase()));
    }

    @Test
    public void CheckFalse() {
        String str1 = sample.toUpperCase();
        assertFalse(str1.equals(str1.toLowerCase()));
    }

    @Test
    void CheckNullUppercase() {
        String nullStr = null;
        assertThrows(NullPointerException.class, () -> {
            nullStr.toUpperCase();
        });
}
}
