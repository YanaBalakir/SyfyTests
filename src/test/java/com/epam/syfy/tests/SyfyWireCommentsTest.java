package com.epam.syfy.tests;

import com.epam.syfy.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SyfyWireCommentsTest extends BaseTest {
    private String facebookEmailValue = "linkin_punk@mail.ru";
    private String facebookPassValue = "Bazarnov123";
    private String commentText = "Wow! That's great!";
    private WebDriverWait wait;
    private int timeoutWait = 10;

    @Test (priority = 1)
    public void verifyCommentsAvailable() {
        System.out.println("Open syfy.com");
        driver.get(BASE_URL);
        driver.manage().addCookie(ck);
        //Open first article
        System.out.println("Go to the latest published article");
        WebElement firstArticle = driver.findElement(By.className("teaser--semi-big-hero"));
        firstArticle.click();
        //Jump to comments section
        System.out.println("Scroll down to comments section");
        driver.get(driver.getCurrentUrl() + "#comments");
        driver.navigate().refresh();
        //Verify comments section available
        System.out.println("Verify that comments section available");
        WebElement commentsSignIn = driver.findElement(By.className("spotim__login-link"));
        Assert.assertTrue(commentsSignIn.isDisplayed());
    }
    @Test (priority = 2)
    public void verifySocialSignIn() {
        // Store the current window handle
        String winHandleBefore = driver.getWindowHandle();
        //Click Facebook login icon
        System.out.println("Click on Facebook icon to login");
        WebElement facebookButton = driver.findElement(By.xpath("//button[@title='Facebook']"));
        facebookButton.click();
        // Switch to Facebook login window
        System.out.println("Switching focus to pop-up window");
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }
        //Type Facebook login data and submit
        System.out.println("Insert Facebook login data");
        WebElement facebookEmail = driver.findElement(By.id("email"));
        facebookEmail.clear();
        facebookEmail.sendKeys(facebookEmailValue);
        WebElement facebookPassword = driver.findElement(By.id("pass"));
        facebookPassword.clear();
        facebookPassword.sendKeys(facebookPassValue);
        WebElement facebookSubmit = driver.findElement(By.name("login"));
        facebookSubmit.click(); //Login window should close itself
        // Switch back to original browser (first window)
        driver.switchTo().window(winHandleBefore);
        //Verify user logged in
        System.out.println("Verify that user successfully logged in with Facebook");
        wait = new WebDriverWait(driver, timeoutWait);
        WebElement commentsSignOut = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("spotim__logout-link")));
        Assert.assertTrue(commentsSignOut.isDisplayed());
    }
    @Test (priority = 3)
    public void verifyLeaveComment() {
        //Post a comment
        WebElement commentsFrame = driver.findElement(By.xpath("//div[contains(@class, 'sppre_frame-container')]//iframe"));
        driver.switchTo().frame(commentsFrame);
        System.out.println("Type any comment");
        WebElement commentInput = driver.findElement(By.className("ql-editor"));
        commentInput.clear();
        commentInput.sendKeys(commentText);
        System.out.println("Click Post button");
        WebElement postButton = driver.findElement(By.className("sppre_send-button"));
        postButton.click();
        //Verify comment appeared
        System.out.println("Verify that comment is in the list");
        String commentsList = driver.findElement(By.className("sppre_messages-list")).getText();
        Assert.assertTrue(commentsList.contains(commentText));
    }
}
