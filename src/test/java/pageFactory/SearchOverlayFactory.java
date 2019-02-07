package pageFactory;

import com.epam.syfy.tests.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchOverlayFactory extends BasePageFactory {
    @FindBy(id = "edit-keyword")
    private WebElement searchInput;
    @FindBy(className = "search-results")
    private WebElement searchResults;
    @FindBy(xpath = "//div[contains(@id, 'solrsearch-results')]//a[contains(@class, 'teaser__content-title')]")
    private WebElement searchFirstResult;

    public SearchOverlayFactory(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }
    public boolean typeSearchRequest() {
        System.out.println("Type Search request");
        searchInput.clear();
        searchInput.sendKeys(Properties.searchRequest);
        return searchResults.isDisplayed();
    }
    public ArticlePageFactory clickFirstSearchResult() {
        System.out.println("Click on first Search result");
        searchFirstResult.click();
        return new ArticlePageFactory(driver);
    }
}
