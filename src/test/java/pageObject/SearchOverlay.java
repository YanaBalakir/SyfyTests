package pageObject;

import com.epam.syfy.tests.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchOverlay extends BasePage {
    private static final By SEARCH_INPUT = By.id("edit-keyword");
    private static final By SEARCH_RESULTS = By.className("search-results");
    private static final By SEARCH_FIRST_RESULT = By.xpath("//div[contains(@id, 'solrsearch-results')]//a[contains(@class, 'teaser__content-title')]");


    public SearchOverlay(WebDriver driver)
    {
        super(driver);
    }
    public boolean typeSearchRequest() {
        System.out.println("Type Search request");
        driver.findElement(SEARCH_INPUT).clear();
        driver.findElement(SEARCH_INPUT).sendKeys(Properties.searchRequest);
        return driver.findElement(SEARCH_RESULTS).isDisplayed();
    }
    public ArticlePage clickFirstSearchResult() {
        System.out.println("Click on first Search result");
        driver.findElement(SEARCH_FIRST_RESULT).click();
        return new ArticlePage(driver);
    }
}
