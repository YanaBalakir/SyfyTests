package com.epam.syfy.tests;

import browserFactory.ChromeDriverCreator;
import browserFactory.WebDriverCreator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected WebDriver driver;

    @BeforeClass (description = "Open Chrome browser")
    public void startBrowser() {
        WebDriverCreator creator = new ChromeDriverCreator();
        driver = creator.createWebDriver();
    }
    @AfterClass (description = "Close browser")
    public void closeBrowser () {
        driver.quit();
    }
}
