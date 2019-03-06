package cucumberTest;
import browserFactory.ChromeDriverCreator;
import browserFactory.WebDriverCreator;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageObject.ArticlePage;
import pageObject.HomePage;
import pageObject.SearchOverlay;

public class SyfySearchTestGherkin {
    protected WebDriver driver;
    private HomePage homePage;
    private SearchOverlay searchOverlay;
    private ArticlePage articlePage;

    @Given("^I opened Syfy site")
    public void iOpenedSyfySite() {
        WebDriverCreator creator = new ChromeDriverCreator();
        driver = creator.createWebDriver();
        homePage = new HomePage(driver).open(); //Open SYFY home page
    }

    @When("^I search ([\\w ]+)$")
    public void iSearch(String query) {
        searchOverlay = homePage.clickSearchButton(); //Click Search button
        boolean searchResults = searchOverlay.typeSearchRequest(query); //Type search request
    }

    @And("^I open search result")
    public void iOpenResultPage() {
        articlePage = searchOverlay.clickFirstSearchResult(); //Open first search result
    }

    @Then("^Article body contains ([\\w ]+)$")
    public void articleContainsRequest(String query) {
        String articleBody = articlePage.getArticleBody();
        Assert.assertTrue(articleBody.contains(query), "Article doesn't contain search request!");
    }
}
