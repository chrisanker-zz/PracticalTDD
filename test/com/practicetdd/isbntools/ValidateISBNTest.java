package com.practicetdd.isbntools;

import static org.junit.Assert.*;
import org.junit.Test;

import static org.junit.Assert.fail;

public class ValidateISBNTest {

    @Test
    public void checkValidISBN(){
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN(395489326);
        assertTrue(result);
    }
}