package com.practicetdd.isbntools;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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
        ExternalISBNDataService databaseService = mock(ExternalISBNDataService.class);
        ExternalISBNDataService webService = mock(ExternalISBNDataService.class);

        when(databaseService.lookup("0395489326")).thenReturn(new Book("0395489326","abc","abc"));

        StockManager stockManager = new StockManager();
        stockManager.setWebService(webService);
        stockManager.setDatabaseService(databaseService);

        String isbn = "0395489326";
        String locatorCode = stockManager.getLocatorCode(isbn);

        verify(databaseService,times(1)).lookup("0395489326");
        verify(webService, times(0)).lookup(anyString());
    }

    @Test
    public void webserviceIsUsedIfDataIsNotPresentInDatabase(){
        ExternalISBNDataService databaseService = mock(ExternalISBNDataService.class);
        ExternalISBNDataService webService = mock(ExternalISBNDataService.class);

        when(databaseService.lookup("0395489326")).thenReturn(null);
        when(webService.lookup("0395489326")).thenReturn(new Book("0395489326","abc","abc"));

        StockManager stockManager = new StockManager();
        stockManager.setWebService(webService);
        stockManager.setDatabaseService(databaseService);

        String isbn = "0395489326";
        String locatorCode = stockManager.getLocatorCode(isbn);

        verify(databaseService,times(1)).lookup("0395489326");
        verify(webService, times(1)).lookup("0395489326");
    }
}
