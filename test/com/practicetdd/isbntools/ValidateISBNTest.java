package com.practicetdd.isbntools;

import static org.junit.Assert.*;
import org.junit.Test;

import static org.junit.Assert.fail;

public class ValidateISBNTest {

    @Test
    public void checkValidISBN(){
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("0395489326");
        assertTrue("first value", result);
        result = validator.checkISBN("0140177396");
        assertTrue("second value", result);
    }

    @Test
    public void checkAnInvalidISBN(){
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("0395489327");
        assertFalse(result);
    }

    @Test(expected = NumberFormatException.class)
    public void nineDigitISBNsAreNotAllowed(){
        ValidateISBN validator = new ValidateISBN();
        validator.checkISBN("123456789");
    }
}