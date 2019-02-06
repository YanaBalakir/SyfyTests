package pageObject;

import com.epam.syfy.tests.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ArticlePage extends BasePage {
    private static final By ARTICLE_TITLE = By.className("blog-post__image-hero-content-title-inner");
    private static final By ARTICLE_BODY = By.className("blog-post__body-copy");
    private static final By COMMENTS_LOGIN_LINK = By.className("spotim__login-link");
    private static final By FACEBOOK_BUTTON = By.xpath("//button[@title='Facebook']");
    private static final By COMMENTS_FRAME = By.xpath("//div[contains(@class, 'sppre_frame-container')]//iframe");
    private static final By COMMENTS_LIST = By.className("sppre_messages-list");
    private static final By COMMENT_INPUT = By.className("ql-editor");
    private static final By POST_COMMENT_BUTTON = By.className("sppre_send-button");

    public ArticlePage(WebDriver driver) {
        super(driver);
    }
    public String getArticleBody() {
        System.out.println("Get article Body copy");
        return driver.findElement(ARTICLE_BODY).getText();
    }
    public boolean scrollToComments() {
        System.out.println("Scroll down to Comments block");
        driver.get(driver.getCurrentUrl() + "#comments");
        driver.navigate().refresh();
        return driver.findElement(COMMENTS_LOGIN_LINK).isDisplayed();
    }

    public String clickFacebookLogin() {
        System.out.println("Click Facebook login icon");
        WebElement facebookButton = driver.findElement(FACEBOOK_BUTTON); //Click Facebook login icon
        facebookButton.click();
        String facebookWindow = null;
        for(String winHandle : driver.getWindowHandles()) {
            facebookWindow = winHandle;
        }
        return facebookWindow;
    }

    public WebElement postComment() {
        //Post a comment
        WebElement commentsFrame = driver.findElement(COMMENTS_FRAME);
        driver.switchTo().frame(commentsFrame);
        System.out.println("Write a comment, post it");
        WebElement commentInput = driver.findElement(COMMENT_INPUT);
        commentInput.clear();
        commentInput.sendKeys(Properties.commentText); //Type comment
        WebElement postButton = driver.findElement(POST_COMMENT_BUTTON);
        postButton.click();
        return driver.findElement(COMMENTS_LIST);
    }
    public String getArticleTitle() {
        WebElement articletitle = driver.findElement(ARTICLE_TITLE);
        return articletitle.getText();
    }

}
