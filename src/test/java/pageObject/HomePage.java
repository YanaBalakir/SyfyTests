package pageObject;

import com.epam.syfy.tests.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {
    private static final String URL = "https://www.syfy.com";
    private static final By SEARCH_BUTTON = By.className("navbar__search");
    private static final By SEARCH_OVERLAY = By.className("js-search-active");
    private static final By SEARCH_INPUT = By.id("edit-keyword");
    private static final By SEARCH_RESULTS = By.className("search-results");
    private static final By SEARCH_FIRST_RESULT = By.xpath("//div[contains(@id, 'solrsearch-results')]//a[contains(@class, 'teaser__content-title')]");
    private static Cookie ck = new Cookie("hide_popup192983", "1"); //Cookie to prevent pop-up window display

    public HomePage(WebDriver driver)
    {
        super(driver);
    }
    public HomePage open() {
        getDriver().get(URL);
        driver.manage().addCookie(ck);
        return this;
    }
    public ArticlePage openFirstArticle() {
        WebElement firstArticle = driver.findElement(By.className("teaser--semi-big-hero"));
        firstArticle.click();
        return new ArticlePage(driver);
    }
    public WebElement clickSearchButton() {
        driver.findElement(SEARCH_BUTTON).click();
        return driver.findElement(SEARCH_OVERLAY);
    }
    public WebElement typeSearchRequest() {
        driver.findElement(SEARCH_INPUT).clear();
        driver.findElement(SEARCH_INPUT).sendKeys(Properties.searchRequest);
        return driver.findElement(SEARCH_RESULTS);
    }
    public ArticlePage clickFirstSearchResult() {
        driver.findElement(SEARCH_FIRST_RESULT).click();
        return new ArticlePage(driver);
    }

}
