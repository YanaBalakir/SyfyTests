package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionsUtil {

    public static boolean reorderTags(WebDriver driver, By firstTag, By tagInput) {
        Actions action = new Actions(driver);
        WebElement tagSelected = driver.findElement(firstTag);
        String tagTitle1 = tagSelected.getText(); //Get title of the 1st tag
        action.dragAndDrop(tagSelected, driver.findElement(tagInput)).build().perform(); //Drag first tag beyond the second
        String tagTitle2 = driver.findElement(firstTag).getText(); //Get title of the 1st tag after DragAndDrop
        if(tagTitle1.equals(tagTitle2)) {
            return false;
        } else {
            return true;
        }
    }
}
