package pageFactory;

import com.epam.syfy.tests.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FacebookLoginPopupFactory extends BasePageFactory {
    private WebDriverWait wait;

    @FindBy(id = "email")
    private WebElement facebookEmail;
    @FindBy(id = "pass")
    private WebElement facebookPassword;
    @FindBy(name = "login")
    private WebElement facebookSubmit;
    @FindBy(className = "spotim__logout-link")
    private WebElement logoutLink;

    public FacebookLoginPopupFactory(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }
    public boolean loginToComments() {
        String winHandleBefore = driver.getWindowHandle(); // Store the current window handle
        for(String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        //Type Facebook login data and submit
        System.out.println("Fill in Facebook login data, submit form");
        facebookEmail.clear();
        facebookEmail.sendKeys(Properties.facebookEmailValue);
        facebookPassword.clear();
        facebookPassword.sendKeys(Properties.facebookPassValue);
        facebookSubmit.click(); //Login window should close itself
        // Switch back to original browser (first window)
        driver.switchTo().window(winHandleBefore);
        wait = new WebDriverWait(driver, Properties.timeoutWait);
        return wait.until(ExpectedConditions.visibilityOf(logoutLink)).isDisplayed();
    }
}
