package pageFactory;

import com.epam.syfy.tests.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ArticlePageFactory extends BasePageFactory {

    @FindBy(className = "blog-post__image-hero-content-title-inner")
    private WebElement articleTitle;
    @FindBy(className = "blog-post__body-copy")
    private WebElement articleBody;
    @FindBy(className = "spotim__login-link")
    private WebElement commentsLoginLink;
    @FindBy(xpath = "//button[@title='Facebook']")
    private WebElement facebookButton;
    @FindBy(xpath = "//div[contains(@class, 'sppre_frame-container')]//iframe")
    private WebElement commentsFrame;
    @FindBy(className = "sppre_messages-list")
    private WebElement commentsList;
    @FindBy(className = "ql-editor")
    private WebElement commentInput;
    @FindBy(className = "sppre_send-button")
    private WebElement postButton;

    public ArticlePageFactory(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }
    public String getArticleBody() {
        System.out.println("Get article Body copy");
        return articleBody.getText();
    }
    public boolean scrollToComments() {
        System.out.println("Scroll down to Comments block");
        driver.get(driver.getCurrentUrl() + "#comments");
        driver.navigate().refresh();
        return commentsLoginLink.isDisplayed();
    }

    public String clickFacebookLogin() {
        System.out.println("Click Facebook login icon");
        facebookButton.click(); //Click Facebook login icon
        String facebookWindow = null;
        for(String winHandle : driver.getWindowHandles()) {
            facebookWindow = winHandle;
        }
        return facebookWindow;
    }

    public WebElement postComment() {
        //Post a comment
        driver.switchTo().frame(commentsFrame);
        System.out.println("Write a comment, post it");
        commentInput.clear();
        commentInput.sendKeys(Properties.commentText); //Type comment
        postButton.click();
        return commentsList;
    }
    public String getArticleTitle() {
        return articleTitle.getText();
    }

}
