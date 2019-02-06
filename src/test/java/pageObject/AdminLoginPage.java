package pageObject;

import com.epam.syfy.tests.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdminLoginPage extends BasePage {
    private static final String URL = "https://edit.syfy.com/user";
    private static final By LOGIN_EMAIL = By.id("edit-name");
    private static final By LOGIN_PASSWORD = By.id("edit-pass");
    private static final By LOGIN_BUTTON = By.id("edit-submit");
    private static final By ADMIN_USERNAME = By.className("admin-menu-account");


    public AdminLoginPage(WebDriver driver) {
        super(driver);
    }
    public AdminLoginPage open() {
        System.out.println("Open CMS login page");
        getDriver().get(URL);
        driver.manage().addCookie(Properties.hidePopup);
        return this;
    }
    public boolean loginEditor() {
        //Type Editor login data and submit
        System.out.println("Login as Editor");
        WebElement emailInput = driver.findElement(LOGIN_EMAIL);
        emailInput.clear();
        emailInput.sendKeys(Properties.editorEmailValue);
        WebElement passwordInput = driver.findElement(LOGIN_PASSWORD);
        passwordInput.clear();
        passwordInput.sendKeys(Properties.editorPasswordValue);
        WebElement loginButton = driver.findElement(LOGIN_BUTTON);
        loginButton.click();
        return driver.findElement(ADMIN_USERNAME).isDisplayed();
    }
}
