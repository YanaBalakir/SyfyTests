package com.epam.syfy.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.ArticlePage;
import pageObject.FacebookLoginPopup;
import pageObject.HomePage;

public class SyfyWireCommentsTest extends BaseTest {
    private ArticlePage articlePage;

    @Test (priority = 1, description = "Verify that article contains Comments block")
    public void verifyCommentsAvailable() {
        HomePage homePage = new HomePage(driver).open(); //Open SYFY home page
        articlePage = homePage.openFirstArticle(); //Open first article
        boolean commentsSignIn = articlePage.scrollToComments(); //Jump to comments section
        Assert.assertTrue(commentsSignIn, "SpotIM commenting block is not available!"); //Verify comments section available
    }
    @Test (priority = 2, description = "Verify that Facebook login popup appears")
    public void verifyFacebookLoginPopup() {
        String facebookLoginWindow = articlePage.clickFacebookLogin(); //Click Facebook login icon
        Assert.assertTrue(facebookLoginWindow != null);
    }

    @Test (priority = 3, description = "Verify that user is able to login to Comments")
    public void verifySocialSignIn() {
        boolean commentsSignOut = new FacebookLoginPopup(driver).loginToComments(); //Login to comments with Facebook
        Assert.assertTrue(commentsSignOut, "User was not able to login!"); //Verify user logged in
    }
    @Test (priority = 4, description = "Verify that user is able to post Comment")
    public void verifyLeaveComment() {
        String commentsList = articlePage.postComment().getText(); //Post a comment
        Assert.assertTrue(commentsList.contains(Properties.commentText), "Comment is not in the list!"); //Verify comment appeared
    }
}
