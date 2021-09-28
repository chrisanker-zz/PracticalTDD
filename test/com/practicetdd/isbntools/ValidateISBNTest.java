package com.practicetdd.isbntools;

import static org.junit.Assert.*;
import org.junit.Test;

import static org.junit.Assert.fail;

public class ValidateISBNTest {
    ValidateISBN validator = new ValidateISBN();

    @Test
    public void checkValidShortISBN(){
        boolean result = validator.checkISBN("0395489326");
        assertTrue("first value", result);
        result = validator.checkISBN("0140177396");
        assertTrue("second value", result);
    }

    @Test
    public void shortISBNNumbersEndingInAnXAreValid(){
        boolean result = validator.checkISBN("140274577X");
        assertTrue(result);
    }

    @Test
    public void checkValidLongISBN(){
        boolean result = validator.checkISBN("9780399501487");
        assertTrue("first value",result);
        result = validator.checkISBN("9780060935467");
        assertTrue("second value",result);
    }

    @Test
    public void checkAnInvalidShortISBN(){
        boolean result = validator.checkISBN("0395489327");
        assertFalse(result);
    }

    @Test
    public void checkAnInvalidLongISBN(){
        boolean result = validator.checkISBN("9780399501488");
        assertFalse(result);
    }

    @Test(expected = NumberFormatException.class)
    public void nineDigitISBNsAreNotAllowed(){
        validator.checkISBN("123456789");
    }
    @Test(expected = NumberFormatException.class)
    public void onlyNumbersAreAllowed(){
        validator.checkISBN("helloworld");
    }
}