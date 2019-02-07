package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePageFactory {
    protected WebDriver driver;

    public BasePageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }
    public WebDriver getDriver()
    {
        return this.driver;
    }

}
