package com.epam.syfy.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;
    protected int timeOut = 10;

    @BeforeClass (description = "Open Chrome browser")
    public void startBrowser() {
        System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);

    }
    @AfterClass (description = "Close browser")
    public void closeBrowser () {
        driver.quit();
    }
}
