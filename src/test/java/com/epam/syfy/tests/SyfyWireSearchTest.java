package com.epam.syfy.tests;
import com.epam.syfy.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SyfyWireSearchTest extends BaseTest {
    private String searchRequest = "Avengers";

    @Test (priority = 1)
    public void verifySearchOverlayOpen() {
        System.out.println("Open syfy.com");
        driver.get(BASE_URL);
        driver.manage().addCookie(ck);
        //Open Search overlay
        System.out.println("Open Search overlay");
        WebElement searchButton = driver.findElement(By.className("navbar__search"));
        searchButton.click();
        //Verify search overlay opened
        System.out.println("Check that overlay opened");
        WebElement searchOverlay = driver.findElement(By.className("js-search-active"));
        Assert.assertTrue(searchOverlay.isDisplayed());
    }
    @Test (priority = 2)
    public void verifySearchResultsDisplay() {
        //Type search request
        System.out.println("Type Search request text");
        WebElement searchField = driver.findElement(By.id("edit-keyword"));
        searchField.clear();
        searchField.sendKeys(searchRequest);
        //Verify search results list is displayed
        System.out.println("Verify that Search results block appeared");
        WebElement searchResults = driver.findElement(By.className("search-results"));
        Assert.assertTrue(searchResults.isDisplayed());
    }
    @Test (priority = 3)
    public void verifySearchResult() {
        //Open first search result
        System.out.println("Click on first Search result");
        WebElement articleLink = driver.findElement(By.xpath("//div[contains(@id, 'solrsearch-results')]//a[contains(@class, 'teaser__content-title')]"));
        articleLink.click();
        //Verify article contains search request in Body
        System.out.println("Verify that article Body text contains Search request(if Search works properly)");
        String articleBody = driver.findElement(By.className("blog-post__body-copy")).getText();
        Assert.assertTrue(articleBody.contains(searchRequest));
    }

}
