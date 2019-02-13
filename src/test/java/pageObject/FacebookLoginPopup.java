package pageObject;

import businessObject.UserFacebook;
import com.epam.syfy.tests.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtil;

public class FacebookLoginPopup extends BasePage {
    private WebDriverWait wait;
    private static final By FACEBOOK_EMAIL = By.id("email");
    private static final By FACEBOOK_PASSWORD = By.id("pass");
    private static final By FACEBOOK_SUBMIT = By.name("login");
    private static final By LOGOUT_LINK = By.className("spotim__logout-link");

    public FacebookLoginPopup(WebDriver driver) {
        super(driver);
    }
    public boolean loginToComments(UserFacebook fbUser) {
        String winHandleBefore = driver.getWindowHandle(); // Store the current window handle
        for(String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        //Type Facebook login data and submit
        System.out.println("Fill in Facebook login data, submit form");
        WebElement facebookEmail = driver.findElement(FACEBOOK_EMAIL);
        facebookEmail.clear();
        facebookEmail.sendKeys(fbUser.getUserEmail());
        WebElement facebookPassword = driver.findElement(FACEBOOK_PASSWORD);
        facebookPassword.clear();
        facebookPassword.sendKeys(fbUser.getUserPassword());
        WebElement facebookSubmit = driver.findElement(FACEBOOK_SUBMIT);
        facebookSubmit.click(); //Login window should close itself
        // Switch back to original browser (first window)
        driver.switchTo().window(winHandleBefore);
        return WaitUtil.waitElement(driver, LOGOUT_LINK);
    }
}
