package pageObject;

import com.epam.syfy.tests.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateArticlePage extends BasePage {
    private WebDriverWait wait = new WebDriverWait(driver, Properties.timeoutWait);;
    private static final String URL = "https://edit.syfy.com/node/add/syfywire-blog-post";
    private static final By ARTICLE_TITLE_FIELD = By.id("edit-title");
    private static final By WYSIWYG_FRAME = By.className("cke_wysiwyg_frame");
    private static final By ARTICLE_BODY_FIELD = By.className("cke_editable");
    private static final By CONTRIBUTOR_FIELD = By.name("field_contributor[und][0][target_id]");
    private static final By CONTRIBUTOR_AUTOCOMPLETE = By.id("autocomplete");
    private static final By COVER_UPLOAD_BUTTON = By.id("edit-field-sfw-cover-media-und-0-browse-button");
    private static final By COVER_UPLOAD_FRAME = By.className("media-modal-frame");
    private static final By COVER_UPLOAD_FIELD = By.className("form-file");
    private static final By COVER_THUMBNAIL = By.className("media-thumbnail");
    private static final By NEXT_BUTTON = By.id("edit-next");
    private static final By SUBMIT_BUTTON = By.id("edit-submit");
    public CreateArticlePage(WebDriver driver) {
        super(driver);
    }
    public CreateArticlePage open() {
        System.out.println("Open Create Article page");
        getDriver().get(URL);
        return this;
    }
    public String fillTitle() {
        WebElement title = driver.findElement(ARTICLE_TITLE_FIELD);
        title.clear();
        title.sendKeys(Properties.title);
        return title.getAttribute("value");
    }
    public String fillBody() {
        WebElement wysiwygFrame = driver.findElement(WYSIWYG_FRAME);
        driver.switchTo().frame(wysiwygFrame);
        WebElement bodyTextarea = driver.findElement(ARTICLE_BODY_FIELD);
        bodyTextarea.clear();
        bodyTextarea.sendKeys(Properties.bodyText);
        String bodyTextCopied = bodyTextarea.getText();
        driver.switchTo().defaultContent();
        return bodyTextCopied;
    }
    public String fillContributor() {
        WebElement contributor = driver.findElement(CONTRIBUTOR_FIELD);
        contributor.clear();
        contributor.sendKeys(Properties.contributorName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(CONTRIBUTOR_AUTOCOMPLETE)).isDisplayed();
        contributor.sendKeys(Keys.DOWN , Keys.RETURN);
        return contributor.getAttribute("value");
    }
    public boolean setCoverImage() {
        WebElement coverUploadButton = driver.findElement(COVER_UPLOAD_BUTTON);
        coverUploadButton.click();
        WebElement coverUploadPopup = driver.findElement(COVER_UPLOAD_FRAME);
        driver.switchTo().frame(coverUploadPopup);
        WebElement coverUploadField = driver.findElement(COVER_UPLOAD_FIELD);
        coverUploadField.sendKeys(Properties.articleCoverImage);
        driver.findElement(NEXT_BUTTON).click();
        driver.findElement(SUBMIT_BUTTON).click();
        driver.switchTo().defaultContent();
        return wait.until(ExpectedConditions.visibilityOfElementLocated(COVER_THUMBNAIL)).isDisplayed();
    }
    public ArticlePage submitArticleForm() {
        driver.findElement(SUBMIT_BUTTON).click();
        return new ArticlePage(driver);
    }
}
