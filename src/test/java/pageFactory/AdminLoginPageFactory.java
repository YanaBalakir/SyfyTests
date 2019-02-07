package pageFactory;

import com.epam.syfy.tests.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminLoginPageFactory extends BasePageFactory {
    private static final String URL = "https://edit.syfy.com/user";

    @FindBy(id = "edit-name")
    private WebElement emailInput;
    @FindBy(id = "edit-pass")
    private WebElement passwordInput;
    @FindBy(id = "edit-submit")
    private WebElement loginButton;
    @FindBy(className = "admin-menu-account")
    private WebElement adminUsername;

    public AdminLoginPageFactory(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }
    public AdminLoginPageFactory open() {
        System.out.println("Open CMS login page");
        getDriver().get(URL);
        driver.manage().addCookie(Properties.hidePopup);
        return this;
    }
    public boolean loginEditor() {
        //Type Editor login data and submit
        System.out.println("Login as Editor");
        emailInput.clear();
        emailInput.sendKeys(Properties.editorEmailValue);
        passwordInput.clear();
        passwordInput.sendKeys(Properties.editorPasswordValue);
        loginButton.click();
        return adminUsername.isDisplayed();
    }
}
