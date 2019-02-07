package pageObject;

import com.epam.syfy.tests.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {
    private static final String URL = "https://www.syfy.com";
    private static final By SEARCH_BUTTON = By.className("navbar__search");
    private static final By SEARCH_OVERLAY = By.className("js-search-active");
    private static final By FIRST_ARTICLE = By.className("teaser--semi-big-hero");

    public HomePage(WebDriver driver)
    {
        super(driver);
    }
    public HomePage open() {
        System.out.println("Open SYFY home page");
        getDriver().get(URL);
        driver.manage().addCookie(Properties.hidePopup);
        return this;
    }
    public ArticlePage openFirstArticle() {
        System.out.println("Find the latest article, open it");
        WebElement firstArticle = driver.findElement(FIRST_ARTICLE);
        firstArticle.click();
        return new ArticlePage(driver);
    }
    public SearchOverlay clickSearchButton() {
        System.out.println("Click Search icon");
        driver.findElement(SEARCH_BUTTON).click();
        driver.findElement(SEARCH_OVERLAY);
        return new SearchOverlay(driver);
    }


}
