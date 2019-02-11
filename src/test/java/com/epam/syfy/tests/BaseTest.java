package com.epam.syfy.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;
    protected int timeOut = 10;

    @BeforeClass (description = "Open Chrome browser")
    public void startBrowser() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        /*try {
            driver = new RemoteWebDriver(new URL("http://192.168.0.105:4444/wd/hub"), options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }*/
        driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
    }
    @AfterClass (description = "Close browser")
    public void closeBrowser () {
        driver.quit();
    }
}
