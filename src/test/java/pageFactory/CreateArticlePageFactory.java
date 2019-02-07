package pageFactory;

import com.epam.syfy.tests.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateArticlePageFactory extends BasePageFactory {
    private WebDriverWait wait = new WebDriverWait(driver, Properties.timeoutWait);;
    private static final String URL = "https://edit.syfy.com/node/add/syfywire-blog-post";

    @FindBy(id = "edit-title")
    private WebElement title;
    @FindBy(className = "cke_wysiwyg_frame")
    private WebElement wysiwygFrame;
    @FindBy(className = "cke_editable")
    private WebElement bodyTextarea;
    @FindBy(name = "field_contributor[und][0][target_id]")
    private WebElement contributor;
    @FindBy(id = "autocomplete")
    private WebElement contributorAutocomplete;
    @FindBy(id = "edit-field-sfw-cover-media-und-0-browse-button")
    private WebElement coverUploadButton;
    @FindBy(className = "media-modal-frame")
    private WebElement coverUploadPopup;
    @FindBy(className = "form-file")
    private WebElement coverUploadField;
    @FindBy(className = "media-thumbnail")
    private WebElement coverThumbnail;
    @FindBy(id = "edit-next")
    private WebElement nextbutton;
    @FindBy(id = "edit-submit")
    private WebElement submitButton;

    public CreateArticlePageFactory(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }
    public CreateArticlePageFactory open() {
        System.out.println("Open Create Article page");
        getDriver().get(URL);
        return this;
    }
    public String fillTitle() {
        title.clear();
        title.sendKeys(Properties.title);
        return title.getAttribute("value");
    }
    public String fillBody() {
        driver.switchTo().frame(wysiwygFrame);
        bodyTextarea.clear();
        bodyTextarea.sendKeys(Properties.bodyText);
        String bodyTextCopied = bodyTextarea.getText();
        driver.switchTo().defaultContent();
        return bodyTextCopied;
    }
    public String fillContributor() {
        contributor.clear();
        contributor.sendKeys(Properties.contributorName);
        wait.until(ExpectedConditions.visibilityOf(contributorAutocomplete)).isDisplayed();
        contributor.sendKeys(Keys.DOWN , Keys.RETURN);
        return contributor.getAttribute("value");
    }
    public boolean setCoverImage() {
        coverUploadButton.click();
        driver.switchTo().frame(coverUploadPopup);
        coverUploadField.sendKeys(Properties.articleCoverImage);
        nextbutton.click();
        submitButton.click();
        driver.switchTo().defaultContent();
        return wait.until(ExpectedConditions.visibilityOf(coverThumbnail)).isDisplayed();
    }
    public ArticlePageFactory submitArticleForm() {
        submitButton.click();
        return new ArticlePageFactory(driver);
    }
}
