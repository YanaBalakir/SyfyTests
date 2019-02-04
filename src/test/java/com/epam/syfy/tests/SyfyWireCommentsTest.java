package com.epam.syfy.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.ArticlePage;
import pageObject.HomePage;

public class SyfyWireCommentsTest extends BaseTest {

    @Test (priority = 1, description = "Verify that article contains Comments block")
    public void verifyCommentsAvailable() {
        System.out.println("Go to the latest published article / Scroll down to comments section");
        WebElement commentsSignIn = new HomePage(driver).open().openFirstArticle().scrollToComments(); //Open first article, Jump to comments section
        System.out.println("Verify that comments section available");
        Assert.assertTrue(commentsSignIn.isDisplayed(), "SpotIM commenting block is not available!"); //Verify comments section available
    }
    @Test (priority = 2, description = "Verify that user is able to login to Comments")
    public void verifySocialSignIn() {
        System.out.println("Login to Comments with Facebook");
        WebElement commentsSignOut = new ArticlePage(driver).loginToComments(); //Login to comments with Facebook
        System.out.println("Verify that user successfully logged in with Facebook");
        Assert.assertTrue(commentsSignOut.isDisplayed(), "User was not able to login!"); //Verify user logged in
    }
    @Test (priority = 3, description = "Verify that user is able to post Comment")
    public void verifyLeaveComment() {
        System.out.println("Post a comment");
        String commentsList = new ArticlePage(driver).postComment().getText(); //Post a comment
        System.out.println("Verify that comment is in the list");
        Assert.assertTrue(commentsList.contains(Properties.commentText), "Comment is not in the list!"); //Verify comment appeared
    }
}
