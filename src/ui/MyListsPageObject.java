package ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject{
    private static final String GO_TO_SELECTED_FOLDER = "org.wikipedia:id/item_title";
    private static final String FIND_TITLE_OF_ARTICLE_NAME_TPL = "//*[contains(@text, '{ARTICLE_TITLE}')]";

    /* TEMPLATES METHODS */
    private static String getArticleTitle(String articleTitle) {
        return FIND_TITLE_OF_ARTICLE_NAME_TPL.replace("{ARTICLE_TITLE}", articleTitle);
    }
    /* TEMPLATES METHODS */

    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void goToSelectedFolder(){
        this.waitForElementAndClick(By.id(GO_TO_SELECTED_FOLDER),
                "Cannot find folder",
                15);
    }

    public void waitForTitle(String articleTitle) {
        String getArticleTitle = getArticleTitle(articleTitle);
        this.waitForElementPresent(By.xpath(getArticleTitle),
                "Cannot find title with name " + articleTitle,
                10);
    }

    public void deleteArticle(String articleTitle) {
        String getArticleTitle = getArticleTitle(articleTitle);
        this.swipeElementToLeft(By.xpath(getArticleTitle),
                "Cannot find title with name " + articleTitle);
    }

    public void waitNotForTitle(String articleTitle) {
        String getArticleTitle = getArticleTitle(articleTitle);
        this.waitForElementNotPresent(By.xpath(getArticleTitle),
                "Cannot delete the saved article" + articleTitle,
                10);
    }

}
