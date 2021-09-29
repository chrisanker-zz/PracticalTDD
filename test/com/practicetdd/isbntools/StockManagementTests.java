package com.practicetdd.isbntools;

import org.junit.Test;
import static org.junit.Assert.*;

import static junit.framework.TestCase.fail;

public class StockManagementTests {
    @Test
    public void testCanGeACorrectLocatorCode(){

        ExternalISBNDataService testService = new ExternalISBNDataService() {
            @Override
            public Book lookup(String isbn) {
                return new Book(isbn,"The Lord of The Rings", "J.R.R. Tolkien");
            }
        };
        StockManager stockManager = new StockManager();
        stockManager.setService(testService);
        String isbn = "0395489326";
        String locatorCode = stockManager.getLocatorCode(isbn);
        assertEquals("9326J5", locatorCode);
    }
}
