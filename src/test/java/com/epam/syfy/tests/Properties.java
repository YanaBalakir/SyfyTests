package com.epam.syfy.tests;

import org.openqa.selenium.Cookie;

public class Properties {
    public static String searchRequest = "Avengers";
    public static String facebookEmailValue = "linkin_punk@mail.ru";
    public static String editorEmailValue = "alexandr.bazarnov@gmail.com";
    public static String facebookPassValue = "Bazarnov123";
    public static String editorPasswordValue = "SyfyEditor123";
    public static String commentText = "Wow! That's great!";
    public static String title = "Test Article Title";
    public static String bodyText = "Test Body text for article";
    public static String contributorName = "Editor";
    public static String articleCoverImage = "./src/test/resources/ArticleCover.jpg";
    public static String articleTag = "Batman";
    public static int timeoutWait = 10;
    public static Cookie hidePopup = new Cookie("hide_popup192983", "1"); //Cookie to prevent pop-up window display

}
