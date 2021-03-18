package ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

abstract public class MyListsPageObject extends MainPageObject{
    protected static String GO_TO_SELECTED_FOLDER;
    protected static  String FIND_TITLE_OF_ARTICLE_NAME_TPL;

    /* TEMPLATES METHODS */
    private static String getArticleTitle(String articleTitle) {
        return FIND_TITLE_OF_ARTICLE_NAME_TPL.replace("{ARTICLE_TITLE}", articleTitle);
    }
    /* TEMPLATES METHODS */

    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void goToSelectedFolder() throws IllegalAccessException {
        this.waitForElementAndClick(GO_TO_SELECTED_FOLDER,
                "Cannot find folder",
                15);
    }

    public void waitForTitle(String articleTitle) throws IllegalAccessException {
        String getArticleTitle = getArticleTitle(articleTitle);
        this.waitForElementPresent(getArticleTitle,
                "Cannot find title with name " + articleTitle,
                10);
    }

    /*public void deleteArticle(String articleTitle) {
        String getArticleTitle = getArticleTitle(articleTitle);
        this.swipeElementToLeft(getArticleTitle,
                "Cannot find title with name " + articleTitle);
    }*/

    public void waitNotForTitle(String articleTitle) throws IllegalAccessException {
        String getArticleTitle = getArticleTitle(articleTitle);
        this.waitForElementNotPresent(getArticleTitle,
                "Cannot delete the saved article" + articleTitle,
                10);
    }

}
