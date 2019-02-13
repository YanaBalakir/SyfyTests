package utils;

import com.epam.syfy.tests.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtil {
    private static WebDriverWait wait;

    public static boolean waitElement(WebDriver driver, By element) {
        wait = new WebDriverWait(driver, Properties.timeoutWait);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(element)).isDisplayed();
    }
}
