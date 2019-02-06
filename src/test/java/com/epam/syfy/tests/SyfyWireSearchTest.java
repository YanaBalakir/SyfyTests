package com.epam.syfy.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.HomePage;
import pageObject.SearchOverlay;

public class SyfyWireSearchTest extends BaseTest {
    private HomePage homePage;
    private SearchOverlay searchOverlay;

    @Test (priority = 1, description = "Verify that Search overlay opens")
    public void verifySearchOverlayOpen() {
        homePage = new HomePage(driver).open(); //Open SYFY home page
        searchOverlay = homePage.clickSearchButton(); //Click Search button
        Assert.assertTrue(searchOverlay != null, "Search overlay didn't open!"); //Verify search overlay opened
    }
    @Test (priority = 2, description = "Verify that Search results appear")
    public void verifySearchResultsDisplay() {
        boolean searchResults = searchOverlay.typeSearchRequest(); //Type search request
        Assert.assertTrue(searchResults, "Search results are not displayed!"); //Verify search results list is displayed
    }
    @Test (priority = 3, description = "Verify that Search result corresponds to Search request")
    public void verifySearchResult() {
        String articleBody = searchOverlay.clickFirstSearchResult().getArticleBody(); //Open first search result, get it's Body
        Assert.assertTrue(articleBody.contains(Properties.searchRequest), "Article doesn't contain search request!"); //Verify article contains search request in Body
    }

}
