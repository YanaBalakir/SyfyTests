package com.epam.syfy.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.AdminLoginPage;
import pageObject.ArticlePage;
import pageObject.CreateArticlePage;

public class CreateArticleTest extends BaseTest {
    private CreateArticlePage addArticle;

    @Test (priority = 1, description = "Verify that Editor is able to login")
    public void verifyEditorLogin() {
        boolean editorUserName = new AdminLoginPage(driver).open().loginEditor(); //Login as Editor
        Assert.assertTrue(editorUserName, "Username is not displayed! Editor didn't login!");
    }
    @Test (priority = 2, description = "Verify Editor can fill in article Title")
    public void verifyArticleTitle() {
        addArticle = new CreateArticlePage(driver).open();
        String title = addArticle.fillTitle();
        Assert.assertEquals(title, Properties.title, "Title displayed doesn't equal to title inserted!");
    }
    @Test (priority = 3, description = "Verify Editor can fill in Body text")
    public void verifyArticleBody() {
        String bodyText = addArticle.fillBody();
        Assert.assertEquals(bodyText, Properties.bodyText, "Body displayed doesn't equal to Body inserted!");
    }
    @Test (priority = 4, description = "Verify Editor can set article Contributor")
    public void verifyArticleContributor() {
        String contributor = addArticle.fillContributor();
        Assert.assertTrue(contributor.contains(Properties.contributorName), "Contributor was not set!");
    }
    @Test (priority = 5, description = "Verify Editor can set article Cover Image")
    public void verifyArticleCover() {
        boolean coverUploaded = addArticle.setCoverImage();
        Assert.assertTrue(coverUploaded, "Article cover image didn't appear!");
    }
    @Test (priority = 6, description = "Verify Editor can set article Tag")
    public void verifyArticleTagAutocomplete() {
        boolean tagSelected = addArticle.setArticleTag();
        Assert.assertTrue(tagSelected, "Article tag was not set!");
    }
    @Test (priority = 7, description = "Verify Editor can reorder article tags")
    public void verifyArticleTagsReorder() {
        boolean tagsReordered = addArticle.reorderArticleTags();
        Assert.assertTrue(tagsReordered);
    }

    @Test (priority = 8, description = "Verify article created")
    public void verifyArticleCreated() {
        ArticlePage createdArticle = addArticle.submitArticleForm();
        String actualTitle = createdArticle.getArticleTitle();
        Assert.assertEquals(actualTitle, Properties.title.toUpperCase(), "Article hasn't been created!");
    }
}
