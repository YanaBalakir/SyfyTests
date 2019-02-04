package com.epam.syfy.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.HomePage;

public class SyfyWireSearchTest extends BaseTest {

    @Test (priority = 1, description = "Verify that Search overlay opens")
    public void verifySearchOverlayOpen() {
        System.out.println("Click Search button to open overlay");
        WebElement searchOverlay =  new HomePage(driver).open().clickSearchButton(); //Click Search button
        System.out.println("Check that overlay opened"); //Verify search overlay opened
        Assert.assertTrue(searchOverlay.isDisplayed(), "Search overlay didn't open!");
    }
    @Test (priority = 2, description = "Verify that Search results appear")
    public void verifySearchResultsDisplay() {
        System.out.println("Type Search request text");
        WebElement searchResults = new HomePage(driver).typeSearchRequest(); //Type search request
        System.out.println("Verify that Search results block appeared"); //Verify search results list is displayed
        Assert.assertTrue(searchResults.isDisplayed(), "Search results are not displayed!");
    }
    @Test (priority = 3, description = "Verify that Search result corresponds to Search request")
    public void verifySearchResult() {
        System.out.println("Click on first Search result");
        String articleBody = new HomePage(driver).clickFirstSearchResult().getArticleBody(); //Open first search result, get it's Body
        System.out.println("Verify that article Body text contains Search request(if Search works properly)");
        Assert.assertTrue(articleBody.contains(Properties.searchRequest), "Article doesn't contain search request!"); //Verify article contains search request in Body
    }

}
