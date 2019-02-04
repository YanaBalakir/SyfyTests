package pageObject;

import com.epam.syfy.tests.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ArticlePage extends BasePage {
    private WebDriverWait wait;
    private static final By ARTICLE_BODY = By.className("blog-post__body-copy");
    private static final By COMMENTS_LOGIN_LINK = By.className("spotim__login-link");
    private static final By FACEBOOK_BUTTON = By.xpath("//button[@title='Facebook']");
    private static final By FACEBOOK_EMAIL = By.id("email");
    private static final By FACEBOOK_PASSWORD = By.id("pass");
    private static final By FACEBOOK_SUBMIT = By.name("login");
    private static final By LOGOUT_LINK = By.className("spotim__logout-link");
    private static final By COMMENTS_FRAME = By.xpath("//div[contains(@class, 'sppre_frame-container')]//iframe");
    private static final By COMMENTS_LIST = By.className("sppre_messages-list");
    private static final By COMMENT_INPUT = By.className("ql-editor");
    private static final By POST_COMMENT_BUTTON = By.className("sppre_send-button");

    public ArticlePage(WebDriver driver) {
        super(driver);
    }
    public String getArticleBody() {
        return driver.findElement(ARTICLE_BODY).getText();
    }
    public WebElement scrollToComments() {
        driver.get(driver.getCurrentUrl() + "#comments");
        driver.navigate().refresh();
        return driver.findElement(COMMENTS_LOGIN_LINK);
    }
    public WebElement loginToComments() {
        // Store the current window handle
        String winHandleBefore = driver.getWindowHandle();
        //Click Facebook login icon
        WebElement facebookButton = driver.findElement(FACEBOOK_BUTTON);
        facebookButton.click();
        // Switch to Facebook login window
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }
        //Type Facebook login data and submit
        WebElement facebookEmail = driver.findElement(FACEBOOK_EMAIL);
        facebookEmail.clear();
        facebookEmail.sendKeys(Properties.facebookEmailValue);
        WebElement facebookPassword = driver.findElement(FACEBOOK_PASSWORD);
        facebookPassword.clear();
        facebookPassword.sendKeys(Properties.facebookPassValue);
        WebElement facebookSubmit = driver.findElement(FACEBOOK_SUBMIT);
        facebookSubmit.click(); //Login window should close itself
        // Switch back to original browser (first window)
        driver.switchTo().window(winHandleBefore);
        wait = new WebDriverWait(driver, Properties.timeoutWait);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(LOGOUT_LINK));
    }
    public WebElement postComment() {
        //Post a comment
        WebElement commentsFrame = driver.findElement(COMMENTS_FRAME);
        driver.switchTo().frame(commentsFrame);
        WebElement commentInput = driver.findElement(COMMENT_INPUT);
        commentInput.clear();
        commentInput.sendKeys(Properties.commentText); //Type comment
        WebElement postButton = driver.findElement(POST_COMMENT_BUTTON);
        postButton.click();
        return driver.findElement(COMMENTS_LIST);
    }


}
