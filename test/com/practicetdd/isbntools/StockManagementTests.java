package com.practicetdd.isbntools;

import org.junit.Test;
import static org.junit.Assert.*;

import static junit.framework.TestCase.fail;

public class StockManagementTests {
    @Test
    public void testCanGeACorrectLocatorCode(){

        ExternalISBNDataService testWebService = new ExternalISBNDataService() {
            @Override
            public Book lookup(String isbn) {
                return new Book(isbn,"The Lord of The Rings", "J.R.R. Tolkien");
            }
        };

        ExternalISBNDataService testDatabaseService = new ExternalISBNDataService() {
            @Override
            public Book lookup(String isbn) {
                return null;
            }
        };

        StockManager stockManager = new StockManager();
        stockManager.setWebService(testWebService);
        stockManager.setDatabaseService(testDatabaseService);
        String isbn = "0395489326";
        String locatorCode = stockManager.getLocatorCode(isbn);
        assertEquals("9326J5", locatorCode);
    }

    @Test
    public void databaseIsUsedIfDataIsPresent(){
        fail();
    }

    @Test
    public void webserviceIsUsedIfDataIsNotPresentInDatabase(){
        fail();
    }
}
