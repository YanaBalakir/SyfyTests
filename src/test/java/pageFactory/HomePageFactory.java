package pageFactory;

import com.epam.syfy.tests.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageFactory extends BasePageFactory {
    private static final String URL = "https://www.syfy.com";

    @FindBy(className = "navbar__search")
    private WebElement searchButton;
    @FindBy(className = "js-search-active")
    private WebElement searchOverlay;
    @FindBy(className = "teaser--semi-big-hero")
    private WebElement firstArticle;

    public HomePageFactory(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }
    public HomePageFactory open() {
        System.out.println("Open SYFY home page");
        getDriver().get(URL);
        driver.manage().addCookie(Properties.hidePopup);
        return this;
    }
    public ArticlePageFactory openFirstArticle() {
        System.out.println("Find the latest article, open it");
        firstArticle.click();
        return new ArticlePageFactory(driver);
    }
    public SearchOverlayFactory clickSearchButton() {
        System.out.println("Click Search icon");
        searchButton.click();
        if (searchOverlay.isDisplayed()) {
            return new SearchOverlayFactory(driver);
        } else {
            return null;
        }
    }


}
